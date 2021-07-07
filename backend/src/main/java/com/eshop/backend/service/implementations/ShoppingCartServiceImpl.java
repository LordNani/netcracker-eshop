package com.eshop.backend.service.implementations;

import com.eshop.backend.model.OrderCartModel;
import com.eshop.backend.model.OrderProductModel;
import com.eshop.backend.repository.interfaces.AuthorizedUserDao;
import com.eshop.backend.exceptions.OrderCartAmountException;
import com.eshop.backend.repository.interfaces.OrderCartDao;
import com.eshop.backend.repository.interfaces.OrderProductDao;
import com.eshop.backend.service.interfaces.ShoppingCartService;
import com.eshop.backend.utils.Role;
import com.eshop.backend.repository.interfaces.ProductDao;
import com.eshop.backend.model.ProductModel;
import com.eshop.backend.model.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final OrderProductDao orderProductDao;
    private final OrderCartDao orderCartDao;
    private final AuthorizedUserDao authorizedUserDao;
    private final ProductDao productDao;

    @Autowired
    public ShoppingCartServiceImpl(OrderProductDao orderProductDao, OrderCartDao orderCartDao, AuthorizedUserDao authorizedUserDao, ProductDao productDao) {
        this.orderProductDao = orderProductDao;
        this.orderCartDao = orderCartDao;
        this.authorizedUserDao = authorizedUserDao;
        this.productDao = productDao;
    }

    @Override
    public void checkProductInStock(List<ProductModel> orderProducts) throws OrderCartAmountException {
        List<Object> problemProducts = new ArrayList<>();
        for (ProductModel product : orderProducts) {
            if (product.getProductAmount() > productDao.getAmountById(product.getId())
                    || productDao.getStatusById(product.getId()).equals("INACTIVE")) {
                new ProductModel();
                problemProducts.add(ProductModel.builder()
                        .id(product.getId())
                        .productAmount(product.getProductAmount() - productDao.getAmountById(product.getId()))
                        .productStatus(productDao.getStatusById(product.getId()))
                        .build());
            }
        }

        if (problemProducts.size() > 0) {
            throw new OrderCartAmountException(problemProducts);
        }
    }

    private AuthorizedUserModel createAnonymousUser() {
        String login = UUID.randomUUID().toString();
        authorizedUserDao.create(AuthorizedUserModel.builder()
                .userLogin(login)
                .userPassword("password")
                .userRole(Role.USER.name())
                .userName(" ")
                .userSurname(" ")
                .userRegistrationDate(new Date(System.currentTimeMillis()))
                .userStatus(Role.INACTIVE.name())
                .userAddress(" ")
                .userNumber(" ")
                .build());
        return authorizedUserDao.getByLogin(login);
    }

    private OrderCartModel unReserveOrderCart(Long userId, OrderCartModel orderCart) {
        orderCartDao.updateStatusById(orderCart.getId(), "UNRESERVED");
        List<ProductModel> unreservedProducts = productDao.getProductsByUserIdAndStatus(userId);

        for (ProductModel unreservedProduct : unreservedProducts) {
            unReserveProduct(unreservedProduct, orderCart.getId());
        }
        return orderCartDao.getLastOrderCartByUserId(userId);
    }

    private void unReserveProduct(ProductModel unreservedProducts, Long orderCartId) {
        int newAmount = productDao.getAmountById(unreservedProducts.getId()) + orderCartDao.getAmountById(unreservedProducts.getId(),orderCartId);
        productDao.updateAmountById(unreservedProducts.getId(), newAmount);
    }

    private OrderCartModel createOrderCart(AuthorizedUserModel user, String orderStatus) {
        orderCartDao.createOrderCart(OrderCartModel.builder()
                .userId(user.getId())
                .courierId((long) -1)
                .packageDescription("Description")
                .orderStatus(orderStatus)
                .totalPrice(0)
                .userName(user.getUserName())
                .deliveryTime(new Date(System.currentTimeMillis()))
                .fullAddress("no address")
                .dontDisturb(true)
                .build());
        return orderCartDao.getLastOrderCartByUserId(user.getId());
    }

    private void reserveOrderCart(List<ProductModel> orderProducts, OrderCartModel orderCart) {
        int totalPrice = 0;
        for (ProductModel product : orderProducts) {
            orderProductDao.createOrderProduct(OrderProductModel.builder()
                    .productId(product.getId())
                    .orderCardId(orderCart.getId())
                    .inCardProductAmount(product.getProductAmount())
                    .inCardProductPrice(product.getProductPrice())
                    .build());


            totalPrice += product.getProductPrice();
            int newAmount = productDao.getAmountById(product.getId()) - product.getProductAmount();
            productDao.updateAmountById(product.getId(), newAmount);
        }
        orderCartDao.updateOrderCartTotalPrice(orderCart.getId(), totalPrice);
    }


    @Override
    @Transactional
    public Long createOrder(List<ProductModel> orderProducts, Long id) {

        checkProductInStock(orderProducts);

        AuthorizedUserModel user = authorizedUserDao.getById(id);
        if (authorizedUserDao.getById(id) == null) {
            user = createAnonymousUser();
        }

        OrderCartModel orderCart = orderCartDao.getLastOrderCartByUserId(user.getId());

        if (orderCart != null) {
            if (orderCart.getOrderStatus().equals("RESERVED")) {
                unReserveOrderCart(user.getId(), orderCart);
            }
            orderCartDao.delete(orderCart.getId());
        }


        orderCart = createOrderCart(user, "RESERVED");
        reserveOrderCart(orderProducts, orderCart);

        return user.getId();
    }

    @Override
    @Transactional
    public void deleteProductFromOrder(ProductModel orderProduct, Long userId) {
        AuthorizedUserModel user = authorizedUserDao.getById(userId);
        if (user != null) {
            OrderCartModel orderCart = orderCartDao.getLastOrderCartByUserId(user.getId());
            if (orderCart != null) {
                if (orderCart.getOrderStatus().equals("RESERVED")) {
                    unReserveProduct(orderProduct, orderCart.getId());
                }
                orderProductDao.deleteProductFromOrderCart(orderProduct.getId(), orderCart.getId());
                List<ProductModel> productInCart = productDao.getProductsByUserIdAndStatus(user.getId());
                if (productInCart.size() == 0) {
                    orderCartDao.delete(orderCart.getId());
                }
            }
        }
    }

    @Override
    public List<ProductModel> getProductInCart(Long userId) {
        return productDao.getProductInShoppingCart(userId);
    }

    @Override
    @Transactional
    public Long addProductToShoppingCart(ProductModel productModel, Long userId) {
        AuthorizedUserModel user = authorizedUserDao.getById(userId);
        if (authorizedUserDao.getById(userId) == null) {
            user = createAnonymousUser();
        }

        OrderCartModel orderCart = orderCartDao.getLastOrderCartByUserId(user.getId());

        if (orderCart == null) {
            orderCart = createOrderCart(user, "UNRESERVED");
        }
        if (orderCart.getOrderStatus().equals("RESERVED")) {
            orderCart = unReserveOrderCart(user.getId(), orderCart);
        }
        //add to cart new item
        orderProductDao.addProductToCart(productModel, orderCart.getId());

        return user.getId();
    }

}

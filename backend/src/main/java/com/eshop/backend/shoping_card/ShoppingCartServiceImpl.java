package com.eshop.backend.shoping_card;

import com.eshop.backend.auth.dao.user.AuthorizedUserDao;
import com.eshop.backend.auth.exceptions.OrderCartAmountException;
import com.eshop.backend.auth.utils.Role;
import com.eshop.backend.product.dao.ProductDao;
import com.eshop.backend.product.dao.models.ProductModel;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartDao shoppingCartDao;
    private final AuthorizedUserDao authorizedUserDao;
    private final ProductDao productDao ;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartDao shoppingCartDao, AuthorizedUserDao authorizedUserDao, ProductDao productDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.authorizedUserDao = authorizedUserDao;
        this.productDao = productDao;
    }

    @Override
    public boolean checkProductInStock(List<ProductModel> orderProducts) throws OrderCartAmountException{
        List<Object> problemProducts = new ArrayList<>();
        for (ProductModel product: orderProducts) {
            if (product.getProductAmount() > shoppingCartDao.getProductsAmountById(product.getId())) {
                new ProductModel();
                problemProducts.add(ProductModel.builder()
                        .id(product.getId())
                        .productAmount(product.getProductAmount() - shoppingCartDao.getProductsAmountById(product.getId()))
                        .build());
            }
        }

        if (problemProducts.size() > 0) {
            throw new OrderCartAmountException(problemProducts);
        }
        return true;
    }

    @Override
    public Long createOrder(List<ProductModel> orderProducts, Long id) {
        AuthorizedUserModel user = authorizedUserDao.getById(id);
        if (checkProductInStock(orderProducts)) {
            if (authorizedUserDao.getById(id) == null) {
                String login = UUID.randomUUID().toString();
                authorizedUserDao.create(AuthorizedUserModel.builder()
                        .userLogin(login)
                        .userPassword("password")
                        .userRole(Role.USER.name())
                        .userName("name")
                        .userSurname("surname")
                        .userRegistrationDate(new Date(System.currentTimeMillis()))
                        .userStatus(Role.ANONYMOUS.name())
                        .userAddress("no address")
                        .userNumber("no number")
                        .build());
                user = authorizedUserDao.getByLogin(login);
            }

            //create order cart for this user
            OrderCartModel orderCart = shoppingCartDao.getOrderCartByUserId(user.getId());
            if (orderCart == null || orderCart.getOrderStatus().equals("RESERVED")) {
                shoppingCartDao.createOrderCart(OrderCartModel.builder()
                        .userId(user.getId())
                        .courierId((long) -1)
                        .packageDescription("Description")
                        .orderStatus("RESERVED")
                        .totalPrice(0)
                        .userName(user.getUserName())
                        .deliveryTime(new Date(System.currentTimeMillis()))
                        .fullAddress("no address")
                        .dontDisturb(true)
                        .build());
                orderCart = shoppingCartDao.getOrderCartByUserId(user.getId());
                //таке замовлення вже э потрібно тільки додати orderproduct
            }
            //create order product
            int totalPrice = 0;
            for (ProductModel product: orderProducts) {
                shoppingCartDao.createOrderProduct(OrderProductModel.builder()
                        .productId(product.getId())
                        .orderCardId(orderCart.getId())
                        .inCardProductAmount(product.getProductAmount())
                        .inCardProductPrice(product.getProductPrice())
                        .build());

                //decrease product amount
                totalPrice += product.getProductPrice();
                int newAmount = productDao.getAmountById(product.getId()) - product.getProductAmount();
                productDao.updateAmountById(product.getId(), newAmount);
            }
            shoppingCartDao.updateOrderCartTotalPrice(orderCart.getId(), totalPrice);
        } else {
            throw new OrderCartAmountException();
        }
        return user.getId();
    }

}

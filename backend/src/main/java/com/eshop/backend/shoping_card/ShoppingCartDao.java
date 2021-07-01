package com.eshop.backend.shoping_card;

import com.eshop.backend.product.dao.models.ProductModel;
import com.eshop.backend.utils.CrudDao;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface ShoppingCartDao extends CrudDao<ProductModel> {
    List<ProductModel> getProductsByIds(List<Long> ids);
    int getProductsAmountById(Long id);
    void createOrderCart(OrderCartModel orderCartModel);
    void createOrderProduct(OrderProductModel orderProductModel);
    OrderCartModel getOrderCartByUserId(Long id);
    void updateOrderCartTotalPrice(Long id, Integer totalPrice);

}

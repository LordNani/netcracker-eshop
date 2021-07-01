package com.eshop.backend.shoping_card;

import com.eshop.backend.product.dao.models.ProductModel;

import java.util.List;

public interface ShoppingCartService {
    boolean checkProductInStock(List<ProductModel> orderProducts);
    Long createOrder(List<ProductModel> orderProducts, Long id);
}

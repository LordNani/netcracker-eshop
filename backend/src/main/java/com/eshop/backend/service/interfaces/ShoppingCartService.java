package com.eshop.backend.service.interfaces;

import com.eshop.backend.model.ProductModel;

import java.util.List;

public interface ShoppingCartService {
    void checkProductInStock(List<ProductModel> orderProducts);
    Long createOrder(List<ProductModel> orderProducts, Long id);
    void deleteProductFromOrder(ProductModel orderProduct, Long userId);
    List<ProductModel> getProductInCart(Long userId);
    Long addProductToShoppingCart(ProductModel productModel, Long userId);
}

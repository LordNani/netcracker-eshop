package com.eshop.backend.user.order_history.services;

import com.eshop.backend.product.dao.models.ProductModel;

import java.util.List;

public interface OrderDetailsService {
    List<ProductModel> getAllProductByOrderId(Long id);
}

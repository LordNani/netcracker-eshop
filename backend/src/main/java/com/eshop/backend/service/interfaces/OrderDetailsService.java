package com.eshop.backend.service.interfaces;

import com.eshop.backend.model.ProductModel;

import java.util.List;

public interface OrderDetailsService {
    List<ProductModel> getAllProductByOrderId(Long id);
}

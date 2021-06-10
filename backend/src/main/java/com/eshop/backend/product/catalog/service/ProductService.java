package com.eshop.backend.product.catalog.service;

import com.eshop.backend.dao.Models.Product;

import java.util.List;

public interface ProductService extends MainService<Product> {
    List<Product> getProductPage(int page, int size);
    List<Product> getAllOrderByWithFilters(int page, int size, String orderBy, List<String> filter);
    List<Product> getAllOrderBy(int page, int size, String orderBy);
    List<Product> getByName (String name);
}

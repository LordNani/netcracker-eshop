package com.eshop.backend.product.catalog.service;

import java.util.List;

public interface MainService<T> {
    void create(T model);
    T getById(Long id);
    List<T> getAll();
    void update(T model);
    void delete(Long id);
}

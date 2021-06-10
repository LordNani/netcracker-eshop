package com.eshop.backend.dao.DataAccess;


import java.util.List;

public interface MainDao<T>{
    void create(T model);

    T getById(int id);

    List<T> getAll();

    void update(T model);

    void delete(Long id);
}

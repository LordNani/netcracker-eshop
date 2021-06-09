package com.eshop.backend.DAO.DataAccess.Product;

import com.eshop.backend.DAO.DataAccess.MainDao;
import com.eshop.backend.DAO.Models.Product;

import java.util.List;

public interface ProductDao extends MainDao <Product>{
    List<Product> getProductPage(int page, int size);
    List<Product> getAllOrderByWithFilters(int page, int size, String orderBy, List<String> filter);
    List<Product> getAllOrderBy(int page, int size, String orderBy);
}

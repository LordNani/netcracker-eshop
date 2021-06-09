package com.eshop.backend.product.catalog.service;

import com.eshop.backend.DAO.DataAccess.Product.ProductDao;
import com.eshop.backend.DAO.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    ProductDao productDao;

    @Autowired
    public ProductServiceImp(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> getProductPage(int page, int size) {
        page = getPageNumeration(page, size);
        return productDao.getProductPage(page, size);
    }

    @Override
    public List<Product> getAllOrderByWithFilters(int page, int size, String orderBy, List<String> filter) {
        page = getPageNumeration(page, size);
        return productDao.getAllOrderByWithFilters(page, size, orderBy, filter);
    }

    @Override
    public List<Product> getAllOrderBy(int page, int size, String orderBy) {
        page = getPageNumeration(page, size);
        return productDao.getAllOrderBy(page, size, orderBy);
    }

    public int getPageNumeration(int page, int size){
        if(page > 1)
            page = (page - 1) * size + 1;
        return page;
    }

    @Override
    public void create(Product model) {

    }

    @Override
    public Product getById(int id) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public void update(Product model) {

    }

    @Override
    public void delete(Long id) {

    }
}

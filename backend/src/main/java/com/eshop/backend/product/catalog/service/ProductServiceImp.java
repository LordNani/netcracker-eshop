package com.eshop.backend.product.catalog.service;

import com.eshop.backend.dao.DataAccess.Product.ProductDao;
import com.eshop.backend.dao.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    ProductDao productdao;

    @Autowired
    public ProductServiceImp(ProductDao productdao) {
        this.productdao = productdao;
    }

    @Override
    public List<Product> getProductPage(int page, int size) {
        page = getPageNumeration(page, size);
        return productdao.getProductPage(page, size);
    }

    @Override
    public List<Product> getAllOrderByWithFilters(int page, int size, String orderBy, List<String> filter) {
        page = getPageNumeration(page, size);
        return productdao.getAllOrderByWithFilters(page, size, orderBy, filter);
    }

    @Override
    public List<Product> getAllOrderBy(int page, int size, String orderBy) {
        page = getPageNumeration(page, size);
        return productdao.getAllOrderBy(page, size, orderBy);
    }

    @Override
    public List<Product> getByName(String name) {
        return productdao.getByName(name);
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
    public Product getById(Long id) {
        return productdao.getById(Math.toIntExact(id));
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

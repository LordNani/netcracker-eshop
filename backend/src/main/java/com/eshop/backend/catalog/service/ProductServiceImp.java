<<<<<<< HEAD:backend/src/main/java/com/eshop/backend/catalog/service/ProductServiceImp.java
package com.eshop.backend.catalog.service;

import com.eshop.backend.product.dao.ProductDao;
import com.eshop.backend.product.dao.models.ProductModel;
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
    public List<ProductModel> getProductPage(int page, int size) {
        page = getPageNumeration(page, size);
        return productdao.getProductPage(page, size);
    }

    @Override
    public List<ProductModel> getAllOrderByWithFilters(int page, int size, String orderBy, List<String> filter) {
        page = getPageNumeration(page, size);
        return productdao.getAllOrderByWithFilters(page, size, orderBy, filter);
    }

    @Override
    public List<ProductModel> getAllOrderBy(int page, int size, String orderBy) {
        page = getPageNumeration(page, size);
        return productdao.getAllOrderBy(page, size, orderBy);
    }

    @Override
    public List<ProductModel> getByName(String name) {
        return productdao.getByName(name);
    }

    public int getPageNumeration(int page, int size){
        if(page > 1)
            page = (page - 1) * size + 1;
        return page;
    }

    @Override
    public void create(ProductModel model) {
    }

    @Override
    public ProductModel getById(Long id) {
        return productdao.getById(id);
    }

    public List<ProductModel> getAll() {
        return null;
    }

    @Override
    public void update(ProductModel model) {

    }

    public void delete(Long id) {

    }
}
=======
package com.eshop.backend.catalog.service;

import com.eshop.backend.product.dao.ProductDao;
import com.eshop.backend.product.dao.models.ProductModel;
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
    public List<ProductModel> getProductPage(int page, int size) {
        page = getPageNumeration(page, size);
        return productdao.getProductPage(page, size);
    }

    @Override
    public List<ProductModel> getAllOrderByWithFilters(int page, int size, String orderBy, List<String> filter) {
        page = getPageNumeration(page, size);
        return productdao.getAllOrderByWithFilters(page, size, orderBy, filter);
    }

    @Override
    public List<ProductModel> getAllOrderBy(int page, int size, String orderBy) {
        page = getPageNumeration(page, size);
        return productdao.getAllOrderBy(page, size, orderBy);
    }

    @Override
    public List<ProductModel> getByName(String name) {
        return productdao.getByName(name);
    }

    public int getPageNumeration(int page, int size){
        if(page > 1)
            page = (page - 1) * size + 1;
        return page;
    }


    public ProductModel getById(Long id) {
        return productdao.getById(id);
    }

    @Override
    public void create(ProductModel productModel) {
        
    }

    @Override
    public void update(ProductModel productModel) {

    }

}
>>>>>>> develop:backend/src/main/java/com/eshop/backend/product/catalog/service/ProductServiceImp.java

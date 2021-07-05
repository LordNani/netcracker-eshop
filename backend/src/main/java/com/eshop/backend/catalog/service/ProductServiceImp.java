
package com.eshop.backend.catalog.service;

import com.eshop.backend.product.dao.ProductDao;
import com.eshop.backend.product.dao.models.FilterModel;
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
//
//    @Override
//    public List<ProductModel> getAllOrderByWithFilters(int page, int size, String orderBy, List<String> filter) {
//        page = getPageNumeration(page, size);
//        return productdao.getAllOrderByWithFilters(page, size, orderBy, filter);
//    }

//    @Override
//    public List<ProductModel> getAllOrderBy(int page, int size, String orderBy) {
//        page = getPageNumeration(page, size);
//        return productdao.getAllOrderBy(page, size, orderBy);
//    }

//    @Override
//    public List<ProductModel> getByName(String name) {
//        return productdao.getByName(name);
//    }

//    @Override
//    public List<ProductModel> getFiltered(int page, int size, FilterModel filterModel) {
//        page = getPageNumeration(page, size);
//        return productdao.getFiltered(page, size, filterModel);
//    }

    @Override
    public List<ProductModel> getSearchedOrderedFiltered(int page, int size, String search, String orderBy, FilterModel filterModel, boolean isActive) {
        page = getPageNumeration(page, size);
        return productdao.getSearchedOrderedFiltered(page, size, search, orderBy, filterModel, isActive);
    }

    @Override
    public List<String> getCategoriesOfProduct(int author, int coverType, int genre, int language, int publisher) {
        return productdao.getCategoriesOfProduct(author, coverType, genre, language, publisher);
    }

    public int getPageNumeration(int page, int size){
        if(page > 1)
            page = (page - 1) * size + 1;
        return page;
    }

    @Override
    public void create(ProductModel model) {
        productdao.create(model);
    }

    @Override
    public ProductModel getById(Long id) {
        return productdao.getById(id);
    }

    @Override
    public List<ProductModel> getPopular(int page, int size) {
        page = getPageNumeration(page, size);
        return productdao.getPopular(page, size);
    }

    @Override
    public List<ProductModel> getNew(int page, int size) {
        page = getPageNumeration(page, size);
        return productdao.getNew(page, size);
    }

    @Override
    public Integer getNumberOfSearchedOrderedFiltered(String search, String orderBy, FilterModel filterModel, boolean isActive) {
        return productdao.getNumberOfSearchedOrderedFiltered(search, orderBy, filterModel, isActive);
    }

//    public List<ProductModel> getAll() {
//        return null;
//    }

    @Override
    public void update(ProductModel model) {
        productdao.update(model);
    }


//    public void delete(Long id) {
//
//    }
}

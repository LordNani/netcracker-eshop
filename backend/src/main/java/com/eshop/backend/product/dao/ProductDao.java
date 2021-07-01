package com.eshop.backend.product.dao;

import com.eshop.backend.product.dao.models.FilterModel;
import com.eshop.backend.product.dao.models.ProductModel;
import com.eshop.backend.utils.CrudDao;

import java.util.List;

public interface ProductDao extends CrudDao<ProductModel> {
    List<ProductModel> getProductPage(int page, int size);
    List<ProductModel> getAllOrderByWithFilters(int page, int size, String orderBy, List<String> filter);
    List<ProductModel> getAllOrderBy(int page, int size, String orderBy);
    List<ProductModel> getByName(String name);
    List<ProductModel> getFiltered(int page, int size, FilterModel filterModel);
    List<ProductModel> getSearchedOrderedFiltered (int page, int size, String search, String orderBy, FilterModel filterModel);
    List<String> getCategoriesOfProduct (int author, int coverType, int genre, int language, int publisher);
    List<ProductModel> getPopular(int page, int size);
    List<ProductModel> getNew(int page, int size);
    Integer getNumberOfSearchedOrderedFiltered(String search, String orderBy, FilterModel filterModel);
    void updateAmountById(Long id, int price);
    Integer getAmountById(Long id);
}

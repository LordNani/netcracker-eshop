package com.eshop.backend.product.dao;

import com.eshop.backend.product.dao.models.FilterModel;
import com.eshop.backend.product.dao.models.ProductModel;
import com.eshop.backend.utils.CrudDao;

import java.util.List;
import java.util.Map;

public interface ProductDao extends CrudDao<ProductModel> {
    List<ProductModel> getProductPage(int page, int size);
    List<ProductModel> getAllOrderByWithFilters(int page, int size, String orderBy, List<String> filter);
    List<ProductModel> getAllOrderBy(int page, int size, String orderBy);
    List<ProductModel> getByName(String name);
    List<ProductModel> getFiltered(int page, int size, FilterModel filterModel);
    List<ProductModel> getSearchedOrderedFiltered (int page, int size, String search, String orderBy, FilterModel filterModel);
    List<String> getCategoriesOfProduct (int author, int coverType, int genre, int language, int publisher);
    Integer getNumberOfSearchedOrderedFiltered(String search, String orderBy, FilterModel filterModel);
}

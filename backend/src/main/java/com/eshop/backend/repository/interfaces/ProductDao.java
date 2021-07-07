package com.eshop.backend.repository.interfaces;

import com.eshop.backend.model.FilterModel;
import com.eshop.backend.model.ProductModel;
import com.eshop.backend.repository.generic.CrudDao;

import java.util.List;

public interface ProductDao extends CrudDao<ProductModel> {
    List<ProductModel> getProductPage(int page, int size);

    List<ProductModel> getSearchedOrderedFiltered(int page, int size, String search, String orderBy, FilterModel filterModel, boolean isActive);

    List<String> getCategoriesOfProduct(int author, int coverType, int genre, int language, int publisher);

    List<ProductModel> getPopular(int page, int size);

    List<ProductModel> getNew(int page, int size);

    List<ProductModel> getProductInShoppingCart(Long userId);

    Integer getNumberOfSearchedOrderedFiltered(String search, String orderBy, FilterModel filterModel, boolean isActive);

    void updateAmountById(Long id, int price);

    Integer getAmountById(Long id);

    String getStatusById(Long id);

    List<ProductModel> getProductsByUserIdAndStatus(Long id);
}

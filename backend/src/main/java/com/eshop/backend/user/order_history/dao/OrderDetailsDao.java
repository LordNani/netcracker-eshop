package com.eshop.backend.user.order_history.dao;

import com.eshop.backend.product.dao.models.ProductModel;
import com.eshop.backend.utils.CrudDao;

import java.util.List;


public interface OrderDetailsDao extends CrudDao<ProductModel> {
    List<ProductModel> getAllProductByOrderId(Long id);
}

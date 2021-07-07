package com.eshop.backend.repository.interfaces;

import com.eshop.backend.model.OrderProductModel;
import com.eshop.backend.model.ProductModel;
import com.eshop.backend.repository.generic.CrudDao;

import java.util.List;

public interface OrderProductDao extends CrudDao<OrderProductModel> {
    void createOrderProduct(OrderProductModel orderProductModel);
    void deleteProductFromOrderCart(Long productId, Long orderCartId);
    void addProductToCart(ProductModel productModel, Long orderCaryId);
    List<ProductModel> getAllProductByOrderId(Long id);
}

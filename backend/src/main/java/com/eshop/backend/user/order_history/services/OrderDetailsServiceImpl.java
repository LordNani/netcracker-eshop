package com.eshop.backend.user.order_history.services;

import com.eshop.backend.product.dao.models.ProductModel;
import com.eshop.backend.user.order_history.dao.OrderDetailsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    private final OrderDetailsDao orderDetailsDao;

    @Autowired
    public OrderDetailsServiceImpl(OrderDetailsDao orderDetailsDao) {
        this.orderDetailsDao = orderDetailsDao;
    }

    @Override
    public List<ProductModel> getAllProductByOrderId(Long id) {
        return orderDetailsDao.getAllProductByOrderId(id);
    }
}

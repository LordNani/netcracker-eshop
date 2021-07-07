package com.eshop.backend.service.implementations;

import com.eshop.backend.model.ProductModel;
import com.eshop.backend.repository.interfaces.OrderProductDao;
import com.eshop.backend.service.interfaces.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    private final OrderProductDao orderProductDao;

    @Autowired
    public OrderDetailsServiceImpl(OrderProductDao orderProductDao) {
        this.orderProductDao = orderProductDao;
    }

    @Override
    public List<ProductModel> getAllProductByOrderId(Long id) {
        return orderProductDao.getAllProductByOrderId(id);
    }
}

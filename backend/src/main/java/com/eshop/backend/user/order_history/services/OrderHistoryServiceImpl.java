package com.eshop.backend.user.order_history.services;

import com.eshop.backend.order_card.dao.models.OrderCardModel;
import com.eshop.backend.user.order_history.dao.OrderHistoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

    private final OrderHistoryDao orderHistoryDao;

    @Autowired
    public OrderHistoryServiceImpl(OrderHistoryDao orderHistoryDao) {
        this.orderHistoryDao = orderHistoryDao;
    }

    @Override
    public List<OrderCardModel> getAllByUserId(Long id) {
        return orderHistoryDao.getAllByUserId(id);
    }
}

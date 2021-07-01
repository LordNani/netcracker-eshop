package com.eshop.backend.user.order_history.services;

import com.eshop.backend.shoping_card.OrderCartModel;
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
    public List<OrderCartModel> getAllByUserId(Long id, int page, int size) {
        page = getPageNumeration(page, size);
        return orderHistoryDao.getAllByUserId(id, page, size);
    }

    @Override
    public Long getOrderCount(Long id) {
        return orderHistoryDao.getOrderCount(id);
    }

    public int getPageNumeration(int page, int size){
        page--;
        if(page > 0)
            page = (page) * size;
        return page;
    }
}

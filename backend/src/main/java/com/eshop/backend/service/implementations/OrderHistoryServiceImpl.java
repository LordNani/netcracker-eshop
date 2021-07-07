package com.eshop.backend.service.implementations;

import com.eshop.backend.model.OrderCartModel;
import com.eshop.backend.repository.interfaces.OrderCartDao;
import com.eshop.backend.service.interfaces.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

    private final OrderCartDao orderCartDao;

    @Autowired
    public OrderHistoryServiceImpl(OrderCartDao orderCartDao) {
        this.orderCartDao = orderCartDao;
    }

    @Override
    public List<OrderCartModel> getAllByUserId(Long id, int page, int size) {
        page = getPageNumeration(page, size);
        return orderCartDao.getAllByUserId(id, page, size);
    }

    @Override
    public Long getOrderCount(Long id) {
        return orderCartDao.getOrderCount(id);
    }

    public int getPageNumeration(int page, int size){
        page--;
        if(page > 0)
            page = (page) * size;
        return page;
    }
}

package com.eshop.backend.service.interfaces;

import com.eshop.backend.model.OrderCartModel;

import java.util.List;

public interface OrderHistoryService {
    List<OrderCartModel> getAllByUserId(Long id, int page, int size);

    Long getOrderCount(Long id);
}

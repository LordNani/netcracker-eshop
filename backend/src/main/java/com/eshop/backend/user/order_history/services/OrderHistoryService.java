package com.eshop.backend.user.order_history.services;

import com.eshop.backend.order_card.dao.models.OrderCardModel;

import java.util.List;

public interface OrderHistoryService {
    List<OrderCardModel> getAllByUserId(Long id, int page, int size);

    Long getOrderCount(Long id);
}

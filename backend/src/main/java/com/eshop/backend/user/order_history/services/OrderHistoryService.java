package com.eshop.backend.user.order_history.services;

import com.eshop.backend.shoping_card.OrderCartModel;

import java.util.List;

public interface OrderHistoryService {
    List<OrderCartModel> getAllByUserId(Long id, int page, int size);

    Long getOrderCount(Long id);
}

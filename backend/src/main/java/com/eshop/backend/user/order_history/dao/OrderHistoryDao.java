package com.eshop.backend.user.order_history.dao;

import com.eshop.backend.shoping_card.OrderCartModel;
import com.eshop.backend.utils.CrudDao;

import java.util.List;

public interface OrderHistoryDao extends CrudDao<OrderCartModel> {
    List<OrderCartModel> getAllByUserId(Long id, int page, int size);

    Long getOrderCount(Long id);
}

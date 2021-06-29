package com.eshop.backend.user.order_history.dao;

import com.eshop.backend.order_card.dao.models.OrderCardModel;
import com.eshop.backend.utils.CrudDao;

import java.util.List;

public interface OrderHistoryDao extends CrudDao<OrderCardModel> {
    List<OrderCardModel> getAllByUserId(Long id, int page, int size);

    Long getOrderCount(Long id);
}

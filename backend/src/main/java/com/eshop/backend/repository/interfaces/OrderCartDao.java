package com.eshop.backend.repository.interfaces;

import com.eshop.backend.dto.OrderCheckoutDto;
import com.eshop.backend.model.OrderCartModel;
import com.eshop.backend.repository.generic.CrudDao;

import java.util.List;

public interface OrderCartDao extends CrudDao<OrderCartModel> {
    void updateOrderCart(OrderCheckoutDto orderCheckoutDto, Long orderCartId);
    void createOrderCart(OrderCartModel orderCartModel);
    OrderCartModel getLastOrderCartByUserId(Long id);
    void updateOrderCartTotalPrice(Long id, Integer totalPrice);
    void updateStatusById(Long id, String status);
    Integer getAmountById(Long id, Long orderCardId);
    List<OrderCartModel> getAllByUserId(Long id, int page, int size);
    Long getOrderCount(Long id);
}

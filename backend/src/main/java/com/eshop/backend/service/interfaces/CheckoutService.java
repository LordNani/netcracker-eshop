package com.eshop.backend.service.interfaces;

import com.eshop.backend.dto.OrderCheckoutDto;

import java.util.Date;
import java.util.List;

public interface CheckoutService {
    List<Integer> getDeliveryHours(Date deliveryDate);

    void updateOrderCart(OrderCheckoutDto orderCheckoutDto);
}

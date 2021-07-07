package com.eshop.backend.service.implementations;

import com.eshop.backend.service.interfaces.CheckoutService;
import com.eshop.backend.dto.OrderCheckoutDto;
import com.eshop.backend.repository.interfaces.AuthorizedUserDao;
import com.eshop.backend.model.OrderCartModel;
import com.eshop.backend.repository.interfaces.CourierCalendarDao;
import com.eshop.backend.repository.interfaces.OrderCartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final OrderCartDao orderCartDao;
    private final CourierCalendarDao courierCalendarDao;
    private final AuthorizedUserDao authorizedUserDao;

    @Autowired
    public CheckoutServiceImpl(OrderCartDao orderCartDao, CourierCalendarDao courierCalendarDao, AuthorizedUserDao authorizedUserDao) {
        this.orderCartDao = orderCartDao;
        this.courierCalendarDao = courierCalendarDao;
        this.authorizedUserDao = authorizedUserDao;
    }

    @Override
    public List<Integer> getDeliveryHours(Date deliveryDate) {
        return courierCalendarDao.getHoursByDate(deliveryDate);
    }

    @Override
    @Transactional
    public void updateOrderCart(OrderCheckoutDto orderCheckoutDto) {
        authorizedUserDao.updateAfterCheckout(orderCheckoutDto);
        OrderCartModel orderCartModel = orderCartDao.getLastOrderCartByUserId(orderCheckoutDto.getUserId());
        orderCartDao.updateOrderCart(orderCheckoutDto, orderCartModel.getId());

    }
}

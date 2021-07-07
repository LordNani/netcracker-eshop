package com.eshop.backend.checkout;

import com.eshop.backend.auth.dao.user.AuthorizedUserDao;
import com.eshop.backend.courier.model.CourierModel;
import com.eshop.backend.courier.service.CourierServiceImp;
import com.eshop.backend.shoping_card.OrderCartModel;
import com.eshop.backend.shoping_card.ShoppingCartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private final CheckoutDao checkoutDao;
    private final ShoppingCartDao shoppingCartDao;
    private final AuthorizedUserDao authorizedUserDao;

    @Autowired
    public CheckoutServiceImpl(CheckoutDao checkoutDao, ShoppingCartDao shoppingCartDao, AuthorizedUserDao authorizedUserDao) {
        this.checkoutDao = checkoutDao;
        this.shoppingCartDao = shoppingCartDao;
        this.authorizedUserDao = authorizedUserDao;
    }

    @Override
    public List<Integer> getDeliveryHours(Date deliveryDate) {
        return checkoutDao.getHoursByDate(deliveryDate);
    }

    @Override
    @Transactional
    public void updateOrderCart(OrderCheckoutDto orderCheckoutDto) {
        authorizedUserDao.updateAfterCheckout(orderCheckoutDto);
        OrderCartModel orderCartModel = shoppingCartDao.getLastOrderCartByUserId(orderCheckoutDto.getUserId());
        checkoutDao.updateOrderCart(orderCheckoutDto, orderCartModel.getId());
        long hour =orderCheckoutDto.getDeliveryTime();
        Date date =orderCheckoutDto.getDate();
        int courierModel = authorizedUserDao.GetCourierForCalendar(hour, date);
        authorizedUserDao.CreateNewDateCourierCalendar(courierModel,orderCartModel.getId(),orderCheckoutDto.getDeliveryTime(), orderCheckoutDto.getDate());
    }
}

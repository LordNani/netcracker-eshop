package com.eshop.backend.auth.dao.user;


import com.eshop.backend.admin.adminDto;
import com.eshop.backend.checkout.OrderCheckoutDto;
import com.eshop.backend.courier.model.CourierModel;
import com.eshop.backend.utils.CrudDao;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;

import java.util.Date;
import java.util.List;

public interface AuthorizedUserDao extends CrudDao<AuthorizedUserModel> {
    AuthorizedUserModel getByLogin(String login);
    AuthorizedUserModel getRoleByLogin(String login);
    AuthorizedUserModel getByToken(String token);
    String getLoginById(Long id);
    void setStatus(AuthorizedUserModel user);
    void updateAfterCheckout(OrderCheckoutDto user);
    List<AuthorizedUserModel> getById(long id);
    List<AuthorizedUserModel> getBy(adminDto admin);
    public Integer GetCourierForCalendar(long hour, java.util.Date date);
    public void CreateNewDateCourierCalendar(long courierid, long ordercardid, long hour, Date date);
}

package com.eshop.backend.repository.interfaces;


import com.eshop.backend.dto.adminDto;
import com.eshop.backend.dto.OrderCheckoutDto;
import com.eshop.backend.repository.generic.CrudDao;
import com.eshop.backend.model.AuthorizedUserModel;

import java.util.List;

public interface AuthorizedUserDao extends CrudDao<AuthorizedUserModel> {
    AuthorizedUserModel getByLogin(String login);
    AuthorizedUserModel getRoleByLogin(String login);
    AuthorizedUserModel getByToken(String token);
    String getLoginById(Long id);
    AuthorizedUserModel getByLoginInfo(String login);
    void updateInfo(AuthorizedUserModel authorizedUserModel);
    void setStatus(AuthorizedUserModel user);
    void updateAfterCheckout(OrderCheckoutDto user);
    List<AuthorizedUserModel> getById(long id);
    List<AuthorizedUserModel> getBy(adminDto admin);

}

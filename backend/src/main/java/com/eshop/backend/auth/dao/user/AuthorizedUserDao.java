package com.eshop.backend.auth.dao.user;


import com.eshop.backend.admin.adminDto;
import com.eshop.backend.utils.CrudDao;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;

import java.util.List;

public interface AuthorizedUserDao extends CrudDao<AuthorizedUserModel> {

    AuthorizedUserModel getByLogin(String login);
    AuthorizedUserModel getRoleByLogin(String login);
    AuthorizedUserModel getByToken(String token);
    String getLoginById(Long id);
    void setStatus(AuthorizedUserModel user);
    void createVerificationToken(AuthorizedUserModel user, String token);


    public  List<AuthorizedUserModel> getById(long id);

    List<AuthorizedUserModel> getBy(adminDto admin);
}

package com.eshop.backend.dao.DataAccess.AuthorizedUser;


import com.eshop.backend.dao.DataAccess.MainDao;
import com.eshop.backend.dao.Models.AuthorizedUser;

public interface AuthorizedUserDao extends MainDao<AuthorizedUser> {

    AuthorizedUser getByLogin(String login);
    AuthorizedUser getByStatus(String status);
    AuthorizedUser getRoleByLogin(String login);
    AuthorizedUser getByToken(String token);
    void createVerificationToken(AuthorizedUser user, String token);

}

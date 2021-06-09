package com.eshop.backend.DAO.DataAccess.controllers;

import com.eshop.backend.DAO.DataAccess.MainDao;
import com.eshop.backend.DAO.Models.AuthorizedUser;

public interface ManagerController extends MainDao<AuthorizedUser> {
    AuthorizedUser getManagerDataByLogin(String login);
    AuthorizedUser updateManagerDataByLogin(String login);
    AuthorizedUser getManagerDataById(int id);
    AuthorizedUser updateManagerDataByID(int id);

}

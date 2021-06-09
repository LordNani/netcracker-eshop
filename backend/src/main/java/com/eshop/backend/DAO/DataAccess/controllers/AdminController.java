package com.eshop.backend.DAO.DataAccess.controllers;

import com.eshop.backend.DAO.DataAccess.MainDao;
import com.eshop.backend.DAO.Models.AuthorizedUser;

public interface AdminController extends ManagerController {
    AuthorizedUser getManagerDataByLogin(String login);
    AuthorizedUser updateManagerDataByLogin(String login);
    AuthorizedUser getManagerDataById(int id);
    AuthorizedUser updateManagerDataByID(int id);
    AuthorizedUser getCourierDataByLogin(String login);
    AuthorizedUser updateCourierDataByLogin(String login);
    AuthorizedUser getCourierDataById(int id);
    AuthorizedUser updateCourierDataById(int id);


}

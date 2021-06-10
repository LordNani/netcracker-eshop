package com.eshop.backend.dao.DataAccess.controllers;

import com.eshop.backend.dao.Models.AuthorizedUser;

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

package com.eshop.backend.dao.DataAccess.controllers;

import com.eshop.backend.dao.DataAccess.MainDao;
import com.eshop.backend.dao.Models.AuthorizedUser;

public interface CourierController extends MainDao<AuthorizedUser> {
    AuthorizedUser getCourierDataByLogin(String login);
    AuthorizedUser updateCourierDataByLogin(String login);
    AuthorizedUser getCourierDataById(int id);
    AuthorizedUser updateCourierDataById(int id);
}

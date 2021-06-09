package com.eshop.backend.DAO.DataAccess.controllers;


import com.eshop.backend.DAO.Models.AuthorizedUser;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AdminControllerImp implements AdminController {


    @Override
    public void create(AuthorizedUser model) {

    }

    @Override
    public AuthorizedUser getById(int id) {
        return null;
    }

    @Override
    public List<AuthorizedUser> getAll() {
        return null;
    }

    @Override
    public void update(AuthorizedUser model) {

    }

    @Override
    public void delete(Long id) {

    }


    @Override
    public AuthorizedUser getManagerDataByLogin(String login) {
        return null;
    }

    @Override
    public AuthorizedUser updateManagerDataByLogin(String login) {
        return null;
    }

    @Override
    public AuthorizedUser getManagerDataById(int id) {
        return null;
    }

    @Override
    public AuthorizedUser updateManagerDataByID(int id) {
        return null;
    }

    @Override
    public AuthorizedUser getCourierDataByLogin(String login) {
        return null;
    }

    @Override
    public AuthorizedUser updateCourierDataByLogin(String login) {
        return null;
    }

    @Override
    public AuthorizedUser getCourierDataById(int id) {
        return null;
    }

    @Override
    public AuthorizedUser updateCourierDataById(int id) {
        return null;
    }
}

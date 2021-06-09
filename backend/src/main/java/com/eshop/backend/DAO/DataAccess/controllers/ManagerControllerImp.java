package com.eshop.backend.DAO.DataAccess.controllers;

import com.eshop.backend.DAO.Models.AuthorizedUser;
import com.eshop.backend.DAO.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ManagerControllerImp implements  ManagerController{

    @Autowired
    JdbcTemplate template;

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

}

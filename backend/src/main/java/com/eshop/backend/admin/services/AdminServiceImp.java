package com.eshop.backend.admin.services;

import com.eshop.backend.admin.adminDto;
import com.eshop.backend.auth.dao.user.AuthorizedUserDao;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AdminServiceImp implements AdminService {
    AuthorizedUserDao authorizedUserDao;

    @Autowired
    public AdminServiceImp(AuthorizedUserDao authorizedUserDao) {
        this.authorizedUserDao = authorizedUserDao;
    }


    @Override
    public List<AuthorizedUserModel> getAllUsers() {
        return null;
    }

    @Override
    public List<AuthorizedUserModel> getAllManager() {
        return null;
    }

    @Override
    public List<AuthorizedUserModel> getAllCourier() {
        return null;
    }

    @Override
    public List<AuthorizedUserModel> getFilteredByStatusOn() {
        return null;
    }

    @Override
    public List<AuthorizedUserModel> getFilteredByStatusOff() {
        return null;
    }

    @Override
    public List<AuthorizedUserModel> getByName(String name) {
        return null;
    }

    @Override
    public List<AuthorizedUserModel> getBy(adminDto admin) {
        return authorizedUserDao.getBy(admin);
    }

    @Override
    public List<AuthorizedUserModel> getBySurname(String surname) {
        return null;
    }

    @Override
    public List<AuthorizedUserModel> getById(long id) {
        return null;
    }
}

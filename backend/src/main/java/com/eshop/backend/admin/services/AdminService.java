package com.eshop.backend.admin.services;

import com.eshop.backend.user.dao.models.AuthorizedUserModel;

import java.util.Collection;
import java.util.List;

public interface AdminService {
    public List<AuthorizedUserModel> getAllUsers() ;
    public List<AuthorizedUserModel> getAllManager();
    public List<AuthorizedUserModel> getAllCourier();
    public List<AuthorizedUserModel> getFilteredByStatusOn();
    public  List<AuthorizedUserModel> getFilteredByStatusOff();
    public List<AuthorizedUserModel> getByName(String name);
    public  List<AuthorizedUserModel> getBySurname(String surname);
    public  List<AuthorizedUserModel> getById(long id);

}

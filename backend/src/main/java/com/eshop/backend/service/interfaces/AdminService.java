package com.eshop.backend.service.interfaces;

import com.eshop.backend.dto.adminDto;
import com.eshop.backend.model.AuthorizedUserModel;

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
    public  List<AuthorizedUserModel> getBy(adminDto filterModel);
}

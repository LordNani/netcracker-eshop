package com.eshop.backend.courier.service;
import com.eshop.backend.courier.model.CourierModel;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;

import java.util.Date;
import java.util.List;

public interface CourierService {
    public List<AuthorizedUserModel> getById(long id);
    public List<CourierModel> getMyTimeTable(long courierid , String date);
    public void setOrderCartStatus(long courierid, String c);
}

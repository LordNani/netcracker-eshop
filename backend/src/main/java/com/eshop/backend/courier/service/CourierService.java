package com.eshop.backend.courier.service;
import com.eshop.backend.courier.model.CourierModel;
import com.eshop.backend.model.AuthorizedUserModel;
import java.util.List;

public interface CourierService {
    public List<AuthorizedUserModel> getById(long id);
    public List<CourierModel> getMyTimeTable(long courierid);
    public void setOrderCartStatus(long courierid, String c);
}

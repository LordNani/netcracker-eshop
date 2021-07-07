package com.eshop.backend.repository.interfaces;

import com.eshop.backend.model.CourierCalendarModel;
import com.eshop.backend.repository.generic.CrudDao;

import java.util.Date;
import java.util.List;

public interface CourierCalendarDao extends CrudDao<CourierCalendarModel> {
    List<Integer> getHoursByDate(Date deliveryDate);

}

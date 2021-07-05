package com.eshop.backend.courier.service;

import com.eshop.backend.auth.dao.user.AuthorizedUserDao;
import com.eshop.backend.courier.CustomerRowMapperForCourier;
import com.eshop.backend.courier.model.CourierModel;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourierServiceImp implements CourierService {
    AuthorizedUserDao authorizedUserDao;
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public CourierServiceImp( JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<AuthorizedUserModel> getById(long id) {return null;}

    @Override
    public List<CourierModel> getMyTimeTable(long courierid) {
        try {
            String TimeTableSQL = "SELECT " +
                    "dontdisturb,fulladdress,deliverytime,totalprice,orderstatus,hour,c.id,c.username,packagedescription " +
                    "FROM authorizeduser a " +
                    "inner join couriercalendar b " +
                    "on b.courierid = a.id " +
                    "inner join ordercart c on a.id = c.courierid " +
                    "where c.courierid = ? ";

            return jdbcTemplate.query(TimeTableSQL, new CustomerRowMapperForCourier(),courierid);
        }
        catch (DataAccessException e){}
        return null;
    }

    @Override
    public void setOrderCartStatus(long id ,String status) {
        String SQL = "UPDATE ordercart " +
                "SET orderstatus = ? " +
                "WHERE id = ?";
        jdbcTemplate.update(SQL,status,id);
    }


}

package com.eshop.backend.courier;

import com.eshop.backend.courier.model.CourierModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapperForCourier implements RowMapper<CourierModel> {

    @Override
    public CourierModel mapRow(ResultSet rs, int rowNum) throws SQLException {

        CourierModel courierModel = new CourierModel();
        courierModel.setId(rs.getLong("id"));
        courierModel.setDontdisturb(rs.getString("dontdisturb"));
        courierModel.setFulladdress(rs.getString("fulladdress"));
        courierModel.setHour(rs.getString("hour"));
        courierModel.setDeliverytime(rs.getDate("deliverytime"));
        courierModel.setTotalprice(rs.getString("totalprice"));
        courierModel.setOrderstatus(rs.getString("orderstatus"));
        courierModel.setUsername(rs.getString("username"));
        courierModel.setPackagedescription(rs.getString("packagedescription"));
        return courierModel;

    }
}

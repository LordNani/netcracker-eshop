package com.eshop.backend.checkout;

import com.eshop.backend.shoping_card.OrderCartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class CheckoutDaoImpl implements CheckoutDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CheckoutDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(OrderCartModel model) {

    }

    @Override
    public OrderCartModel getById(Long id) {
        return null;
    }

    @Override
    public List<OrderCartModel> getAll() {
        return null;
    }

    @Override
    public void update(OrderCartModel model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Integer> getHoursByDate(Date deliveryDate) {
        String sql = "SELECT hour FROM couriercalendar WHERE calendardate = ? " +
                "and calendardate >= ? group by hour";

        RowMapper<Integer> rowMapper = (rs, rowNum) ->  rs.getInt("hour");
        try {
            return jdbcTemplate.query(sql, rowMapper, deliveryDate, new Date());
        } catch (Exception e)  {
            e.toString();
            return null;
        }
    }

    @Override
    public void updateOrderCart(OrderCheckoutDto orderCheckoutDto, Long orderCartId) {
        String SQL = "update ordercart set userid = ?, " +
                "packagedescription = ?, orderstatus = ?, username = ?, " +
                "deliverytime = ?, " +
                "fulladdress = ?, dontdisturb = ?" +
                "where id = ?";

        try {
            jdbcTemplate.update(SQL, orderCheckoutDto.getUserId(), orderCheckoutDto.getComment(),
                    "INDELIVERY", orderCheckoutDto.getFirstName() + " " + orderCheckoutDto.getLastName(),
                    orderCheckoutDto.getDate(), orderCheckoutDto.getAddress(), orderCheckoutDto.isDoNotDisturb(),
                    orderCartId);
        } catch (Exception e) {
            String str = e.toString();
        }
    }
}

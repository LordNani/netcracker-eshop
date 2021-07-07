package com.eshop.backend.repository.implementations;

import com.eshop.backend.dto.OrderCheckoutDto;
import com.eshop.backend.model.OrderCartModel;
import com.eshop.backend.repository.interfaces.OrderCartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;

@Repository
public class OrderCartDaoImpl implements OrderCartDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderCartDaoImpl(JdbcTemplate jdbcTemplate) {
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
        String sql = "DELETE FROM ordercart WHERE id = ?";
        jdbcTemplate.update(sql, id);

    }

    @Override
    public void updateOrderCart(OrderCheckoutDto orderCheckoutDto, Long orderCartId) {
        String SQL = "update ordercart set userid = ?, " +
                "packagedescription = ?, orderstatus = ?, username = ?, " +
                "deliverytime = ?, " +
                "fulladdress = ?, dontdisturb = ?" +
                "where id = ?";


        jdbcTemplate.update(SQL, orderCheckoutDto.getUserId(), orderCheckoutDto.getComment(),
                "INDELIVERY", orderCheckoutDto.getFirstName() + " " + orderCheckoutDto.getLastName(),
                orderCheckoutDto.getDate(), orderCheckoutDto.getAddress(), orderCheckoutDto.isDoNotDisturb(),
                orderCartId);

    }

    @Override
    public void createOrderCart(OrderCartModel orderCartModel) {

        String SQL = "insert into ordercart (userid, courierid," +
                "packagedescription, orderstatus, totalprice, username, deliverytime," +
                "fulladdress, dontdisturb)\n" +
                "values (?,?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(SQL, orderCartModel.getUserId(), orderCartModel.getCourierId(),
                orderCartModel.getPackageDescription(), orderCartModel.getOrderStatus(),
                orderCartModel.getTotalPrice(), orderCartModel.getUserName(),
                orderCartModel.getDeliveryTime(), orderCartModel.getFullAddress(),
                orderCartModel.isDontDisturb());

    }

    @Override
    public OrderCartModel getLastOrderCartByUserId(Long id) {
        String sql = "SELECT id, userid, courierid, packagedescription, " +
                "orderstatus, totalprice, username, deliverytime, fulladdress, " +
                "dontdisturb FROM ordercart where userid = ? and orderstatus in ('RESERVED', 'UNRESERVED') order by id desc limit 1";

        RowMapper<OrderCartModel> rowMapper = (rs, rowNum) -> new OrderCartModel(
                rs.getLong("id"),
                rs.getLong("userid"),
                rs.getLong("courierid"),
                rs.getString("packagedescription"),
                rs.getString("orderstatus"),
                rs.getInt("totalprice"),
                rs.getString("username"),
                rs.getDate("deliverytime"),
                rs.getString("fulladdress"),
                rs.getBoolean("dontdisturb"));
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<OrderCartModel> getAllByUserId(Long id, int page, int size) {
        try {
            String sql = "SELECT * FROM ordercart WHERE userid = ?" +
                    "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

            PreparedStatementCreator statementCreator = con -> {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setLong(1, id);
                ps.setInt(2, page);
                ps.setInt(3, size);
                return ps;
            };

            RowMapper<OrderCartModel> rowMapper = (rs, rowNum) -> OrderCartModel.builder()
                    .id(rs.getLong("id"))
                    .userId(rs.getLong("userid"))
                    .courierId(rs.getLong("courierid"))
                    .packageDescription(rs.getString("packagedescription"))
                    .orderStatus(rs.getString("orderstatus"))
                    .totalPrice(rs.getInt("totalprice"))
                    .userName(rs.getString("username"))
                    .deliveryTime(rs.getDate("deliverytime"))
                    .fullAddress(rs.getString("fulladdress"))
                    .dontDisturb(rs.getBoolean("dontdisturb"))
                    .build();
            return jdbcTemplate.query(statementCreator, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public void updateOrderCartTotalPrice(Long id, Integer totalPrice) {
        String SQL = "update ordercart set totalprice = ? where id = ?";
        jdbcTemplate.update(SQL, totalPrice, id);
    }

    @Override
    public void updateStatusById(Long id, String status) {
        String sql = "update ordercart set orderstatus = ?\n" +
                " WHERE id = ?";
        jdbcTemplate.update(sql, status, id);

    }

    @Override
    public Integer getAmountById(Long id, Long orderCardId) {
        try {
            String sql = "SELECT incardproductamount FROM orderproduct where productid = ?" +
                    " AND ordercardid = ?";
            return jdbcTemplate.queryForObject(sql, Integer.class, id, orderCardId);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    @Override
    public Long getOrderCount(Long id) {
        String sql = "SELECT COUNT(*) FROM ordercart WHERE userid = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, id);
    }
}

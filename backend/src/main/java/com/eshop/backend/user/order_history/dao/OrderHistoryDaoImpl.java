package com.eshop.backend.user.order_history.dao;

import com.eshop.backend.shoping_card.OrderCartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;

@Repository
public class OrderHistoryDaoImpl implements OrderHistoryDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderHistoryDaoImpl(JdbcTemplate jdbcTemplate) {
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

        return Collections.emptyList();
    }

    @Override
    public void update(OrderCartModel model) {

    }

    @Override
    public void delete(Long id) {

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
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public Long getOrderCount(Long id) {
        String sql = "SELECT COUNT(*) FROM ordercart WHERE userid = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, id);
    }
}

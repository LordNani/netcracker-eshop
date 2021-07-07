package com.eshop.backend.repository.implementations;

import com.eshop.backend.model.CourierCalendarModel;
import com.eshop.backend.repository.interfaces.CourierCalendarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Repository
public class CourierCalendarDaoImpl implements CourierCalendarDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CourierCalendarDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Integer> getHoursByDate(Date deliveryDate) {
        String sql = "SELECT hour FROM couriercalendar WHERE calendardate = ? " +
                "and calendardate >= ? group by hour";

        RowMapper<Integer> rowMapper = (rs, rowNum) ->  rs.getInt("hour");
        try {
            return jdbcTemplate.query(sql, rowMapper, deliveryDate, new Date());
        } catch (EmptyResultDataAccessException e)  {
            return null;
        }
    }

    @Override
    public void create(CourierCalendarModel model) {

    }

    @Override
    public CourierCalendarModel getById(Long id) {
        return null;
    }

    @Override
    public List<CourierCalendarModel> getAll() {
        return null;
    }

    @Override
    public void update(CourierCalendarModel model) {

    }

    @Override
    public void delete(Long id) {

    }
}

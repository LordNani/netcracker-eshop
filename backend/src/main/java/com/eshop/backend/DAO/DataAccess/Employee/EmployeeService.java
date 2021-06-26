package com.eshop.backend.DAO.DataAccess.Employee;

import com.eshop.backend.DAO.Models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;
// import java.util.List;

@Service


public class EmployeeService {

    private JdbcTemplate jdbcTemplate;
    private ResultSet rs;


    @Autowired
    public EmployeeService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Employee> getEmployees() {
        String sql = ("SELECT * FROM employee");
        return jdbcTemplate.query(sql, new EmployeeMapper());
    }
    public Boolean getEmployee(String id) {
        String sql = ("SELECT * FROM employee WHERE id = ?");
        return jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, id);
                return ps.execute();
            }

        });
    }

    public Boolean createEmployee(Employee employee) {
        String sql = ("INSERT INTO employee VALUES(?,?,?,?,?,?,?)");

        return jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, employee.getId());
                ps.setString(2, employee.getFirstName());
                ps.setString(3, employee.getLastName());
                ps.setString(4, employee.getEmail());
                ps.setString(5, employee.getPhoneNumber());
                ps.setString(6, employee.getRole());
                employee.setStatus("Active");
                ps.setString(7, employee.getStatus());
                return ps.execute();
            }

        });

    }
    public Boolean deleteEmployee(String id) {
        String sql = ("DELETE FROM employee WHERE id=?");
        return jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, id);
                return ps.execute();
            }

        });

    }


    public Boolean editEmployee(Employee employee, String id) {
        String sql = ("UPDATE employee SET \"firstName\" = ?, \"lastName\" = ?, email = ?, \"phoneNumber\" = ?, role = ?, status = ? WHERE id =?");

        return jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {

                ps.setString(1, employee.getFirstName());
                ps.setString(2, employee.getLastName());
                ps.setString(3, employee.getEmail());
                ps.setString(4, employee.getPhoneNumber());
                ps.setString(5, employee.getRole());
                ps.setString(6, employee.getStatus());
                ps.setString(7, id);
                return ps.execute();
            }

        });

    }
}


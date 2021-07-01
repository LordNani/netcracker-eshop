package com.eshop.backend.utils.Employee;

import com.eshop.backend.DAO.DataAccess.Employee.EmployeeMapper;
import com.eshop.backend.DAO.Models.Employee;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Service;
import java.util.Date;
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


    public List<AuthorizedUserModel> getEmployees() {
        String sql = ("SELECT * FROM authorizeduser");
        return jdbcTemplate.query(sql, new EmployeeMapper());
    }


    public Boolean createEmployee(AuthorizedUserModel authorizedUserModel) {
        String sql = ("INSERT INTO authorizeduser (username, usersurname, userlogin, usernumber,userrole, userstatus, userpassword, userregistrationdate, useraddress) VALUES(?,?,?,?,?,?,?,?,?)");

        return jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {

//                ps.setLong(1, authorizedUserModel.getId());
                ps.setString(1, authorizedUserModel.getUserName());
                System.out.println(authorizedUserModel.getUserName());
                ps.setString(2, authorizedUserModel.getUserSurname());
                ps.setString(3, authorizedUserModel.getUserLogin());
                ps.setString(4, authorizedUserModel.getUserNumber());
                ps.setString(5, authorizedUserModel.getUserRole());
                //authorizedUserModel.setUserStatus("Active");
                ps.setString(6, authorizedUserModel.getUserStatus());
                System.out.println("Hello");
                System.out.println(authorizedUserModel.getUserStatus());
                authorizedUserModel.setUserPassword("password");
                ps.setString(7,authorizedUserModel.getUserPassword());
                authorizedUserModel.setUserRegistrationDate(new java.sql.Date(new java.util.Date().getTime()));
                ps.setDate(8,authorizedUserModel.getUserRegistrationDate());
                authorizedUserModel.setUserAddress("");
                ps.setString(9,authorizedUserModel.getUserAddress());
                return ps.execute();


            }

        });
    }

    public Boolean deleteEmployee(Long id) {
        String sql = ("DELETE FROM authorizeduser WHERE id=?");
        return jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setLong(1, id);
                return ps.execute();
            }

        });

    }
//
//
    public Boolean editEmployee(AuthorizedUserModel authorizedUserModel) {
        String sql = ("UPDATE authorizeduser SET username = ?, usersurname = ?, userlogin = ?, usernumber = ?, userrole = ?, userstatus = ? WHERE id =?");

        return jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {

                ps.setString(1, authorizedUserModel.getUserName());
                ps.setString(2, authorizedUserModel.getUserSurname());
                ps.setString(3, authorizedUserModel.getUserLogin());
                ps.setString(4, authorizedUserModel.getUserNumber());
                ps.setString(5, authorizedUserModel.getUserRole());
                ps.setString(6, authorizedUserModel.getUserStatus());
                ps.setLong(  7, authorizedUserModel.getId());
                return ps.execute();
            }

        });

    }


}


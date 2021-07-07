package com.eshop.backend.repository.mappers;

import com.eshop.backend.model.AuthorizedUserModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<AuthorizedUserModel> {

    @Override
    public AuthorizedUserModel mapRow(ResultSet resultSet, int i) throws SQLException {
       AuthorizedUserModel employee = new AuthorizedUserModel();

       employee.setId(resultSet.getLong("id"));
       employee.setUserName(resultSet.getString("username"));
       employee.setUserSurname(resultSet.getString("usersurname"));
       employee.setUserLogin(resultSet.getString("userlogin"));
       employee.setUserNumber(resultSet.getString("usernumber"));
       employee.setUserRole(resultSet.getString("userrole"));
       employee.setUserStatus(resultSet.getString("userstatus"));

       return employee;
    }
}

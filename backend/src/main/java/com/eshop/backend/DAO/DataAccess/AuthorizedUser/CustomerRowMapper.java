package com.eshop.backend.DAO.DataAccess.AuthorizedUser;

import com.eshop.backend.DAO.Models.AuthorizedUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<AuthorizedUser> {

    @Override
    public AuthorizedUser mapRow(ResultSet rs, int rowNum) throws SQLException {

        AuthorizedUser authorizedUsers = new AuthorizedUser();
        authorizedUsers.setId(rs.getLong("id"));
        authorizedUsers.setUserLogin(rs.getString("userlogin"));
        authorizedUsers.setUserPassword(rs.getString("userpassword"));
        authorizedUsers.setUserRole(rs.getString("userrole"));
        authorizedUsers.setUserName(rs.getString("username"));
        authorizedUsers.setUserRegistrationDate(rs.getDate("userregistrationdate"));
        authorizedUsers.setUserStatus(rs.getString("userstatus"));
        authorizedUsers.setUserAddress(rs.getString("useraddress"));
        authorizedUsers.setUserNumber(rs.getString("usernumber"));
        return authorizedUsers;

    }
}

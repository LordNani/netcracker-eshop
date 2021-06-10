package com.eshop.backend.dao.DataAccess.AuthorizedUser;

import com.eshop.backend.dao.Models.AuthorizedUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<AuthorizedUser> {

    @Override
    public AuthorizedUser mapRow(ResultSet rs, int rowNum) throws SQLException {

        AuthorizedUser authorizedUsers = new AuthorizedUser();
        authorizedUsers.setId(rs.getLong("id"));
        authorizedUsers.setEmail(rs.getString("user_login"));
        authorizedUsers.setPassword(rs.getString("user_password"));
        authorizedUsers.setRole(rs.getString("user_role"));
        authorizedUsers.setStatus(rs.getString("user_status"));
        return authorizedUsers;

    }
}

package com.eshop.backend.auth.dao.user;

import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<AuthorizedUserModel> {

    @Override
    public AuthorizedUserModel mapRow(ResultSet rs, int rowNum) throws SQLException {

        AuthorizedUserModel authorizedUsersModel = new AuthorizedUserModel();
        authorizedUsersModel.setId(rs.getLong("id"));
        authorizedUsersModel.setUserLogin(rs.getString("userlogin"));
        authorizedUsersModel.setUserPassword(rs.getString("userpassword"));
        authorizedUsersModel.setUserRole(rs.getString("userrole"));
        authorizedUsersModel.setUserName(rs.getString("username"));
        authorizedUsersModel.setUserRegistrationDate(rs.getDate("userregistrationdate"));
        authorizedUsersModel.setUserStatus(rs.getString("userstatus"));
        authorizedUsersModel.setUserAddress(rs.getString("useraddress"));
        authorizedUsersModel.setUserNumber(rs.getString("usernumber"));
        return authorizedUsersModel;

    }
}

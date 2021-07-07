package com.eshop.backend.repository.mappers;

import com.eshop.backend.model.AuthorizedUserModel;
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
        authorizedUsersModel.setUserSurname(rs.getString("usersurname"));
        authorizedUsersModel.setUserRegistrationDate(rs.getDate("userregistrationdate"));
        authorizedUsersModel.setUserStatus(rs.getString("userstatus"));
        authorizedUsersModel.setUserAddress(rs.getString("useraddress"));
        authorizedUsersModel.setUserNumber(rs.getString("usernumber"));
        return authorizedUsersModel;

    }
}

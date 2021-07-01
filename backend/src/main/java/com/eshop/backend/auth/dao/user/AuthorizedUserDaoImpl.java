package com.eshop.backend.auth.dao.user;

import com.eshop.backend.auth.utils.Role;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository
public class AuthorizedUserDaoImpl implements AuthorizedUserDao {

    private final JdbcTemplate jdbcTemplate;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthorizedUserDaoImpl(JdbcTemplate jdbcTemplate, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public void create(AuthorizedUserModel user) {

        String SQL = "insert into authorizeduser (userlogin, userpassword," +
                "userrole, username, usersurname, userregistrationdate, userstatus," +
                " useraddress, usernumber)\n" +
                "values (?,?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(SQL, user.getUserLogin(), user.getUserPassword(),
                Role.USER.name(), user.getUserName(), user.getUserSurname(), new Date(System.currentTimeMillis()),
                Role.ANONYMOUS.name(), user.getUserAddress(), user.getUserNumber());
    }

    @Override
    public AuthorizedUserModel getByLogin(String login) throws DataAccessException {
        try {
            String getUserSql = "SELECT * FROM authorizeduser WHERE userlogin = ?";
            AuthorizedUserModel user = jdbcTemplate.queryForObject(getUserSql, new CustomerRowMapper(), login);
            return user;
        } catch (DataAccessException e) {
            String str = e.toString();
            return null;
        }

    }

    @Override
    public AuthorizedUserModel getFilteredByStatusOn(String status) {
        return null;
    }

    //@Override
    public AuthorizedUserModel getByStatus(String status) {
        return null;
    }

    @Override
    public AuthorizedUserModel getRoleByLogin(String login) {
        try {
            String getUserSql = "SELECT * FROM authorizeduser WHERE userlogin = ?";
            return jdbcTemplate.queryForObject(getUserSql, new CustomerRowMapper(), login);
        } catch (DataAccessException e) {
            String srt = e.toString();
            return null;
        }
    }

    @Override
    public AuthorizedUserModel getByToken(String token) {
        try {
            String getUserSql = "SELECT a.id, a.userlogin , a.userpassword, a.userrole, a.username, a.usersurname," +
                    "a.userregistrationdate, a.userstatus, a.useraddress, a.usernumber from authorizeduser a\n" +
                    "inner join verificationtoken t on a.id = t.authorizeduserid\n" +
                    "WHERE t.tokenvalue = ?";
            return jdbcTemplate.queryForObject(getUserSql, new CustomerRowMapper(), token);
        } catch (DataAccessException e) {
            String str = e.toString();
            return null;
        }
    }

    @Override
    public String getLoginById(Long id) {
        try {
            String getUserSql = "SELECT userlogin FROM authorizeduser WHERE id = ?";
            RowMapper<AuthorizedUserModel> rowMapper = (rs, rowNum) -> AuthorizedUserModel.builder()
                    .userLogin(rs.getString("userlogin"))
                    .build();
            return Objects.requireNonNull(jdbcTemplate.queryForObject(getUserSql, rowMapper, id)).getUserLogin();
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public void setStatus(AuthorizedUserModel user) {
        String SQL = "update authorizeduser set userstatus = ? where id = ?";
        jdbcTemplate.update(SQL, user.getUserStatus(), user.getId());
    }

    @Override
    public void createVerificationToken(AuthorizedUserModel user, String token) {
        //save token
//        String SQL = "update users set \"user_login\" = ?,\n" +
//                "                   \"user_password\"= ?,\n" +
//                "                   \"user_role\" = ?,\n" +
//                "                   \"user_status\" = ?,\n" +
//                "                   \"user_token\" = ?\n" +
//                "                   where id = ?";
//        try {
//            jdbcTemplate.update(SQL, user.getEmail(), user.getPassword(), user.getRole(),
//                    user.getStatus(), token, user.getId());
//        } catch (Exception e) {
//            String str = e.toString();
//        }
    }


    @Override
    public List<AuthorizedUserModel> getAllUsers() {
        String getAllAuthorizedUsersSQL = "SELECT * FROM AUTHORIZEDUSER where userrole IN ('MANAGER','COURIER')";

        RowMapper<AuthorizedUserModel> rowMapper = (rs, rowNum) -> new AuthorizedUserModel(
                rs.getLong("id"),
                rs.getString("userlogin"),
                rs.getString("userpassword"),
                rs.getString("userrole"),
                rs.getString("username"),
                rs.getString("usersurname"),
                rs.getDate("userregistrationdate"),
                rs.getString("userstatus"),
                rs.getString("useraddress"),
                rs.getString("usernumber"));
        return jdbcTemplate.query(getAllAuthorizedUsersSQL, rowMapper);
    }

    @Override
    public List<AuthorizedUserModel> getAllManager() {
        String getAllAuthorizedUsersSQL = "SELECT * FROM AUTHORIZEDUSER where USERROLE='MANAGER'";

        RowMapper<AuthorizedUserModel> rowMapper = (rs, rowNum) -> new AuthorizedUserModel(
                rs.getLong("id"),
                rs.getString("userlogin"),
                rs.getString("userpassword"),
                rs.getString("userrole"),
                rs.getString("username"),
                rs.getString("usersurname"),
                rs.getDate("userregistrationdate"),
                rs.getString("userstatus"),
                rs.getString("useraddress"),
                rs.getString("usernumber"));
        return jdbcTemplate.query(getAllAuthorizedUsersSQL, rowMapper);
    }

    @Override
    public List<AuthorizedUserModel> getAllCourier() {
        String getAllAuthorizedUsersSQL = "SELECT * FROM AUTHORIZEDUSER where USERROLE='COURIER'";

        RowMapper<AuthorizedUserModel> rowMapper = (rs, rowNum) -> new AuthorizedUserModel(
                rs.getLong("id"),
                rs.getString("userlogin"),
                rs.getString("userpassword"),
                rs.getString("userrole"),
                rs.getString("username"),
                rs.getString("usersurname"),
                rs.getDate("userregistrationdate"),
                rs.getString("userstatus"),
                rs.getString("useraddress"),
                rs.getString("usernumber"));
        return jdbcTemplate.query(getAllAuthorizedUsersSQL, rowMapper);

    }

    @Override
    public List<AuthorizedUserModel> getFilteredByStatusOn() {
        String getAllAuthorizedUsersSQL = "SELECT * FROM AUTHORIZEDUSER where userstatus='ON' AND userrole IN ('MANAGER','COURIER')";

        RowMapper<AuthorizedUserModel> rowMapper = (rs, rowNum) -> new AuthorizedUserModel(
                rs.getLong("id"),
                rs.getString("userlogin"),
                rs.getString("userpassword"),
                rs.getString("userrole"),
                rs.getString("username"),
                rs.getString("usersurname"),
                rs.getDate("userregistrationdate"),
                rs.getString("userstatus"),
                rs.getString("useraddress"),
                rs.getString("usernumber"));
        return jdbcTemplate.query(getAllAuthorizedUsersSQL, rowMapper);
    }

    @Override
    public List<AuthorizedUserModel> getFilteredByStatusOff() {
        String getAllAuthorizedUsersSQL = "SELECT * FROM AUTHORIZEDUSER where userstatus='OFF' AND userrole IN ('MANAGER','COURIER') ";

        RowMapper<AuthorizedUserModel> rowMapper = (rs, rowNum) -> new AuthorizedUserModel(
                rs.getLong("id"),
                rs.getString("userlogin"),
                rs.getString("userpassword"),
                rs.getString("getRole"),
                rs.getString("getName"),
                rs.getString("getSurname"),
                rs.getDate("userregistrationdate"),
                rs.getString("UserStatus"),
                rs.getString("UserAddres"),
                rs.getString("UserNumber"));
        return jdbcTemplate.query(getAllAuthorizedUsersSQL, rowMapper);
    }

    //    @Override
    public List<AuthorizedUserModel> getBySurname(String surname) {
        String getAllAuthorizedUsersSQL = "SELECT * FROM AUTHORIZEDUSER where usersurname = " + surname;

        RowMapper<AuthorizedUserModel> rowMapper = (rs, rowNum) -> new AuthorizedUserModel(
                rs.getLong("id"),
                rs.getString("userlogin"),
                rs.getString("userpassword"),
                rs.getString("getRole"),
                rs.getString("getName"),
                rs.getString("getSurname"),
                rs.getDate("userregistrationdate"),
                rs.getString("UserStatus"),
                rs.getString("UserAddres"),
                rs.getString("UserNumber"));
        return jdbcTemplate.query(getAllAuthorizedUsersSQL, rowMapper);
    }

    @Override
    public List<AuthorizedUserModel> getById(long id) {
        String getAllAuthorizedUsersSQL = "SELECT * FROM authorizeduser where id = ?";

        RowMapper<AuthorizedUserModel> rowMapper = (rs, rowNum) -> new AuthorizedUserModel(
                rs.getLong("id"),
                rs.getString("userlogin"),
                rs.getString("userpassword"),
                rs.getString("userrole"),
                rs.getString("username"),
                rs.getString("usersurname"),
                rs.getDate("userregistrationdate"),
                rs.getString("userstatus"),
                rs.getString("useraddress"),
                rs.getString("usernumber"));
        return jdbcTemplate.query(getAllAuthorizedUsersSQL, rowMapper, id);
    }

    @Override
    public List<AuthorizedUserModel> getByName(String name) {
        String getAllAuthorizedUsersSQL = "SELECT * FROM AUTHORIZEDUSER where  USERNAME ILIKE '%" + name + "%' or USERSURNAME ILIKE '%" + name + "%'";
        System.out.println(getAllAuthorizedUsersSQL);
        RowMapper<AuthorizedUserModel> rowMapper = (rs, rowNum) -> new AuthorizedUserModel(
                rs.getLong("id"),
                rs.getString("userlogin"),
                rs.getString("userpassword"),
                rs.getString("userrole"),
                rs.getString("username"),
                rs.getString("usersurname"),
                rs.getDate("userregistrationdate"),
                rs.getString("userstatus"),
                rs.getString("useraddress"),
                rs.getString("usernumber"));
        return jdbcTemplate.query(getAllAuthorizedUsersSQL, rowMapper);
    }

    @Override
    public AuthorizedUserModel getById(Long id) {
        try {
            String getUserSql = "SELECT * FROM authorizeduser WHERE id = ?";
            return jdbcTemplate.queryForObject(getUserSql, new CustomerRowMapper(), id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public List<AuthorizedUserModel> getAll() {
        return Collections.emptyList();
    }

    @Override
    public void update(AuthorizedUserModel user) {
        String SQL = "update authorizeduser set userlogin = ?, " +
                "userpassword = ?, userrole = ?, username = ?, usersurname = ?, " +
                "userregistrationdate = ?, " +
                "userstatus = ?, useraddress = ?, usernumber = ? " +
                "where id = ?";

        try {
            jdbcTemplate.update(SQL, user.getUserLogin(), bCryptPasswordEncoder.encode(user.getUserPassword()),
                    user.getUserRole(), user.getUserName(), user.getUserSurname(), user.getUserRegistrationDate(),
                    user.getUserStatus(), user.getUserAddress(), user.getUserNumber(), user.getId());
        } catch (Exception e) {
            String str = e.toString();
        }
    }

    @Override
    public void delete(Long id) {

    }

}

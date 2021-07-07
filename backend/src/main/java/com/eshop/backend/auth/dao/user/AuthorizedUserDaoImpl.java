package com.eshop.backend.auth.dao.user;

import com.eshop.backend.admin.adminDto;
import com.eshop.backend.auth.utils.Role;
import com.eshop.backend.checkout.OrderCheckoutDto;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
                " useraddress, usernumber) " +
                "values (?,?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(SQL, user.getUserLogin(), bCryptPasswordEncoder.encode(user.getUserPassword()),
                Role.USER.name(), user.getUserName(), user.getUserSurname(), new Date(System.currentTimeMillis()),
                Role.INACTIVE.name(), user.getUserAddress(), user.getUserNumber());
    }

    @Override
    public AuthorizedUserModel getByLogin(String login) throws DataAccessException {
        try {
            String getUserSql = "SELECT * FROM authorizeduser WHERE userlogin = ?";
            return jdbcTemplate.queryForObject(getUserSql, new CustomerRowMapper(), login);
        } catch (Exception e) {
            return null;
        }

    }


    @Override
    public AuthorizedUserModel getRoleByLogin(String login) {
        try {
            String getUserSql = "SELECT * FROM authorizeduser WHERE userlogin = ?";
            return jdbcTemplate.queryForObject(getUserSql, new CustomerRowMapper(), login);
        } catch (EmptyResultDataAccessException e) {
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
        } catch (EmptyResultDataAccessException e) {
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
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void setStatus(AuthorizedUserModel user) {
        String SQL = "update authorizeduser set userstatus = ? where id = ?";
        jdbcTemplate.update(SQL, user.getUserStatus(), user.getId());
    }

    @Override
    public List<AuthorizedUserModel> getById(long id) {
        return null;
    }

    @Override
    public List<AuthorizedUserModel> getBy(adminDto admin) {
        String getAllAuthorizedUsersSQL = "SELECT * FROM authorizeduser where (USERNAME ILIKE '%' || ? || '%' or USERSURNAME ILIKE  '%' || ? || '%') AND userrole  IN (?,?) AND  userstatus IN (?,?,?)";
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
        String[] job = admin.job.split(",");
        String[] status = admin.status.split(",");
        return jdbcTemplate.query(getAllAuthorizedUsersSQL, rowMapper, admin.NS, admin.NS, job[0], job[1], status[0], status[1], status[2]);
    }

    @Override
    public Integer GetCourierForCalendar(long hour, Date date) {
        try {


            String getUserSql = " SELECT a.id FROM authorizeduser a full outer join couriercalendar b  on b.courierid = a.id  full outer join ordercart c on a.id = c.courierid " +
                    "where (hour != ?)AND(calendardate!= ?)AND (userrole = 'COURIER') order by a.id desc fetch first 1 rows only";
            return jdbcTemplate.queryForObject(getUserSql, Integer.class, hour, date);
        }catch (Exception e) {
            e.toString();
        };
        return 0;
    };

    @Override
    public void CreateNewDateCourierCalendar(long courierid, long ordercardid, long hour,Date date) {
        String SQL = " INSERT INTO couriercalendar (courierid,cartid,hour,calendardate) VALUES (?,?,?,?)";
try {
        jdbcTemplate.update(SQL,courierid,ordercardid, hour,date);
    }catch (Exception e) {
        e.toString();
    };


    }

    @Override
    public AuthorizedUserModel getById(Long id) {
        try {
            String getUserSql = "SELECT * FROM authorizeduser WHERE id = ?";
            return jdbcTemplate.queryForObject(getUserSql, new CustomerRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
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
        jdbcTemplate.update(SQL, user.getUserLogin(), bCryptPasswordEncoder.encode(user.getUserPassword()),
                user.getUserRole(), user.getUserName(), user.getUserSurname(), user.getUserRegistrationDate(),
                user.getUserStatus(), user.getUserAddress(), user.getUserNumber(), user.getId());
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void updateAfterCheckout(OrderCheckoutDto orderCheckoutDto) {
        String SQL = "update authorizeduser set username = ?, " +
                "usersurname = ?, " +
                "useraddress = ?, usernumber = ? " +
                "where id = ?";

        jdbcTemplate.update(SQL, orderCheckoutDto.getFirstName(), orderCheckoutDto.getLastName(),
                orderCheckoutDto.getAddress(), orderCheckoutDto.getPhoneNumber(), orderCheckoutDto.getUserId());
    }

}

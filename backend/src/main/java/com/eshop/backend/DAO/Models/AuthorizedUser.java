package com.eshop.backend.DAO.Models;

import lombok.*;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizedUser {
    private Long id;
    private String userLogin;
    private String userPassword;
    private String userRole;
    private String userStatus;
    private String userName;
    private Date userRegistrationDate;
    private String userAddress;
    private String userNumber;


    public AuthorizedUser(String userLogin, String userPassword, String userRole, String userStatus) {
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.userRole = userRole;
        this.userStatus = userStatus;
        this.userRegistrationDate = new Date(new java.util.Date().getTime());
    }
}

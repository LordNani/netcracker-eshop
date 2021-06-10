package com.eshop.backend.dao.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizedUser {
    private Long id;
    private String email;
    private String password;
    private String role;
    private String status;


    public AuthorizedUser(String userLogin, String userPassword, String userRole, String userStatus) {
        this.email = userLogin;
        this.password = userPassword;
        this.role = userRole;
        this.status = userStatus;
    }
}

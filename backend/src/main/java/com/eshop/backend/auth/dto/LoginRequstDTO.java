package com.eshop.backend.auth.dto;

import lombok.Data;

@Data
public class LoginRequstDTO {
    private String userLogin;
    private String userPassword;
}

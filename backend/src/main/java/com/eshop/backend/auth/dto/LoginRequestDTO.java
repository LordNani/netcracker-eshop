package com.eshop.backend.auth.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {

    private String userLogin;
    private String userPassword;
    private String recaptchaResponse;
}

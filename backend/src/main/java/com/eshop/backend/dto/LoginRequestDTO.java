package com.eshop.backend.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {

    private String userLogin;
    private String userPassword;
    private String recaptchaResponse;
}

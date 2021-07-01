package com.eshop.backend.auth.dto;

import lombok.Data;

@Data
public class ResetPasswordDTO {
    private String token;
    private String password;
    private String confirmPassword;
}

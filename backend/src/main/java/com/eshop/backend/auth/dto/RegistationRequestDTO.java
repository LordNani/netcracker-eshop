package com.eshop.backend.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistationRequestDTO {

    private String userName;
    private String userSurname;
    private String userLogin;
    private String userPassword;

}

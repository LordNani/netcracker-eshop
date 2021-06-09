package com.eshop.backend.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistationRequestDTO {

    private String firstName;
    private String lastName;
    private String userLogin;
    private String userPassword;

}

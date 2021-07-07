package com.eshop.backend.dto;

import lombok.Data;

@Data
public class UserInfoDTO {
    private Long id;
    private String userLogin;
    private String userName;
    private String userSurname;
    private String userAddress;
    private String userNumber;

}

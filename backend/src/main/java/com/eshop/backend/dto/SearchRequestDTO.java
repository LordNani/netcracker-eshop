package com.eshop.backend.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequestDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String role;
    private String status;

}


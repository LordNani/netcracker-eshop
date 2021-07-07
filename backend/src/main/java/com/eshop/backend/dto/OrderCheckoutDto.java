package com.eshop.backend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrderCheckoutDto {

    private String firstName;
    private String lastName;
    private boolean doNotDisturb;
    private String phoneNumber;
    private String comment;
    private String address;
    private Date date;
    private int deliveryTime;
    private Long userId;

}

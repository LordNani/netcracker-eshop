package com.eshop.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCartModel {

    private Long id;

    private Long userId;

    private Long courierId;

    private String packageDescription;

    private String orderStatus;

    private Integer totalPrice;

    private String userName;

    private Date deliveryTime;

    private String fullAddress;

    private boolean dontDisturb;
}

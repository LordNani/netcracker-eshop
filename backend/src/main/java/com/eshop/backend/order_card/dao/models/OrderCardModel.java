package com.eshop.backend.order_card.dao.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCardModel {
    private Long id;
    private Long userId;
    private Long courierId;
    private String packageDescription;
    private String orderStatus;
    private Integer totalPrice;
    private String userName;
    private Date deliveryTime;
    private String fullAddress;
    private Boolean dontDisturb;
}

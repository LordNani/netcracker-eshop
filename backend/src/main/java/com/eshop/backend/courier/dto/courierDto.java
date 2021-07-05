package com.eshop.backend.courier.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class courierDto {
    public long cartId;
    public long courierId;
    public String date;
    public String status;
}

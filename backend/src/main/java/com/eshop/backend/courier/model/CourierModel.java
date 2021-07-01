package com.eshop.backend.courier.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourierModel {
    private Long id;
    private String username;
    private String dontdisturb;
    private String fulladdress;
    private String hour;
    private Date deliverytime;
    private String totalprice;
    private String orderstatus;
    private String packagedescription;
}

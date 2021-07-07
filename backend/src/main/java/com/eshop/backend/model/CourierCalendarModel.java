package com.eshop.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourierCalendarModel {
    Long courierId;
    Long cartId;
    Integer hour;
    Date calendarDate;
}

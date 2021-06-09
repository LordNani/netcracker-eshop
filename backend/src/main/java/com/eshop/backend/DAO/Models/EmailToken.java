package com.eshop.backend.DAO.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailToken {

    private static final int EXPIRATION = 60 * 24;

    private Long id;
    private String token;
    private Long userId;
    private Date expiryDate;

    public EmailToken(String token, Long userId) {
        this.token = token;
        this.userId = userId;
        this.expiryDate = calculateExpiryDate();
    }

    private Date calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, EmailToken.EXPIRATION);
        return new Date(cal.getTime().getTime());
    }
}

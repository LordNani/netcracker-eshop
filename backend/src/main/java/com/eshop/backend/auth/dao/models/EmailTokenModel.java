package com.eshop.backend.auth.dao.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailTokenModel {

    private static final int EXPIRATION = 60 * 24;

    private Long id;
    private String tokenName;
    private String tokenValue;
    private LocalDateTime tokenExpiryDate;
    private Long authorizedUserId;

    public EmailTokenModel(String tokenName, String tokenValue, Long authorizedUserId) {
        this.tokenName = tokenName;
        this.tokenValue = tokenValue;
        this.authorizedUserId = authorizedUserId;
        this.tokenExpiryDate =  LocalDateTime.now().plusSeconds(EXPIRATION);
    }

}

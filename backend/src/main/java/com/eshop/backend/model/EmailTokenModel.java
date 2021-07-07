package com.eshop.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailTokenModel {

    private static final int EXPIRATION = 60 * 60 * 24;

    private Long id;
    private String tokenName;
    private String tokenValue;
    private LocalDateTime tokenExpiryDate;
    private Long authorizedUserId;

    public EmailTokenModel(String tokenName, String tokenValue, Long authorizedUserId) {
        this.tokenName = tokenName;
        this.tokenValue = tokenValue;
        this.authorizedUserId = authorizedUserId;
        this.tokenExpiryDate = LocalDateTime.now().plusSeconds(EXPIRATION);
    }

}

package com.eshop.backend.service.interfaces;

import com.eshop.backend.dto.RegistationRequestDTO;

public interface RegistrationService {
    boolean registration(RegistationRequestDTO request);

    boolean confirmUserAccount(String confirmationToken);
}

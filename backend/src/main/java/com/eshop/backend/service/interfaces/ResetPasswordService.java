package com.eshop.backend.service.interfaces;

import com.eshop.backend.dto.ForgotPasswordDTO;
import com.eshop.backend.dto.ResetPasswordDTO;
import com.eshop.backend.dto.ValidateResetTokenDTO;

public interface ResetPasswordService {
    void forgotPassword(ForgotPasswordDTO forgotPasswordDTO);

    boolean validateResetToken(ValidateResetTokenDTO validateResetTokenDTO);

    boolean resetPassword(ResetPasswordDTO resetPasswordDTO);
}

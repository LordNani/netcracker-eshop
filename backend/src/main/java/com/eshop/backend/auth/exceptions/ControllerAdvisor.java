package com.eshop.backend.auth.exceptions;

import com.eshop.backend.auth.exceptions.dto.ErrorMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler({UserAlreadyExistsException.class, NeedMailConfirmationException.class,
                        NoUserWithThisEmailException.class, NewPasswordSameAsOldException.class,
                        ChangeExistMailException.class})
    public ResponseEntity<ErrorMessageDTO> handleException(
            Exception ex, WebRequest request) {

        ErrorMessageDTO message = new ErrorMessageDTO(
                LocalDateTime.now(),
                ex.getMessage());

        return new ResponseEntity<ErrorMessageDTO>(message, HttpStatus.BAD_REQUEST);
    }

}

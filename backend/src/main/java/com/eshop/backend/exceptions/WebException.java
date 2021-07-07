package com.eshop.backend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WebException extends RuntimeException {

    private HttpStatus httpStatus;
    private List<Object> problemList;

    public WebException(String message, List<Object> problemList) {
        super(message);
        this.problemList = problemList;
    }

    public WebException(String message) {
        super(message);
    }
}

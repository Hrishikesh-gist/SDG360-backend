package com.example.SDG_calculator.Impact_calculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBussinessException(BusinessException be)
    {
        return new ResponseEntity<List<ErrorModel>>(be.getErrors(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

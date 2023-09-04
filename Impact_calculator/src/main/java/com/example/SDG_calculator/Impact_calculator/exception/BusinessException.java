package com.example.SDG_calculator.Impact_calculator.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@NoArgsConstructor
@Getter
@Setter

public class BusinessException extends RuntimeException implements Serializable {
    private transient List<ErrorModel> errors;
    public BusinessException(List<ErrorModel> errors)
    {
        this.errors=errors;
    }
}

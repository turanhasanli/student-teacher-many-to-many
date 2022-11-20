package com.example.demo.controller;


import com.example.demo.exception.NotFoundException;
import com.example.demo.model.constants.ExceptionConstants;
import com.example.demo.model.dto.ExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.ElementCollection;

import static com.example.demo.model.constants.ExceptionConstants.*;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto handle(Exception ex) {
        log.error("Exception", ex);
        return new ExceptionDto(NOT_FOUND_EXCEPTION_CODE, UNEXPECTED_EXCEPTION_MESSAGE);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handle(NotFoundException ex) {
        log.error("Exception", ex);
        return new ExceptionDto(ex.getCode(), ex.getMessage());
    }

}

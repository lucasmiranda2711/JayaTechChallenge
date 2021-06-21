package com.jaya.challenge.controller.exceptionhandler;

import com.jaya.challenge.exception.NotImplementedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(value = WebExchangeBindException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final WebExchangeBindException ex) {
        log.error(ex.getMessage());

        return new ResponseEntity<>(ex.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("\n")), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotImplementedException.class)
    protected ResponseEntity<Object> handleNotImplemented(final NotImplementedException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_IMPLEMENTED);
    }
}

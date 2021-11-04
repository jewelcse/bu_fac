package com.budev.exceptions;


import com.budev.utils.MethodUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {


    @ExceptionHandler(value= UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundException(UserNotFoundException exception){
        HttpStatus status= HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(MethodUtils.prepareErrorJSON(status,exception),status);
    }
}

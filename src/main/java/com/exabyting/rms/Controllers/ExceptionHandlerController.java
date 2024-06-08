package com.exabyting.rms.Controllers;

import com.exabyting.rms.Exception.ApiException;
import com.exabyting.rms.Exception.ResourceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlerController {


    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<?>resourceNotFoundException(Exception ex){
        return new ResponseEntity<>(new ApiException(ex.getMessage(), HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);
    }
}

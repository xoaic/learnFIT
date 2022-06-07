package com.example.se2project.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler extends Exception{

    @ExceptionHandler(Exception.class)
    public String internalServerError(){
        return "error";
    }

    @ExceptionHandler(CustomException.class)
    public String customException(){
        return "error";
    }

}

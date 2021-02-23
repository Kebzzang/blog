package com.kebzzang.blog.GlobalExceptionHandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    //어디서든 이 exception이 발생하면 여기로 오도록
    @ExceptionHandler(value=IllegalArgumentException.class)
    public String handleArgumentException(IllegalArgumentException e){

        return "<h1>"+e.getMessage()+"</h1>";
    }
}

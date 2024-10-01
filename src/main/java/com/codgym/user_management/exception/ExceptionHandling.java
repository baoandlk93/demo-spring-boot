package com.codgym.user_management.exception;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@ComponentScan("com.codgym.user_management")
public class ExceptionHandling {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception exception) {
        return new ModelAndView("error", "message", exception.getMessage());
    }
}

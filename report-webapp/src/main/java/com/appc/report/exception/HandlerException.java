package com.appc.report.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler({Throwable.class})
    public ModelAndView handleArithmeticException(Throwable ex) {
        ex.printStackTrace();
        ModelAndView mv = new ModelAndView("error/404");
        mv.addObject("exception", ex);
        return mv;
    }
}
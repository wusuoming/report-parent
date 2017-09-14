package com.appc.report.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler({Throwable.class})
    public ModelAndView handleArithmeticException(Throwable ex) {
        System.out.println("出异常了: " + ex);
        ModelAndView mv = new ModelAndView("error/404");
        mv.addObject("exception", ex);
        return mv;
    }
}
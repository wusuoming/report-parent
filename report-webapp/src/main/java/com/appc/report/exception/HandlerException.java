package com.appc.report.exception;

import basic.common.core.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class HandlerException {
    private static Logger log = LoggerFactory.getLogger(HandlerException.class);
    @Autowired
    private HttpServletRequest request;

    @ExceptionHandler({Throwable.class})
    public ModelAndView handleArithmeticException(Throwable ex) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        ex.printStackTrace();
        if (request.getRequestURI().endsWith(".html")) {
            ModelAndView mv = new ModelAndView("error/404");
            mv.addObject("exception", ex);
            if (ex instanceof BaseException) {
                mv.addObject("message", ((BaseException) ex).getDetailMessage());
            }
            return mv;
        } else {
            ModelAndView mv = new ModelAndView();
            /*	使用response返回	*/
            response.setStatus(HttpStatus.OK.value()); //设置状态码
            response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType
            response.setCharacterEncoding("UTF-8"); //避免乱码
            response.setHeader("Cache-Control", "no-cache, must-revalidate");
            try {
                if (ex instanceof BaseException) {
                    response.getWriter().write("{\"code\":\"" + ((BaseException) ex).getCode() + "\",\"message\":\"" + ((BaseException) ex).getDetailMessage() + "\"}");
                } else {
                    response.getWriter().write("{\"code\":-1,\"message\":\"" + ex.getMessage() + "\"}");

                }
            } catch (IOException e) {
                log.error("与客户端通讯异常:" + e.getMessage(), e);
            }

            log.debug("异常:" + ex.getMessage(), ex);
            return mv;
        }
    }
}
package com.zcy.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
//拦截所有异常，并跳转到指定的error页面
public class  ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHander(HttpServletRequest request, Exception e) throws Exception {
        logger.error("Requst URL : {}，Exception : {}", request.getRequestURL(),e);

//        判断状态是否被指定，如果被指定，则跳转到状态码指定的页面
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url",request.getRequestURL());
        modelAndView.addObject("exception", e);
        modelAndView.setViewName("error/error");
        return modelAndView;

    }
}

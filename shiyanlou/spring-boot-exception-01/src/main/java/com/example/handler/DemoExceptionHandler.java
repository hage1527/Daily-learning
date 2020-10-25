package com.example.handler;

import com.example.exception.JsonException;
import com.example.exception.PageException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class DemoExceptionHandler {

    /**
     * 处理 JsonException
     * @param exception
     * @return 错误信息
     */
    @ResponseBody
    @ExceptionHandler(JsonException.class)
    public String jsonErrorHandler(JsonException exception){
        System.out.println("接口异常，返回JSON数据");
        return exception.toString();
    }

    /**
     * 处理 PageException
     * @param exception
     * @return 跳转到错误页面
     */
    @ExceptionHandler(PageException.class)
    public ModelAndView pageErrorHandler(PageException exception){
        System.out.println("页面访问异常，跳转到错误页面"+exception);
        ModelAndView view = new ModelAndView();
        view.setViewName("errorPage");
        view.addObject("message", exception.getMessage());
        return view;
    }

}


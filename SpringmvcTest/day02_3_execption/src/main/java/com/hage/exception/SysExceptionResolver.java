package com.hage.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Administrator
 * @date: 2019/10/14
 * Description: 自定义异常处理器
 */
public class SysExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //获取到异常对象
        SysException sysException = null;
        if (ex instanceof SysException ){
            sysException = (SysException) ex;
        }else {
            sysException= new SysException("系统维护中");
        }
        //创建modelandview
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errName", sysException.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}

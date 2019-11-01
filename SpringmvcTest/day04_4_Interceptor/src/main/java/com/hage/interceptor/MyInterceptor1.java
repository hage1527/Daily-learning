package com.hage.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Administrator
 * @date: 2019/10/15
 * Description: 自定义拦截器
 */
public class MyInterceptor1 implements HandlerInterceptor {
    /**
     * 调用在处理器映射器选择合适的处理器之后，但是在处理器适配器调用处理器（controller）执行之前
     * return true 放行，执行下一个拦截器，如果没有执行controller中的方法
     * return false 不放行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle执行了。。。。。");
        return true;
    }

    /**
     * 调用在处理器适配器执行处理器之后，在DispatcherServlet视图渲染之前
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle执行了。。。。。");
    }

    /**
     * handler执行结束后，视图渲染后
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion执行了。。。。。。");
    }
}

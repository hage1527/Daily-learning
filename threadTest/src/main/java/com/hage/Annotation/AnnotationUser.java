package com.hage.Annotation;


/**
 * @author: Administrator
 * @date: 2019/11/10
 * Description: 使用注解
 */
public class AnnotationUser {
    @MethodAnnotation(value = "method方法注解")
    public void method(){
        System.out.println("method 方法执行");
    }
}

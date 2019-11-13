package com.hage.Annotation;

import java.lang.annotation.*;

/**
 * @author: Administrator
 * @date: 2019/11/10
 * Description: 自定义方法注解
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface MethodAnnotation {
    String value() default "这是一个方法注解";
}

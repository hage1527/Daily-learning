package com.hage.Annotation;

import java.lang.reflect.Method;

/**
 * @author: Administrator
 * @date: 2019/11/10
 * Description: 解释注解
 */
public class AnnotationTest {
    public static void main(String[] args) throws NoSuchMethodException {
        // 实例化类
        AnnotationUser user = new AnnotationUser();
        // 通过反射获取类信息
        Class<? extends AnnotationUser> userClass = user.getClass();
        // 类获取method方法
        Method method = userClass.getMethod("method");
        // 获取method方法上的注解信息
        MethodAnnotation annotation = method.getAnnotation(MethodAnnotation.class);
        // 通过注解信息执行操作
        System.out.println(annotation.value());
    }
}

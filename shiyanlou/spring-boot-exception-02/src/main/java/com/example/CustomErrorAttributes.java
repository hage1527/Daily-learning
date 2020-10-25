package com.example;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 自定义异常数据
 */
@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        // 调用父类的方法获取包装好的异常数据
        Map<String, Object> attributes = super.getErrorAttributes(webRequest, options);
        if (attributes.get("status").equals(404)) {
            // 添加自定义属性值
            attributes.put("company", "shiyanlou");
        }
        return attributes;
    }
}

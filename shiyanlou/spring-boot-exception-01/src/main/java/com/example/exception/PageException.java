package com.example.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 页面异常类
 */
@Getter
@Setter
public class PageException extends RuntimeException {
    /**
     * 错误状态码
     */
    private Integer code;

    public PageException() {

    }

    public PageException(String message, Integer code) {
        // 继承throwable类的属性，表示错误信息
        super(message);
        this.code = code;
    }

}

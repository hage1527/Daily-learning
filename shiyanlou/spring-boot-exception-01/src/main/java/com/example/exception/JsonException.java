package com.example.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * JSON异常类
 */
@Getter
@Setter
public class JsonException extends RuntimeException {
    /**
     * 错误状态码
     */
    private Integer code;

    public JsonException() {

    }

    public JsonException(String message, Integer code) {
        // 继承throwable类的属性，表示错误信息
        super(message);
        this.code = code;
    }

}

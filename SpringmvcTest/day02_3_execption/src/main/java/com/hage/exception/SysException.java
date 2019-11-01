package com.hage.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: Administrator
 * @date: 2019/10/14
 * Description: 自定义异常类
 */
@Getter
@Setter
public class SysException extends Exception {
    private String message;

    public SysException(String message) {
        this.message = message;
    }
}

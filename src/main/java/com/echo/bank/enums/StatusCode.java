package com.echo.bank.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author echo
 * @version 1.0
 * Create by 2023/5/24 14:24
 */
@Getter
@ToString
@AllArgsConstructor
public enum StatusCode {

    /**
     * 成功
     */
    SUCCESS(200, "成功"),
    ERROR(500, "服务器内部错误"),
    FAIL(999, "自定义异常");

    private int code;
    private String msg;

}

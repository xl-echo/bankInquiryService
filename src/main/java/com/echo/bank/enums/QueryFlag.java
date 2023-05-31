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
public enum QueryFlag {

    /**
     * 成功
     */
    BALANCE("余额", "balance"),
    DETAIL("明细", "detail"),
    ;

    private String name;
    private String desc;

}

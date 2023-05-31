package com.echo.bank.pojo;

import lombok.Data;

/**
 * @author echo
 * @version 1.0
 * Create by 2023/5/29 15:15
 */
@Data
public class BalanceResult {

    /**
     * 返回状态
     */
    private String retCode;

    /**
     * 返回描述
     */
    private String retMsg;

    /**
     * 余额
     */
    private Balance balance;

}

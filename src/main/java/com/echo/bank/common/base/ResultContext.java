package com.echo.bank.common.base;

import com.echo.bank.pojo.BalanceResult;
import lombok.Data;

/**
 * @author echo
 * @version 1.0
 * Create by 2023/5/24 14:35
 */
@Data
public class ResultContext {

    /**
     * 程序返回状态码
     */
    private String status;

    /**
     * 描述
     */
    private String msg;

    private BalanceResult balanceResult;

}


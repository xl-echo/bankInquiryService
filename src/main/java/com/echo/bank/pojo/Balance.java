package com.echo.bank.pojo;

import lombok.Data;

/**
 * @author echo
 * @version 1.0
 * Create by 2023/5/29 15:23
 */
@Data
public class Balance {

    /**
     * 查询账号
     */
    private String accountNo;

    /**
     * 实时余额
     */
    private String realBalance;

    /**
     * 可用余额
     */
    private String availableBalance;

}

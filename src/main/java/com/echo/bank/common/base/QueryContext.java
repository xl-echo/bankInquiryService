package com.echo.bank.common.base;

import lombok.Data;

/**
 * @author echo
 * @version 1.0
 * Create by 2023/5/24 14:24
 */
@Data
public class QueryContext {

    /**
     * 查询类型：balance: 余额, detail：明细
     */
    private String queryFlag;

    /**
     * 银行编号
     */
    private String bankCode;

    /**
     * 币种
     */
    private String currencyCode;

    /**
     * 请求唯一号
     */
    private String traceId;

    /**
     * 跳板机ID
     */
    private String machineId;

    /**
     * 登录ip
     */
    private String signIp;

    /**
     * 登录端口
     */
    private String signPort;

    /**
     * 交易ip
     */
    private String tradeIp;

    /**
     * 交易端口
     */
    private String tradePort;

    /**
     * 渠道ID
     */
    private String channelId;

}

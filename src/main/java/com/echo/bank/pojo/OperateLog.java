package com.echo.bank.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 银行请求日志记录表
 *
 * @author echo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperateLog {
    /**
     * 自增id
     */
    private Integer id;

    /**
     * 请求参数
     */
    private String paramContext;

    /**
     * 返回参数
     */
    private String resultContext;

    /**
     * 请求类型 1:
     */
    private String type;

    /**
     * 被请求方法上标记的日志描述
     */
    private String desc;

    /**
     * 请求或被请求端Ip
     */
    private String requestIp;

    /**
     * 请求状态
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private String updateUser;
}
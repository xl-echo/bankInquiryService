package com.echo.bank.pojo;

import java.util.Date;

/**
 * 银行请求日志记录表
 * @author echo
 */
public class SpringboardMachineAddr {
    /**
     * 自增id
     */
    private Long id;

    /**
     * 银行编号
     */
    private String bankCode;

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

    /**
     * 状态 0: 无效 1: 有效
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getSignIp() {
        return signIp;
    }

    public void setSignIp(String signIp) {
        this.signIp = signIp;
    }

    public String getSignPort() {
        return signPort;
    }

    public void setSignPort(String signPort) {
        this.signPort = signPort;
    }

    public String getTradeIp() {
        return tradeIp;
    }

    public void setTradeIp(String tradeIp) {
        this.tradeIp = tradeIp;
    }

    public String getTradePort() {
        return tradePort;
    }

    public void setTradePort(String tradePort) {
        this.tradePort = tradePort;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
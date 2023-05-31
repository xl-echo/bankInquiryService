package com.echo.bank.dao;

import com.echo.bank.pojo.SpringboardMachineAddr;

import java.util.List;

public interface SpringboardMachineAddrMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SpringboardMachineAddr record);

    int insertSelective(SpringboardMachineAddr record);

    SpringboardMachineAddr selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SpringboardMachineAddr record);

    int updateByPrimaryKey(SpringboardMachineAddr record);

    List<SpringboardMachineAddr> selectByBankCode(String bankCode);
}
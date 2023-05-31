package com.echo.bank.service;

import com.echo.bank.dao.SpringboardMachineAddrMapper;
import com.echo.bank.pojo.SpringboardMachineAddr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author echo
 * @version 1.0
 * Create by 2023/5/25 14:00
 */
@Service
public class SpringboardMachineAddrService {

    @Autowired
    private SpringboardMachineAddrMapper springboardMachineAddrMapper;

    public void insert(SpringboardMachineAddr record) {
        springboardMachineAddrMapper.insert(record);
    }

    public List<SpringboardMachineAddr> selectByBankCode(String bankCode) {
        return springboardMachineAddrMapper.selectByBankCode(bankCode);
    }

}

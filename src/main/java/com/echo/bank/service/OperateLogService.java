package com.echo.bank.service;

import com.echo.bank.dao.OperateLogMapper;
import com.echo.bank.pojo.OperateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author echo
 * @version 1.0
 * Create by 2023/5/25 13:56
 */
@Service
public class OperateLogService {

    @Autowired
    private OperateLogMapper operateLogMapper;

    public void insert(OperateLog record) {
        operateLogMapper.insert(record);
    }
}

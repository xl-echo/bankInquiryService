package com.echo.bank.framework.aspect;

import com.echo.bank.dao.OperateLogMapper;
import com.echo.bank.pojo.OperateLog;
import com.echo.bank.service.OperateLogService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author echo
 * @version 1.0
 * Create by 2023/5/25 9:48
 */
@Slf4j
public class OperationLogSaveThread implements Runnable {

    private OperateLogService operateLogService;
    private OperateLog operateLog;

    public OperationLogSaveThread(OperateLogService operateLogService, OperateLog operateLog) {
        this.operateLogService = operateLogService;
        this.operateLog = operateLog;
    }

    @Override
    public void run() {
        log.info("开始异步执行记录请求日志!");
        try {
            operateLogService.insert(operateLog);
        } catch (Exception e) {
            log.info("异步记录日志出现报错！msg: {}", e.getMessage());
        }
    }
}

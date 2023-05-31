package com.echo.bank.controller;

import com.echo.bank.common.base.QueryContext;
import com.echo.bank.common.base.ResultContext;
import com.echo.bank.framework.aspect.OperationLog;
import com.echo.bank.framework.ioc.SpringContextUtils;
import com.echo.bank.framework.result.Result;
import com.echo.bank.handler.ProcessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 银行查询服务
 *
 * @author echo
 * @version 1.0
 * Create by 2023/5/23 14:59
 */
@Slf4j
@RestController
@RequestMapping(value = "/query")
public class BankInquiryController {

    @OperationLog(desc = "银行查询服务")
    @PostMapping(value = "/accountInfo")
    public Result<ResultContext> getDetail(@RequestBody QueryContext queryContext) {
        log.info("开始执行银行查询服务!");
        ProcessHandler bean = SpringContextUtils.getBean(queryContext.getBankCode(), ProcessHandler.class);
        ResultContext invoke = bean.invoke(queryContext);
        log.info("银行查询服务执行完成!");
        return Result.packageResultMethod(invoke);
    }

}

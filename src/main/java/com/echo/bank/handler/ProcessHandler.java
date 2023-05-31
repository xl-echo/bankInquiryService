package com.echo.bank.handler;

import com.echo.bank.common.base.QueryContext;
import com.echo.bank.common.base.ResultContext;

/**
 * @author echo
 * @version 1.0
 * Create by 2023/5/24 14:21
 */
public interface ProcessHandler {

    /**
     * 顶级父类接口，用于定义查询服务执行方法
     *
     * @param queryContext 查询参数
     * @return ResultContext 返回值
     */
    ResultContext invoke(QueryContext queryContext);

}

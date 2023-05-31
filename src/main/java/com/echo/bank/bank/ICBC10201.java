package com.echo.bank.bank;

import cn.hutool.core.lang.TypeReference;
import com.alibaba.fastjson.JSON;
import com.echo.bank.common.base.ParserEngine;
import com.echo.bank.common.base.QueryContext;
import com.echo.bank.common.base.ResultContext;
import com.echo.bank.framework.ioc.SpringContextUtils;
import com.echo.bank.handler.AbstractProcessHandler;
import com.echo.bank.pojo.BalanceResult;
import com.echo.bank.utils.HttpUtils;
import com.echo.bank.utils.SocketConnector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 银行查询服务
 *
 * @author echo
 * @version 1.0
 * Create by 2023/5/23 14:59
 */
@Slf4j
@Scope(value = "prototype")
@Component(value = "102")
public class ICBC10201 extends AbstractProcessHandler {

    /**
     * 以下代码是真实业务逻辑，避开了真实的数据拼接，需要根据各银行的拼接情况来拼接请求或者返回数据
     *
     * @param queryContext
     * @return
     */
    @Override
    protected ResultContext queryBalance(QueryContext queryContext) {
        log.info("开始执行余额查询！方法：queryBalance被执行");

        // bean to string xml
        String s = javaBeanToStrXml(queryContext);
        log.info("bean to string xml success! xml content: {}", s);

        // to bank sign
        String signMsg = "urj&*^534faj;";
        String signStr = sign(signMsg);
        log.info("sign success! signStr: {}", signStr);

        // send query content into bank
        String result = sendQueryContentIntoBank(signStr + JSON.toJSONString(queryContext));

        // string xml to bean
        BalanceResult balanceResult = strXmlToJavaBean(bankDataModel(), queryContext);
        log.info("string xml to bean sccess! bean content: {}", JSON.toJSONString(balanceResult));

        ResultContext resultContext = new ResultContext();
        resultContext.setStatus("0000");
        resultContext.setBalanceResult(balanceResult);
        return resultContext;
    }

    @Override
    protected ResultContext queryDetail(QueryContext queryContext) {
        log.info("开始执行明细查询！方法：queryDetail被执行");
        ResultContext resultContext = new ResultContext();
        resultContext.setStatus("0000");
        return new ResultContext();
    }

    private String sendQueryContentIntoBank(String queryContent) {
        SocketConnector socketConnector = new SocketConnector("127.0.0.1", 8081);
        return socketConnector.request(queryContent);
    }

    private String sign(String signMsg) {
        return HttpUtils.sendPostJson(signMsg, "");
    }

    private String javaBeanToStrXml(QueryContext queryContext) {
        String ruleModeName = "templates/request-" + queryContext.getBankCode() + "-" + queryContext.getQueryFlag() + ".vm";
        ParserEngine parserEngine = SpringContextUtils.getBean("parserEngine", ParserEngine.class);

        Map<String, Object> stringObjectMap = JSON.parseObject(JSON.toJSONString(queryContext), new TypeReference<Map<String, Object>>() {
        });
        return parserEngine.beanToStringXml(ruleModeName, stringObjectMap);
    }

    private BalanceResult strXmlToJavaBean(String bankResultStrXml, QueryContext queryContext) {
        ParserEngine parserEngine = SpringContextUtils.getBean("parserEngine", ParserEngine.class);
        return parserEngine.parseXml2Object(bankResultStrXml, "response-" + queryContext.getBankCode() + "-" + queryContext.getQueryFlag());
    }

    /**
     * 模拟银行返回数据
     *
     * @return
     */
    private String bankDataModel() {
        return "<?xml version=\"1.0\" encoding=\"Big5\" ?>\n" +
                "<content>\n" +
                "    <code>000000</code>\n" +
                "    <msg>查询成功</msg>\n" +
                "    <trans>\n" +
                "        <info>\n" +
                "            <accNo>1000123324122568</accNo>\n" +
                "            <bal>321920.000000</bal>\n" +
                "            <avabal>320920.000000</avabal>\n" +
                "        </info>\n" +
                "    </trans>\n" +
                "</content>";
    }
}

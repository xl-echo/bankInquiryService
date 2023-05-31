package com.echo.bank.handler;

import com.echo.bank.common.base.QueryContext;
import com.echo.bank.common.base.ResultContext;
import com.echo.bank.enums.QueryFlag;
import com.echo.bank.framework.exception.BankException;
import com.echo.bank.framework.ioc.SpringContextUtils;
import com.echo.bank.pojo.SpringboardMachineAddr;
import com.echo.bank.service.SpringboardMachineAddrService;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Random;

/**
 * @author echo
 * @version 1.0
 * Create by 2023/5/24 14:21
 */
@Slf4j
public abstract class AbstractProcessHandler implements ProcessHandler {

    @Override
    public ResultContext invoke(QueryContext queryContext) {
        String queryFlag = queryContext.getQueryFlag();
        log.info("开始执行查询，查询类型为：{}", queryFlag);

        // 参数校验
        checkParam(queryContext);

        // 公共参数补齐
        paramComplement(queryContext);

        // 负载均衡
        loadBalance(queryContext);

        // 执行查询
        if (QueryFlag.BALANCE.getDesc().equals(queryFlag)) {
            return this.queryBalance(queryContext);
        }
        if (QueryFlag.DETAIL.getDesc().equals(queryFlag)) {
            return this.queryDetail(queryContext);
        }
        return new ResultContext();
    }

    /**
     * 负载均衡
     * @param queryContext 入参
     */
    private void loadBalance(QueryContext queryContext) {
        SpringboardMachineAddrService springboardMachineAddrService = SpringContextUtils.getBean("springboardMachineAddrService", SpringboardMachineAddrService.class);
        List<SpringboardMachineAddr> springboardMachineAddrs = springboardMachineAddrService.selectByBankCode(queryContext.getBankCode());

        if(CollectionUtils.isEmpty(springboardMachineAddrs)) {
            throw new BankException("未查询到对应的负载均衡地址信息!");
        }
        Random rand = new Random();
        int randomIndex = rand.nextInt(springboardMachineAddrs.size());
        SpringboardMachineAddr springboardMachineAddr = springboardMachineAddrs.get(randomIndex);

        queryContext.setChannelId(springboardMachineAddr.getChannelId());
        queryContext.setMachineId(springboardMachineAddr.getMachineId());
        queryContext.setSignIp(springboardMachineAddr.getSignIp());
        queryContext.setSignPort(springboardMachineAddr.getSignPort());
        queryContext.setTradeIp(springboardMachineAddr.getTradeIp());
        queryContext.setTradePort(springboardMachineAddr.getTradePort());
    }

    /**
     * 公共参数补齐
     *
     * @param queryContext 入参
     */
    private void paramComplement(QueryContext queryContext) {

    }

    /**
     * 使用规则引擎校验传入参数
     *
     * @param queryContext 入参
     */
    private void checkParam(QueryContext queryContext) {
        log.info("开始执行规则引擎参数校验");
        KieSession kieSession = SpringContextUtils.getBean("kieSession", KieSession.class);
        // 执行某个组的规则
        kieSession.getAgenda().getAgendaGroup("checkParam").setFocus();
        kieSession.insert(queryContext);
        // 执行所有规则
        kieSession.fireAllRules();
        // 执行指定规则
//        kieSession.fireAllRules(new AgendaFilter() {
//            @Override
//            public boolean accept(Match match) {
//                String ruleName = match.getRule().getName();
//                return Objects.equals(ruleName, "checkQueryBalanceParamCurrencyCode");
//            }
//        });
        kieSession.dispose();
        log.info("规则引擎参数校验完成");
    }


    /**
     * 查询账户余额
     *
     * @param queryContext
     * @return
     */
    protected abstract ResultContext queryBalance(QueryContext queryContext);

    /**
     * 查询账户明细
     *
     * @param queryContext
     * @return
     */
    protected abstract ResultContext queryDetail(QueryContext queryContext);

}

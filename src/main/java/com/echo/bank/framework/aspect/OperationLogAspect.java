package com.echo.bank.framework.aspect;

import com.alibaba.fastjson.JSONObject;
import com.echo.bank.enums.StatusCode;
import com.echo.bank.framework.ioc.SpringContextUtils;
import com.echo.bank.framework.thread.ExecutorServiceUtil;
import com.echo.bank.pojo.OperateLog;
import com.echo.bank.service.OperateLogService;
import com.echo.bank.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.ExecutorService;

/**
 * @author echo
 * @version 1.0
 * Create by 2023/5/25 9:19
 */
@Slf4j
@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private OperateLogService operateLogService;

    /**
     * 扫描使用这个注解的方法
     */
    @Pointcut("@annotation(com.echo.bank.framework.aspect.OperationLog)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Date beginTime = new Date();
        String result = null;
        String status = null;
        try {
            Object obj = point.proceed();
            if (obj != null) {
                result = JSONObject.toJSONString(obj);
            }
            status = StatusCode.SUCCESS.getCode() + "";
            return obj;
        } catch (Exception e) {
            //请求执行出错
            result = e.getMessage();
            status = StatusCode.ERROR.getCode() + "";
            throw e;
        } finally {
            saveLog(point, beginTime, new Date(), result, status);
        }
    }

    /**
     * 保存日志
     */
    private void saveLog(ProceedingJoinPoint joinPoint, Date beginTime, Date endTime, String result, String status) {
        OperateLog operateLog = new OperateLog();
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            OperationLog annotation = method.getAnnotation(OperationLog.class);
            if (annotation != null) {
                operateLog.setDesc(annotation.desc());
                operateLog.setType(annotation.type());
            }
            if (joinPoint.getArgs() != null) {
                try {
                    operateLog.setParamContext(JSONObject.toJSONString(joinPoint.getArgs()));
                } catch (Exception e) {
                    log.info("解析并记录请求参数出现错误! msg: {}", e.getMessage());
                }
            }
            operateLog.setCreateUser("echo");
            operateLog.setCreateTime(beginTime);
            operateLog.setUpdateTime(endTime);
            operateLog.setStatus(status);
            operateLog.setResultContext(result);

            setRequestIp(operateLog);
        } catch (Exception e) {
            log.info("记录请求日志时初始化日志对象报错! msg: {}", e.getMessage());
        }

        ExecutorService instance = ExecutorServiceUtil.getInstance();
        instance.execute(new OperationLogSaveThread(operateLogService, operateLog));
    }

    private void setRequestIp(OperateLog operateLog) {
        try {
            RequestUtils requestUtils = SpringContextUtils.getBean("requestUtils", RequestUtils.class);
            HttpServletRequest httpServletRequest = requestUtils.getHttpServletRequest();
            String requestIp = requestUtils.getRequestIp(httpServletRequest);
            operateLog.setRequestIp(requestIp);
        } catch (Exception e) {
            // 向外请求的时候，使用该IP
            operateLog.setRequestIp("127.0.0.1");
        }
    }

}

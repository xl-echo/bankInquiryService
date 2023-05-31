package com.echo.bank.framework.thread;

import com.echo.bank.framework.exception.BankException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author echo
 * @version 1.0
 * Create by 2023/5/25 9:19
 */
public class ExecutorServiceUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExecutorServiceUtil.class);

    private static ThreadPoolExecutor executorService;

    private static final int INT_CORE_POOL_SIZE = 50;
    private static final int MAXIMUM_POOL_SIZE = 100;
    private static final int KEEP_ALIVE_TIME = 60;
    private static final int WORK_QUEUE_SIZE = 2000;

    static {
        executorService = new ThreadPoolExecutor(
                INT_CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(WORK_QUEUE_SIZE),
                new CommunityThreadFactory(),
                new DataMigrationRejectedExecutionHandler());
    }

    private ExecutorServiceUtil() {
        if (executorService != null) {
            throw new BankException("Reflection call constructor terminates execution!!!");
        }
    }

    public static ExecutorService getInstance() {
        logger.info("queue size: {}", executorService.getQueue().size());
        logger.info("Number of active threads: {}", executorService.getActiveCount());
        logger.info("Number of execution completion threads: {}", executorService.getCompletedTaskCount());
        return executorService;
    }
}

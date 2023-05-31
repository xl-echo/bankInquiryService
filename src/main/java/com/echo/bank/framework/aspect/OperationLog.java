package com.echo.bank.framework.aspect;

import java.lang.annotation.*;

/**
 * @author echo
 * @version 1.0
 * Create by 2023/5/25 9:16
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLog {

    /**
     * 执行方法功能描述
     */
    String desc() default "";

    /**
     * 1:发送, 0:接收
     */
    String type() default "0";
}


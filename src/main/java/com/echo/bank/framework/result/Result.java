package com.echo.bank.framework.result;

import com.echo.bank.enums.StatusCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author echo
 * @version 1.0
 * Create by 2023/5/24 14:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 2120267584344923858L;

    /**
     * 返回状态
     */
    private Integer status = 0;
    /**
     * 调用失败返回信息
     */
    private String message = null;
    /**
     * 调用成功返回结果
     */
    private T data = null;

    /**
     * 封装返回值
     *
     * @param t 格式化类型
     * @return Result<T> 返回类
     */
    public static <T> Result<T> packageResultMethod(T t) {
        Result<T> result = new Result<>();
        result.setStatus(StatusCode.SUCCESS.getCode());
        result.setData(t);
        return result;
    }
}
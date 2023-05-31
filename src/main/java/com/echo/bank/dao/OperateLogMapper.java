package com.echo.bank.dao;

import com.echo.bank.pojo.OperateLog;
import org.springframework.context.annotation.Scope;

/**
 * @author echo
 */
public interface OperateLogMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(OperateLog record);

    int insertSelective(OperateLog record);

    OperateLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OperateLog record);

    int updateByPrimaryKey(OperateLog record);
}
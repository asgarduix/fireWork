package com.asi.huanan.service.dao.mybatis.mapper;

import com.asi.huanan.service.dao.mybatis.model.LogFriFacSamerisk;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacSameriskExample;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacSameriskKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogFriFacSameriskMapper {
    long countByExample(LogFriFacSameriskExample example);

    int deleteByExample(LogFriFacSameriskExample example);

    int deleteByPrimaryKey(LogFriFacSameriskKey key);

    int insert(LogFriFacSamerisk record);

    int insertSelective(LogFriFacSamerisk record);

    List<LogFriFacSamerisk> selectByExample(LogFriFacSameriskExample example);

    LogFriFacSamerisk selectByPrimaryKey(LogFriFacSameriskKey key);

    int updateByExampleSelective(@Param("record") LogFriFacSamerisk record, @Param("example") LogFriFacSameriskExample example);

    int updateByExample(@Param("record") LogFriFacSamerisk record, @Param("example") LogFriFacSameriskExample example);

    int updateByPrimaryKeySelective(LogFriFacSamerisk record);

    int updateByPrimaryKey(LogFriFacSamerisk record);
}
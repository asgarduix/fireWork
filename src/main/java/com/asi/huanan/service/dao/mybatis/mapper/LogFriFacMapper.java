package com.asi.huanan.service.dao.mybatis.mapper;

import com.asi.huanan.service.dao.mybatis.model.LogFriFac;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacExample;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogFriFacMapper {
    long countByExample(LogFriFacExample example);

    int deleteByExample(LogFriFacExample example);

    int deleteByPrimaryKey(LogFriFacKey key);

    int insert(LogFriFac record);

    int insertSelective(LogFriFac record);

    List<LogFriFac> selectByExample(LogFriFacExample example);

    LogFriFac selectByPrimaryKey(LogFriFacKey key);

    int updateByExampleSelective(@Param("record") LogFriFac record, @Param("example") LogFriFacExample example);

    int updateByExample(@Param("record") LogFriFac record, @Param("example") LogFriFacExample example);

    int updateByPrimaryKeySelective(LogFriFac record);

    int updateByPrimaryKey(LogFriFac record);
}
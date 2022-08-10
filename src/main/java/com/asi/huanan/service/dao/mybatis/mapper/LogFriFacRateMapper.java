package com.asi.huanan.service.dao.mybatis.mapper;

import com.asi.huanan.service.dao.mybatis.model.LogFriFacRate;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacRateExample;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacRateKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogFriFacRateMapper {
    long countByExample(LogFriFacRateExample example);

    int deleteByExample(LogFriFacRateExample example);

    int deleteByPrimaryKey(LogFriFacRateKey key);

    int insert(LogFriFacRate record);

    int insertSelective(LogFriFacRate record);

    List<LogFriFacRate> selectByExample(LogFriFacRateExample example);

    LogFriFacRate selectByPrimaryKey(LogFriFacRateKey key);

    int updateByExampleSelective(@Param("record") LogFriFacRate record, @Param("example") LogFriFacRateExample example);

    int updateByExample(@Param("record") LogFriFacRate record, @Param("example") LogFriFacRateExample example);

    int updateByPrimaryKeySelective(LogFriFacRate record);

    int updateByPrimaryKey(LogFriFacRate record);
}
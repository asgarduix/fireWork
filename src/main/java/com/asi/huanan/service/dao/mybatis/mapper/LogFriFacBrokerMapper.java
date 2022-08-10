package com.asi.huanan.service.dao.mybatis.mapper;

import com.asi.huanan.service.dao.mybatis.model.LogFriFacBroker;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacBrokerExample;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacBrokerKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogFriFacBrokerMapper {
    long countByExample(LogFriFacBrokerExample example);

    int deleteByExample(LogFriFacBrokerExample example);

    int deleteByPrimaryKey(LogFriFacBrokerKey key);

    int insert(LogFriFacBroker record);

    int insertSelective(LogFriFacBroker record);

    List<LogFriFacBroker> selectByExample(LogFriFacBrokerExample example);

    LogFriFacBroker selectByPrimaryKey(LogFriFacBrokerKey key);

    int updateByExampleSelective(@Param("record") LogFriFacBroker record, @Param("example") LogFriFacBrokerExample example);

    int updateByExample(@Param("record") LogFriFacBroker record, @Param("example") LogFriFacBrokerExample example);

    int updateByPrimaryKeySelective(LogFriFacBroker record);

    int updateByPrimaryKey(LogFriFacBroker record);
}
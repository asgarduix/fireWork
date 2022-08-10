package com.asi.huanan.service.dao.mybatis.mapper;

import com.asi.huanan.service.dao.mybatis.model.LogFriFacShares;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacSharesExample;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacSharesKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogFriFacSharesMapper {
    long countByExample(LogFriFacSharesExample example);

    int deleteByExample(LogFriFacSharesExample example);

    int deleteByPrimaryKey(LogFriFacSharesKey key);

    int insert(LogFriFacShares record);

    int insertSelective(LogFriFacShares record);

    List<LogFriFacShares> selectByExample(LogFriFacSharesExample example);

    LogFriFacShares selectByPrimaryKey(LogFriFacSharesKey key);

    int updateByExampleSelective(@Param("record") LogFriFacShares record, @Param("example") LogFriFacSharesExample example);

    int updateByExample(@Param("record") LogFriFacShares record, @Param("example") LogFriFacSharesExample example);

    int updateByPrimaryKeySelective(LogFriFacShares record);

    int updateByPrimaryKey(LogFriFacShares record);
}
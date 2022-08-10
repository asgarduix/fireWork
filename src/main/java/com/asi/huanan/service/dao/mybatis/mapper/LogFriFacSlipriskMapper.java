package com.asi.huanan.service.dao.mybatis.mapper;

import com.asi.huanan.service.dao.mybatis.model.LogFriFacSlipriskExample;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacSlipriskKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogFriFacSlipriskMapper {
    long countByExample(LogFriFacSlipriskExample example);

    int deleteByExample(LogFriFacSlipriskExample example);

    int deleteByPrimaryKey(LogFriFacSlipriskKey key);

    int insert(LogFriFacSlipriskKey record);

    int insertSelective(LogFriFacSlipriskKey record);

    List<LogFriFacSlipriskKey> selectByExample(LogFriFacSlipriskExample example);

    int updateByExampleSelective(@Param("record") LogFriFacSlipriskKey record, @Param("example") LogFriFacSlipriskExample example);

    int updateByExample(@Param("record") LogFriFacSlipriskKey record, @Param("example") LogFriFacSlipriskExample example);
}
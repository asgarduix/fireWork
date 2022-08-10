package com.asi.huanan.service.dao.mybatis.mapper;

import com.asi.huanan.service.dao.mybatis.model.LogFriFacRincom;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacRincomExample;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacRincomKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogFriFacRincomMapper {
    long countByExample(LogFriFacRincomExample example);

    int deleteByExample(LogFriFacRincomExample example);

    int deleteByPrimaryKey(LogFriFacRincomKey key);

    int insert(LogFriFacRincom record);

    int insertSelective(LogFriFacRincom record);

    List<LogFriFacRincom> selectByExample(LogFriFacRincomExample example);

    LogFriFacRincom selectByPrimaryKey(LogFriFacRincomKey key);

    int updateByExampleSelective(@Param("record") LogFriFacRincom record, @Param("example") LogFriFacRincomExample example);

    int updateByExample(@Param("record") LogFriFacRincom record, @Param("example") LogFriFacRincomExample example);

    int updateByPrimaryKeySelective(LogFriFacRincom record);

    int updateByPrimaryKey(LogFriFacRincom record);
}
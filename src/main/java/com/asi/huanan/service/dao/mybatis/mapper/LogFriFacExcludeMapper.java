package com.asi.huanan.service.dao.mybatis.mapper;

import com.asi.huanan.service.dao.mybatis.model.LogFriFacExcludeExample;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacExcludeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogFriFacExcludeMapper {
    long countByExample(LogFriFacExcludeExample example);

    int deleteByExample(LogFriFacExcludeExample example);

    int deleteByPrimaryKey(LogFriFacExcludeKey key);

    int insert(LogFriFacExcludeKey record);

    int insertSelective(LogFriFacExcludeKey record);

    List<LogFriFacExcludeKey> selectByExample(LogFriFacExcludeExample example);

    int updateByExampleSelective(@Param("record") LogFriFacExcludeKey record, @Param("example") LogFriFacExcludeExample example);

    int updateByExample(@Param("record") LogFriFacExcludeKey record, @Param("example") LogFriFacExcludeExample example);
}
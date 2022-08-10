package com.asi.huanan.service.dao.mybatis.mapper;

import com.asi.huanan.service.dao.mybatis.model.LogFriFacPolicyExample;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacPolicyKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogFriFacPolicyMapper {
    long countByExample(LogFriFacPolicyExample example);

    int deleteByExample(LogFriFacPolicyExample example);

    int deleteByPrimaryKey(LogFriFacPolicyKey key);

    int insert(LogFriFacPolicyKey record);

    int insertSelective(LogFriFacPolicyKey record);

    List<LogFriFacPolicyKey> selectByExample(LogFriFacPolicyExample example);

    int updateByExampleSelective(@Param("record") LogFriFacPolicyKey record, @Param("example") LogFriFacPolicyExample example);

    int updateByExample(@Param("record") LogFriFacPolicyKey record, @Param("example") LogFriFacPolicyExample example);
}
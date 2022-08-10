package com.asi.huanan.service.dao.mybatis.mapper;

import com.asi.huanan.service.dao.mybatis.model.LogFriFacNprop;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacNpropExample;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacNpropKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogFriFacNpropMapper {
    long countByExample(LogFriFacNpropExample example);

    int deleteByExample(LogFriFacNpropExample example);

    int deleteByPrimaryKey(LogFriFacNpropKey key);

    int insert(LogFriFacNprop record);

    int insertSelective(LogFriFacNprop record);

    List<LogFriFacNprop> selectByExample(LogFriFacNpropExample example);

    LogFriFacNprop selectByPrimaryKey(LogFriFacNpropKey key);

    int updateByExampleSelective(@Param("record") LogFriFacNprop record, @Param("example") LogFriFacNpropExample example);

    int updateByExample(@Param("record") LogFriFacNprop record, @Param("example") LogFriFacNpropExample example);

    int updateByPrimaryKeySelective(LogFriFacNprop record);

    int updateByPrimaryKey(LogFriFacNprop record);
}
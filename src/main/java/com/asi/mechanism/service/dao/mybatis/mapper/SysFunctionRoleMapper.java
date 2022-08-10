package com.asi.mechanism.service.dao.mybatis.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.asi.mechanism.service.dao.mybatis.model.SysFunctionRole;
import com.asi.mechanism.service.dao.mybatis.model.SysFunctionRoleExample;

public interface SysFunctionRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_FUNCTION_ROLE
     *
     * @mbg.generated Wed Dec 23 15:38:55 CST 2020
     */
    long countByExample(SysFunctionRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_FUNCTION_ROLE
     *
     * @mbg.generated Wed Dec 23 15:38:55 CST 2020
     */
    int deleteByExample(SysFunctionRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_FUNCTION_ROLE
     *
     * @mbg.generated Wed Dec 23 15:38:55 CST 2020
     */
    int insert(SysFunctionRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_FUNCTION_ROLE
     *
     * @mbg.generated Wed Dec 23 15:38:55 CST 2020
     */
    int insertSelective(SysFunctionRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_FUNCTION_ROLE
     *
     * @mbg.generated Wed Dec 23 15:38:55 CST 2020
     */
    List<SysFunctionRole> selectByExample(SysFunctionRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_FUNCTION_ROLE
     *
     * @mbg.generated Wed Dec 23 15:38:55 CST 2020
     */
    int updateByExampleSelective(@Param("record") SysFunctionRole record, @Param("example") SysFunctionRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_FUNCTION_ROLE
     *
     * @mbg.generated Wed Dec 23 15:38:55 CST 2020
     */
    int updateByExample(@Param("record") SysFunctionRole record, @Param("example") SysFunctionRoleExample example);
}
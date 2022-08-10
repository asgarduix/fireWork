package com.asi.mechanism.service.dao.mybatis.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.asi.mechanism.service.dao.mybatis.model.SysFuncUiField;
import com.asi.mechanism.service.dao.mybatis.model.SysFuncUiFieldExample;
import com.asi.mechanism.service.dao.mybatis.model.SysFuncUiFieldKey;

public interface SysFuncUiFieldMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_func_ui_field
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    long countByExample(SysFuncUiFieldExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_func_ui_field
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    int deleteByExample(SysFuncUiFieldExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_func_ui_field
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    int deleteByPrimaryKey(SysFuncUiFieldKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_func_ui_field
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    int insert(SysFuncUiField record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_func_ui_field
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    int insertSelective(SysFuncUiField record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_func_ui_field
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    List<SysFuncUiField> selectByExample(SysFuncUiFieldExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_func_ui_field
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    SysFuncUiField selectByPrimaryKey(SysFuncUiFieldKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_func_ui_field
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    int updateByExampleSelective(@Param("record") SysFuncUiField record, @Param("example") SysFuncUiFieldExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_func_ui_field
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    int updateByExample(@Param("record") SysFuncUiField record, @Param("example") SysFuncUiFieldExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_func_ui_field
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    int updateByPrimaryKeySelective(SysFuncUiField record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_func_ui_field
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    int updateByPrimaryKey(SysFuncUiField record);
}
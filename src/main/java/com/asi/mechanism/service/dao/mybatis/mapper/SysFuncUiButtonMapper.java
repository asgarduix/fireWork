package com.asi.mechanism.service.dao.mybatis.mapper;

import com.asi.mechanism.service.dao.mybatis.model.SysFuncUiButton;
import com.asi.mechanism.service.dao.mybatis.model.SysFuncUiButtonExample;
import com.asi.mechanism.service.dao.mybatis.model.SysFuncUiButtonKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysFuncUiButtonMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_func_ui_button
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    long countByExample(SysFuncUiButtonExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_func_ui_button
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    int deleteByExample(SysFuncUiButtonExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_func_ui_button
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    int deleteByPrimaryKey(SysFuncUiButtonKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_func_ui_button
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    int insert(SysFuncUiButton record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_func_ui_button
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    int insertSelective(SysFuncUiButton record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_func_ui_button
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    List<SysFuncUiButton> selectByExample(SysFuncUiButtonExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_func_ui_button
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    SysFuncUiButton selectByPrimaryKey(SysFuncUiButtonKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_func_ui_button
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    int updateByExampleSelective(@Param("record") SysFuncUiButton record, @Param("example") SysFuncUiButtonExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_func_ui_button
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    int updateByExample(@Param("record") SysFuncUiButton record, @Param("example") SysFuncUiButtonExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_func_ui_button
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    int updateByPrimaryKeySelective(SysFuncUiButton record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_func_ui_button
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    int updateByPrimaryKey(SysFuncUiButton record);
}
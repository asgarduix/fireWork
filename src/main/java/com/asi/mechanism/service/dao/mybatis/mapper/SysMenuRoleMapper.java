package com.asi.mechanism.service.dao.mybatis.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.asi.mechanism.service.dao.mybatis.model.SysMenuRole;
import com.asi.mechanism.service.dao.mybatis.model.SysMenuRoleExample;

public interface SysMenuRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu_role
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    long countByExample(SysMenuRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu_role
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    int deleteByExample(SysMenuRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu_role
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    int insert(SysMenuRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu_role
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    int insertSelective(SysMenuRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu_role
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    List<SysMenuRole> selectByExample(SysMenuRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu_role
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    int updateByExampleSelective(@Param("record") SysMenuRole record, @Param("example") SysMenuRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu_role
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    int updateByExample(@Param("record") SysMenuRole record, @Param("example") SysMenuRoleExample example);
}
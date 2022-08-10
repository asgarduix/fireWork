package com.asi.mechanism.service.dao.mybatis.mapper;

import com.asi.mechanism.service.dao.mybatis.model.SysAccessRecord;
import com.asi.mechanism.service.dao.mybatis.model.SysAccessRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysAccessRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_access_record
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    long countByExample(SysAccessRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_access_record
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    int deleteByExample(SysAccessRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_access_record
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    int insert(SysAccessRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_access_record
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    int insertSelective(SysAccessRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_access_record
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    List<SysAccessRecord> selectByExample(SysAccessRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_access_record
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    int updateByExampleSelective(@Param("record") SysAccessRecord record, @Param("example") SysAccessRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_access_record
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    int updateByExample(@Param("record") SysAccessRecord record, @Param("example") SysAccessRecordExample example);
}
package com.asi.huanan.service.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.asi.huanan.service.dao.mybatis.model.Rin1206;
import com.asi.huanan.service.dao.mybatis.model.Rin1206Example;

public interface Rin1206Mapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RIN1206
     *
     * @mbg.generated Mon Dec 20 13:52:34 CST 2021
     */
    long countByExample(Rin1206Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RIN1206
     *
     * @mbg.generated Mon Dec 20 13:52:34 CST 2021
     */
    int deleteByExample(Rin1206Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RIN1206
     *
     * @mbg.generated Mon Dec 20 13:52:34 CST 2021
     */
    int insert(Rin1206 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RIN1206
     *
     * @mbg.generated Mon Dec 20 13:52:34 CST 2021
     */
    int insertSelective(Rin1206 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RIN1206
     *
     * @mbg.generated Mon Dec 20 13:52:34 CST 2021
     */
    List<Rin1206> selectByExample(Rin1206Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RIN1206
     *
     * @mbg.generated Mon Dec 20 13:52:34 CST 2021
     */
    int updateByExampleSelective(@Param("record") Rin1206 record, @Param("example") Rin1206Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RIN1206
     *
     * @mbg.generated Mon Dec 20 13:52:34 CST 2021
     */
    int updateByExample(@Param("record") Rin1206 record, @Param("example") Rin1206Example example);
    
    //Rin1206 truncate table
    @Update("<script>"+
    		"TRUNCATE TABLE Rin1206"+
    		"</script>")
    void truncateTable();
}
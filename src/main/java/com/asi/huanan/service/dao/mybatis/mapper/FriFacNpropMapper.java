package com.asi.huanan.service.dao.mybatis.mapper;

import com.asi.huanan.service.dao.mybatis.model.FriFacNprop;
import com.asi.huanan.service.dao.mybatis.model.FriFacNpropExample;
import com.asi.huanan.service.dao.mybatis.model.FriFacNpropKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FriFacNpropMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_nprop
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    long countByExample(FriFacNpropExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_nprop
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    int deleteByExample(FriFacNpropExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_nprop
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    int deleteByPrimaryKey(FriFacNpropKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_nprop
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    int insert(FriFacNprop record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_nprop
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    int insertSelective(FriFacNprop record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_nprop
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    List<FriFacNprop> selectByExample(FriFacNpropExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_nprop
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    FriFacNprop selectByPrimaryKey(FriFacNpropKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_nprop
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    int updateByExampleSelective(@Param("record") FriFacNprop record, @Param("example") FriFacNpropExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_nprop
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    int updateByExample(@Param("record") FriFacNprop record, @Param("example") FriFacNpropExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_nprop
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    int updateByPrimaryKeySelective(FriFacNprop record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_nprop
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    int updateByPrimaryKey(FriFacNprop record);
}
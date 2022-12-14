package com.asi.huanan.service.dao.mybatis.mapper;

import com.asi.huanan.service.dao.mybatis.model.FriFacRate;
import com.asi.huanan.service.dao.mybatis.model.FriFacRateExample;
import com.asi.huanan.service.dao.mybatis.model.FriFacRateKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FriFacRateMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rate
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    long countByExample(FriFacRateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rate
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    int deleteByExample(FriFacRateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rate
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    int deleteByPrimaryKey(FriFacRateKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rate
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    int insert(FriFacRate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rate
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    int insertSelective(FriFacRate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rate
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    List<FriFacRate> selectByExample(FriFacRateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rate
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    FriFacRate selectByPrimaryKey(FriFacRateKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rate
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    int updateByExampleSelective(@Param("record") FriFacRate record, @Param("example") FriFacRateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rate
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    int updateByExample(@Param("record") FriFacRate record, @Param("example") FriFacRateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rate
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    int updateByPrimaryKeySelective(FriFacRate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rate
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    int updateByPrimaryKey(FriFacRate record);
}
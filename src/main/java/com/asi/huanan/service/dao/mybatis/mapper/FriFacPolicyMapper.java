package com.asi.huanan.service.dao.mybatis.mapper;

import com.asi.huanan.service.dao.mybatis.model.FriFacPolicyExample;
import com.asi.huanan.service.dao.mybatis.model.FriFacPolicyKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FriFacPolicyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_policy
     *
     * @mbg.generated Mon Nov 29 11:09:12 CST 2021
     */
    long countByExample(FriFacPolicyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_policy
     *
     * @mbg.generated Mon Nov 29 11:09:12 CST 2021
     */
    int deleteByExample(FriFacPolicyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_policy
     *
     * @mbg.generated Mon Nov 29 11:09:12 CST 2021
     */
    int deleteByPrimaryKey(FriFacPolicyKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_policy
     *
     * @mbg.generated Mon Nov 29 11:09:12 CST 2021
     */
    int insert(FriFacPolicyKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_policy
     *
     * @mbg.generated Mon Nov 29 11:09:12 CST 2021
     */
    int insertSelective(FriFacPolicyKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_policy
     *
     * @mbg.generated Mon Nov 29 11:09:12 CST 2021
     */
    List<FriFacPolicyKey> selectByExample(FriFacPolicyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_policy
     *
     * @mbg.generated Mon Nov 29 11:09:12 CST 2021
     */
    int updateByExampleSelective(@Param("record") FriFacPolicyKey record, @Param("example") FriFacPolicyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_policy
     *
     * @mbg.generated Mon Nov 29 11:09:12 CST 2021
     */
    int updateByExample(@Param("record") FriFacPolicyKey record, @Param("example") FriFacPolicyExample example);
}
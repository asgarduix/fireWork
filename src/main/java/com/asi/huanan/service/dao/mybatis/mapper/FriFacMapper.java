package com.asi.huanan.service.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.asi.huanan.service.dao.mybatis.model.FriFac;
import com.asi.huanan.service.dao.mybatis.model.FriFacExample;
import com.asi.huanan.vo.Rin1303QueryCesNoVOResp;

public interface FriFacMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac
     *
     * @mbg.generated Mon Nov 29 10:45:41 CST 2021
     */
    long countByExample(FriFacExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac
     *
     * @mbg.generated Mon Nov 29 10:45:41 CST 2021
     */
    int deleteByExample(FriFacExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac
     *
     * @mbg.generated Mon Nov 29 10:45:41 CST 2021
     */
    int deleteByPrimaryKey(String slipNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac
     *
     * @mbg.generated Mon Nov 29 10:45:41 CST 2021
     */
    int insert(FriFac record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac
     *
     * @mbg.generated Mon Nov 29 10:45:41 CST 2021
     */
    int insertSelective(FriFac record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac
     *
     * @mbg.generated Mon Nov 29 10:45:41 CST 2021
     */
    List<FriFac> selectByExample(FriFacExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac
     *
     * @mbg.generated Mon Nov 29 10:45:41 CST 2021
     */
    FriFac selectByPrimaryKey(String slipNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac
     *
     * @mbg.generated Mon Nov 29 10:45:41 CST 2021
     */
    int updateByExampleSelective(@Param("record") FriFac record, @Param("example") FriFacExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac
     *
     * @mbg.generated Mon Nov 29 10:45:41 CST 2021
     */
    int updateByExample(@Param("record") FriFac record, @Param("example") FriFacExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac
     *
     * @mbg.generated Mon Nov 29 10:45:41 CST 2021
     */
    int updateByPrimaryKeySelective(FriFac record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac
     *
     * @mbg.generated Mon Nov 29 10:45:41 CST 2021
     */
    int updateByPrimaryKey(FriFac record);
    
    @Select("<script>select cession_no as txtcession_no, slip_no as txtslip_no, cession_name as txtcession_name, policy_no as txtpolicy_no "
    		+ "from fri_fac "
    		+ "where cession_no like concat(#{cessionNo,jdbcType=VARCHAR},'%') </script>")
    
    List<Rin1303QueryCesNoVOResp> queryCessionNoList(@Param("cessionNo") String cessionNo);
    
    @Update("<script>update fri_fac "
    		+ "set acct_flag = 'Y' "
    		+ "where slip_no =  #{slipNo} </script>")
    void updateRin1303AcctFlag(@Param("slipNo") String slipNo);
}
package com.asi.huanan.service.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.asi.huanan.service.dao.mybatis.model.FriFacShares;
import com.asi.huanan.service.dao.mybatis.model.FriFacSharesExample;
import com.asi.huanan.service.dao.mybatis.model.FriFacSharesKey;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1205TableFacShare;

public interface FriFacSharesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_shares
     *
     * @mbg.generated Tue Nov 16 11:50:10 CST 2021
     */
    long countByExample(FriFacSharesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_shares
     *
     * @mbg.generated Tue Nov 16 11:50:10 CST 2021
     */
    int deleteByExample(FriFacSharesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_shares
     *
     * @mbg.generated Tue Nov 16 11:50:10 CST 2021
     */
    int deleteByPrimaryKey(FriFacSharesKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_shares
     *
     * @mbg.generated Tue Nov 16 11:50:10 CST 2021
     */
    int insert(FriFacShares record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_shares
     *
     * @mbg.generated Tue Nov 16 11:50:10 CST 2021
     */
    int insertSelective(FriFacShares record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_shares
     *
     * @mbg.generated Tue Nov 16 11:50:10 CST 2021
     */
    List<FriFacShares> selectByExample(FriFacSharesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_shares
     *
     * @mbg.generated Tue Nov 16 11:50:10 CST 2021
     */
    FriFacShares selectByPrimaryKey(FriFacSharesKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_shares
     *
     * @mbg.generated Tue Nov 16 11:50:10 CST 2021
     */
    int updateByExampleSelective(@Param("record") FriFacShares record, @Param("example") FriFacSharesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_shares
     *
     * @mbg.generated Tue Nov 16 11:50:10 CST 2021
     */
    int updateByExample(@Param("record") FriFacShares record, @Param("example") FriFacSharesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_shares
     *
     * @mbg.generated Tue Nov 16 11:50:10 CST 2021
     */
    int updateByPrimaryKeySelective(FriFacShares record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_shares
     *
     * @mbg.generated Tue Nov 16 11:50:10 CST 2021
     */
    int updateByPrimaryKey(FriFacShares record);
    
    
    
    @Select("<script> select isnull(sum(amt), 0) as fac_amt, "
    		+ "isnull(sum(amt_ear),0) as fac_ear_share, "
    		+ "isnull(sum(amt_typ),0) as fac_typ_share "
    		+ "from fri_fac_shares "
    		+ "where policy_no = #{policyNo,jdbcType=VARCHAR} "
    		+ "and endorse_no = #{endorseNo,jdbcType=VARCHAR} "
    		+ "and addr_no = #{addrNo,jdbcType=VARCHAR} "
    		+ "</script>")
    
    List<Rin1205TableFacShare> getFacShare(@Param("policyNo") String policyNo, @Param("endorseNo") String endorseNo, @Param("addrNo") String addrNo);
}
package com.asi.huanan.service.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.asi.huanan.service.dao.mybatis.model.FriTreatyShares;
import com.asi.huanan.service.dao.mybatis.model.FriTreatySharesExample;
import com.asi.huanan.vo.Rin1205VORespSub;
import com.asi.huanan.vo.Rin1205UpdateShareAmtPremVOReq;

public interface FriTreatySharesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_treaty_shares
     *
     * @mbg.generated Tue Nov 16 09:20:59 CST 2021
     */
    long countByExample(FriTreatySharesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_treaty_shares
     *
     * @mbg.generated Tue Nov 16 09:20:59 CST 2021
     */
    int deleteByExample(FriTreatySharesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_treaty_shares
     *
     * @mbg.generated Tue Nov 16 09:20:59 CST 2021
     */
    int insert(FriTreatyShares record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_treaty_shares
     *
     * @mbg.generated Tue Nov 16 09:20:59 CST 2021
     */
    int insertSelective(FriTreatyShares record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_treaty_shares
     *
     * @mbg.generated Tue Nov 16 09:20:59 CST 2021
     */
    List<FriTreatyShares> selectByExample(FriTreatySharesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_treaty_shares
     *
     * @mbg.generated Tue Nov 16 09:20:59 CST 2021
     */
    int updateByExampleSelective(@Param("record") FriTreatyShares record, @Param("example") FriTreatySharesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_treaty_shares
     *
     * @mbg.generated Tue Nov 16 09:20:59 CST 2021
     */
    int updateByExample(@Param("record") FriTreatyShares record, @Param("example") FriTreatySharesExample example);
    
    @Select("<script>select treaty_no as treatyNo, share_amt as shareAmt, share_prem  as sharePrem "
    		+ "from fri_treaty_shares "
    		+ "where treaty_no = 'Retain' "
    		+ "<if test=\"record.policyNo != null and record.policyNo != ''\"> "
    		+ "AND policy_no = #{record.policyNo,jdbcType=VARCHAR} "
    		+ "</if>"
    		+ "AND endorse_no = #{record.endorseNo,jdbcType=VARCHAR} "
    		+ "<if test=\"record.addrNo != null \"> "
    		+ "AND addr_no = #{record.addrNo,jdbcType=INTEGER} "
    		+ "</if>"
    		+ "<if test=\"record.treatyYear != null and record.treatyYear != ''\"> "
    		+ "AND treaty_year = #{record.treatyYear,jdbcType=VARCHAR} "
    		+ "</if>"
    		+ "<if test=\"record.riskNo != null and record.riskNo != ''\"> "
    		+ "AND risk_no = #{record.riskNo,jdbcType=VARCHAR} "
    		+ "</if>"   		
    		+ "</script>")
    
    List<FriTreatyShares> getRetainResult(@Param("record") FriTreatyShares record);
    
    
    @Select("<script>select treaty_no as txttreaty_no_i, share_amt as numshare_amt_i, share_prem as numshare_prem_i "
    		+ "from fri_treaty_shares "
    		+ "where treaty_no != 'Retain'"
    		+ "<if test=\"record.policyNo != null and record.policyNo != ''\"> "
    		+ "AND policy_no = #{record.policyNo,jdbcType=VARCHAR} "
    		+ "</if>"
    		+ "AND endorse_no = #{record.endorseNo,jdbcType=VARCHAR} "
    		+ "<if test=\"record.addrNo != null \"> "
    		+ "AND addr_no = #{record.addrNo,jdbcType=INTEGER} "
    		+ "</if>"
    		+ "<if test=\"record.treatyYear != null and record.treatyYear != ''\"> "
    		+ "AND treaty_year = #{record.treatyYear,jdbcType=VARCHAR} "
    		+ "</if>"
    		+ "<if test=\"record.riskNo != null and record.riskNo != ''\"> "
    		+ "AND risk_no = #{record.riskNo,jdbcType=VARCHAR} "
    		+ "</if> "	
    		+ "</script>")
    
    List<Rin1205VORespSub> getShareResult(@Param("record") FriTreatyShares record);
    
    @Update("<script>update fri_treaty_shares "
    		+ "set share_amt = #{record.numshare_amt_0,jdbcType=VARCHAR}, "
    		+ "share_prem = #{record.numshare_prem_0,jdbcType=VARCHAR} "
    		+ "where policy_no = #{record.txtpolicy_no,jdbcType=VARCHAR} "
    		+ "<choose>"
    		+ "<when test=\"record.txtendorse_no != null and record.txtendorse_no !=''\">" 
    		+ "and endorse_no = #{record.txtendorse_no,jdbcType=VARCHAR} " 
    		+ "</when>" 
    		+ "<otherwise>" 
    		+ "and endorse_no = '' " 
    		+ "</otherwise>" 
    		+ "</choose>"
    		+ "and addr_no = #{record.numaddr_no,jdbcType=VARCHAR} "
    		+ "and treaty_year = #{record.treaty_year,jdbcType=VARCHAR} "
    		+ "and treaty_no = #{record.treaty_no,jdbcType=VARCHAR} "
    		+ "and risk_no = #{record.riskNo,jdbcType=VARCHAR}</script>")
    
    int updateShareAmtPrem(@Param("record") Rin1205UpdateShareAmtPremVOReq record);
    
    
    
    /**
     * Rin1304_??????????????????????????????????????????????????????????????????????????????
     * @param policyNo
     * @param endorseNo
     * @return
     */
//    @Delete("<script>"
//    		+"delete from  fri_treaty_shares \n"   
//    		+"WHERE  policy_no = #{policyNo,jdbcType=VARCHAR} \n"
//    		+"AND endorse_no = #{endorseNo,jdbcType=VARCHAR}\n"
//    		+"</script>")
    @Delete("<script> delete from  fri_treaty_shares \n WHERE  policy_no = #{policyNo,jdbcType=VARCHAR} \n AND endorse_no = #{endorseNo,jdbcType=VARCHAR}\n </script>")
    int deleteFriTreatyShares (@Param("policyNo") String policyNo,@Param("endorseNo") String endorseNo);

}
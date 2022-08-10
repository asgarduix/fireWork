package com.asi.huanan.service.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.asi.huanan.service.dao.mybatis.model.FriFacSlipriskExample;
import com.asi.huanan.service.dao.mybatis.model.FriFacSlipriskKey;
import com.asi.huanan.vo.rin1301.req.Rin1301HandleDataVOReq;

public interface FriFacSlipriskMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_sliprisk
     *
     * @mbg.generated Tue Jan 04 09:49:09 CST 2022
     */
    long countByExample(FriFacSlipriskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_sliprisk
     *
     * @mbg.generated Tue Jan 04 09:49:09 CST 2022
     */
    int deleteByExample(FriFacSlipriskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_sliprisk
     *
     * @mbg.generated Tue Jan 04 09:49:09 CST 2022
     */
    int deleteByPrimaryKey(FriFacSlipriskKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_sliprisk
     *
     * @mbg.generated Tue Jan 04 09:49:09 CST 2022
     */
    int insert(FriFacSlipriskKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_sliprisk
     *
     * @mbg.generated Tue Jan 04 09:49:09 CST 2022
     */
    int insertSelective(FriFacSlipriskKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_sliprisk
     *
     * @mbg.generated Tue Jan 04 09:49:09 CST 2022
     */
    List<FriFacSlipriskKey> selectByExample(FriFacSlipriskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_sliprisk
     *
     * @mbg.generated Tue Jan 04 09:49:09 CST 2022
     */
    int updateByExampleSelective(@Param("record") FriFacSlipriskKey record, @Param("example") FriFacSlipriskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_sliprisk
     *
     * @mbg.generated Tue Jan 04 09:49:09 CST 2022
     */
    int updateByExample(@Param("record") FriFacSlipriskKey record, @Param("example") FriFacSlipriskExample example);
    
    
    
    @Insert("<script>"
    		+ " INSERT INTO fri_fac_sliprisk (slip_no, risk_no) "
    		+ " SELECT DISTINCT  #{record.slipNo,jdbcType=VARCHAR}, B.risk_no  "
    		+ " FROM fri_policy A, fri_policy_dtl B "
    		+ " WHERE A.policy_dbgn &lt;= #{record.treatyDend,jdbcType=VARCHAR} "
    		+ " AND A.policy_dend &gt; #{record.treatyDbgn,jdbcType=VARCHAR}    "
    		+ " AND B.risk_no IN "
    		+ " <foreach collection=\"record.facSlipRiskVoList\" item=\"riskNo\" index=\"index\" open=\"(\" close=\")\" separator=\",\">"
    		+ " #{riskNo,jdbcType=VARCHAR}"
    		+ " </foreach> "
    		+ " AND A.policy_no = B.policy_no "
    		+ " AND A.endorse_no = B.endorse_no "
    		+ "</script>")
    int insertByQueryPolicy(@Param("record") Rin1301HandleDataVOReq record);
    
    
    
    
}
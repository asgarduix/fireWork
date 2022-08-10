package com.asi.huanan.service.dao.mybatis.mapper.customerize;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.asi.huanan.service.dao.mybatis.model.customerize.rin1301.Rin1301QueryMainData;
import com.asi.huanan.service.dao.mybatis.model.customerize.rin1301.Rin1301QueryPolicyData;
import com.asi.huanan.service.dao.mybatis.model.customerize.rin1301.Rin1301QueryRinserData;
import com.asi.huanan.vo.rin1301.req.Rin1301AQueryParamVoReq;
import com.asi.huanan.vo.rin1301.req.Rin1301QueryMainDataVOReq;
import com.asi.huanan.vo.rin1301.req.Rin1301QueryPolicyDetailVOReq;

public interface Rin1301Mapper {
	/**
	 * 臨分資料維護主頁查詢<br>
	 * 查詢條件為保單號、合約編號、知會/更正號擇一
	 * 
	 * @param rin1301QueryMainDataVOReq
	 * @return
	 */
	@ResultMap("com.asi.huanan.service.dao.mybatis.mapper.customerize.Rin1301Mapper.mainDataMap")
	@Select("<script>"
			+ " SELECT DISTINCT B.LOG_seq, A.policy_no, A.endorse_no, B.cession_no, B.cession_name, B.slip_no, C.turn_flag AS conversion_status, '' AS isLOG, 'Y' AS print_type ,B.pre_slip_no ,B.pre_cession_no,B.treaty_dend,B.fac_type"
			+ " FROM fri_fac B" 
			+ " LEFT JOIN fri_fac_policy A ON A.slip_no = B.slip_no "
			+ " LEFT JOIN fri_accounting C ON A.slip_no = C.bill_no"
			+ " WHERE B.slip_no IN (SELECT slip_no FROM fri_fac_rincom WHERE acct_flag = 'Y' AND slip_no = B.slip_no ) "
			+ "<if test=\"record.policyNo != null and record.policyNo != ''\">"
			+ " AND A.policy_no LIKE #{record.policyNo,jdbcType=VARCHAR} " 
			+ "</if>"
			+ "<if test=\"record.slipNo != null and record.slipNo != ''\">"
			+ " AND B.slip_no LIKE #{record.slipNo,jdbcType=VARCHAR} " 
			+ "</if>"
			+ "<if test=\"record.cessionNo != null and record.cessionNo != ''\">"
			+ " AND B.cession_no LIKE #{record.cessionNo,jdbcType=VARCHAR} "
			+ "</if>" 
			+ " UNION ALL "
			+ " SELECT DISTINCT B.LOG_seq, A.policy_no, A.endorse_no, B.cession_no, B.cession_name, B.slip_no, C.turn_flag AS conversion_status,  '' AS isLOG , 'N' AS print_type ,B.pre_slip_no ,B.pre_cession_no,B.treaty_dend,B.fac_type"
			+ " FROM fri_fac B" 
			+ " LEFT JOIN fri_fac_policy A ON A.slip_no = B.slip_no "
			+ " LEFT JOIN fri_accounting C ON A.slip_no = C.bill_no"
			+ " WHERE B.slip_no NOT IN (SELECT slip_no FROM fri_fac_rincom WHERE acct_flag = 'Y' AND slip_no = B.slip_no ) "
			+ "<if test=\"record.policyNo != null  and record.policyNo != ''\">"
			+ " AND A.policy_no LIKE #{record.policyNo,jdbcType=VARCHAR} " 
			+ "</if>"
			+ "<if test=\"record.slipNo != null  and record.slipNo != ''\">"
			+ " AND B.slip_no LIKE #{record.slipNo,jdbcType=VARCHAR} " 
			+ "</if>"
			+ "<if test=\"record.cessionNo != null  and record.cessionNo != ''\">"
			+ " AND B.cession_no LIKE #{record.cessionNo,jdbcType=VARCHAR} " 
			+ "</if>" 
			+ " UNION ALL "
			+ " SELECT DISTINCT 'Log-'+LOG_A.LOG_seq AS LOG_seq, "
			+ "  LOG_A.policy_no, LOG_A.endorse_no, LOG_B.cession_no, LOG_B.cession_name, LOG_B.slip_no, '' AS conversion_status , 'Y' AS isLOG , '' AS print_type ,LOG_B.pre_slip_no ,LOG_B.pre_cession_no,LOG_B.treaty_dend,LOG_B.fac_type"
			+ " FROM LOG_fri_fac_policy LOG_A "
			+ " INNER JOIN fri_fac LOG_B ON LOG_A.slip_no = LOG_B.slip_no "
			+ "<if test=\"record.policyNo != null  and record.policyNo != ''\">"
			+ " WHERE LOG_A.policy_no LIKE #{record.policyNo,jdbcType=VARCHAR} " 
			+ "</if>"
			+ "<if test=\"record.slipNo != null  and record.slipNo != ''\">"
			+ " WHERE LOG_B.slip_no LIKE #{record.slipNo,jdbcType=VARCHAR} " 
			+ "</if>"
			+ "<if test=\"record.cessionNo != null  and record.cessionNo != ''\">"
			+ " WHERE LOG_B.cession_no LIKE #{record.cessionNo,jdbcType=VARCHAR} " 
			+ "</if>"
			+ " ORDER BY  LOG_seq, B.cession_name" 
			+ "</script>")
	List<Rin1301QueryMainData> queryFacPolicy(@Param("record") Rin1301QueryMainDataVOReq rin1301QueryMainDataVOReq)
			throws Exception;

	/**
	 * 臨分資料維護主頁查詢<br>
	 * 查詢條件為未列印及暫緩或不轉之臨分期間(時間區間)
	 * 
	 * @param rin1301QueryMainDataVOReq
	 * @return
	 */
	@ResultMap("com.asi.huanan.service.dao.mybatis.mapper.customerize.Rin1301Mapper.mainDataMap")
	@Select("<script>"
			+ " SELECT DISTINCT A.policy_no, A.endorse_no, B.cession_no, B.cession_name, A.slip_no, '未完成' AS tmp , c.acct_flag, c.transfer_status , 1 AS show_seq ,B.pre_slip_no ,B.pre_cession_no ,B.treaty_dend,B.fac_type"
			+ " FROM fri_fac_policy A, fri_fac B, fri_fac_rincom C " + " WHERE A.policy_no + A.endorse_no IN "
			+ " ( SELECT policy_no + endorse_no FROM fri_policy "
			+ " WHERE policy_dprt BETWEEN  #{record.policyDprtBgn, jdbcType=VARCHAR} AND #{record.policyDprtEnd, jdbcType=VARCHAR} "
			+ " AND isnull(temp_flag,'N') != 'Y' ) "
			+ " AND a.slip_no = b.slip_no AND b.slip_no = c.slip_no AND b.acct_flag ='N' AND c.share_status != '3' AND c.share_status != '2' "
			+ " UNION "
			+ " SELECT DISTINCT A.policy_no, A.endorse_no, B.cession_no, B.cession_name, A.slip_no, '未列印' AS tmp , c.acct_flag, c.transfer_status , 2 AS show_seq ,B.pre_slip_no ,B.pre_cession_no ,B.treaty_dend,B.fac_type"
			+ " FROM fri_fac_policy A, fri_fac B, fri_fac_rincom C " + " WHERE A.policy_no + A.endorse_no IN "
			+ " ( SELECT policy_no + endorse_no FROM fri_policy "
			+ " WHERE policy_dprt BETWEEN  #{record.policyDprtBgn, jdbcType=VARCHAR} AND #{record.policyDprtEnd, jdbcType=VARCHAR} "
			+ " AND isnull(temp_flag,'N') != 'Y' ) "
			+ " AND a.slip_no = b.slip_no AND b.slip_no = c.slip_no AND b.acct_flag ='N' AND c.share_status = '2' "
			+ " UNION "
			+ " SELECT DISTINCT A.policy_no, A.endorse_no, B.cession_no, B.cession_name, A.slip_no, '未列印' AS tmp , c.acct_flag, c.transfer_status , 3 AS show_seq ,B.pre_slip_no ,B.pre_cession_no ,B.treaty_dend,B.fac_type"
			+ " FROM fri_fac_policy A, fri_fac B, fri_fac_rincom C " + " WHERE A.policy_no + A.endorse_no IN "
			+ " ( SELECT policy_no + endorse_no FROM fri_policy "
			+ " WHERE policy_dprt BETWEEN  #{record.policyDprtBgn, jdbcType=VARCHAR} AND #{record.policyDprtEnd, jdbcType=VARCHAR} "
			+ " AND isnull(temp_flag,'N') != 'Y' ) "
			+ " AND a.slip_no = b.slip_no AND b.slip_no = c.slip_no AND isnull(C.acct_flag,'') ='N' AND c.share_status != '3' "
			+ " UNION "
			+ " SELECT DISTINCT A.policy_no, A.endorse_no, B.cession_no, B.cession_name, A.slip_no, '不轉檔' AS tmp , c.acct_flag, c.transfer_status , 4 AS show_seq ,B.pre_slip_no ,B.pre_cession_no ,B.treaty_dend,B.fac_type"
			+ " FROM fri_fac_policy A, fri_fac B, fri_fac_rincom C " + " WHERE A.policy_no + A.endorse_no IN "
			+ " ( SELECT policy_no + endorse_no FROM fri_policy "
			+ " WHERE policy_dprt BETWEEN  #{record.policyDprtBgn, jdbcType=VARCHAR} AND #{record.policyDprtEnd, jdbcType=VARCHAR} "
			+ " AND isnull(temp_flag,'N') != 'Y' ) "
			+ " AND a.slip_no = b.slip_no AND b.slip_no = c.slip_no AND isnull(C.transfer_status,'') ='N' " 
			+ " UNION "
			+ " SELECT DISTINCT A.policy_no, A.endorse_no, B.cession_no, B.cession_name, A.slip_no, '暫緩' AS tmp , c.acct_flag, c.transfer_status , 5 AS show_seq ,B.pre_slip_no ,B.pre_cession_no ,B.treaty_dend,B.fac_type"
			+ " FROM fri_fac_policy A, fri_fac B, fri_fac_rincom C " + " WHERE A.policy_no + A.endorse_no IN "
			+ " ( SELECT policy_no + endorse_no FROM fri_policy "
			+ " WHERE policy_dprt BETWEEN  #{record.policyDprtBgn, jdbcType=VARCHAR} AND #{record.policyDprtEnd, jdbcType=VARCHAR} "
			+ " AND isnull(temp_flag,'N') != 'Y' ) "
			+ " AND a.slip_no = b.slip_no AND b.slip_no = c.slip_no AND isnull(C.transfer_status,'') ='H' "
			+ " ORDER BY show_seq, A.policy_no, A.endorse_no" 
			+ "</script>")
	List<Rin1301QueryMainData> queryFacPrintAccount(
			@Param("record") Rin1301QueryMainDataVOReq rin1301QueryMainDataVOReq) throws Exception;

	
	/**
	 * 臨分資料維護內頁_查詢保批單視窗輸入同險代號查詢保單明細<br>
	 * 
	 * @param rin1301QueryPolicyDetailVOReq
	 * @return
	 */
	@ResultMap("com.asi.huanan.service.dao.mybatis.mapper.customerize.Rin1301Mapper.policyDataMap")
	@Select(" <script> " 
			+ " SELECT DISTINCT A.risk_no, A.policy_no, A.endorse_no , A.addr_no, A.amt, B.mkovse "
			+ " FROM fri_policy_dtl A, fri_policy B  "
			+ " WHERE B.policy_no = A.policy_no AND B.endorse_no = A.endorse_no " 
			+ " AND A.risk_no IN  "
			+ " <foreach collection=\"record.riskNos\" item=\"riskNo\" index=\"index\" open=\"(\" close=\")\" separator=\",\"> "
			+ " #{riskNo, jdbcType=VARCHAR} " 
			+ " </foreach>"
			+ " <if test=\"record.mkovse != null  and record.mkovse != ''\">"
			+ " AND B.mkovse = #{record.mkovse, jdbcType=VARCHAR} "
			+ " </if>"
			+ " AND ((A.policy_dbgn BETWEEN #{record.treatyDBgn, jdbcType=VARCHAR} AND #{record.treatyDEnd, jdbcType=VARCHAR}) "
			+ " OR (A.policy_dend  BETWEEN #{record.treatyDBgn, jdbcType=VARCHAR} AND #{record.treatyDEnd, jdbcType=VARCHAR})) "
			+ " AND ( SELECT TOP 1 ISNULL( temp_flag,'N') FROM fri_policy C WHERE C.policy_no = A.policy_no AND C.endorse_no = A.endorse_no ) != 'Y' "
			+ " ORDER BY A.risk_no, A.policy_no, A.endorse_no, A.addr_no " 
			+ " </script> ")
	List<Rin1301QueryPolicyData> queryPolicyDetailBySameRiskCode(
			@Param("record") Rin1301AQueryParamVoReq rin1301AQueryParamVoReq);
	

	
	
	/**
	 * 臨分資料維護內頁_檢查保批單是否有被其他合約號使用<br>
	 * 
	 * @param rin1301QueryPolicyDetailVOReq
	 * @return
	 */
	@ResultMap("com.asi.huanan.service.dao.mybatis.mapper.customerize.Rin1301Mapper.policyDataMap")
	@Select(" <script> " 
			+ " SELECT DISTINCT B.policy_no,B.endorse_no ,A.cession_no "
			+ " FROM fri_fac A "
			+ " Left JOIN fri_fac_policy B ON a.slip_no=b.slip_no "
			+ " WHERE "
			+ " <foreach collection=\"records\" item=\"record\" index=\"index\"  separator=\"or\"> " + " ( "
			+ " <if test=\"record.cessionNo != null  and record.cessionNo != ''\">"
			+ " A.cession_no != #{record.cessionNo, jdbcType=VARCHAR} AND "
			+ " </if>"
			+ "  B.policy_no = #{record.policyNo, jdbcType=VARCHAR} "
			+ "<if test=\"record.endorseNo != null  and record.endorseNo != ''\">"
			+ " AND B.endorse_no = #{record.endorseNo, jdbcType=VARCHAR} " 
			+ "</if>" + " ) " 
			+ " </foreach>"
			+ " </script> ")
	List<Rin1301QueryPolicyData> checkPolEndorseByOtherCessionUse(
			 @Param("records") List<Rin1301QueryPolicyDetailVOReq> voList);

	/**
	 * 臨分資料維護內頁_保單基本資料頁籤明細表格1<br>
	 * 
	 * @param rin1301QueryPolicyDetailVOReq
	 * @return
	 */
	@ResultMap("com.asi.huanan.service.dao.mybatis.mapper.customerize.Rin1301Mapper.policyDataMap")
	@Select({"<script>" ,
			 " SELECT DISTINCT A.policy_no, A.endorse_no, A.cinsurant, A.mkovse, ",
			 " ( SELECT TOP 1 end_reason FROM fri_policy WHERE policy_no = A.policy_no AND endorse_no = A.endorse_no ) AS end_reason ",
			 " FROM fri_policy A " ,
			 " WHERE ",
			 "<foreach collection=\"records\" item=\"record\" index=\"index\"  separator=\"or\"> " ,
			 " ( ",
			 "  A.policy_no = #{record.policyNo, jdbcType=VARCHAR} ",
			 " AND A.endorse_no = #{record.endorseNo, jdbcType=VARCHAR} " ,
			 " ) " ,
			 "</foreach>",
			 " ORDER BY A.policy_no" ,
			 "</script>"})
	List<Rin1301QueryPolicyData> queryPolicyDetail1(@Param("records") List<Rin1301QueryPolicyDetailVOReq> voList);

	@ResultMap("com.asi.huanan.service.dao.mybatis.mapper.customerize.Rin1301Mapper.policyDataMap")
	@Select("<script>" 
			+ " SELECT DISTINCT A.policy_no, A.endorse_no, A.cinsurant, A.mkovse, "
			+ " ( SELECT TOP 1 end_reason FROM fri_policy WHERE policy_no = A.policy_no AND endorse_no = A.endorse_no ) AS end_reason "
			+ " FROM fri_policy A , fri_fac_policy B" 
			+ " WHERE " 
			+ " A.policy_no = B.policy_no"
			+ " AND A.endorse_no = B.endorse_no " 
			+ "<if test=\"record.slipNo != null  and record.slipNo != ''\">"
			+ " AND B.slip_no = #{record.slipNo, jdbcType=VARCHAR} " 
			+ "</if>" 
			+ " ORDER BY A.policy_no" 
			+ "</script>")
	List<Rin1301QueryPolicyData> queryPolicyDetail1ByEdit(@Param("record") Rin1301QueryPolicyDetailVOReq vo);
	
	@ResultMap("com.asi.huanan.service.dao.mybatis.mapper.customerize.Rin1301Mapper.policyDataMap")
	@Select("<script>" 
			+ " SELECT DISTINCT A.policy_no, A.endorse_no, A.cinsurant, A.mkovse, B.LOG_seq, "
			+ " ( SELECT TOP 1 end_reason FROM fri_policy WHERE policy_no = A.policy_no AND endorse_no = A.endorse_no ) AS end_reason "
			+ " FROM fri_policy A , Log_fri_fac_policy B" 
			+ " WHERE " 
			+ " A.policy_no = B.policy_no"
			+ " AND A.endorse_no = B.endorse_no " 
			+ "<if test=\"record.slipNo != null  and record.slipNo != ''\">"
			+ " AND B.slip_no = #{record.slipNo, jdbcType=VARCHAR} " 
			+ "</if>" 
			+ "<if test=\"record.logSeq != null  and record.logSeq != ''\">"
			+ " AND B.LOG_seq = #{record.logSeq, jdbcType=VARCHAR} " 
			+ "</if>" 
			+ " ORDER BY A.policy_no" 
			+ "</script>")
	List<Rin1301QueryPolicyData> queryPolicyDetail1WithLogSeqByEdit(@Param("record") Rin1301QueryPolicyDetailVOReq vo);

	/**
	 * 臨分資料維護內頁_保單基本資料頁籤明細表格2<br>
	 * 
	 * @param rin1301QueryPolicyDetailVOReq
	 * @return
	 */
	@ResultMap("com.asi.huanan.service.dao.mybatis.mapper.customerize.Rin1301Mapper.policyDataMap")
	@Select("<script>" 
			+ " SELECT DISTINCT A.policy_no, A.endorse_no, A.addr_no, A.useprop_name, "
			+ " D.ename as const_class, A.prop_addr, A.risk_no " 
			+ " FROM fri_policy_dtl A, build_grade D " 
			+ " WHERE "
			+ " Ltrim(A.const_class) = D.build_no  AND "
			+ " <foreach collection=\"records\" item=\"record\" index=\"index\" separator=\"or\" open=\"(\" close=\")\" >  "
			+ " ( " 
			+ " A.policy_no  = #{record.policyNo, jdbcType=VARCHAR}"
			+ " AND A.endorse_no = #{record.endorseNo, jdbcType=VARCHAR} " 
			+ "<if test=\"record.addrNo != null  and record.addrNo != ''\">"
			+ " AND A.addr_no = #{record.addrNo, jdbcType=VARCHAR} " 
			+ "</if>" 
			+ " ) " 
			+ " </foreach>"
			+ " ORDER BY A.policy_no" 
			+ "</script>")
	List<Rin1301QueryPolicyData> queryPolicyDetail2(@Param("records") List<Rin1301QueryPolicyDetailVOReq> voList);

	@ResultMap("com.asi.huanan.service.dao.mybatis.mapper.customerize.Rin1301Mapper.policyDataMap")
	@Select("<script>" 
			+ " SELECT DISTINCT A.policy_no, A.endorse_no, A.addr_no, A.useprop_name, "
			+ " D.ename as const_class, A.prop_addr, A.risk_no "
			+ " FROM fri_policy_dtl A, fri_fac_policy B,build_grade D " 
			+ " WHERE " 
			+ " A.policy_no = B.policy_no"
			+ " AND A.endorse_no = B.endorse_no" 
			+ " AND A.addr_no = B.addr_no"
			+ " AND Ltrim(A.const_class) = D.build_no  "
			+ "<if test=\"record.slipNo != null  and record.slipNo != ''\">"
			+ " AND B.slip_no = #{record.slipNo, jdbcType=VARCHAR} " 
			+ "</if>" 
			+ " ORDER BY A.policy_no" 
			+ "</script>")
	List<Rin1301QueryPolicyData> queryPolicyDetail2ByEdit(@Param("record") Rin1301QueryPolicyDetailVOReq vo);
	
	@ResultMap("com.asi.huanan.service.dao.mybatis.mapper.customerize.Rin1301Mapper.policyDataMap")
	@Select("<script>" 
			+ " SELECT DISTINCT A.policy_no, A.endorse_no, A.addr_no, A.useprop_name, B.LOG_seq  "
			+ " D.ename as const_class, A.prop_addr, A.risk_no "
			+ " FROM fri_policy_dtl A, LOG_fri_fac_policy B,build_grade D " 
			+ " WHERE " 
			+ " A.policy_no = B.policy_no"
			+ " AND A.endorse_no = B.endorse_no" 
			+ " AND A.addr_no = B.addr_no"
			+ " AND Ltrim(A.const_class) = D.build_no  "
			+ "<if test=\"record.slipNo != null  and record.slipNo != ''\">"
			+ " AND B.slip_no = #{record.slipNo, jdbcType=VARCHAR} " 
			+ "</if>" 
			+ "<if test=\"record.logSeq != null  and record.logSeq != ''\">"
			+ " AND B.LOG_seq = #{record.logSeq, jdbcType=VARCHAR} " 
			+ "</if>" 
			+ " ORDER BY A.policy_no" 
			+ "</script>")
	List<Rin1301QueryPolicyData> queryPolicyDetail2WithLogSeqByEdit(@Param("record") Rin1301QueryPolicyDetailVOReq vo);

	/**
	 * 臨分資料維護內頁_保單基本資料頁籤明細表格3<br>
	 * 
	 * @param rin1301QueryPolicyDetailVOReq
	 * @return
	 */
	@ResultMap("com.asi.huanan.service.dao.mybatis.mapper.customerize.Rin1301Mapper.policyDataMap")
	@Select("<script>" 
			+ " SELECT DISTINCT A.policy_no, A.endorse_no, A.addr_no, A.addition_seq, "
			+ " A.addition_no, D.ename as addition_ename "
			+ " FROM fri_policy_addition A, fri_policy_dtl X, converage D " 
			+ " WHERE "
			+ " Ltrim(A.addition_no) = D.converage_no " 
			+ " AND A.policy_no = X.policy_no"
			+ " AND A.endorse_no = X.endorse_no" 
			+ " AND A.addr_no = X.addr_no  AND "
			+ " <foreach collection=\"records\" item=\"record\" index=\"index\" separator=\"or\" open=\"(\" close=\")\" >  "
			+ " ( " 
			+ " A.policy_no  = #{record.policyNo, jdbcType=VARCHAR}"
			+ " AND A.endorse_no = #{record.endorseNo, jdbcType=VARCHAR} " 
			+ "<if test=\"record.addrNo != null  and record.addrNo != ''\">"
			+ " AND A.addr_no = #{record.addrNo, jdbcType=VARCHAR} " 
			+ "</if>" 
			
			+ " ) " 
			+ " </foreach>"
			+ " ORDER BY A.policy_no" 
			+ "</script>")
	List<Rin1301QueryPolicyData> queryPolicyDetail3(@Param("records") List<Rin1301QueryPolicyDetailVOReq> voList);

	@ResultMap("com.asi.huanan.service.dao.mybatis.mapper.customerize.Rin1301Mapper.policyDataMap")
	@Select("<script>" 
			+ " SELECT DISTINCT A.policy_no, A.endorse_no, A.addr_no, A.addition_seq, "
			+ " A.addition_no, D.ename as addition_ename "
			+ " FROM fri_policy_addition A, fri_fac_policy B, fri_policy_dtl X, converage D " 
			+ " WHERE "
			+ " A.policy_no = B.policy_no" 
			+ " AND Ltrim(A.addition_no) = D.converage_no "
			+ " AND A.endorse_no = B.endorse_no " 
			+ " AND B.policy_no = X.policy_no "
			+ " AND B.endorse_no = X.endorse_no " 
			+ " AND B.addr_no = X.addr_no "
			+ "<if test=\"record.slipNo != null  and record.slipNo != ''\">"
			+ " AND B.slip_no = #{record.slipNo, jdbcType=VARCHAR} " 
			+ "</if>" 
			+ " ORDER BY A.policy_no" 
			+ "</script>")
	List<Rin1301QueryPolicyData> queryPolicyDetail3ByEdit(@Param("record") Rin1301QueryPolicyDetailVOReq vo);
	
	@ResultMap("com.asi.huanan.service.dao.mybatis.mapper.customerize.Rin1301Mapper.policyDataMap")
	@Select("<script>" 
			+ " SELECT DISTINCT A.policy_no, A.endorse_no, A.addr_no, A.addition_seq, "
			+ " A.addition_no, D.ename as addition_ename, B.LOG_seq "
			+ " FROM fri_policy_addition A, LOG_fri_fac_policy B, fri_policy_dtl X, converage D " 
			+ " WHERE "
			+ " A.policy_no = B.policy_no" 
			+ " AND Ltrim(A.addition_no) = D.converage_no "
			+ " AND A.endorse_no = B.endorse_no " 
			+ " AND B.policy_no = X.policy_no "
			+ " AND B.endorse_no = X.endorse_no " 
			+ " AND B.addr_no = X.addr_no "
			+ "<if test=\"record.slipNo != null  and record.slipNo != ''\">"
			+ " AND B.slip_no = #{record.slipNo, jdbcType=VARCHAR} " 
			+ "</if>" 
			+ "<if test=\"record.logSeq != null  and record.logSeq != ''\">"
			+ " AND B.LOG_seq = #{record.logSeq, jdbcType=VARCHAR} " 
			+ "</if>" 
			+ " ORDER BY A.policy_no" 
			+ "</script>")
	List<Rin1301QueryPolicyData> queryPolicyDetail3WithLogSeqByEdit(@Param("record") Rin1301QueryPolicyDetailVOReq vo);

	/**
	 * 臨分資料維護內頁_險種保額保費頁籤明細表格4<br>
	 * 
	 * @param rin1301QueryPolicyDetailVOReq
	 * @return
	 */

	@ResultMap("com.asi.huanan.service.dao.mybatis.mapper.customerize.Rin1301Mapper.policyDataMap")
	@Select("<script>"
			+ " SELECT DISTINCT A.policy_no, A.endorse_no, A.addr_no, A.property_no, A.property_name,D.ename as property_ename, A.amt, "
			+ " C.coins_rate, ISNULL(ROUND(A.amt/(NULLIF(C.coins_rate,0)/100),0),0) as coins_amt  "
			+ " FROM fri_policy_prop A, fri_policy C, fri_policy_dtl X, prop_name D " 
			+ " WHERE "
			+ " A.policy_no = X.policy_no" 
			+ " AND A.endorse_no = X.endorse_no" 
			+ " AND A.addr_no = X.addr_no "
			+ " AND A.policy_no = C.policy_no" 
			+ " AND A.endorse_no = C.endorse_no"
			+ " AND A.property_no = D.prop_no AND"
			+ " <foreach collection=\"records\" item=\"record\" index=\"index\" separator=\"or\" open=\"(\" close=\")\" >  "
			+ " ( " + " A.policy_no  = #{record.policyNo, jdbcType=VARCHAR}"
			+ " AND A.endorse_no = #{record.endorseNo, jdbcType=VARCHAR} "
			+ "<if test=\"record.addrNo != null  and record.addrNo != ''\">"
			+ " AND A.addr_no = #{record.addrNo, jdbcType=VARCHAR} " 
			+ "</if>" + " ) " 
			+ " </foreach>"
			+ " ORDER BY A.policy_no" 
			+ "</script>")
	List<Rin1301QueryPolicyData> queryPolicyDetail4(@Param("records") List<Rin1301QueryPolicyDetailVOReq> voList);

	@ResultMap("com.asi.huanan.service.dao.mybatis.mapper.customerize.Rin1301Mapper.policyDataMap")
	@Select("<script>"
			+ " SELECT DISTINCT A.policy_no, A.endorse_no, A.addr_no, A.property_no, A.property_name,D.ename as property_ename, A.amt, "
			+ " C.coins_rate, ISNULL(ROUND(A.amt/(NULLIF(C.coins_rate,0)/100),0),0) as coins_amt  "
			+ " FROM fri_policy_prop A, fri_policy C, fri_policy_dtl X, prop_name D , fri_fac_policy B " 
			+ " WHERE "
			+ " A.policy_no = B.policy_no" 
			+ " AND A.endorse_no = B.endorse_no" 
			+ " AND A.addr_no = B.addr_no "
			+ " AND C.policy_no = B.policy_no" 
			+ " AND C.endorse_no = B.endorse_no" 
			+ " AND B.policy_no = X.policy_no"
			+ " AND B.endorse_no = X.endorse_no" 
			+ " AND B.addr_no = X.addr_no" 
			+ " AND A.property_no = D.prop_no"
			+ "<if test=\"record.slipNo != null  and record.slipNo != ''\">"
			+ " AND B.slip_no = #{record.slipNo, jdbcType=VARCHAR} " 
			+ "</if>" 
			+ " ORDER BY A.policy_no" 
			+ "</script>")
	List<Rin1301QueryPolicyData> queryPolicyDetail4ByEdit(@Param("record") Rin1301QueryPolicyDetailVOReq vo);
	
	
	@ResultMap("com.asi.huanan.service.dao.mybatis.mapper.customerize.Rin1301Mapper.policyDataMap")
	@Select("<script>"
			+ " SELECT DISTINCT A.policy_no, A.endorse_no, A.addr_no, A.property_no, A.property_name,D.ename as property_ename, A.amt, "
			+ " C.coins_rate, ISNULL(ROUND(A.amt/(NULLIF(C.coins_rate,0)/100),0),0) as coins_amt, B.LOG_seq  "
			+ " FROM fri_policy_prop A, fri_policy C, fri_policy_dtl X, prop_name D , LOG_fri_fac_policy B " 
			+ " WHERE "
			+ " A.policy_no = B.policy_no" 
			+ " AND A.endorse_no = B.endorse_no" 
			+ " AND A.addr_no = B.addr_no "
			+ " AND C.policy_no = B.policy_no" 
			+ " AND C.endorse_no = B.endorse_no" 
			+ " AND B.policy_no = X.policy_no"
			+ " AND B.endorse_no = X.endorse_no" 
			+ " AND B.addr_no = X.addr_no" 
			+ " AND A.property_no = D.prop_no"
			+ "<if test=\"record.slipNo != null  and record.slipNo != ''\">"
			+ " AND B.slip_no = #{record.slipNo, jdbcType=VARCHAR} " 
			+ "</if>" 
			+ "<if test=\"record.logSeq != null  and record.logSeq != ''\">"
			+ " AND B.LOG_seq = #{record.logSeq, jdbcType=VARCHAR} " 
			+ "</if>"
			+ " ORDER BY A.policy_no" 
			+ "</script>")
	List<Rin1301QueryPolicyData> queryPolicyDetail4WithLogSeqByEdit(@Param("record") Rin1301QueryPolicyDetailVOReq vo);

	@ResultMap("com.asi.huanan.service.dao.mybatis.mapper.customerize.Rin1301Mapper.policyDataMap")
	@Select("<script>" 
			+" SELECT DISTINCT A.policy_no, A.endorse_no, A.addr_no, C.policy_dbgn, C.policy_dend, "
			+" A.addition_seq, A.addition_no, A.addition_name, A.amt, A.prem, C.coins_rate, C.comm_rate, "
			+" ISNULL(ROUND(A.amt/(NULLIF(C.coins_rate,0)/100),0),0) as coins_amt, "
			+" ISNULL(ROUND(A.prem/(NULLIF(C.coins_rate,0)/100),0),0) as coins_prem, "
			+" ISNULL(ROUND(A.prem*C.comm_rate,0),0) as comm, "
			+" ISNULL(K.kind_id,'1') as content, "
			+" ISNULL(K.include_amt,'Y') as include_amt, "
			+" ISNULL(K.include_prem,'Y') as include_prem "
			+" FROM fri_policy_addition A "
			+" JOIN fri_policy C ON  A.policy_no = C.policy_no AND A.endorse_no = C.endorse_no "
			+" JOIN fri_policy_dtl X ON A.policy_no = X.policy_no AND A.endorse_no = X.endorse_no AND A.addr_no = X.addr_no "
			+" LEFT JOIN  fri_kind_mapping K  ON A.addition_no = K.addition_no "
			+ " WHERE "
			+ " A.addition_no != 'L' AND "
			+ " <foreach collection=\"records\" item=\"record\" index=\"index\" separator=\"or\" open=\"(\" close=\")\" >  "
			+ " ( " 
			+ " A.policy_no  = #{record.policyNo, jdbcType=VARCHAR} "
			+ " AND A.endorse_no = #{record.endorseNo, jdbcType=VARCHAR} " 
			+ "<if test=\"record.addrNo != null  and record.addrNo != ''\">"
			+ " AND A.addr_no = #{record.addrNo, jdbcType=VARCHAR} " 
			+ "</if>" + " ) " 
			+ " </foreach>"
			+ " ORDER BY A.policy_no " 
			+ "</script>")
	List<Rin1301QueryPolicyData> queryPolicyDetail5(@Param("records") List<Rin1301QueryPolicyDetailVOReq> voList);

	@ResultMap("com.asi.huanan.service.dao.mybatis.mapper.customerize.Rin1301Mapper.policyDataMap")
	@Select("<script>"
			+ " SELECT A.policy_no, A.endorse_no, A.addr_no, A.risk_no, C.policy_dbgn as duty_dbgn , C.policy_dend as duty_dend ,"
			+ " ISNULL(ROUND((A.amt_flt+A.amt_fix)/(NULLIF(C.coins_rate,0)/100),0),0) as amt, "
			+ " ISNULL(ROUND((A.prem_flt+A.prem_fix)/(NULLIF(C.coins_rate,0)/100),0),0) as prem,"
			+ " ISNULL(ROUND(A.amt_typ/(NULLIF(C.coins_rate,0)/100),0),0) as amt_typ, "
			+ " ISNULL(ROUND(A.prem_typ/(NULLIF(C.coins_rate,0)/100),0),0) as prem_typ, "
			+ " ISNULL(ROUND(A.amt_ear/(NULLIF(C.coins_rate,0)/100),0),0) as amt_ear, "
			+ " ISNULL(ROUND(A.prem_ear/(NULLIF(C.coins_rate,0)/100),0),0) as prem_ear, "
			+ " C.coins_rate, C.end_reason " + " FROM fri_policy_dtl A, fri_policy C  " 
			+ " WHERE "
			+ "  A.policy_no = C.policy_no  " 
			+ "  AND A.endorse_no = C.endorse_no "
			+ "<if test=\"null != records and records.size > 0\">"
			+" AND "
			+ " <foreach collection=\"records\" item=\"record\" index=\"index\" separator=\"or\" open=\"(\" close=\")\" >  "
			+ " ( " 
			+ " A.policy_no  = #{record.policyNo, jdbcType=VARCHAR}"
			+ " AND A.endorse_no = #{record.endorseNo, jdbcType=VARCHAR} " 
			+ "<if test=\"record.addrNo != null  and record.addrNo != ''\">"
			+ " AND A.addr_no = #{record.addrNo, jdbcType=VARCHAR} " 
			+ "</if>" 
			+ " ) " 
			+ " </foreach>"
			+ "</if>"
			+ " ORDER BY A.policy_no" 
			+ "</script>")
	List<Rin1301QueryPolicyData> queryPolicyDetail10(@Param("records") List<Rin1301QueryPolicyDetailVOReq> voList);

	@ResultMap("com.asi.huanan.service.dao.mybatis.mapper.customerize.Rin1301Mapper.policyDataMap")
	@Select("<script>"
			+ " SELECT A.policy_no, A.endorse_no, A.addr_no, A.risk_no, C.policy_dbgn as duty_dbgn , C.policy_dend as duty_dend ,"
			+ " ISNULL(ROUND((A.amt_flt+A.amt_fix)/(NULLIF(C.coins_rate,0)/100),0),0) as amt, "
			+ " ISNULL(ROUND((A.prem_flt+A.prem_fix)/(NULLIF(C.coins_rate,0)/100),0),0) as prem,"
			+ " ISNULL(ROUND(A.amt_typ/(NULLIF(C.coins_rate,0)/100),0),0) as amt_typ, "
			+ " ISNULL(ROUND(A.prem_typ/(NULLIF(C.coins_rate,0)/100),0),0) as prem_typ, "
			+ " ISNULL(ROUND(A.amt_ear/(NULLIF(C.coins_rate,0)/100),0),0) as amt_ear, "
			+ " ISNULL(ROUND(A.prem_ear/(NULLIF(C.coins_rate,0)/100),0),0) as prem_ear, "
			+ " C.coins_rate, C.end_reason " 
			+ " FROM fri_policy_dtl A, fri_fac_policy B,fri_policy C  " 
			+ " WHERE "
			+ "  A.policy_no = B.policy_no  " 
			+ " AND A.endorse_no = B.endorse_no " 
			+ " AND A.addr_no = B.addr_no"
			+ " AND A.policy_no = C.policy_no" 
			+ " AND A.endorse_no = C.endorse_no"
			+ "<if test=\"record.slipNo != null  and record.slipNo != ''\">"
			+ " AND B.slip_no = #{record.slipNo, jdbcType=VARCHAR} " 
			+ "</if>"
			+ " ORDER BY A.policy_no" 
			+ "</script>")
	List<Rin1301QueryPolicyData> queryPolicyDetail10ByEdit(@Param("record") Rin1301QueryPolicyDetailVOReq vo);
	
	
	@ResultMap("com.asi.huanan.service.dao.mybatis.mapper.customerize.Rin1301Mapper.policyDataMap")
	@Select("<script>"
			+ " SELECT A.policy_no, A.endorse_no, A.addr_no, A.risk_no, C.policy_dbgn as duty_dbgn , C.policy_dend as duty_dend ,"
			+ " ISNULL(ROUND((A.amt_flt+A.amt_fix)/(NULLIF(C.coins_rate,0)/100),0),0) as amt, "
			+ " ISNULL(ROUND((A.prem_flt+A.prem_fix)/(NULLIF(C.coins_rate,0)/100),0),0) as prem,"
			+ " ISNULL(ROUND(A.amt_typ/(NULLIF(C.coins_rate,0)/100),0),0) as amt_typ, "
			+ " ISNULL(ROUND(A.prem_typ/(NULLIF(C.coins_rate,0)/100),0),0) as prem_typ, "
			+ " ISNULL(ROUND(A.amt_ear/(NULLIF(C.coins_rate,0)/100),0),0) as amt_ear, "
			+ " ISNULL(ROUND(A.prem_ear/(NULLIF(C.coins_rate,0)/100),0),0) as prem_ear, "
			+ " C.coins_rate, C.end_reason, B.LOG_seq " 
			+ " FROM fri_policy_dtl A, LOG_fri_fac_policy B,fri_policy C  " 
			+ " WHERE "
			+ "  A.policy_no = B.policy_no  " 
			+ " AND A.endorse_no = B.endorse_no " 
			+ " AND A.addr_no = B.addr_no"
			+ " AND A.policy_no = C.policy_no" 
			+ " AND A.endorse_no = C.endorse_no"
			+ "<if test=\"record.slipNo != null  and record.slipNo != ''\">"
			+ " AND B.slip_no = #{record.slipNo, jdbcType=VARCHAR} " 
			+ "</if>"
			+ "<if test=\"record.logSeq != null  and record.logSeq != ''\">"
			+ " AND B.LOG_seq = #{record.logSeq, jdbcType=VARCHAR} " 
			+ "</if>"
			+ " ORDER BY A.policy_no" 
			+ "</script>")
	List<Rin1301QueryPolicyData> queryPolicyDetail10WithLogSeqByEdit(@Param("record") Rin1301QueryPolicyDetailVOReq vo);

	@ResultMap("com.asi.huanan.service.dao.mybatis.mapper.customerize.Rin1301Mapper.rinserResultMap")
	@Select("<script>" 
			+ " SELECT DISTINCT A.*, B.ename as rinser_ename " 
			+ " , (SELECT TOP 1 close_date "
			+ "    FROM fri_accounting " 
			+ "    WHERE bill_no=a.slip_no " 
			+ "   AND AMEND_SEQ = ("
			+ " SELECT MAX(AMEND_SEQ) FROM fri_accounting WHERE BILL_NO=A.slip_no)) as close_date  "
			+ " FROM fri_fac_rincom A, fri_com B" 
			+ " WHERE " 
			+ "  A.rin_com_id= B.rin_com_id  "
			+ "<if test=\"record.slipNo != null  and record.slipNo != ''\">"
			+ " AND A.slip_no = #{record.slipNo, jdbcType=VARCHAR} " 
			+ "</if>" 
			+ "</script>")
	List<Rin1301QueryRinserData> queryRinserDetail(@Param("record") Rin1301QueryPolicyDetailVOReq vo);
	
	
	@ResultMap("com.asi.huanan.service.dao.mybatis.mapper.customerize.Rin1301Mapper.logRinserResultMap")
	@Select("<script>" 
			+ " SELECT DISTINCT A.*, B.ename as rinser_ename " 
			+ " , (SELECT TOP 1 close_date "
			+ "    FROM fri_accounting " 
			+ "    WHERE bill_no=a.slip_no " 
			+ "   AND AMEND_SEQ = ("
			+ " SELECT MAX(AMEND_SEQ) FROM fri_accounting WHERE BILL_NO=A.slip_no)) as close_date  "
			+ " FROM LOG_fri_fac_rincom A, fri_com B" 
			+ " WHERE " 
			+ "  A.rin_com_id= B.rin_com_id  "
			+ "<if test=\"record.slipNo != null  and record.slipNo != ''\">"
			+ " AND A.slip_no = #{record.slipNo, jdbcType=VARCHAR} " 
			+ "</if>" 
			+ "<if test=\"record.logSeq != null  and record.logSeq != ''\">"
			+ " AND A.LOG_seq = #{record.logSeq, jdbcType=VARCHAR} " 
			+ "</if>" 
			+ "</script>")
	List<Rin1301QueryRinserData> queryRinserDetailForLogSeq(@Param("record") Rin1301QueryPolicyDetailVOReq vo);

	

}

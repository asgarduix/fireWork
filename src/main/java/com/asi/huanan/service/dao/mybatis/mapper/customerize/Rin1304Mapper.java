package com.asi.huanan.service.dao.mybatis.mapper.customerize;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1304QueryTree0;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1304QueryTree1;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1304QueryTree2;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1304QueryTree3;
import com.asi.huanan.vo.Rin1304QueryTreeVoReq;

public interface Rin1304Mapper {
	/**
	 * 
	 * @param rin1301QueryMainDataVOReq
	 * @return
	 * @throws Exception
	 */
	@Select("<script>" 
	        + "  SELECT policy_no AS policyNo, \r\n" + "  endorse_no AS endorseNo, \r\n"
			+ "  cinsurant AS cinsurant, \r\n" + "  einsurant AS einsurant, \r\n" + "  fire_type AS fireType, \r\n"
			+ "  policy_type AS policyType, \r\n" + "  policy_year AS policyYear, \r\n"
			+ "  irate_type AS irateType, \r\n" + "  ifloat AS ifloat, \r\n" + "  policy_dbgn AS policyDbgn, \r\n"
			+ "  policy_dend AS policyDend, \r\n" + "  coins_flag AS coinsFlag, \r\n" + "  all_amt AS allAmt, \r\n"
			+ "  all_prem AS allPrem, \r\n" + "  amt AS amt, \r\n" + "  prem AS prem, \r\n"
			+ "  com_amt AS comAmt, \r\n" + "  com_prem AS comPrem, \r\n" + "  comm AS comm, \r\n"
			+ "  comm_rate AS commRate, \r\n" + "  prepay_rate AS prepayRate, \r\n"
			+ "  nature_flag AS natureFlag, \r\n" + "  end_reason AS endReason, \r\n" + "  calc_flag AS calcFlag, \r\n"
			+ "  ref_no AS refNo, \r\n" + "  policy_dprt AS policyDprt, \r\n" + "  change_flag AS changeFlag, \r\n"
			+ "  policy_mode AS policyMode, \r\n" + "  coins_rate AS coinsRate, \r\n"
			+ "  old_policy AS oldPolicy, \r\n" + "  ri_policyno AS riPolicyno, \r\n" + "  rin_com_id AS rinComId, \r\n"
			+ "  txtOfficer1 AS txtofficer1, \r\n" + "  txtOfficer2 AS txtofficer2, \r\n"
			+ "  fac_flag AS facFlag, \r\n" + "  eqsmin AS eqsmin, \r\n" + "  eqprum AS eqprum, \r\n"
			+ "  policy_dprt_Ori AS policyDprtOri, \r\n" + "  AccEnterDate AS accenterdate, \r\n"
			+ "  AccCloseDate AS accclosedate, \r\n" + "  AccTransferState AS acctransferstate, \r\n"
			+ "  Broker_id AS brokerId, \r\n" + "  Receive_date AS receiveDate, \r\n" + "  currency AS currency, \r\n"
			+ "  CurrencyExchangeRate AS currencyexchangerate, \r\n" + "  office AS office, \r\n"
			+ "  CountryID AS countryid, \r\n" + "  CountryName AS countryname, \r\n" + "  temp_flag AS tempFlag, \r\n"
			+ "  org_prem AS orgPrem, \r\n" + "  org_comm AS orgComm, \r\n" + "  rin_com_id_OLD AS rinComIdOld, \r\n"
			+ "  broker_id_OLD AS brokerIdOld, \r\n" + "  mkovse AS mkovse \r\n" 
			+ "FROM  fri_policy \r\n"
			+ "WHERE 1 = 1 " 
			+ "AND policy_no = #{record.policyNo}  \n"
			+"AND endorse_no = #{record.endorseNo} " 
			+ "</script>")
	List<Rin1304QueryTree0> queryTreeLayer0(@Param("record") Rin1304QueryTreeVoReq rin1301QueryMainDataVOReq);

	/**
	 * 
	 * @param rin1301QueryMainDataVOReq
	 * @return
	 * @throws Exception
	 */
	@Select("<script>" 
	        + "SELECT\r\n" + "  policy_no AS policyNo, \r\n" + "  endorse_no AS endorseNo, \r\n"
			+ "  addr_no AS addrNo, \r\n" + "  addr_no_ori AS addrNoOri, \r\n" + "  risk_no AS riskNo, \r\n"
			+ "  amt AS amt, \r\n" + "  prem AS prem, \r\n" + "  prop_addr AS propAddr, \r\n"
			+ "  zip_code AS zipCode, \r\n" + "  area_code AS areaCode, \r\n" + "  useprop_code AS usepropCode, \r\n"
			+ "  useprop_name AS usepropName, \r\n" + "  const_class AS constClass, \r\n"
			+ "  limit_no AS limitNo, \r\n" + "LIMIT\r\n" + "  AS \r\n" + "LIMIT\r\n" + ", amt_flt AS amtFlt, \r\n"
			+ "  prem_flt AS premFlt, \r\n" + "  amt_fix AS amtFix, \r\n" + "  prem_fix AS premFix, \r\n"
			+ "  amt_typ AS amtTyp, \r\n" + "  prem_typ AS premTyp, \r\n" + "  amt_ear AS amtEar, \r\n"
			+ "  prem_ear AS premEar, \r\n" + "  risk_flag AS riskFlag, \r\n" + "  risk_name AS riskName, \r\n"
			+ "  limit_rate AS limitRate, \r\n" + "  limit_ori AS limitOri, \r\n" + "  flor_no AS florNo, \r\n"
			+ "  policy_dbgn AS policyDbgn, \r\n" + "  policy_dend AS policyDend, \r\n"
			+ "  end_reason AS endReason, \r\n" + "  mercno AS mercno, \r\n" + "  (  SELECT\r\n"
			+ "  TOP 1 acct_flag \r\n" + "  FROM fri_treaty_shares \r\n" + "  WHERE\r\n"
			+ "      fri_treaty_shares.policy_no = fri_policy_dtl.policy_no AND\r\n"
			+ "      fri_treaty_shares.endorse_no = fri_policy_dtl.endorse_no AND\r\n"
			+ "      fri_treaty_shares.addr_no = fri_policy_dtl.addr_no AND\r\n"
			+ "      fri_treaty_shares.risk_no = fri_policy_dtl.risk_no AND\r\n"
			+ "      fri_treaty_shares.acct_flag = 'Y'\r\n" + "  ) AS acct_flag \r\n"
			+ "FROM fri_policy_dtl \r\n" 
			+ "WHERE 1 = 1 " 
			+ " AND policy_no = #{record.policyNo} \n"
			+" AND endorse_no = #{record.endorseNo} \n" 
			+" order by addrNo "
			+ "</script>")
	List<Rin1304QueryTree1> queryTreeLayer1(@Param("record") Rin1304QueryTreeVoReq rin1301QueryMainDataVOReq);

	/**
	 * 
	 * @param rin1301QueryMainDataVOReq
	 * @return
	 * @throws Exception
	 */
	@Select("<script>" 
	        + "SELECT\r\n" + "  policy_no as policyNo,\r\n" + "endorse_no as endorseNo,\r\n"
			+ "addr_no as addrNo,\r\n" + "prop_no as propNo,\r\n" + "float_flag as floatFlag,\r\n"
			+ "property_no as propertyNo,\r\n" + "property_name as propertyName,\r\n"
			+ "property_code as propertyCode,\r\n" + "amt as amt,\r\n" + "prem as prem,\r\n"
			+ "useprop_code as usepropCode,\r\n" + "useprop_name as usepropName,\r\n" + "const_class as constClass,\r\n"
			+ "limit_no as limitNo,\r\n" + "spec_term as specTerm,\r\n" + "ext_term as extTerm\r\n" + "FROM\r\n"
			+ "  fri_policy_prop \r\n" 
			+ "WHERE 1 = 1 " 
			+ " AND policy_no = #{record.policyNo}  \n"
			+" AND endorse_no = #{record.endorseNo} \n" 
			+ "AND  addr_no =  #{record.addrNo} "
			+ "</script>")
	List<Rin1304QueryTree2> queryTreeLayer2(@Param("record") Rin1304QueryTreeVoReq rin1301QueryMainDataVOReq);

	/**
	 * 
	 * @param rin1301QueryMainDataVOReq
	 * @return
	 * @throws Exception
	 */
	@Select("<script>" 
	        + "SELECT\r\n" + "policy_no as policyNo,\r\n" + "endorse_no as endorseNo,\r\n"
			+ "addr_no as addrNo,\r\n" + "prop_no as propNo,\r\n" + "addition_seq as additionSeq,\r\n"
			+ "addition_no as additionNo,\r\n" + "addition_name as additionName,\r\n" + "amt as amt,\r\n"
			+ "prem as prem,\r\n" + "prercv_rate as prercvRate,\r\n" + "limit as limit,\r\n"
			+ "limit_year as limitYear,\r\n" + "limit_rate as limitRate,\r\n" + "rate as rate,\r\n"
			+ "deduct_rem as deductRem,\r\n" + "mercno as mercno\r\n" 
			+ "FROM fri_policy_addition \r\n" 
			+ "WHERE 1 = 1 "  
			+ " AND policy_no = #{record.policyNo}  \n"
			+" AND endorse_no = #{record.endorseNo} \n"  
			+ "AND  addr_no =  #{record.addrNo} \n" 
			+ "AND prop_no = #{record.propNo} "
			+ "</script>")
	List<Rin1304QueryTree3> queryTreeLayer3(@Param("record") Rin1304QueryTreeVoReq rin1301QueryMainDataVOReq);

}

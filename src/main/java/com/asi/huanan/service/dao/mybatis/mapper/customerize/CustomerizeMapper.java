package com.asi.huanan.service.dao.mybatis.mapper.customerize;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.asi.huanan.service.dao.mybatis.model.FriFac;
import com.asi.huanan.service.dao.mybatis.model.FriPolicyDtl;
import com.asi.huanan.service.dao.mybatis.model.FriTreaty;
import com.asi.huanan.service.dao.mybatis.model.FriTreatyShares;
import com.asi.huanan.service.dao.mybatis.model.customerize.BatchqueueJoinBatchlist;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1101FricomJoinRicmpf1;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1205TableMain;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1302Table;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1303Query2;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1303QueryMain;
import com.asi.huanan.vo.Rin1205QueryVOReq;
import com.asi.huanan.vo.Rin1203AVOResp;
import com.asi.huanan.vo.Rin1204VOReq1;
import com.asi.huanan.vo.Rin1204VOReq2;
import com.asi.huanan.vo.Rin1204VOReq3;
import com.asi.huanan.vo.Rin1204VOResp;
import com.asi.huanan.vo.Rin1204VOResp1;
import com.asi.huanan.vo.Rin1204VOResp2;
import com.asi.huanan.vo.Rin1204VOResp3;
import com.asi.huanan.vo.Rin1204VOResp4;
import com.asi.huanan.vo.Rin1204VOResp5;
import com.asi.huanan.vo.Rin1204VOResp6;
import com.asi.huanan.vo.Rin1204VOResp7;
import com.asi.huanan.vo.Rin1204VOResp8;
import com.asi.huanan.vo.Rin1204VOResp9;
import com.asi.huanan.vo.Rin1206PrintModelVO;
import com.asi.huanan.vo.Rin1206QueryConditionVO;
import com.asi.huanan.vo.Rin1206QueryDataVO;
import com.asi.huanan.vo.Rin1303QueryRinComBySlipNoVOResp;

public interface CustomerizeMapper {

	/**
	 * Rin1101A-「再保人代號」單筆搜尋
	 * 
	 * @param rinComId
	 * @return list FricomJoinRicmpf1
	 * @author yiting 2021/09/29
	 */
	@Select("<script> select a.rin_com_id as rinComId, a.ename, a.cname, a.acct_Area as acctArea, a.favtyp, a.[inout], a.remark1, a.shige, a.watchto, a.signnok, CONVERT (varchar(12), a.blocklst ,111) as blocklst, b.ocode, b.carmrk, b.marmrk, b.firmrk, b.accmrk, b.ahmrk from fri_com a left join ricmpf1 b on a.rin_com_id = b.compn1 where a.rin_Com_Id = #{rinComId,jdbcType=VARCHAR} </script>")
	List<Rin1101FricomJoinRicmpf1> queryOneReiner(@Param("rinComId") String rinComId);
//此註解為上列方法SQL的格式
//	@Select("<script>" + "select a.rin_com_id as rinComId, a.ename, a.cname, a.acct_Area as acctArea, a.favtyp, "
//			+ "a.[inout], a.remark1, a.shige, a.watchto, a.signnok, "
//			+ "CONVERT (varchar(12), a.blocklst ,111) as blocklst, "
//			+ "b.ocode, b.carmrk, b.marmrk, b.firmrk, b.accmrk, b.ahmrk " + "from fri_com a "
//			+ "left join ricmpf1 b on a.rin_com_id = b.compn1 " + "where a.rin_Com_Id = #{rinComId,jdbcType=VARCHAR}"
//			+ "</script>")
//	List<Rin1101FricomJoinRicmpf1> queryOneReiner(@Param("rinComId") String rinComId);

	// 再保臨分到期_立即執行
	@Select("<script>" + " SELECT Distinct A.cession_no, C.policy_no ,A.insurant, A.use_prop, E.All_amt, E.coins_rate, "
			+ " E.amt,A.treaty_dend, A.address, F.ename, B.share_rate "
			+ " FROM fri_fac A, fri_fac_rincom B, fri_fac_policy C, fri_policy E, fri_com F "
			+ " WHERE A.slip_no = B.slip_no AND A.slip_no = C.slip_no AND C.policy_no = E.policy_no "
			+ " AND C.endorse_no = E.endorse_no AND B.rin_com_id = F.rin_com_id  "
			+ " <if test=\"treaty_dend_Bgn != null\"> AND A.treaty_dend &gt;= #{treaty_dend_Bgn,jdbcType=TIMESTAMP} </if> "
			+ " <if test=\"treaty_dend_End != null \"> AND A.treaty_dend &lt;= #{treaty_dend_End,jdbcType=TIMESTAMP} </if> "
			+ " order by A.cession_no, C.policy_no ,A.insurant, A.use_prop, E.All_amt, E.coins_rate, E.amt, A.treaty_dend, A.address, F.ename, B.share_rate "
			+ "</script>")
	List<Rin1302Table> getRin1302MainData(@Param("treaty_dend_Bgn") Date treaty_dend_Bgn, @Param("treaty_dend_End") Date treaty_dend_End);

	// 批次作業監視器_資料搜尋_初始畫面
	@Select("<script>" + " SELECT A.account, A.batchid, A.endtime, A.exitcode, A.processcontrolid, A.processstatus, "
			+ " A.starttime, A.submittime, A.taskid, B.batchdescription " + " FROM batchqueue A, batchlist B "
			+ " WHERE A.batchid = B.batchid " + " <if test=\"account != 'fire_reins'\">"
			+ " AND A.account = #{account,jdbcType=VARCHAR} " + " </if>" + " ORDER BY A.submittime DESC" + "</script>")
	List<BatchqueueJoinBatchlist> queryUseAccount(@Param("account") String account);

	// 批次作業監視器_資料搜尋_報表選項
	@Select("<script>" + " SELECT A.account, A.batchid, A.endtime, A.exitcode, A.processcontrolid, A.processstatus, "
			+ " A.starttime, A.submittime, A.taskid, B.batchdescription " + " FROM batchqueue A, batchlist B "
			+ " WHERE 1=1 " + " and A.batchid = B.batchid " + " <if test=\"account != 'fire_reins'\">"
			+ " AND A.account = #{account,jdbcType=VARCHAR} " + " </if>"
			+ " <if test=\"batchid != null and batchid !=''\"> " + " AND B.batchid = #{batchid,jdbcType=VARCHAR}  "
			+ " AND A.BatchReprotAccessPath LIKE '%xls%' "
			+ " </if>" + " ORDER BY A.submittime DESC" + "</script>")
	List<BatchqueueJoinBatchlist> queryUseAccountAndBatchid(@Param("account") String account,
			@Param("batchid") String batchid);
	
	// 批次作業監視器_資料搜尋_報表選項(pdf)
	@Select("<script>" + " SELECT A.account, A.batchid, A.endtime, A.exitcode, A.processcontrolid, A.processstatus, "
			+ " A.starttime, A.submittime, A.taskid, B.batchdescription " + " FROM batchqueue A, batchlist B "
			+ " WHERE 1=1 " + " and A.batchid = B.batchid " + " <if test=\"account != 'fire_reins'\">"
			+ " AND A.account = #{account,jdbcType=VARCHAR} " + " </if>"
			+ " <if test=\"batchid != null and batchid !=''\"> " + " AND B.batchid = #{batchid,jdbcType=VARCHAR}  "
			+ " AND A.BatchReprotAccessPath LIKE '%pdf%' "
			+ " </if>" + " ORDER BY A.submittime DESC" + "</script>")
	List<BatchqueueJoinBatchlist> queryUseAccountAndBatchid2(@Param("account") String account,
			@Param("batchid") String batchid);

	/**
	 * Rin1203-每日排程insert N年前資料到fri_temparea
	 * 
	 * @param policy_dprtS,policy_dprtE
	 * @return
	 * @author Sophia 2021/11/11
	 */
//	@Insert({"<script> "
//			, "INSERT INTO fri_temparea "
//			, "select distinct Pd.area_code as area_code , 0 as proc_count from fri_policy as Pm,fri_policy_dtl as Pd "
//			, "where  Pm.policy_dprt between  #{policy_dprtS,jdbcType=VARCHAR} and  #{policy_dprtE,jdbcType=VARCHAR} "
//			, "and Pm.policy_no = Pd.policy_no and Pm.endorse_no = Pd.endorse_no "
//			, "and Pm.calc_flag = 'Y' and PD.area_code like '%' "
//			, "and Pd.area_code not in (select distinct PD.area_code from fri_policy as Pm, fri_policy_dtl as Pd "
//			, "where  Pm.policy_dprt between  #{policy_dprtS,jdbcType=VARCHAR} and  #{policy_dprtE,jdbcType=VARCHAR} "
//			, "and Pd.risk_flag ='Y' and Pm.policy_no = Pd.policy_no and Pm.endorse_no = Pd.endorse_no and Pm.calc_flag = 'Y' and PD.area_code like '%') "
//			, "UNION "
//			, "select distinct Pd.area_code as area_code, 0 as proc_count from fri_policy as Pm, fri_policy_dtl as Pd "
//			, "where  Pm.policy_dprt between  #{policy_dprtS,jdbcType=VARCHAR} and  #{policy_dprtE,jdbcType=VARCHAR} "
//			, "and Pm.policy_no = Pd.policy_no and Pm.endorse_no = Pd.endorse_no and Pm.calc_flag = 'Y' and Pd.risk_flag = 'N' and PD.area_code like '%' "
//			, "and Pd.area_code in (select distinct PD.area_code from fri_policy as Pm, fri_policy_dtl as Pd "
//			, "where  Pm.policy_dprt between  #{policy_dprtS,jdbcType=VARCHAR} and  #{policy_dprtE,jdbcType=VARCHAR} "
//			, "and Pd.risk_flag='Y' and Pm.policy_no = Pd.policy_no and Pm.endorse_no = Pd.endorse_no and Pm.calc_flag = 'Y' and PD.area_code like '%')"
//			, "UNION "
//			, "select distinct Pd.area_code as area_code, -1 as proc_count from fri_policy as Pm, fri_policy_dtl as Pd "
//			, "where  Pm.policy_dprt between  #{policy_dprtS,jdbcType=VARCHAR} and  #{policy_dprtE,jdbcType=VARCHAR} "
//			, "and Pm.policy_no = Pd.policy_no and Pm.endorse_no = Pd.endorse_no and Pm.calc_flag = 'Y' and PD.area_code like '%' "
//			, "and Pd.area_code not in (select distinct PD.area_code from fri_policy as Pm, fri_policy_dtl as Pd "
//			, "where  Pm.policy_dprt between  #{policy_dprtS,jdbcType=VARCHAR} and  #{policy_dprtE,jdbcType=VARCHAR} and Pd.risk_flag='N' "
//			, "and Pm.policy_no = Pd.policy_no and Pm.endorse_no = Pd.endorse_no and Pm.calc_flag = 'Y' and PD.area_code like '%') "
//			, "</script>"})
	@Insert("<script> INSERT INTO fri_temparea select distinct Pd.area_code as area_code , 0 as proc_count from fri_policy as Pm,fri_policy_dtl as Pd where  Pm.policy_dprt between  #{policy_dprtS,jdbcType=VARCHAR} and  #{policy_dprtE,jdbcType=VARCHAR} and Pm.policy_no = Pd.policy_no and Pm.endorse_no = Pd.endorse_no and Pm.calc_flag = 'Y' and PD.area_code like '%' and Pd.area_code not in (select distinct PD.area_code from fri_policy as Pm, fri_policy_dtl as Pd where  Pm.policy_dprt between  #{policy_dprtS,jdbcType=VARCHAR} and  #{policy_dprtE,jdbcType=VARCHAR} and Pd.risk_flag ='Y' and Pm.policy_no = Pd.policy_no and Pm.endorse_no = Pd.endorse_no and Pm.calc_flag = 'Y' and PD.area_code like '%') UNION select distinct Pd.area_code as area_code, 0 as proc_count from fri_policy as Pm, fri_policy_dtl as Pd where  Pm.policy_dprt between  #{policy_dprtS,jdbcType=VARCHAR} and  #{policy_dprtE,jdbcType=VARCHAR} and Pm.policy_no = Pd.policy_no and Pm.endorse_no = Pd.endorse_no and Pm.calc_flag = 'Y' and Pd.risk_flag = 'N' and PD.area_code like '%' and Pd.area_code in (select distinct PD.area_code from fri_policy as Pm, fri_policy_dtl as Pd where  Pm.policy_dprt between  #{policy_dprtS,jdbcType=VARCHAR} and  #{policy_dprtE,jdbcType=VARCHAR} and Pd.risk_flag='Y' and Pm.policy_no = Pd.policy_no and Pm.endorse_no = Pd.endorse_no and Pm.calc_flag = 'Y' and PD.area_code like '%') UNION select distinct Pd.area_code as area_code, -1 as proc_count from fri_policy as Pm, fri_policy_dtl as Pd where  Pm.policy_dprt between  #{policy_dprtS,jdbcType=VARCHAR} and  #{policy_dprtE,jdbcType=VARCHAR} and Pm.policy_no = Pd.policy_no and Pm.endorse_no = Pd.endorse_no and Pm.calc_flag = 'Y' and PD.area_code like '%' and Pd.area_code not in (select distinct PD.area_code from fri_policy as Pm, fri_policy_dtl as Pd where  Pm.policy_dprt between  #{policy_dprtS,jdbcType=VARCHAR} and  #{policy_dprtE,jdbcType=VARCHAR} and Pd.risk_flag='N' and Pm.policy_no = Pd.policy_no and Pm.endorse_no = Pd.endorse_no and Pm.calc_flag = 'Y' and PD.area_code like '%') </script>")
	int insertToFriTemparea(@Param("policy_dprtS") String policy_dprtS, @Param("policy_dprtE") String policy_dprtE);

	/**
	 * Rin1203-每日排程刪除舊資料
	 * 
	 * @param
	 * @return
	 * @author Sophia 2021/11/11
	 */
	@Delete("<script> DELETE FROM  fri_temparea  </script>")
	int deleteAllFriTemparea();

	/**
	 * Rin1205-「分保同險資料」搜尋
	 * 
	 * @param model
	 * 
	 * @return list Rin1205TableMain
	 * @author yiting 2021/11/12
	 */
	@Select("<script> SELECT distinct fts.policy_no " + ",fts.treaty_year " + ",fts.endorse_no " + ",fts.addr_no "
			+ ",convert(varchar, fts.duty_bgn, 111) as duty_bgn " + ",convert(varchar, fts.duty_end, 111) as duty_end "
			+ ",fts.risk_no " + ",fpd.useprop_name " + ",convert(varchar, fp.policy_dprt, 111) as policy_dprt "
			+ ",fpd.amt " + ",(fpd.prem + fpd.prem_typ + fpd.prem_ear) as prem " + ",fpd.prem as share_prem "
			+ ",fpd.[limit] " + ",fpd.amt_typ " + ",fpd.amt_ear " + ", (select top 1 acct_flag from fri_treaty_shares "
			+ "	where policy_no=fpd.policy_no " + "	AND endorse_no = fpd.endorse_no " + "	AND addr_no = fpd.addr_no "
			+ "	AND treaty_no != 'Retain' " + "	AND treaty_no != 'Spc_Ret' " + ") as acct_flag " + ",fts.iendorsement2 "
			+ "FROM fri_treaty_shares fts " + "INNER JOIN fri_policy fp " + "ON fp.policy_no = fts.policy_no "
			+ "AND fp.endorse_no = fts.endorse_no " + "AND fts.treaty_year = #{record.treatyYear,jdbcType=VARCHAR} "
			+ "AND fts.risk_no = #{record.riskNo,jdbcType=VARCHAR} " + "LEFT OUTER JOIN fri_policy_dtl fpd "
			+ "ON fpd.policy_no = fts.policy_no " + "AND fpd.endorse_no = fts.endorse_no "
			+ "AND fpd.addr_no = fts.addr_no " + "WHERE fpd.policy_dbgn &lt; #{record.policyDate,jdbcType=VARCHAR} "
			+ "AND fpd.policy_dend &gt;= #{record.policyDate,jdbcType=VARCHAR} "
			+ "Order by policy_dprt, duty_bgn, fts.policy_no, fts.endorse_no, fts.addr_no</script>")

	List<Rin1205TableMain> queryRin1205MainData(@Param("record") Rin1205QueryVOReq record);

	/**
	 * Rin1203A-同險設定查詢資料
	 * 
	 * @param policy_dprtS
	 * @param policy_dprtE
	 * @param txtareaCode
	 * @return
	 * @author Sophia 2021/11/16
	 */
	@Select({"<script>"
			, " select Pd.risk_no as txtrisk_no, 'Y' as txtrisk_flag, max(Pm.change_flag) as change_flag,"
			, " Pm.policy_no as txtpolicy_no, Pm.endorse_no as txtendorse_no, Pm.cinsurant as txtcinsurant,"
			, " max(Pd.addr_no) as numaddr_no, Pd.prop_addr as txtprop_addr,Pd.useprop_code as txtuseprop_code,"
			, " max(Pd.useprop_name) as txtuseprop_name, sum(Pd.amt_typ) as amt_typ, sum(Pd.amt_ear) as amt_ear,"
			, " sum(Pd.prem_typ) as prem_typ, sum(Pd.prem_ear) as prem_ear, max(Pm.policy_dbgn) as policy_dbgn, "
			, " max(Pm.policy_dend) as policy_dend, sum(Pd.amt) as numamt, sum(Pd.prem) as prem, max(Pd.limit_no) as limit_no, "
			, " max(Pd.limit) as limit, max(Pd.zip_code) as zip_code, max(Pd.area_code) as txtarea_code, max(Pd.risk_name) as txtrisk_name ,"
			, " (Select TOP 1 acct_flag from fri_treaty_shares as fts "
			, " where fts.policy_no=Pm.policy_no "
			, " and fts.endorse_no=Pm.endorse_no "
			, " and fts.addr_no=Pd.addr_no "
			, " and fts.risk_no=Pd.risk_no "
			, "and fts.acct_flag='Y' ) as txtacct_flag "
			, " from fri_policy as Pm, fri_policy_dtl as Pd "
			, " where 1=1 "
			, " <if test=\"policy_dprtS !=null and policy_dprtS !=''\"> and (( Pm.policy_dprt &gt;= #{policy_dprtS,jdbcType=VARCHAR}) </if>"
			, " <if test=\"policy_dprtE !=null and policy_dprtE !=''\"> and (Pm.policy_dprt &lt;= #{policy_dprtE,jdbcType=VARCHAR} )  </if> "
			, " <if test=\"policy_dprtS !=null and policy_dprtS !=''\"> or ( Pm.policy_dend &gt;=  #{policy_dprtS,jdbcType=VARCHAR})) </if>"
			, " and Pm.policy_no = Pd.policy_no and Pm.endorse_no = Pd.endorse_no "
			, " and Pd.area_code =  #{txtareaCode,jdbcType=VARCHAR}"
			, " and Pm.calc_flag = 'Y' and Pd.risk_flag='Y' "
			, " group by Pm.policy_no, Pm.endorse_no, Pd.prop_addr, Pd.risk_no, Pm.cinsurant , Pd.addr_no ,Pd.useprop_code,Pd.area_code "
			, " union"
			, " select Pd.risk_no , Pd.risk_flag  , "
			, " Pm.change_flag, Pm.policy_no  , Pm.endorse_no , Pm.cinsurant , Pd.addr_no, Pd.prop_addr ,Pd.useprop_code, Pd.useprop_name, Pd.amt_typ, amt_ear,"
			, " prem_typ, prem_ear, Pm.policy_dbgn, Pm.policy_dend, Pd.amt, Pd.prem, Pd.limit_no, Pd.limit, Pd.zip_code, Pd.area_code, ltrim(Pd.risk_name) ,"
			, " (Select TOP 1 acct_flag "
			, " from fri_treaty_shares as fts "
			, " where fts.policy_no=Pm.policy_no and fts.endorse_no=Pm.endorse_no and fts.addr_no=Pd.addr_no and fts.risk_no=Pd.risk_no and fts.acct_flag='Y' ) as acct_flag "
			, " from fri_policy as Pm, fri_policy_dtl as Pd "
			, " where 1=1 "
			, " <if test=\"policy_dprtS !=null and policy_dprtS !=''\"> and  Pm.policy_dprt &gt;= #{policy_dprtS,jdbcType=VARCHAR} </if> "
			, " <if test=\"policy_dprtE !=null and policy_dprtE !=''\"> and  Pm.policy_dprt &lt;= #{policy_dprtE,jdbcType=VARCHAR} </if> "
			, " and Pm.policy_no = Pd.policy_no and Pm.endorse_no = Pd.endorse_no "
			, " and Pd.area_code = #{txtareaCode,jdbcType=VARCHAR} "
			, " and Pm.calc_flag = 'Y' and Pd.risk_flag='N' "
			, " order by txtrisk_flag, Pd.prop_addr, Pd.risk_no, Pm.policy_no, Pm.endorse_no, Pm.cinsurant  desc "
			, "</script>"})
	List<Rin1203AVOResp> queryAreaPolicy(@Param("policy_dprtS") String policy_dprtS,@Param("policy_dprtE") String policy_dprtE, @Param("txtareaCode") String txtareaCode);
//此註解為上列方法SQL的格式
//	@Select("<script>"
//			+ "select Pd.risk_no as txtrisk_no, 'Y' as txtrisk_flag, max(Pm.change_flag) as change_flag, Pm.policy_no as txtpolicy_no, Pm.endorse_no as txtendorse_no, "
//			+ "Pm.cinsurant as txtcinsurant, max(Pd.addr_no) as numaddr_no, Pd.prop_addr as txtprop_addr, Pd.useprop_code as txtuseprop_code,max(Pd.useprop_name) as txtuseprop_name, \n"
//			+ "sum(Pd.amt_typ) as amt_typ, sum(Pd.amt_ear) as amt_ear, sum(Pd.prem_typ) as prem_typ, \n"
//			+ "sum(Pd.prem_ear) as prem_ear, max(Pm.policy_dbgn) as policy_dbgn, max(Pm.policy_dend) as policy_dend, \n"
//			+ "sum(Pd.amt) as numamt, sum(Pd.prem) as prem, max(Pd.limit_no) as limit_no, max(Pd.limit) as limit, \n"
//			+ "max(Pd.zip_code) as zip_code, max(Pd.area_code) as txtarea_code, max(Pd.risk_name) as txtrisk_name \n"
//			+ ", (Select TOP 1 acct_flag from fri_treaty_shares as fts \n" 
//			+ "where fts.policy_no=Pm.policy_no \n"
//			+ "and fts.endorse_no=Pm.endorse_no \n" + "and fts.addr_no=Pd.addr_no \n" + "and fts.risk_no=Pd.risk_no \n"
//			+ "and fts.acct_flag='Y' ) as txtacct_flag \n" 
//			+ "from fri_policy as Pm, fri_policy_dtl as Pd \n"
//			+ "where 1=1 \n"
//			+ "<if test=\"policy_dprtS !=null and policy_dprtS !=''\"> and (( Pm.policy_dprt &gt;= #{policy_dprtS,jdbcType=VARCHAR}) </if>  "
//			+ "<if test=\"policy_dprtE !=null and policy_dprtE !=''\"> and (Pm.policy_dprt &lt;= #{policy_dprtE,jdbcType=VARCHAR} )</if>   \n"
//			+ " <if test=\"policy_dprtS !=null and policy_dprtS !=''\"> or ( Pm.policy_dend &gt;=  #{policy_dprtS,jdbcType=VARCHAR})) </if> \n"
//			+ "and Pm.policy_no = Pd.policy_no and Pm.endorse_no = Pd.endorse_no \n"
//			+ "and Pd.area_code =  #{txtareaCode,jdbcType=VARCHAR} \n"
//			+ "and Pm.calc_flag = 'Y' and Pd.risk_flag='Y'  \n"
//			+ "group by Pm.policy_no, Pm.endorse_no, Pd.prop_addr, Pd.risk_no, Pm.cinsurant , Pd.addr_no, Pd.useprop_code  \n"
//	
//
//		    + "union \n"
//		    +" select Pd.risk_no , Pd.risk_flag  , Pm.change_flag, Pm.policy_no  , Pm.endorse_no , Pd.useprop_code,\n"
//		    + " Pm.cinsurant , Pd.addr_no, Pd.prop_addr , Pd.useprop_name, Pd.amt_typ, amt_ear, prem_typ, prem_ear, \n"
//		    + "Pm.policy_dbgn, Pm.policy_dend, Pd.amt, Pd.prem, Pd.limit_no, Pd.limit, Pd.zip_code, Pd.area_code, ltrim(Pd.risk_name) \n"
//			+ ", (Select TOP 1 acct_flag from fri_treaty_shares as fts \n" 
//		    + "where fts.policy_no=Pm.policy_no \n"
//			+ "and fts.endorse_no=Pm.endorse_no \n" + "and fts.addr_no=Pd.addr_no \n" + "and fts.risk_no=Pd.risk_no \n"
//			+ "and fts.acct_flag='Y' ) as acct_flag \n" 
//			+ "from fri_policy as Pm, fri_policy_dtl as Pd \n"
//			+ "where 1=1 \n"
//			+ "<if test=\"policy_dprtS !=null and policy_dprtS !=''\"> and  Pm.policy_dprt &gt;= #{policy_dprtS,jdbcType=VARCHAR} </if> "
//			+ "<if test=\"policy_dprtE !=null and policy_dprtE !=''\"> and  Pm.policy_dprt &lt;= #{policy_dprtE,jdbcType=VARCHAR} </if> \n"
//			+ "and Pm.policy_no = Pd.policy_no and Pm.endorse_no = Pd.endorse_no\n"
//			+ "and Pd.area_code = #{txtareaCode,jdbcType=VARCHAR}\n" + "and Pm.calc_flag = 'Y' and Pd.risk_flag='N' \n"
//			+ "order by txtrisk_flag, Pd.prop_addr, Pd.risk_no, Pm.policy_no, Pm.endorse_no, Pm.cinsurant  desc \n" + "</script>")
//	List<Rin1203AVOResp> queryAreaPolicy(@Param("policy_dprtS") String policy_dprtS,@Param("policy_dprtE") String policy_dprtE, @Param("txtareaCode") String txtareaCode);

	/**
	 * 當輸入同險=99999999999 時, 清除該起始日後所有同險分保資料
	 * 
	 * @param ucRocDbgn
	 * @param treatyYear
	 * @return
	 */
	@Delete("<script> " + "delete fri_treaty_shares " + "from  (select * from fri_treaty_shares "
			+ "where policy_dprt &gt;= #{ucRocDbgn} " + "and treaty_year = #{treatyYear} "
			+ "and acct_flag != 'Y') as t1, fri_treaty_shares " + "where t1.policy_no = fri_treaty_shares.policy_no "
			+ "and t1.endorse_no = fri_treaty_shares.endorse_no " + "and t1.addr_no = fri_treaty_shares.addr_no "
			+ "and t1.treaty_year = fri_treaty_shares.treaty_year " + "and t1.treaty_no = fri_treaty_shares.treaty_no "
			+ "</script>")
	int deleteOldReinsData999(@Param("ucRocDbgn") String ucRocDbgn, @Param("treatyYear") String treatyYear);

	/**
	 * 當指定同險時, 查詢該同險在起始日後所有分保資料
	 * 
	 * @param ucRocDbgn
	 * @param riskNo
	 * @return
	 */
	@Select("<script> Select B.policy_no policyno, B.endorse_no endorseno, B.addr_no addrno from fri_policy_dtl B inner join fri_policy A On B.policy_no = A.policy_no And B.endorse_no = A.endorse_no where A.policy_dprt &gt;= #{ucRocDbgn} " + "and B.risk_no = #{riskNo} " + "</script>")
	List<FriPolicyDtl> queryReinsBeDeleteData(@Param("ucRocDbgn") String ucRocDbgn, @Param("riskNo") String riskNo);
//此註解為上列方法SQL的格式
//	@Select("<script> " + "Select B.policy_no policyno, B.endorse_no endorseno, B.addr_no addrno "
//			+ "from fri_policy_dtl B "
//			+ "inner join fri_policy A On B.policy_no = A.policy_no And B.endorse_no = A.endorse_no "
//			+ "where A.policy_dprt &gt;= #{ucRocDbgn} " + "and left(B.risk_no,10) = #{riskNo} " + "</script>")
//	List<FriPolicyDtl> queryReinsBeDeleteData(@Param("ucRocDbgn") String ucRocDbgn, @Param("riskNo") String riskNo);

	/**
	 * 當指定同險時, 僅清除該同險在起始日後所有分保資料
	 * 
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @return
	 */
	@Delete("<script> Delete from fri_treaty_shares  where policy_no = #{policyNo}  and endorse_no = #{endorseNo}  and addr_no = #{addrNo}  and acct_flag != 'Y' </script>")
	int deleteOldReinsDataSpe(@Param("policyNo") String policyNo, @Param("endorseNo") String endorseNo,
			@Param("addrNo") Short addrNo);
//此註解為上列方法SQL的格式
//	@Delete("<script> " + "Delete from fri_treaty_shares " + " where policy_no = #{policyNo} "
//			+ " and endorse_no = #{endorseNo} " + " and addr_no = #{addrNo} " + " and acct_flag != 'Y' " + "</script>")
//	int deleteOldReinsDataSpe(@Param("policyNo") String policyNo, @Param("endorseNo") String endorseNo,
//			@Param("addrNo") Short addrNo);

	/**
	 * 讀取合約分保順序
	 * 
	 * @param treatyYear
	 * @return
	 */
	@Select("<script> select A.policy_type policytype, B.treaty_year treatyyear, B.treaty_no treatyno, B.acct_type accttype, B.treaty_mode treatymode, B.limit_base limitbase, B.share_type sharetype, B.share_rate sharerate, B.limit_general limitgeneral, B.limit_total limittotal, B.retain_times retaintimes, B.ref_treaty_no reftreatyno, B.coins_rate coinsrate, A.share_order shareorder from fri_treaty_share_order A inner join fri_treaty B on A.treaty_year = #{treatyYear} and A.treaty_year = B.treaty_year and A.treaty_no = B.treaty_no order by A.policy_type, A.share_order </script>")
	List<Rin1204VOResp1> queryTreatyShareOrder(@Param("treatyYear") String treatyYear);
//此註解為上列方法SQL的格式
//	@Select("<script> "
//			+ "select A.policy_type policytype, B.treaty_year treatyyear, B.treaty_no treatyno, B.acct_type accttype, B.treaty_mode treatymode, "
//			+ "B.limit_base limitbase, B.share_type sharetype, B.share_rate sharerate, B.limit_general limitgeneral, B.limit_total limittotal, "
//			+ "B.retain_times retaintimes, B.ref_treaty_no reftreatyno, B.coins_rate coinsrate, A.share_order shareorder "
//			+ "from fri_treaty_share_order A "
//			+ "inner join fri_treaty B on A.treaty_year = #{treatyYear} and A.treaty_year = B.treaty_year and A.treaty_no = B.treaty_no "
//			+ "order by A.policy_type, A.share_order" + "</script>")
//	List<Rin1204VOResp1> queryTreatyShareOrder(@Param("treatyYear") String treatyYear);

	/**
	 * 讀取待分保明細清單
	 * 
	 * @param ucRocDbgn
	 * @param ucRocDend
	 * @param riskNo
	 * @return
	 */
	@Select("<script> SELECT A.policy_dprt policydprt, A.policy_no policyno, A.endorse_no endorseno, A.policy_type policytype, A.all_amt allamt, A.amt, A.coins_rate coinsrate, A.change_flag changeflag, A.ifloat, A.old_policy oldpolicy, B.policy_dbgn policydbgn, B.policy_dend policydend,  B.addr_no addrno, B.addr_no_ori addrnoori, b.risk_no as riskno, B.amt AS damt, B.prem, B.prop_addr propaddr, B.zip_code zipcode, B.area_code areacode, B.useprop_code usepropcode, B.useprop_name usepropname, B.const_class constclass, B.limit_no limitno, B.limit, B.amt_flt amtflt, B.prem_flt premflt, B.amt_fix amtfix, B.prem_fix premfix, B.amt_typ amttyp, B.prem_typ premtyp, B.amt_ear amtear, B.prem_ear premear, B.risk_flag riskflag, B.risk_name riskname, B.limit_rate limitrate, B.limit_ori limitori, B.end_reason endreason, A.end_reason as endreasonmain, A.Calc_flag Calcflag FROM fri_policy A, fri_policy_dtl B WHERE A.policy_dprt between #{ucRocDbgn} and #{ucRocDend} AND A.calc_flag = 'Y' and A.policy_year &lt;= 1  <if test=' riskNo !=  \"99999999999\" '> and B.risk_no = #{riskNo} </if> AND isnull(A.temp_flag, 'N') != 'Y' AND B.risk_flag = 'Y' AND A.policy_no = B.policy_no AND A.endorse_no = B.endorse_no ORDER BY A.policy_dprt, B.risk_no, B.policy_dbgn, A.policy_no, A.endorse_no, B.addr_no, B.amt desc </script>")
	List<Rin1204VOResp2> queryShareDetailList(@Param("ucRocDbgn") String ucRocDbgn,
			@Param("ucRocDend") String ucRocDend, @Param("riskNo") String riskNo);
//此註解為上列方法SQL的格式
//	@Select("<script> "
//			+ "SELECT A.policy_dprt policydprt, A.policy_no policyno, A.endorse_no endorseno, A.policy_type policytype, A.all_amt allamt, A.amt, A.coins_rate coinsrate, "
//			+ "A.change_flag changeflag, A.ifloat, A.old_policy oldpolicy, B.policy_dbgn policydbgn, B.policy_dend policydend,  B.addr_no addrno, B.addr_no_ori addrnoori, "
//			+ "left(b.risk_no,10) as riskno, B.amt AS damt, B.prem, B.prop_addr propaddr, B.zip_code zipcode, B.area_code areacode, "
//			+ "B.useprop_code usepropcode, B.useprop_name usepropname, B.const_class constclass, B.limit_no limitno, B.limit, B.amt_flt amtflt, B.prem_flt premflt, "
//			+ "B.amt_fix amtfix, B.prem_fix premfix, B.amt_typ amttyp, B.prem_typ premtyp, B.amt_ear amtear, B.prem_ear premear, B.risk_flag riskflag, B.risk_name riskname, "
//			+ "B.limit_rate limitrate, B.limit_ori limitori, B.end_reason endreason, A.end_reason as endreasonmain, A.Calc_flag Calcflag "
//			+ "FROM fri_policy A, fri_policy_dtl B " + "WHERE A.policy_dprt between #{ucRocDbgn} and #{ucRocDend} "
//			+ "AND A.calc_flag = 'Y' and A.policy_year &lt;= 1  " + "<if test=' riskNo !=  \"99999999999\" '> "
//			+ "and left(B.risk_no,10) = #{riskNo} " + " </if> "
//			+ "AND isnull(A.temp_flag, 'N') != 'Y' AND B.risk_flag = 'Y' "
//			+ "AND A.policy_no = B.policy_no AND A.endorse_no = B.endorse_no "
//			+ "ORDER BY A.policy_dprt, B.risk_no, B.policy_dbgn, A.policy_no, A.endorse_no, B.addr_no, B.amt desc"
//			+ "</script>")
//	List<Rin1204VOResp2> queryShareDetailList(@Param("ucRocDbgn") String ucRocDbgn,
//			@Param("ucRocDend") String ucRocDend, @Param("riskNo") String riskNo);

	/**
	 * 查詢同險未處理保單(同險代號!="99999999999")
	 * 
	 * @param ucRocDbgn
	 * @param ucRocDend
	 * @param riskNo
	 * @return
	 */
	@Select("<script> select Pd.policy_no policyno, Pd.endorse_no endorseno, Pd.addr_no addrno, Pd.prop_addr propaddr, Pd.zip_code zipcode, Pd.area_code areacode from fri_policy as Pm, fri_policy_dtl as Pd where Pm.policy_dprt between #{ucRocDbgn} and #{ucRocDend} and Pm.policy_no = Pd.policy_no and Pm.endorse_no = Pd.endorse_no and Pd.risk_flag = 'N' and pm.calc_flag = 'Y' and Pd.risk_no = #{riskNo} Order by pd.area_code, pd.policy_no, pd.endorse_no, pd.addr_no </script>")
	List<Rin1204VOResp> queryUnProcPolicy1(@Param("ucRocDbgn") String ucRocDbgn, @Param("ucRocDend") String ucRocDend,
			@Param("riskNo") String riskNo);
//此註解為上列方法SQL的格式	
//	@Select("<script> "
//			+ "select Pd.policy_no policyno, Pd.endorse_no endorseno, Pd.addr_no addrno, Pd.prop_addr propaddr, Pd.zip_code zipcode, Pd.area_code areacode "
//			+ "from fri_policy as Pm, fri_policy_dtl as Pd "
//			+ "where Pm.policy_dprt between #{ucRocDbgn} and #{ucRocDend} "
//			+ "and Pm.policy_no = Pd.policy_no and Pm.endorse_no = Pd.endorse_no "
//			+ "and Pd.risk_flag = 'N' and pm.calc_flag = 'Y' " + "and Pd.risk_no = #{riskNo} "
//			+ "Order by pd.area_code, pd.policy_no, pd.endorse_no, pd.addr_no " + "</script>")
//	List<Rin1204VOResp> queryUnProcPolicy1(@Param("ucRocDbgn") String ucRocDbgn, @Param("ucRocDend") String ucRocDend,
//			@Param("riskNo") String riskNo);

	/**
	 * 查詢同險未處理保單(同險代號="99999999999")
	 * 
	 * @param ucRocDbgn
	 * @param ucRocDend
	 * @return
	 */
	@Select("<script> select Pd.policy_no policyno, Pd.endorse_no endorseno, Pd.addr_no addrno, Pd.prop_addr propaddr, Pd.zip_code zipcode, Pd.area_code areacode from fri_policy as Pm, fri_policy_dtl as Pd where Pm.policy_dprt between #{ucRocDbgn} and #{ucRocDend} and Pm.policy_no = Pd.policy_no and Pm.endorse_no = Pd.endorse_no and Pd.risk_flag = 'N' and pm.calc_flag = 'Y' Order by pd.area_code, pd.policy_no, pd.endorse_no, pd.addr_no </script>")
	List<Rin1204VOResp> queryUnProcPolicy2(@Param("ucRocDbgn") String ucRocDbgn, @Param("ucRocDend") String ucRocDend);
//此註解為上列方法SQL的格式	
//	@Select("<script> "
//			+ "select Pd.policy_no policyno, Pd.endorse_no endorseno, Pd.addr_no addrno, Pd.prop_addr propaddr, Pd.zip_code zipcode, Pd.area_code areacode "
//			+ "from fri_policy as Pm, fri_policy_dtl as Pd "
//			+ "where Pm.policy_dprt between #{ucRocDbgn} and #{ucRocDend} "
//			+ "and Pm.policy_no = Pd.policy_no and Pm.endorse_no = Pd.endorse_no "
//			+ "and Pd.risk_flag = 'N' and pm.calc_flag = 'Y' "
//			+ "Order by pd.area_code, pd.policy_no, pd.endorse_no, pd.addr_no " + "</script>")
//	List<Rin1204VOResp> queryUnProcPolicy2(@Param("ucRocDbgn") String ucRocDbgn, @Param("ucRocDend") String ucRocDend);
	/**
	 * 查詢該保單之同險是否有續單保單
	 * 
	 * @param riskNo
	 * @param policyDend
	 * @param policyDbgn
	 * @param policyDprt
	 * @param policyNo
	 * @return
	 */
	@Select("<script> SELECT distinct Old_Policy OldPolicy FROM fri_PolicyJoinDtl WHERE Old_Policy != '' and Old_policy !='0' AND risk_no = #{riskNo} AND policy_dbgn &lt; #{policyDend} and policy_dend &gt; #{policyDbgn} AND policy_dprt &gt; '2003/12/31' and policy_dprt &lt;= #{policyDprt} AND old_policy != #{policyNo} </script>")
	List<Rin1204VOResp3> queryOldPolicyList(@Param("riskNo") String riskNo, @Param("policyDend") String policyDend,
			@Param("policyDbgn") String policyDbgn, @Param("policyDprt") String policyDprt,
			@Param("policyNo") String policyNo);
//此註解為上列方法SQL的格式	
//	@Select("<script> " + "SELECT distinct Old_Policy OldPolicy " + "FROM fri_PolicyJoinDtl "
//			+ "WHERE Old_Policy != '' and Old_policy !='0' " + "AND risk_no = #{riskNo} "
//			+ "AND policy_dbgn &lt; #{policyDend} and policy_dend &gt; #{policyDbgn} "
//			+ "AND policy_dprt &gt; '2003/12/31' and policy_dprt &lt;= #{policyDprt} "
//			+ "AND old_policy != #{policyNo} " + "</script>")
//	List<Rin1204VOResp3> queryOldPolicyList(@Param("riskNo") String riskNo, @Param("policyDend") String policyDend,
//			@Param("policyDbgn") String policyDbgn, @Param("policyDprt") String policyDprt,
//			@Param("policyNo") String policyNo);

	/**
	 * Rin1303-用更正號查詢臨分再保人
	 * 
	 * @param slip_no
	 * @return
	 * @author yiting 2021/11/29
	 */
	@Select({"<script> ",
			 " SELECT A.slip_no, A.rin_com_id as txtrin_com_id, a.acct_flag as txtacct_flag, a.transfer_status as txtTurn_flag, a.bill_no_external as txtbill_no_external, B.ename as txtename,",
			 " ISNULL(( select top 1 bill_no from fri_accounting where bill_no = A.slip_no and Broker_id = A.rin_com_id ),'') as txtbill_no, ",
			 " ISNULL(( select top 1 turn_flag from fri_accounting where bill_no = A.slip_no and Broker_id = A.rin_com_id ",
			 " and NOT(close_date is null) and (close_date != '1900/01/01') ",
			 " and amend_seq = (select MAX(amend_seq) from fri_accounting where bill_no = A.slip_no )),'') as turn_flag ",
			 " FROM fri_fac_rincom A INNER JOIN fri_com B ON A.rin_com_id = B.rin_com_id ",
			 " WHERE A.slip_no = #{slipNo,jdbcType=VARCHAR} AND A.share_status = '2' ",
			 "</script>"})
	List<Rin1303QueryRinComBySlipNoVOResp> queryRinComBySlipNo(@Param("slipNo") String slipNo);

	/**
	 * 查詢該批單(不慮保單)是否有新續保單
	 * 
	 * @param policyNo
	 * @param policyDEND
	 * @return
	 */
	@Select({"<script> ", 
	         "select distinct A.policy_no policyno from fri_policy A inner join fri_policy_dtl B ",
			 "on A.policy_no=B.policy_no and A.endorse_no = B.endorse_no ",
			 "Where A.Old_policy= #{policyNo} and B.policy_dbgn= #{policyDEND} " ,
			 "</script>"})
	List<Rin1204VOResp4> queryMoNextPolicy(@Param("policyNo") String policyNo, @Param("policyDEND") String policyDEND);

	/**
	 * 查詢該保單期間之同險分保總額
	 * 
	 * @param treatyYear
	 * @param riskNo
	 * @param policyDEND
	 * @param policyDBGN
	 * @param policyPRT
	 * @param oldPolicyList
	 * @param oldPolicy
	 * @return
	 */
	@Select({"<script>" ,
	         "select SUM(share_amt) as shareamt from fri_treaty_shares ",
			 "where treaty_year &lt;= #{treatyYear} " ,
	         "and treaty_no != 'FAC_Retain' and treaty_no != 'SEC_Retain' ",
			 "and risk_no = #{riskNo} " ,
	         "and duty_bgn &lt; #{policyDEND} " ,
			 "and duty_end &gt; #{policyDBGN} ",
			 "and policy_dprt &lt;= #{policyPRT} " ,
			 "<foreach item='item' index='index' collection='oldPolicyList' ",
			 "open='and Not(' separator=' Or ' close=')'> " ,
			 "(Policy_no = #{item}) " ,
		     "</foreach> ",
			 "<if test='oldPolicy != null and oldPolicy != \"\"'> ",
			 "and policy_no + endorse_no != #{oldPolicy} ",
			 "</if> " ,
			 "</script>"})
	List<Rin1204VOResp5> queryMoShareAmt(@Param("treatyYear") String treatyYear, @Param("riskNo") String riskNo,
			@Param("policyDEND") String policyDEND, @Param("policyDBGN") String policyDBGN,
			@Param("policyPRT") String policyPRT, @Param("oldPolicyList") List<String> oldPolicyList,
			@Param("oldPolicy") String oldPolicy);

	/**
	 * 讀取臨分分保資料 Sum amt, prem of FAC share
	 * 
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @return
	 */
	@Select({"<script>",
			 "select SUM(A.amt) FACAmt, SUM(A.prem) FACPrem, SUM(A.amt_typ) FACAmtTyp, SUM(A.prem_typ) FACPremTyp, ",
			 "SUM(A.amt_ear) FACAmtEar, SUM(A.prem_ear) FACPremEar ",
			 "from fri_fac_shares A, fri_fac B ",
			 "where A.policy_no = #{policyNo} ",
			 "and A.endorse_no = #{endorseNo} " ,
			 "and A.addr_no = #{addrNo} ",
			 "and A.slip_no = B.slip_no " ,
			 "and (B.acct_flag = 'Y' or IsNull(B.TreatySet,'') = 'Y' ) " ,
			 "</script>"})
	List<Rin1204VOResp6> queryFACShare(@Param("policyNo") String policyNo, @Param("endorseNo") String endorseNo,
			@Param("addrNo") Short addrNo);

	/**
	 * 計算臨分自留(FAC_Retain) Sum amt, prem of FAC_Retail share
	 * 
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @return
	 */
	@Select({"<script>",
			 "select DISTINCT B.slip_no slipno, B.excess_bgn excessbgn, B.excess_limit excesslimit from fri_fac_shares A, fri_fac B ",
			 "where A.policy_no = #{policyNo} " ,
			 "and A.endorse_no = #{endorseNo} " ,
			 "and A.addr_no = #{addrNo} ",
			 "and A.slip_no = B.slip_no " ,
			 "and B.fac_type='2' ", // 臨分類型(1.一般2.超賠)
			 "and (B.acct_flag = 'Y' or IsNull(B.TreatySet,'') = 'Y' ) ",
			 "</script>"})
	List<FriFac> queryFACRetain(@Param("policyNo") String policyNo, @Param("endorseNo") String endorseNo,
			@Param("addrNo") Short addrNo);

	/**
	 * 查詢"臨分標的物檔"保額加總
	 * 
	 * @param exSlip
	 * @return
	 */
	@Select({"<script>",
	         "select sum(isnull(A.amt,0)) as ExAmt from fri_fac_nprop A, fri_fac B ",
			 "where A.slip_no = #{exSlip} ",
			 "and A.slip_no=B.slip_no and (B.acct_flag = 'Y' or IsNull(B.TreatySet,'') = 'Y' ) " ,
			 "</script>"})
	List<Rin1204VOResp7> queryExAmt(@Param("exSlip") String exSlip);

	/**
	 * 查詢"臨分再保人檔"分出比率 (百分比) % 加總
	 * 
	 * @param exSlip
	 * @return
	 */
	@Select({"<script>" ,
	         "select sum(A.share_rate) as ExShareRate from fri_fac_rincom A, fri_fac B ",
			 "where A.slip_no = #{exSlip} " ,
	         "and A.slip_no=B.slip_no and A.share_status='2' ", // 分保狀況(1.分保協商中2.分保完成)
			 "and (B.acct_flag = 'Y' or IsNull(B.TreatySet,'') = 'Y' ) " ,
	         "</script>"})
	List<Rin1204VOResp8> queryExShare(@Param("exSlip") String exSlip);

	/**
	 * 查詢合約分保檔
	 * 
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @param treatyYear
	 * @param riskNo
	 * @return
	 */
	@Select({"<script>" ,
	         "select policy_no policyno from fri_treaty_shares " ,
			 "where policy_no = #{policyNo} ",
			 "and endorse_no = #{endorseNo} " ,
			 "and addr_no = #{addrNo} " ,
			 "and treaty_year = #{treatyYear} ",
			 "and treaty_no = 'FAC_Retain' " ,
			 "and risk_no = #{riskNo} " ,
			 "</script>"})
	List<FriTreatyShares> queryExShare2(@Param("policyNo") String policyNo, @Param("endorseNo") String endorseNo,
			@Param("addrNo") Short addrNo, @Param("treatyYear") String treatyYear, @Param("riskNo") String riskNo);

	/**
	 * update 合約分保檔
	 * 
	 * @param rin1204voReq1
	 * @return
	 */
	@Update({"<script>" ,
	         "update fri_treaty_shares set ",
			 "amt_tsi = amt_tsi + #{record.msShareAmt} + #{record.msPlyShareAmt} , ",
			 "amt = amt + #{record.msDamt} , " ,
			 "prem = prem + #{record.msDprem} , ",
			 "amt_typ = amt_typ + #{record.msDamtTyp} , " ,
			 "prem_typ = prem_typ + #{record.msDpremTyp} , ",
			 "amt_ear = amt_ear + #{record.msDamtEar} , " ,
			 "prem_ear = prem_ear + #{record.msDpremEar} , ",
			 "share_amt = share_amt + #{record.retainAmt} , " ,
			 "share_prem = share_prem + #{record.retainPrem} ",
			 "where policy_no = #{record.msDpolicyNo} " ,
			 "and endorse_no = #{record.msDendorseNo} ",
			 "and addr_no = #{record.msDaddrNo} " ,
			 "and treaty_year = #{record.treatyYear} ",
			 "and treaty_no = 'FAC_Retain' " ,
			 "and risk_no = #{record.msDriskNo} " ,
			 "</script>"})
	int updateFriTreatyShares(@Param("record") Rin1204VOReq1 rin1204voReq1);

	/**
	 * insert 合約分保檔
	 * 
	 * @param rin1204voReq2
	 * @return
	 */
	@Insert({"<script>",
			 "insert into fri_treaty_shares (policy_no, endorse_no, addr_no, treaty_year, treaty_no, risk_no, duty_bgn, duty_end, acct_type, treaty_type, ",
			 "amt_tsi, amt, prem, amt_typ, prem_typ, amt_ear, prem_ear, share_amt, share_prem, share_amt_typ, share_prem_typ, ",
			 "share_amt_ear, share_prem_ear, remark, treaty_mode, calc_date, calc_user, acct_flag, iendorsement2, policy_dprt) values( ",
			 "#{record.msDpolicyNo}, #{record.msDendorseNo}, #{record.msDaddrNo}, #{record.msTreatyYear}, 'FAC_Retain', ",
			 "#{record.msDriskNo}, #{record.msPolicyDBGN}, #{record.msPolicyDEND}, '0', '2', #{record.msShareAmt} + #{record.msPlyShareAmt}, ",
			 "#{record.msDamt}, #{record.msDprem}, #{record.msDamtTyp}, #{record.msDpremTyp}, #{record.msDamtEar}, #{record.msDpremEar}, ",
			 "#{record.retainAmt}, #{record.retainPrem}, 0, 0, 0, 0, ' ', '0', #{record.now}, ' ', 'N', ' ', #{record.msPolicyPRT} )",
			 "</script>"})
	int insertFriTreatyShares(@Param("record") Rin1204VOReq2 rin1204voReq2);

	/**
	 * Query PolDtl
	 * 
	 * @param policyNo
	 * @param addrNoOri
	 * @return
	 */
	@Select({"<script>" ,
	         "Select Policy_no Policyno , endorse_no endorseno, addr_no addrno From fri_PolicyJoinDtl ",
			 "Where policy_no = #{policyNo} " ,
	         "And addr_no_ori = #{addrNoOri} ",
			 "Order by policy_dprt, endorse_no asc ", // 找最早一筆 (保額應為正項)
	         "</script>"})
	List<Rin1204VOResp> queryMoTemp(@Param("policyNo") String policyNo, @Param("addrNoOri") Short addrNoOri);

	/**
	 * Query Policy Shares
	 * 
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @return
	 */
	@Select({"<script>",
			 "select policy_no policyno, endorse_no endorseno, addr_no addrno, treaty_year treatyyear, treaty_no treatyno, ",
			 "risk_no riskno, duty_bgn dutybgn, duty_end dutyend, acct_type accttype, treaty_type treatytype, amt_tsi amttsi, ",
			 "amt, prem, amt_typ amttyp, prem_typ premtyp, amt_ear amtear, prem_ear premear, share_amt shareamt, share_prem shareprem,",
			 "share_amt_typ shareamttyp, share_prem_typ sharepremtyp, share_amt_ear shareamtear, share_prem_ear sharepremear, remark, ",
			 "treaty_mode treatymode, calc_date calcdate, calc_user calcuser, acct_flag acctflag, iendorsement2 from fri_treaty_shares ",
			 "Where policy_no = #{policyNo} " ,
			 "And endorse_no = #{endorseNo} ",
			 "And addr_no = #{addrNo} ",
			 "And treaty_type = '1' " ,
			 "Order by treaty_mode Desc " ,
			 "</script>"})
	List<FriTreatyShares> queryMoCancel(@Param("policyNo") String policyNo, @Param("endorseNo") String endorseNo,
			@Param("addrNo") String addrNo);

	/**
	 * insert 合約分保檔2
	 * @param rin1204voReq2
	 * @return
	 */
	@Insert({"<script>",
			 "insert into fri_treaty_shares (policy_no, endorse_no, addr_no, treaty_year, treaty_no, risk_no, duty_bgn, duty_end, acct_type, treaty_type, ",
			 "amt_tsi, amt, prem, amt_typ, prem_typ, amt_ear, prem_ear, share_amt, share_prem, share_amt_typ, share_prem_typ, ",
			 "share_amt_ear, share_prem_ear, remark, treaty_mode, calc_date, calc_user, acct_flag, iendorsement2, policy_dprt) values( ",
			 "#{record.msDpolicyNo}, #{record.msDendorseNo}, #{record.msDaddrNo}, #{record.msTreatyYear}, #{record.msCtreatyNo}, ",
			 "#{record.msDriskNo}, #{record.msPolicyDBGN}, #{record.msPolicyDEND}, #{record.msCacctType}, '1', #{record.msCamtTsi}, ",
			 "#{record.msDamt}, #{record.msDprem}, #{record.msDamtTyp}, #{record.msDpremTyp}, #{record.msDamtEar}, #{record.msDpremEar}, ",
			 "0, #{record.msSharePrem}, 0, 0, 0, 0, ' ', #{record.msCtreatyMode}, #{record.now}, ' ', 'N', ' ', #{record.msPolicyPRT} )",
			 "</script>"})
	int insertFriTreatyShares2(@Param("record") Rin1204VOReq2 rin1204voReq2);
	
	/**
	 * Rin1303-查詢報表「臨分主檔」資料
	 * @param cessionNo
	 * @param slipNo
	 * @param rinComId
	 * @return list Rin1303QueryMain
	 * @author yiting 	2021/12/06
	 */
	@Select("<script>SELECT A.treaty_date as treatyDate, A.treaty_year as treatyYear, " + 
			"A.policy_no_seq as policyNoSeq, "
			+ "CONVERT(varchar, A.treaty_dbgn, 111) as treatyDbgn, "
			+ "CONVERT(varchar, A.treaty_dend, 111) as treatyDend, " + 
			"A.days, A.insurant, A.policy_no as policyNo, A.mkovse, " + 
			"B.acct_flag as acctFlag " + 
			"FROM fri_fac A, fri_fac_rincom B " + 
			"WHERE A.cession_no = #{cessionNo} " + 
			"AND A.slip_no = #{slipNo} " + 
			"AND A.slip_no = B.slip_no " + 
			"AND B.rin_com_id = #{rinComId} " + 
			" </script>")
	List<Rin1303QueryMain> queryRin1303PrintMain(@Param("cessionNo") String cessionNo, @Param("slipNo") String slipNo, @Param("rinComId") String rinComId);
	
	/**
	 * Get Treaty Share Order
	 * @param treatyYear
	 * @param policyType
	 * @return
	 */
	@Select({"<script>",
			" select B.limit_base limitbase, B.share_rate sharerate, B.limit_general limitgeneral, B.limit_total limittotal, B.coins_rate coinsrate ",
			" from fri_treaty_share_order A inner join fri_treaty B ", 
			" on A.treaty_year = #{treatyYear} ",
			" and A.treaty_year = B.treaty_year and B.treaty_mode='1' ",
			" and A.treaty_no = B.treaty_no and A.policy_type = #{policyType} ",
			"</script>"})
	List<FriTreaty> querymMoRetain(@Param("treatyYear") String treatyYear, @Param("policyType") String policyType);

	/**
	 * 取得同險限額
	 * @param riskNo
	 * @param treatyYear
	 * @param policyDEND
	 * @param policyDBGN
	 * @param policyPRT
	 * @return
	 */
	@Select({"<script>",
	  		 " select MIN(limit) as minLimit from fri_PolicyJoinDtl ", 
			 " where risk_no = #{riskNo} ",
			 " and Year(policy_dprt) =  #{treatyYear} ", // 只找本年度限額
			// policy_dbgn, policy_dend 不確定是否要加"="，目前先依照舊程式
			 " and policy_dbgn &lt; #{policyDEND} ", 
			 " and policy_dend &gt; #{policyDBGN} ",
			 " and policy_dprt &lt;= #{policyPRT} ",
			 " and limit != 0 ",
			 "</script>"})
	List<Rin1204VOResp9> queryMinLimit(@Param("riskNo") String riskNo, @Param("treatyYear") String treatyYear,
			@Param("policyDEND") String policyDEND, @Param("policyDBGN") String policyDBGN,
			@Param("policyPRT") String policyPRT);

	/**
	 * 計算此同險已擺放保額 TODO duty_bgn 和 duty_end 的等於符號尚未確認
	 * @param treatyYear
	 * @param riskNo
	 * @param policyDEND
	 * @param policyDBGN
	 * @param policyPRT
	 * @param oldPolicyList
	 * @param oldPolicy
	 * @param nextPolicy
	 * @return
	 */
	@Select({"<script>", 
			 "select SUM(share_amt) as shareamt from fri_treaty_shares ",
			 "where treaty_year &lt;= #{treatyYear} ",// 不可大於合約年度!
			 "and treaty_no = 'FFQSQ' ", 
			 "and risk_no = #{riskNo} ", 
			 "and duty_bgn &lt;= #{policyDEND} ",
			 "and duty_end &gt;= #{policyDBGN} ", 
			 "and policy_dprt &lt;= #{policyPRT} ",
			// 期間內之續保件，上一張保單視為到期不列入
			 "<foreach item='item' index='index' collection='oldPolicyList' ",
			 "open='and Not(' separator=' Or ' close=')'> ", 
			 "(Policy_no = #{item}) ", 
			 "</foreach> ",
			// 目前為續保
			 "<if test='oldPolicy != null and oldPolicy != \"\"'> ", 
			 "and policy_no + endorse_no != #{oldPolicy} ",
			 "</if> ",
			// 批單到期日有同一日期起保之續保單時
			 "<if test='nextPolicy != null and nextPolicy != \"\"'> ", 
			 "and policy_no != #{nextPolicy} ",
			 "</if> ",
			 "</script>"})
	List<Rin1204VOResp5> queryMoShareAmt2(@Param("treatyYear") String treatyYear, @Param("riskNo") String riskNo,
			@Param("policyDEND") String policyDEND, @Param("policyDBGN") String policyDBGN,
			@Param("policyPRT") String policyPRT, @Param("oldPolicyList") List<String> oldPolicyList,
			@Param("oldPolicy") String oldPolicy, @Param("nextPolicy") String nextPolicy);
	
	/**
	 * Rin1303-查詢報表「臨分再保人檔」資料
	 * @param slipNo
	 * @param rinComId
	 * @return list Rin1303Query2
	 * @author yiting 	2021/12/06
	 */
	@Select("<script>SELECT A.share_rate as shareRate, A.cede_prem as cedePrem, A.comm_rate as commRate, A.tax_rate as taxRate, " + 
			"A.handling_rate as handlingRate, A.broker_rate as brokerRate, A.discount_rate as discountRate, A.ref_no as refNo, B.ename, " + 
			"CONVERT(varchar, A.paid_date_expect, 111) as paidDateExpect, A.transfer_status as transferStatus, A.orgcomm, A.orgtax, A.orgprem " + 
			"FROM fri_fac_rincom A, fri_com B " + 
			"WHERE A.rin_com_id = B.rin_com_id " + 
			"AND A.slip_no = #{slipNo} " + 
			"AND A.rin_com_id = #{rinComId} " + 
			"</script>")
	List<Rin1303Query2> queryRin1303Print2(@Param("slipNo") String slipNo, @Param("rinComId") String rinComId);

	/**
	 * Rin1206取得合約帳單明細資料
	 * @param printModelVo
	 * @return
	 * @author denny_huang
	 */
	@Select({"<script>" ,
			" SELECT Distinct C.policy_no, C.endorse_no, B.policy_dbgn, B.policy_dend, C.policy_dprt, C.addr_no,C.amt, C.prem, B.limit_no, " ,
			" C.treaty_year, C.treaty_no,   C.share_amt, C.share_prem, C.acct_flag " ,
			" FROM fri_PolicyJoinDtl B, fri_treaty_shares C " , 
			" WHERE B.policy_no = c.policy_no " , 
			" AND B.endorse_no = C.endorse_no " ,
			" AND B.addr_no = C.addr_no	" ,
			" AND C.treaty_type = '1' "  ,
			" and C.policy_dprt between #{model.policyDprtBgn} and #{model.policyDprtEnd} ",
			" and C.treaty_no = #{model.treatyNo}  " , 
			" and C.acct_type = '2' " ,
			"</script>"})
	List<Rin1206PrintModelVO> getContractDetail(@Param("model") Rin1206PrintModelVO printModelVo);
	
	//Rin1206讀取合約再保人資料
	@Select({"<script>" ,
			" SELECT A.rin_com_id, A.rin_com_seq, A.rin_com_share, A.rin_com_tax, B.businesstax_rate, B.handling_rate, " , 
			" B.stamptax_rate, B.agent_rate, B.firrulcom_rate, B.treaty_name, C.ename, B.other_share, B.share_rate, B.share_type " , 
			" FROM fri_treaty_rincom A, fri_treaty B, fri_com C " , 
			" WHERE A.treaty_year = B.treaty_year " , 
			" AND A.treaty_no = B.treaty_no " , 
			" AND A.rin_com_id = C.rin_com_id ",
			" AND A.treaty_year = #{model.treaty_year} ",
			" AND A.treaty_no = #{model.treaty_no} ",
			" AND B.acct_type = #{model.monthPeriod} ",			
			"</script>"})
	List<Rin1206PrintModelVO> CreateRincom(@Param("model") Rin1206PrintModelVO printModelVo);
	
	//Rin1206讀取合約再保人資料
	@Select({"<script>" +
			" SELECT A.rin_com_id, A.rin_com_seq, A.rin_com_share, A.rin_com_contract_no, " , 
			" B.businesstax_rate, B.handling_rate, B.stamptax_rate, B.agent_rate, B.firrulcom_rate " , 
			" FROM fri_treaty_rincom A, fri_treaty B, " , 
			" WHERE A.treaty_year = B.treaty_year " , 
			" AND A.treaty_no = B.treaty_no " , 
			" AND A.treaty_year = #{model.treaty_year} ",
			" AND A.treaty_no = #{model.treaty_no} ",
			" AND B.acct_type = #{model.monthPeriod}  ",			
			"</script>"})
	List<Rin1206PrintModelVO> CreateRincom2(@Param("model") Rin1206PrintModelVO printModelVo);
	

	//Rin1206寫入帳單暫存檔前置查詢-總表
	@Select({"<script>" ,
			" SELECT A.treaty_year, A.treaty_name,B.treaty_no, B.treaty_sname, B.Share_rate, B.firrulcom_rate, A.dprt_month, " , 
			" Sum(A.share_amt) as treaty_amts, Sum(A.share_prem) as treaty_prems, Sum(A.ripaid) as ripaids " , 
			" FROM Rin1206 A, fri_treaty B " , 
			" WHERE A.treaty_year = B.treaty_year " , 
			" and A.treaty_no = B.treaty_no  " ,
			" and A.rptid = #{record.rptid}  ",
			" and B.treaty_no = #{record.treatyNo} "  ,			
			"<if test=\"record.policyMode == '1'.toString() \"> ",
    			" and A.Endorse_no = \'\' ",
    		"</if> ",
    		"<if test=\"record.policyMode == '2'.toString() \"> ",
    			" and A.Endorse_no != ''  ",
    		"</if> ",		
   		
			" Group by A.treaty_year, A.treaty_name,B.treaty_no, B.treaty_sname, B.Share_rate, B.firrulcom_rate, A.dprt_month ",
			"</script>"})
	List<Rin1206QueryDataVO> QueryData1(@Param("record") Rin1206QueryConditionVO record);
	
	//Rin1206寫入帳單暫存檔前置查詢-再保人
	@Select({"<script>" ,
			"SELECT A.treaty_year, A.treaty_no, A.treaty_name, B.treaty_sname, B.other_share, B.firrulcom_rate, A.dprt_month, " , 
			"Sum(A.share_amt) as treaty_amts, Sum(A.share_prem) as treaty_prems, Sum(A.ripaid) as ripaids " ,
			"FROM Rin1206 A, fri_treaty B " , 
			"WHERE A.treaty_year = B.treaty_year   " , 
			"and A.treaty_no = B.treaty_no   " ,
			"and A.rptid = #{record.rptid}   ",
			"<if test=\"record.policyMode == '1'.toString() \"> ",
				"and A.Endorse_no = \'\' ",
			"</if>  ",
			"<if test=\"record.policyMode == '2'.toString() \">  ",
				"and A.Endorse_no != ''  ",
			"</if>  ",		
			"Group by A.treaty_year, A.treaty_name,B.treaty_no, B.treaty_sname, B.Share_rate, B.firrulcom_rate, A.dprt_month ",
			"</script>"})
	List<Rin1206QueryDataVO> QueryData2(@Param("record") Rin1206QueryConditionVO record);
		
	//rin1206 取treaty_mode
	@Select({"<script>",
			" Select top 1 treaty_mode  ",
			" FROM fri_treaty = #{treatyYear} ",
			" where treaty_year = #{treatyNo} ",
			"</script>"})	
	int getTreatyMode(@Param("treatyYear") String treatyYear, @Param("treatyNo")String treatyNo);

	/**
	 * 已出帳資料檢核
	 * @param msDpolicyNo
	 * @param msDendorseNo
	 * @param msDaddrNo
	 * @param treatyYear
	 * @param msOtreatyNo
	 * @return
	 */
	@Select({"<script>",
			 " select top 1 * from fri_treaty_shares " ,
			 " where policy_no = #{msDpolicyNo} ",
			 " and endorse_no = #{msDendorseNo} " ,
			 " and addr_no = #{msDaddrNo} " ,
			 " and treaty_year = #{treatyYear} ",
			 " and treaty_no = #{msOtreatyNo} " ,
			 " and acct_flag='Y' " ,
			 "</script>"})
	List<FriTreatyShares> billedCheck(@Param("msDpolicyNo") String msDpolicyNo,
			@Param("msDendorseNo") String msDendorseNo, @Param("msDaddrNo") Short msDaddrNo,
			@Param("treatyYear") String treatyYear, @Param("msOtreatyNo") String msOtreatyNo);

	/**
	 * Insert Treaty Share --[Retain]
	 * @param rin1204voReq2
	 * @return
	 */
	@Insert({"<script>",
			 "insert into fri_treaty_shares (policy_no, endorse_no, addr_no, treaty_year, treaty_no, risk_no, duty_bgn, duty_end, acct_type, treaty_type, ",
			 "amt_tsi, amt, prem, amt_typ, prem_typ, amt_ear, prem_ear, share_amt, share_prem, share_amt_typ, share_prem_typ, ",
			 "share_amt_ear, share_prem_ear, remark, treaty_mode, calc_date, calc_user, acct_flag, iendorsement2, policy_dprt) values( ",
			 "#{record.msDpolicyNo}, #{record.msDendorseNo}, #{record.msDaddrNo}, #{record.msTreatyYear}, 'Retain', ",
			 "#{record.msDriskNo}, #{record.msPolicyDBGN}, #{record.msPolicyDEND}, '0', '1', #{record.msShareAmt} + #{record.msPlyShareAmt}, ",
			 "#{record.msDamt}, #{record.msDprem}, #{record.msDamtTyp}, #{record.msDpremTyp}, #{record.msDamtEar}, #{record.msDpremEar}, ",
			 "#{record.retainAmt}, #{record.retainPrem}, 0, 0, 0, 0, ' ', '0', #{record.now}, ' ', 'N', ' ', #{record.msPolicyPRT} )",
			 "</script>"})
	int insertFriTreatyShares3(@Param("record") Rin1204VOReq2 rin1204voReq2);

	/**
	 * 計算已擺放保額 (依同險角度)
	 * @param rin1204voReq3
	 * @return
	 */
	@Select({"<script>" ,
			 " select SUM(share_amt) as shareamt from fri_treaty_shares ",
			 " where treaty_year &lt;= #{record.treatyYear} " ,// 於有效期間內皆需計算，無關合約年度!,但不可大於該合約年度
			 " and treaty_no = #{record.treatyNo} " ,
			 " and risk_no = #{record.riskNo} ",
			 " and duty_bgn &lt;= #{record.policyDEND} " ,
			 " and duty_end &gt;= #{record.policyDBGN} ",
			 " and policy_dprt &lt;= #{record.policyPRT} ",
			 "<foreach item='item' index='index' collection='record.oldPolicyList' ",
			 "open='and Not(' separator=' Or ' close=')'> " ,
			 " (Policy_no = #{item}) " ,
			 "</foreach> ",
			// 目前保單為續保
			 "<if test='record.oldPolicy != null and record.oldPolicy != \"\"'> ",
			 " and policy_no + endorse_no != #{record.oldPolicy} ",
			 "</if> ",
			// 批單到期日有同一日期起保之續保單時
			 "<if test='record.nextPolicy != null and record.nextPolicy != \"\"'> ",
			 " and policy_no != #{record.nextPolicy} " ,
			 "</if> " ,
			 "</script>"})
	List<Rin1204VOResp5> queryMoShareAmt3(@Param("record") Rin1204VOReq3 rin1204voReq3);

	/**
	 * 07-11.寫入合約分保檔
	 * @param rin1204voReq2
	 * @return
	 */
	@Insert({"<script>",
			 "insert into fri_treaty_shares (policy_no, endorse_no, addr_no, treaty_year, treaty_no, risk_no, duty_bgn, duty_end, acct_type, treaty_type, ",
			 "amt_tsi, amt, prem, amt_typ, prem_typ, amt_ear, prem_ear, share_amt, share_prem, share_amt_typ, share_prem_typ, ",
			 "share_amt_ear, share_prem_ear, remark, treaty_mode, calc_date, calc_user, acct_flag, iendorsement2, policy_dprt) values( ",
			 "#{record.msDpolicyNo}, #{record.msDendorseNo}, #{record.msDaddrNo}, #{record.msTreatyYear}, #{record.msCtreatyNo}, ",
			 "#{record.msDriskNo}, #{record.msPolicyDBGN}, #{record.msPolicyDEND}, #{record.msCacctType}, '1', #{record.msShareAmt} + #{record.msPlyShareAmt}, ",
			 "#{record.msDamt}, #{record.msDprem}, #{record.msDamtTyp}, #{record.msDpremTyp}, #{record.msDamtEar}, #{record.msDpremEar}, ",
			 "#{record.retainAmt}, #{record.retainPrem}, 0, 0, 0, 0, ' ', #{record.msCtreatyMode}, #{record.now}, ' ', 'N', ' ', #{record.msPolicyPRT} )",
			 "</script>"})
	int insertFriTreatyShares4(@Param("record") Rin1204VOReq2 rin1204voReq2);

	/**
	 * [5.5.3.1.3]計算第2自留(SEC_Retain)
	 * @param rin1204voReq2
	 * @return
	 */
	@Insert({"<script>",
			 "insert into fri_treaty_shares (policy_no, endorse_no, addr_no, treaty_year, treaty_no, risk_no, duty_bgn, duty_end, acct_type, treaty_type, ",
			 "amt_tsi, amt, prem, amt_typ, prem_typ, amt_ear, prem_ear, share_amt, share_prem, share_amt_typ, share_prem_typ, ",
			 "share_amt_ear, share_prem_ear, remark, treaty_mode, calc_date, calc_user, acct_flag, iendorsement2, policy_dprt) values( ",
			 "#{record.msDpolicyNo}, #{record.msDendorseNo}, #{record.msDaddrNo}, #{record.msTreatyYear}, 'SEC_Retain', ",
			 "#{record.msDriskNo}, #{record.msPolicyDBGN}, #{record.msPolicyDEND}, '0', '1', #{record.retainAmt}, ",
			 "#{record.msDamt}, #{record.msDprem}, #{record.msDamtTyp}, #{record.msDpremTyp}, #{record.msDamtEar}, #{record.msDpremEar}, ",
			 "#{record.retainAmt}, #{record.retainPrem}, 0, 0, 0, 0, ' ', '0', #{record.now}, ' ', 'N', ' ', #{record.msPolicyPRT} )",
			 "</script>"})
	int insertFriTreatyShares5(@Param("record") Rin1204VOReq2 rin1204voReq2);

	/**
	 * 取得合約分保順序1
	 * @param treatyYear
	 * @param policyType
	 * @return
	 */
	@Select({"<script>" ,
	         " select B.treaty_year treatyyear, B.treaty_no treatyno, B.acct_type accttype, B.treaty_mode treatymode, B.limit_base limitbase, ",
			 " B.share_type sharetype, B.share_rate sharerate, B.limit_general limitgeneral, B.limit_total limittotal, B.retain_times retaintimes, ",
			 " B.ref_treaty_no reftreatyno, B.coins_rate coinsrate, A.share_order shareorder ",
			 " from fri_treaty_share_order A inner join fri_treaty B ",
			 " on A.treaty_year = #{treatyYear} ",
			 " and A.policy_type = #{policyType} ",
			 " and A.treaty_year = B.treaty_year and A.treaty_no = B.treaty_no ",
			 " order by A.share_order desc ",
			 "</script>"})
	List<Rin1204VOResp1> queryMoTreatyOrder(@Param("treatyYear") String treatyYear, @Param("policyType") String policyType);

	/**
	 * 計算此同險已擺放保額
	 * @param rin1204voReq3
	 * @return
	 */
	@Select({"<script>" ,
			 " select SUM(share_amt) as shareamt from fri_treaty_shares ",
			 " where treaty_year &lt;= #{record.treatyYear} ", // 於有效期間內皆需計算，無關合約年度!,但不可大於該合約年度
			 " and treaty_no = #{record.treatyNo} " ,
			 " and risk_no = #{record.riskNo} ",
			 " and duty_bgn &lt;= #{record.policyDEND} " ,
			 " and duty_end &gt;= #{record.policyDBGN} ",
			 "<foreach item='item' index='index' collection='record.oldPolicyList' ",
			 "open='and Not(' separator=' Or ' close=')'> " ,
			 " (Policy_no = #{item}) " ,
			 "</foreach> ",
			// 目前保單為續保
			 "<if test='record.oldPolicy != null and record.oldPolicy != \"\"'> ",
			 " and policy_no + endorse_no != #{record.oldPolicy} ",
			 "</if> ",
			// 批單到期日有同一日期起保之續保單時
			 "<if test='record.nextPolicy != null and record.nextPolicy != \"\"'> ",
			 " and policy_no != #{record.nextPolicy} " ,
			 "</if> " ,
			 "</script>"})
	List<Rin1204VOResp5> queryMoShareAmt4(@Param("record") Rin1204VOReq3 rin1204voReq3);

	/**
	 * 計算逐單已擺放保額
	 * @param rin1204voReq3
	 * @return
	 */
	@Select({"<script>",
			 " select SUM(share_amt) as shareamt from fri_treaty_shares ",
			 " where treaty_year = #{record.treatyYear} ",
			 " and policy_dprt &gt; '2003/12/31' and policy_dprt &lt;= #{record.policyPRT} ",
			 " and treaty_no = #{record.treatyNo} ", 
			 " and policy_no = #{record.policyNo} ",
			 " and endorse_no = #{record.endorseNo} " ,
			 " and addr_no = #{record.addrNo} " ,
			 "</script>"})
	List<Rin1204VOResp5> queryMoShareAmt5(@Param("record") Rin1204VOReq3 rin1204voReq3);

	/**
	 * 計算合約QS自留額
	 * @param treatyYear
	 * @param policyType
	 * @return
	 */
	@Select({"<script>",
			 " select Sum(B.share_rate) as exShareRate from fri_treaty_share_order A inner join fri_treaty B ",
			 " on A.treaty_year = #{treatyYear} ",
			 " and A.treaty_year = B.treaty_year and B.treaty_mode='1' ",
			 " and A.treaty_no = B.treaty_no and A.policy_type = #{policyType} ",
			 "</script>"})
	List<Rin1204VOResp8> querymMoRetain2(@Param("treatyYear") String treatyYear, @Param("policyType") String policyType);

	/**
	 * 檢核區間是否已關帳
	 * @param ucRocDbgn
	 * @param ucRocDend
	 * @param riskNo
	 * @return
	 */
	@Select({"<script>",
			 "Select top 1 policy_no, endorse_no, addr_no from fri_treaty_shares ",
			 "where policy_dprt &gt;= #{ucRocDbgn} ",
			 "<if test=' riskNo !=  \"99999999999\" '> ",
			 "and risk_no = #{riskNo} " ,
			 " </if> ",
			 "and acct_flag = 'Y' ",
			 "</script>"})
	List<Rin1204VOResp> checkIsClose(@Param("ucRocDbgn") String ucRocDbgn, @Param("ucRocDend") String ucRocDend, @Param("riskNo") String riskNo);

}
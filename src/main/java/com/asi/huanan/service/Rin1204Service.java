package com.asi.huanan.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.huanan.service.customerize.CustomerizeService;
import com.asi.huanan.service.dao.mybatis.mapper.customerize.CustomerizeMapper;
import com.asi.huanan.service.dao.mybatis.model.Batchlog;
import com.asi.huanan.service.dao.mybatis.model.Batchqueue;
import com.asi.huanan.service.dao.mybatis.model.FriFac;
import com.asi.huanan.service.dao.mybatis.model.FriPolicyDtl;
import com.asi.huanan.service.dao.mybatis.model.FriTreaty;
import com.asi.huanan.service.dao.mybatis.model.FriTreatyCalRecord;
import com.asi.huanan.service.dao.mybatis.model.FriTreatyShares;
import com.asi.huanan.vo.Rin1204VOReq;
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
import com.asi.mechanism.SpringProperty;
import com.asi.mechanism.service.connector.MyBatisConnector;
import com.asi.swissknife.Asiutil;

@Service
public class Rin1204Service {

	private Log log = LogFactory.getLog(Rin1204Service.class);

	@Autowired
	private CustomerizeService customerizeService;
	@Autowired
	private FriTreatyCalRecordService friTreatyCalRecordService;
	@Autowired
	private BatchlogService batchlogService;
	@Autowired
	private MyBatisConnector mybatis;
	@Autowired
	private BatchqueueService batchqueueService;
	
	// ????????? log ?????????
	StringBuilder stringBuilder = new StringBuilder("");	
	
	/**
	 * ??????????????????
	 * @param rin1204VOReq
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	public String autoReinsurance(Rin1204VOReq rin1204VOReq, String taskId)
			throws Exception {
		log.debug(">>> Rin1204Controller.autoReinsurance(??????????????????)");
		Asiutil util = new Asiutil();
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		Configuration mybatisConfig = mybatis.createSqlSessionFactory().getConfiguration();
		String ucRocDend = "";
		String ucRocDbgn = "";
		String riskNo = "";
		String treatyYear = "";
		String msSQL = "";
		BigDecimal BHUNDRED = new BigDecimal("100");
		int ix = 0;
		try {
			String nowTime = util.processDateToString(new Date(), "yyyyMMddHHmm");
			// ??????????????????
			stringBuilder.delete(0, stringBuilder.length());
			writeFile(nowTime, "", taskId, "Y");
			Boolean mbQuit;
			Boolean mbInLoop;
			String msPolicyNo_pre = "";
			String msEndorseNo_pre = "";
			String msDrisk_no_pre = "";
			String nextPolicy = "";
			Long msCoinShareTotAmt = Long.parseLong("0");
			Long msFACAmt = Long.parseLong("0");
			Long msFACPrem = Long.parseLong("0");
			Long msFACTYPAmt = Long.parseLong("0");
			Long msFACTYPPrem = Long.parseLong("0");
			Long msFACEARAmt = Long.parseLong("0");
			Long msFACEARPrem = Long.parseLong("0");
			BigDecimal retainAmt = new BigDecimal("0");
			Long msExProp = Long.parseLong("0");
			String msExSlip = "";
			Long msExBgn = Long.parseLong("0");
			Long msExEnd = Long.parseLong("0");
			BigDecimal msExShare = new BigDecimal("0");
			BigDecimal dExRate = new BigDecimal("0");
			BigDecimal retainPrem = new BigDecimal("0");
			BigDecimal msShareAmt = new BigDecimal("0");
			BigDecimal msPlyShareAmt = new BigDecimal("0");
			BigDecimal msSharePrem = new BigDecimal("0");
			BigDecimal msTREPrem = new BigDecimal("0");
			String msCpolicy_no = "";
			String msCendorse_no = "";
			String msCaddr_no = "0";
			String msCtreaty_year = "";
			String msCtreaty_no = "";
			String msCrisk_no = "";
			String msCduty_bgn = "";
			String msCduty_end = "";	
			String msCacct_type = "";
			String msCtreaty_type = "";
			String msCrate_mode = "";
			Long msCamt_tsi = Long.parseLong("0");
			Long msCamt = Long.parseLong("0");
			Long msCprem = Long.parseLong("0");
			Long msCamt_typ = Long.parseLong("0");
			Long msCprem_typ = Long.parseLong("0");
			Long msCamt_ear = Long.parseLong("0");
			Long msCprem_ear = Long.parseLong("0");
			Long msCshare_amt = Long.parseLong("0");
			Long msCshare_prem = Long.parseLong("0");
			Long msCshare_amt_typ = Long.parseLong("0");
			BigDecimal msCshare_prem_typ = new BigDecimal("0");
			Long msCshare_amt_ear = Long.parseLong("0");
			Long msCshare_prem_ear = Long.parseLong("0");
			String msCremark = "";
			String msCtreaty_mode = "";
			String msCcalc_date = "";
			String msCcalc_user = "";
			String msCacct_flag = "";
			String msCiendorsement2 = "";
			Long msTREAmt = Long.parseLong("0");
//			Long msTREPrem = Long.parseLong("0");
			Long msTRETYPAmt = Long.parseLong("0");
			Long msTRETYPPrem = Long.parseLong("0");
			Long msTREEARAmt = Long.parseLong("0");
			Long msTREEARPrem = Long.parseLong("0");
			BigDecimal QSshare_rate = new BigDecimal("0");
			String QSlimit_base = "";
			Long msOlimit_general = Long.parseLong("0");
			Long msOlimit_total =  Long.parseLong("0");
			BigDecimal msOcoins_rate = new BigDecimal("0");
			Long msMinLimit = Long.parseLong("0");
			Long msPlyLimit = Long.parseLong("0");
			Long msPlyLimitOri = Long.parseLong("0");
			String msOtreaty_year = "";
			String msOtreaty_no = "";
			String msOacct_type = "";
			String msOtreaty_mode = "";
			String msOlimit_base = "";
			String msOshare_type = "";
			BigDecimal msOshare_rate = new BigDecimal("0");
			BigDecimal msOretain_times = new BigDecimal("0");
			String msOref_treaty_no = "";
			BigDecimal msTotShareAmt = new BigDecimal("0");
			Long msRefLimit = Long.parseLong("0");
			Long calc_amt = Long.parseLong("0");
			BigDecimal msShareAmt1 = new BigDecimal("0");
			BigDecimal msQSamt = new BigDecimal("0");
			String retType = "";
			String msPolicyNo = "";
			String msEndorseNo = "";
			Boolean Quit_For_Flag1 = false;
			
			CustomerizeMapper customerizeMapper = sqlSession.getMapper(CustomerizeMapper.class);
			// [1] ????????????
			// ????????????
			treatyYear = rin1204VOReq.getTreatyYear();
			// ????????????
			ucRocDbgn = rin1204VOReq.getUcRocDbgn();
			// ????????????
			ucRocDend = rin1204VOReq.getUcRocDend();
			// ????????????
			riskNo = rin1204VOReq.getRiskNo();
			if (StringUtils.isBlank(treatyYear) && StringUtils.isBlank(ucRocDbgn) && StringUtils.isBlank(ucRocDend)
					&& StringUtils.isBlank(riskNo)) {
				savelog(taskId, "????????????????????????, ?????????????????????????????????");
			}
			// [2] ?????????????????????
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			if(sdf.parse(ucRocDbgn).after(sdf.parse("2003/12/31"))) {
				if("99999999999".equals(riskNo)) {
					// [2.1] ???????????????=99999999999 ???, ?????????????????????????????????????????????
					try {
						customerizeService.deleteOldReinsData999(customerizeMapper, ucRocDbgn, treatyYear);
					} catch (Exception e) {
						savelog(taskId, "Err: " + ucRocDbgn + "-" + ucRocDend + "-" + treatyYear + "??????????????????????????????-01, ??????????????????????????????" + e.toString());
						throw e;
					}
				}else {
					// [2.2] ??????????????????, ???????????????????????????????????????????????????
					// ??????"????????????"???"????????????"???????????????????????????(??????policy_no, endorse_no, addr_no )
					List<FriPolicyDtl> friPolicyDtlList = new ArrayList<>();
					try {
						friPolicyDtlList = customerizeService.queryReinsBeDeleteData(ucRocDbgn, riskNo);
					} catch (Exception e) {
						savelog(taskId, "Err: " + ucRocDbgn + "-" + ucRocDend + "-" + treatyYear + "??????????????????????????????-01R, ??????????????????????????????" + e.toString());
						throw e;
					}
					if(!friPolicyDtlList.isEmpty()) {
						for (FriPolicyDtl friPolicyDtl : friPolicyDtlList) {
							// ???????????????????????????????????????????????????????????????
							try {
								customerizeService.deleteOldReinsDataSpe(customerizeMapper, nullToSpace(friPolicyDtl.getPolicyNo()),
										nullToSpace(friPolicyDtl.getEndorseNo()), nullToZero(friPolicyDtl.getAddrNo()));
							} catch (Exception e) {
								savelog(taskId, "Err: " + ucRocDbgn + "-" + ucRocDend + "-" + treatyYear + "??????????????????????????????-02R, ??????????????????????????????" + e.toString());
								throw e;
							}
						}
					}
				}
			}
			
			log.debug(" Clear Time:" + util.processDateToString(new Date(), "yyyy/MM/dd HH:mm:ss"));
			
			sqlSession.commit();

			// [3] ????????????????????????
			savelog(taskId, "??????????????????" + ucRocDbgn + "-" + ucRocDend + " ???????????????" + riskNo + "-- ???????????????" + "  " + util.processDateToString(new Date(), "yyyy/MM/dd HH:mm:ss"));
			String gsStartTime = util.processDateToString(new Date(), "MMddHHmm");
			List<Rin1204VOResp1> moTreatyOrderAll;
			try {
				moTreatyOrderAll = customerizeService.queryTreatyShareOrder(treatyYear);
			} catch (Exception e) {
				savelog(taskId, "Err: " + "????????????????????????????????????-07, ??????????????????????????????");
				throw e;
			}
			if(moTreatyOrderAll.isEmpty()) {
				savelog(taskId, "Err: " + "?????????????????????????????????????????????, ?????????????????????????????????1");
				throw new Exception("Err: " + "?????????????????????????????????????????????, ?????????????????????????????????1");
			}
			
			// [4] ???????????????????????????
			log.debug("Bgn>>" + util.processDateToString(new Date(), "yyyy/MM/dd HH:mm:ss"));
			List<Rin1204VOResp2> shareDetailList;
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ucRocDbgn", rin1204VOReq.getUcRocDbgn());
			    map.put("ucRocDend", rin1204VOReq.getUcRocDend());
			    map.put("riskNo", rin1204VOReq.getRiskNo());
			    msSQL = getSQL(mybatisConfig, map, "queryShareDetailList");
				writeFile(nowTime, "???????????????????????????: " + msSQL, taskId, "Y");
				shareDetailList = customerizeService.queryShareDetailList(ucRocDbgn, ucRocDend, riskNo);
			} catch (Exception e) {
				savelog(taskId, msPolicyNo + "-" + msEndorseNo + "????????????????????????-03, ??????????????????????????????");
				throw e;
			}
			log.debug("End>>" + util.processDateToString(new Date(), "yyyy/MM/dd HH:mm:ss"));
			if(shareDetailList.isEmpty()) {
				savelog(taskId, msPolicyNo + "-" + msEndorseNo + "??????????????????????????????????????????????????????, ??????????????????????????????");
				// ?????????????????????"????????????"????????????????????????????????? Exception
				return msPolicyNo + "-" + msEndorseNo + "??????????????????????????????????????????????????????, ??????????????????????????????";
//				throw new Exception(msPolicyNo + "-" + msEndorseNo + "??????????????????????????????????????????????????????, ??????????????????????????????");
			}
			
			// [5] ??????????????????
			writeFile(nowTime, "---- start -----" + util.processDateToString(new Date(), "yyyy/MM/dd HH:mm:ss"), taskId, "N");
			writeFile(nowTime, "????????????: " + msSQL, taskId, "Y");
			for (int k = 0; k < shareDetailList.size(); k++) {
				// ????????? taskID ??? TABLE batchqueue ?????????????????????"3"??????????????????????????????????????????????????? "3"
				if("3".equals(getProcessStatus(taskId, batchqueueService))) {
					writeFile(nowTime, "", taskId, "Y");
					return "3";
				}
				
				ix = ix + 1;
		        String msPolicyPRT = "";
		        if(shareDetailList.get(k).getPolicyDprt() != null) {
		        	msPolicyPRT = util.processDateToString(shareDetailList.get(k).getPolicyDprt(), "yyyy/MM/dd");
		        }
		        msPolicyNo = nullToSpace(shareDetailList.get(k).getPolicyNo());
		        msEndorseNo =  nullToSpace(shareDetailList.get(k).getEndorseNo());
		        String msPolicyDBGN = "";
		        if(shareDetailList.get(k).getPolicyDbgn() != null) {
		        	msPolicyDBGN = util.processDateToString(shareDetailList.get(k).getPolicyDbgn(), "yyyy/MM/dd");
		        }
		        String msPolicyDEND = "";
		        if(shareDetailList.get(k).getPolicyDend() != null) {
		        	msPolicyDEND = util.processDateToString(shareDetailList.get(k).getPolicyDend(), "yyyy/MM/dd");
		        }
		        String msPolicyType = nullToSpace(shareDetailList.get(k).getPolicyType());
		        Long msAllAmt = nullToZero(shareDetailList.get(k).getAllAmt());
		        Long msAmt = nullToZero(shareDetailList.get(k).getAmt());
		        BigDecimal msCoinsRate = nullToZero(shareDetailList.get(k).getCoinsRate());
		        String msChangeFlag = nullToZero(shareDetailList.get(k).getChangeFlag());
		        String msOldPolicy = nullToZero(shareDetailList.get(k).getOldPolicy());
		        String msDpolicy_no = nullToSpace(shareDetailList.get(k).getPolicyNo());
		        String msDendorse_no = nullToSpace(shareDetailList.get(k).getEndorseNo());
		        Short msDaddr_no = nullToZero(shareDetailList.get(k).getAddrNo());
		        String msDrisk_no = nullToSpace(shareDetailList.get(k).getRiskNo());
		        Long msDamt = nullToZero(shareDetailList.get(k).getDamt());
		        Long msDprem = nullToZero(shareDetailList.get(k).getPrem());
		        String msDprop_addr = nullToSpace(shareDetailList.get(k).getPropAddr());
		        String msDzip_code = nullToSpace(shareDetailList.get(k).getZipCode());
		        String msDarea_code = nullToSpace(shareDetailList.get(k).getAreaCode());
		        String msDuseprop_code = nullToSpace(shareDetailList.get(k).getUsepropCode());
		        String msDuseprop_name = nullToSpace(shareDetailList.get(k).getUsepropName());
		        String msDconst_class = nullToSpace(shareDetailList.get(k).getConstClass());
		        String msDlimit_no = nullToSpace(shareDetailList.get(k).getLimitNo());
		        Long msDlimit = nullToZero(shareDetailList.get(k).getLimit());
		        Long msDamt_flt = nullToZero(shareDetailList.get(k).getAmtFlt());
		        Long msDprem_flt = nullToZero(shareDetailList.get(k).getPremFlt());
		        Long msDamt_fix = nullToZero(shareDetailList.get(k).getAmtFix());
		        Long msDprem_fix = nullToZero(shareDetailList.get(k).getPremFix());
		        Long msDamt_typ = nullToZero(shareDetailList.get(k).getAmtTyp());
		        Long msDprem_typ = nullToZero(shareDetailList.get(k).getPremTyp());
		        Long msDamt_ear = nullToZero(shareDetailList.get(k).getAmtEar());
		        Long msDprem_ear = nullToZero(shareDetailList.get(k).getPremEar());
		        String msDrisk_flag = nullToSpace(shareDetailList.get(k).getRiskFlag());
		        String msDrisk_name = nullToSpace(shareDetailList.get(k).getRiskName());
		        BigDecimal msDlimit_rate = nullToZero(shareDetailList.get(k).getLimitRate());
		        Long msDlimit_ori = nullToZero(shareDetailList.get(k).getLimitOri());
		        Short msDaddr_no_ori = nullToZero(shareDetailList.get(k).getAddrNoOri());
		        String msEndReason = nullToSpace(shareDetailList.get(k).getEndReason());
		        String msEndReason_main = nullToSpace(shareDetailList.get(k).getEndReasonMain());
		        String msCalcFlag = nullToZero(shareDetailList.get(k).getCalcFlag());
		        
				writeFile(nowTime,
						"==================================================================================================================================================================", taskId, "N");
				writeFile(nowTime, "policy_no:" + msPolicyNo + "---endorse_no:" + msEndorseNo + "--addr_no:"
						+ msDaddr_no + "--StartTime:" + util.processDateToString(new Date(), "yyyy/MM/dd HH:mm:ss"), taskId, "N");
		        
		        // [5.1] ?????????????????????????????????????????????
    		    //  [5.1.1] ??????????????????????????????(????????????????????????????????????????????????????????????????????????)
				Map<String, Object> map = new HashMap<>();
				map.put("riskNo", msDrisk_no);
			    map.put("policyDend", msPolicyDEND);
			    map.put("policyDbgn", msPolicyDBGN);
			    map.put("policyDprt", msPolicyPRT);
			    map.put("policyNo", msDpolicy_no);
			    msSQL = getSQL(mybatisConfig, map, "queryOldPolicyList");
				writeFile(nowTime, "???????????????  " + msSQL, taskId, "N");
		        List<Rin1204VOResp3> moOldPolicy = new ArrayList<>();
		        if(!msPolicyNo.equals(msPolicyNo_pre) || !msEndorseNo.equals(msEndorseNo_pre) || !msDrisk_no.equals(msDrisk_no_pre)) {
		        	try {
						moOldPolicy = customerizeService.queryOldPolicyList(msDrisk_no, msPolicyDEND,
								msPolicyDBGN, msPolicyPRT, msDpolicy_no);
					} catch (Exception e) {
						savelog(taskId, "???????????????????????????-14, ??????????????????????????????");
						writeFile(nowTime, "???????????????????????????-14, ??????????????????????????????", taskId, "Y");
						throw e;
					}
		        	 msPolicyNo_pre = msPolicyNo;
    	             msEndorseNo_pre = msEndorseNo;
    	             msDrisk_no_pre = msDrisk_no;
		        }
		        // [5.1.2] ???????????????(????????????)?????????????????????
		        if(StringUtils.isNotBlank(msEndorseNo)) {
		        	Map<String, Object> map2 = new HashMap<>();
					map2.put("policyNo", msPolicyNo);
				    map2.put("policyDEND", msPolicyDEND);
				    msSQL = getSQL(mybatisConfig, map2, "queryMoNextPolicy");
					writeFile(nowTime, "???????????????????????????:" + msSQL, taskId, "N");
		        	List<Rin1204VOResp4> moNextPolicy = new ArrayList<>();
					try {
						moNextPolicy = customerizeService.queryMoNextPolicy(msPolicyNo, msPolicyDEND);
					} catch (Exception e) {
						savelog(taskId, "??????????????????????????????-14, ??????????????????????????????");
						throw e;
					}
		        	if(moNextPolicy.isEmpty()) {
		        		nextPolicy = "";
		        	}else {
		        		nextPolicy = moNextPolicy.get(0).getPolicyNo();
		        	}
		        	writeFile(nowTime, "???????????????:" + nextPolicy, taskId, "N");
		        }else {
		        	nextPolicy = ""; // ???????????????????????????????????????
		        }
				
				//  [5.2] ??????????????????????????????????????????(??????[5.1]?????????????????????)???Retain + Q/S + Surp???
		        List<String> oldPolicyList = new ArrayList<>();
		        for(int i=0 ; i< moOldPolicy.size() ; i++) {
		        	if(moOldPolicy.get(i).getOldPolicy() == null) {
		        		oldPolicyList.add("");
		        	}else {
		        		oldPolicyList.add(moOldPolicy.get(i).getOldPolicy());
		        	}
		        }
		        List<Rin1204VOResp5> moShareAmt = new ArrayList<>();
		        Map<String, Object> map2 = new HashMap<>();
		        map2.put("treatyYear", treatyYear);
		        map2.put("riskNo", msDrisk_no);
		        map2.put("policyDEND", msPolicyDEND);
		        map2.put("policyDBGN", msPolicyDBGN);
		        map2.put("policyPRT", msPolicyPRT);
		        map2.put("oldPolicyList", oldPolicyList);
		        map2.put("oldPolicy", msOldPolicy);
		        msSQL = getSQL(mybatisConfig, map2, "queryMoShareAmt");
				try {
					moShareAmt = customerizeService.queryMoShareAmt(treatyYear, msDrisk_no, msPolicyDEND, msPolicyDBGN, msPolicyPRT, oldPolicyList, msOldPolicy);
				} catch (Exception e) {
					log.debug(e.toString());
					Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
					writeFile(nowTime, "????????????????????????-14 =>" + msSQL, taskId, "N");
					savelog(taskId, "????????????????????????-14, ??????????????????????????????");
					if (k == shareDetailList.size() - 1) {
						afterLoop2(nowTime, ix, ucRocDbgn, ucRocDend, riskNo, taskId);
						sqlSession.commit();
						return "";
					}else {
						continue;
					}
				}
		        if(moShareAmt.isEmpty()) {
		        	msCoinShareTotAmt = Long.parseLong("0");
		        }else {
					if (moShareAmt.get(0) == null || moShareAmt.get(0).getShareAmt() == null) {
		        		msCoinShareTotAmt = Long.parseLong("0");
		        	}else {
		        		msCoinShareTotAmt = moShareAmt.get(0).getShareAmt();
		        	}
		        }
		        
		        writeFile(nowTime, "msCoinShareTotAmt???" + msSQL, taskId, "N");
		        writeFile(nowTime, "msCoinShareTotAmt???" + msCoinShareTotAmt, taskId, "N");
		        
		        // ??????????????????????????????????????????10??????????????????0
		        if("10".equals(msEndReason)) {
		        	msDamt = Long.parseLong("0");
		        	msDamt_flt = Long.parseLong("0");
		        	msDamt_fix = Long.parseLong("0");
		        	msDamt_typ = Long.parseLong("0");
		        	msDamt_ear = Long.parseLong("0");
		        }
		        
				writeFile(nowTime, "=================================>1  " + msPolicyNo + "----------" + msEndorseNo
						+ "--------" + msDaddr_no + "===  " + msPolicyDBGN + " <--> " + msPolicyDEND, taskId, "N");
		        
				//  [5.3] ????????????????????????0???, ????????????????????????????????????????????????    Check Amt If 0 Get Ori Policy Data
				if (msDamt == 0 && msDprem == 0) {
					if (k == shareDetailList.size() - 1) {
						afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
						sqlSession.commit();
						return "";
					} else {
						continue;
					}
				}
				// ????????????????????????0?????????,???????????????????????????????????????
				if(msDamt == 0) {  // ?????????0
					// [5.4] ????????????0????????????( If msDamt = 0)
					
					/**
					 * ??????=0,????????????????????????????????????????????????Surp???Q/S???Retain
					 * ???????????????????????????????????????????????????????????????????????????????????????????????????????????????
					 */
					
					// ???????????????????????????Check Fac Prem>
					
					// [5.4.1] ???????????????????????? Sum amt, prem of FAC share
					msFACPrem = Long.parseLong("0");
					List<Rin1204VOResp6> moFACShare = new ArrayList<>();
					Map<String, Object> map3 = new HashMap<>();
					map3.put("policyNo", msDpolicy_no);
					map3.put("endorseNo", msDendorse_no);
					map3.put("addrNo", msDaddr_no);
				    msSQL = getSQL(mybatisConfig, map3, "queryFACShare");
					try {
						moFACShare = customerizeService.queryFACShare(msDpolicy_no, msDendorse_no, msDaddr_no);
					} catch (Exception e) {
						log.debug(e.toString());
						Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
						writeFile(nowTime, "????????????????????????-05(5.4.1]=>" + msSQL, taskId, "N");
						savelog(taskId, "????????????????????????-05, ??????????????????????????????");
						if (k == shareDetailList.size() - 1) {
							afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
							sqlSession.commit();
							return "";
						} else {
							continue;
						}
					}
					if(moFACShare.isEmpty()) {
						msFACAmt = Long.parseLong("0");
                        msFACPrem = Long.parseLong("0");
                        msFACTYPAmt = Long.parseLong("0");
                        msFACTYPPrem = Long.parseLong("0");
                        msFACEARAmt = Long.parseLong("0");
                        msFACEARPrem = Long.parseLong("0");
					}else {
						if (moFACShare.get(0) == null || moFACShare.get(0).getFacAmt() == null) {
							msFACAmt = Long.parseLong("0");
						} else {
							msFACAmt = moFACShare.get(0).getFacAmt();
						}
						if (moFACShare.get(0) == null || moFACShare.get(0).getFacPrem() == null) {
							msFACPrem = Long.parseLong("0");
						} else {
							msFACPrem = moFACShare.get(0).getFacPrem();
						}
						if (moFACShare.get(0) == null || moFACShare.get(0).getFacAmtTyp() == null) {
							msFACTYPAmt = Long.parseLong("0");
						} else {
							msFACTYPAmt = moFACShare.get(0).getFacAmtTyp();
						}
						if (moFACShare.get(0) == null || moFACShare.get(0).getFacPremTyp() == null) {
							msFACTYPPrem = Long.parseLong("0");
						} else {
							msFACTYPPrem = moFACShare.get(0).getFacPremTyp();
						}
						if (moFACShare.get(0) == null || moFACShare.get(0).getFacAmtEar() == null) {
							msFACEARAmt = Long.parseLong("0");
						} else {
							msFACEARAmt = moFACShare.get(0).getFacAmtEar();
						}
						if (moFACShare.get(0) == null || moFACShare.get(0).getFacPremEar() == null) {
							msFACEARPrem = Long.parseLong("0");
						} else {
							msFACEARPrem = moFACShare.get(0).getFacPremEar();
						}
					}
					
					// ??????????????????, ????????? 0
					if(StringUtils.isBlank(msDendorse_no) || "R".equals(StringUtils.upperCase(msEndReason_main))) {
						// [5.4.2]??????????????????(FAC_Retain) Sum amt, prem of FAC_Retail share
						List<FriFac> moFACRetain = new ArrayList<>();
						Map<String, Object> map4 = new HashMap<>();
						map4.put("policyNo", msDpolicy_no);
						map4.put("endorseNo", msDendorse_no);
						map4.put("addrNo", msDaddr_no);
						msSQL = getSQL(mybatisConfig, map4, "queryFACRetain");
						try {
							moFACRetain = customerizeService.queryFACRetain(msDpolicy_no, msDendorse_no, msDaddr_no);
						} catch (Exception e) {
							log.debug(e.toString());
							Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
							writeFile(nowTime, "????????????????????????-05A=>" + msSQL, taskId, "N");
							savelog(taskId, "????????????????????????-05A, ??????????????????????????????" + e.toString());
							if (k == shareDetailList.size() - 1) {
								afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
								sqlSession.commit();
								return "";
							} else {
								continue;
							}
						}
						if(!moFACRetain.isEmpty()) {
							retainAmt = BigDecimal.ZERO;
							for (FriFac friFac : moFACRetain) {
								msExProp = Long.parseLong("0");
								// ?????????,?????????
								msExSlip = nullToSpace(friFac.getSlipNo());
								// ??????????????????
								msExBgn = nullToZero(friFac.getExcessBgn());
								// ??????????????????
								msExEnd = nullToZero(friFac.getExcessLimit());
								// ??????"??????????????????"????????????
								List<Rin1204VOResp7> moExAmt = new ArrayList<>();
								Map<String, Object> map5 = new HashMap<>();
								map5.put("exSlip", msExSlip);
							    msSQL = getSQL(mybatisConfig, map5, "queryExAmt");
								try {
									moExAmt = customerizeService.queryExAmt(msExSlip);
								} catch (Exception e) {
									log.debug(e.toString());
									Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
									writeFile(nowTime, "????????????????????????-05B1=>" + msSQL, taskId, "N");
									savelog(taskId, "????????????????????????-05B1, ?????????????????????????????? " + e.toString());
									if (k == shareDetailList.size() - 1) {
										afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
										sqlSession.commit();
										return "";
									} else {
										continue;
									}
								}
								if(!moExAmt.isEmpty() && moExAmt.get(0).getExAmt() != null) {
									msExProp = moExAmt.get(0).getExAmt();
								}
								if(msExEnd == 0) {
									msExEnd = msExProp;
								}
								// --------------------------------------
								msExShare = BigDecimal.ZERO;
								// ??????"??????????????????"???????????? (?????????) % ??????
								List<Rin1204VOResp8> moExShare = new ArrayList<>();
								Map<String, Object> map6 = new HashMap<>();
								map6.put("exSlip", msExSlip);
							    msSQL = getSQL(mybatisConfig, map6, "queryExShare");
								try {
									moExShare = customerizeService.queryExShare(msExSlip);
								} catch (Exception e) {
									log.debug(e.toString());
									Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
									writeFile(nowTime, "????????????????????????-05C-1=>" + msSQL, taskId, "N");
									savelog(taskId, "????????????????????????-05C-1, ??????????????????????????????");
									if (k == shareDetailList.size() - 1) {
										afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
										sqlSession.commit();
										return "";
									} else {
										continue;
									}
								}
								if(!moExShare.isEmpty() && moExShare.get(0).getExShareRate() != null) {
									msExShare = moExShare.get(0).getExShareRate();
								}
								
								// dExRate = (msExEnd - msExBgn) / msExProp
								dExRate = new BigDecimal(msExEnd).subtract(new BigDecimal(msExBgn)).divide(new BigDecimal(msExProp), 4, RoundingMode.HALF_UP);
				                // RetainAmt = msDamt / (msCoinsRate / 100) * (msExShare / 100) * (1 - dExRate)
								retainAmt = ((new BigDecimal(msDamt).divide(msCoinsRate.divide(BHUNDRED), 4, RoundingMode.HALF_UP))
										.multiply(msExShare.divide(BHUNDRED)))
												.multiply(BigDecimal.ONE.subtract(dExRate));
								writeFile(nowTime,
										"sp 1: retainamt=" + retainAmt.toPlainString() + " msdamt=" + msDamt.toString()
												+ " mscoinsrate=" + msCoinsRate.toPlainString() + " msexshare="
												+ msExShare.toPlainString() + " dexrate=" + dExRate.toPlainString()
												+ " msexend=" + msExEnd.toString() + " msexbgn=" + msExBgn.toString()
												+ " msexprop=" + msExProp.toString(), taskId, "N");
								// RetainPrem = msDprem * (1 / (msCoinsRate / 100) * (msExShare / 100) * (1 - dExRate))
								retainPrem = new BigDecimal(msDprem).multiply(
										BigDecimal.ONE.divide(msCoinsRate.divide(BHUNDRED), 4, RoundingMode.HALF_UP)
												.multiply(msExShare.divide(BHUNDRED))
												.multiply(BigDecimal.ONE.subtract(dExRate)));
								
								// EDIT????????????????????? (?????????764???)
								List<FriTreatyShares> moExShare2 = new ArrayList<>();
								Map<String, Object> map7 = new HashMap<>();
								map7.put("policyNo", msDpolicy_no);
								map7.put("endorseNo", msDendorse_no);
								map7.put("addrNo", msDaddr_no);
								map7.put("treatyYear", treatyYear);
								map7.put("riskNo", msDrisk_no);
							    msSQL = getSQL(mybatisConfig, map7, "queryExShare2");
								try {
									moExShare2 = customerizeService.queryExShare2(msDpolicy_no, msDendorse_no, msDaddr_no, treatyYear, msDrisk_no);
								} catch (Exception e) {
									log.debug(e.toString());
									Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
									writeFile(nowTime, "????????????????????????-05C-2=>" + msSQL, taskId, "N");
									savelog(taskId, "????????????????????????????????????-05C-2, ??????????????????????????????");
								}
								if(!moExShare2.isEmpty()) {
									Rin1204VOReq1 rin1204VOReq1 = new Rin1204VOReq1();
									rin1204VOReq1.setMsShareAmt(msShareAmt);
									rin1204VOReq1.setMsPlyShareAmt(msPlyShareAmt);
									rin1204VOReq1.setMsDamt(msDamt);
									rin1204VOReq1.setMsDprem(msDprem);
									rin1204VOReq1.setMsDamtTyp(msDamt_typ);
									rin1204VOReq1.setMsDpremTyp(msDprem_typ);
									rin1204VOReq1.setMsDamtEar(msDamt_ear);
									rin1204VOReq1.setMsDpremEar(msDprem_ear);
									rin1204VOReq1.setRetainAmt(retainAmt);
									rin1204VOReq1.setRetainPrem(retainPrem);
									rin1204VOReq1.setMsDpolicyNo(msDpolicy_no);
									rin1204VOReq1.setMsDendorseNo(msDendorse_no);
									rin1204VOReq1.setMsDaddrNo(msDaddr_no);
									rin1204VOReq1.setTreatyYear(treatyYear);
									rin1204VOReq1.setMsDriskNo(msDrisk_no);
									// update ???????????????
									Map<String, Object> map8 = new HashMap<>();
									map8.put("record", rin1204VOReq1);
								    msSQL = getSQL(mybatisConfig, map8, "updateFriTreatyShares");
									try {
										customerizeService.updateFriTreatyShares(customerizeMapper, rin1204VOReq1);
									} catch (Exception e) {
										log.debug(e.toString());
										Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
										savelog(taskId, msPolicyNo + "-" + msEndorseNo + "?????????" + msDrisk_no + "????????????????????????????????????, ??????????????????????????????");
										writeFile(nowTime, msPolicyNo + "-" + msEndorseNo + "?????????" + msDrisk_no + "????????????????????????????????????, ??????????????????????????????", taskId, "N");
										writeFile(nowTime, msSQL, taskId, "N");
										if (k == shareDetailList.size() - 1) {
											afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
											sqlSession.commit();
											return "";
										} else {
											continue;
										}
									}
									 sqlSession.commit();
									// ????????? taskID ??? TABLE batchqueue ?????????????????????"3"??????????????????????????????????????????????????? "3"
									if("3".equals(getProcessStatus(taskId, batchqueueService))) {
										writeFile(nowTime, "", taskId, "Y");
										return "3";
									}
								}else {
									Rin1204VOReq2 rin1204VOReq2 = new Rin1204VOReq2();
									rin1204VOReq2.setMsDpolicyNo(msDpolicy_no);
									rin1204VOReq2.setMsDendorseNo(msDendorse_no);
									rin1204VOReq2.setMsDaddrNo(msDaddr_no);
									rin1204VOReq2.setMsTreatyYear(treatyYear);
									rin1204VOReq2.setMsDriskNo(msDrisk_no);
									rin1204VOReq2.setMsPolicyDBGN(msPolicyDBGN);
									rin1204VOReq2.setMsPolicyDEND(msPolicyDEND);
									rin1204VOReq2.setMsShareAmt(msShareAmt);
									rin1204VOReq2.setMsPlyShareAmt(msPlyShareAmt);
									rin1204VOReq2.setMsDamt(msDamt);
									rin1204VOReq2.setMsDprem(msDprem);
									rin1204VOReq2.setMsDamtTyp(msDamt_typ);
									rin1204VOReq2.setMsDpremTyp(msDprem_typ);
									rin1204VOReq2.setMsDamtEar(msDamt_ear);
									rin1204VOReq2.setMsDpremEar(msDprem_ear);
									rin1204VOReq2.setRetainAmt(retainAmt);
									rin1204VOReq2.setRetainPrem(retainPrem);
									rin1204VOReq2.setNow(util.processDateToString(new Date(), "yyyy/MM/dd HH:mm:ss"));
									rin1204VOReq2.setMsPolicyPRT(msPolicyPRT);
									// insert ???????????????
									Map<String, Object> map8 = new HashMap<>();
									map8.put("record", rin1204VOReq2);
								    msSQL = getSQL(mybatisConfig, map8, "insertFriTreatyShares");
									try {
										customerizeService.insertFriTreatyShares(customerizeMapper, rin1204VOReq2);
									} catch (Exception e) {
										log.debug(e.toString());
										Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
										savelog(taskId, msPolicyNo + "-" + msEndorseNo + "?????????" + msDrisk_no + "????????????????????????????????????, ??????????????????????????????");
										writeFile(nowTime, msPolicyNo + "-" + msEndorseNo + "?????????" + msDrisk_no + "????????????????????????????????????, ??????????????????????????????", taskId, "N");
										writeFile(nowTime, msSQL, taskId, "N");
										if (k == shareDetailList.size() - 1) {
											afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
											sqlSession.commit();
											return "";
										} else {
											continue;
										}
									}
									 sqlSession.commit();
									// ????????? taskID ??? TABLE batchqueue ?????????????????????"3"??????????????????????????????????????????????????? "3"
									if("3".equals(getProcessStatus(taskId, batchqueueService))) {
										writeFile(nowTime, "", taskId, "Y");
										return "3";
									}
								}
								// ????????? 846 ???
								msFACAmt  = msFACAmt + retainAmt.longValue();
							}
						}
					}
					writeFile(nowTime, "===>..........  ??????????????????" + msFACAmt + "(FAC_RET:1)", taskId, "N");
					
					// [5.4.3]???????????????0???????????????(??????)
					// ????????? 855 ???
					msDprem = msDprem - msFACPrem;
					// ??????????????????????????????????????????,?????????????????????
					// 1.0 Query PolDtl
					List<Rin1204VOResp> moTemp = new ArrayList<>();
					Map<String, Object> map1 = new HashMap<>();
					map1.put("policyNo", msDpolicy_no);
					map1.put("addrNoOri", msDaddr_no_ori);
				    msSQL = getSQL(mybatisConfig, map1, "queryMoTemp");
					try {
						moTemp = customerizeService.queryMoTemp(msDpolicy_no, msDaddr_no_ori);
					} catch (Exception e) {
						log.debug(e.toString());
						Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
						savelog(taskId, msPolicyNo + "-" + msEndorseNo + "?????????" + msDrisk_no + "??????????????????????????????-04, ??????????????????????????????");
						if (k == shareDetailList.size() - 1) {
							afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
							return "";
						} else {
							continue;
						}
					}
					if(moTemp.isEmpty()) {
						writeFile(nowTime, "?????????????????????????????????, ??????????????????????????????(type1) " + msSQL, taskId, "N");
						if (k == shareDetailList.size() - 1) {
							afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
							sqlSession.commit();
							return "";
						} else {
							continue;
						}
					}
					
					// 2.0 Query Policy Shares
					String policyNoMoTemp = "";
					if(moTemp.get(0).getPolicyNo() != null) {
						policyNoMoTemp = moTemp.get(0).getPolicyNo();
					}
					String endorseNoMoTemp = "";
					if(moTemp.get(0).getEndorseNo() != null) {
						endorseNoMoTemp = moTemp.get(0).getEndorseNo();
					}
					String addrNoMoTemp = "";
					if(moTemp.get(0).getAddrNo() != null) {
						addrNoMoTemp = moTemp.get(0).getAddrNo().toString();
					}
					List<FriTreatyShares> moCancel = new ArrayList<>();
					Map<String, Object> map4 = new HashMap<>();
					map4.put("policyNo", policyNoMoTemp);
					map4.put("endorseNo", endorseNoMoTemp);
					map4.put("addrNo", addrNoMoTemp);
				    msSQL = getSQL(mybatisConfig, map4, "queryMoCancel");
					try {
						moCancel = customerizeService.queryMoCancel(policyNoMoTemp, endorseNoMoTemp, addrNoMoTemp);
					} catch (Exception e) {
						log.debug(e.toString());
						Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
						savelog(taskId, msPolicyNo + "-" + msEndorseNo + "?????????" + msDrisk_no + "????????????????????????-04, ??????????????????????????????");
						if (k == shareDetailList.size() - 1) {
							afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
							sqlSession.commit();
							return "";
						} else {
							continue;
						}
					}
					if(moCancel.isEmpty()) {
						writeFile(nowTime, "?????????????????????????????????, ??????????????????????????????(type2) " + msSQL, taskId, "N");
						if (k == shareDetailList.size() - 1) {
							afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
							sqlSession.commit();
							return "";
						} else {
							continue;
						}
					}
					// If Ori Policy Exist Continue
					msSharePrem = BigDecimal.ZERO;
					msTREPrem = BigDecimal.ZERO;
					
					for(int j = 0 ; j < moCancel.size() ; j++) {
						msCpolicy_no = "";
						if(moCancel.get(j).getPolicyNo() != null) {
							msCpolicy_no = moCancel.get(j).getPolicyNo();
						}
						msCendorse_no = "";
						if(moCancel.get(j).getEndorseNo() != null) {
							msCendorse_no = moCancel.get(j).getEndorseNo();
						}
						msCaddr_no = "0";
						if(moCancel.get(j).getAddrNo() != null) {
							msCaddr_no = moCancel.get(j).getAddrNo().toString();
						}
						msCtreaty_year = "";
						if(moCancel.get(j).getTreatyYear() != null) {
							msCtreaty_year = moCancel.get(j).getTreatyYear();
						}
						msCtreaty_no = "";
						if(moCancel.get(j).getTreatyNo() != null) {
							msCtreaty_no = moCancel.get(j).getTreatyNo();
						}
						msCrisk_no = "";
						if(moCancel.get(j).getRiskNo() != null) {
							msCrisk_no = moCancel.get(j).getRiskNo();
						}
						msCduty_bgn = "";
						if(moCancel.get(j).getDutyBgn() != null) {
							msCduty_bgn = util.processDateToString(moCancel.get(j).getDutyBgn(), "yyyy/MM/dd");
						}
						msCduty_end = "";
						if(moCancel.get(j).getDutyEnd() != null) {
							msCduty_end = util.processDateToString(moCancel.get(j).getDutyEnd(), "yyyy/MM/dd");
						}
						msCacct_type = "";
						if(moCancel.get(j).getAcctType()!= null) {
							msCacct_type = moCancel.get(j).getAcctType();
						}
						msCtreaty_type = "";
						if(moCancel.get(j).getTreatyType()!= null) {
							msCtreaty_type = moCancel.get(j).getTreatyType();
						}
						msCamt_tsi = Long.parseLong("0");
						if(moCancel.get(j).getAmtTsi() != null) {
							msCamt_tsi = moCancel.get(j).getAmtTsi();
						}
						msCamt = Long.parseLong("0");
						if(moCancel.get(j).getAmt() != null) {
							msCamt = moCancel.get(j).getAmt();
						}
						msCprem = Long.parseLong("0");
						if(moCancel.get(j).getPrem() != null) {
							msCprem = moCancel.get(j).getPrem();
						}
						msCamt_typ = Long.parseLong("0");
						if(moCancel.get(j).getAmtTyp() != null) {
							msCamt_typ = moCancel.get(j).getAmtTyp();
						}
						msCprem_typ = Long.parseLong("0");
						if(moCancel.get(j).getPremTyp() != null) {
							msCprem_typ = moCancel.get(j).getPremTyp();
						}
						msCamt_ear = Long.parseLong("0");
						if(moCancel.get(j).getAmtEar() != null) {
							msCamt_ear = moCancel.get(j).getAmtEar();
						}
						msCprem_ear = Long.parseLong("0");
						if(moCancel.get(j).getPremEar() != null) {
							msCprem_ear = moCancel.get(j).getPremEar();
						}
						msCshare_amt = Long.parseLong("0");
						if(moCancel.get(j).getShareAmt() != null) {
							msCshare_amt = moCancel.get(j).getShareAmt();
						}
						msCshare_prem = Long.parseLong("0");
						if(moCancel.get(j).getSharePrem() != null) {
							msCshare_prem = moCancel.get(j).getSharePrem();
						}
						msCshare_amt_typ = Long.parseLong("0");
						if(moCancel.get(j).getShareAmtTyp() != null) {
							msCshare_amt_typ = moCancel.get(j).getShareAmtTyp();
						}
						msCshare_prem_typ = new BigDecimal("0");
						if(moCancel.get(j).getSharePremTyp() != null) {
							msCshare_prem_typ = moCancel.get(j).getSharePremTyp();
						}
						msCshare_amt_ear = Long.parseLong("0");
						if(moCancel.get(j).getShareAmtEar() != null) {
							msCshare_amt_ear = moCancel.get(j).getShareAmtEar();
						}
						msCshare_prem_ear = Long.parseLong("0");
						if(moCancel.get(j).getSharePremEar() != null) {
							msCshare_prem_ear = moCancel.get(j).getSharePremEar();
						}
						msCremark = "";
						if(moCancel.get(j).getRemark() != null) {
							msCremark = moCancel.get(j).getRemark();
						}
						msCtreaty_mode = "";
						if(moCancel.get(j).getTreatyMode() != null) {
							msCtreaty_mode = moCancel.get(j).getTreatyMode();
						}
						msCcalc_date = "";
						if(moCancel.get(j).getCalcDate() != null) {
							msCcalc_date = util.processDateToString(moCancel.get(j).getCalcDate(), "yyyy/MM/dd");  
						}
						msCcalc_user = "";
						if(moCancel.get(j).getCalcUsr() != null) {
							msCcalc_user = moCancel.get(j).getCalcUsr();
						}
						msCacct_flag = "";
						if(moCancel.get(j).getAcctFlag() != null) {
							msCacct_flag = moCancel.get(j).getAcctFlag();
						}
						msCiendorsement2 = "";
						if(moCancel.get(j).getIendorsement2() != null) {
							msCiendorsement2 = moCancel.get(j).getIendorsement2();
						}
						
						// ???????????????
						if(j != moCancel.size()-1) {
							if(msCamt == 0) {
								msSharePrem = BigDecimal.ZERO;
							}else {
								/**
								 * Fix ????????? ?????? ????????????????????????????????????????????????
								 * ??? Fix ???????????????????????? number ??????????????????????????? ????????? FIX ??????-8.4 ?????????-8???
								 * ?????? JAVA ?????? "RoundingMode.DOWN"
								 */
								// msSharePrem = Fix(msDprem * (msCshare_amt / msCamt))
								msSharePrem = new BigDecimal(msDprem).multiply(new BigDecimal(msCshare_amt)
										.divide(new BigDecimal(msCamt), 4, RoundingMode.HALF_UP))
										.setScale(0, RoundingMode.DOWN);
							}
						}else {
							// msSharePrem = msDprem - msTREPrem
							msSharePrem = new BigDecimal(msDprem).subtract(msTREPrem); // ????????????
						}
						
						// Insert Into fri_treaty_shares EDIT?????????????????????
						Rin1204VOReq2 rin1204VOReq2 = new Rin1204VOReq2();
						rin1204VOReq2.setMsDpolicyNo(msDpolicy_no);
						rin1204VOReq2.setMsDendorseNo(msDendorse_no);
						rin1204VOReq2.setMsDaddrNo(msDaddr_no);
						rin1204VOReq2.setMsTreatyYear(treatyYear);
						rin1204VOReq2.setMsCtreatyNo(msCtreaty_no);
						rin1204VOReq2.setMsDriskNo(msDrisk_no);
						rin1204VOReq2.setMsPolicyDBGN(msPolicyDBGN);
						rin1204VOReq2.setMsPolicyDEND(msPolicyDEND);
						rin1204VOReq2.setMsCacctType(msCacct_type);
						rin1204VOReq2.setMsCamtTsi(msCamt_tsi);
						rin1204VOReq2.setMsDamt(msDamt);
						rin1204VOReq2.setMsDprem(msDprem);
						rin1204VOReq2.setMsDamtTyp(msDamt_typ);
						rin1204VOReq2.setMsDpremTyp(msDprem_typ);
						rin1204VOReq2.setMsDamtEar(msDamt_ear);
						rin1204VOReq2.setMsDpremEar(msDprem_ear);
						rin1204VOReq2.setMsSharePrem(msSharePrem.longValue());
						rin1204VOReq2.setMsCtreatyMode(msCtreaty_mode);
						rin1204VOReq2.setNow(util.processDateToString(new Date(), "yyyy/MM/dd HH:mm:ss"));
						rin1204VOReq2.setMsPolicyPRT(msPolicyPRT);
						try {
							customerizeService.insertFriTreatyShares2(customerizeMapper, rin1204VOReq2);
						} catch (Exception e) {
							log.debug(e.toString());
							Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
							savelog(taskId, msPolicyNo + "-" + msEndorseNo + "?????????" + msDrisk_no + "?????????0???????????????????????????1, ??????????????????????????????");
							Quit_For_Flag1 = true;
							break;
						}
						Quit_For_Flag1 = false;
						sqlSession.commit();
						// ????????? taskID ??? TABLE batchqueue ?????????????????????"3"??????????????????????????????????????????????????? "3"
						if("3".equals(getProcessStatus(taskId, batchqueueService))) {
							writeFile(nowTime, "", taskId, "Y");
							return "3";
						}
						
						msTREPrem = msTREPrem.add(msSharePrem);
					}
					if(Quit_For_Flag1 == true) {
						if (k == shareDetailList.size() - 1) {
							afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
							sqlSession.commit();
							return "";
						} else {
							continue;
						}
					}
				}else {
					// [5.5] ????????????0?????????( If msDamt <> 0)
					// Check Amt And Prem If Not 0 Process ???????????????????????????0??????????????????
					if (msDamt != 0 || msDprem != 0 || msDamt_typ != 0 || msDprem_typ != 0 || msDamt_ear != 0
							|| msDprem_ear != 0) {
						// [5.5.1] ??????????????????/??????  01. ======== Sum amt, prem of FAC share
						List<Rin1204VOResp6> moFACShare = new ArrayList<>();
						Map<String, Object> map1 = new HashMap<>();
						map1.put("policyNo", msDpolicy_no);
						map1.put("endorseNo", msDendorse_no);
						map1.put("addrNo", msDaddr_no);
					    msSQL = getSQL(mybatisConfig, map1, "queryFACShare");
						writeFile(nowTime, "??????????????????: " + msSQL, taskId, "N");
						try {
							moFACShare = customerizeService.queryFACShare(msDpolicy_no, msDendorse_no, msDaddr_no);
						} catch (Exception e) {
							log.debug(e.toString());
							Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
							writeFile(nowTime, "????????????????????????-05(5.5]=>" + msSQL, taskId, "N");
							savelog(taskId, "????????????????????????-05, ??????????????????????????????");
							if (k == shareDetailList.size() - 1) {
								afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
								sqlSession.commit();
								return "";
							} else {
								continue;
							}
						}
						if(moFACShare.isEmpty()) {
							msFACAmt = Long.parseLong("0");
	                        msFACPrem = Long.parseLong("0");
	                        msFACTYPAmt = Long.parseLong("0");
	                        msFACTYPPrem = Long.parseLong("0");
	                        msFACEARAmt = Long.parseLong("0");
	                        msFACEARPrem = Long.parseLong("0");
						}else {
							if (moFACShare.get(0) == null || moFACShare.get(0).getFacAmt() == null) {
								msFACAmt = Long.parseLong("0");
							} else {
								msFACAmt = moFACShare.get(0).getFacAmt();
							}
							if (moFACShare.get(0) == null || moFACShare.get(0).getFacPrem() == null) {
								msFACPrem = Long.parseLong("0");
							} else {
								msFACPrem = moFACShare.get(0).getFacPrem();
							}
							if (moFACShare.get(0) == null || moFACShare.get(0).getFacAmtTyp() == null) {
								msFACTYPAmt = Long.parseLong("0");
							} else {
								msFACTYPAmt = moFACShare.get(0).getFacAmtTyp();
							}
							if (moFACShare.get(0) == null || moFACShare.get(0).getFacPremTyp() == null) {
								msFACTYPPrem = Long.parseLong("0");
							} else {
								msFACTYPPrem = moFACShare.get(0).getFacPremTyp();
							}
							if (moFACShare.get(0) == null || moFACShare.get(0).getFacAmtEar() == null) {
								msFACEARAmt = Long.parseLong("0");
							} else {
								msFACEARAmt = moFACShare.get(0).getFacAmtEar();
							}
							if (moFACShare.get(0) == null || moFACShare.get(0).getFacPremEar() == null) {
								msFACEARPrem = Long.parseLong("0");
							} else {
								msFACEARPrem = moFACShare.get(0).getFacPremEar();
							}
						}
						
						// [5.5.2] ??????????????????(FAC_Retain)  02. ======== Sum amt, prem of FAC_Retail share
						// ??????????????????, ????????? 0
						if(StringUtils.isBlank(msDendorse_no) || "R".equals(StringUtils.upperCase(msEndReason_main))) {
							List<FriFac> moFACRetain = new ArrayList<>();
							Map<String, Object> map3 = new HashMap<>();
							map3.put("policyNo", msDpolicy_no);
							map3.put("endorseNo", msDendorse_no);
							map3.put("addrNo", msDaddr_no);
						    msSQL = getSQL(mybatisConfig, map3, "queryFACRetain");
						    writeFile(nowTime, "temp getdata: " + msSQL, taskId, "N");
							try {
								moFACRetain = customerizeService.queryFACRetain(msDpolicy_no, msDendorse_no, msDaddr_no);
							} catch (Exception e) {
								log.debug(e.toString());
								Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
								savelog(taskId, "????????????????????????-05A, ??????????????????????????????");
								if (k == shareDetailList.size() - 1) {
									afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
									sqlSession.commit();
									return "";
								} else {
									continue;
								}
							}
							retainAmt = BigDecimal.ZERO;
							if(!moFACRetain.isEmpty()) {
								for (FriFac friFac : moFACRetain) {
									msExProp = Long.parseLong("0");
									// ?????????,?????????
									msExSlip = nullToSpace(friFac.getSlipNo());
									// ??????????????????
									msExBgn = nullToZero(friFac.getExcessBgn());
									// ??????????????????
									msExEnd = nullToZero(friFac.getExcessLimit());
									// ????????????????????????, ???????????????????????????
									if(msDamt < 0) {
										msExBgn = -1 * Math.abs(msExBgn);
									}else {
										msExBgn = Math.abs(msExBgn);
									}
									// ??????"??????????????????"????????????
									List<Rin1204VOResp7> moExAmt = new ArrayList<>();
									Map<String, Object> map4 = new HashMap<>();
									map4.put("exSlip", msExSlip);
								    msSQL = getSQL(mybatisConfig, map4, "queryExAmt");
								    writeFile(nowTime, "temp get moExAmt: " + msSQL, taskId, "N");
									try {
										moExAmt = customerizeService.queryExAmt(msExSlip);
									} catch (Exception e) {
										log.debug(e.toString());
										Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
										savelog(taskId, "????????????????????????-05B2, ??????????????????????????????:" + e.toString());
										if (k == shareDetailList.size() - 1) {
											afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
											sqlSession.commit();
											return "";
										} else {
											continue;
										}
									}
									if(!moExAmt.isEmpty() && moExAmt.get(0).getExAmt() != null) {
										msExProp = moExAmt.get(0).getExAmt();
									}
									if(msExEnd == 0) {
										msExEnd = msExProp;
									}
									// --------------------------------------
									msExShare = BigDecimal.ZERO;
									// ??????"??????????????????"???????????? (?????????) % ??????
									List<Rin1204VOResp8> moExShare = new ArrayList<>();
									try {
										moExShare = customerizeService.queryExShare(msExSlip);
									} catch (Exception e) {
										log.debug(e.toString());
										Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
										savelog(taskId, "????????????????????????-05C, ??????????????????????????????");
										if (k == shareDetailList.size() - 1) {
											afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
											sqlSession.commit();
											return "";
										} else {
											continue;
										}
									}
									if(!moExShare.isEmpty() && moExShare.get(0).getExShareRate() != null) {
										msExShare = moExShare.get(0).getExShareRate();
									}
									
									if(msExProp != 0) {
										// dExRate = (msExEnd - msExBgn) / msExProp
										dExRate = new BigDecimal(msExEnd).subtract(new BigDecimal(msExBgn)).divide(new BigDecimal(msExProp), 4, RoundingMode.HALF_UP);
										// RetainAmt = (msDamt / (msCoinsRate / 100) * (msExShare / 100) * (1 - dExRate))
										// ????????? RetainAmt ?????????????????????????????????
									}else {
										dExRate = BigDecimal.ZERO;
	                                    retainAmt = BigDecimal.ZERO;
									}
									// RetainAmt = Round((msDamt / (msCoinsRate / 100) * (msExShare / 100) * (1 - dExRate)), 0)
									retainAmt = new BigDecimal(msDamt)
											.divide(msCoinsRate.divide(BHUNDRED), 4, RoundingMode.HALF_UP)
											.multiply(msExShare.divide(BHUNDRED))
											.multiply(BigDecimal.ONE.subtract(dExRate)).round(new MathContext(0));
									writeFile(nowTime, "xx RetainAmt=" + retainAmt + " msexend=" + msExEnd + " msexbgn=" + msExBgn + " msexprop=" + msExProp, taskId, "N");
									writeFile(nowTime, "temp......msdamt=" + msDamt + " mscoinsrate=" + msCoinsRate + " msexshare=" + msExShare + " dexrate=" + dExRate, taskId, "N");
									// RetainPrem = Round(msDprem * (RetainAmt / msDamt), 0)
									retainPrem = new BigDecimal(msDprem)
											.multiply(retainAmt.divide(new BigDecimal(msDamt), 4, RoundingMode.HALF_UP))
											.round(new MathContext(0));
									
									// EDIT????????????????????? (?????????1118???)
									List<FriTreatyShares> moExShare2 = new ArrayList<>();
									try {
										moExShare2 = customerizeService.queryExShare2(msDpolicy_no, msDendorse_no, msDaddr_no, treatyYear, msDrisk_no);
									} catch (Exception e) {
										log.debug(e.toString());
										Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
										savelog(taskId, "????????????????????????????????????-05C, ??????????????????????????????");
									}
									if(!moExShare2.isEmpty()) {
										Rin1204VOReq1 rin1204VOReq1 = new Rin1204VOReq1();
										rin1204VOReq1.setMsShareAmt(msShareAmt);
										rin1204VOReq1.setMsPlyShareAmt(msPlyShareAmt);
										rin1204VOReq1.setMsDamt(msDamt);
										rin1204VOReq1.setMsDprem(msDprem);
										rin1204VOReq1.setMsDamtTyp(msDamt_typ);
										rin1204VOReq1.setMsDpremTyp(msDprem_typ);
										rin1204VOReq1.setMsDamtEar(msDamt_ear);
										rin1204VOReq1.setMsDpremEar(msDprem_ear);
										rin1204VOReq1.setRetainAmt(retainAmt);
										rin1204VOReq1.setRetainPrem(retainPrem);
										rin1204VOReq1.setMsDpolicyNo(msDpolicy_no);
										rin1204VOReq1.setMsDendorseNo(msDendorse_no);
										rin1204VOReq1.setMsDaddrNo(msDaddr_no);
										rin1204VOReq1.setTreatyYear(treatyYear);
										rin1204VOReq1.setMsDriskNo(msDrisk_no);
										// update ???????????????
										Map<String, Object> map5 = new HashMap<>();
										map5.put("record", rin1204VOReq1);
									    msSQL = getSQL(mybatisConfig, map5, "updateFriTreatyShares");
										try {
											customerizeService.updateFriTreatyShares(customerizeMapper, rin1204VOReq1);
										} catch (Exception e) {
											log.debug(e.toString());
											Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
											savelog(taskId, msPolicyNo + "-" + msEndorseNo + "?????????" + msDrisk_no + "????????????????????????????????????, ??????????????????????????????");
											writeFile(nowTime, msPolicyNo + "-" + msEndorseNo + "?????????" + msDrisk_no + "????????????????????????????????????, ??????????????????????????????", taskId, "N");
											writeFile(nowTime, msSQL, taskId, "N");
											if (k == shareDetailList.size() - 1) {
												afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
												sqlSession.commit();
												return "";
											} else {
												continue;
											}
										}
										 sqlSession.commit();
										// ????????? taskID ??? TABLE batchqueue ?????????????????????"3"??????????????????????????????????????????????????? "3"
										if("3".equals(getProcessStatus(taskId, batchqueueService))) {
											writeFile(nowTime, "", taskId, "Y");
											return "3";
										}
									}else {
										Rin1204VOReq2 rin1204VOReq2 = new Rin1204VOReq2();
										rin1204VOReq2.setMsDpolicyNo(msDpolicy_no);
										rin1204VOReq2.setMsDendorseNo(msDendorse_no);
										rin1204VOReq2.setMsDaddrNo(msDaddr_no);
										rin1204VOReq2.setMsTreatyYear(treatyYear);
										rin1204VOReq2.setMsDriskNo(msDrisk_no);
										rin1204VOReq2.setMsPolicyDBGN(msPolicyDBGN);
										rin1204VOReq2.setMsPolicyDEND(msPolicyDEND);
										rin1204VOReq2.setMsShareAmt(msShareAmt);
										rin1204VOReq2.setMsPlyShareAmt(msPlyShareAmt);
										rin1204VOReq2.setMsDamt(msDamt);
										rin1204VOReq2.setMsDprem(msDprem);
										rin1204VOReq2.setMsDamtTyp(msDamt_typ);
										rin1204VOReq2.setMsDpremTyp(msDprem_typ);
										rin1204VOReq2.setMsDamtEar(msDamt_ear);
										rin1204VOReq2.setMsDpremEar(msDprem_ear);
										rin1204VOReq2.setRetainAmt(retainAmt);
										rin1204VOReq2.setRetainPrem(retainPrem);
										rin1204VOReq2.setNow(util.processDateToString(new Date(), "yyyy/MM/dd HH:mm:ss"));
										rin1204VOReq2.setMsPolicyPRT(msPolicyPRT);
										// insert ???????????????
										Map<String, Object> map5 = new HashMap<>();
										map5.put("record", rin1204VOReq2);
									    msSQL = getSQL(mybatisConfig, map5, "insertFriTreatyShares");
										try {
											customerizeService.insertFriTreatyShares(customerizeMapper, rin1204VOReq2);
										} catch (Exception e) {
											log.debug(e.toString());
											Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
											savelog(taskId, msPolicyNo + "-" + msEndorseNo + "?????????" + msDrisk_no + "????????????????????????????????????, ??????????????????????????????");
											writeFile(nowTime, msPolicyNo + "-" + msEndorseNo + "?????????" + msDrisk_no + "????????????????????????????????????, ??????????????????????????????", taskId, "N");
											writeFile(nowTime, msSQL, taskId, "N");
											if (k == shareDetailList.size() - 1) {
												afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
												sqlSession.commit();
												return "";
											} else {
												continue;
											}
										}
										 sqlSession.commit();
										// ????????? taskID ??? TABLE batchqueue ?????????????????????"3"??????????????????????????????????????????????????? "3"
										if("3".equals(getProcessStatus(taskId, batchqueueService))) {
											writeFile(nowTime, "", taskId, "Y");
											return "3";
										}
									}
//									// ????????? 1199 ???
									msFACAmt  = msFACAmt + retainAmt.longValue();
								}
							}
						}
						writeFile(nowTime, "===>..........  ??????????????????" + msFACAmt + "(RetainAmt=" + retainAmt + ")(FAC_RET:2)", taskId, "N");
						msTREAmt = Long.parseLong("0");
	                    msTREPrem = BigDecimal.ZERO;
	                    msTRETYPAmt = Long.parseLong("0");
	                    msTRETYPPrem = Long.parseLong("0");
	                    msTREEARAmt = Long.parseLong("0");
	                    msTREEARPrem = Long.parseLong("0");
	                    
	                    /**
	                     * ????????????
	                     * ?????????1.Retain  2.Q/S  3.Surp (?????????)
	                     * ?????????1.Surp    2.Q/S  3.Retain
	                     */
	                    
	                    // [5.5.3]??????????????????    03. ======== Calc Share amt, prem of Retain Treaty
	                    // Calc Treaty For Retain    Step 3 To 6
	                    if(msDamt != 0) {
	                    	// ?????????????????????0??????????????????
							if ((msDamt - msFACAmt - msTREAmt != 0) || (msDamt_typ - msFACTYPAmt - msTRETYPAmt != 0)
									|| (msDamt_ear - msFACEARAmt - msTREEARAmt != 0)) {
								// [5.5.3.1]???????????????
								if(msDamt >= 0) { // ??????
									writeFile(nowTime, "===>..........  ??????????????????" + msDamt, taskId, "N");
									// [5.5.3.1.1]????????????QS?????????(Retain)
									// 03-1. ======== Get Treaty Share Order ========
									List<FriTreaty> moRetain = new ArrayList<>();
									try {
										moRetain = customerizeService.querymMoRetain(treatyYear, msPolicyType);
									} catch (Exception e) {
										log.debug(e.toString());
										Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
										savelog(taskId, "[" + msDpolicy_no + "]-[" + msDendorse_no + "]????????????????????????-11, ??????????????????????????????");
										if (k == shareDetailList.size() - 1) {
											afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
											sqlSession.commit();
											return "";
										} else {
											continue;
										}
									}
									if(moRetain.isEmpty()) {
										 QSshare_rate = BigDecimal.ZERO;
									}else {
										QSshare_rate = BigDecimal.ZERO;
										if(moRetain.get(0).getShareRate() != null) {
											QSshare_rate = moRetain.get(0).getShareRate();
										}
										QSlimit_base = "";
										if(moRetain.get(0).getLimitBase() != null) {
											QSlimit_base = moRetain.get(0).getLimitBase();
										}
										msOlimit_general = Long.parseLong("0");
										if(moRetain.get(0).getLimitGeneral() != null) {
											msOlimit_general = moRetain.get(0).getLimitGeneral();
										}
										msOlimit_total = Long.parseLong("0");
										if(moRetain.get(0).getLimitTotal() != null) {
											msOlimit_total = moRetain.get(0).getLimitTotal() ;
										}
										msOcoins_rate = BigDecimal.ZERO;
										if(moRetain.get(0).getCoinsRate() != null) {
											msOcoins_rate= moRetain.get(0).getCoinsRate();
										}
									}
									
									// 03-2. ======== Get Exist Min Retain Limit For   [Retain Use]========
									// A.??????????????????
									if("1".equals(QSlimit_base)) {
										// ??????
										List<Rin1204VOResp9> moMinLimit = new ArrayList<>();
										Map<String, Object> map3 = new HashMap<>();
										map3.put("riskNo", msDrisk_no);
										map3.put("treatyYear", treatyYear);
										map3.put("policyDEND", msPolicyDEND);
										map3.put("policyDBGN", msPolicyDBGN);
										map3.put("policyPRT", msPolicyPRT);
									    msSQL = getSQL(mybatisConfig, map3, "queryMinLimit");
									    writeFile(nowTime, "===>..........  ?????????????????? " + msSQL, taskId, "N");
										try {
											moMinLimit = customerizeService.queryMinLimit(msDrisk_no,
													treatyYear, msPolicyDEND, msPolicyDBGN, msPolicyPRT);
										} catch (Exception e) {
											log.debug(e.toString());
											Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
											savelog(taskId, "????????????????????????-13, ??????????????????????????????");
											if (k == shareDetailList.size() - 1) {
												afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
												sqlSession.commit();
												return "";
											} else {
												continue;
											}
										}
										if(!moMinLimit.isEmpty()) {
											if(moMinLimit.get(0)==null || moMinLimit.get(0).getMinLimit() == null) {
												msMinLimit = Long.parseLong("0");
											}else {
												msMinLimit = moMinLimit.get(0).getMinLimit();
											}
											if(msMinLimit == 0) {
												msMinLimit = msDlimit;
											}
										}else {
											savelog(taskId, "????????????????????????, ???????????????????????????");
											if (k == shareDetailList.size() - 1) {
												afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
												sqlSession.commit();
												return "";
											} else {
												continue;
											}
										}
									}else {
										// ??????
										msMinLimit = msDlimit;
									}
									writeFile(nowTime, "===>..........  ??????????????????" + msMinLimit + "  ??????????????????" + msDlimit, taskId, "N");
									// B.????????????????????????????????????????????????
									if(msDlimit > msMinLimit) {
										msPlyLimit = msMinLimit;
	                                    msPlyLimitOri = msPlyLimit;
									}else {
										msPlyLimit = msDlimit;
	                                    msPlyLimitOri = msPlyLimit;
									}

									// C.[change_flag <> X]??? ??????????????????,????????? msPlyLimit ????????????????????????
									if(!"X".equals(StringUtils.upperCase(msChangeFlag.trim()))) {
										msShareAmt = BigDecimal.ZERO;
										// C-1 --????????????
										if (BHUNDRED.subtract(msCoinsRate).compareTo(msOcoins_rate) <= 0) {
											if (msPlyLimit > msOlimit_general && msOlimit_general != 0) {
												msPlyLimit = msOlimit_general;
											}
										}
										// C-2 --?????????   [ ????????????,?????????(retain+Q/S+Surp)????????????????????????]
										if (msCoinsRate.compareTo(BHUNDRED) != 0
												&& msCoinsRate.compareTo(msOcoins_rate) < 0) {
											// C-2-1.Query Treaty Shares ????????????
											msShareAmt = new BigDecimal(msCoinShareTotAmt);
											// C-2-2.Get Min Limit
											if(msShareAmt.compareTo(BigDecimal.ZERO) > 0) {
												if (new BigDecimal(msPlyLimit).add(msShareAmt)
														.compareTo(new BigDecimal(msOlimit_total)) > 0) {
													msPlyLimit = new BigDecimal(msOlimit_total).subtract(msShareAmt).longValue();
												}
											}
											if(msPlyLimit < 0) {
												msPlyLimit = Long.parseLong("0");
											}
										}
									}
									writeFile(nowTime, "===>..........  ????????????" + msPlyLimit, taskId, "N");
									// 04. ======== Calc Same Risk Amt ======== ??????????????????????????????
									if("1".equals(QSlimit_base)) { // ??????
										List<Rin1204VOResp5> moShareAmt2 = new ArrayList<>();
										Map<String, Object> map3 = new HashMap<>();
										map3.put("treatyYear", treatyYear);
										map3.put("riskNo", msDrisk_no);
										map3.put("policyDEND", msPolicyDEND);
										map3.put("policyDBGN", msPolicyDBGN);
										map3.put("policyPRT", msPolicyPRT);
										map3.put("oldPolicyList", oldPolicyList);
										map3.put("oldPolicy", msOldPolicy);
										map3.put("nextPolicy", nextPolicy);
									    msSQL = getSQL(mybatisConfig, map3, "queryMoShareAmt2");
										try {
											moShareAmt2 = customerizeService.queryMoShareAmt2(
													treatyYear, msDrisk_no, msPolicyDEND, msPolicyDBGN, msPolicyPRT,
													oldPolicyList, msOldPolicy, nextPolicy);
										} catch (Exception e) {
											log.debug(e.toString());
											Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
											savelog(taskId, "????????????????????????-14, ??????????????????????????????");
											if (k == shareDetailList.size() - 1) {
												afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
												sqlSession.commit();
												return "";
											} else {
												continue;
											}
										}
										if(moShareAmt2.isEmpty()) {
											msShareAmt = BigDecimal.ZERO;
										}else {
											if(moShareAmt2.get(0)==null || moShareAmt2.get(0).getShareAmt() == null) {
												msShareAmt = BigDecimal.ZERO;
											}else {
												msShareAmt = new BigDecimal(moShareAmt2.get(0).getShareAmt());
											}
											if(msShareAmt.compareTo(BigDecimal.ZERO) < 0) {
												msShareAmt = BigDecimal.ZERO;
											}
										}
									}else {
										// ??????
										msShareAmt = BigDecimal.ZERO;
									}
									writeFile(nowTime, "===>..........  ???????????????" + msShareAmt + "  " + msSQL, taskId, "N");
									
									// 05. ======== Calc Share To Treaty Limit  ======== '???????????????????????? [??????-???????????????]
									// A.????????????-???????????????
									if(!"X".equals(StringUtils.upperCase(msChangeFlag.trim())) && msCoinsRate.compareTo(BHUNDRED) != 0 &&
											msCoinsRate.compareTo(msOcoins_rate) < 0 ) {
//										msPlyLimit = (msPlyLimit * (100 - QSshare_rate) / 100);
										msPlyLimit = new BigDecimal(msPlyLimit)
												.multiply(BHUNDRED.subtract(QSshare_rate))
												.divide(BHUNDRED).longValue();
										writeFile(nowTime, "tmp0:" + msPlyLimit, taskId, "N");
									}else {
										if (new BigDecimal(msPlyLimit)
												.multiply(BHUNDRED.subtract(QSshare_rate))
												.divide(BHUNDRED).compareTo(msShareAmt) > 0) {
//											msPlyLimit = (msPlyLimit * (100 - QSshare_rate) / 100) - msShareAmt
											msPlyLimit = new BigDecimal(msPlyLimit)
													.multiply(BHUNDRED.subtract(QSshare_rate))
													.divide(BHUNDRED).subtract(msShareAmt).longValue();
											writeFile(nowTime, "tmp1:" + msPlyLimit, taskId, "N");
										}else {
											msPlyLimit = Long.parseLong("0");  // ????????????
										}
									}
									if (new BigDecimal(msPlyLimit).compareTo(new BigDecimal(msDamt - msFACAmt)
											.multiply(BHUNDRED.subtract(QSshare_rate))
											.divide(BHUNDRED)) > 0) {
										// ????????? 1391
										msPlyLimit = new BigDecimal(msDamt - msFACAmt)
												.multiply(BHUNDRED.subtract(QSshare_rate))
												.divide(BHUNDRED).longValue();
										writeFile(nowTime, "tmp2:" + msPlyLimit + " msDamt=" + msDamt + " msfacamt=" + msFACAmt + " qsshare_rate=" + QSshare_rate, taskId, "N");
									}else {
										writeFile(nowTime, "tmp3:" + msPlyLimit, taskId, "N");
									}
									// B.?????????????????????????????????
									if (new BigDecimal(msPlyLimit).compareTo(
											new BigDecimal(msPlyLimitOri).multiply(BHUNDRED.subtract(QSshare_rate))
													.divide(BHUNDRED).subtract(msShareAmt)) < 0) {
										writeFile(nowTime, "tmp4:" + msPlyLimit, taskId, "N");
									} else {
										if (new BigDecimal(msPlyLimitOri).multiply(BHUNDRED.subtract(QSshare_rate))
												.divide(BHUNDRED).subtract(msShareAmt).compareTo(BigDecimal.ZERO) < 0) {
											msPlyLimit = Long.parseLong("0"); // ????????????
										}else {
											msPlyLimit = new BigDecimal(msPlyLimitOri)
													.multiply(BHUNDRED.subtract(QSshare_rate)).divide(BHUNDRED)
													.subtract(msShareAmt).longValue();
											writeFile(nowTime, "tmp5:" + msPlyLimit, taskId, "N");
										}
									}
									// RetainAmt = CDbl(FormatNumber(msPlyLimit, 0))
									// ???????????????????????????????????????????????????JAVA??????????????????????????????????????????????????????
									retainAmt = new BigDecimal(msPlyLimit).setScale(0, RoundingMode.HALF_UP);
									// RetainPrem = CDbl(FormatNumber(msDprem * (RetainAmt / msDamt), 0))
									retainPrem = new BigDecimal(msDprem).multiply(
											retainAmt.divide(new BigDecimal(msDamt), 4, RoundingMode.HALF_UP)).setScale(0, RoundingMode.HALF_UP);
									// ??????????????????????????????y , ?????????????????????
									if("S".equals(msPolicyType) && "Y".equals(msCalcFlag)) {
										retainAmt = new BigDecimal(msDamt).setScale(0, RoundingMode.HALF_UP);
										retainPrem = new BigDecimal(msDprem)
												.multiply(retainAmt.divide(new BigDecimal(msDamt), 4, RoundingMode.HALF_UP)).setScale(0, RoundingMode.HALF_UP);
									}
									writeFile(nowTime, "===>msDamt=" + msDamt + " msFACAmt=" + msFACAmt + "  msShareAmt=" + msShareAmt + " QSshare_rate=" + QSshare_rate, taskId, "N");
									writeFile(nowTime, "===>******************1  ????????????" + retainAmt, taskId, "N");
									// ???????????????????????????, ?????????????????? ??????????????????????????????????????????????????????
									if(billedCheck(msDpolicy_no, msDendorse_no, msDaddr_no, treatyYear, "Retain", taskId, nowTime) == true) {
										// ?????????1424???
										retainAmt = BigDecimal.ZERO;
			                            retainPrem = BigDecimal.ZERO;
			                            writeFile(nowTime, "===>******************1  ?????????????????? : " + retainAmt, taskId, "N");
									}else if ("Y".equals(msCalcFlag)) {
										// ???????????????Y???, ?????????Retain
										// 06. =========  Insert Treaty Share --[Retain]  =====================
										Rin1204VOReq2 rin1204VOReq2 = new Rin1204VOReq2();
										rin1204VOReq2.setMsDpolicyNo(msDpolicy_no);
										rin1204VOReq2.setMsDendorseNo(msDendorse_no);
										rin1204VOReq2.setMsDaddrNo(msDaddr_no);
										rin1204VOReq2.setMsTreatyYear(treatyYear);
										rin1204VOReq2.setMsDriskNo(msDrisk_no);
										rin1204VOReq2.setMsPolicyDBGN(msPolicyDBGN);
										rin1204VOReq2.setMsPolicyDEND(msPolicyDEND);
										rin1204VOReq2.setMsShareAmt(msShareAmt);
										rin1204VOReq2.setMsPlyShareAmt(msPlyShareAmt);
										rin1204VOReq2.setMsDamt(msDamt);
										rin1204VOReq2.setMsDprem(msDprem);
										rin1204VOReq2.setMsDamtTyp(msDamt_typ);
										rin1204VOReq2.setMsDpremTyp(msDprem_typ);
										rin1204VOReq2.setMsDamtEar(msDamt_ear);
										rin1204VOReq2.setMsDpremEar(msDprem_ear);
										rin1204VOReq2.setRetainAmt(retainAmt);
										rin1204VOReq2.setRetainPrem(retainPrem);
										rin1204VOReq2.setNow(util.processDateToString(new Date(), "yyyy/MM/dd HH:mm:ss"));
										rin1204VOReq2.setMsPolicyPRT(msPolicyPRT);
										try {
											customerizeService.insertFriTreatyShares3(customerizeMapper, rin1204VOReq2);
										} catch (Exception e) {
											log.debug(e.toString());
											Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
											savelog(taskId, msDpolicy_no + "-" + msDendorse_no + " ?????????" + msDrisk_no + "????????????????????????2, ??????????????????????????????");
											if (k == shareDetailList.size() - 1) {
												afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
												sqlSession.commit();
												return "";
											} else {
												continue;
											}
										}
										 sqlSession.commit();
										// ????????? taskID ??? TABLE batchqueue ?????????????????????"3"??????????????????????????????????????????????????? "3"
										if("3".equals(getProcessStatus(taskId, batchqueueService))) {
											writeFile(nowTime, "", taskId, "Y");
											return "3";
										}
										
										msTREAmt = new BigDecimal(msTREAmt).add(retainAmt).longValue() ;
									}
									
									// [5.5.3.1.2]????????????FFQSQ, FFQSS, FRXL?????????   07. ======== Calc Share amt, prem of Other Treaty  ========
									
									// Calc Treaty For Other (Not Retain)    Step 07-1 To 07-11
									// 1477
									mbQuit = false;
									mbInLoop = false;
									for (Rin1204VOResp1 treatyOrderAll : moTreatyOrderAll) {
										if(msDamt < msFACAmt + msTREAmt) {
											mbQuit = true;
										}
										
										if (StringUtils.upperCase(treatyOrderAll.getPolicyType())
												.equals(StringUtils.upperCase(msPolicyType)) && Boolean.FALSE.equals(mbQuit)) {
											mbInLoop = true;
											Quit_For_Flag1 = false;
											msOtreaty_year = "";
											if(treatyOrderAll.getTreatyYear() != null) {
												msOtreaty_year = treatyOrderAll.getTreatyYear();
											}
											msOtreaty_no = "";
											if(treatyOrderAll.getTreatyNo() != null) {
												msOtreaty_no = treatyOrderAll.getTreatyNo();
											}
											msOacct_type = "";
			                                if(treatyOrderAll.getAcctType() != null) {
			                                	msOacct_type = treatyOrderAll.getAcctType();
			                                }
			                                msOtreaty_mode = "";
			                                if(treatyOrderAll.getTreatyMode() != null) {
			                                	msOtreaty_mode = treatyOrderAll.getTreatyMode();
			                                }
			                                msOlimit_base = "";
			                                if(treatyOrderAll.getLimitBase() != null) {
			                                	msOlimit_base = treatyOrderAll.getLimitBase();
			                                }
			                                msOshare_type = "";
			                                if(treatyOrderAll.getShareType() != null) {
			                                	msOshare_type = treatyOrderAll.getShareType();
			                                }
			                                msOshare_rate = BigDecimal.ZERO;
			                                if(treatyOrderAll.getShareRate() != null) {
			                                	msOshare_rate = treatyOrderAll.getShareRate();
			                                }
			                                msOlimit_general = Long.parseLong("0");
			                                if(treatyOrderAll.getLimitGeneral() != null) {
			                                	msOlimit_general = treatyOrderAll.getLimitGeneral();
			                                }
			                                msOlimit_total = Long.parseLong("0");
			                                if(treatyOrderAll.getLimitTotal() != null) {
			                                	msOlimit_total = treatyOrderAll.getLimitTotal();
			                                }
			                                msOretain_times = BigDecimal.ZERO;
			                                if(treatyOrderAll.getRetainTimes() != null) {
			                                	msOretain_times = treatyOrderAll.getRetainTimes();
			                                }
			                                msOref_treaty_no = "";
			                                if(treatyOrderAll.getRefTreatyNo() != null) {
			                                	msOref_treaty_no = treatyOrderAll.getRefTreatyNo();
			                                }
			                                msOcoins_rate = BigDecimal.ZERO;
			                                if(treatyOrderAll.getCoinsRate() != null) {
			                                	msOcoins_rate = treatyOrderAll.getCoinsRate();
			                                }
			                                
			                                // ???????????????=????????????-???????????????
//			                                msTotShareAmt = msDamt - msFACAmt - msTREAmt
											msTotShareAmt = new BigDecimal(msDamt).subtract(new BigDecimal(msFACAmt)).subtract(new BigDecimal(msTREAmt));
			                                
											writeFile(nowTime,
													"===>..........  ??????" + msOtreaty_no + ".....Amt=[" + msDamt
															+ "] FAC=[" + msFACAmt + "] Tre=[" + msTREAmt + "]    ???????????????"
															+ msTotShareAmt, taskId, "N");
											
											if (billedCheck(msDpolicy_no, msDendorse_no, msDaddr_no, treatyYear,
													msOtreaty_no, taskId, nowTime) == true) {
												// ??????????????????_A
												continue;
											}
											
											if(msTotShareAmt.compareTo(BigDecimal.ZERO) >= 0) {
												// Get Exist Min Limit For   [Other Treaty Use]
												// 07-1.??????????????????
												if("1".equals(msOlimit_base)) {
													// ??????
													List<Rin1204VOResp9> moMinLimit;
													Map<String, Object> map3 = new HashMap<>();
													map3.put("riskNo", msDrisk_no);
													map3.put("treatyYear", treatyYear);
													map3.put("policyDEND", msPolicyDEND);
													map3.put("policyDBGN", msPolicyDBGN);
													map3.put("policyPRT", msPolicyPRT);
												    msSQL = getSQL(mybatisConfig, map3, "queryMinLimit");
													writeFile(nowTime, "????????????????????????: " + msSQL, taskId, "N");
													try {
														moMinLimit = customerizeService.queryMinLimit(msDrisk_no, treatyYear, msPolicyDEND, msPolicyDBGN, msPolicyPRT);
													} catch (Exception e) {
														log.debug(e.toString());
														Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
														savelog(taskId, "????????????????????????-09, ??????????????????????????????");
														Quit_For_Flag1 = true;
														break;
													}
													if(moMinLimit.isEmpty()) {
														savelog(taskId, "????????????????????????, ???????????????????????????");
														Quit_For_Flag1 = true;
														break;
													}else {
														if (moMinLimit.get(0) == null || moMinLimit.get(0).getMinLimit() == null) {
															msMinLimit = Long.parseLong("0");
														}else {
															msMinLimit = moMinLimit.get(0).getMinLimit();
														}
														if(msMinLimit == 0) {
															msMinLimit = msDlimit;
														}
													}
												}else {
													// ??????
													msMinLimit = msDlimit;
												}
												
												writeFile(nowTime, "===>..........  ??????????????????" + msMinLimit + "  ??????????????????" + msDlimit, taskId, "N");
												
												// 07-2.????????????????????????????????????????????????
												if(msDlimit > msMinLimit) {
													msPlyLimit = msMinLimit;
												}else {
													msPlyLimit = msDlimit;
												}
												
												// 07-3.??????????????????
												msRefLimit = msPlyLimit;
												// ???code?????????
//	                                             RefTreaty(l).treaty_year = msOtreaty_year
//	                                             RefTreaty(l).treaty_no = msOtreaty_no
//	                                             RefTreaty(l).limit = msPlyLimit
												
												// 07-4.???????????????
												if("1".equals(msOshare_type)) {
													// ??????
													// msPlyLimit = msPlyLimit * msOshare_rate / 100
													msPlyLimit = new BigDecimal(msPlyLimit).multiply(msOshare_rate).divide(BHUNDRED).longValue();
												}else {
													// ??????
													// msPlyLimit = msPlyLimit * msOshare_rate
													msPlyLimit = new BigDecimal(msPlyLimit).multiply(msOshare_rate).longValue();
												}
												
												if("FRXL".equals(msOtreaty_no)) {
													msPlyLimit = msOlimit_general;
												}
												
												writeFile(nowTime, "===>..........   ????????????>>>>" + msPlyLimit, taskId, "N");
												
												// 07-5.????????????????????? (???????????????)
												if("1".equals(msOlimit_base)) {
													// ??????
													Rin1204VOReq3 rin1204VOReq3 = new Rin1204VOReq3();
													rin1204VOReq3.setTreatyYear(treatyYear);
													rin1204VOReq3.setTreatyNo(msOtreaty_no);
													rin1204VOReq3.setRiskNo(msDrisk_no);
													rin1204VOReq3.setPolicyDEND(msPolicyDEND);
													rin1204VOReq3.setPolicyDBGN(msPolicyDBGN);
													rin1204VOReq3.setPolicyPRT(msPolicyPRT);
													rin1204VOReq3.setOldPolicyList(oldPolicyList);
													rin1204VOReq3.setOldPolicy(msOldPolicy);
													rin1204VOReq3.setNextPolicy(nextPolicy);
													List<Rin1204VOResp5> moShareAmt3;
													Map<String, Object> map3 = new HashMap<>();
													map3.put("record", rin1204VOReq3);
												    msSQL = getSQL(mybatisConfig, map3, "queryMoShareAmt3");
													try {
														moShareAmt3 = customerizeService.queryMoShareAmt3(rin1204VOReq3);
													} catch (Exception e) {
														log.debug(e.toString());
														Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
														savelog(taskId, "????????????????????????-08, ??????????????????????????????");
														Quit_For_Flag1 = true;
														break;
													}
													if(moShareAmt3.isEmpty()) {
														msShareAmt = BigDecimal.ZERO;
													}else {
														if (moShareAmt3.get(0) == null || moShareAmt3.get(0).getShareAmt() == null) {
															msShareAmt = BigDecimal.ZERO;
														}else {
															msShareAmt = new BigDecimal(moShareAmt3.get(0).getShareAmt());
														}
														if(msShareAmt.compareTo(BigDecimal.ZERO) < 0) {
															msShareAmt = BigDecimal.ZERO;
														}
													}
												}else {
													// ??????
													msShareAmt = BigDecimal.ZERO;
												}
												
												// 07-5.????????????????????? (Total)
												calc_amt = msDamt - msFACAmt;
												
												writeFile(nowTime, "===>..........  ???????????????" + msShareAmt + "  Calc_amt" + calc_amt + "  " + msSQL, taskId, "N");
												
												// 07-6.?????????????????????????????? (Treaty)
												if("1".equals(msOshare_type)) {
													// ????????????
													// msPlyShareAmt = Calc_amt * msOshare_rate / 100
													msPlyShareAmt = new BigDecimal(calc_amt).multiply(msOshare_rate).divide(BHUNDRED);
												}else {
													// ????????????
													msPlyShareAmt = msTotShareAmt;
												}
												writeFile(nowTime,
														"1:msPlyShareAmt=" + msPlyShareAmt + "  msOshare_type="
																+ msOshare_type + "  Calc_amt=" + calc_amt
																+ "  msOshare_rate=" + msOshare_rate + "  msPlyLimit="
																+ msPlyLimit + "  msShareAmt=" + msShareAmt, taskId, "N");
												writeFile(nowTime, "===>..........  msPlyShareAmt" + msPlyShareAmt
														+ "  msPlyLimit=" + msPlyLimit + "  msShareAmt=" + msShareAmt, taskId, "N");
												
												// 07-7.??????????????????????????????
												if (msPlyShareAmt.compareTo(new BigDecimal(msPlyLimit)) > 0) {
													msPlyShareAmt = new BigDecimal(msPlyLimit);
												}
												
												// 07-8.???????????????????????????????????????????????????????????????
												if (msPlyShareAmt.compareTo(
														new BigDecimal(msPlyLimit).subtract(msShareAmt)) > 0) {
													if (new BigDecimal(msPlyLimit).subtract(msShareAmt)
															.compareTo(BigDecimal.ZERO) > 0) {
														msPlyShareAmt = new BigDecimal(msPlyLimit).subtract(msShareAmt);
													}else {
														msPlyShareAmt = BigDecimal.ZERO;
													}
												}
												
												writeFile(nowTime,
														"===>..........  msPlyShareAmt" + msPlyShareAmt
																+ "  msotreaty_mode=" + msOtreaty_mode + "  msPlyLimit="
																+ msPlyLimit + "  msShareAmt=" + msShareAmt, taskId, "N");
												writeFile(nowTime, "5:msPlyLimit=" + msPlyLimit + "  msTotShareAmt="
														+ msTotShareAmt, taskId, "N");
												                                            
												// 07-9.[change_flag <> X]??? ??????????????????
												if(!"X".equals(StringUtils.upperCase(msChangeFlag.trim()))) {
													if (msCoinsRate.compareTo(BHUNDRED) != 0) {
														if(msCoinsRate.compareTo(msOcoins_rate) < 0) {
															writeFile(nowTime,
																	"4.4: msCoinsRate=" + msCoinsRate
																			+ "  msOcoins_rate=" + msOcoins_rate
																			+ "  msOlimit_general=" + msOlimit_general, taskId, "N");
															// ???????????????????????????????????? msShareAmt
															// ????????????msCoinShareTotAmt???????????????????????????????????????(msTREAmt)?????????????????????
															msShareAmt1 = new BigDecimal(msCoinShareTotAmt + msTREAmt);
															writeFile(nowTime,
																	"4.3:msPlyLimit=" + msPlyLimit + "  msTotShareAmt="
																			+ msTotShareAmt + "  msShareAmt1="
																			+ msShareAmt1 + "  msOlimit_total="
																			+ msOlimit_total + "  msCoinShareTotAmt="
																			+ msCoinShareTotAmt + "  msTREAmt="
																			+ msTREAmt, taskId, "N");
															
															if("1".equals(msOtreaty_mode)) {
																writeFile(nowTime,
																		"4.2.1:msPlyLimit=" + msPlyLimit
																				+ "  msTotShareAmt=" + msTotShareAmt
																				+ "  msShareAmt1=" + msShareAmt1
																				+ "  msOlimit_total=" + msOlimit_total
																				+ "  RetainAmt=" + retainAmt, taskId, "N");
																//  If (Round(msShareAmt1, 0) - Round(RetainAmt, 0)) > 0 Then
																if (msShareAmt1.round(new MathContext(0))
																		.subtract(retainAmt.round(new MathContext(0)))
																		.compareTo(BigDecimal.ZERO) > 0) {
																	if (new BigDecimal(msPlyLimit).add(msShareAmt1)
																			.compareTo(new BigDecimal(
																					msOlimit_total)) > 0) {
																		msPlyLimit = new BigDecimal(msOlimit_total).subtract(msShareAmt1).longValue();
																		writeFile(nowTime, "4.2:msPlyLimit="
																				+ msPlyLimit + "  msTotShareAmt="
																				+ msTotShareAmt + "  msShareAmt1="
																				+ msShareAmt1 + "  msOlimit_total="
																				+ msOlimit_total, taskId, "N");
																	}else {
																		writeFile(nowTime, "4.1.1:msPlyLimit="
																				+ msPlyLimit + "  msTotShareAmt="
																				+ msTotShareAmt + "  msShareAmt1="
																				+ msShareAmt1 + "  msOlimit_total="
																				+ msOlimit_total, taskId, "N");
																	}
																}
															}else {
																if (msPlyLimit
																		+ msShareAmt1.longValue() > msOlimit_total) {
																	msPlyLimit = msOlimit_total
																			- msShareAmt1.longValue();
																}
																writeFile(nowTime, "4.1:msPlyLimit=" + msPlyLimit
																		+ "  msTotShareAmt=" + msTotShareAmt, taskId, "N");
															}
															
														}
														if(msPlyLimit < 0) {
															msPlyLimit = Long.parseLong("0");
														}
														writeFile(nowTime, "4:msPlyLimit=" + msPlyLimit
																+ "  msTotShareAmt=" + msTotShareAmt, taskId, "N");
													}else {
														if(msPlyLimit > msOlimit_general) {
															msPlyLimit = msOlimit_general;
														}
														writeFile(nowTime, "3:msPlyLimit=" + msPlyLimit
																+ "  msTotShareAmt=" + msTotShareAmt, taskId, "N");
													}
												}
												
												writeFile(nowTime,
														"===>..........  ????????????" + msPlyLimit + "  ?????????" + msPlyShareAmt, taskId, "N");
												// 07-10.??? msPlyLimit ????????????????????????
												if(msPlyShareAmt.longValue() > msPlyLimit) {
													msPlyShareAmt = new BigDecimal(msPlyLimit);
													writeFile(nowTime, "2:msPlyShareAmt=" + msPlyShareAmt, taskId, "N");
												}
												if(msPlyShareAmt.compareTo(msTotShareAmt) > 0) {
													msPlyShareAmt = msTotShareAmt;
													writeFile(nowTime, "1:msPlyShareAmt=" + msPlyShareAmt, taskId, "N");
												}
												// msPlyShareAmt = CDbl(FormatNumber(msPlyShareAmt, 0))
												msPlyShareAmt = msPlyShareAmt.setScale(0, RoundingMode.HALF_UP);
												
												writeFile(nowTime, "===>******************1  " + msOtreaty_no + "??????" + msPlyShareAmt, taskId, "N");
												// 07-11.?????????????????????
												Rin1204VOReq2 rin1204VOReq2 = new Rin1204VOReq2();
												rin1204VOReq2.setMsDpolicyNo(msDpolicy_no);
												rin1204VOReq2.setMsDendorseNo(msDendorse_no);
												rin1204VOReq2.setMsDaddrNo(msDaddr_no);
												rin1204VOReq2.setMsTreatyYear(treatyYear);
												rin1204VOReq2.setMsCtreatyNo(msOtreaty_no);
												rin1204VOReq2.setMsDriskNo(msDrisk_no);
												rin1204VOReq2.setMsPolicyDBGN(msPolicyDBGN);
												rin1204VOReq2.setMsPolicyDEND(msPolicyDEND);
												rin1204VOReq2.setMsCacctType(msOacct_type);
												rin1204VOReq2.setMsShareAmt(msShareAmt);
												rin1204VOReq2.setMsPlyShareAmt(msPlyShareAmt);
												rin1204VOReq2.setMsDamt(msDamt);
												rin1204VOReq2.setMsDprem(msDprem);
												rin1204VOReq2.setMsDamtTyp(msDamt_typ);
												rin1204VOReq2.setMsDpremTyp(msDprem_typ);
												rin1204VOReq2.setMsDamtEar(msDamt_ear);
												rin1204VOReq2.setMsDpremEar(msDprem_ear);
												// Fix(msPlyShareAmt)
												rin1204VOReq2.setRetainAmt(msPlyShareAmt.setScale(0, RoundingMode.DOWN));
												// Fix(msDprem * (msPlyShareAmt / msDamt))
												rin1204VOReq2.setRetainPrem(new BigDecimal(msDprem)
														.multiply(msPlyShareAmt.divide(new BigDecimal(msDamt), 4, RoundingMode.HALF_UP))
														.setScale(0, RoundingMode.DOWN));
												rin1204VOReq2.setMsCtreatyMode(msOtreaty_mode);
												rin1204VOReq2.setNow(util.processDateToString(new Date(), "yyyy/MM/dd HH:mm:ss"));
												rin1204VOReq2.setMsPolicyPRT(msPolicyPRT);
												// ?????????????????????
												try {
													customerizeService.insertFriTreatyShares4(customerizeMapper, rin1204VOReq2);
												} catch (Exception e) {
													log.debug(e.toString());
													Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
													savelog(taskId, msPolicyNo + "-" + msEndorseNo + "?????????" + msDrisk_no + "????????????????????????3, ??????????????????????????????");
													Quit_For_Flag1 = true;
													break;
												}
												sqlSession.commit();
												// ????????? taskID ??? TABLE batchqueue ?????????????????????"3"??????????????????????????????????????????????????? "3"
												if("3".equals(getProcessStatus(taskId, batchqueueService))) {
													writeFile(nowTime, "", taskId, "Y");
													return "3";
												}
												msTREAmt = new BigDecimal(msTREAmt).add(msPlyShareAmt).longValue();
											}
											
										}else {
											if(Boolean.TRUE.equals(mbInLoop)) {
												mbQuit = true;
											}
										}
										
										if(Boolean.TRUE.equals(mbQuit)) {
											break;
										}
									}
									if(Boolean.TRUE.equals(Quit_For_Flag1)) {
										if (k == shareDetailList.size() - 1) {
											afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
											sqlSession.commit();
											return "";
										} else {
											continue;
										}
									}
									// [5.5.3.1.3]?????????2??????(SEC_Retain)    08. ======== Calc Share amt, prem of SEC_Retain Treaty  ========
									// Calc Treaty For SEC Retain    Step 08
									
									if(msDamt > (msFACAmt + msTREAmt)) {
										// ??????????????????, ????????????????????????
										if(!"N".equals(msCalcFlag)) {
											savelog(taskId, msPolicyNo + "-" + msEndorseNo + "?????????" + msDrisk_no + "-" + msDaddr_no + "????????????" + (msDamt - msFACAmt - msTREAmt) + "?????????");
										}
										retainAmt =  new BigDecimal(msDamt - msFACAmt - msTREAmt);
										retainPrem = new BigDecimal(msDprem)
												.multiply(retainAmt.divide(new BigDecimal(msDamt), 4, RoundingMode.HALF_UP));
										
										Rin1204VOReq2 rin1204VOReq2 = new Rin1204VOReq2();
										rin1204VOReq2.setMsDpolicyNo(msDpolicy_no);
										rin1204VOReq2.setMsDendorseNo(msDendorse_no);
										rin1204VOReq2.setMsDaddrNo(msDaddr_no);
										rin1204VOReq2.setMsTreatyYear(treatyYear);
										rin1204VOReq2.setMsDriskNo(msDrisk_no);
										rin1204VOReq2.setMsPolicyDBGN(msPolicyDBGN);
										rin1204VOReq2.setMsPolicyDEND(msPolicyDEND);
										rin1204VOReq2.setRetainAmt(retainAmt);
										rin1204VOReq2.setMsDamt(msDamt);
										rin1204VOReq2.setMsDprem(msDprem);
										rin1204VOReq2.setMsDamtTyp(msDamt_typ);
										rin1204VOReq2.setMsDpremTyp(msDprem_typ);
										rin1204VOReq2.setMsDamtEar(msDamt_ear);
										rin1204VOReq2.setMsDpremEar(msDprem_ear);
										rin1204VOReq2.setRetainAmt(retainAmt);
										rin1204VOReq2.setRetainPrem(retainPrem);
										rin1204VOReq2.setNow(util.processDateToString(new Date(), "yyyy/MM/dd HH:mm:ss"));
										rin1204VOReq2.setMsPolicyPRT(msPolicyPRT);
//										// ?????????????????????
										try {
											customerizeService.insertFriTreatyShares5(customerizeMapper, rin1204VOReq2);
										} catch (Exception e) {
											log.debug(e.toString());
											Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
											savelog(taskId, msPolicyNo + "-" + msEndorseNo + "?????????" + msDrisk_no + "????????????????????????????????????, ??????????????????????????????");
										}
										sqlSession.commit();
										// ????????? taskID ??? TABLE batchqueue ?????????????????????"3"??????????????????????????????????????????????????? "3"
										if("3".equals(getProcessStatus(taskId, batchqueueService))) {
											writeFile(nowTime, "", taskId, "Y");
											return "3";
										}
									}
									// [5.5.3.2]???????????????
								}else {
									// ??????
									// [5.5.3.2.1]????????????????????????(???->???)   Get Treaty Share Order
									msQSamt = BigDecimal.ZERO;
									msTREAmt = Long.parseLong("0");
									
									List<Rin1204VOResp1> moTreatyOrder;
									Map<String, Object> map3 = new HashMap<>();
									map3.put("treatyYear", treatyYear);
									map3.put("policyType", msPolicyType);
								    msSQL = getSQL(mybatisConfig, map3, "queryMoTreatyOrder");
									writeFile(nowTime, "????????????????????????1: " + msSQL, taskId, "N");
									try {
										moTreatyOrder = customerizeService.queryMoTreatyOrder(treatyYear, msPolicyType);
									} catch (Exception e) {
										log.debug(e.toString());
										Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
										savelog(taskId, "????????????????????????-20, ??????????????????????????????");
										if (k == shareDetailList.size() - 1) {
											afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
											sqlSession.commit();
											return "";
										} else {
											continue;
										}
									}
									
									// ??????????????????????????????????????????SQL --> ??????????????????VB????????????JAVA????????????
									// WriteFile "????????????????????????2: " & msSQL
									// moTreatyOrder = customerizeService.queryMoTreatyOrder(treatyYear, msPolicyType);
									if(moTreatyOrder.isEmpty() && !"S".equals(msPolicyType)) {
										savelog(taskId, "?????????????????????????????????????????????, ?????????????????????????????????2");
										if (k == shareDetailList.size() - 1) {
											afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
											sqlSession.commit();
											return "";
										} else {
											continue;
										}
									}
									
									// [5.5.3.2.2]????????????FFQSQ, FFQSS, FRXL?????????   If Policy Treaty Order Exist Continue
									if(!moTreatyOrder.isEmpty()) {
										for (Rin1204VOResp1 treatyOrder : moTreatyOrder) {
											Quit_For_Flag1 = false;
											msOtreaty_year = nullToSpace(treatyOrder.getTreatyYear());
											msOtreaty_no = nullToSpace(treatyOrder.getTreatyNo());
											msOacct_type = nullToSpace(treatyOrder.getAcctType());
											msOtreaty_mode = nullToSpace(treatyOrder.getTreatyMode());
											msOlimit_base = nullToSpace(treatyOrder.getLimitBase());
											msOshare_type = nullToSpace(treatyOrder.getShareType());
											msOshare_rate = nullToZero(treatyOrder.getShareRate());
											msOlimit_general = nullToZero(treatyOrder.getLimitGeneral());
											msOlimit_total = nullToZero(treatyOrder.getLimitTotal());
											msOretain_times = nullToZero(treatyOrder.getRetainTimes());
											msOref_treaty_no = nullToSpace(treatyOrder.getRefTreatyNo());
											msOcoins_rate = nullToZero(treatyOrder.getCoinsRate());
											
											// ??? Notice02???==>   ?????????????????????????????????????????????  If Need Process Modify Here Add Check fri_treaty_exclude
											
											// ???????????????=????????????-???????????????
											msTotShareAmt = new BigDecimal(msDamt - msFACAmt - msTREAmt);
											writeFile(nowTime,
													"===>..........  ??????--???????????????" + msTotShareAmt + "(msDamt=" + msDamt
															+ ", msFACAmt=" + msFACAmt + ",msTREAmt=" + msTREAmt + ")", taskId, "N");
											
											if(billedCheck(msDpolicy_no, msDendorse_no, msDaddr_no, msOtreaty_year, msOtreaty_no, taskId, nowTime) == true) {
												// 1972
												continue;
											}
											if (msTotShareAmt.compareTo(BigDecimal.ZERO) <= 0) {
												// Calc Same Risk Amt ??????????????????????????????
												List<Rin1204VOResp5> moShareAmt4 = new ArrayList<>();
												if("1".equals(msOlimit_base)) {
													// ??????
													Rin1204VOReq3 rin1204VOReq3 = new Rin1204VOReq3();
													rin1204VOReq3.setTreatyYear(treatyYear);
													rin1204VOReq3.setTreatyNo(msOtreaty_no);
													rin1204VOReq3.setRiskNo(msDrisk_no);
													rin1204VOReq3.setPolicyDEND(msPolicyDEND);
													rin1204VOReq3.setPolicyDBGN(msPolicyDBGN);
													rin1204VOReq3.setOldPolicyList(oldPolicyList);
													rin1204VOReq3.setOldPolicy(msOldPolicy);
													rin1204VOReq3.setNextPolicy(nextPolicy);
													Map<String, Object> map4 = new HashMap<>();
													map4.put("record", rin1204VOReq3);
												    msSQL = getSQL(mybatisConfig, map4, "queryMoShareAmt4");
													try {
														moShareAmt4 = customerizeService.queryMoShareAmt4(rin1204VOReq3);
													} catch (Exception e) {
														log.debug(e.toString());
														Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
														savelog(taskId, "????????????????????????-21, ??????????????????????????????");
														Quit_For_Flag1 = true;
				                                        break;
													}
												}else {
													// ??????
													Rin1204VOReq3 rin1204VOReq3 = new Rin1204VOReq3();
													rin1204VOReq3.setTreatyYear(treatyYear);
													rin1204VOReq3.setPolicyPRT(msPolicyPRT);
													rin1204VOReq3.setTreatyNo(msOtreaty_no);
													rin1204VOReq3.setPolicyNo(msDpolicy_no);
													rin1204VOReq3.setEndorseNo(msDendorse_no);
													rin1204VOReq3.setAddrNo(msDaddr_no);
													Map<String, Object> map4 = new HashMap<>();
													map4.put("record", rin1204VOReq3);
												    msSQL = getSQL(mybatisConfig, map4, "queryMoShareAmt5");
													try {
														moShareAmt4 = customerizeService.queryMoShareAmt5(rin1204VOReq3);
													} catch (Exception e) {
														log.debug(e.toString());
														Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
														savelog(taskId, "????????????????????????-21, ??????????????????????????????");
														Quit_For_Flag1 = true;
				                                        break;
													}
												}
												if(moShareAmt4.isEmpty()) {
													msShareAmt = BigDecimal.ZERO;
												}else {
													if (moShareAmt4.get(0) == null || moShareAmt4.get(0).getShareAmt() == null) {
														msShareAmt = BigDecimal.ZERO;
													}else {
														msShareAmt = new BigDecimal(moShareAmt4.get(0).getShareAmt());
													}
												}
												
												writeFile(nowTime, "===>..........  ??????" + msOtreaty_no + "???????????????"
														+ msShareAmt + "   " + msSQL, taskId, "N");
												// 2028
												// Calc Policy Share In This Treaty ??????????????????????????????
												if("1".equals(msOtreaty_mode)) {
													// Q/S
													if("1".equals(msOshare_type)) {
														// ????????????
														// msPlyShareAmt = msTotShareAmt * msOshare_rate / 100
														msPlyShareAmt = msTotShareAmt.multiply(msOshare_rate).divide(BHUNDRED);
													}else {
														// ????????????
														// msPlyShareAmt = msTotShareAmt * (msOshare_rate / (1 + msOshare_rate))
														msPlyShareAmt = msPlyShareAmt.multiply(msOshare_rate
																.divide(BigDecimal.ONE.add(msOshare_rate), 4, RoundingMode.HALF_UP));
													}
													if(msPlyShareAmt.add(msShareAmt)
															.compareTo(BigDecimal.ZERO) <= 0) {
														if (msPlyShareAmt.compareTo(
																msShareAmt.multiply(new BigDecimal("-1"))) <= 0) {
															msPlyShareAmt = new BigDecimal("-1").multiply(msShareAmt);
														}
													}
													if (msTotShareAmt.compareTo(BigDecimal.ZERO) == 0) {
														msPlyShareAmt = BigDecimal.ZERO;
													}
													// 2049
													if("1".equals(msOshare_type)) {
														// ????????????
														// msQSamt = msPlyShareAmt / (msOshare_rate / 100)
														msQSamt = msPlyShareAmt
																.divide(msOshare_rate.divide(BHUNDRED), 4, RoundingMode.HALF_UP);
													}else {
														// ????????????
														// msQSamt = msPlyShareAmt / (msOshare_rate / (1 + msOshare_rate))
														msQSamt = msPlyShareAmt.divide(msOshare_rate
																.divide(BigDecimal.ONE.add(msOshare_rate), 4, RoundingMode.HALF_UP));
													}
												}else {
													// Not Q/S
													if (msTotShareAmt.add(msShareAmt)
															.compareTo(BigDecimal.ZERO) > 0) {
														msPlyShareAmt = msTotShareAmt;
													}else {
														// ???????????????<0, ???????????????????????????
														if(msShareAmt.compareTo(BigDecimal.ZERO) < 0) {
															msPlyShareAmt = BigDecimal.ZERO;
														} else if (msTotShareAmt.compareTo(
																msShareAmt.multiply(new BigDecimal("-1"))) <= 0) {
															msPlyShareAmt = new BigDecimal("-1").multiply(msShareAmt);
														}
													}
													if(msTotShareAmt.compareTo(BigDecimal.ZERO) == 0) {
														msPlyShareAmt = BigDecimal.ZERO;
													}
												}
												// ????????????????????????????????????
												if (msShareAmt.compareTo(BigDecimal.ZERO) == 0) {
													msPlyShareAmt = BigDecimal.ZERO;
												}
//													msPlyShareAmt = Fix(msPlyShareAmt)
												msPlyShareAmt = msPlyShareAmt.setScale(0, RoundingMode.DOWN);
												writeFile(nowTime, "===>******************1  ????????????" + msPlyShareAmt, taskId, "N");
												// EDIT?????????????????????
												Rin1204VOReq2 rin1204VOReq2 = new Rin1204VOReq2();
												rin1204VOReq2.setMsDpolicyNo(msDpolicy_no);
												rin1204VOReq2.setMsDendorseNo(msDendorse_no);
												rin1204VOReq2.setMsDaddrNo(msDaddr_no);
												rin1204VOReq2.setMsTreatyYear(treatyYear);
												rin1204VOReq2.setMsCtreatyNo(msOtreaty_no);
												rin1204VOReq2.setMsDriskNo(msDrisk_no);
												rin1204VOReq2.setMsPolicyDBGN(msPolicyDBGN);
												rin1204VOReq2.setMsPolicyDEND(msPolicyDEND);
												rin1204VOReq2.setMsCacctType(msOacct_type);
												rin1204VOReq2.setMsShareAmt(msShareAmt);
												rin1204VOReq2.setMsPlyShareAmt(msPlyShareAmt);
												rin1204VOReq2.setMsDamt(msDamt);
												rin1204VOReq2.setMsDprem(msDprem);
												rin1204VOReq2.setMsDamtTyp(msDamt_typ);
												rin1204VOReq2.setMsDpremTyp(msDprem_typ);
												rin1204VOReq2.setMsDamtEar(msDamt_ear);
												rin1204VOReq2.setMsDpremEar(msDprem_ear);
												rin1204VOReq2.setRetainAmt(msPlyShareAmt);
												// Fix(msDprem * (msPlyShareAmt / msDamt))
												rin1204VOReq2.setRetainPrem(new BigDecimal(msDprem)
														.multiply(msPlyShareAmt.divide(new BigDecimal(msDamt), 4, RoundingMode.HALF_UP))
														.setScale(0, RoundingMode.DOWN));
												rin1204VOReq2.setMsCtreatyMode(msOtreaty_mode);
												rin1204VOReq2.setNow(util.processDateToString(new Date(), "yyyy/MM/dd HH:mm:ss"));
												rin1204VOReq2.setMsPolicyPRT(msPolicyPRT);
												try {
													customerizeService.insertFriTreatyShares4(customerizeMapper, rin1204VOReq2);
												} catch (Exception e) {
													log.debug(e.toString());
													Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
													savelog(taskId, msPolicyNo + "-" + msEndorseNo + "?????????" + msDrisk_no + "????????????????????????4, ??????????????????????????????");
													Quit_For_Flag1 = true;
			                                        break;
												}
												sqlSession.commit();
												// ????????? taskID ??? TABLE batchqueue ?????????????????????"3"??????????????????????????????????????????????????? "3"
												if("3".equals(getProcessStatus(taskId, batchqueueService))) {
													writeFile(nowTime, "", taskId, "Y");
													return "3";
												}
												
												msTREAmt = new BigDecimal(msTREAmt).add(msPlyShareAmt).longValue();
											}
										}
									}
									
									// 2125
									if(Boolean.TRUE.equals(Quit_For_Flag1)) {
										if (k == shareDetailList.size() - 1) {
											afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
											sqlSession.commit();
											return "";
										} else {
											continue;
										}
									}
			                        // ???????????????=????????????-???????????????
									msTotShareAmt = new BigDecimal(msDamt - msFACAmt - msTREAmt);
									// [5.5.3.2.3]????????????QS?????????(?????????=N?????? SEC_Retain, ????????? Retain)     Get Treaty Share Order ========  '???????????????
			                        if(billedCheck(msDpolicy_no, msDendorse_no, msDaddr_no, treatyYear, "Retain", taskId, nowTime) == true) {
			                        	retainAmt = BigDecimal.ZERO;
			                            retainPrem = BigDecimal.ZERO;
			                        }else {
			                        	List<Rin1204VOResp8> moRetain;
										try {
											moRetain = customerizeService.querymMoRetain2(treatyYear, msPolicyType);
										} catch (Exception e) {
											log.debug(e.toString());
											Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
											savelog(taskId, "????????????????????????-22, ??????????????????????????????");
											if (k == shareDetailList.size() - 1) {
												afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
												sqlSession.commit();
												return "";
											} else {
												continue;
											}
										}
			                        	if(moRetain.isEmpty()) {
			                        		QSshare_rate = BigDecimal.ZERO;
			                        	}else {
			                        		if (moRetain.get(0) == null || moRetain.get(0).getExShareRate() == null) {
			                        			QSshare_rate = BigDecimal.ZERO;
			                        		}else {
			                        			QSshare_rate = moRetain.get(0).getExShareRate();
			                        		}
			                        	}
			                        	// RetainAmt = msQSamt * (100 - QSshare_rate) / 100
										retainAmt = msQSamt.multiply(BHUNDRED.subtract(QSshare_rate))
												.divide(BHUNDRED);
			                        	
										// ???????????????, ????????????SEC_Retain
										if("N".equals(msCalcFlag)) {
											retType = "SEC_Retain";
											retainAmt = new BigDecimal(msDamt);
										}else {
											// ????????? Retain
											retType = "Retain";
											if("S".equals(msPolicyType)) {
												retainAmt = new BigDecimal(msDamt);
											}
										}
										
										if (new BigDecimal(msDamt).subtract(msQSamt)
												.compareTo(new BigDecimal("-1")) == 0) {
											retainAmt = retainAmt.subtract(BigDecimal.ONE); // ???????????? [????????????????????????1??????????????????]
										}
										
										if (msTotShareAmt.compareTo(BigDecimal.ZERO) == 0) {
											retainAmt = BigDecimal.ZERO;
										}
										
										// RetainAmt = CDbl(FormatNumber(RetainAmt, 0))
										retainAmt = retainAmt.setScale(0, RoundingMode.HALF_UP);
										// RetainPrem = CDbl(FormatNumber(msDprem * (RetainAmt / msDamt), 0))
										retainPrem = new BigDecimal(msDprem).multiply(
												retainAmt.divide(new BigDecimal(msDamt), 4, RoundingMode.HALF_UP))
												.setScale(0, RoundingMode.HALF_UP);
										
										writeFile(nowTime, "===>..........  ??????--????????????" + retainAmt, taskId, "N");
										
										// EDIT?????????????????????
										Rin1204VOReq2 rin1204VOReq2 = new Rin1204VOReq2();
										rin1204VOReq2.setMsDpolicyNo(msDpolicy_no);
										rin1204VOReq2.setMsDendorseNo(msDendorse_no);
										rin1204VOReq2.setMsDaddrNo(msDaddr_no);
										rin1204VOReq2.setMsTreatyYear(treatyYear);
										rin1204VOReq2.setMsCtreatyNo(retType);
										rin1204VOReq2.setMsDriskNo(msDrisk_no);
										rin1204VOReq2.setMsPolicyDBGN(msPolicyDBGN);
										rin1204VOReq2.setMsPolicyDEND(msPolicyDEND);
										rin1204VOReq2.setMsCacctType("0");
										rin1204VOReq2.setMsShareAmt(msShareAmt);
										rin1204VOReq2.setMsPlyShareAmt(msPlyShareAmt);
										rin1204VOReq2.setMsDamt(msDamt);
										rin1204VOReq2.setMsDprem(msDprem);
										rin1204VOReq2.setMsDamtTyp(msDamt_typ);
										rin1204VOReq2.setMsDpremTyp(msDprem_typ);
										rin1204VOReq2.setMsDamtEar(msDamt_ear);
										rin1204VOReq2.setMsDpremEar(msDprem_ear);
										rin1204VOReq2.setRetainAmt(retainAmt);
										rin1204VOReq2.setRetainPrem(retainPrem);
										rin1204VOReq2.setMsCtreatyMode("0");
										rin1204VOReq2.setNow(util.processDateToString(new Date(), "yyyy/MM/dd HH:mm:ss"));
										rin1204VOReq2.setMsPolicyPRT(msPolicyPRT);
										try {
											customerizeService.insertFriTreatyShares4(customerizeMapper, rin1204VOReq2);
										} catch (Exception e) {
											log.debug(e.toString());
											Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
											savelog(taskId, msPolicyNo + "-" + msEndorseNo + "?????????" + msDrisk_no + "????????????????????????5, ??????????????????????????????");
											if (k == shareDetailList.size() - 1) {
												afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
												sqlSession.commit();
												return "";
											} else {
												continue;
											}
										}
										 sqlSession.commit();
										// ????????? taskID ??? TABLE batchqueue ?????????????????????"3"??????????????????????????????????????????????????? "3"
										if("3".equals(getProcessStatus(taskId, batchqueueService))) {
											writeFile(nowTime, "", taskId, "Y");
											return "3";
										}
										// 2237
			                        }
									
									msTREAmt = new BigDecimal(msTREAmt).add(retainAmt).longValue();
									// ??????-????????????
									if(msDamt != msFACAmt + msTREAmt) {
										savelog(taskId, msPolicyNo + "-" + msEndorseNo + "?????????" + msDrisk_no + "-" + msDaddr_no + "????????????" + (msDamt - msFACAmt - msTREAmt) + "?????????");
									}
								}
							}
	                    }
	                    
					}
				}
				
				writeFile(nowTime, "policy_no:" + msPolicyNo + "---endorse_no:" + msEndorseNo + "--addr_no:"
						+ msDaddr_no + "--EndTime:" + util.processDateToString(new Date(), "yyyy/MM/dd HH:mm:ss"), taskId, "N");
				// ????????? taskID ??? TABLE batchqueue ?????????????????????"3"??????????????????????????????????????????????????? "3"
				if("3".equals(getProcessStatus(taskId, batchqueueService))) {
					writeFile(nowTime, "", taskId, "Y");
					return "3";
				}
			}
			// loop2
			afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
			
			sqlSession.commit();
			// ????????? taskID ??? TABLE batchqueue ?????????????????????"3"??????????????????????????????????????????????????? "3"
			if("3".equals(getProcessStatus(taskId, batchqueueService))) {
				writeFile(nowTime, "", taskId, "Y");
				return "3";
			}
			
		} catch (Exception e) {
			sqlSession.rollback();
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			throw e;
		}finally {
			sqlSession.close();
		}

		return "";
	}

	private String getProcessStatus(String taskId, BatchqueueService batchqueueService) throws Exception {
		Batchqueue model = new Batchqueue();
		model.setTaskid(taskId);
		model.setBatchid("Rin1204");
		List<Batchqueue> batchqueueList = batchqueueService.queryByBatchqueue(model);
		if(!batchqueueList.isEmpty() && batchqueueList.get(0).getProcessstatus()==3) {
			return "3";
		}else {
			return "";
		}
	}

	private String nullToZero(String strVal) {
		if(strVal == null) {
			return "0";
		}else {
			return strVal;
		}
	}

	private BigDecimal nullToZero(BigDecimal bigDecimalVal) {
		if(bigDecimalVal == null) {
			return BigDecimal.ZERO;
		}else {
			return bigDecimalVal;
		}
	}

	private Long nullToZero(Long longVal) {
		if(longVal == null) {
			return 0l;
		}else {
			return longVal;
		}
	}

	private Short nullToZero(Short shortVal) {
		if(shortVal == null) {
			return 0;
		}else {
			return shortVal;
		}
	}

	private String nullToSpace(String strVal) {
		if(strVal == null) {
			return "";
		}else {
			return strVal;
		}
	}

	private String getSQL(Configuration mybatisConfig, Map<String, Object> paramMap, String mapperId) {
		String preNamespace = "com.asi.huanan.service.dao.mybatis.mapper.customerize.CustomerizeMapper";
		BoundSql boundSql = mybatisConfig.getMappedStatement(preNamespace + "." + mapperId).getBoundSql(paramMap);
		String sql = boundSql.getSql();
		MappedStatement mappedStatement = mybatisConfig.getMappedStatement(preNamespace + "." + mapperId);
		TypeHandlerRegistry typeHandlerRegistry = mappedStatement.getConfiguration().getTypeHandlerRegistry();
		List<ParameterMapping> boundParams = boundSql.getParameterMappings();
		for(ParameterMapping param : boundParams) {
		    if(param.getMode() != ParameterMode.OUT) {
		    	 Object value;
		    	 String propertyName = param.getProperty();
		    	 if(boundSql.hasAdditionalParameter(propertyName)) {
		    		 value = boundSql.getAdditionalParameter(propertyName);
		    	 }else if (paramMap == null) {
                    value = null;
                } else if (typeHandlerRegistry.hasTypeHandler(paramMap.getClass())) {
                    value = paramMap;
                } else {
                    MetaObject metaObject = mybatisConfig.newMetaObject(paramMap);
                    value = metaObject.getValue(propertyName);
                }
		    	JdbcType jdbcType = param.getJdbcType();
		    	if (value == null && jdbcType == null) jdbcType = mybatisConfig.getJdbcTypeForNull();
		    	 sql = replaceParameter(sql, value, jdbcType, param.getJavaType());
		    }
		}		
		return sql;
	}
	
	/**
     * ????????????????????????
     *
     * ????????????????????????????????????????????????????????????????????????????????????????????????
     * @param sql
     * @param value
     * @param jdbcType
     * @param javaType
     * @return
     */
    private String replaceParameter(String sql, Object value, JdbcType jdbcType, Class<?> javaType) {
        String strValue = String.valueOf(value);
        if (jdbcType != null) {
            switch (jdbcType) {
                //??????

                case BIT:
                case TINYINT:
                case SMALLINT:
                case INTEGER:
                case BIGINT:
                case FLOAT:
                case REAL:
                case DOUBLE:
                case NUMERIC:
                case DECIMAL:
                    break;
                //??????

                case DATE:
                case TIME:
                case TIMESTAMP:
                    //??????????????????????????????????????????

                default:
                    strValue = "'" + strValue + "'";


            }
        } else if (Number.class.isAssignableFrom(javaType)) {
            //???????????????

        } else {
            strValue = "'" + strValue + "'";
        }
        return sql.replaceFirst("\\?", strValue);
    }

    // ??????????????????????????????(????????????: ?????????, ?????????, ???Exception???)
	private void writeFile(String nowTime, String msg, String taskId, String isWrite) {
		String oriFileContent = "";
//		String filein = msg + "\r\n";
		String filein = msg + "\n";
		stringBuilder.append(filein);
		try {
			if("Y".equals(isWrite)) {
				filein = stringBuilder.toString();
				// ????????????
				String path = SpringProperty.getLocalFilePath();
				
				File directory = new File(path);
				if(!directory.isFile()) {
					directory.mkdirs();
				}
				
				File file = new File(path + nowTime + "Rin1204Log.txt");
				// ?????????????????????????????????
				if (!file.exists()) {
					Boolean createNew = file.createNewFile();
					if(Boolean.TRUE.equals(createNew)) {
						log.debug("create file success");
					}
					// ?????? batchqueue BatchReprotAccessPath, ??????????????????
					batchqueueService.updatePathByTaskId(taskId, path + nowTime + "Rin1204Log.txt");
				}
				// ??????????????????????????? // TODO ??????????????????
//				Long filelength = file.length(); // ??????????????????
//				byte[] filecontent = new byte[filelength.intValue()];
//				oriFileContent = fileInput(file, filecontent);
				bufferWrite(file, oriFileContent, filein);
			}
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
		}
	}

	private void bufferWrite(File file, String oriFileContent, String filein) {
		try (BufferedWriter out = new BufferedWriter(new FileWriter(file));){
			out.write(oriFileContent + "\n" + filein);
			out.flush(); // ??????????????????????????????
			out.close(); // ????????????????????????				
		} catch (IOException e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
		}
		
	}

	private String fileInput(File file, byte[] filecontent) {
		try (FileInputStream in = new FileInputStream(file);) {
			int byteNum = in.read(filecontent);
			log.debug("read " + byteNum + "bytes");
			in.close();				
		} catch (IOException e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
		}
		return new String(filecontent);
	}

	private void savelog(String taskId, String msg) {
		try {
			Batchlog model = new Batchlog();
			model.setTaskid(taskId);
			model.setLogtime(new Date());
			model.setLogdescription(msg);
			batchlogService.insert(model);
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
		}
	}


	/**
	 * ?????????????????????
	 * @param msDpolicyNo
	 * @param msDendorseNo
	 * @param msDaddrNo
	 * @param treatyYear
	 * @param msOtreatyNo
	 * @return
	 */
	private boolean billedCheck(String msDpolicyNo, String msDendorseNo, Short msDaddrNo, String treatyYear,
			String msOtreatyNo, String taskId, String nowTime) {
		Boolean  billedCheck = false;
		try {
			List<FriTreatyShares> friTreatySharesList = customerizeService.billedCheck(msDpolicyNo, msDendorseNo, msDaddrNo,
					treatyYear, msOtreatyNo);
			if(!friTreatySharesList.isEmpty()) {
				billedCheck = true;
				writeFile(nowTime, "===>?????????????????????(" + msDpolicyNo + " / " + msDendorseNo + " / " + msDaddrNo + " / "
						+ treatyYear + " / " + msOtreatyNo + ")", taskId, "N");
			}
		} catch (Exception e) {
			savelog(taskId, "????????????????????????????????? , ??????????????????????????????(" + msDpolicyNo + " / " + msDendorseNo + " / " + msDaddrNo + " / "
					+ treatyYear + " / " + msOtreatyNo + ")");
			writeFile(nowTime, "????????????????????????????????? , ??????????????????????????????(" + msDpolicyNo + " / " + msDendorseNo + " / " + msDaddrNo + " / "
					+ treatyYear + " / " + msOtreatyNo + ")", taskId, "N");
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
		}
		return billedCheck;
	}
	
	private void afterLoop2(String nowTime, int ix, String msPolicyBGN, String msPolicyEND, String msRiskNo, String taskId){
		Asiutil util = new Asiutil();
		writeFile(nowTime, "---- end ----- " + ix + " " + util.processDateToString(new Date(), "yyyy/MM/dd HH:mm:ss"), taskId, "Y");
		log.debug("End=" + util.processDateToString(new Date(), "yyyy/MM/dd HH:mm:ss"));
		FriTreatyCalRecord model = new FriTreatyCalRecord();
		model.setProgramId("1204");
		// ??????DB???????????????????????????insert?????????update
		List<FriTreatyCalRecord> friTreatyCalRecordList;
		try {
			friTreatyCalRecordList = friTreatyCalRecordService.queryByFriTreatyCalRecord(model);
			model.setCalcDate(util.processStringToDate("yyyy/MM/dd", msPolicyEND));
			if(friTreatyCalRecordList.isEmpty()) {
				friTreatyCalRecordService.insert(model);
			}else {
				friTreatyCalRecordService.update(model);
			}
		} catch (Exception e) {
			savelog(taskId, "??????????????????????????????, ??????????????????????????????");
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
		} finally {
			savelog(taskId, "??????????????????" + msPolicyBGN + "-" + msPolicyEND + " ???????????????" + msRiskNo + " ??? --???????????????" + "  " + util.processDateToString(new Date(), "yyyy/MM/dd HH:mm:ss"));
			savelog(taskId, "??????????????????????????????");
		}
	}
}
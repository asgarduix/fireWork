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
	
	// 組寫入 log 的字串
	StringBuilder stringBuilder = new StringBuilder("");	
	
	/**
	 * 執行自動分保
	 * @param rin1204VOReq
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	public String autoReinsurance(Rin1204VOReq rin1204VOReq, String taskId)
			throws Exception {
		log.debug(">>> Rin1204Controller.autoReinsurance(執行自動分保)");
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
			// 先寫個空檔案
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
			// [1] 讀取參數
			// 合約年度
			treatyYear = rin1204VOReq.getTreatyYear();
			// 分保起日
			ucRocDbgn = rin1204VOReq.getUcRocDbgn();
			// 分保迄日
			ucRocDend = rin1204VOReq.getUcRocDend();
			// 同險代號
			riskNo = rin1204VOReq.getRiskNo();
			if (StringUtils.isBlank(treatyYear) && StringUtils.isBlank(ucRocDbgn) && StringUtils.isBlank(ucRocDend)
					&& StringUtils.isBlank(riskNo)) {
				savelog(taskId, "無參數資料可分保, 請確認分保資料處理區間");
			}
			// [2] 清除舊分保資料
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			if(sdf.parse(ucRocDbgn).after(sdf.parse("2003/12/31"))) {
				if("99999999999".equals(riskNo)) {
					// [2.1] 當輸入同險=99999999999 時, 清除該起始日後所有同險分保資料
					try {
						customerizeService.deleteOldReinsData999(customerizeMapper, ucRocDbgn, treatyYear);
					} catch (Exception e) {
						savelog(taskId, "Err: " + ucRocDbgn + "-" + ucRocDend + "-" + treatyYear + "清除分保資料發生錯誤-01, 請聯絡資訊室人員處理" + e.toString());
						throw e;
					}
				}else {
					// [2.2] 當指定同險時, 僅清除該同險在起始日後所有分保資料
					// 先由"分保起日"和"同險代號"查詢要被清除的資料(得到policy_no, endorse_no, addr_no )
					List<FriPolicyDtl> friPolicyDtlList = new ArrayList<>();
					try {
						friPolicyDtlList = customerizeService.queryReinsBeDeleteData(ucRocDbgn, riskNo);
					} catch (Exception e) {
						savelog(taskId, "Err: " + ucRocDbgn + "-" + ucRocDend + "-" + treatyYear + "清除分保資料發生錯誤-01R, 請聯絡資訊室人員處理" + e.toString());
						throw e;
					}
					if(!friPolicyDtlList.isEmpty()) {
						for (FriPolicyDtl friPolicyDtl : friPolicyDtlList) {
							// 由查到的資料當參數，以遍歷方式一筆一筆刪除
							try {
								customerizeService.deleteOldReinsDataSpe(customerizeMapper, nullToSpace(friPolicyDtl.getPolicyNo()),
										nullToSpace(friPolicyDtl.getEndorseNo()), nullToZero(friPolicyDtl.getAddrNo()));
							} catch (Exception e) {
								savelog(taskId, "Err: " + ucRocDbgn + "-" + ucRocDend + "-" + treatyYear + "清除分保資料發生錯誤-02R, 請聯絡資訊室人員處理" + e.toString());
								throw e;
							}
						}
					}
				}
			}
			
			log.debug(" Clear Time:" + util.processDateToString(new Date(), "yyyy/MM/dd HH:mm:ss"));
			
			sqlSession.commit();

			// [3] 讀取合約分保順序
			savelog(taskId, "分保期間為：" + ucRocDbgn + "-" + ucRocDend + " 同險代號：" + riskNo + "-- 開始時間：" + "  " + util.processDateToString(new Date(), "yyyy/MM/dd HH:mm:ss"));
			String gsStartTime = util.processDateToString(new Date(), "MMddHHmm");
			List<Rin1204VOResp1> moTreatyOrderAll;
			try {
				moTreatyOrderAll = customerizeService.queryTreatyShareOrder(treatyYear);
			} catch (Exception e) {
				savelog(taskId, "Err: " + "合約分保順序資料取得錯誤-07, 請聯絡資訊室人員處理");
				throw e;
			}
			if(moTreatyOrderAll.isEmpty()) {
				savelog(taskId, "Err: " + "合約分保順序未設定或合約未設定, 請檢查合約分保相關設定1");
				throw new Exception("Err: " + "合約分保順序未設定或合約未設定, 請檢查合約分保相關設定1");
			}
			
			// [4] 讀取待分保明細清單
			log.debug("Bgn>>" + util.processDateToString(new Date(), "yyyy/MM/dd HH:mm:ss"));
			List<Rin1204VOResp2> shareDetailList;
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ucRocDbgn", rin1204VOReq.getUcRocDbgn());
			    map.put("ucRocDend", rin1204VOReq.getUcRocDend());
			    map.put("riskNo", rin1204VOReq.getRiskNo());
			    msSQL = getSQL(mybatisConfig, map, "queryShareDetailList");
				writeFile(nowTime, "讀取待分保明細清單: " + msSQL, taskId, "Y");
				shareDetailList = customerizeService.queryShareDetailList(ucRocDbgn, ucRocDend, riskNo);
			} catch (Exception e) {
				savelog(taskId, msPolicyNo + "-" + msEndorseNo + "分保資料取得錯誤-03, 請聯絡資訊室人員處理");
				throw e;
			}
			log.debug("End>>" + util.processDateToString(new Date(), "yyyy/MM/dd HH:mm:ss"));
			if(shareDetailList.isEmpty()) {
				savelog(taskId, msPolicyNo + "-" + msEndorseNo + "無保單明細資料可處理或同險未設定完成, 請聯絡資訊室人員處理");
				// 客戶說這個不算"執行失敗"，故直接回傳字串，不拋 Exception
				return msPolicyNo + "-" + msEndorseNo + "無保單明細資料可處理或同險未設定完成, 請聯絡資訊室人員處理";
//				throw new Exception(msPolicyNo + "-" + msEndorseNo + "無保單明細資料可處理或同險未設定完成, 請聯絡資訊室人員處理");
			}
			
			// [5] 開始進行分保
			writeFile(nowTime, "---- start -----" + util.processDateToString(new Date(), "yyyy/MM/dd HH:mm:ss"), taskId, "N");
			writeFile(nowTime, "分保清單: " + msSQL, taskId, "Y");
			for (int k = 0; k < shareDetailList.size(); k++) {
				// 檢查此 taskID 在 TABLE batchqueue 中的狀態，若為"3"，則代表已手動停止程式，故直接回傳 "3"
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
		        
		        // [5.1] 查詢該保單之同險是否有續單保單
    		    //  [5.1.1] 排除本身保單之續保單(因應續保單與前批單同天印單問題，致使無法計入累積)
				Map<String, Object> map = new HashMap<>();
				map.put("riskNo", msDrisk_no);
			    map.put("policyDend", msPolicyDEND);
			    map.put("policyDbgn", msPolicyDBGN);
			    map.put("policyDprt", msPolicyPRT);
			    map.put("policyNo", msDpolicy_no);
			    msSQL = getSQL(mybatisConfig, map, "queryOldPolicyList");
				writeFile(nowTime, "續保單清單  " + msSQL, taskId, "N");
		        List<Rin1204VOResp3> moOldPolicy = new ArrayList<>();
		        if(!msPolicyNo.equals(msPolicyNo_pre) || !msEndorseNo.equals(msEndorseNo_pre) || !msDrisk_no.equals(msDrisk_no_pre)) {
		        	try {
						moOldPolicy = customerizeService.queryOldPolicyList(msDrisk_no, msPolicyDEND,
								msPolicyDBGN, msPolicyPRT, msDpolicy_no);
					} catch (Exception e) {
						savelog(taskId, "續保單資料取得錯誤-14, 請聯絡資訊室人員處理");
						writeFile(nowTime, "續保單資料取得錯誤-14, 請聯絡資訊室人員處理", taskId, "Y");
						throw e;
					}
		        	 msPolicyNo_pre = msPolicyNo;
    	             msEndorseNo_pre = msEndorseNo;
    	             msDrisk_no_pre = msDrisk_no;
		        }
		        // [5.1.2] 查詢該批單(不慮保單)是否有新續保單
		        if(StringUtils.isNotBlank(msEndorseNo)) {
		        	Map<String, Object> map2 = new HashMap<>();
					map2.put("policyNo", msPolicyNo);
				    map2.put("policyDEND", msPolicyDEND);
				    msSQL = getSQL(mybatisConfig, map2, "queryMoNextPolicy");
					writeFile(nowTime, "查詢是否有新續保單:" + msSQL, taskId, "N");
		        	List<Rin1204VOResp4> moNextPolicy = new ArrayList<>();
					try {
						moNextPolicy = customerizeService.queryMoNextPolicy(msPolicyNo, msPolicyDEND);
					} catch (Exception e) {
						savelog(taskId, "新續保單資料取得錯誤-14, 請聯絡資訊室人員處理");
						throw e;
					}
		        	if(moNextPolicy.isEmpty()) {
		        		nextPolicy = "";
		        	}else {
		        		nextPolicy = moNextPolicy.get(0).getPolicyNo();
		        	}
		        	writeFile(nowTime, "新續保單號:" + nextPolicy, taskId, "N");
		        }else {
		        	nextPolicy = ""; // 重置變數以免受上筆資料影響
		        }
				
				//  [5.2] 查詢該保單期間之同險分保總額(排除[5.1]取得之續保單號)（Retain + Q/S + Surp）
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
					writeFile(nowTime, "分保資料取得錯誤-14 =>" + msSQL, taskId, "N");
					savelog(taskId, "分保資料取得錯誤-14, 請聯絡資訊室人員處理");
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
		        
		        writeFile(nowTime, "msCoinShareTotAmt：" + msSQL, taskId, "N");
		        writeFile(nowTime, "msCoinShareTotAmt：" + msCoinShareTotAmt, taskId, "N");
		        
		        // 貨物結清特別處理：批改事由為10時，則保額為0
		        if("10".equals(msEndReason)) {
		        	msDamt = Long.parseLong("0");
		        	msDamt_flt = Long.parseLong("0");
		        	msDamt_fix = Long.parseLong("0");
		        	msDamt_typ = Long.parseLong("0");
		        	msDamt_ear = Long.parseLong("0");
		        }
		        
				writeFile(nowTime, "=================================>1  " + msPolicyNo + "----------" + msEndorseNo
						+ "--------" + msDaddr_no + "===  " + msPolicyDBGN + " <--> " + msPolicyDEND, taskId, "N");
		        
				//  [5.3] 當保額及保費均為0時, 該筆資料不計算分保，換下一張地址    Check Amt If 0 Get Ori Policy Data
				if (msDamt == 0 && msDprem == 0) {
					if (k == shareDetailList.size() - 1) {
						afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
						sqlSession.commit();
						return "";
					} else {
						continue;
					}
				}
				// 分出需考慮保額為0的保費,依照原保單分保比例計算保費
				if(msDamt == 0) {  // 保額為0
					// [5.4] 當保額為0時之處理( If msDamt = 0)
					
					/**
					 * 保額=0,保費依據保額分保比率分配，順序為Surp、Q/S、Retain
					 * 且僅就該地址第一筆之分保情形計算分保比率（不為加總後之分保比率，因應相同）
					 */
					
					// 批退保費前，亦應先Check Fac Prem>
					
					// [5.4.1] 讀取臨分分保資料 Sum amt, prem of FAC share
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
						writeFile(nowTime, "分保資料取得錯誤-05(5.4.1]=>" + msSQL, taskId, "N");
						savelog(taskId, "分保資料取得錯誤-05, 請聯絡資訊室人員處理");
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
					
					// 只有保單才作, 批單設 0
					if(StringUtils.isBlank(msDendorse_no) || "R".equals(StringUtils.upperCase(msEndReason_main))) {
						// [5.4.2]計算臨分自留(FAC_Retain) Sum amt, prem of FAC_Retail share
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
							writeFile(nowTime, "分保資料取得錯誤-05A=>" + msSQL, taskId, "N");
							savelog(taskId, "分保資料取得錯誤-05A, 請聯絡資訊室人員處理" + e.toString());
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
								// 知會號,更正號
								msExSlip = nullToSpace(friFac.getSlipNo());
								// 超賠起賠金額
								msExBgn = nullToZero(friFac.getExcessBgn());
								// 超賠賠款上限
								msExEnd = nullToZero(friFac.getExcessLimit());
								// 查詢"臨分標的物檔"保額加總
								List<Rin1204VOResp7> moExAmt = new ArrayList<>();
								Map<String, Object> map5 = new HashMap<>();
								map5.put("exSlip", msExSlip);
							    msSQL = getSQL(mybatisConfig, map5, "queryExAmt");
								try {
									moExAmt = customerizeService.queryExAmt(msExSlip);
								} catch (Exception e) {
									log.debug(e.toString());
									Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
									writeFile(nowTime, "分保資料取得錯誤-05B1=>" + msSQL, taskId, "N");
									savelog(taskId, "分保資料取得錯誤-05B1, 請聯絡資訊室人員處理 " + e.toString());
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
								// 查詢"臨分再保人檔"分出比率 (百分比) % 加總
								List<Rin1204VOResp8> moExShare = new ArrayList<>();
								Map<String, Object> map6 = new HashMap<>();
								map6.put("exSlip", msExSlip);
							    msSQL = getSQL(mybatisConfig, map6, "queryExShare");
								try {
									moExShare = customerizeService.queryExShare(msExSlip);
								} catch (Exception e) {
									log.debug(e.toString());
									Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
									writeFile(nowTime, "分保資料取得錯誤-05C-1=>" + msSQL, taskId, "N");
									savelog(taskId, "分保資料取得錯誤-05C-1, 請聯絡資訊室人員處理");
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
								
								// EDIT寫入合約分保檔 (舊程式764行)
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
									writeFile(nowTime, "分保資料取得錯誤-05C-2=>" + msSQL, taskId, "N");
									savelog(taskId, "自留分保分保資料取得錯誤-05C-2, 請聯絡資訊室人員處理");
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
									// update 合約分保檔
									Map<String, Object> map8 = new HashMap<>();
									map8.put("record", rin1204VOReq1);
								    msSQL = getSQL(mybatisConfig, map8, "updateFriTreatyShares");
									try {
										customerizeService.updateFriTreatyShares(customerizeMapper, rin1204VOReq1);
									} catch (Exception e) {
										log.debug(e.toString());
										Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
										savelog(taskId, msPolicyNo + "-" + msEndorseNo + "同險：" + msDrisk_no + "超額自留分保資料更正錯誤, 請聯絡資訊室人員處理");
										writeFile(nowTime, msPolicyNo + "-" + msEndorseNo + "同險：" + msDrisk_no + "超額自留分保資料更正錯誤, 請聯絡資訊室人員處理", taskId, "N");
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
									// 檢查此 taskID 在 TABLE batchqueue 中的狀態，若為"3"，則代表已手動停止程式，故直接回傳 "3"
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
									// insert 合約分保檔
									Map<String, Object> map8 = new HashMap<>();
									map8.put("record", rin1204VOReq2);
								    msSQL = getSQL(mybatisConfig, map8, "insertFriTreatyShares");
									try {
										customerizeService.insertFriTreatyShares(customerizeMapper, rin1204VOReq2);
									} catch (Exception e) {
										log.debug(e.toString());
										Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
										savelog(taskId, msPolicyNo + "-" + msEndorseNo + "同險：" + msDrisk_no + "超額自留分保資料新增錯誤, 請聯絡資訊室人員處理");
										writeFile(nowTime, msPolicyNo + "-" + msEndorseNo + "同險：" + msDrisk_no + "超額自留分保資料新增錯誤, 請聯絡資訊室人員處理", taskId, "N");
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
									// 檢查此 taskID 在 TABLE batchqueue 中的狀態，若為"3"，則代表已手動停止程式，故直接回傳 "3"
									if("3".equals(getProcessStatus(taskId, batchqueueService))) {
										writeFile(nowTime, "", taskId, "Y");
										return "3";
									}
								}
								// 舊程式 846 行
								msFACAmt  = msFACAmt + retainAmt.longValue();
							}
						}
					}
					writeFile(nowTime, "===>..........  臨分分出保額" + msFACAmt + "(FAC_RET:1)", taskId, "N");
					
					// [5.4.3]新增保額為0之分保資料(保費)
					// 舊程式 855 行
					msDprem = msDprem - msFACPrem;
					// 查詢該地址之最原始保批單資料,僅取得最早一筆
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
						savelog(taskId, msPolicyNo + "-" + msEndorseNo + "同險：" + msDrisk_no + "保單明細資料取得錯誤-04, 請聯絡資訊室人員處理");
						if (k == shareDetailList.size() - 1) {
							afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
							return "";
						} else {
							continue;
						}
					}
					if(moTemp.isEmpty()) {
						writeFile(nowTime, "無原保單分保資料可參考, 請聯絡資訊室人員處理(type1) " + msSQL, taskId, "N");
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
						savelog(taskId, msPolicyNo + "-" + msEndorseNo + "同險：" + msDrisk_no + "分保資料取得錯誤-04, 請聯絡資訊室人員處理");
						if (k == shareDetailList.size() - 1) {
							afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
							sqlSession.commit();
							return "";
						} else {
							continue;
						}
					}
					if(moCancel.isEmpty()) {
						writeFile(nowTime, "無原保單分保資料可參考, 請聯絡資訊室人員處理(type2) " + msSQL, taskId, "N");
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
						
						// 計算分保費
						if(j != moCancel.size()-1) {
							if(msCamt == 0) {
								msSharePrem = BigDecimal.ZERO;
							}else {
								/**
								 * Fix 會移除 數位 的分數部分，並傳回結果的整數值。
								 * 而 Fix 會傳回大於或等於 number 的第一個負數整數。 例如， FIX 會將-8.4 轉換成-8。
								 * 換成 JAVA 就是 "RoundingMode.DOWN"
								 */
								// msSharePrem = Fix(msDprem * (msCshare_amt / msCamt))
								msSharePrem = new BigDecimal(msDprem).multiply(new BigDecimal(msCshare_amt)
										.divide(new BigDecimal(msCamt), 4, RoundingMode.HALF_UP))
										.setScale(0, RoundingMode.DOWN);
							}
						}else {
							// msSharePrem = msDprem - msTREPrem
							msSharePrem = new BigDecimal(msDprem).subtract(msTREPrem); // 最後一筆
						}
						
						// Insert Into fri_treaty_shares EDIT寫入合約分保檔
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
							savelog(taskId, msPolicyNo + "-" + msEndorseNo + "同險：" + msDrisk_no + "保額為0之分保資料新增錯誤1, 請聯絡資訊室人員處理");
							Quit_For_Flag1 = true;
							break;
						}
						Quit_For_Flag1 = false;
						sqlSession.commit();
						// 檢查此 taskID 在 TABLE batchqueue 中的狀態，若為"3"，則代表已手動停止程式，故直接回傳 "3"
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
					// [5.5] 保額不為0之處理( If msDamt <> 0)
					// Check Amt And Prem If Not 0 Process 未分出保額保費不為0才做分保處理
					if (msDamt != 0 || msDprem != 0 || msDamt_typ != 0 || msDprem_typ != 0 || msDamt_ear != 0
							|| msDprem_ear != 0) {
						// [5.5.1] 讀取臨分保額/保費  01. ======== Sum amt, prem of FAC share
						List<Rin1204VOResp6> moFACShare = new ArrayList<>();
						Map<String, Object> map1 = new HashMap<>();
						map1.put("policyNo", msDpolicy_no);
						map1.put("endorseNo", msDendorse_no);
						map1.put("addrNo", msDaddr_no);
					    msSQL = getSQL(mybatisConfig, map1, "queryFACShare");
						writeFile(nowTime, "讀取臨分保額: " + msSQL, taskId, "N");
						try {
							moFACShare = customerizeService.queryFACShare(msDpolicy_no, msDendorse_no, msDaddr_no);
						} catch (Exception e) {
							log.debug(e.toString());
							Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
							writeFile(nowTime, "分保資料取得錯誤-05(5.5]=>" + msSQL, taskId, "N");
							savelog(taskId, "分保資料取得錯誤-05, 請聯絡資訊室人員處理");
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
						
						// [5.5.2] 計算臨分自留(FAC_Retain)  02. ======== Sum amt, prem of FAC_Retail share
						// 只有保單才作, 批單設 0
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
								savelog(taskId, "分保資料取得錯誤-05A, 請聯絡資訊室人員處理");
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
									// 知會號,更正號
									msExSlip = nullToSpace(friFac.getSlipNo());
									// 超賠起賠金額
									msExBgn = nullToZero(friFac.getExcessBgn());
									// 超賠賠款上限
									msExEnd = nullToZero(friFac.getExcessLimit());
									// 調整超賠起賠金額, 使與火險保額同正負
									if(msDamt < 0) {
										msExBgn = -1 * Math.abs(msExBgn);
									}else {
										msExBgn = Math.abs(msExBgn);
									}
									// 查詢"臨分標的物檔"保額加總
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
										savelog(taskId, "分保資料取得錯誤-05B2, 請聯絡資訊室人員處理:" + e.toString());
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
									// 查詢"臨分再保人檔"分出比率 (百分比) % 加總
									List<Rin1204VOResp8> moExShare = new ArrayList<>();
									try {
										moExShare = customerizeService.queryExShare(msExSlip);
									} catch (Exception e) {
										log.debug(e.toString());
										Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
										savelog(taskId, "分保資料取得錯誤-05C, 請聯絡資訊室人員處理");
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
										// 此處的 RetainAmt 會被下方蓋掉，故不用寫
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
									
									// EDIT寫入合約分保檔 (舊程式1118行)
									List<FriTreatyShares> moExShare2 = new ArrayList<>();
									try {
										moExShare2 = customerizeService.queryExShare2(msDpolicy_no, msDendorse_no, msDaddr_no, treatyYear, msDrisk_no);
									} catch (Exception e) {
										log.debug(e.toString());
										Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
										savelog(taskId, "自留分保分保資料取得錯誤-05C, 請聯絡資訊室人員處理");
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
										// update 合約分保檔
										Map<String, Object> map5 = new HashMap<>();
										map5.put("record", rin1204VOReq1);
									    msSQL = getSQL(mybatisConfig, map5, "updateFriTreatyShares");
										try {
											customerizeService.updateFriTreatyShares(customerizeMapper, rin1204VOReq1);
										} catch (Exception e) {
											log.debug(e.toString());
											Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
											savelog(taskId, msPolicyNo + "-" + msEndorseNo + "同險：" + msDrisk_no + "超額自留分保資料更正錯誤, 請聯絡資訊室人員處理");
											writeFile(nowTime, msPolicyNo + "-" + msEndorseNo + "同險：" + msDrisk_no + "超額自留分保資料更正錯誤, 請聯絡資訊室人員處理", taskId, "N");
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
										// 檢查此 taskID 在 TABLE batchqueue 中的狀態，若為"3"，則代表已手動停止程式，故直接回傳 "3"
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
										// insert 合約分保檔
										Map<String, Object> map5 = new HashMap<>();
										map5.put("record", rin1204VOReq2);
									    msSQL = getSQL(mybatisConfig, map5, "insertFriTreatyShares");
										try {
											customerizeService.insertFriTreatyShares(customerizeMapper, rin1204VOReq2);
										} catch (Exception e) {
											log.debug(e.toString());
											Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
											savelog(taskId, msPolicyNo + "-" + msEndorseNo + "同險：" + msDrisk_no + "超額自留分保資料新增錯誤, 請聯絡資訊室人員處理");
											writeFile(nowTime, msPolicyNo + "-" + msEndorseNo + "同險：" + msDrisk_no + "超額自留分保資料新增錯誤, 請聯絡資訊室人員處理", taskId, "N");
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
										// 檢查此 taskID 在 TABLE batchqueue 中的狀態，若為"3"，則代表已手動停止程式，故直接回傳 "3"
										if("3".equals(getProcessStatus(taskId, batchqueueService))) {
											writeFile(nowTime, "", taskId, "Y");
											return "3";
										}
									}
//									// 舊程式 1199 行
									msFACAmt  = msFACAmt + retainAmt.longValue();
								}
							}
						}
						writeFile(nowTime, "===>..........  臨分分出保額" + msFACAmt + "(RetainAmt=" + retainAmt + ")(FAC_RET:2)", taskId, "N");
						msTREAmt = Long.parseLong("0");
	                    msTREPrem = BigDecimal.ZERO;
	                    msTRETYPAmt = Long.parseLong("0");
	                    msTRETYPPrem = Long.parseLong("0");
	                    msTREEARAmt = Long.parseLong("0");
	                    msTREEARPrem = Long.parseLong("0");
	                    
	                    /**
	                     * 合約順序
	                     * 批加：1.Retain  2.Q/S  3.Surp (保單同)
	                     * 批減：1.Surp    2.Q/S  3.Retain
	                     */
	                    
	                    // [5.5.3]計算合約分保    03. ======== Calc Share amt, prem of Retain Treaty
	                    // Calc Treaty For Retain    Step 3 To 6
	                    if(msDamt != 0) {
	                    	// 未分出保額不為0才做分保處理
							if ((msDamt - msFACAmt - msTREAmt != 0) || (msDamt_typ - msFACTYPAmt - msTRETYPAmt != 0)
									|| (msDamt_ear - msFACEARAmt - msTREEARAmt != 0)) {
								// [5.5.3.1]批加之處理
								if(msDamt >= 0) { // 批加
									writeFile(nowTime, "===>..........  批加自留保額" + msDamt, taskId, "N");
									// [5.5.3.1.1]計算合約QS之自留(Retain)
									// 03-1. ======== Get Treaty Share Order ========
									List<FriTreaty> moRetain = new ArrayList<>();
									try {
										moRetain = customerizeService.querymMoRetain(treatyYear, msPolicyType);
									} catch (Exception e) {
										log.debug(e.toString());
										Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
										savelog(taskId, "[" + msDpolicy_no + "]-[" + msDendorse_no + "]分保資料取得錯誤-11, 請聯絡資訊室人員處理");
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
									// A.取得保單限額
									if("1".equals(QSlimit_base)) {
										// 同險
										List<Rin1204VOResp9> moMinLimit = new ArrayList<>();
										Map<String, Object> map3 = new HashMap<>();
										map3.put("riskNo", msDrisk_no);
										map3.put("treatyYear", treatyYear);
										map3.put("policyDEND", msPolicyDEND);
										map3.put("policyDBGN", msPolicyDBGN);
										map3.put("policyPRT", msPolicyPRT);
									    msSQL = getSQL(mybatisConfig, map3, "queryMinLimit");
									    writeFile(nowTime, "===>..........  取得同險限額 " + msSQL, taskId, "N");
										try {
											moMinLimit = customerizeService.queryMinLimit(msDrisk_no,
													treatyYear, msPolicyDEND, msPolicyDBGN, msPolicyPRT);
										} catch (Exception e) {
											log.debug(e.toString());
											Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
											savelog(taskId, "分保資料取得錯誤-13, 請聯絡資訊室人員處理");
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
											savelog(taskId, "保單限額資料錯誤, 請檢查保單限額資料");
											if (k == shareDetailList.size() - 1) {
												afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
												sqlSession.commit();
												return "";
											} else {
												continue;
											}
										}
									}else {
										// 逐單
										msMinLimit = msDlimit;
									}
									writeFile(nowTime, "===>..........  同險最小限額" + msMinLimit + "  保單最小限額" + msDlimit, taskId, "N");
									// B.比較本保單限額與同險限額取較小的
									if(msDlimit > msMinLimit) {
										msPlyLimit = msMinLimit;
	                                    msPlyLimitOri = msPlyLimit;
									}else {
										msPlyLimit = msDlimit;
	                                    msPlyLimitOri = msPlyLimit;
									}

									// C.[change_flag <> X]者 取得合約限額,並比與 msPlyLimit 比較取得最小限額
									if(!"X".equals(StringUtils.upperCase(msChangeFlag.trim()))) {
										msShareAmt = BigDecimal.ZERO;
										// C-1 --非共保件
										if (BHUNDRED.subtract(msCoinsRate).compareTo(msOcoins_rate) <= 0) {
											if (msPlyLimit > msOlimit_general && msOlimit_general != 0) {
												msPlyLimit = msOlimit_general;
											}
										}
										// C-2 --共保件   [ 限額不變,但總數(retain+Q/S+Surp)不能大於共保限額]
										if (msCoinsRate.compareTo(BHUNDRED) != 0
												&& msCoinsRate.compareTo(msOcoins_rate) < 0) {
											// C-2-1.Query Treaty Shares 前已查詢
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
									writeFile(nowTime, "===>..........  共保限額" + msPlyLimit, taskId, "N");
									// 04. ======== Calc Same Risk Amt ======== 計算此同險已擺放保額
									if("1".equals(QSlimit_base)) { // 同險
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
											savelog(taskId, "分保資料取得錯誤-14, 請聯絡資訊室人員處理");
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
										// 逐單
										msShareAmt = BigDecimal.ZERO;
									}
									writeFile(nowTime, "===>..........  已擺放保額" + msShareAmt + "  " + msSQL, taskId, "N");
									
									// 05. ======== Calc Share To Treaty Limit  ======== '計算可擺放之保額 [限額-已分入保額]
									// A.共保限額-可使用限額
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
											msPlyLimit = Long.parseLong("0");  // 限額已滿
										}
									}
									if (new BigDecimal(msPlyLimit).compareTo(new BigDecimal(msDamt - msFACAmt)
											.multiply(BHUNDRED.subtract(QSshare_rate))
											.divide(BHUNDRED)) > 0) {
										// 舊程式 1391
										msPlyLimit = new BigDecimal(msDamt - msFACAmt)
												.multiply(BHUNDRED.subtract(QSshare_rate))
												.divide(BHUNDRED).longValue();
										writeFile(nowTime, "tmp2:" + msPlyLimit + " msDamt=" + msDamt + " msfacamt=" + msFACAmt + " qsshare_rate=" + QSshare_rate, taskId, "N");
									}else {
										writeFile(nowTime, "tmp3:" + msPlyLimit, taskId, "N");
									}
									// B.保單限額與共保限額比較
									if (new BigDecimal(msPlyLimit).compareTo(
											new BigDecimal(msPlyLimitOri).multiply(BHUNDRED.subtract(QSshare_rate))
													.divide(BHUNDRED).subtract(msShareAmt)) < 0) {
										writeFile(nowTime, "tmp4:" + msPlyLimit, taskId, "N");
									} else {
										if (new BigDecimal(msPlyLimitOri).multiply(BHUNDRED.subtract(QSshare_rate))
												.divide(BHUNDRED).subtract(msShareAmt).compareTo(BigDecimal.ZERO) < 0) {
											msPlyLimit = Long.parseLong("0"); // 限額已滿
										}else {
											msPlyLimit = new BigDecimal(msPlyLimitOri)
													.multiply(BHUNDRED.subtract(QSshare_rate)).divide(BHUNDRED)
													.subtract(msShareAmt).longValue();
											writeFile(nowTime, "tmp5:" + msPlyLimit, taskId, "N");
										}
									}
									// RetainAmt = CDbl(FormatNumber(msPlyLimit, 0))
									// 舊程式是轉為千分位格式又轉為數字，JAVA方式原本就是數字格式，故不用這層邏輯
									retainAmt = new BigDecimal(msPlyLimit).setScale(0, RoundingMode.HALF_UP);
									// RetainPrem = CDbl(FormatNumber(msDprem * (RetainAmt / msDamt), 0))
									retainPrem = new BigDecimal(msDprem).multiply(
											retainAmt.divide(new BigDecimal(msDamt), 4, RoundingMode.HALF_UP)).setScale(0, RoundingMode.HALF_UP);
									// 若為商店險且分保註為y , 則分配額同保額
									if("S".equals(msPolicyType) && "Y".equals(msCalcFlag)) {
										retainAmt = new BigDecimal(msDamt).setScale(0, RoundingMode.HALF_UP);
										retainPrem = new BigDecimal(msDprem)
												.multiply(retainAmt.divide(new BigDecimal(msDamt), 4, RoundingMode.HALF_UP)).setScale(0, RoundingMode.HALF_UP);
									}
									writeFile(nowTime, "===>msDamt=" + msDamt + " msFACAmt=" + msFACAmt + "  msShareAmt=" + msShareAmt + " QSshare_rate=" + QSshare_rate, taskId, "N");
									writeFile(nowTime, "===>******************1  自留分保" + retainAmt, taskId, "N");
									// 加入已出帳資料檢核, 處理限額調整 造成分保結果與已出帳資料出現二筆狀況
									if(billedCheck(msDpolicy_no, msDendorse_no, msDaddr_no, treatyYear, "Retain", taskId, nowTime) == true) {
										// 舊程式1424行
										retainAmt = BigDecimal.ZERO;
			                            retainPrem = BigDecimal.ZERO;
			                            writeFile(nowTime, "===>******************1  自留分保調整 : " + retainAmt, taskId, "N");
									}else if ("Y".equals(msCalcFlag)) {
										// 當分保註為Y時, 才寫入Retain
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
											savelog(taskId, msDpolicy_no + "-" + msDendorse_no + " 同險：" + msDrisk_no + "分保資料新增錯誤2, 請聯絡資訊室人員處理");
											if (k == shareDetailList.size() - 1) {
												afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
												sqlSession.commit();
												return "";
											} else {
												continue;
											}
										}
										 sqlSession.commit();
										// 檢查此 taskID 在 TABLE batchqueue 中的狀態，若為"3"，則代表已手動停止程式，故直接回傳 "3"
										if("3".equals(getProcessStatus(taskId, batchqueueService))) {
											writeFile(nowTime, "", taskId, "Y");
											return "3";
										}
										
										msTREAmt = new BigDecimal(msTREAmt).add(retainAmt).longValue() ;
									}
									
									// [5.5.3.1.2]計算合約FFQSQ, FFQSS, FRXL分配額   07. ======== Calc Share amt, prem of Other Treaty  ========
									
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
			                                
			                                // 未分出保額=保單保額-已分出保額
//			                                msTotShareAmt = msDamt - msFACAmt - msTREAmt
											msTotShareAmt = new BigDecimal(msDamt).subtract(new BigDecimal(msFACAmt)).subtract(new BigDecimal(msTREAmt));
			                                
											writeFile(nowTime,
													"===>..........  合約" + msOtreaty_no + ".....Amt=[" + msDamt
															+ "] FAC=[" + msFACAmt + "] Tre=[" + msTREAmt + "]    未分出保額"
															+ msTotShareAmt, taskId, "N");
											
											if (billedCheck(msDpolicy_no, msDendorse_no, msDaddr_no, treatyYear,
													msOtreaty_no, taskId, nowTime) == true) {
												// 讀取下一合約_A
												continue;
											}
											
											if(msTotShareAmt.compareTo(BigDecimal.ZERO) >= 0) {
												// Get Exist Min Limit For   [Other Treaty Use]
												// 07-1.取得保單限額
												if("1".equals(msOlimit_base)) {
													// 同險
													List<Rin1204VOResp9> moMinLimit;
													Map<String, Object> map3 = new HashMap<>();
													map3.put("riskNo", msDrisk_no);
													map3.put("treatyYear", treatyYear);
													map3.put("policyDEND", msPolicyDEND);
													map3.put("policyDBGN", msPolicyDBGN);
													map3.put("policyPRT", msPolicyPRT);
												    msSQL = getSQL(mybatisConfig, map3, "queryMinLimit");
													writeFile(nowTime, "同險分保資料取得: " + msSQL, taskId, "N");
													try {
														moMinLimit = customerizeService.queryMinLimit(msDrisk_no, treatyYear, msPolicyDEND, msPolicyDBGN, msPolicyPRT);
													} catch (Exception e) {
														log.debug(e.toString());
														Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
														savelog(taskId, "分保資料取得錯誤-09, 請聯絡資訊室人員處理");
														Quit_For_Flag1 = true;
														break;
													}
													if(moMinLimit.isEmpty()) {
														savelog(taskId, "保單限額資料錯誤, 請檢查保單限額資料");
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
													// 逐單
													msMinLimit = msDlimit;
												}
												
												writeFile(nowTime, "===>..........  同險最小限額" + msMinLimit + "  保單最小限額" + msDlimit, taskId, "N");
												
												// 07-2.比較本保單限額與同險限額取較小的
												if(msDlimit > msMinLimit) {
													msPlyLimit = msMinLimit;
												}else {
													msPlyLimit = msDlimit;
												}
												
												// 07-3.限額寫入陣列
												msRefLimit = msPlyLimit;
												// 廢code用不到
//	                                             RefTreaty(l).treaty_year = msOtreaty_year
//	                                             RefTreaty(l).treaty_no = msOtreaty_no
//	                                             RefTreaty(l).limit = msPlyLimit
												
												// 07-4.此合約限額
												if("1".equals(msOshare_type)) {
													// 比率
													// msPlyLimit = msPlyLimit * msOshare_rate / 100
													msPlyLimit = new BigDecimal(msPlyLimit).multiply(msOshare_rate).divide(BHUNDRED).longValue();
												}else {
													// 線數
													// msPlyLimit = msPlyLimit * msOshare_rate
													msPlyLimit = new BigDecimal(msPlyLimit).multiply(msOshare_rate).longValue();
												}
												
												if("FRXL".equals(msOtreaty_no)) {
													msPlyLimit = msOlimit_general;
												}
												
												writeFile(nowTime, "===>..........   合約限額>>>>" + msPlyLimit, taskId, "N");
												
												// 07-5.計算已擺放保額 (依同險角度)
												if("1".equals(msOlimit_base)) {
													// 同險
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
														savelog(taskId, "分保資料取得錯誤-08, 請聯絡資訊室人員處理");
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
													// 逐單
													msShareAmt = BigDecimal.ZERO;
												}
												
												// 07-5.計算可擺放保額 (Total)
												calc_amt = msDamt - msFACAmt;
												
												writeFile(nowTime, "===>..........  已擺放保額" + msShareAmt + "  Calc_amt" + calc_amt + "  " + msSQL, taskId, "N");
												
												// 07-6.計算該合約可分入額度 (Treaty)
												if("1".equals(msOshare_type)) {
													// 分入比率
													// msPlyShareAmt = Calc_amt * msOshare_rate / 100
													msPlyShareAmt = new BigDecimal(calc_amt).multiply(msOshare_rate).divide(BHUNDRED);
												}else {
													// 分入線數
													msPlyShareAmt = msTotShareAmt;
												}
												writeFile(nowTime,
														"1:msPlyShareAmt=" + msPlyShareAmt + "  msOshare_type="
																+ msOshare_type + "  Calc_amt=" + calc_amt
																+ "  msOshare_rate=" + msOshare_rate + "  msPlyLimit="
																+ msPlyLimit + "  msShareAmt=" + msShareAmt, taskId, "N");
												writeFile(nowTime, "===>..........  msPlyShareAmt" + msPlyShareAmt
														+ "  msPlyLimit=" + msPlyLimit + "  msShareAmt=" + msShareAmt, taskId, "N");
												
												// 07-7.比較可分入額度及限額
												if (msPlyShareAmt.compareTo(new BigDecimal(msPlyLimit)) > 0) {
													msPlyShareAmt = new BigDecimal(msPlyLimit);
												}
												
												// 07-8.計算此合約是否超過限額，即該保單可分入保額
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
												                                            
												// 07-9.[change_flag <> X]者 取得合約限額
												if(!"X".equals(StringUtils.upperCase(msChangeFlag.trim()))) {
													if (msCoinsRate.compareTo(BHUNDRED) != 0) {
														if(msCoinsRate.compareTo(msOcoins_rate) < 0) {
															writeFile(nowTime,
																	"4.4: msCoinsRate=" + msCoinsRate
																			+ "  msOcoins_rate=" + msOcoins_rate
																			+ "  msOlimit_general=" + msOlimit_general, taskId, "N");
															// 先前已取得該地址分配保額 msShareAmt
															// 另增加：msCoinShareTotAmt應加上同保單其他合約分保額(msTREAmt)來計算共保限額
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
														"===>..........  共保限額" + msPlyLimit + "  分配額" + msPlyShareAmt, taskId, "N");
												// 07-10.與 msPlyLimit 比較取得最小限額
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
												
												writeFile(nowTime, "===>******************1  " + msOtreaty_no + "分保" + msPlyShareAmt, taskId, "N");
												// 07-11.寫入合約分保檔
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
												// 寫入合約分保檔
												try {
													customerizeService.insertFriTreatyShares4(customerizeMapper, rin1204VOReq2);
												} catch (Exception e) {
													log.debug(e.toString());
													Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
													savelog(taskId, msPolicyNo + "-" + msEndorseNo + "同險：" + msDrisk_no + "分保資料新增錯誤3, 請聯絡資訊室人員處理");
													Quit_For_Flag1 = true;
													break;
												}
												sqlSession.commit();
												// 檢查此 taskID 在 TABLE batchqueue 中的狀態，若為"3"，則代表已手動停止程式，故直接回傳 "3"
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
									// [5.5.3.1.3]計算第2自留(SEC_Retain)    08. ======== Calc Share amt, prem of SEC_Retain Treaty  ========
									// Calc Treaty For SEC Retain    Step 08
									
									if(msDamt > (msFACAmt + msTREAmt)) {
										// 若不是不分保, 須產生未分配訊息
										if(!"N".equals(msCalcFlag)) {
											savelog(taskId, msPolicyNo + "-" + msEndorseNo + "同險：" + msDrisk_no + "-" + msDaddr_no + "保額尚餘" + (msDamt - msFACAmt - msTREAmt) + "未分配");
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
//										// 寫入合約分保檔
										try {
											customerizeService.insertFriTreatyShares5(customerizeMapper, rin1204VOReq2);
										} catch (Exception e) {
											log.debug(e.toString());
											Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
											savelog(taskId, msPolicyNo + "-" + msEndorseNo + "同險：" + msDrisk_no + "第二自留分保資料新增錯誤, 請聯絡資訊室人員處理");
										}
										sqlSession.commit();
										// 檢查此 taskID 在 TABLE batchqueue 中的狀態，若為"3"，則代表已手動停止程式，故直接回傳 "3"
										if("3".equals(getProcessStatus(taskId, batchqueueService))) {
											writeFile(nowTime, "", taskId, "Y");
											return "3";
										}
									}
									// [5.5.3.2]批減之處理
								}else {
									// 批減
									// [5.5.3.2.1]取得合約分保順序(大->小)   Get Treaty Share Order
									msQSamt = BigDecimal.ZERO;
									msTREAmt = Long.parseLong("0");
									
									List<Rin1204VOResp1> moTreatyOrder;
									Map<String, Object> map3 = new HashMap<>();
									map3.put("treatyYear", treatyYear);
									map3.put("policyType", msPolicyType);
								    msSQL = getSQL(mybatisConfig, map3, "queryMoTreatyOrder");
									writeFile(nowTime, "取得合約分保順序1: " + msSQL, taskId, "N");
									try {
										moTreatyOrder = customerizeService.queryMoTreatyOrder(treatyYear, msPolicyType);
									} catch (Exception e) {
										log.debug(e.toString());
										Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
										savelog(taskId, "分保資料取得錯誤-20, 請聯絡資訊室人員處理");
										if (k == shareDetailList.size() - 1) {
											afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
											sqlSession.commit();
											return "";
										} else {
											continue;
										}
									}
									
									// 提出疑問，為何要查兩個相同的SQL --> 客戶回覆此為VB做法，故JAVA刪除此段
									// WriteFile "取得合約分保順序2: " & msSQL
									// moTreatyOrder = customerizeService.queryMoTreatyOrder(treatyYear, msPolicyType);
									if(moTreatyOrder.isEmpty() && !"S".equals(msPolicyType)) {
										savelog(taskId, "合約分保順序未設定或合約未設定, 請檢查合約分保相關設定2");
										if (k == shareDetailList.size() - 1) {
											afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
											sqlSession.commit();
											return "";
										} else {
											continue;
										}
									}
									
									// [5.5.3.2.2]計算合約FFQSQ, FFQSS, FRXL分配額   If Policy Treaty Order Exist Continue
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
											
											// ！ Notice02：==>   若可區分合約排外業務於此段處理  If Need Process Modify Here Add Check fri_treaty_exclude
											
											// 未分出保額=保單保額-已分出保額
											msTotShareAmt = new BigDecimal(msDamt - msFACAmt - msTREAmt);
											writeFile(nowTime,
													"===>..........  批減--未分出保額" + msTotShareAmt + "(msDamt=" + msDamt
															+ ", msFACAmt=" + msFACAmt + ",msTREAmt=" + msTREAmt + ")", taskId, "N");
											
											if(billedCheck(msDpolicy_no, msDendorse_no, msDaddr_no, msOtreaty_year, msOtreaty_no, taskId, nowTime) == true) {
												// 1972
												continue;
											}
											if (msTotShareAmt.compareTo(BigDecimal.ZERO) <= 0) {
												// Calc Same Risk Amt 計算此同險已擺放保額
												List<Rin1204VOResp5> moShareAmt4 = new ArrayList<>();
												if("1".equals(msOlimit_base)) {
													// 同險
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
														savelog(taskId, "分保資料取得錯誤-21, 請聯絡資訊室人員處理");
														Quit_For_Flag1 = true;
				                                        break;
													}
												}else {
													// 逐單
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
														savelog(taskId, "分保資料取得錯誤-21, 請聯絡資訊室人員處理");
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
												
												writeFile(nowTime, "===>..........  合約" + msOtreaty_no + "已擺放保額"
														+ msShareAmt + "   " + msSQL, taskId, "N");
												// 2028
												// Calc Policy Share In This Treaty 計算分入本合約的保額
												if("1".equals(msOtreaty_mode)) {
													// Q/S
													if("1".equals(msOshare_type)) {
														// 分入比率
														// msPlyShareAmt = msTotShareAmt * msOshare_rate / 100
														msPlyShareAmt = msTotShareAmt.multiply(msOshare_rate).divide(BHUNDRED);
													}else {
														// 分入線數
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
														// 分入比率
														// msQSamt = msPlyShareAmt / (msOshare_rate / 100)
														msQSamt = msPlyShareAmt
																.divide(msOshare_rate.divide(BHUNDRED), 4, RoundingMode.HALF_UP);
													}else {
														// 分入線數
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
														// 若已擺放額<0, 則不再進行批減分保
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
												// 未分入此合約，則無法減保
												if (msShareAmt.compareTo(BigDecimal.ZERO) == 0) {
													msPlyShareAmt = BigDecimal.ZERO;
												}
//													msPlyShareAmt = Fix(msPlyShareAmt)
												msPlyShareAmt = msPlyShareAmt.setScale(0, RoundingMode.DOWN);
												writeFile(nowTime, "===>******************1  合約分保" + msPlyShareAmt, taskId, "N");
												// EDIT寫入合約分保檔
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
													savelog(taskId, msPolicyNo + "-" + msEndorseNo + "同險：" + msDrisk_no + "分保資料新增錯誤4, 請聯絡資訊室人員處理");
													Quit_For_Flag1 = true;
			                                        break;
												}
												sqlSession.commit();
												// 檢查此 taskID 在 TABLE batchqueue 中的狀態，若為"3"，則代表已手動停止程式，故直接回傳 "3"
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
			                        // 未分出保額=保單保額-已分出保額
									msTotShareAmt = new BigDecimal(msDamt - msFACAmt - msTREAmt);
									// [5.5.3.2.3]計算合約QS自留額(分保註=N時放 SEC_Retain, 否則放 Retain)     Get Treaty Share Order ========  '最後算自留
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
											savelog(taskId, "分保資料取得錯誤-22, 請聯絡資訊室人員處理");
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
			                        	
										// 當不分保時, 以保額放SEC_Retain
										if("N".equals(msCalcFlag)) {
											retType = "SEC_Retain";
											retainAmt = new BigDecimal(msDamt);
										}else {
											// 其他放 Retain
											retType = "Retain";
											if("S".equals(msPolicyType)) {
												retainAmt = new BigDecimal(msDamt);
											}
										}
										
										if (new BigDecimal(msDamt).subtract(msQSamt)
												.compareTo(new BigDecimal("-1")) == 0) {
											retainAmt = retainAmt.subtract(BigDecimal.ONE); // 需再議！ [批減保額為奇數，1元加至自留額]
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
										
										writeFile(nowTime, "===>..........  批減--自留保額" + retainAmt, taskId, "N");
										
										// EDIT寫入合約分保檔
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
											savelog(taskId, msPolicyNo + "-" + msEndorseNo + "同險：" + msDrisk_no + "分保資料新增錯誤5, 請聯絡資訊室人員處理");
											if (k == shareDetailList.size() - 1) {
												afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
												sqlSession.commit();
												return "";
											} else {
												continue;
											}
										}
										 sqlSession.commit();
										// 檢查此 taskID 在 TABLE batchqueue 中的狀態，若為"3"，則代表已手動停止程式，故直接回傳 "3"
										if("3".equals(getProcessStatus(taskId, batchqueueService))) {
											writeFile(nowTime, "", taskId, "Y");
											return "3";
										}
										// 2237
			                        }
									
									msTREAmt = new BigDecimal(msTREAmt).add(retainAmt).longValue();
									// 批減-未分配完
									if(msDamt != msFACAmt + msTREAmt) {
										savelog(taskId, msPolicyNo + "-" + msEndorseNo + "同險：" + msDrisk_no + "-" + msDaddr_no + "保額尚餘" + (msDamt - msFACAmt - msTREAmt) + "未分配");
									}
								}
							}
	                    }
	                    
					}
				}
				
				writeFile(nowTime, "policy_no:" + msPolicyNo + "---endorse_no:" + msEndorseNo + "--addr_no:"
						+ msDaddr_no + "--EndTime:" + util.processDateToString(new Date(), "yyyy/MM/dd HH:mm:ss"), taskId, "N");
				// 檢查此 taskID 在 TABLE batchqueue 中的狀態，若為"3"，則代表已手動停止程式，故直接回傳 "3"
				if("3".equals(getProcessStatus(taskId, batchqueueService))) {
					writeFile(nowTime, "", taskId, "Y");
					return "3";
				}
			}
			// loop2
			afterLoop2(nowTime, ix, ucRocDbgn, ucRocDbgn, riskNo, taskId);
			
			sqlSession.commit();
			// 檢查此 taskID 在 TABLE batchqueue 中的狀態，若為"3"，則代表已手動停止程式，故直接回傳 "3"
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
     * 根據類型替換參數
     *
     * 僅作為數位和字串兩種類型進行處理，需要特殊處理的可以繼續完善這裡
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
                //数字

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
                //日期

                case DATE:
                case TIME:
                case TIMESTAMP:
                    //其他，包含字串和其他特殊類型

                default:
                    strValue = "'" + strValue + "'";


            }
        } else if (Number.class.isAssignableFrom(javaType)) {
            //不加單引號

        } else {
            strValue = "'" + strValue + "'";
        }
        return sql.replaceFirst("\\?", strValue);
    }

    // 多一參數控制是否寫入(寫入時機: 第一次, 完成時, 報Exception時)
	private void writeFile(String nowTime, String msg, String taskId, String isWrite) {
		String oriFileContent = "";
//		String filein = msg + "\r\n";
		String filein = msg + "\n";
		stringBuilder.append(filein);
		try {
			if("Y".equals(isWrite)) {
				filein = stringBuilder.toString();
				// 檔案路徑
				String path = SpringProperty.getLocalFilePath();
				
				File directory = new File(path);
				if(!directory.isFile()) {
					directory.mkdirs();
				}
				
				File file = new File(path + nowTime + "Rin1204Log.txt");
				// 若檔案不存在則建立新檔
				if (!file.exists()) {
					Boolean createNew = file.createNewFile();
					if(Boolean.TRUE.equals(createNew)) {
						log.debug("create file success");
					}
					// 更新 batchqueue BatchReprotAccessPath, 儲存檔案路徑
					batchqueueService.updatePathByTaskId(taskId, path + nowTime + "Rin1204Log.txt");
				}
				// 原檔存在，故先讀檔 // TODO 改成不讀原檔
//				Long filelength = file.length(); // 獲取文件長度
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
			out.flush(); // 把快取區內容壓入檔案
			out.close(); // 最後記得關閉檔案				
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
	 * 已出帳資料檢核
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
				writeFile(nowTime, "===>本筆資料已出帳(" + msDpolicyNo + " / " + msDendorseNo + " / " + msDaddrNo + " / "
						+ treatyYear + " / " + msOtreatyNo + ")", taskId, "N");
			}
		} catch (Exception e) {
			savelog(taskId, "已出帳資料檢核發生錯誤 , 請聯絡資訊室人員處理(" + msDpolicyNo + " / " + msDendorseNo + " / " + msDaddrNo + " / "
					+ treatyYear + " / " + msOtreatyNo + ")");
			writeFile(nowTime, "已出帳資料檢核發生錯誤 , 請聯絡資訊室人員處理(" + msDpolicyNo + " / " + msDendorseNo + " / " + msDaddrNo + " / "
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
		// 先查DB有無該筆資料，無則insert，有則update
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
			savelog(taskId, "分保計算記錄新增錯誤, 請聯絡資訊室人員處理");
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
		} finally {
			savelog(taskId, "分保期間為：" + msPolicyBGN + "-" + msPolicyEND + " 同險代號：" + msRiskNo + " ＆ --完成時間：" + "  " + util.processDateToString(new Date(), "yyyy/MM/dd HH:mm:ss"));
			savelog(taskId, "自動分保處理作業完成");
		}
	}
}
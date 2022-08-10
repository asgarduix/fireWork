package com.asi.huanan.service.customerize;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.huanan.service.dao.mybatis.mapper.customerize.CustomerizeMapper;
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
import com.asi.huanan.service.repository.cutomerize.CustomerizeRepository;
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
import com.asi.huanan.vo.Rin1205QueryVOReq;
import com.asi.huanan.vo.Rin1303QueryRinComBySlipNoVOResp;

@Service
public class CustomerizeService {

	private Log log = LogFactory.getLog(CustomerizeService.class);

	@Autowired
	private CustomerizeRepository repository;

	// =====針對使用自訂SQL=====

	/**
	 * 
	 * @param rinComId
	 * @return
	 * @throws Exception
	 */
	public List<Rin1101FricomJoinRicmpf1> queryOneReiner(final String rinComId) throws Exception {
		return repository.queryOneReiner(rinComId);
	}

	/**
	 * 再保臨分到期_立即執行
	 * 
	 * @param treaty_dend_Bgn
	 * @param treaty_dend_End
	 * @return
	 * @throws Exception
	 */
	public List<Rin1302Table> getRin1302MainData(Date treaty_dend_Bgn, Date treaty_dend_End) throws Exception {
		return repository.getRin1302MainData(treaty_dend_Bgn, treaty_dend_End);
	}

	// 批次作業監視器_資料搜尋_初始畫面
	public List<BatchqueueJoinBatchlist> queryUseAccount(String account) throws Exception {
		return repository.queryUseAccount(account);
	}

	// 批次作業監視器_資料搜尋_報表選項
	public List<BatchqueueJoinBatchlist> queryUseAccountAndBatchid(String account, String batchid) throws Exception {
		return repository.queryUseAccountAndBatchid(account, batchid);
	}
	
	// 批次作業監視器_資料搜尋_報表選項(pdf)
	public List<BatchqueueJoinBatchlist> queryUseAccountAndBatchid2(String account, String batchid) throws Exception {
		return repository.queryUseAccountAndBatchid2(account, batchid);
	}

	/**
	 * Rin1203_同險設定，取系統當天日期和前兩年資料為查詢條件，新增至暫存表
	 * 
	 * @param policy_dprtS , policy_dprtE
	 * @return
	 * @throws Exception
	 */
	public int insertToFriTemparea(String policy_dprtS, String policy_dprtE) throws Exception {
		return repository.insertToFriTemparea(policy_dprtS, policy_dprtE);
	}

	/**
	 * Rin1203_同險設定，每日將暫存表前一天資料刪除
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	public int deleteAllFriTemparea() throws Exception {
		return repository.deleteAllFriTemparea();
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Rin1205TableMain> queryRin1205MainData(Rin1205QueryVOReq model, CustomerizeMapper mapper)
			throws Exception {
		return repository.queryRin1205MainData(model, mapper);
	}

	/**
	 * Rin1203_同險設定，用印單日期和地段代號，做為地段條件查詢
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Rin1203AVOResp> queryAreaPolicy(String policy_dprtS, String policy_dprtE, String txtarea_code)
			throws Exception {
		return repository.queryAreaPolicy(policy_dprtS, policy_dprtE, txtarea_code);
	}

	/**
	 * 當輸入同險=99999999999 時, 清除該起始日後所有同險分保資料
	 * 
	 * @param customerizeMapper
	 * @param ucRocDbgn
	 * @param treatyYear
	 * @return
	 */
	public int deleteOldReinsData999(CustomerizeMapper customerizeMapper, String ucRocDbgn, String treatyYear) {
		return repository.deleteOldReinsData999(customerizeMapper, ucRocDbgn, treatyYear);
	}

	/**
	 * 當指定同險時, 查詢該同險在起始日後所有分保資料
	 * 
	 * @param ucRocDbgn
	 * @param riskNo
	 * @return
	 * @throws Exception
	 */
	public List<FriPolicyDtl> queryReinsBeDeleteData(String ucRocDbgn, String riskNo) throws Exception {
		return repository.queryReinsBeDeleteData(ucRocDbgn, riskNo);
	}

	/**
	 * 當指定同險時, 僅清除該同險在起始日後所有分保資料
	 * 
	 * @param customerizeMapper
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @return
	 */
	public int deleteOldReinsDataSpe(CustomerizeMapper customerizeMapper, String policyNo, String endorseNo,
			Short addrNo) {
		return repository.deleteOldReinsDataSpe(customerizeMapper, policyNo, endorseNo, addrNo);
	}

	/**
	 * 讀取合約分保順序
	 * 
	 * @param treatyYear
	 * @return
	 * @throws Exception
	 */
	public List<Rin1204VOResp1> queryTreatyShareOrder(String treatyYear) throws Exception {
		return repository.queryTreatyShareOrder(treatyYear);
	}

	/**
	 * 讀取待分保明細清單
	 * 
	 * @param ucRocDbgn
	 * @param ucRocDend
	 * @param riskNo
	 * @return
	 * @throws Exception
	 */
	public List<Rin1204VOResp2> queryShareDetailList(String ucRocDbgn, String ucRocDend, String riskNo)
			throws Exception {
		return repository.queryShareDetailList(ucRocDbgn, ucRocDend, riskNo);
	}

	/**
	 * Rin1303-用更正號查詢臨分再保人
	 * 
	 * @param slip_no
	 * @return list Rin1303QueryRinComBySlipNoVOResp
	 * @throws Exception
	 */
	public List<Rin1303QueryRinComBySlipNoVOResp> queryRinComBySlipNo(final String slipNo) throws Exception {
		return repository.queryRinComBySlipNo(slipNo);
	}

	/**
	 * 查詢同險未處理保單(同險代號!="99999999999")
	 * 
	 * @param ucRocDbgn
	 * @param ucRocDend
	 * @param riskNo
	 * @return
	 * @throws Exception
	 */
	public List<Rin1204VOResp> queryUnProcPolicy1(String ucRocDbgn, String ucRocDend, String riskNo) throws Exception {
		return repository.queryUnProcPolicy1(ucRocDbgn, ucRocDend, riskNo);
	}

	/**
	 * 查詢同險未處理保單(同險代號="99999999999")
	 * 
	 * @param ucRocDbgn
	 * @param ucRocDend
	 * @return
	 * @throws Exception
	 */
	public List<Rin1204VOResp> queryUnProcPolicy2(String ucRocDbgn, String ucRocDend) throws Exception {
		return repository.queryUnProcPolicy2(ucRocDbgn, ucRocDend);
	}

	/**
	 * 查詢該保單之同險是否有續單保單
	 * 
	 * @param riskNo
	 * @param policyDend
	 * @param policyDbgn
	 * @param policyDprt
	 * @param policyNo
	 * @return
	 * @throws Exception
	 */
	public List<Rin1204VOResp3> queryOldPolicyList(String riskNo, String policyDend, String policyDbgn,
			String policyDprt, String policyNo) throws Exception {
		return repository.queryOldPolicyList(riskNo, policyDend, policyDbgn, policyDprt, policyNo);
	}

	/**
	 * 查詢該批單(不慮保單)是否有新續保單
	 * 
	 * @param policyNo
	 * @param policyDEND
	 * @return
	 * @throws Exception
	 */
	public List<Rin1204VOResp4> queryMoNextPolicy(String policyNo, String policyDEND) throws Exception {
		return repository.queryMoNextPolicy(policyNo, policyDEND);
	}

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
	 * @throws Exception
	 */
	public List<Rin1204VOResp5> queryMoShareAmt(String treatyYear, String riskNo, String policyDEND, String policyDBGN,
			String policyPRT, List<String> oldPolicyList, String oldPolicy) throws Exception {
		return repository.queryMoShareAmt(treatyYear, riskNo, policyDEND, policyDBGN, policyPRT, oldPolicyList,
				oldPolicy);
	}

	/**
	 * 讀取臨分分保資料 Sum amt, prem of FAC share
	 * 
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @return
	 * @throws Exception
	 */
	public List<Rin1204VOResp6> queryFACShare(String policyNo, String endorseNo, Short addrNo) throws Exception {
		return repository.queryFACShare(policyNo, endorseNo, addrNo);
	}

	/**
	 * 計算臨分自留(FAC_Retain) Sum amt, prem of FAC_Retail share
	 * 
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @return
	 * @throws Exception
	 */
	public List<FriFac> queryFACRetain(String policyNo, String endorseNo, Short addrNo) throws Exception {
		return repository.queryFACRetain(policyNo, endorseNo, addrNo);
	}

	/**
	 * 查詢"臨分標的物檔"保額加總
	 * 
	 * @param exSlip
	 * @return
	 * @throws Exception
	 */
	public List<Rin1204VOResp7> queryExAmt(String exSlip) throws Exception {
		return repository.queryExAmt(exSlip);
	}

	/**
	 * 查詢"臨分再保人檔"分出比率 (百分比) % 加總
	 * 
	 * @param exSlip
	 * @return
	 * @throws Exception
	 */
	public List<Rin1204VOResp8> queryExShare(String exSlip) throws Exception {
		return repository.queryExShare(exSlip);
	}

	/**
	 * 查詢合約分保檔
	 * 
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @param treatyYear
	 * @param riskNo
	 * @return
	 * @throws Exception
	 */
	public List<FriTreatyShares> queryExShare2(String policyNo, String endorseNo, Short addrNo, String treatyYear,
			String riskNo) throws Exception {
		return repository.queryExShare2(policyNo, endorseNo, addrNo, treatyYear, riskNo);
	}

	/**
	 * update 合約分保檔
	 * 
	 * @param customerizeMapper
	 * @param rin1204VOReq1
	 * @return
	 */
	public int updateFriTreatyShares(CustomerizeMapper customerizeMapper, Rin1204VOReq1 rin1204VOReq1) {
		return repository.updateFriTreatyShares(customerizeMapper, rin1204VOReq1);
	}

	/**
	 * insert 合約分保檔
	 * 
	 * @param customerizeMapper
	 * @param rin1204voReq2
	 * @return
	 */
	public int insertFriTreatyShares(CustomerizeMapper customerizeMapper, Rin1204VOReq2 rin1204voReq2) {
		return repository.insertFriTreatyShares(customerizeMapper, rin1204voReq2);
	}

	/**
	 * Query PolDtl
	 * 
	 * @param policyNo
	 * @param addrNoOri
	 * @return
	 * @throws Exception
	 */
	public List<Rin1204VOResp> queryMoTemp(String policyNo, Short addrNoOri) throws Exception {
		return repository.queryMoTemp(policyNo, addrNoOri);
	}

	/**
	 * Query Policy Shares
	 * 
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @return
	 * @throws Exception
	 */
	public List<FriTreatyShares> queryMoCancel(String policyNo, String endorseNo, String addrNo) throws Exception {
		return repository.queryMoCancel(policyNo, endorseNo, addrNo);
	}

	
	/**
     * Rin1303-查詢報表「臨分主檔」資料
	 * @param cessionNo
	 * @param slipNo
	 * @param rinComId
	 * @return list Rin1303QueryMain
     * @throws Exception
     */
    public List<Rin1303QueryMain> queryRin1303PrintMain(String cessionNo, String slipNo, String rinComId, CustomerizeMapper mapper)
            throws Exception
    {
        return repository.queryRin1303PrintMain(cessionNo, slipNo, rinComId, mapper);
    }
    
	/**
     * Rin1303-查詢報表「臨分再保人檔」資料
	 * @param slipNo
	 * @param rinComId
	 * @return list Rin1303Query2
     * @throws Exception
     */
    public List<Rin1303Query2> queryRin1303Print2(String slipNo, String rinComId, CustomerizeMapper mapper)
            throws Exception
    {
        return repository.queryRin1303Print2(slipNo, rinComId, mapper);
    }


	/**
	 * insert 合約分保檔2
	 * @param customerizeMapper
	 * @param rin1204voReq2
	 * @return
	 */
	public int insertFriTreatyShares2(CustomerizeMapper customerizeMapper, Rin1204VOReq2 rin1204voReq2) {
		return repository.insertFriTreatyShares2(customerizeMapper, rin1204voReq2);
	}

	/**
	 * Get Treaty Share Order
	 * @param treatyYear
	 * @param msPolicyType
	 * @return
	 * @throws Exception 
	 */
	public List<FriTreaty> querymMoRetain(String treatyYear, String policyType) throws Exception {
		return repository.querymMoRetain(treatyYear, policyType);
	}

	/**
	 * 取得同險限額
	 * @param msDrisk_no
	 * @param treatyYear
	 * @param msPolicyDEND
	 * @param msPolicyDBGN
	 * @param msPolicyPRT
	 * @return
	 * @throws Exception 
	 */
	public List<Rin1204VOResp9> queryMinLimit(String riskNo, String treatyYear, String policyDEND,
			String policyDBGN, String policyPRT) throws Exception {
		return repository.queryMinLimit(riskNo, treatyYear, policyDEND, policyDBGN, policyPRT);
	}

	/**
	 * 計算此同險已擺放保額
	 * @param treatyYear
	 * @param msDrisk_no
	 * @param msPolicyDEND
	 * @param msPolicyDBGN
	 * @param msPolicyPRT
	 * @param oldPolicyList
	 * @param msOldPolicy
	 * @param nextPolicy
	 * @return
	 * @throws Exception 
	 */
	public List<Rin1204VOResp5> queryMoShareAmt2(String treatyYear, String riskNo, String policyDEND, String policyDBGN,
			String policyPRT, List<String> oldPolicyList, String oldPolicy, String nextPolicy) throws Exception {
		return repository.queryMoShareAmt2(treatyYear, riskNo, policyDEND, policyDBGN, policyPRT, oldPolicyList,
				oldPolicy, nextPolicy);
	}

	/**
	 * 已出帳資料檢核
	 * @param msDpolicyNo
	 * @param msDendorseNo
	 * @param msDaddrNo
	 * @param treatyYear
	 * @param msOtreatyNo
	 * @return
	 * @throws Exception 
	 */
	public List<FriTreatyShares> billedCheck(String msDpolicyNo, String msDendorseNo, Short msDaddrNo,
			String treatyYear, String msOtreatyNo) throws Exception {
		return repository.billedCheck(msDpolicyNo, msDendorseNo, msDaddrNo, treatyYear, msOtreatyNo);
	}

	/**
	 * Insert Treaty Share --[Retain]
	 * @param customerizeMapper
	 * @param rin1204voReq2
	 */
	public int insertFriTreatyShares3(CustomerizeMapper customerizeMapper, Rin1204VOReq2 rin1204voReq2) {
		return repository.insertFriTreatyShares3(customerizeMapper, rin1204voReq2);
	}

	/**
	 * 計算已擺放保額 (依同險角度)
	 * @param rin1204voReq3
	 * @return
	 * @throws Exception 
	 */
	public List<Rin1204VOResp5> queryMoShareAmt3(Rin1204VOReq3 rin1204voReq3) throws Exception {
		return repository.queryMoShareAmt3(rin1204voReq3);
	}

	/**
	 * 07-11.寫入合約分保檔
	 * @param customerizeMapper
	 * @param rin1204voReq2
	 * @return
	 */
	public int insertFriTreatyShares4(CustomerizeMapper customerizeMapper, Rin1204VOReq2 rin1204voReq2) {
		return repository.insertFriTreatyShares4(customerizeMapper, rin1204voReq2);
	}

	/**
	 * [5.5.3.1.3]計算第2自留(SEC_Retain)
	 * @param customerizeMapper
	 * @param rin1204voReq2
	 * @return
	 */
	public int insertFriTreatyShares5(CustomerizeMapper customerizeMapper, Rin1204VOReq2 rin1204voReq2) {
		return repository.insertFriTreatyShares5(customerizeMapper, rin1204voReq2);
	}

	/**
	 * 取得合約分保順序1
	 * @param treatyYear
	 * @param msPolicyType
	 * @return
	 * @throws Exception 
	 */
	public List<Rin1204VOResp1> queryMoTreatyOrder(String treatyYear, String policyType) throws Exception {
		return repository.queryMoTreatyOrder(treatyYear, policyType);
	}

	/**
	 * 計算此同險已擺放保額
	 * @param rin1204voReq3
	 * @return
	 * @throws Exception 
	 */
	public List<Rin1204VOResp5> queryMoShareAmt4(Rin1204VOReq3 rin1204voReq3) throws Exception {
		return repository.queryMoShareAmt4(rin1204voReq3);
	}

	/**
	 * 計算逐單已擺放保額
	 * @param rin1204voReq3
	 * @return
	 * @throws Exception 
	 */
	public List<Rin1204VOResp5> queryMoShareAmt5(Rin1204VOReq3 rin1204voReq3) throws Exception {
		return repository.queryMoShareAmt5(rin1204voReq3);
	}

	/**
	 * 計算合約QS自留額
	 * @param treatyYear
	 * @param policyType
	 * @return
	 * @throws Exception 
	 */
	public List<Rin1204VOResp8> querymMoRetain2(String treatyYear, String policyType) throws Exception {
		return repository.querymMoRetain2(treatyYear, policyType);
	}

	/**
	 * 檢核區間是否已關帳
	 * @param ucRocDbgn
	 * @param ucRocDend
	 * @param riskNo
	 * @return
	 * @throws Exception 
	 */
	public List<Rin1204VOResp> checkIsClose(String ucRocDbgn, String ucRocDend, String riskNo) throws Exception {
		return repository.checkIsClose(ucRocDbgn, ucRocDend, riskNo);
	}
}

package com.asi.huanan.service.dao.mybatis.model.customerize.rin1301;

import java.util.Date;

/**
 * 臨分資料維護主頁查詢
 * @author chewei_hu
 */
public class Rin1301QueryMainData {
	//命名對應SQL查詢的欄位	
	//舊code的QryFacPolicy, QryFacPrintAccount SQL 皆查詢之欄位
	private String policyNo;			//保單號
	private String endorseNo;		//批單號
	private String cessionNo;		//合約編號
	private String cessionName;		//合約名稱
	private String slipNo;		//知會/更正號
	
	//舊code的QryFacPolicy SQL 查詢之欄位
	private String logSeq;			//修改註記
	private String conversionStatus ;		//轉檔狀況
	private String isLog;
	private String printType;			//帳單列印

	//舊code的QryFacPrintAccount SQL 查詢之欄位
	private String tmp;			
	private String acctFlag ;			//是否帳單列印
	private String transferStatus;		//轉檔註
	private String showSeq;
	private String preSlipNo;
	private String preCessionNo;
	private Date treatyDend;
	private String facType;
	
	
	
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getEndorseNo() {
		return endorseNo;
	}
	public void setEndorseNo(String endorseNo) {
		this.endorseNo = endorseNo;
	}
	public String getCessionNo() {
		return cessionNo;
	}
	public void setCessionNo(String cessionNo) {
		this.cessionNo = cessionNo;
	}
	public String getCessionName() {
		return cessionName;
	}
	public void setCessionName(String cessionName) {
		this.cessionName = cessionName;
	}
	public String getSlipNo() {
		return slipNo;
	}
	public void setSlipNo(String slipNo) {
		this.slipNo = slipNo;
	}
	public String getLogSeq() {
		return logSeq;
	}
	public void setLogSeq(String logSeq) {
		this.logSeq = logSeq;
	}
	public String getConversionStatus() {
		return conversionStatus;
	}
	public void setConversionStatus(String conversionStatus) {
		this.conversionStatus = conversionStatus;
	}
	public String getIsLog() {
		return isLog;
	}
	public void setIsLog(String isLog) {
		this.isLog = isLog;
	}
	public String getPrintType() {
		return printType;
	}
	public void setPrintType(String printType) {
		this.printType = printType;
	}
	public String getTmp() {
		return tmp;
	}
	public void setTmp(String tmp) {
		this.tmp = tmp;
	}
	public String getAcctFlag() {
		return acctFlag;
	}
	public void setAcctFlag(String acctFlag) {
		this.acctFlag = acctFlag;
	}
	public String getTransferStatus() {
		return transferStatus;
	}
	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
	}
	public String getShowSeq() {
		return showSeq;
	}
	public void setShowSeq(String showSeq) {
		this.showSeq = showSeq;
	}
	public String getPreSlipNo() {
		return preSlipNo;
	}
	public void setPreSlipNo(String preSlipNo) {
		this.preSlipNo = preSlipNo;
	}
	public String getPreCessionNo() {
		return preCessionNo;
	}
	public void setPreCessionNo(String preCessionNo) {
		this.preCessionNo = preCessionNo;
	}
	public Date getTreatyDend() {
		return treatyDend;
	}
	public void setTreatyDend(Date treatyDend) {
		this.treatyDend = treatyDend;
	}
	public String getFacType() {
		return facType;
	}
	public void setFacType(String facType) {
		this.facType = facType;
	}			
	
	
	
}

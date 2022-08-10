package com.asi.huanan.service.dao.mybatis.model.customerize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1303報表(臨分主檔資料)查詢結果")
public class Rin1303QueryMain {

	//fri_fac
	@ApiModelProperty (value = "立帳日")
	private String treatyDate;	
	@ApiModelProperty (value = "合約年度")
	private String treatyYear;	
	@ApiModelProperty (value = "保批單作業序號")
	private String policyNoSeq;	
	@ApiModelProperty (value = "合約起始日")
	private String treatyDbgn;	
	@ApiModelProperty (value = "合約迄止日")
	private String treatyDend;	
	@ApiModelProperty (value = "日數")
	private String days;	
	@ApiModelProperty (value = "同險被保險人等")
	private String insurant;	
	@ApiModelProperty (value = "保批單號")
	private String policyNo;	
	@ApiModelProperty (value = "境外分入註記")
	private String mkovse;	
	
	
	//fri_fac_rincom
	@ApiModelProperty (value = "帳單列印否")
	private String acctFlag;
	
	
	public String getTreatyDate() {
		return treatyDate;
	}
	public void setTreatyDate(String treatyDate) {
		this.treatyDate = treatyDate;
	}
	public String getTreatyYear() {
		return treatyYear;
	}
	public void setTreatyYear(String treatyYear) {
		this.treatyYear = treatyYear;
	}
	public String getPolicyNoSeq() {
		return policyNoSeq;
	}
	public void setPolicyNoSeq(String policyNoSeq) {
		this.policyNoSeq = policyNoSeq;
	}
	public String getTreatyDbgn() {
		return treatyDbgn;
	}
	public void setTreatyDbgn(String treatyDbgn) {
		this.treatyDbgn = treatyDbgn;
	}
	public String getTreatyDend() {
		return treatyDend;
	}
	public void setTreatyDend(String treatyDend) {
		this.treatyDend = treatyDend;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getInsurant() {
		return insurant;
	}
	public void setInsurant(String insurant) {
		this.insurant = insurant;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getMkovse() {
		return mkovse;
	}
	public void setMkovse(String mkovse) {
		this.mkovse = mkovse;
	}
	public String getAcctFlag() {
		return acctFlag;
	}
	public void setAcctFlag(String acctFlag) {
		this.acctFlag = acctFlag;
	}	
	
	
	
	

	
	
	
	
	

	
	

	
}

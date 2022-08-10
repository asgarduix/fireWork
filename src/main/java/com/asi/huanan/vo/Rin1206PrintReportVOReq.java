package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1206設定列印條件")
public class Rin1206PrintReportVOReq {
	
	@ApiModelProperty (value = "啟動狀態")
	private String rdoMode;
	
	@ApiModelProperty (value = "排程註冊時間")
	private String submitTime;
	
	@ApiModelProperty (value = "選擇狀態")
	private String rdoTKind1;
	@ApiModelProperty (value = "合約年度")
	private String txtTreatyYear;
	@ApiModelProperty (value = "列印狀態-月季半年")
	private String monthPeriod;
	@ApiModelProperty (value = "列印狀態-日期區間")
	private String periodNo;
	@ApiModelProperty (value = "指定合約代號")
	private String treatyNo;
	@ApiModelProperty (value = "使用者帳號")
	private String account;
	
	public String getRdoMode() {
		return rdoMode;
	}
	public void setRdoMode(String rdoMode) {
		this.rdoMode = rdoMode;
	}
	
	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	
	public String getRdoTKind1() {
		return rdoTKind1;
	}
	public void setRdoTKind1(String rdoTKind1) {
		this.rdoTKind1 = rdoTKind1;
	}
	
	public String getTxtTreatyYear() {
		return txtTreatyYear;
	}
	public void setTxtTreatyYear(String txtTreatyYear) {
		this.txtTreatyYear = txtTreatyYear;
	}
	public String getMonthPeriod() {
		return monthPeriod;
	}
	public void setMonthPeriod(String monthPeriod) {
		this.monthPeriod = monthPeriod;
	}
	public String getPeriodNo() {
		return periodNo;
	}
	public void setPeriodNo(String periodNo) {
		this.periodNo = periodNo;
	}
	public String getTreatyNo() {
		return treatyNo;
	}
	public void setTreatyNo(String treatyNo) {
		this.treatyNo = treatyNo;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
}

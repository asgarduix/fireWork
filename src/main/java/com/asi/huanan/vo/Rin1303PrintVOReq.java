package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1303報表查詢條件")
public class Rin1303PrintVOReq {
	
	@ApiModelProperty (value = "已列印否")
	private String acctFlag;
	@ApiModelProperty (value = "轉檔狀況")
	private String turnFlag;
	@ApiModelProperty (value = "帳單類別")
	private String billType;
	@ApiModelProperty (value = "合約號")
	private String cessionNo;
	@ApiModelProperty (value = "更正號(知會號)")
	private String slipNo;
	@ApiModelProperty (value = "再保人代號")
	private String rinComId;
	@ApiModelProperty (value = "使用者帳號")
	private String account;
	
	
	public String getAcctFlag() {
		return acctFlag;
	}
	public void setAcctFlag(String acctFlag) {
		this.acctFlag = acctFlag;
	}
	public String getTurnFlag() {
		return turnFlag;
	}
	public void setTurnFlag(String turnFlag) {
		this.turnFlag = turnFlag;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getCessionNo() {
		return cessionNo;
	}
	public void setCessionNo(String cessionNo) {
		this.cessionNo = cessionNo;
	}
	public String getSlipNo() {
		return slipNo;
	}
	public void setSlipNo(String slipNo) {
		this.slipNo = slipNo;
	}
	public String getRinComId() {
		return rinComId;
	}
	public void setRinComId(String rinComId) {
		this.rinComId = rinComId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	
	
	

	
}

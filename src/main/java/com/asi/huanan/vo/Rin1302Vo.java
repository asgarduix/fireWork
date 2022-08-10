package com.asi.huanan.vo;

import io.swagger.annotations.ApiModelProperty;

public class Rin1302Vo {
	
	@ApiModelProperty (value = "開始日期")
	private String dtaStart;
	@ApiModelProperty (value = "時")
	private String ddlHour;
	@ApiModelProperty (value = "分")
	private String ddlMin;
	@ApiModelProperty (value = "秒")
	private String ddlddlMin;
	@ApiModelProperty (value = "臨份件合約到期")
	private String treaty_dend;
	@ApiModelProperty (value = "使用者帳號")
	private String account;
	
	public String getDtaStart() {
		return dtaStart;
	}
	public void setDtaStart(String dtaStart) {
		this.dtaStart = dtaStart;
	}
	public String getDdlHour() {
		return ddlHour;
	}
	public void setDdlHour(String ddlHour) {
		this.ddlHour = ddlHour;
	}
	public String getDdlMin() {
		return ddlMin;
	}
	public void setDdlMin(String ddlMin) {
		this.ddlMin = ddlMin;
	}
	public String getDdlddlMin() {
		return ddlddlMin;
	}
	public void setDdlddlMin(String ddlddlMin) {
		this.ddlddlMin = ddlddlMin;
	}
	public String getTreaty_dend() {
		return treaty_dend;
	}
	public void setTreaty_dend(String treaty_dend) {
		this.treaty_dend = treaty_dend;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
}

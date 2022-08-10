package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1204執行自動分保")
public class Rin1204VOReq {

	@ApiModelProperty(value = "排程時間")
	private String submitTime;

	@ApiModelProperty(value = "登入帳號")
	private String account;

	@ApiModelProperty(value = "啟動狀態(1:立即執行, 2:排程執行)")
	private String bootStatus;

	@ApiModelProperty(value = "合約年度")
	private String treatyYear;

	@ApiModelProperty(value = "分保起日")
	private String ucRocDbgn;

	@ApiModelProperty(value = "分保迄日")
	private String ucRocDend;

	@ApiModelProperty(value = "同險代號")
	private String riskNo;

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBootStatus() {
		return bootStatus;
	}

	public void setBootStatus(String bootStatus) {
		this.bootStatus = bootStatus;
	}

	public String getTreatyYear() {
		return treatyYear;
	}

	public void setTreatyYear(String treatyYear) {
		this.treatyYear = treatyYear;
	}

	public String getUcRocDbgn() {
		return ucRocDbgn;
	}

	public void setUcRocDbgn(String ucRocDbgn) {
		this.ucRocDbgn = ucRocDbgn;
	}

	public String getUcRocDend() {
		return ucRocDend;
	}

	public void setUcRocDend(String ucRocDend) {
		this.ucRocDend = ucRocDend;
	}

	public String getRiskNo() {
		return riskNo;
	}

	public void setRiskNo(String riskNo) {
		this.riskNo = riskNo;
	}

}

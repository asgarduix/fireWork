package com.asi.huanan.vo;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class Batch002AVo {

	// batchqueue
	@ApiModelProperty(value = "TaskID")
	private String taskid;
	@ApiModelProperty(value = "batch元件")
	private String batchid;
	@ApiModelProperty(value = "開始時間")
	private Date starttime;
	@ApiModelProperty(value = "結束時間")
	private Date endtime;
	@ApiModelProperty(value = "執行狀態")
	private Byte processstatus;
	@ApiModelProperty(value = "")
	private Date submittime;
	@ApiModelProperty(value = "")
	private String account;
	@ApiModelProperty(value = "")
	private String processcontrolid;
	@ApiModelProperty(value = "")
	private String exitcode;

	// batchlist
	@ApiModelProperty(value = "程式名稱")
	private String batchdescription;

	@ApiModelProperty(value = "LogDescription")
	private String logdescription;
	@ApiModelProperty(value = "keyword")
	private String keyword;
	
	@ApiModelProperty(value = "報表選項")
	private String reportType;
	
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public String getBatchid() {
		return batchid;
	}
	public void setBatchid(String batchid) {
		this.batchid = batchid;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public Byte getProcessstatus() {
		return processstatus;
	}
	public void setProcessstatus(Byte processstatus) {
		this.processstatus = processstatus;
	}
	public Date getSubmittime() {
		return submittime;
	}
	public void setSubmittime(Date submittime) {
		this.submittime = submittime;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getProcesscontrolid() {
		return processcontrolid;
	}
	public void setProcesscontrolid(String processcontrolid) {
		this.processcontrolid = processcontrolid;
	}
	public String getExitcode() {
		return exitcode;
	}
	public void setExitcode(String exitcode) {
		this.exitcode = exitcode;
	}
	public String getBatchdescription() {
		return batchdescription;
	}
	public void setBatchdescription(String batchdescription) {
		this.batchdescription = batchdescription;
	}
	public String getLogdescription() {
		return logdescription;
	}
	public void setLogdescription(String logdescription) {
		this.logdescription = logdescription;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
}

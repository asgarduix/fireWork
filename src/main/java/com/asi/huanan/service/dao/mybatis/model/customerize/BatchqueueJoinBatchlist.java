package com.asi.huanan.service.dao.mybatis.model.customerize;

import java.util.Date;

public class BatchqueueJoinBatchlist {
	
	//batchqueue
		private String taskid;		
		private String batchid;
		private Date starttime;
		private Date endtime;
		private Byte processstatus;
		private Date submittime;
		private String account;
		private String processcontrolid;
		private String exitcode;
		
	//batchlist
		private String batchdescription;
		
	//處理過
		private String startDateString;
		private String endDateString;
		private String processstatusString;
		
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

		public String getStartDateString() {
			return startDateString;
		}

		public void setStartDateString(String startDateString) {
			this.startDateString = startDateString;
		}

		public String getEndDateString() {
			return endDateString;
		}

		public void setEndDateString(String endDateString) {
			this.endDateString = endDateString;
		}

		public String getProcessstatusString() {
			return processstatusString;
		}

		public void setProcessstatusString(String processstatusString) {
			this.processstatusString = processstatusString;
		}
}

package com.asi.huanan.service.dao.mybatis.model;

import java.util.Date;

public class Batchqueue {
	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column batchqueue.TaskID
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	private String taskid;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column batchqueue.BatchID
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	private String batchid;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column batchqueue.SubmitTime
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	private Date submittime;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column batchqueue.StartTime
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	private Date starttime;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column batchqueue.EndTime
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	private Date endtime;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column batchqueue.ProcessStatus
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	private Byte processstatus;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column batchqueue.Account
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	private String account;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column batchqueue.ProcessControlID
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	private String processcontrolid;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column batchqueue.ExitCode
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	private String exitcode;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column batchqueue.BatchReprotAccessPath
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	private String batchreprotaccesspath;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column batchqueue.TaskID
	 *
	 * @return the value of batchqueue.TaskID
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	public String getTaskid() {
		return taskid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column batchqueue.TaskID
	 *
	 * @param taskid the value for batchqueue.TaskID
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column batchqueue.BatchID
	 *
	 * @return the value of batchqueue.BatchID
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	public String getBatchid() {
		return batchid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column batchqueue.BatchID
	 *
	 * @param batchid the value for batchqueue.BatchID
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	public void setBatchid(String batchid) {
		this.batchid = batchid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column batchqueue.SubmitTime
	 *
	 * @return the value of batchqueue.SubmitTime
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	public Date getSubmittime() {
		return submittime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column batchqueue.SubmitTime
	 *
	 * @param submittime the value for batchqueue.SubmitTime
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	public void setSubmittime(Date submittime) {
		this.submittime = submittime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column batchqueue.StartTime
	 *
	 * @return the value of batchqueue.StartTime
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	public Date getStarttime() {
		return starttime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column batchqueue.StartTime
	 *
	 * @param starttime the value for batchqueue.StartTime
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column batchqueue.EndTime
	 *
	 * @return the value of batchqueue.EndTime
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	public Date getEndtime() {
		return endtime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column batchqueue.EndTime
	 *
	 * @param endtime the value for batchqueue.EndTime
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column batchqueue.ProcessStatus
	 *
	 * @return the value of batchqueue.ProcessStatus
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	public Byte getProcessstatus() {
		return processstatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column batchqueue.ProcessStatus
	 *
	 * @param processstatus the value for batchqueue.ProcessStatus
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	public void setProcessstatus(Byte processstatus) {
		this.processstatus = processstatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column batchqueue.Account
	 *
	 * @return the value of batchqueue.Account
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column batchqueue.Account
	 *
	 * @param account the value for batchqueue.Account
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column batchqueue.ProcessControlID
	 *
	 * @return the value of batchqueue.ProcessControlID
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	public String getProcesscontrolid() {
		return processcontrolid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column batchqueue.ProcessControlID
	 *
	 * @param processcontrolid the value for batchqueue.ProcessControlID
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	public void setProcesscontrolid(String processcontrolid) {
		this.processcontrolid = processcontrolid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column batchqueue.ExitCode
	 *
	 * @return the value of batchqueue.ExitCode
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	public String getExitcode() {
		return exitcode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column batchqueue.ExitCode
	 *
	 * @param exitcode the value for batchqueue.ExitCode
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	public void setExitcode(String exitcode) {
		this.exitcode = exitcode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column batchqueue.BatchReprotAccessPath
	 *
	 * @return the value of batchqueue.BatchReprotAccessPath
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	public String getBatchreprotaccesspath() {
		return batchreprotaccesspath;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column batchqueue.BatchReprotAccessPath
	 *
	 * @param batchreprotaccesspath the value for batchqueue.BatchReprotAccessPath
	 *
	 * @mbg.generated Mon Dec 06 15:20:23 CST 2021
	 */
	public void setBatchreprotaccesspath(String batchreprotaccesspath) {
		this.batchreprotaccesspath = batchreprotaccesspath;
	}

	// 處理過
	private String endDateString;

	public String getEndDateString() {
		return endDateString;
	}

	public void setEndDateString(String endDateString) {
		this.endDateString = endDateString;
	}
}
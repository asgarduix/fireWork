package com.asi.huanan.vo;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1206帳單明細查詢 &輸出「明細資料」")
public class Rin1206PrintModelVO {
	
	//查詢條件
	@ApiModelProperty (value = "印單起始日")
	private Date policyDprtBgn;
	@ApiModelProperty (value = "印單結束日")
	private Date policyDprtEnd;
	@ApiModelProperty (value = "合約代號")
	private String treatyNo;		
	@ApiModelProperty (value = "合約型態-月季半年")
	private String monthPeriod;
	@ApiModelProperty (value = "合約年度")
	private String treatyYear;	
	@ApiModelProperty (value = "選擇狀態")
	private String rdoTKind1;
	
	public Date getPolicyDprtBgn() {
		return policyDprtBgn;
	}
	public void setPolicyDprtBgn(Date policyDprtBgn) {
		this.policyDprtBgn = policyDprtBgn;
	}
	public Date getPolicyDprtEnd() {
		return policyDprtEnd;
	}
	public void setPolicyDprtEnd(Date policyDprtEnd) {
		this.policyDprtEnd = policyDprtEnd;
	}
	public String getTreatyNo() {
		return treatyNo;
	}
	public void setTreatyNo(String treatyNo) {
		this.treatyNo = treatyNo;
	}
	public String getMonthPeriod() {
		return monthPeriod;
	}
	public void setMonthPeriod(String monthPeriod) {
		this.monthPeriod = monthPeriod;
	}
	public String getTreatyYear() {
		return treatyYear;
	}
	public void setTreatyYear(String treatyYear) {
		this.treatyYear = treatyYear;
	}
	public String getRdoTKind1() {
		return rdoTKind1;
	}
	public void setRdoTKind1(String rdoTKind1) {
		this.rdoTKind1 = rdoTKind1;
	}

//=============================================================

	//保單分保計算紀錄資料選取(明細資料) getContractDetail_sql回傳
	@ApiModelProperty (value = "policy_no")
	private String policy_no;
	@ApiModelProperty (value = "endorse_no")
	private String endorse_no;
	@ApiModelProperty (value = "policy_dbgn")
	private Date policy_dbgn;
	@ApiModelProperty (value = "policy_dend")
	private Date policy_dend;
	@ApiModelProperty (value = "policy_dprt")
	private Date policy_dprt;
	@ApiModelProperty (value = "addr_no")
	private Integer addr_no;
	@ApiModelProperty (value = "amt")
	private BigDecimal amt;
	@ApiModelProperty (value = "prem")
	private BigDecimal prem;
	@ApiModelProperty (value = "limit_no")
	private String limit_no;
	@ApiModelProperty (value = "treaty_year")
	private String treaty_year;
	@ApiModelProperty (value = "treaty_no")
	private String treaty_no;
	@ApiModelProperty (value = "share_amt")
	private BigDecimal share_amt;
	@ApiModelProperty (value = "share_prem")
	private BigDecimal share_prem;
	@ApiModelProperty (value = "acct_flag")
	private String acct_flag;

	public String getPolicy_no() {
		return policy_no;
	}
	public void setPolicy_no(String policy_no) {
		this.policy_no = policy_no;
	}
	public String getEndorse_no() {
		return endorse_no;
	}
	public void setEndorse_no(String endorse_no) {
		this.endorse_no = endorse_no;
	}
	
	public Date getPolicy_dbgn() {
		return policy_dbgn;
	}
	public void setPolicy_dbgn(Date policy_dbgn) {
		this.policy_dbgn = policy_dbgn;
	}
	public Date getPolicy_dend() {
		return policy_dend;
	}
	public void setPolicy_dend(Date policy_dend) {
		this.policy_dend = policy_dend;
	}
	public Date getPolicy_dprt() {
		return policy_dprt;
	}
	public void setPolicy_dprt(Date policy_dprt) {
		this.policy_dprt = policy_dprt;
	}
	public Integer getAddr_no() {
		return addr_no;
	}
	public void setAddr_no(Integer addr_no) {
		this.addr_no = addr_no;
	}
	
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	
	public BigDecimal getPrem() {
		return prem;
	}
	public void setPrem(BigDecimal prem) {
		this.prem = prem;
	}
	public String getLimit_no() {
		return limit_no;
	}
	public void setLimit_no(String limit_no) {
		this.limit_no = limit_no;
	}
	public String getTreaty_year() {
		return treaty_year;
	}
	public void setTreaty_year(String treaty_year) {
		this.treaty_year = treaty_year;
	}
	public String getTreaty_no() {
		return treaty_no;
	}
	public void setTreaty_no(String treaty_no) {
		this.treaty_no = treaty_no;
	}
	
	public BigDecimal getShare_amt() {
		return share_amt;
	}
	public void setShare_amt(BigDecimal share_amt) {
		this.share_amt = share_amt;
	}
	public BigDecimal getShare_prem() {
		return share_prem;
	}
	public void setShare_prem(BigDecimal share_prem) {
		this.share_prem = share_prem;
	}
	public String getAcct_flag() {
		return acct_flag;
	}
	public void setAcct_flag(String acct_flag) {
		this.acct_flag = acct_flag;
	}
//==================================================================	
	//讀取合約再保人資料 CreateRincom_sql回傳
	@ApiModelProperty (value = "policy_no")
	private String rin_com_id;
	@ApiModelProperty (value = "rin_com_seq")
	private String rin_com_seq;
	@ApiModelProperty (value = "polirin_com_sharecy_no")
	private String rin_com_share;
	@ApiModelProperty (value = "rin_com_tax")
	private String rin_com_tax;
	@ApiModelProperty (value = "businesstax_rate")
	private BigDecimal businesstax_rate;
	@ApiModelProperty (value = "handling_rate")
	private BigDecimal handling_rate;
	@ApiModelProperty (value = "stamptax_rate")
	private BigDecimal stamptax_rate;
	@ApiModelProperty (value = "agent_rate")
	private BigDecimal agent_rate;
	@ApiModelProperty (value = "firrulcom_rate")
	private BigDecimal firrulcom_rate;
	@ApiModelProperty (value = "treaty_name")
	private String treaty_name;
	@ApiModelProperty (value = "ename")
	private String ename;
	@ApiModelProperty (value = "other_share")
	private String other_share;
	@ApiModelProperty (value = "share_rate")
	private String share_rate;
	@ApiModelProperty (value = "share_type")
	private String share_type;
	@ApiModelProperty (value = "rin_com_contract_no")
	private String rin_com_contract_no;

	public String getRin_com_id() {
		return rin_com_id;
	}
	public void setRin_com_id(String rin_com_id) {
		this.rin_com_id = rin_com_id;
	}
	public String getRin_com_seq() {
		return rin_com_seq;
	}
	public void setRin_com_seq(String rin_com_seq) {
		this.rin_com_seq = rin_com_seq;
	}
	public String getRin_com_share() {
		return rin_com_share;
	}
	public void setRin_com_share(String rin_com_share) {
		this.rin_com_share = rin_com_share;
	}
	public String getRin_com_tax() {
		return rin_com_tax;
	}
	public void setRin_com_tax(String rin_com_tax) {
		this.rin_com_tax = rin_com_tax;
	}

	public BigDecimal getBusinesstax_rate() {
		return businesstax_rate;
	}
	public void setBusinesstax_rate(BigDecimal businesstax_rate) {
		this.businesstax_rate = businesstax_rate;
	}
	public BigDecimal getHandling_rate() {
		return handling_rate;
	}
	public void setHandling_rate(BigDecimal handling_rate) {
		this.handling_rate = handling_rate;
	}
	public BigDecimal getStamptax_rate() {
		return stamptax_rate;
	}
	public void setStamptax_rate(BigDecimal stamptax_rate) {
		this.stamptax_rate = stamptax_rate;
	}
	public BigDecimal getAgent_rate() {
		return agent_rate;
	}
	public void setAgent_rate(BigDecimal agent_rate) {
		this.agent_rate = agent_rate;
	}
	public BigDecimal getFirrulcom_rate() {
		return firrulcom_rate;
	}
	public void setFirrulcom_rate(BigDecimal firrulcom_rate) {
		this.firrulcom_rate = firrulcom_rate;
	}
	public String getTreaty_name() {
		return treaty_name;
	}
	public void setTreaty_name(String treaty_name) {
		this.treaty_name = treaty_name;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getOther_share() {
		return other_share;
	}
	public void setOther_share(String other_share) {
		this.other_share = other_share;
	}
	public String getShare_rate() {
		return share_rate;
	}
	public void setShare_rate(String share_rate) {
		this.share_rate = share_rate;
	}
	public String getShare_type() {
		return share_type;
	}
	public void setShare_type(String share_type) {
		this.share_type = share_type;
	}
	public String getRin_com_contract_no() {
		return rin_com_contract_no;
	}
	public void setRin_com_contract_no(String rin_com_contract_no) {
		this.rin_com_contract_no = rin_com_contract_no;
	}

//========================================================

	//以下三個日期為輸出報表時使用之字串型別日期
	@ApiModelProperty (value = "policy_dateBgn")
	private String policy_dateBgn;
	@ApiModelProperty (value = "policy_dateEnd")
	private String policy_dateEnd;
	@ApiModelProperty (value = "policy_datePrt")
	private String policy_datePrt;

	public String getPolicy_dateBgn() {
		return policy_dateBgn;
	}
	public void setPolicy_dateBgn(String policy_dateBgn) {
		this.policy_dateBgn = policy_dateBgn;
	}
	public String getPolicy_dateEnd() {
		return policy_dateEnd;
	}
	public void setPolicy_dateEnd(String policy_dateEnd) {
		this.policy_dateEnd = policy_dateEnd;
	}
	public String getPolicy_datePrt() {
		return policy_datePrt;
	}
	public void setPolicy_datePrt(String policy_datePrt) {
		this.policy_datePrt = policy_datePrt;
	}

}

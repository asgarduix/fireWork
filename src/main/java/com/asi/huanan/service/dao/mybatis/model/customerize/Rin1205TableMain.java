package com.asi.huanan.service.dao.mybatis.model.customerize;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1205分保同險資料(main)")
public class Rin1205TableMain {

	@ApiModelProperty (value = "保單號")
	private String policy_no;			
	@ApiModelProperty (value = "批單號")
	private String endorse_no;			
	@ApiModelProperty (value = "序號")
	private String addr_no;			
	@ApiModelProperty (value = "生效日期起")
	private String duty_bgn;	
	@ApiModelProperty (value = "生效日期迄")
	private String duty_end;
	@ApiModelProperty (value = "同險編號")
	private String risk_no;
	@ApiModelProperty (value = "使用性質")
	private String useprop_name;
	@ApiModelProperty (value = "印單日")
	private String policy_dprt;
	@ApiModelProperty (value = "總保額")
	private BigDecimal amt;			
	@ApiModelProperty (value = "費率計算用(prem + prem_typ + prem_ear)")
	private BigDecimal prem;			
	@ApiModelProperty (value = "總保費")
	private BigDecimal share_prem;
	@ApiModelProperty (value = "保單限額")
	private BigDecimal limit;			
	@ApiModelProperty (value = "地震自留計算用")
	private BigDecimal amt_ear;			
	@ApiModelProperty (value = "颱風自留計算用")
	private BigDecimal amt_typ;			
	@ApiModelProperty (value = "帳單註記")
	private String acct_flag;
	@ApiModelProperty (value = "原批單號")
	private String iendorsement2;
	@ApiModelProperty (value = "合約年度")
	private String treaty_year;
	
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
	public String getAddr_no() {
		return addr_no;
	}
	public void setAddr_no(String addr_no) {
		this.addr_no = addr_no;
	}
	public String getDuty_bgn() {
		return duty_bgn;
	}
	public void setDuty_bgn(String duty_bgn) {
		this.duty_bgn = duty_bgn;
	}
	public String getDuty_end() {
		return duty_end;
	}
	public void setDuty_end(String duty_end) {
		this.duty_end = duty_end;
	}
	public String getRisk_no() {
		return risk_no;
	}
	public void setRisk_no(String risk_no) {
		this.risk_no = risk_no;
	}
	public String getUseprop_name() {
		return useprop_name;
	}
	public void setUseprop_name(String useprop_name) {
		this.useprop_name = useprop_name;
	}
	public String getPolicy_dprt() {
		return policy_dprt;
	}
	public void setPolicy_dprt(String policy_dprt) {
		this.policy_dprt = policy_dprt;
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
	public BigDecimal getShare_prem() {
		return share_prem;
	}
	public void setShare_prem(BigDecimal share_prem) {
		this.share_prem = share_prem;
	}
	public BigDecimal getLimit() {
		return limit;
	}
	public void setLimit(BigDecimal limit) {
		this.limit = limit;
	}
	public BigDecimal getAmt_ear() {
		return amt_ear;
	}
	public void setAmt_ear(BigDecimal amt_ear) {
		this.amt_ear = amt_ear;
	}
	public BigDecimal getAmt_typ() {
		return amt_typ;
	}
	public void setAmt_typ(BigDecimal amt_typ) {
		this.amt_typ = amt_typ;
	}
	public String getAcct_flag() {
		return acct_flag;
	}
	public void setAcct_flag(String acct_flag) {
		this.acct_flag = acct_flag;
	}
	public String getIendorsement2() {
		return iendorsement2;
	}
	public void setIendorsement2(String iendorsement2) {
		this.iendorsement2 = iendorsement2;
	}
	public String getTreaty_year() {
		return treaty_year;
	}
	public void setTreaty_year(String treaty_year) {
		this.treaty_year = treaty_year;
	}
	


	
}

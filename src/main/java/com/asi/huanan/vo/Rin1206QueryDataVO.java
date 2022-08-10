package com.asi.huanan.vo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1206總表 &再保人 資料查詢用")
public class Rin1206QueryDataVO {

	@ApiModelProperty (value = "treaty_year")
	private Integer treaty_year;
	@ApiModelProperty (value = "treaty_name")
	private String treaty_name;
	@ApiModelProperty (value = "treaty_no")
	private String treaty_no;
	@ApiModelProperty (value = "treaty_sname")
	private String treaty_sname;
	@ApiModelProperty (value = "Share_rate")
	private BigDecimal Share_rate;
	@ApiModelProperty (value = "firrulcom_rate")
	private BigDecimal firrulcom_rate;
	@ApiModelProperty (value = "dprt_month")
	private String dprt_month;
	@ApiModelProperty (value = "treaty_amts")
	private BigDecimal treaty_amts;
	@ApiModelProperty (value = "treaty_prems")
	private BigDecimal treaty_prems;
	@ApiModelProperty (value = "ripaids")
	private BigDecimal ripaids;
	
	private BigDecimal other_share;

	public Integer getTreaty_year() {
		return treaty_year;
	}
	public void setTreaty_year(Integer treaty_year) {
		this.treaty_year = treaty_year;
	}
	public String getTreaty_name() {
		return treaty_name;
	}
	public void setTreaty_name(String treaty_name) {
		this.treaty_name = treaty_name;
	}
	public String getTreaty_no() {
		return treaty_no;
	}
	public void setTreaty_no(String treaty_no) {
		this.treaty_no = treaty_no;
	}
	public String getTreaty_sname() {
		return treaty_sname;
	}
	public void setTreaty_sname(String treaty_sname) {
		this.treaty_sname = treaty_sname;
	}
	public BigDecimal getShare_rate() {
		return Share_rate;
	}
	public void setShare_rate(BigDecimal share_rate) {
		Share_rate = share_rate;
	}
	public BigDecimal getFirrulcom_rate() {
		return firrulcom_rate;
	}
	public void setFirrulcom_rate(BigDecimal firrulcom_rate) {
		this.firrulcom_rate = firrulcom_rate;
	}
	public String getDprt_month() {
		return dprt_month;
	}
	public void setDprt_month(String dprt_month) {
		this.dprt_month = dprt_month;
	}
	public BigDecimal getTreaty_amts() {
		return treaty_amts;
	}
	public void setTreaty_amts(BigDecimal treaty_amts) {
		this.treaty_amts = treaty_amts;
	}
	public BigDecimal getTreaty_prems() {
		return treaty_prems;
	}
	public void setTreaty_prems(BigDecimal treaty_prems) {
		this.treaty_prems = treaty_prems;
	}
	public BigDecimal getRipaids() {
		return ripaids;
	}
	public void setRipaids(BigDecimal ripaids) {
		this.ripaids = ripaids;
	}
	public BigDecimal getOther_share() {
		return other_share;
	}
	public void setOther_share(BigDecimal other_share) {
		this.other_share = other_share;
	}
	
	
}

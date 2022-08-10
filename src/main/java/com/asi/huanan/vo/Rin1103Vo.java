package com.asi.huanan.vo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1103表格欄位")
public class Rin1103Vo {

	@ApiModelProperty (value = "合約年度")
	private String txttreaty_year;			//合約年度
	@ApiModelProperty (value = "合約編號")
	private String txttreaty_no;			//合約編號
	@ApiModelProperty (value = "梯次佣金類別")
	private String txtcomm_type;			//梯次佣金類別
	@ApiModelProperty (value = "損失率上限百分比")
	private BigDecimal numupper_limit;		//損失率上限百分比
	@ApiModelProperty (value = "損失率下限百分比")
	private BigDecimal numlower_limit;		//損失率下限百分比
	@ApiModelProperty(value = "佣金百分比")
	private BigDecimal numcomm_rate;		//佣金百分比
	@ApiModelProperty (value = "序號")
	private short serial;					//序號

	public String getTxttreaty_year() {
		return txttreaty_year;
	}

	public void setTxttreaty_year(String txttreaty_year) {
		this.txttreaty_year = txttreaty_year;
	}

	public String getTxttreaty_no() {
		return txttreaty_no;
	}

	public void setTxttreaty_no(String txttreaty_no) {
		this.txttreaty_no = txttreaty_no;
	}

	public String getTxtcomm_type() {
		return txtcomm_type;
	}

	public void setTxtcomm_type(String txtcomm_type) {
		this.txtcomm_type = txtcomm_type;
	}

	public BigDecimal getNumupper_limit() {
		return numupper_limit;
	}

	public void setNumupper_limit(BigDecimal numupper_limit) {
		this.numupper_limit = numupper_limit;
	}

	public BigDecimal getNumlower_limit() {
		return numlower_limit;
	}

	public void setNumlower_limit(BigDecimal numlower_limit) {
		this.numlower_limit = numlower_limit;
	}

	public BigDecimal getNumcomm_rate() {
		return numcomm_rate;
	}

	public void setNumcomm_rate(BigDecimal numcomm_rate) {
		this.numcomm_rate = numcomm_rate;
	}

	public short getSerial() {
		return serial;
	}

	public void setSerial(short serial) {
		this.serial = serial;
	}
	
	

	
	
}

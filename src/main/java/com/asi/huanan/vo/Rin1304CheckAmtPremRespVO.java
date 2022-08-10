package com.asi.huanan.vo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1304反算_附加險總額=華南保費保額")
public class Rin1304CheckAmtPremRespVO {
	@ApiModelProperty (value = "保單號碼")
	private String policyNo;
	
	@ApiModelProperty (value = "批單號碼")
	private String endorseNo;
	
	@ApiModelProperty (value = "保單明細加總保額")
	private BigDecimal amtDtl;
	
	@ApiModelProperty (value = "保單明細加總保費")
	private BigDecimal premDtl;
	
	@ApiModelProperty (value = "總保額")
	private BigDecimal amt;
	
	@ApiModelProperty (value = "總保費")
	private BigDecimal prem;

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getEndorseNo() {
		return endorseNo;
	}

	public void setEndorseNo(String endorseNo) {
		this.endorseNo = endorseNo;
	}

	public BigDecimal getAmtDtl() {
		return amtDtl;
	}

	public void setAmtDtl(BigDecimal amtDtl) {
		this.amtDtl = amtDtl;
	}

	public BigDecimal getPremDtl() {
		return premDtl;
	}

	public void setPremDtl(BigDecimal premDtl) {
		this.premDtl = premDtl;
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

	
}

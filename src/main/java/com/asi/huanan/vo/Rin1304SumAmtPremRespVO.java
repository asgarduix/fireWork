package com.asi.huanan.vo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1304反算用_保額/保費加總")
public class Rin1304SumAmtPremRespVO {
	@ApiModelProperty (value = "保單號碼")
	private String policyNo;
	
	@ApiModelProperty (value = "批單號碼")
	private String endorseNo;
	
	@ApiModelProperty (value = "地址序號")
	private String addrNo;
	
	@ApiModelProperty (value = "標的物序號")
	private String propNo;
	
	@ApiModelProperty (value = "保額")
	private BigDecimal amt;
	
	@ApiModelProperty (value = "保費")
	private BigDecimal prem;

	@ApiModelProperty (value = "颱洪本單總保額")
	private BigDecimal amtTyp;
	
	@ApiModelProperty (value = "颱洪本單總保費")
	private BigDecimal premTyp;
	
	@ApiModelProperty (value = "地震本單總保額")
	private BigDecimal amtEar;
	
	@ApiModelProperty (value = "地震本單總保費")
	private BigDecimal premEar;

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

	public String getAddrNo() {
		return addrNo;
	}

	public void setAddrNo(String addrNo) {
		this.addrNo = addrNo;
	}

	public String getPropNo() {
		return propNo;
	}

	public void setPropNo(String propNo) {
		this.propNo = propNo;
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

	public BigDecimal getAmtTyp() {
		return amtTyp;
	}

	public void setAmtTyp(BigDecimal amtTyp) {
		this.amtTyp = amtTyp;
	}

	public BigDecimal getPremTyp() {
		return premTyp;
	}

	public void setPremTyp(BigDecimal premTyp) {
		this.premTyp = premTyp;
	}

	public BigDecimal getAmtEar() {
		return amtEar;
	}

	public void setAmtEar(BigDecimal amtEar) {
		this.amtEar = amtEar;
	}

	public BigDecimal getPremEar() {
		return premEar;
	}

	public void setPremEar(BigDecimal premEar) {
		this.premEar = premEar;
	}

	
}

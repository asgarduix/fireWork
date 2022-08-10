package com.asi.huanan.vo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1204執行自動分保，讀取合約分保順序")
public class Rin1204VOResp1 {

	@ApiModelProperty(value = "保單種類")
	private String policyType;

	@ApiModelProperty(value = "合約年度")
	private String treatyYear;

	@ApiModelProperty(value = "合約代號")
	private String treatyNo;

	@ApiModelProperty(value = "帳單製作期")
	private String acctType;

	@ApiModelProperty(value = "合約型態")
	private String treatyMode;

	@ApiModelProperty(value = "限額基礎")
	private String limitBase;

	@ApiModelProperty(value = "分入方式")
	private String shareType;

	@ApiModelProperty(value = "分入比率")
	private BigDecimal shareRate;

	@ApiModelProperty(value = "一般限額")
	private Long limitGeneral;

	@ApiModelProperty(value = "共保限額")
	private Long limitTotal;

	@ApiModelProperty(value = "自留額倍數")
	private BigDecimal retainTimes;

	@ApiModelProperty(value = "比較基礎合約代號")
	private String refTreatyNo;

	@ApiModelProperty(value = "共保比率")
	private BigDecimal coinsRate;

	@ApiModelProperty(value = "分保順序")
	private Short shareOrder;

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public String getTreatyYear() {
		return treatyYear;
	}

	public void setTreatyYear(String treatyYear) {
		this.treatyYear = treatyYear;
	}

	public String getTreatyNo() {
		return treatyNo;
	}

	public void setTreatyNo(String treatyNo) {
		this.treatyNo = treatyNo;
	}

	public String getAcctType() {
		return acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}

	public String getTreatyMode() {
		return treatyMode;
	}

	public void setTreatyMode(String treatyMode) {
		this.treatyMode = treatyMode;
	}

	public String getLimitBase() {
		return limitBase;
	}

	public void setLimitBase(String limitBase) {
		this.limitBase = limitBase;
	}

	public String getShareType() {
		return shareType;
	}

	public void setShareType(String shareType) {
		this.shareType = shareType;
	}

	public BigDecimal getShareRate() {
		return shareRate;
	}

	public void setShareRate(BigDecimal shareRate) {
		this.shareRate = shareRate;
	}

	public Long getLimitGeneral() {
		return limitGeneral;
	}

	public void setLimitGeneral(Long limitGeneral) {
		this.limitGeneral = limitGeneral;
	}

	public Long getLimitTotal() {
		return limitTotal;
	}

	public void setLimitTotal(Long limitTotal) {
		this.limitTotal = limitTotal;
	}

	public BigDecimal getRetainTimes() {
		return retainTimes;
	}

	public void setRetainTimes(BigDecimal retainTimes) {
		this.retainTimes = retainTimes;
	}

	public String getRefTreatyNo() {
		return refTreatyNo;
	}

	public void setRefTreatyNo(String refTreatyNo) {
		this.refTreatyNo = refTreatyNo;
	}

	public BigDecimal getCoinsRate() {
		return coinsRate;
	}

	public void setCoinsRate(BigDecimal coinsRate) {
		this.coinsRate = coinsRate;
	}

	public Short getShareOrder() {
		return shareOrder;
	}

	public void setShareOrder(Short shareOrder) {
		this.shareOrder = shareOrder;
	}

}
package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1303報表")
public class Rin1303PrintVO {
	
	@ApiModelProperty (value = "RefNo(1)")
	private String refNo;
	@ApiModelProperty (value = "英文名稱(2)")
	private String ename;
	@ApiModelProperty (value = "合約號+再保人代號+保批單作業號(3)")
	private String cessionNo;
	@ApiModelProperty (value = "Slip編號(4)")
	private String slipNo;
	@ApiModelProperty (value = "立帳日(5)")
	private String period;	
	@ApiModelProperty (value = "幣別(26)")
	private String currency;
	@ApiModelProperty (value = "分出比率(百分比)(6)")
	private String shareRate;
	@ApiModelProperty (value = "(借方)再保費/分出再保費(7)")
	private String premiumDeb;
	@ApiModelProperty (value = "(貸方)再保費/分出再保費(8)")
	private String premiumCred;
	@ApiModelProperty (value = "佣金率(9)")
	private String commRate;
	@ApiModelProperty (value = "(借方)佣金(10)")
	private String commissionDeb;
	@ApiModelProperty (value = "(貸方)佣金(11)")
	private String commissionCred;
	@ApiModelProperty (value = "營業稅率(12)")
	private String taxRate;
	@ApiModelProperty (value = "(借方)營業稅(13)")
	private String businessTaxDeb;
	@ApiModelProperty (value = "(貸方)營業稅(14)")
	private String businessTaxCred;
	@ApiModelProperty (value = "代理率(15)")
	private String brokerRate;
	@ApiModelProperty (value = "(借方)經紀人佣金(28)")
	private String brokerFeeDeb;
	@ApiModelProperty (value = "(貸方)經紀人佣金(29)")
	private String brokerFeeCred;
	@ApiModelProperty (value = "折讓率(16)")
	private String discountRate;
	@ApiModelProperty (value = "(借方)折讓費(30)")
	private String discountFeeDeb;
	@ApiModelProperty (value = "(貸方)折讓費(31)")
	private String discountFeeCred;
	@ApiModelProperty (value = "處理率(27)")
	private String handlingRate;
	@ApiModelProperty (value = "(借方)處理費(32)")
	private String handlingFeeDeb;
	@ApiModelProperty (value = "(貸方)處理費(33)")
	private String handlingFeeCred;
	@ApiModelProperty (value = "餘額(34)")
	private String balanceDue;
	@ApiModelProperty (value = "(借方)餘額(17)")
	private String balanceDueDeb;
	@ApiModelProperty (value = "(貸方)餘額(35)")
	private String balanceDueCred;
	@ApiModelProperty (value = "(借方)加總(18)")
	private String subTotalDeb;
	@ApiModelProperty (value = "(貸方)加總(19)")
	private String subTotalCred;
	@ApiModelProperty (value = "同險被保人等(20)")
	private String insurant;
	@ApiModelProperty (value = "合約起迄日與日數(21)")
	private String periodRange;
	@ApiModelProperty (value = "保批單號(22)")
	private String policyNo;
	@ApiModelProperty (value = "英文名稱(23)")
	private String rinComSname;
	@ApiModelProperty (value = "經紀人分出比率(24)")
	private String brokerShareRate;	
	@ApiModelProperty (value = "希望付款日(25)")
	private String paidDateExpect;
	
	
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getCessionNo() {
		return cessionNo;
	}
	public void setCessionNo(String cessionNo) {
		this.cessionNo = cessionNo;
	}
	public String getSlipNo() {
		return slipNo;
	}
	public void setSlipNo(String slipNo) {
		this.slipNo = slipNo;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getShareRate() {
		return shareRate;
	}
	public void setShareRate(String shareRate) {
		this.shareRate = shareRate;
	}
	public String getPremiumDeb() {
		return premiumDeb;
	}
	public void setPremiumDeb(String premiumDeb) {
		this.premiumDeb = premiumDeb;
	}
	public String getPremiumCred() {
		return premiumCred;
	}
	public void setPremiumCred(String premiumCred) {
		this.premiumCred = premiumCred;
	}
	public String getCommRate() {
		return commRate;
	}
	public void setCommRate(String commRate) {
		this.commRate = commRate;
	}
	public String getCommissionDeb() {
		return commissionDeb;
	}
	public void setCommissionDeb(String commissionDeb) {
		this.commissionDeb = commissionDeb;
	}
	public String getCommissionCred() {
		return commissionCred;
	}
	public void setCommissionCred(String commissionCred) {
		this.commissionCred = commissionCred;
	}
	public String getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}
	public String getBusinessTaxDeb() {
		return businessTaxDeb;
	}
	public void setBusinessTaxDeb(String businessTaxDeb) {
		this.businessTaxDeb = businessTaxDeb;
	}
	public String getBusinessTaxCred() {
		return businessTaxCred;
	}
	public void setBusinessTaxCred(String businessTaxCred) {
		this.businessTaxCred = businessTaxCred;
	}
	public String getBrokerRate() {
		return brokerRate;
	}
	public void setBrokerRate(String brokerRate) {
		this.brokerRate = brokerRate;
	}
	public String getBrokerFeeDeb() {
		return brokerFeeDeb;
	}
	public void setBrokerFeeDeb(String brokerFeeDeb) {
		this.brokerFeeDeb = brokerFeeDeb;
	}
	public String getBrokerFeeCred() {
		return brokerFeeCred;
	}
	public void setBrokerFeeCred(String brokerFeeCred) {
		this.brokerFeeCred = brokerFeeCred;
	}
	public String getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(String discountRate) {
		this.discountRate = discountRate;
	}
	public String getDiscountFeeDeb() {
		return discountFeeDeb;
	}
	public void setDiscountFeeDeb(String discountFeeDeb) {
		this.discountFeeDeb = discountFeeDeb;
	}
	public String getDiscountFeeCred() {
		return discountFeeCred;
	}
	public void setDiscountFeeCred(String discountFeeCred) {
		this.discountFeeCred = discountFeeCred;
	}
	public String getHandlingRate() {
		return handlingRate;
	}
	public void setHandlingRate(String handlingRate) {
		this.handlingRate = handlingRate;
	}
	public String getHandlingFeeDeb() {
		return handlingFeeDeb;
	}
	public void setHandlingFeeDeb(String handlingFeeDeb) {
		this.handlingFeeDeb = handlingFeeDeb;
	}
	public String getHandlingFeeCred() {
		return handlingFeeCred;
	}
	public void setHandlingFeeCred(String handlingFeeCred) {
		this.handlingFeeCred = handlingFeeCred;
	}
	public String getBalanceDue() {
		return balanceDue;
	}
	public void setBalanceDue(String balanceDue) {
		this.balanceDue = balanceDue;
	}
	public String getBalanceDueDeb() {
		return balanceDueDeb;
	}
	public void setBalanceDueDeb(String balanceDueDeb) {
		this.balanceDueDeb = balanceDueDeb;
	}
	public String getBalanceDueCred() {
		return balanceDueCred;
	}
	public void setBalanceDueCred(String balanceDueCred) {
		this.balanceDueCred = balanceDueCred;
	}
	public String getSubTotalDeb() {
		return subTotalDeb;
	}
	public void setSubTotalDeb(String subTotalDeb) {
		this.subTotalDeb = subTotalDeb;
	}
	public String getSubTotalCred() {
		return subTotalCred;
	}
	public void setSubTotalCred(String subTotalCred) {
		this.subTotalCred = subTotalCred;
	}
	public String getInsurant() {
		return insurant;
	}
	public void setInsurant(String insurant) {
		this.insurant = insurant;
	}
	public String getPeriodRange() {
		return periodRange;
	}
	public void setPeriodRange(String periodRange) {
		this.periodRange = periodRange;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getRinComSname() {
		return rinComSname;
	}
	public void setRinComSname(String rinComSname) {
		this.rinComSname = rinComSname;
	}
	public String getBrokerShareRate() {
		return brokerShareRate;
	}
	public void setBrokerShareRate(String brokerShareRate) {
		this.brokerShareRate = brokerShareRate;
	}
	public String getPaidDateExpect() {
		return paidDateExpect;
	}
	public void setPaidDateExpect(String paidDateExpect) {
		this.paidDateExpect = paidDateExpect;
	}	
	
	
	
	
	

	
	

	
}

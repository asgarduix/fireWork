package com.asi.huanan.service.dao.mybatis.model.customerize;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1303報表(臨分再保人檔資料)查詢結果")
public class Rin1303Query2 {

	//fri_fac_rincom
	@ApiModelProperty (value = "分出比率(百分比)")
	private String shareRate;
	@ApiModelProperty (value = "分出再保費")
	private String cedePrem;
	@ApiModelProperty (value = "佣金率")
	private String commRate;
	@ApiModelProperty (value = "營業稅率")
	private String taxRate;
	@ApiModelProperty (value = "處理率")
	private String handlingRate;
	@ApiModelProperty (value = "代理率")
	private String brokerRate;
	@ApiModelProperty (value = "折讓率")
	private String discountRate;
	@ApiModelProperty (value = "RefNo")
	private String refNo;
	@ApiModelProperty (value = "希望付款日")
	private String paidDateExpect;
	@ApiModelProperty (value = "轉檔註")
	private String transferStatus;
	@ApiModelProperty (value = "佣金(原幣)")
	private BigDecimal orgcomm;
	@ApiModelProperty (value = "營業稅(原幣)")
	private BigDecimal orgtax;
	@ApiModelProperty (value = "再保費(原幣)")
	private BigDecimal orgprem;
	
	
	//fri_com
	@ApiModelProperty (value = "英文名稱")
	private String ename;


	public String getShareRate() {
		return shareRate;
	}


	public void setShareRate(String shareRate) {
		this.shareRate = shareRate;
	}


	public String getCedePrem() {
		return cedePrem;
	}


	public void setCedePrem(String cedePrem) {
		this.cedePrem = cedePrem;
	}


	public String getCommRate() {
		return commRate;
	}


	public void setCommRate(String commRate) {
		this.commRate = commRate;
	}


	public String getTaxRate() {
		return taxRate;
	}


	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}


	public String getHandlingRate() {
		return handlingRate;
	}


	public void setHandlingRate(String handlingRate) {
		this.handlingRate = handlingRate;
	}


	public String getBrokerRate() {
		return brokerRate;
	}


	public void setBrokerRate(String brokerRate) {
		this.brokerRate = brokerRate;
	}


	public String getDiscountRate() {
		return discountRate;
	}


	public void setDiscountRate(String discountRate) {
		this.discountRate = discountRate;
	}


	public String getRefNo() {
		return refNo;
	}


	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}


	public String getPaidDateExpect() {
		return paidDateExpect;
	}


	public void setPaidDateExpect(String paidDateExpect) {
		this.paidDateExpect = paidDateExpect;
	}


	public String getTransferStatus() {
		return transferStatus;
	}


	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
	}

	
	public BigDecimal getOrgcomm() {
		return orgcomm;
	}


	public void setOrgcomm(BigDecimal orgcomm) {
		this.orgcomm = orgcomm;
	}


	public BigDecimal getOrgtax() {
		return orgtax;
	}


	public void setOrgtax(BigDecimal orgtax) {
		this.orgtax = orgtax;
	}


	public BigDecimal getOrgprem() {
		return orgprem;
	}


	public void setOrgprem(BigDecimal orgprem) {
		this.orgprem = orgprem;
	}


	public String getEname() {
		return ename;
	}


	public void setEname(String ename) {
		this.ename = ename;
	}

}

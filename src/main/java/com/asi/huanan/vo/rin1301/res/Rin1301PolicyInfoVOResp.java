package com.asi.huanan.vo.rin1301.res;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "臨分資料維護內頁查詢臨分保批單清單")
public class Rin1301PolicyInfoVOResp {
	@ApiModelProperty(value = "保單號")
	private String policyNo;

	@ApiModelProperty(value = "批單號")
	private String endorseNo;
	
	@ApiModelProperty(value = "合約號")
	private String cessionNo;

	@ApiModelProperty(value = "同險代號")
	private String riskNo;
	
	@ApiModelProperty(value = "地址序號")
	private String addrNo;
	
	@ApiModelProperty(value = "保額")
	private BigDecimal amt;
	
	@ApiModelProperty(value = "境外分入註記")
	private String mkovse;

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

	public String getRiskNo() {
		return riskNo;
	}

	public void setRiskNo(String riskNo) {
		this.riskNo = riskNo;
	}

	public String getAddrNo() {
		return addrNo;
	}

	public void setAddrNo(String addrNo) {
		this.addrNo = addrNo;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public String getMkovse() {
		return mkovse;
	}

	public void setMkovse(String mkovse) {
		this.mkovse = mkovse;
	}

	public String getCessionNo() {
		return cessionNo;
	}

	public void setCessionNo(String cessionNo) {
		this.cessionNo = cessionNo;
	}
	
	
	
}

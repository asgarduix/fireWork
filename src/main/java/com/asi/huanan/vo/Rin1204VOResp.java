package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1204執行自動分保，查詢同險未處理保單")
public class Rin1204VOResp {

	@ApiModelProperty(value = "保單號")
	private String policyNo;

	@ApiModelProperty(value = "批單號")
	private String endorseNo;

	@ApiModelProperty(value = "地址序號")
	private Short addrNo;

	@ApiModelProperty(value = "標的物地址")
	private String propAddr;

	@ApiModelProperty(value = "郵遞區號")
	private String zipCode;

	@ApiModelProperty(value = "地段代碼")
	private String areaCode;

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

	public Short getAddrNo() {
		return addrNo;
	}

	public void setAddrNo(Short addrNo) {
		this.addrNo = addrNo;
	}

	public String getPropAddr() {
		return propAddr;
	}

	public void setPropAddr(String propAddr) {
		this.propAddr = propAddr;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

}
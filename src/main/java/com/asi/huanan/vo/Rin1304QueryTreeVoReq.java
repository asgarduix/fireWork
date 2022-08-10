package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1304查詢樹條件物件")
public class Rin1304QueryTreeVoReq {
	@ApiModelProperty(value = "保單號")
	private String policyNo;
	@ApiModelProperty(value = "批單號")
	private String endorseNo;
	@ApiModelProperty(value = "住址號")
	private String addrNo;
	@ApiModelProperty(value = "標的物號")
	private String propNo;

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getEndorseNo() {
		return endorseNo;
	}

	public String getAddrNo() {
		return addrNo;
	}

	public String getPropNo() {
		return propNo;
	}

	public void setEndorseNo(String endorseNo) {
		this.endorseNo = endorseNo;
	}

	public void setAddrNo(String addrNo) {
		this.addrNo = addrNo;
	}

	public void setPropNo(String propNo) {
		this.propNo = propNo;
	}

}

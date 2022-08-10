package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1304_取得地址序號")
public class Rin1304GetAddrNoVO {

	@ApiModelProperty (value = "保單號碼")
	private String policyNo;
	
	@ApiModelProperty (value = "批單號碼")
	private String endorseNo;
	
	@ApiModelProperty (value = "地址序號")
	private String addrNo;

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
	
	
}

package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1304保批單明細更新保單地址序號")
public class Rin1304UpdateAddrNoOriResVO {
	@ApiModelProperty (value = "保單號碼")
	private String policyNo;
	
	@ApiModelProperty (value = "批單號碼")
	private String endorseNo;
	
	@ApiModelProperty (value = "保單地址序號")
	private String addrNoOri;

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

	public String getAddrNoOri() {
		return addrNoOri;
	}

	public void setAddrNoOri(String addrNoOri) {
		this.addrNoOri = addrNoOri;
	}

	
	
}

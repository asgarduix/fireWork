package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1204執行自動分保，查詢該批單(不慮保單)是否有新續保單")
public class Rin1204VOResp4 {

	@ApiModelProperty(value = "保單號")
	private String policyNo;

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

}
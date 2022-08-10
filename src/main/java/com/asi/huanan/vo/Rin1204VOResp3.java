package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1204執行自動分保，查詢該保單之同險是否有續單保單")
public class Rin1204VOResp3 {

	@ApiModelProperty(value = "續保單號")
	private String oldPolicy;

	public String getOldPolicy() {
		return oldPolicy;
	}

	public void setOldPolicy(String oldPolicy) {
		this.oldPolicy = oldPolicy;
	}

}
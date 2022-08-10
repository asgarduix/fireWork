package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1204執行自動分保，查詢臨分標的物檔")
public class Rin1204VOResp7 {

	@ApiModelProperty(value = "保額加總")
	private Long exAmt;

	public Long getExAmt() {
		return exAmt;
	}

	public void setExAmt(Long exAmt) {
		this.exAmt = exAmt;
	}

}
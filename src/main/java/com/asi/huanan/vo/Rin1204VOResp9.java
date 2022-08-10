package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1204執行自動分保，取得保單限額")
public class Rin1204VOResp9 {

	@ApiModelProperty(value = "保單限額")
	private Long minLimit;

	public Long getMinLimit() {
		return minLimit;
	}

	public void setMinLimit(Long minLimit) {
		this.minLimit = minLimit;
	}

}
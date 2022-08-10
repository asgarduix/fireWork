package com.asi.huanan.vo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1204執行自動分保，查詢臨分再保人檔分出比率 (百分比) % 加總")
public class Rin1204VOResp8 {

	@ApiModelProperty(value = "分出比率 (百分比) % 加總")
	private BigDecimal exShareRate;

	public BigDecimal getExShareRate() {
		return exShareRate;
	}

	public void setExShareRate(BigDecimal exShareRate) {
		this.exShareRate = exShareRate;
	}

}
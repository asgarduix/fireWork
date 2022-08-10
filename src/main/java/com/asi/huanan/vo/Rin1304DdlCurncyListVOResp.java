package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1304A其他資訊幣別下拉選單")
public class Rin1304DdlCurncyListVOResp {
	@ApiModelProperty (value = "幣別")
	private String curncy;
	
	@ApiModelProperty (value = "匯率")
	private String exRate;

	public String getCurncy() {
		return curncy;
	}

	public void setCurncy(String curncy) {
		this.curncy = curncy;
	}

	public String getExRate() {
		return exRate;
	}

	public void setExRate(String exRate) {
		this.exRate = exRate;
	}
	
	
}

package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "再保人/經紀人代號")
public class Rin1102AQueryOneReinserVOReq {
	
	@ApiModelProperty (value = "再保人/經紀人代號")
	private String reinser;

	public String getReinser() {
		return reinser;
	}

	public void setReinser(String reinser) {
		this.reinser = reinser;
	}  
}

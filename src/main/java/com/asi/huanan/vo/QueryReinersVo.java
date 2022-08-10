package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "搜尋「再保人」")
public class QueryReinersVo {
	
	@ApiModelProperty (value = "再保人代號")
	private String rinComId;  

	public String getRinComId() {
		return rinComId;
	}

	public void setRinComId(String rinComId) {
		this.rinComId = rinComId;
	}
	
	

}

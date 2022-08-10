package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "查詢地段代號資料")
public class QueryByNumbeCodeVo {

	@ApiModelProperty (notes = "地段代號")
	private String number_code;

	public String getNumber_code() {
		return number_code;
	}

	public void setNumber_code(String number_code) {
		this.number_code = number_code;
	}
	
}

package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1206合約代號查詢條件")
public class Rin1206QueryTreatyNoVOReq {
	
	@ApiModelProperty (value = "合約年度")
	private String treaty_year;

	public String getTreaty_year() {
		return treaty_year;
	}

	public void setTreaty_year(String treaty_year) {
		this.treaty_year = treaty_year;
	}
	
	
}

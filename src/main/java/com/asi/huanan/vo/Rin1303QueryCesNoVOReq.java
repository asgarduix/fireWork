package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1303「合約號資料」搜尋條件")
public class Rin1303QueryCesNoVOReq {
	
	@ApiModelProperty (value = "合約號")
	private String cessionNo;

	public String getCessionNo() {
		return cessionNo;
	}

	public void setCessionNo(String cessionNo) {
		this.cessionNo = cessionNo;
	}  
	

	
	

	
}

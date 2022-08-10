package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1303「臨分再保人資料」搜尋條件")
public class Rin1303QueryRinComBySlipNoVOReq {
	
	@ApiModelProperty (value = "更正號")
	private String slipNo;

	public String getSlipNo() {
		return slipNo;
	}

	public void setSlipNo(String slipNo) {
		this.slipNo = slipNo;
	}
	
}

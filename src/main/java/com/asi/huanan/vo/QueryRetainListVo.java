package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "搜尋「限額資料」用的條件")
public class QueryRetainListVo {

	@ApiModelProperty (value = "合約年度")
	private String treatyYear;  
	
	@ApiModelProperty (value = "限額代號")
	private String limitId;

	public String getTreatyYear() {
		return treatyYear;
	}

	public void setTreatyYear(String treatyYear) {
		this.treatyYear = treatyYear;
	}

	public String getLimitId() {
		return limitId;
	}

	public void setLimitId(String limitId) {
		this.limitId = limitId;
	}


	
	
	
	
}

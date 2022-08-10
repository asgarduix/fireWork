package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "搜尋「梯次佣金」用的合約資料")
public class QueryTreatyCommListVo {

	@ApiModelProperty (value = "合約年度")
	private String treatyYear;  
	
	@ApiModelProperty (value = "合約編號")
	private String treatyNo;

	public String getTreatyYear() {
		return treatyYear;
	}

	public void setTreatyYear(String treatyYear) {
		this.treatyYear = treatyYear;
	}

	public String getTreatyNo() {
		return treatyNo;
	}

	public void setTreatyNo(String treatyNo) {
		this.treatyNo = treatyNo;
	}  
	
	
	
	
	
}

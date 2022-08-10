package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "刪除再保合約資料條件")
public class Rin1102ADeleteOneTreatyVOReq {
	
	@ApiModelProperty (value = "合約年度")
	private String treatyYear;  
	
	@ApiModelProperty (value = "合約代號")
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

package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "搜尋「合約分保順序維護」的條件")
public class Rin1108QueryTreatyShareOrderListVOReq {

	@ApiModelProperty (value = "合約年度")
	private String treatyYear;		
	@ApiModelProperty (value = "保單種類")
	private String policyType;
	public String getTreatyYear() {
		return treatyYear;
	}
	public void setTreatyYear(String treatyYear) {
		this.treatyYear = treatyYear;
	}
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}		

	
	
	
}

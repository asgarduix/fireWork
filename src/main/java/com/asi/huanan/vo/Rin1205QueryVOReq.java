package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1205「分保調整同險」搜尋條件")
public class Rin1205QueryVOReq {
	
	@ApiModelProperty (value = "同險編號")
	private String riskNo;  
	
	@ApiModelProperty (value = "合約年度")
	private String treatyYear;

	@ApiModelProperty (value = "有效日期")
	private String policyDate;

	
	public String getRiskNo() {
		return riskNo;
	}

	public void setRiskNo(String riskNo) {
		this.riskNo = riskNo;
	}

	public String getTreatyYear() {
		return treatyYear;
	}

	public void setTreatyYear(String treatyYear) {
		this.treatyYear = treatyYear;
	}

	public String getPolicyDate() {
		return policyDate;
	}

	public void setPolicyDate(String policyDate) {
		this.policyDate = policyDate;
	}
	
	

	
}

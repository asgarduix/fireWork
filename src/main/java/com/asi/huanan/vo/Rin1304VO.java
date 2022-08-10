package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1304查詢條件")
public class Rin1304VO {
	
	@ApiModelProperty (value = "篩選條件")
	private String filterPolicy;
	
	@ApiModelProperty (value = "保單號碼")
	private String policyNo;
	
	@ApiModelProperty (value = "批單號碼")
	private String endorseNo;

	public String getFilterPolicy() {
		return filterPolicy;
	}

	public void setFilterPolicy(String filterPolicy) {
		this.filterPolicy = filterPolicy;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getEndorseNo() {
		return endorseNo;
	}

	public void setEndorseNo(String endorseNo) {
		this.endorseNo = endorseNo;
	}
	
	
	
	
   
}

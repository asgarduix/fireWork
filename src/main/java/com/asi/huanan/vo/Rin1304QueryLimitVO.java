package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1304保批單明細檔查詢保單限額、限額代號、限額")
public class Rin1304QueryLimitVO {
	@ApiModelProperty (value = "保單列印日取'年'")
	private String policyYear;
	
	@ApiModelProperty (value = "使用性質代號")
	private String propCode;
	
	@ApiModelProperty (value = "建築等級英文代號")
	private String constClass;
	
	@ApiModelProperty (value = "限額代號")
	private String limitId;
	
	@ApiModelProperty (value = "限額")
	private String limitAmount;

	public String getPolicyYear() {
		return policyYear;
	}

	public void setPolicyYear(String policyYear) {
		this.policyYear = policyYear;
	}

	public String getPropCode() {
		return propCode;
	}

	public void setPropCode(String propCode) {
		this.propCode = propCode;
	}

	public String getConstClass() {
		return constClass;
	}

	public void setConstClass(String constClass) {
		this.constClass = constClass;
	}

	public String getLimitId() {
		return limitId;
	}

	public void setLimitId(String limitId) {
		this.limitId = limitId;
	}

	public String getLimitAmount() {
		return limitAmount;
	}

	public void setLimitAmount(String limitAmount) {
		this.limitAmount = limitAmount;
	}
	
	
	
}

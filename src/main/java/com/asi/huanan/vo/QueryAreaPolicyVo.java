package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "查詢同險設定資料")
public class QueryAreaPolicyVo {

	@ApiModelProperty (notes = "印單日期起")
	private String policy_dprtS; 
	
	@ApiModelProperty (notes = "印單日期迄")
	private String policy_dprtE;  
	
	@ApiModelProperty (notes = "地段代號")
	private String txtarea_code;
	
	@ApiModelProperty (notes = "範圍")
	private String risk_flag;

	public String getPolicy_dprtS() {
		return policy_dprtS;
	}

	public void setPolicy_dprtS(String policy_dprtS) {
		this.policy_dprtS = policy_dprtS;
	}

	public String getPolicy_dprtE() {
		return policy_dprtE;
	}

	public void setPolicy_dprtE(String policy_dprtE) {
		this.policy_dprtE = policy_dprtE;
	}

	public String getTxtarea_code() {
		return txtarea_code;
	}

	public void setTxtarea_code(String txtarea_code) {
		this.txtarea_code = txtarea_code;
	}

	public String getRisk_flag() {
		return risk_flag;
	}

	public void setRisk_flag(String risk_flag) {
		this.risk_flag = risk_flag;
	}

	
	
	
	
}

package com.asi.huanan.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "確認同險(多筆)")
public class UpdateFriPolicyDel {
	
	@ApiModelProperty (value = "同險代號資料")
	private List<Rin1203AVOResp> riskDdlList;
	
	@ApiModelProperty (value = "同險代號")
	private String riskNo;
	
	@ApiModelProperty (value = "同險名稱")
	private String riskName;

	

	public List<Rin1203AVOResp> getRiskDdlList() {
		return riskDdlList;
	}

	public void setRiskDdlList(List<Rin1203AVOResp> riskDdlList) {
		this.riskDdlList = riskDdlList;
	}

	public String getRiskNo() {
		return riskNo;
	}

	public void setRiskN0(String riskNo) {
		this.riskNo = riskNo;
	}

	public String getRiskName() {
		return riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}



	

}

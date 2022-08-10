package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1102A再保人註銷與適格性檢核")
public class Rin1102AChkRinIdUsableVOReq {

	@ApiModelProperty (value = "合約期間開始日")
	private String treatyBgn;		
	@ApiModelProperty (value = "再保人/經紀人代號")
	private String rinComId;
	@ApiModelProperty (value = "對象(再保人/經紀人)")
	private String type;
	
	public String getTreatyBgn() {
		return treatyBgn;
	}
	public void setTreatyBgn(String treatyBgn) {
		this.treatyBgn = treatyBgn;
	}
	public String getRinComId() {
		return rinComId;
	}
	public void setRinComId(String rinComId) {
		this.rinComId = rinComId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}		
	
	

	
	
	
}

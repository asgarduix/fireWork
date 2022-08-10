package com.asi.huanan.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1102A再保人經紀人最終支付公司檢核")
public class Rin1102AChkEnodeVOReq {
	
	@ApiModelProperty (value = "再保人代號")
	private List<String> rinComIdList;
	
	@ApiModelProperty (value = "經紀人代號")
	private List<String> brokerIdList;

	public List<String> getRinComIdList() {
		return rinComIdList;
	}

	public void setRinComIdList(List<String> rinComIdList) {
		this.rinComIdList = rinComIdList;
	}

	public List<String> getBrokerIdList() {
		return brokerIdList;
	}

	public void setBrokerIdList(List<String> brokerIdList) {
		this.brokerIdList = brokerIdList;
	}


	
}

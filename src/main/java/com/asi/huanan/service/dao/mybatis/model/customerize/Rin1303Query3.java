package com.asi.huanan.service.dao.mybatis.model.customerize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1303報表(經紀人資料)查詢結果")
public class Rin1303Query3 {


	@ApiModelProperty (value = "經紀人英文名稱")
	private String rinComSname;
	@ApiModelProperty (value = "經紀人分出比率")
	private String brokerShareRate;
	
	
	public String getRinComSname() {
		return rinComSname;
	}
	public void setRinComSname(String rinComSname) {
		this.rinComSname = rinComSname;
	}
	public String getBrokerShareRate() {
		return brokerShareRate;
	}
	public void setBrokerShareRate(String brokerShareRate) {
		this.brokerShareRate = brokerShareRate;
	}


}

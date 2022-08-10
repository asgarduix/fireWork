package com.asi.huanan.vo;

import io.swagger.annotations.ApiModelProperty;

public class Rin1304GetUsePropVO {
	@ApiModelProperty (value = "使用性質代號")
	private String usePropId;
	
	@ApiModelProperty (value = "使用性質名稱")
	private String usePropName;

	public String getUsePropId() {
		return usePropId;
	}

	public void setUsePropId(String usePropId) {
		this.usePropId = usePropId;
	}

	public String getUsePropName() {
		return usePropName;
	}

	public void setUsePropName(String usePropName) {
		this.usePropName = usePropName;
	}
	
	
}

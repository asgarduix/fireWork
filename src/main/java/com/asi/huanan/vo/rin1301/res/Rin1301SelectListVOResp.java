package com.asi.huanan.vo.rin1301.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1301A頁面下拉選單")
public class Rin1301SelectListVOResp {
	
	@ApiModelProperty(value = "選項名稱")
	String optionKey;
	
	@ApiModelProperty(value = "選項值")
	String optionValue;

	public String getOptionKey() {
		return optionKey;
	}

	public void setOptionKey(String optionKey) {
		this.optionKey = optionKey;
	}

	public String getOptionValue() {
		return optionValue;
	}

	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}
	
	

}

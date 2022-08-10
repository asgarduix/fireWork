package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "清除地段代號用的條件")
public class DeleteUsedArea {

	@ApiModelProperty(value = "地段代號")
	private String txtarea_code;

	public String getTxtarea_code() {
		return txtarea_code;
	}

	public void setTxtarea_code(String txtarea_code) {
		this.txtarea_code = txtarea_code;
	}
	
}

package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1203表格欄位")
public class Rin1203Vo {

	@ApiModelProperty (value = "地段代號")
	private String txtarea_code;

	public String getTxtarea_code() {
		return txtarea_code;
	}

	public void setTxtarea_code(String txtarea_code) {
		this.txtarea_code = txtarea_code;
	}

	
	
}

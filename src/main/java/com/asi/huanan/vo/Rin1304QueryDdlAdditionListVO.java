package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1304附加險明細頁下拉選單")
public class Rin1304QueryDdlAdditionListVO {
	@ApiModelProperty (value = "附加險代號")
	private String txtaddition_no;	
	
	@ApiModelProperty (value = "附加險名稱")
	private String txtaddition_name;

	public String getTxtaddition_no() {
		return txtaddition_no;
	}

	public void setTxtaddition_no(String txtaddition_no) {
		this.txtaddition_no = txtaddition_no;
	}

	public String getTxtaddition_name() {
		return txtaddition_name;
	}

	public void setTxtaddition_name(String txtaddition_name) {
		this.txtaddition_name = txtaddition_name;
	}	
	
	
	
}

package com.asi.huanan.vo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "刪除「慕尼黑地區設定」資料的條件")
public class DeleteMunichVo {

	@ApiModelProperty (value = "天災區域代號")
	private String txtnatural_id;			

	
	public String getTxtnatural_id() {
		return txtnatural_id;
	}
	public void setTxtnatural_id(String txtnatural_id) {
		this.txtnatural_id = txtnatural_id;
	}

	
}

package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "刪除「限額資料」用的條件")
public class DeleteRetainVo {

	@ApiModelProperty (value = "合約年度")
	private String txttreaty_year;  
	
	@ApiModelProperty (value = "限額代號")
	private String txtlimit_id;

	public String getTxttreaty_year() {
		return txttreaty_year;
	}

	public void setTxttreaty_year(String txttreaty_year) {
		this.txttreaty_year = txttreaty_year;
	}

	public String getTxtlimit_id() {
		return txtlimit_id;
	}

	public void setTxtlimit_id(String txtlimit_id) {
		this.txtlimit_id = txtlimit_id;
	}

	


	
	
	
	
}

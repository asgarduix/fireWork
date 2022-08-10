package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1203A下拉選單")
public class QueryRiskListVo {
	
	@ApiModelProperty (value = "同險編號")
	private String txtrisk_no;		
	
	@ApiModelProperty (value = "同險名稱")
	private String txtrisk_name;	
	
	@ApiModelProperty (value = "地段代碼")
	private String txtarea_code;		
	

	public String getTxtrisk_no() {
		return txtrisk_no;
	}

	public void setTxtrisk_no(String txtrisk_no) {
		this.txtrisk_no = txtrisk_no;
	}

	public String getTxtrisk_name() {
		return txtrisk_name;
	}

	public void setTxtrisk_name(String txtrisk_name) {
		this.txtrisk_name = txtrisk_name;
	}

	public String getTxtarea_code() {
		return txtarea_code;
	}

	public void setTxtarea_code(String txtarea_code) {
		this.txtarea_code = txtarea_code;
	}

	
	
}

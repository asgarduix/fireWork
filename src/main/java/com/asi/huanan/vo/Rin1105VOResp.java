package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1105表格欄位")
public class Rin1105VOResp {

	@ApiModelProperty (value = "合約年度")
	private String txttreaty_year;			
	@ApiModelProperty (value = "限額代號")
	private String txtlimit_id;		
	@ApiModelProperty (value = "限額")
	private String numlimit_amount;
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
	public String getNumlimit_amount() {
		return numlimit_amount;
	}
	public void setNumlimit_amount(String numlimit_amount) {
		this.numlimit_amount = numlimit_amount;
	}			



	

	
	
}

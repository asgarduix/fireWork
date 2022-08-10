package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1102表格欄位")
public class Rin1102VOResp {

	@ApiModelProperty (value = "合約年度")
	private String txttreaty_year;			
	@ApiModelProperty (value = "合約代號")
	private String txttreaty_no;		
	@ApiModelProperty (value = "合約名稱")
	private String txttreaty_name;		

	public String getTxttreaty_year() {
		return txttreaty_year;
	}

	public void setTxttreaty_year(String txttreaty_year) {
		this.txttreaty_year = txttreaty_year;
	}

	public String getTxttreaty_no() {
		return txttreaty_no;
	}

	public void setTxttreaty_no(String txttreaty_no) {
		this.txttreaty_no = txttreaty_no;
	}

	public String getTxttreaty_name() {
		return txttreaty_name;
	}

	public void setTxttreaty_name(String txttreaty_name) {
		this.txttreaty_name = txttreaty_name;
	}

	
	
	
}

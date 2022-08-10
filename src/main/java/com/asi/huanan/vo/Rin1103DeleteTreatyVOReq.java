package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "刪除「梯次佣金資料」用的條件")
public class Rin1103DeleteTreatyVOReq {
	@ApiModelProperty(value = "合約年度")
	private String txttreaty_year;	
	@ApiModelProperty(value = "合約編號")
	private String txttreaty_no;	
	@ApiModelProperty(value = "梯次佣金類別")
	private String txtcomm_type;	
	@ApiModelProperty(value = "序號")
	private Short  serial;		 
	
	
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
	public String getTxtcomm_type() {
		return txtcomm_type;
	}
	public void setTxtcomm_type(String txtcomm_type) {
		this.txtcomm_type = txtcomm_type;
	}
	public Short getSerial() {
		return serial;
	}
	public void setSerial(Short serial) {
		this.serial = serial;
	}



	
}

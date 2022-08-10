package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1102Apop再保人代號表格")
public class Rin1102ApopVOResp {

	@ApiModelProperty (value = "再保人代號")
	private String lblrin_com_id;		
	@ApiModelProperty (value = "再保人英文名稱")
	private String lblename;		
	@ApiModelProperty (value = "再保人中文名稱")
	private String lblcname;
	@ApiModelProperty (value = "再保人中文簡稱")
	private String lblsname;		
	@ApiModelProperty (value = "備註")
	private String lblremark;
	@ApiModelProperty (value = "註銷日")
	private String dtaUSEMRK;
	public String getLblrin_com_id() {
		return lblrin_com_id;
	}
	public void setLblrin_com_id(String lblrin_com_id) {
		this.lblrin_com_id = lblrin_com_id;
	}
	public String getLblename() {
		return lblename;
	}
	public void setLblename(String lblename) {
		this.lblename = lblename;
	}
	public String getLblcname() {
		return lblcname;
	}
	public void setLblcname(String lblcname) {
		this.lblcname = lblcname;
	}
	public String getLblsname() {
		return lblsname;
	}
	public void setLblsname(String lblsname) {
		this.lblsname = lblsname;
	}
	public String getLblremark() {
		return lblremark;
	}
	public void setLblremark(String lblremark) {
		this.lblremark = lblremark;
	}
	public String getDtaUSEMRK() {
		return dtaUSEMRK;
	}
	public void setDtaUSEMRK(String dtaUSEMRK) {
		this.dtaUSEMRK = dtaUSEMRK;
	}
	
	
}

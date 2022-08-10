package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1303「臨分再保人」資料")
public class Rin1303QueryRinComBySlipNoVOResp {
	
	@ApiModelProperty (value = "再保人代號")
	private String txtrin_com_id;
	@ApiModelProperty (value = "已列印否")
	private String txtacct_flag;
	@ApiModelProperty (value = "轉檔狀況")
	private String txtTurn_flag;
	@ApiModelProperty (value = "帳單號碼")
	private String txtbill_no;
	@ApiModelProperty (value = "外部帳單號碼")
	private String txtbill_no_external;
	@ApiModelProperty (value = "再保人名稱")
	private String txtename;
	@ApiModelProperty (value = "更正號")
	private String slip_no;
	
	
	public String getTxtrin_com_id() {
		return txtrin_com_id;
	}
	public void setTxtrin_com_id(String txtrin_com_id) {
		this.txtrin_com_id = txtrin_com_id;
	}
	public String getTxtacct_flag() {
		return txtacct_flag;
	}
	public void setTxtacct_flag(String txtacct_flag) {
		this.txtacct_flag = txtacct_flag;
	}
	public String getTxtTurn_flag() {
		return txtTurn_flag;
	}
	public void setTxtTurn_flag(String txtTurn_flag) {
		this.txtTurn_flag = txtTurn_flag;
	}
	public String getTxtbill_no() {
		return txtbill_no;
	}
	public void setTxtbill_no(String txtbill_no) {
		this.txtbill_no = txtbill_no;
	}
	public String getTxtbill_no_external() {
		return txtbill_no_external;
	}
	public void setTxtbill_no_external(String txtbill_no_external) {
		this.txtbill_no_external = txtbill_no_external;
	}
	public String getTxtename() {
		return txtename;
	}
	public void setTxtename(String txtename) {
		this.txtename = txtename;
	}
	public String getSlip_no() {
		return slip_no;
	}
	public void setSlip_no(String slip_no) {
		this.slip_no = slip_no;
	}
	

	

}

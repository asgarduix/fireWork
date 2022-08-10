package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1102A經濟人分攤比率列表")
public class Rin1102ABrokerVOResp {

	@ApiModelProperty (value = "經紀人代號")
	private String txtBroker_id;		
	@ApiModelProperty (value = "經紀人名稱")
	private String txtBroker_sname;		
	@ApiModelProperty (value = "再保人代號")
	private String txtrin_com_id2;
	@ApiModelProperty (value = "再保人名稱")
	private String txtrin_com_sname2;		
	@ApiModelProperty (value = "再保人分攤比率")
	private String numrin_com_share2;
	public String getTxtBroker_id() {
		return txtBroker_id;
	}
	public void setTxtBroker_id(String txtBroker_id) {
		this.txtBroker_id = txtBroker_id;
	}
	public String getTxtBroker_sname() {
		return txtBroker_sname;
	}
	public void setTxtBroker_sname(String txtBroker_sname) {
		this.txtBroker_sname = txtBroker_sname;
	}
	public String getTxtrin_com_id2() {
		return txtrin_com_id2;
	}
	public void setTxtrin_com_id2(String txtrin_com_id2) {
		this.txtrin_com_id2 = txtrin_com_id2;
	}
	public String getTxtrin_com_sname2() {
		return txtrin_com_sname2;
	}
	public void setTxtrin_com_sname2(String txtrin_com_sname2) {
		this.txtrin_com_sname2 = txtrin_com_sname2;
	}
	public String getNumrin_com_share2() {
		return numrin_com_share2;
	}
	public void setNumrin_com_share2(String numrin_com_share2) {
		this.numrin_com_share2 = numrin_com_share2;
	}
	
	
}

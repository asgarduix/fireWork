package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1102A再保人分攤比例列表")
public class Rin1102ARincomVOResp {

	@ApiModelProperty (value = "再保人代號")
	private String txtrin_com_id1;		
	@ApiModelProperty (value = "序號")
	private String numrin_com_seq;		
	@ApiModelProperty (value = "再保人名稱")
	private String txtrin_com_sname1;
	@ApiModelProperty (value = "分攤比例")
	private String numrin_com_share1;		
	@ApiModelProperty (value = "代扣營業稅")
	private String numrin_com_tax;
	public String getTxtrin_com_id1() {
		return txtrin_com_id1;
	}
	public void setTxtrin_com_id1(String txtrin_com_id1) {
		this.txtrin_com_id1 = txtrin_com_id1;
	}
	public String getNumrin_com_seq() {
		return numrin_com_seq;
	}
	public void setNumrin_com_seq(String numrin_com_seq) {
		this.numrin_com_seq = numrin_com_seq;
	}
	public String getTxtrin_com_sname1() {
		return txtrin_com_sname1;
	}
	public void setTxtrin_com_sname1(String txtrin_com_sname1) {
		this.txtrin_com_sname1 = txtrin_com_sname1;
	}
	public String getNumrin_com_share1() {
		return numrin_com_share1;
	}
	public void setNumrin_com_share1(String numrin_com_share1) {
		this.numrin_com_share1 = numrin_com_share1;
	}
	public String getNumrin_com_tax() {
		return numrin_com_tax;
	}
	public void setNumrin_com_tax(String numrin_com_tax) {
		this.numrin_com_tax = numrin_com_tax;
	}		

}

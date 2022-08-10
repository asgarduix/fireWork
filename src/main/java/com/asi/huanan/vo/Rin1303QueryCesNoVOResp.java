package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1303「合約號資料」資料")
public class Rin1303QueryCesNoVOResp {
	
	@ApiModelProperty (value = "合約號")
	private String txtcession_no;
	@ApiModelProperty (value = "更正號")
	private String txtslip_no;
	@ApiModelProperty (value = "保單號")
	private String txtpolicy_no;
	@ApiModelProperty (value = "合約名稱")
	private String txtcession_name;
	
	
	public String getTxtcession_no() {
		return txtcession_no;
	}
	public void setTxtcession_no(String txtcession_no) {
		this.txtcession_no = txtcession_no;
	}
	public String getTxtslip_no() {
		return txtslip_no;
	}
	public void setTxtslip_no(String txtslip_no) {
		this.txtslip_no = txtslip_no;
	}
	public String getTxtpolicy_no() {
		return txtpolicy_no;
	}
	public void setTxtpolicy_no(String txtpolicy_no) {
		this.txtpolicy_no = txtpolicy_no;
	}
	public String getTxtcession_name() {
		return txtcession_name;
	}
	public void setTxtcession_name(String txtcession_name) {
		this.txtcession_name = txtcession_name;
	}


	

	
	

	
}

package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "修改慕尼黑地區設定資料與條件")
public class Rin1106UpdateMunichVOReq {

	@ApiModelProperty (value = "天災區域代號")
	private String txtnatural_id;			
	@ApiModelProperty (value = "縣市名稱")
	private String txtcity_name;		
	@ApiModelProperty (value = "慕尼黑地區代號")
	private String txtmunich_id;
	@ApiModelProperty (value = "慕尼黑地區說明")
	private String txtmunich_desc;
	
	public String getTxtnatural_id() {
		return txtnatural_id;
	}
	public void setTxtnatural_id(String txtnatural_id) {
		this.txtnatural_id = txtnatural_id;
	}
	public String getTxtcity_name() {
		return txtcity_name;
	}
	public void setTxtcity_name(String txtcity_name) {
		this.txtcity_name = txtcity_name;
	}
	public String getTxtmunich_id() {
		return txtmunich_id;
	}
	public void setTxtmunich_id(String txtmunich_id) {
		this.txtmunich_id = txtmunich_id;
	}
	public String getTxtmunich_desc() {
		return txtmunich_desc;
	}
	public void setTxtmunich_desc(String txtmunich_desc) {
		this.txtmunich_desc = txtmunich_desc;
	}			

	
}

package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1304附加險明細頁下拉選單")
public class Rin1304QueryDdlPropertyListVO {
	@ApiModelProperty (value = "標的物代號")
	private String propertyNo;	
	
	@ApiModelProperty (value = "標的物名稱")
	private String propertyName;
	
	@ApiModelProperty (value = "標地物保品名稱簡碼")
	private String ename;

	public String getPropertyNo() {
		return propertyNo;
	}

	public void setPropertyNo(String propertyNo) {
		this.propertyNo = propertyNo;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}
	
	
	
}

package com.asi.huanan.service.dao.mybatis.model.customerize;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1303查詢經紀人結果")
public class Rin1303Query4 {

	@ApiModelProperty (value = "再保人代號")
	private String rin_com_id;
	@ApiModelProperty (value = "分出比率")
	private BigDecimal rin_com_share;
	@ApiModelProperty (value = "英文名稱")
	private String rin_com_name;
	@ApiModelProperty (value = "broker代號")
	private String brokerId;

	
	
	public String getRin_com_id() {
		return rin_com_id;
	}
	public void setRin_com_id(String rin_com_id) {
		this.rin_com_id = rin_com_id;
	}
	public BigDecimal getRin_com_share() {
		return rin_com_share;
	}
	public void setRin_com_share(BigDecimal rin_com_share) {
		this.rin_com_share = rin_com_share;
	}
	public String getRin_com_name() {
		return rin_com_name;
	}
	public void setRin_com_name(String rin_com_name) {
		this.rin_com_name = rin_com_name;
	}
	public String getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}
	


}

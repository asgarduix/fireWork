package com.asi.huanan.service.dao.mybatis.model.customerize;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1205分保同險資料(臨分分保額)")
public class Rin1205TableFacShare {

	@ApiModelProperty (value = "臨分保額")
	private BigDecimal fac_amt;			
	@ApiModelProperty (value = "地震臨分")
	private BigDecimal fac_ear_share;			
	@ApiModelProperty (value = "颱風臨分")
	private BigDecimal fac_typ_share;
	
	
	public BigDecimal getFac_amt() {
		return fac_amt;
	}
	public void setFac_amt(BigDecimal fac_amt) {
		this.fac_amt = fac_amt;
	}
	public BigDecimal getFac_ear_share() {
		return fac_ear_share;
	}
	public void setFac_ear_share(BigDecimal fac_ear_share) {
		this.fac_ear_share = fac_ear_share;
	}
	public BigDecimal getFac_typ_share() {
		return fac_typ_share;
	}
	public void setFac_typ_share(BigDecimal fac_typ_share) {
		this.fac_typ_share = fac_typ_share;
	}			


	
	

	
	

	
}

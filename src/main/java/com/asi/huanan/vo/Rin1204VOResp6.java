package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1204執行自動分保，讀取臨分分保資料")
public class Rin1204VOResp6 {

	@ApiModelProperty(value = "合約異動分保額")
	private Long facAmt;

	@ApiModelProperty(value = "異動分保保費")
	private Long facPrem;

	@ApiModelProperty(value = "此合約颱洪分保額")
	private Long facAmtTyp;

	@ApiModelProperty(value = "此合約颱洪分保費")
	private Long facPremTyp;

	@ApiModelProperty(value = "此合約地震分保額")
	private Long facAmtEar;

	@ApiModelProperty(value = "此合約地震分保費")
	private Long facPremEar;

	public Long getFacAmt() {
		return facAmt;
	}

	public void setFacAmt(Long facAmt) {
		this.facAmt = facAmt;
	}

	public Long getFacPrem() {
		return facPrem;
	}

	public void setFacPrem(Long facPrem) {
		this.facPrem = facPrem;
	}

	public Long getFacAmtTyp() {
		return facAmtTyp;
	}

	public void setFacAmtTyp(Long facAmtTyp) {
		this.facAmtTyp = facAmtTyp;
	}

	public Long getFacPremTyp() {
		return facPremTyp;
	}

	public void setFacPremTyp(Long facPremTyp) {
		this.facPremTyp = facPremTyp;
	}

	public Long getFacAmtEar() {
		return facAmtEar;
	}

	public void setFacAmtEar(Long facAmtEar) {
		this.facAmtEar = facAmtEar;
	}

	public Long getFacPremEar() {
		return facPremEar;
	}

	public void setFacPremEar(Long facPremEar) {
		this.facPremEar = facPremEar;
	}

}
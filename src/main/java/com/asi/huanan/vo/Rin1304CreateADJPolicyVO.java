package com.asi.huanan.vo;

import io.swagger.annotations.ApiModelProperty;

public class Rin1304CreateADJPolicyVO {
	@ApiModelProperty(value = "保單號碼")
	private String policyNo;

	@ApiModelProperty(value = "批單號碼")
	private String endorseNo;
	
	@ApiModelProperty(value = "新保單號碼")
	private String PolicyNoADJ;
	
	@ApiModelProperty(value = "新批單號碼")
	private String EndorseNoADJ;

	@ApiModelProperty(value = "保單日期")
	private String policyDprt;

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getEndorseNo() {
		return endorseNo;
	}

	public void setEndorseNo(String endorseNo) {
		this.endorseNo = endorseNo;
	}

	public String getPolicyNoADJ() {
		return PolicyNoADJ;
	}

	public void setPolicyNoADJ(String policyNoADJ) {
		PolicyNoADJ = policyNoADJ;
	}

	public String getEndorseNoADJ() {
		return EndorseNoADJ;
	}

	public void setEndorseNoADJ(String endorseNoADJ) {
		EndorseNoADJ = endorseNoADJ;
	}

	public String getPolicyDprt() {
		return policyDprt;
	}

	public void setPolicyDprt(String policyDprt) {
		this.policyDprt = policyDprt;
	}

	
}

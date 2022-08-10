package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1304刪除所有需要欄位")
public class Rin1304DeletePolicyAllVORes {

	@ApiModelProperty (value = "保單號碼")
	private String policyNo;
	
	@ApiModelProperty (value = "批單號碼")
	private String endorseNo;
	
	@ApiModelProperty (value = "地址序號")
	private String addrNo;

	@ApiModelProperty (value = "標的物序號")
	private String propNo;
	
	@ApiModelProperty (value = "附加險序號")
	private String additionSeq;

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

	public String getAddrNo() {
		return addrNo;
	}

	public void setAddrNo(String addrNo) {
		this.addrNo = addrNo;
	}

	public String getPropNo() {
		return propNo;
	}

	public void setPropNo(String propNo) {
		this.propNo = propNo;
	}

	public String getAdditionSeq() {
		return additionSeq;
	}

	public void setAdditionSeq(String additionSeq) {
		this.additionSeq = additionSeq;
	}

	
}

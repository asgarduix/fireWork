package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1304保批單附加險明細查詢資料")
public class Rin1304QueryPolicyAdditionVO {
	
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
	
	@ApiModelProperty (value = "附加險代號")
	private String additionNo;

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

	public String getAdditionNo() {
		return additionNo;
	}

	public void setAdditionNo(String additionNo) {
		this.additionNo = additionNo;
	}

	

}

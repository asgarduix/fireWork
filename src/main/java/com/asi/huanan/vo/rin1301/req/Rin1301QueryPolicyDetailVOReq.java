package com.asi.huanan.vo.rin1301.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "臨分資料維護內頁之保單資料查詢參數")
public class Rin1301QueryPolicyDetailVOReq {

	@ApiModelProperty(value = "保單號")
	private String policyNo;

	@ApiModelProperty(value = "批單號")
	private String endorseNo;
	
	@ApiModelProperty(value = "地址序號")
	private String addrNo;

	@ApiModelProperty(value = "知會/更正號")
	private String slipNo;
	
	@ApiModelProperty(value = "合約號")
	private String cessionNo;
	
	@ApiModelProperty(value = "修改註記")
	private String logSeq;
	
	@ApiModelProperty(value = "日數")
	private Short days;
	

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

	public String getSlipNo() {
		return slipNo;
	}

	public void setSlipNo(String slipNo) {
		this.slipNo = slipNo;
	}

	public String getCessionNo() {
		return cessionNo;
	}

	public void setCessionNo(String cessionNo) {
		this.cessionNo = cessionNo;
	}
	
	public String getLogSeq() {
		return logSeq;
	}

	public void setLogSeq(String logSeq) {
		this.logSeq = logSeq;
	}

	public Short getDays() {
		return days;
	}

	public void setDays(Short days) {
		this.days = days;
	}
	
	
	
}

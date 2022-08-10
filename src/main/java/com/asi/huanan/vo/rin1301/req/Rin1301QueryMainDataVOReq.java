package com.asi.huanan.vo.rin1301.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "臨分資料維護主頁查詢參數")
public class Rin1301QueryMainDataVOReq {
	
	@ApiModelProperty(value = "保單號")
	private String policyNo;

	@ApiModelProperty(value = "合約編號")
	private String cessionNo;
	
	@ApiModelProperty(value = "知會/更正號")
	private String slipNo;
	
	@ApiModelProperty(value = "未列印及暫緩或不轉之臨分起日")
	private String policyDprtBgn;
	
	@ApiModelProperty(value = "未列印及暫緩或不轉之臨分訖日")
	private String policyDprtEnd;

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getCessionNo() {
		return cessionNo;
	}

	public void setCessionNo(String cessionNo) {
		this.cessionNo = cessionNo;
	}

	public String getSlipNo() {
		return slipNo;
	}

	public void setSlipNo(String slipNo) {
		this.slipNo = slipNo;
	}

	public String getPolicyDprtBgn() {
		return policyDprtBgn;
	}

	public void setPolicyDprtBgn(String policyDprtBgn) {
		this.policyDprtBgn = policyDprtBgn;
	}

	public String getPolicyDprtEnd() {
		return policyDprtEnd;
	}

	public void setPolicyDprtEnd(String policyDprtEnd) {
		this.policyDprtEnd = policyDprtEnd;
	}

	
	
}

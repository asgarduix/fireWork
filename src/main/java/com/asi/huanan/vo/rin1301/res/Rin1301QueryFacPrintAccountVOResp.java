package com.asi.huanan.vo.rin1301.res;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "臨分資料維護主頁查詢")
public class Rin1301QueryFacPrintAccountVOResp {

	@ApiModelProperty(value = "保單號")
	private String policyNo;

	@ApiModelProperty(value = "批單號")
	private String endorseNo;

	@ApiModelProperty(value = "合約編號")
	private String cessionNo;

	@ApiModelProperty(value = "合約名稱")
	private String cessionName;

	@ApiModelProperty(value = "知會/更正號")
	private String slipNo;

	@ApiModelProperty(value = "名稱待確認")
	private String tmp;

	@ApiModelProperty(value = "帳單列印否")
	private String acctFlag;

	@ApiModelProperty(value = "前知會/更正號")
	private String preSlipNo;
	
	@ApiModelProperty(value = "前合約號")
	private String preCessionNo;
	
	@JsonFormat(pattern="yyyy/MM/dd",timezone = "GMT+8")
	@ApiModelProperty(value = "合約迄止日")
	private Date treatyDend;
	
	@ApiModelProperty(value = "臨分類型")
	private String facType;

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

	public String getCessionNo() {
		return cessionNo;
	}

	public void setCessionNo(String cessionNo) {
		this.cessionNo = cessionNo;
	}

	public String getCessionName() {
		return cessionName;
	}

	public void setCessionName(String cessionName) {
		this.cessionName = cessionName;
	}

	public String getSlipNo() {
		return slipNo;
	}

	public void setSlipNo(String slipNo) {
		this.slipNo = slipNo;
	}

	public String getTmp() {
		return tmp;
	}

	public void setTmp(String tmp) {
		this.tmp = tmp;
	}

	public String getAcctFlag() {
		return acctFlag;
	}

	public void setAcctFlag(String acctFlag) {
		this.acctFlag = acctFlag;
	}

	public String getPreSlipNo() {
		return preSlipNo;
	}

	public void setPreSlipNo(String preSlipNo) {
		this.preSlipNo = preSlipNo;
	}

	public String getPreCessionNo() {
		return preCessionNo;
	}

	public void setPreCessionNo(String preCessionNo) {
		this.preCessionNo = preCessionNo;
	}

	public Date getTreatyDend() {
		return treatyDend;
	}

	public void setTreatyDend(Date treatyDend) {
		this.treatyDend = treatyDend;
	}

	public String getFacType() {
		return facType;
	}

	public void setFacType(String facType) {
		this.facType = facType;
	}

}

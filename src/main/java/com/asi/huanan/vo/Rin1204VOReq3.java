package com.asi.huanan.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Rin1204執行自動分保，查詢該保單期間之同險分保總額")
public class Rin1204VOReq3 {

	private String treatyYear;

	private String riskNo;

	private String policyDBGN;

	private String policyDEND;

	private String policyPRT;

	private String treatyNo;

	private List<String> oldPolicyList;

	private String oldPolicy;

	private String nextPolicy;

	private String policyNo;

	private String endorseNo;

	private Short addrNo;

	public String getTreatyYear() {
		return treatyYear;
	}

	public void setTreatyYear(String treatyYear) {
		this.treatyYear = treatyYear;
	}

	public String getRiskNo() {
		return riskNo;
	}

	public void setRiskNo(String riskNo) {
		this.riskNo = riskNo;
	}

	public String getPolicyDBGN() {
		return policyDBGN;
	}

	public void setPolicyDBGN(String policyDBGN) {
		this.policyDBGN = policyDBGN;
	}

	public String getPolicyDEND() {
		return policyDEND;
	}

	public void setPolicyDEND(String policyDEND) {
		this.policyDEND = policyDEND;
	}

	public String getPolicyPRT() {
		return policyPRT;
	}

	public void setPolicyPRT(String policyPRT) {
		this.policyPRT = policyPRT;
	}

	public String getTreatyNo() {
		return treatyNo;
	}

	public void setTreatyNo(String treatyNo) {
		this.treatyNo = treatyNo;
	}

	public List<String> getOldPolicyList() {
		return oldPolicyList;
	}

	public void setOldPolicyList(List<String> oldPolicyList) {
		this.oldPolicyList = oldPolicyList;
	}

	public String getOldPolicy() {
		return oldPolicy;
	}

	public void setOldPolicy(String oldPolicy) {
		this.oldPolicy = oldPolicy;
	}

	public String getNextPolicy() {
		return nextPolicy;
	}

	public void setNextPolicy(String nextPolicy) {
		this.nextPolicy = nextPolicy;
	}

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

	public Short getAddrNo() {
		return addrNo;
	}

	public void setAddrNo(Short addrNo) {
		this.addrNo = addrNo;
	}

}

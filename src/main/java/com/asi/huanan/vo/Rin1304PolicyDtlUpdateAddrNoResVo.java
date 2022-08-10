package com.asi.huanan.vo;

import com.asi.huanan.service.dao.mybatis.model.FriPolicyDtl;

import io.swagger.annotations.ApiModelProperty;

public class Rin1304PolicyDtlUpdateAddrNoResVo {
	
	@ApiModelProperty (value = "舊地址序號")
	private String oldAddrNo;
	
	@ApiModelProperty (value = "保單明細")
	private FriPolicyDtl policyDtlList;

	public String getOldAddrNo() {
		return oldAddrNo;
	}

	public void setOldAddrNo(String oldAddrNo) {
		this.oldAddrNo = oldAddrNo;
	}

	public FriPolicyDtl getPolicyDtlList() {
		return policyDtlList;
	}

	public void setPolicyDtlList(FriPolicyDtl policyDtlList) {
		this.policyDtlList = policyDtlList;
	}
	
	

}

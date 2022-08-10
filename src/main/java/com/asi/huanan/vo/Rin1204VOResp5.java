package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1204執行自動分保，查詢該保單期間之同險分保總額")
public class Rin1204VOResp5 {

	@ApiModelProperty(value = "合約分保額")
	private Long shareAmt;

	public Long getShareAmt() {
		return shareAmt;
	}

	public void setShareAmt(Long shareAmt) {
		this.shareAmt = shareAmt;
	}

}
package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1206寫入帳單暫存檔前置查詢-總表 & 再保人")
public class Rin1206QueryConditionVO {
	//查詢條件
		@ApiModelProperty (value = "rptid")
		private Long rptid;
		
		@ApiModelProperty (value = "合約年份")
		private String treatyNo;

		@ApiModelProperty (value = "保單或批單")
		private String policyMode;

		public Long getRptid() {
			return rptid;
		}

		public void setRptid(Long rptid) {
			this.rptid = rptid;
		}

		public String getTreatyNo() {
			return treatyNo;
		}

		public void setTreatyNo(String treatyNo) {
			this.treatyNo = treatyNo;
		}

		public String getPolicyMode() {
			return policyMode;
		}

		public void setPolicyMode(String policyMode) {
			this.policyMode = policyMode;
		}
}

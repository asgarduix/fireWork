package com.asi.huanan.vo.rin1301.req;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "臨分資料維護明細查詢參數")
public class Rin1301AQueryParamVoReq {
	
	@ApiModelProperty(value = "同險代號")
	private List<String> riskNos ;
	
	@ApiModelProperty(value = "生效起日")
	private String treatyDBgn ;
	
	@ApiModelProperty(value = "生效迄日")
	private String treatyDEnd ;
	
	@ApiModelProperty(value = "境外分入註記")
	private String mkovse;
	
	

	public List<String> getRiskNos() {
		return riskNos;
	}

	public void setRiskNos(List<String> riskNos) {
		this.riskNos = riskNos;
	}

	public String getTreatyDBgn() {
		return treatyDBgn;
	}

	public void setTreatyDBgn(String treatyDBgn) {
		this.treatyDBgn = treatyDBgn;
	}

	public String getTreatyDEnd() {
		return treatyDEnd;
	}

	public void setTreatyDEnd(String treatyDEnd) {
		this.treatyDEnd = treatyDEnd;
	}

	public String getMkovse() {
		return mkovse;
	}

	public void setMkovse(String mkovse) {
		this.mkovse = mkovse;
	}

}

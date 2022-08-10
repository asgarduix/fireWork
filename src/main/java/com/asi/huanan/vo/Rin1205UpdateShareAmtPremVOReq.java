package com.asi.huanan.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1205修改合約保額與保費的條件與內容")
public class Rin1205UpdateShareAmtPremVOReq {

	@ApiModelProperty (value = "保單號")
	private String txtpolicy_no;			
	@ApiModelProperty (value = "批單號")
	private String txtendorse_no;			
	@ApiModelProperty (value = "序號")
	private String numaddr_no;			
	@ApiModelProperty (value = "同險代號")
	private String riskNo;
	@ApiModelProperty (value = "保額")
	private Long numshare_amt_0;
	@ApiModelProperty (value = "保費")
	private Long numshare_prem_0;
	@ApiModelProperty (value = "各合約分出保額/保費")
	private List<Rin1205VORespSub> rin1205SubVo;
	@ApiModelProperty (value = "合約年度")
	private String treaty_year;
	@ApiModelProperty (value = "合約代號")
	private String treaty_no;
	
	
	public String getTxtpolicy_no() {
		return txtpolicy_no;
	}
	public void setTxtpolicy_no(String txtpolicy_no) {
		this.txtpolicy_no = txtpolicy_no;
	}
	public String getTxtendorse_no() {
		return txtendorse_no;
	}
	public void setTxtendorse_no(String txtendorse_no) {
		this.txtendorse_no = txtendorse_no;
	}
	public String getNumaddr_no() {
		return numaddr_no;
	}
	public void setNumaddr_no(String numaddr_no) {
		this.numaddr_no = numaddr_no;
	}
	public String getRiskNo() {
		return riskNo;
	}
	public void setRiskNo(String riskNo) {
		this.riskNo = riskNo;
	}
	public Long getNumshare_amt_0() {
		return numshare_amt_0;
	}
	public void setNumshare_amt_0(Long numshare_amt_0) {
		this.numshare_amt_0 = numshare_amt_0;
	}
	public Long getNumshare_prem_0() {
		return numshare_prem_0;
	}
	public void setNumshare_prem_0(Long numshare_prem_0) {
		this.numshare_prem_0 = numshare_prem_0;
	}
	public List<Rin1205VORespSub> getRin1205SubVo() {
		return rin1205SubVo;
	}
	public void setRin1205SubVo(List<Rin1205VORespSub> rin1205SubVo) {
		this.rin1205SubVo = rin1205SubVo;
	}
	public String getTreaty_year() {
		return treaty_year;
	}
	public void setTreaty_year(String treaty_year) {
		this.treaty_year = treaty_year;
	}
	public String getTreaty_no() {
		return treaty_no;
	}
	public void setTreaty_no(String treaty_no) {
		this.treaty_no = treaty_no;
	}

	
	
	

	
}

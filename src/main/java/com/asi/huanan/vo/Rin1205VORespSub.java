package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1205各合約分出保額/保費")
public class Rin1205VORespSub {

	@ApiModelProperty (value = "i合約代號")
	private String txttreaty_no_i;	
	@ApiModelProperty (value = "i分保額")
	private String numshare_amt_i;		
	@ApiModelProperty (value = "i分保費")
	private String numshare_prem_i;
	
	
	public String getTxttreaty_no_i() {
		return txttreaty_no_i;
	}
	public void setTxttreaty_no_i(String txttreaty_no_i) {
		this.txttreaty_no_i = txttreaty_no_i;
	}
	public String getNumshare_amt_i() {
		return numshare_amt_i;
	}
	public void setNumshare_amt_i(String numshare_amt_i) {
		this.numshare_amt_i = numshare_amt_i;
	}
	public String getNumshare_prem_i() {
		return numshare_prem_i;
	}
	public void setNumshare_prem_i(String numshare_prem_i) {
		this.numshare_prem_i = numshare_prem_i;
	}			

	

	
	
}

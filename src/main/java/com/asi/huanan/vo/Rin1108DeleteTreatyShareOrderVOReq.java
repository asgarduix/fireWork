package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "刪除「合約分保順序維護」資料用的條件")
public class Rin1108DeleteTreatyShareOrderVOReq {

	@ApiModelProperty (value = "合約年度")
	private String txttreaty_year;		
	@ApiModelProperty (value = "保單種類")
	private String txtpolicy_type;		
	@ApiModelProperty (value = "分保順序")
	private short numshare_order;		

	
	
	public String getTxttreaty_year() {
		return txttreaty_year;
	}
	public void setTxttreaty_year(String txttreaty_year) {
		this.txttreaty_year = txttreaty_year;
	}
	public String getTxtpolicy_type() {
		return txtpolicy_type;
	}
	public void setTxtpolicy_type(String txtpolicy_type) {
		this.txtpolicy_type = txtpolicy_type;
	}
	public short getNumshare_order() {
		return numshare_order;
	}
	public void setNumshare_order(short numshare_order) {
		this.numshare_order = numshare_order;
	}

	
	
	
	

	
	
}

package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1303設定轉檔狀況內容與修改條件")
public class Rin1303UpdateStatusByNoIdVOReq {
	
	@ApiModelProperty (value = "再保人代號")
	private String rinComId;
	@ApiModelProperty (value = "外部帳單號碼")
	private String billNoExternal;
	@ApiModelProperty (value = "更正號")
	private String slipNo;
	@ApiModelProperty (value = "轉檔狀況")
	private String transferStatus;
	
	
	public String getRinComId() {
		return rinComId;
	}
	public void setRinComId(String rinComId) {
		this.rinComId = rinComId;
	}
	public String getBillNoExternal() {
		return billNoExternal;
	}
	public void setBillNoExternal(String billNoExternal) {
		this.billNoExternal = billNoExternal;
	}
	public String getSlipNo() {
		return slipNo;
	}
	public void setSlipNo(String slipNo) {
		this.slipNo = slipNo;
	}
	public String getTransferStatus() {
		return transferStatus;
	}
	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
	}
	
	

}

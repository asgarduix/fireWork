package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1304「臨分分入_轉檔頁面」資料")
public class Rin1304ConvertVORsep {
	
	@ApiModelProperty (value = "入帳日期")
	private String dtaAccEnterDate;
	
	@ApiModelProperty (value = "銷帳日期")
	private String dtaAccCloseDate;
	
	@ApiModelProperty (value = "再保人")
	private String txtBroker_id;
	
	@ApiModelProperty (value = "帳單日")
	private String dtaReceive_date;
	
	@ApiModelProperty (value = "帳單幣別")
	private String txtcurrency;
	
	@ApiModelProperty (value = "兌換率")
	private String txtCurrencyExchangeRate;
	
	@ApiModelProperty (value = "再保費(原幣)")
	private String numorg_prem;
	
	@ApiModelProperty (value = "境外分入註記(Y/N)")
	private String txtMkovse;
	
	@ApiModelProperty (value = "轉檔狀態")
	private String txtAccTransferState;
	
	@ApiModelProperty (value = "辦事處")
	private String txtOffice;
	
	@ApiModelProperty (value = "國名代號")
	private String txtCountryID;
	
	@ApiModelProperty (value = "佣金(原幣)")
	private String numorg_comm;

	public String getDtaAccEnterDate() {
		return dtaAccEnterDate;
	}

	public void setDtaAccEnterDate(String dtaAccEnterDate) {
		this.dtaAccEnterDate = dtaAccEnterDate;
	}

	public String getDtaAccCloseDate() {
		return dtaAccCloseDate;
	}

	public void setDtaAccCloseDate(String dtaAccCloseDate) {
		this.dtaAccCloseDate = dtaAccCloseDate;
	}

	public String getTxtBroker_id() {
		return txtBroker_id;
	}

	public void setTxtBroker_id(String txtBroker_id) {
		this.txtBroker_id = txtBroker_id;
	}

	public String getDtaReceive_date() {
		return dtaReceive_date;
	}

	public void setDtaReceive_date(String dtaReceive_date) {
		this.dtaReceive_date = dtaReceive_date;
	}

	public String getTxtcurrency() {
		return txtcurrency;
	}

	public void setTxtcurrency(String txtcurrency) {
		this.txtcurrency = txtcurrency;
	}

	public String getTxtCurrencyExchangeRate() {
		return txtCurrencyExchangeRate;
	}

	public void setTxtCurrencyExchangeRate(String txtCurrencyExchangeRate) {
		this.txtCurrencyExchangeRate = txtCurrencyExchangeRate;
	}

	public String getNumorg_prem() {
		return numorg_prem;
	}

	public void setNumorg_prem(String numorg_prem) {
		this.numorg_prem = numorg_prem;
	}

	public String getTxtMkovse() {
		return txtMkovse;
	}

	public void setTxtMkovse(String txtMkovse) {
		this.txtMkovse = txtMkovse;
	}

	public String getTxtAccTransferState() {
		return txtAccTransferState;
	}

	public void setTxtAccTransferState(String txtAccTransferState) {
		this.txtAccTransferState = txtAccTransferState;
	}

	public String getTxtOffice() {
		return txtOffice;
	}

	public void setTxtOffice(String txtOffice) {
		this.txtOffice = txtOffice;
	}

	public String getTxtCountryID() {
		return txtCountryID;
	}

	public void setTxtCountryID(String txtCountryID) {
		this.txtCountryID = txtCountryID;
	}

	public String getNumorg_comm() {
		return numorg_comm;
	}

	public void setNumorg_comm(String numorg_comm) {
		this.numorg_comm = numorg_comm;
	}
	
	
	
}

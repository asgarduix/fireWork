package com.asi.huanan.vo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1304保批單資料頁面所有欄位")
public class Rin1304FriPolicyVOResp {
	
	@ApiModelProperty (value = "保單號碼查詢條件")
	private String txtpolicy_no_query;
	
	@ApiModelProperty (value = "批單號碼查詢條件")
	private String txtendorse_no_query;
	
	@ApiModelProperty (value = "附加險代號")
	private String numAddition_no;
	
	@ApiModelProperty (value = "保單號碼")
	private String txtpolicy_no;
	
	@ApiModelProperty (value = "批單號碼")
	private String txtendorse_no;
	
	@ApiModelProperty (value = "地址")
	private String numAddr_No;
	
	@ApiModelProperty (value = "標的物地址")
	private String txtprop_addr;

	@ApiModelProperty (value = "標的物序號")
	private String numprop_no;
	
	@ApiModelProperty (value = "標的物名稱")
	private String txtProperty_Name;
	
	@ApiModelProperty (value = "附加險序號")
	private String numaddition_seq;
	
	@ApiModelProperty (value = "附加險名稱")
	private String txtaddition_name;
	
	@ApiModelProperty (value = "續保單號")
	private String txtold_policy;
	
	@ApiModelProperty (value = "批加減異動天然災害旗標")
	private String txtnature_flag;
	
	@ApiModelProperty (value = "被保險人")
	private String txtcinsurant;
	
	@ApiModelProperty (value = "被保險人英文名")
	private String txteinsurant;
	
	@ApiModelProperty (value = "流動註記")
	private String txtifloat;
	
	@ApiModelProperty (value = "保單始期")
	private String txtpolicy_dbgn;
	
	@ApiModelProperty (value = "保單終期")
	private String txtpolicy_dend;
	
	@ApiModelProperty (value = "保單列印日")
	private String txtpolicy_dprt;
	
	@ApiModelProperty (value = "保單類別")
	private String txtpolicy_type;
	
	@ApiModelProperty (value = "年期")
	private String txtpolicy_year;
	
	@ApiModelProperty (value = "險種")
	private String txtfire_type;
	
	@ApiModelProperty (value = "費率性質代號")
	private String txtirate_type;
	
	@ApiModelProperty (value = "共保註記")
	private String txtcoins_flag;
	
	@ApiModelProperty (value = "共保比率")
	private BigDecimal txtcoins_rate;
	
	@ApiModelProperty (value = "共保累計保額")
	private BigDecimal totalAmt;
	
	@ApiModelProperty (value = "共保累計保費")
	private BigDecimal totalPrem;
	
	@ApiModelProperty (value = "保額")
	private BigDecimal numamt;
	
	@ApiModelProperty (value = "保費")
	private BigDecimal numprem;
	
	@ApiModelProperty (value = "共保保額總保額")
	private BigDecimal numall_amt;
	
	@ApiModelProperty (value = "共保保額地震險")
	private BigDecimal numamt_1_eq;

	@ApiModelProperty (value = "共保保額颱風洪水險")
	private BigDecimal numamt_2_ty;
	
	@ApiModelProperty (value = "共保保額營業中斷險")
	private BigDecimal numamt_3_bi;
	
	@ApiModelProperty (value = "華南保額總保額")
	private BigDecimal num_amt_hn;
	
	@ApiModelProperty (value = "華南保額總保費")
	private BigDecimal num_prem_hn;
	
	@ApiModelProperty (value = "加總華南保額總保額")
	private BigDecimal numAllAmtHn;
	
	@ApiModelProperty (value = "華南保額地震險")
	private BigDecimal numamt_1_eq_hn;
	
	@ApiModelProperty (value = "華南保額颱風洪水險")
	private BigDecimal numamt_2_ty_hn;
	
	@ApiModelProperty (value = "華南保額營業中斷險")
	private BigDecimal numamt_3_bi_hn;
	
	@ApiModelProperty (value = "共保保費總保費")
	private BigDecimal numall_prem;
	
	@ApiModelProperty (value = "共保保費火險及其他附加險")
	private BigDecimal numprem_1_reins;
	
	@ApiModelProperty (value = "共保保費地震險")
	private BigDecimal numprem_2_eq;

	@ApiModelProperty (value = "共保保費颱風洪水險")
	private BigDecimal numprem_3_ty;
	
	@ApiModelProperty (value = "共保保費營業中斷險")
	private BigDecimal numprem_4_bi;
	
	@ApiModelProperty (value = "華南保費總保費")
	private BigDecimal numall_prem_hn;
	
	@ApiModelProperty (value = "加總華南保費總保費")
	private BigDecimal numAllPremHn;
	
	@ApiModelProperty (value = "華南保費火險及其他附加險")
	private BigDecimal numprem_1_reins_hn;
	
	@ApiModelProperty (value = "華南保費地震險")
	private BigDecimal numprem_2_eq_hn;

	@ApiModelProperty (value = "華南保費颱風洪水險")
	private BigDecimal numprem_3_ty_hn;
	
	@ApiModelProperty (value = "華南保費營業中斷險")
	private BigDecimal numprem_4_bi_hn;
	
	@ApiModelProperty (value = "異動保額")
	private BigDecimal numcom_amt;
	
	@ApiModelProperty (value = "異動保費")
	private BigDecimal numcom_prem;
	
	@ApiModelProperty (value = "批加減佣金")
	private BigDecimal numcomm;
	
	@ApiModelProperty (value = "佣金率")
	private BigDecimal numcomm_rate;

	@ApiModelProperty (value = "預收比率")
	private BigDecimal numprepay_rate;
	
	@ApiModelProperty (value = "批改事由")
	private String txtend_reason;
	
	@ApiModelProperty (value = "是否分保(Y/N)")
	private String txtcalc_flag;
	
	@ApiModelProperty (value = "關連單號")
	private String txtref_no;
	
	@ApiModelProperty (value = "分入保單號")
	private String txtRi_policyno;
	
	@ApiModelProperty (value = "調整註記(Y/N/X)")
	private String txtchange_flag;
	
	@ApiModelProperty (value = "臨分註記(Y/N)")
	private String txtFac_flag;
	
	@ApiModelProperty (value = "保單來源類別")
	private String txtpolicy_mode;
	
	@ApiModelProperty (value = "經紀人代號")
	private String txtRin_com_id;
	
	@ApiModelProperty (value = "經紀人名稱")
	private String txtcname;
	
	@ApiModelProperty (value = "經手人代號")
	private String txttxtOfficer1;
	
	@ApiModelProperty (value = "經手人名稱")
	private String txttxtOfficer2;
	
	//......其他資訊......//
	
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

	public String getTxtpolicy_no_query() {
		return txtpolicy_no_query;
	}

	public void setTxtpolicy_no_query(String txtpolicy_no_query) {
		this.txtpolicy_no_query = txtpolicy_no_query;
	}

	public String getTxtendorse_no_query() {
		return txtendorse_no_query;
	}

	public void setTxtendorse_no_query(String txtendorse_no_query) {
		this.txtendorse_no_query = txtendorse_no_query;
	}

	public String getNumAddition_no() {
		return numAddition_no;
	}

	public void setNumAddition_no(String numAddition_no) {
		this.numAddition_no = numAddition_no;
	}

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

	public String getNumAddr_No() {
		return numAddr_No;
	}

	public void setNumAddr_No(String numAddr_No) {
		this.numAddr_No = numAddr_No;
	}

	public String getTxtprop_addr() {
		return txtprop_addr;
	}

	public void setTxtprop_addr(String txtprop_addr) {
		this.txtprop_addr = txtprop_addr;
	}

	public String getNumprop_no() {
		return numprop_no;
	}

	public void setNumprop_no(String numprop_no) {
		this.numprop_no = numprop_no;
	}

	public String getTxtProperty_Name() {
		return txtProperty_Name;
	}

	public void setTxtProperty_Name(String txtProperty_Name) {
		this.txtProperty_Name = txtProperty_Name;
	}

	public String getNumaddition_seq() {
		return numaddition_seq;
	}

	public void setNumaddition_seq(String numaddition_seq) {
		this.numaddition_seq = numaddition_seq;
	}

	public String getTxtaddition_name() {
		return txtaddition_name;
	}

	public void setTxtaddition_name(String txtaddition_name) {
		this.txtaddition_name = txtaddition_name;
	}

	public String getTxtold_policy() {
		return txtold_policy;
	}

	public void setTxtold_policy(String txtold_policy) {
		this.txtold_policy = txtold_policy;
	}

	public String getTxtnature_flag() {
		return txtnature_flag;
	}

	public void setTxtnature_flag(String txtnature_flag) {
		this.txtnature_flag = txtnature_flag;
	}

	public String getTxtcinsurant() {
		return txtcinsurant;
	}

	public void setTxtcinsurant(String txtcinsurant) {
		this.txtcinsurant = txtcinsurant;
	}

	public String getTxteinsurant() {
		return txteinsurant;
	}

	public void setTxteinsurant(String txteinsurant) {
		this.txteinsurant = txteinsurant;
	}

	public String getTxtifloat() {
		return txtifloat;
	}

	public void setTxtifloat(String txtifloat) {
		this.txtifloat = txtifloat;
	}

	public String getTxtpolicy_dbgn() {
		return txtpolicy_dbgn;
	}

	public void setTxtpolicy_dbgn(String txtpolicy_dbgn) {
		this.txtpolicy_dbgn = txtpolicy_dbgn;
	}

	public String getTxtpolicy_dend() {
		return txtpolicy_dend;
	}

	public void setTxtpolicy_dend(String txtpolicy_dend) {
		this.txtpolicy_dend = txtpolicy_dend;
	}

	public String getTxtpolicy_dprt() {
		return txtpolicy_dprt;
	}

	public void setTxtpolicy_dprt(String txtpolicy_dprt) {
		this.txtpolicy_dprt = txtpolicy_dprt;
	}

	public String getTxtpolicy_type() {
		return txtpolicy_type;
	}

	public void setTxtpolicy_type(String txtpolicy_type) {
		this.txtpolicy_type = txtpolicy_type;
	}

	public String getTxtpolicy_year() {
		return txtpolicy_year;
	}

	public void setTxtpolicy_year(String txtpolicy_year) {
		this.txtpolicy_year = txtpolicy_year;
	}

	public String getTxtfire_type() {
		return txtfire_type;
	}

	public void setTxtfire_type(String txtfire_type) {
		this.txtfire_type = txtfire_type;
	}

	public String getTxtirate_type() {
		return txtirate_type;
	}

	public void setTxtirate_type(String txtirate_type) {
		this.txtirate_type = txtirate_type;
	}

	public String getTxtcoins_flag() {
		return txtcoins_flag;
	}

	public void setTxtcoins_flag(String txtcoins_flag) {
		this.txtcoins_flag = txtcoins_flag;
	}

	public BigDecimal getTxtcoins_rate() {
		return txtcoins_rate;
	}

	public void setTxtcoins_rate(BigDecimal txtcoins_rate) {
		this.txtcoins_rate = txtcoins_rate;
	}

	public BigDecimal getNumamt() {
		return numamt;
	}

	public void setNumamt(BigDecimal numamt) {
		this.numamt = numamt;
	}

	public BigDecimal getNumprem() {
		return numprem;
	}

	public void setNumprem(BigDecimal numprem) {
		this.numprem = numprem;
	}

	public BigDecimal getNumall_amt() {
		return numall_amt;
	}

	public void setNumall_amt(BigDecimal numall_amt) {
		this.numall_amt = numall_amt;
	}

	public BigDecimal getNumamt_1_eq() {
		return numamt_1_eq;
	}

	public void setNumamt_1_eq(BigDecimal numamt_1_eq) {
		this.numamt_1_eq = numamt_1_eq;
	}

	public BigDecimal getNumamt_2_ty() {
		return numamt_2_ty;
	}

	public void setNumamt_2_ty(BigDecimal numamt_2_ty) {
		this.numamt_2_ty = numamt_2_ty;
	}

	public BigDecimal getNumamt_3_bi() {
		return numamt_3_bi;
	}

	public void setNumamt_3_bi(BigDecimal numamt_3_bi) {
		this.numamt_3_bi = numamt_3_bi;
	}

	public BigDecimal getNum_amt_hn() {
		return num_amt_hn;
	}

	public void setNum_amt_hn(BigDecimal num_amt_hn) {
		this.num_amt_hn = num_amt_hn;
	}

	public BigDecimal getNum_prem_hn() {
		return num_prem_hn;
	}

	public void setNum_prem_hn(BigDecimal num_prem_hn) {
		this.num_prem_hn = num_prem_hn;
	}

	public BigDecimal getNumAllAmtHn() {
		return numAllAmtHn;
	}

	public void setNumAllAmtHn(BigDecimal numAllAmtHn) {
		this.numAllAmtHn = numAllAmtHn;
	}

	public BigDecimal getNumamt_1_eq_hn() {
		return numamt_1_eq_hn;
	}

	public void setNumamt_1_eq_hn(BigDecimal numamt_1_eq_hn) {
		this.numamt_1_eq_hn = numamt_1_eq_hn;
	}

	public BigDecimal getNumamt_2_ty_hn() {
		return numamt_2_ty_hn;
	}

	public void setNumamt_2_ty_hn(BigDecimal numamt_2_ty_hn) {
		this.numamt_2_ty_hn = numamt_2_ty_hn;
	}

	public BigDecimal getNumamt_3_bi_hn() {
		return numamt_3_bi_hn;
	}

	public void setNumamt_3_bi_hn(BigDecimal numamt_3_bi_hn) {
		this.numamt_3_bi_hn = numamt_3_bi_hn;
	}

	public BigDecimal getNumall_prem() {
		return numall_prem;
	}

	public void setNumall_prem(BigDecimal numall_prem) {
		this.numall_prem = numall_prem;
	}

	public BigDecimal getNumprem_1_reins() {
		return numprem_1_reins;
	}

	public void setNumprem_1_reins(BigDecimal numprem_1_reins) {
		this.numprem_1_reins = numprem_1_reins;
	}

	public BigDecimal getNumprem_2_eq() {
		return numprem_2_eq;
	}

	public void setNumprem_2_eq(BigDecimal numprem_2_eq) {
		this.numprem_2_eq = numprem_2_eq;
	}

	public BigDecimal getNumprem_3_ty() {
		return numprem_3_ty;
	}

	public void setNumprem_3_ty(BigDecimal numprem_3_ty) {
		this.numprem_3_ty = numprem_3_ty;
	}

	public BigDecimal getNumprem_4_bi() {
		return numprem_4_bi;
	}

	public void setNumprem_4_bi(BigDecimal numprem_4_bi) {
		this.numprem_4_bi = numprem_4_bi;
	}

	public BigDecimal getNumall_prem_hn() {
		return numall_prem_hn;
	}

	public void setNumall_prem_hn(BigDecimal numall_prem_hn) {
		this.numall_prem_hn = numall_prem_hn;
	}

	public BigDecimal getNumAllPremHn() {
		return numAllPremHn;
	}

	public void setNumAllPremHn(BigDecimal numAllPremHn) {
		this.numAllPremHn = numAllPremHn;
	}

	public BigDecimal getNumprem_1_reins_hn() {
		return numprem_1_reins_hn;
	}

	public void setNumprem_1_reins_hn(BigDecimal numprem_1_reins_hn) {
		this.numprem_1_reins_hn = numprem_1_reins_hn;
	}

	public BigDecimal getNumprem_2_eq_hn() {
		return numprem_2_eq_hn;
	}

	public void setNumprem_2_eq_hn(BigDecimal numprem_2_eq_hn) {
		this.numprem_2_eq_hn = numprem_2_eq_hn;
	}

	public BigDecimal getNumprem_3_ty_hn() {
		return numprem_3_ty_hn;
	}

	public void setNumprem_3_ty_hn(BigDecimal numprem_3_ty_hn) {
		this.numprem_3_ty_hn = numprem_3_ty_hn;
	}

	public BigDecimal getNumprem_4_bi_hn() {
		return numprem_4_bi_hn;
	}

	public void setNumprem_4_bi_hn(BigDecimal numprem_4_bi_hn) {
		this.numprem_4_bi_hn = numprem_4_bi_hn;
	}

	public BigDecimal getNumcom_amt() {
		return numcom_amt;
	}

	public void setNumcom_amt(BigDecimal numcom_amt) {
		this.numcom_amt = numcom_amt;
	}

	public BigDecimal getNumcom_prem() {
		return numcom_prem;
	}

	public void setNumcom_prem(BigDecimal numcom_prem) {
		this.numcom_prem = numcom_prem;
	}

	public BigDecimal getNumcomm() {
		return numcomm;
	}

	public void setNumcomm(BigDecimal numcomm) {
		this.numcomm = numcomm;
	}

	public BigDecimal getNumcomm_rate() {
		return numcomm_rate;
	}

	public void setNumcomm_rate(BigDecimal numcomm_rate) {
		this.numcomm_rate = numcomm_rate;
	}

	public BigDecimal getNumprepay_rate() {
		return numprepay_rate;
	}

	public void setNumprepay_rate(BigDecimal numprepay_rate) {
		this.numprepay_rate = numprepay_rate;
	}

	public String getTxtend_reason() {
		return txtend_reason;
	}

	public void setTxtend_reason(String txtend_reason) {
		this.txtend_reason = txtend_reason;
	}

	public String getTxtcalc_flag() {
		return txtcalc_flag;
	}

	public void setTxtcalc_flag(String txtcalc_flag) {
		this.txtcalc_flag = txtcalc_flag;
	}

	public String getTxtref_no() {
		return txtref_no;
	}

	public void setTxtref_no(String txtref_no) {
		this.txtref_no = txtref_no;
	}

	public String getTxtRi_policyno() {
		return txtRi_policyno;
	}

	public void setTxtRi_policyno(String txtRi_policyno) {
		this.txtRi_policyno = txtRi_policyno;
	}

	public String getTxtchange_flag() {
		return txtchange_flag;
	}

	public void setTxtchange_flag(String txtchange_flag) {
		this.txtchange_flag = txtchange_flag;
	}

	public String getTxtFac_flag() {
		return txtFac_flag;
	}

	public void setTxtFac_flag(String txtFac_flag) {
		this.txtFac_flag = txtFac_flag;
	}

	public String getTxtpolicy_mode() {
		return txtpolicy_mode;
	}

	public void setTxtpolicy_mode(String txtpolicy_mode) {
		this.txtpolicy_mode = txtpolicy_mode;
	}

	public String getTxtRin_com_id() {
		return txtRin_com_id;
	}

	public void setTxtRin_com_id(String txtRin_com_id) {
		this.txtRin_com_id = txtRin_com_id;
	}

	public String getTxttxtOfficer1() {
		return txttxtOfficer1;
	}

	public void setTxttxtOfficer1(String txttxtOfficer1) {
		this.txttxtOfficer1 = txttxtOfficer1;
	}

	public String getTxttxtOfficer2() {
		return txttxtOfficer2;
	}

	public void setTxttxtOfficer2(String txttxtOfficer2) {
		this.txttxtOfficer2 = txttxtOfficer2;
	}

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

	public BigDecimal getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	public BigDecimal getTotalPrem() {
		return totalPrem;
	}

	public void setTotalPrem(BigDecimal totalPrem) {
		this.totalPrem = totalPrem;
	}

	public String getTxtcname() {
		return txtcname;
	}

	public void setTxtcname(String txtcname) {
		this.txtcname = txtcname;
	}

	
}

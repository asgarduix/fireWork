package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1304保批單資料頁面所有欄位")
public class Rin1304FriPolicyVORes {
	

	@ApiModelProperty (value = "保單號碼")
	private String txtpolicy_no;
	
	@ApiModelProperty (value = "批單號碼")
	private String txtendorse_no;
	
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
	
	@ApiModelProperty (value = "年期")
	private String txtpolicy_year;
	
	@ApiModelProperty (value = "險種")
	private String txtfire_type;
	
	@ApiModelProperty (value = "費率性質代號")
	private String txtirate_type;
	
	@ApiModelProperty (value = "共保註記")
	private String txtcoins_flag;
	
	@ApiModelProperty (value = "共保比率")
	private String txtcoins_rate;
	
	@ApiModelProperty (value = "共保保額總保額")
	private String numall_amt;
	
	@ApiModelProperty (value = "華南保額總保額")
	private String num_amt;
	
	@ApiModelProperty (value = "共保保費總保費")
	private String numall_prem;
	
	@ApiModelProperty (value = "異動保額")
	private String numcom_amt;
	
	@ApiModelProperty (value = "異動保費")
	private String numcom_prem;
	
	@ApiModelProperty (value = "佣金率")
	private String numcomm;
	
	@ApiModelProperty (value = "批加減佣金")
	private String numcomm_rate;

	@ApiModelProperty (value = "預收比率")
	private String numprepay_rate;
	
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
	
	@ApiModelProperty (value = "分入公司")
	private String txtRin_com_id;
	
	@ApiModelProperty (value = "經手人1")
	private String txttxtOfficer1;
	
	@ApiModelProperty (value = "經手人2")
	private String txtTxtOfficer2;

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

	public String getTxtcoins_rate() {
		return txtcoins_rate;
	}

	public void setTxtcoins_rate(String txtcoins_rate) {
		this.txtcoins_rate = txtcoins_rate;
	}

	public String getNumall_amt() {
		return numall_amt;
	}

	public void setNumall_amt(String numall_amt) {
		this.numall_amt = numall_amt;
	}

	public String getNum_amt() {
		return num_amt;
	}

	public void setNum_amt(String num_amt) {
		this.num_amt = num_amt;
	}

	public String getNumall_prem() {
		return numall_prem;
	}

	public void setNumall_prem(String numall_prem) {
		this.numall_prem = numall_prem;
	}

	public String getNumcom_amt() {
		return numcom_amt;
	}

	public void setNumcom_amt(String numcom_amt) {
		this.numcom_amt = numcom_amt;
	}

	public String getNumcom_prem() {
		return numcom_prem;
	}

	public void setNumcom_prem(String numcom_prem) {
		this.numcom_prem = numcom_prem;
	}

	public String getNumcomm() {
		return numcomm;
	}

	public void setNumcomm(String numcomm) {
		this.numcomm = numcomm;
	}

	public String getNumcomm_rate() {
		return numcomm_rate;
	}

	public void setNumcomm_rate(String numcomm_rate) {
		this.numcomm_rate = numcomm_rate;
	}

	public String getNumprepay_rate() {
		return numprepay_rate;
	}

	public void setNumprepay_rate(String numprepay_rate) {
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

	public String getTxtTxtOfficer2() {
		return txtTxtOfficer2;
	}

	public void setTxtTxtOfficer2(String txtTxtOfficer2) {
		this.txtTxtOfficer2 = txtTxtOfficer2;
	}
	

}

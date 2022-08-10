package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1304保批單明細資料頁面所有欄位")
public class Rin1304FriPolicyDtlVOResp {
	
	@ApiModelProperty (value = "保單號碼")
	private String txtpolicy_no;
	
	@ApiModelProperty (value = "批單號碼")
	private String txtendorse_no;
	
	@ApiModelProperty (value = "地址序號")
	private String numaddr_no;
	
	@ApiModelProperty (value = "保單地址序號")
	private String numaddr_no_ori;
	
	@ApiModelProperty (value = "同險編號")
	private String txtrisk_no;
	
	@ApiModelProperty (value = "是否完成同險(Y/N)")
	private String txtrisk_flag;
	
	@ApiModelProperty (value = "同險名稱")
	private String txtrisk_name;
	
	@ApiModelProperty (value = "起保日")
	private String txtpolicy_dbgn;
	
	@ApiModelProperty (value = "訖止日")
	private String txtpolicy_dend;
	
	@ApiModelProperty (value = "標的物地址")
	private String txtprop_addr;
	
	@ApiModelProperty (value = "郵遞區號")
	private String txtzip_code;
	
	@ApiModelProperty (value = "地段代碼")
	private String txtarea_code;
	
	@ApiModelProperty (value = "使用性質代號")
	private String txtuseprop_code;
	
	@ApiModelProperty (value = "使用性質名稱")
	private String txtuseprop_name;
	
	@ApiModelProperty (value = "樓層數")
	private String numFlor_no;
	
	@ApiModelProperty (value = "建築等級英文代號")
	private String txtconst_class;
	
	@ApiModelProperty (value = "限額代號")
	private String limit_no;
	
	@ApiModelProperty (value = "限額")
	private String limit;
	
	@ApiModelProperty (value = "限額比率")
	private String txtlimit_rate;
	
	@ApiModelProperty (value = "保單限額")
	private String numlimit_ori;
	
	@ApiModelProperty (value = "異動總保額")
	private String numamt;
	
	@ApiModelProperty (value = "異動總保費")
	private String numprem;
	
	@ApiModelProperty (value = "流動貨物保額")
	private String numamt_flt;
	
	@ApiModelProperty (value = "流動貨物保費")
	private String numprem_flt;
	
	@ApiModelProperty (value = "非流動貨物保額")
	private String numamt_fix;
	
	@ApiModelProperty (value = "非流動貨物保費")
	private String numprem_fix;
	
	@ApiModelProperty (value = "颱洪本單總保額")
	private String numamt_typ;
	
	@ApiModelProperty (value = "颱洪本單總保費")
	private String numprem_typ;
	
	@ApiModelProperty (value = "地震本單總保額")
	private String numamt_ear;
	
	@ApiModelProperty (value = "地震本單總保費")
	private String numprem_ear;
	
	@ApiModelProperty (value = "該明細累計保額")
	private String sumamt;
	
	@ApiModelProperty (value = "29險種代碼")
	private String txtmercno;

	
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

	public String getTxtrisk_no() {
		return txtrisk_no;
	}

	public void setTxtrisk_no(String txtrisk_no) {
		this.txtrisk_no = txtrisk_no;
	}

	public String getTxtrisk_flag() {
		return txtrisk_flag;
	}

	public void setTxtrisk_flag(String txtrisk_flag) {
		this.txtrisk_flag = txtrisk_flag;
	}

	public String getTxtrisk_name() {
		return txtrisk_name;
	}

	public void setTxtrisk_name(String txtrisk_name) {
		this.txtrisk_name = txtrisk_name;
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

	public String getTxtprop_addr() {
		return txtprop_addr;
	}

	public void setTxtprop_addr(String txtprop_addr) {
		this.txtprop_addr = txtprop_addr;
	}

	public String getTxtzip_code() {
		return txtzip_code;
	}

	public void setTxtzip_code(String txtzip_code) {
		this.txtzip_code = txtzip_code;
	}

	public String getTxtarea_code() {
		return txtarea_code;
	}

	public void setTxtarea_code(String txtarea_code) {
		this.txtarea_code = txtarea_code;
	}

	public String getTxtuseprop_code() {
		return txtuseprop_code;
	}

	public void setTxtuseprop_code(String txtuseprop_code) {
		this.txtuseprop_code = txtuseprop_code;
	}

	public String getTxtuseprop_name() {
		return txtuseprop_name;
	}

	public void setTxtuseprop_name(String txtuseprop_name) {
		this.txtuseprop_name = txtuseprop_name;
	}

	public String getNumFlor_no() {
		return numFlor_no;
	}

	public void setNumFlor_no(String numFlor_no) {
		this.numFlor_no = numFlor_no;
	}

	public String getTxtconst_class() {
		return txtconst_class;
	}

	public void setTxtconst_class(String txtconst_class) {
		this.txtconst_class = txtconst_class;
	}

	public String getLimit_no() {
		return limit_no;
	}

	public void setLimit_no(String limit_no) {
		this.limit_no = limit_no;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getTxtlimit_rate() {
		return txtlimit_rate;
	}

	public void setTxtlimit_rate(String txtlimit_rate) {
		this.txtlimit_rate = txtlimit_rate;
	}

	public String getNumlimit_ori() {
		return numlimit_ori;
	}

	public void setNumlimit_ori(String numlimit_ori) {
		this.numlimit_ori = numlimit_ori;
	}

	public String getNumamt() {
		return numamt;
	}

	public void setNumamt(String numamt) {
		this.numamt = numamt;
	}

	public String getNumprem() {
		return numprem;
	}

	public void setNumprem(String numprem) {
		this.numprem = numprem;
	}

	public String getNumamt_flt() {
		return numamt_flt;
	}

	public void setNumamt_flt(String numamt_flt) {
		this.numamt_flt = numamt_flt;
	}

	public String getNumprem_flt() {
		return numprem_flt;
	}

	public void setNumprem_flt(String numprem_flt) {
		this.numprem_flt = numprem_flt;
	}

	public String getNumamt_fix() {
		return numamt_fix;
	}

	public void setNumamt_fix(String numamt_fix) {
		this.numamt_fix = numamt_fix;
	}

	public String getNumprem_fix() {
		return numprem_fix;
	}

	public void setNumprem_fix(String numprem_fix) {
		this.numprem_fix = numprem_fix;
	}

	public String getNumamt_typ() {
		return numamt_typ;
	}

	public void setNumamt_typ(String numamt_typ) {
		this.numamt_typ = numamt_typ;
	}

	public String getNumprem_typ() {
		return numprem_typ;
	}

	public void setNumprem_typ(String numprem_typ) {
		this.numprem_typ = numprem_typ;
	}

	public String getNumamt_ear() {
		return numamt_ear;
	}

	public void setNumamt_ear(String numamt_ear) {
		this.numamt_ear = numamt_ear;
	}

	public String getNumprem_ear() {
		return numprem_ear;
	}

	public void setNumprem_ear(String numprem_ear) {
		this.numprem_ear = numprem_ear;
	}

	public String getSumamt() {
		return sumamt;
	}

	public void setSumamt(String sumamt) {
		this.sumamt = sumamt;
	}

	public String getTxtmercno() {
		return txtmercno;
	}

	public void setTxtmercno(String txtmercno) {
		this.txtmercno = txtmercno;
	}

	public String getNumaddr_no_ori() {
		return numaddr_no_ori;
	}

	public void setNumaddr_no_ori(String numaddr_no_ori) {
		this.numaddr_no_ori = numaddr_no_ori;
	}
	
	
}

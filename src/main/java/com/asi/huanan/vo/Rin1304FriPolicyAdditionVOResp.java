package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1304保批單附加險明細資料頁面所有欄位")
public class Rin1304FriPolicyAdditionVOResp {
	
	@ApiModelProperty (value = "保單號碼")
	private String txtpolicy_no;
	
	@ApiModelProperty (value = "批單號碼")
	private String txtendorse_no;
	
	@ApiModelProperty (value = "地址序號")
	private String txtaddr_no;
	
	@ApiModelProperty (value = "標的物序號")
	private String txtprop_no;
	
	@ApiModelProperty (value = "附加險序號")
	private String txtaddition_seq;
	
	@ApiModelProperty (value = "附加險代號")
	private String txtaddition_no;
	
	@ApiModelProperty (value = "附加險名稱")
	private String txtaddition_name;
	
	@ApiModelProperty (value = "保額")
	private String numamt;

	@ApiModelProperty (value = "保費")
	private String numprem;
	
	@ApiModelProperty (value = "預收比例")
	private String numprercv_rate;
	
	@ApiModelProperty (value = "費率％")
	private String numrate;
	
	@ApiModelProperty (value = "100%保額")
	private String numcoinamt;
	
	@ApiModelProperty (value = "100%保費")
	private String numcoinprem;
	
	@ApiModelProperty (value = "限額比例")
	private String numlimit_rate;
	
	@ApiModelProperty (value = "單次賠款限額")
	private String numlimit;
	
	@ApiModelProperty (value = "全年累計賠款限額")
	private String numlimit_year;
	
	@ApiModelProperty (value = "29 險種代碼")
	private String txtmercno;

	@ApiModelProperty (value = "自付額說明")
	private String numdeduct_rem;
	
	@ApiModelProperty (value = "共保比率")
	private String coinsRate;

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

	public String getTxtaddr_no() {
		return txtaddr_no;
	}

	public void setTxtaddr_no(String txtaddr_no) {
		this.txtaddr_no = txtaddr_no;
	}

	public String getTxtprop_no() {
		return txtprop_no;
	}

	public void setTxtprop_no(String txtprop_no) {
		this.txtprop_no = txtprop_no;
	}

	public String getTxtaddition_seq() {
		return txtaddition_seq;
	}

	public void setTxtaddition_seq(String txtaddition_seq) {
		this.txtaddition_seq = txtaddition_seq;
	}

	public String getTxtaddition_no() {
		return txtaddition_no;
	}

	public void setTxtaddition_no(String txtaddition_no) {
		this.txtaddition_no = txtaddition_no;
	}

	public String getTxtaddition_name() {
		return txtaddition_name;
	}

	public void setTxtaddition_name(String txtaddition_name) {
		this.txtaddition_name = txtaddition_name;
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

	public String getNumprercv_rate() {
		return numprercv_rate;
	}

	public void setNumprercv_rate(String numprercv_rate) {
		this.numprercv_rate = numprercv_rate;
	}

	public String getNumrate() {
		return numrate;
	}

	public void setNumrate(String numrate) {
		this.numrate = numrate;
	}

	public String getNumcoinamt() {
		return numcoinamt;
	}

	public void setNumcoinamt(String numcoinamt) {
		this.numcoinamt = numcoinamt;
	}

	public String getNumcoinprem() {
		return numcoinprem;
	}

	public void setNumcoinprem(String numcoinprem) {
		this.numcoinprem = numcoinprem;
	}

	public String getNumlimit_rate() {
		return numlimit_rate;
	}

	public void setNumlimit_rate(String numlimit_rate) {
		this.numlimit_rate = numlimit_rate;
	}

	public String getNumlimit() {
		return numlimit;
	}

	public void setNumlimit(String numlimit) {
		this.numlimit = numlimit;
	}

	public String getNumlimit_year() {
		return numlimit_year;
	}

	public void setNumlimit_year(String numlimit_year) {
		this.numlimit_year = numlimit_year;
	}

	public String getTxtmercno() {
		return txtmercno;
	}

	public void setTxtmercno(String txtmercno) {
		this.txtmercno = txtmercno;
	}

	public String getNumdeduct_rem() {
		return numdeduct_rem;
	}

	public void setNumdeduct_rem(String numdeduct_rem) {
		this.numdeduct_rem = numdeduct_rem;
	}

	public String getCoinsRate() {
		return coinsRate;
	}

	public void setCoinsRate(String coinsRate) {
		this.coinsRate = coinsRate;
	}
	

	


}

package com.asi.huanan.vo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1203A頁面所有欄位")
public class Rin1203AVOResp {
	
	@ApiModelProperty (value = "同險代號")
	private String txtrisk_no;
	
	@ApiModelProperty (value = "被保險人")
	private String txtcinsurant;	
	
	@ApiModelProperty (value = "處理狀態")
	private String txtrisk_flag;

	@ApiModelProperty (value = "使用性質代號")
	private String txtuseprop_code;

	@ApiModelProperty (value = "使用性質名稱")
	private String txtuseprop_name;

	@ApiModelProperty (value = "保單號碼")
	private String txtpolicy_no;
	
	@ApiModelProperty (value = "批單號碼")
	private String txtendorse_no;

	@ApiModelProperty (value = "地址序號")
	private Integer numaddr_no;

	@ApiModelProperty (value = "保額")
	private BigDecimal numamt;
	
	@ApiModelProperty (value = "地段代號")
	private String txtarea_code;

	@ApiModelProperty (value = "標的物地址")
	private String txtprop_addr;
	
	@ApiModelProperty (value = "同險名稱")
	private String txtrisk_name;
	
	@ApiModelProperty (value = "已分保列印")
	private String txtacct_flag;
	
	@ApiModelProperty (value = "判別範圍碼用")
	private Integer procCount;
	
	@ApiModelProperty (value = "id序號")
	private Integer id;

	public String getTxtrisk_no() {
		return txtrisk_no;
	}

	public void setTxtrisk_no(String txtrisk_no) {
		this.txtrisk_no = txtrisk_no;
	}

	public String getTxtcinsurant() {
		return txtcinsurant;
	}

	public void setTxtcinsurant(String txtcinsurant) {
		this.txtcinsurant = txtcinsurant;
	}

	public String getTxtrisk_flag() {
		return txtrisk_flag;
	}

	public void setTxtrisk_flag(String txtrisk_flag) {
		this.txtrisk_flag = txtrisk_flag;
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


	public Integer getNumaddr_no() {
		return numaddr_no;
	}

	public void setNumaddr_no(Integer numaddr_no) {
		this.numaddr_no = numaddr_no;
	}

	public BigDecimal getNumamt() {
		return numamt;
	}

	public void setNumamt(BigDecimal numamt) {
		this.numamt = numamt;
	}

	public String getTxtarea_code() {
		return txtarea_code;
	}

	public void setTxtarea_code(String txtarea_code) {
		this.txtarea_code = txtarea_code;
	}

	public String getTxtprop_addr() {
		return txtprop_addr;
	}

	public void setTxtprop_addr(String txtprop_addr) {
		this.txtprop_addr = txtprop_addr;
	}

	public String getTxtrisk_name() {
		return txtrisk_name;
	}

	public void setTxtrisk_name(String txtrisk_name) {
		this.txtrisk_name = txtrisk_name;
	}

	public String getTxtacct_flag() {
		return txtacct_flag;
	}

	public void setTxtacct_flag(String txtacct_flag) {
		this.txtacct_flag = txtacct_flag;
	}

	public Integer getProcCount() {
		return procCount;
	}

	public void setProcCount(Integer procCount) {
		this.procCount = procCount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
	
}

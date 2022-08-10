package com.asi.huanan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1304保批單標的物明細資料頁面所有欄位")
public class Rin1304FriPolicyPropVOResp {
	
	@ApiModelProperty (value = "保單號碼")
	private String txtpolicy_no;
	
	@ApiModelProperty (value = "批單號碼")
	private String txtendorse_no;

	@ApiModelProperty (value = "地址序號")
	private String numaddr_no;
	
	@ApiModelProperty (value = "標的物序號")
	private String numprop_no;
	
	@ApiModelProperty (value = "流動註記")
	private String txtfloatFlag;
	
	@ApiModelProperty (value = "標的物代號")
	private String txtproperty_no;
	
	@ApiModelProperty (value = "標的物保品名稱")
	private String txtproperty_name;
	
	@ApiModelProperty (value = "標的物保品簡碼")
	private String txtproperty_code;
	
	@ApiModelProperty (value = "保額")
	private String numamt;
	
	@ApiModelProperty (value = "保費")
	private String numprem;
	
	@ApiModelProperty (value = "使用性質代號")
	private String txtuseprop_code;
	
	@ApiModelProperty (value = "使用性質名稱")
	private String txtuseprop_name;
	
	@ApiModelProperty (value = "建築等級英文代號")
	private String txtconst_class;
	
	@ApiModelProperty (value = "限額代號")
	private String txtlimit_no;
	
	@ApiModelProperty (value = "適用特別條款")
	private String txtspec_term;
	
	@ApiModelProperty (value = "適用附加條款")
	private String txtext_term;

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

	public String getNumprop_no() {
		return numprop_no;
	}

	public void setNumprop_no(String numprop_no) {
		this.numprop_no = numprop_no;
	}

	public String getTxtfloatFlag() {
		return txtfloatFlag;
	}

	public void setTxtfloatFlag(String txtfloatFlag) {
		this.txtfloatFlag = txtfloatFlag;
	}

	public String getTxtproperty_no() {
		return txtproperty_no;
	}

	public void setTxtproperty_no(String txtproperty_no) {
		this.txtproperty_no = txtproperty_no;
	}

	public String getTxtproperty_name() {
		return txtproperty_name;
	}

	public void setTxtproperty_name(String txtproperty_name) {
		this.txtproperty_name = txtproperty_name;
	}

	public String getTxtproperty_code() {
		return txtproperty_code;
	}

	public void setTxtproperty_code(String txtproperty_code) {
		this.txtproperty_code = txtproperty_code;
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

	public String getTxtconst_class() {
		return txtconst_class;
	}

	public void setTxtconst_class(String txtconst_class) {
		this.txtconst_class = txtconst_class;
	}

	public String getTxtlimit_no() {
		return txtlimit_no;
	}

	public void setTxtlimit_no(String txtlimit_no) {
		this.txtlimit_no = txtlimit_no;
	}

	public String getTxtspec_term() {
		return txtspec_term;
	}

	public void setTxtspec_term(String txtspec_term) {
		this.txtspec_term = txtspec_term;
	}

	public String getTxtext_term() {
		return txtext_term;
	}

	public void setTxtext_term(String txtext_term) {
		this.txtext_term = txtext_term;
	}

	
	
}

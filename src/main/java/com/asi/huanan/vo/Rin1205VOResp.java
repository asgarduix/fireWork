package com.asi.huanan.vo;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1205表格欄位")
public class Rin1205VOResp {

	@ApiModelProperty (value = "保單號")
	private String txtpolicy_no;			
	@ApiModelProperty (value = "批單號")
	private String txtendorse_no;			
	@ApiModelProperty (value = "序號")
	private String numaddr_no;			
	@ApiModelProperty (value = "生效日期起")
	private String dtaduty_bgn;	
	@ApiModelProperty (value = "生效日期迄")
	private String dtaduty_end;
	@ApiModelProperty (value = "同險代號")
	private String riskNo;
	@ApiModelProperty (value = "使用性質")
	private String txtuseprop_name;
	@ApiModelProperty (value = "印單日")
	private String dtapolicy_dprt;
	@ApiModelProperty (value = "總保額")
	private String numamt;			
	@ApiModelProperty (value = "總保費")
	private String numprem;			
	@ApiModelProperty (value = "保單費率")
	private String numprem_rate;			
	@ApiModelProperty (value = "分保費率")
	private String numshare_prem_rate;	
	@ApiModelProperty (value = "保單限額")
	private String numlimit;
	@ApiModelProperty (value = "臨分保額")
	private BigDecimal sum_amt_1;			
	@ApiModelProperty (value = "地震自留")
	private BigDecimal numamt_ear_1;			
	@ApiModelProperty (value = "颱風自留")
	private BigDecimal numamt_typ_1;			
	@ApiModelProperty (value = "地震臨分")
	private BigDecimal numamt_ear_2;	
	@ApiModelProperty (value = "颱風臨分")
	private BigDecimal numamt_typ_2;
	@ApiModelProperty (value = "帳單註記")
	private String txtacct_flag;
	@ApiModelProperty (value = "原批單號")
	private String txtiendorsement2;
	@ApiModelProperty (value = "各合約分出保額/保費")
	private List<Rin1205VORespSub> rin1205SubVo;
	@ApiModelProperty (value = "合約年度")
	private String treaty_year;
	@ApiModelProperty (value = "資料pk")
	private String primaryKey;
	
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
	public String getDtaduty_bgn() {
		return dtaduty_bgn;
	}
	public void setDtaduty_bgn(String dtaduty_bgn) {
		this.dtaduty_bgn = dtaduty_bgn;
	}
	public String getDtaduty_end() {
		return dtaduty_end;
	}
	public void setDtaduty_end(String dtaduty_end) {
		this.dtaduty_end = dtaduty_end;
	}
	public String getRiskNo() {
		return riskNo;
	}
	public void setRiskNo(String riskNo) {
		this.riskNo = riskNo;
	}
	public String getTxtuseprop_name() {
		return txtuseprop_name;
	}
	public void setTxtuseprop_name(String txtuseprop_name) {
		this.txtuseprop_name = txtuseprop_name;
	}
	public String getDtapolicy_dprt() {
		return dtapolicy_dprt;
	}
	public void setDtapolicy_dprt(String dtapolicy_dprt) {
		this.dtapolicy_dprt = dtapolicy_dprt;
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
	public String getNumprem_rate() {
		return numprem_rate;
	}
	public void setNumprem_rate(String numprem_rate) {
		this.numprem_rate = numprem_rate;
	}
	public String getNumshare_prem_rate() {
		return numshare_prem_rate;
	}
	public void setNumshare_prem_rate(String numshare_prem_rate) {
		this.numshare_prem_rate = numshare_prem_rate;
	}
	public String getNumlimit() {
		return numlimit;
	}
	public void setNumlimit(String numlimit) {
		this.numlimit = numlimit;
	}
	public BigDecimal getSum_amt_1() {
		return sum_amt_1;
	}
	public void setSum_amt_1(BigDecimal sum_amt_1) {
		this.sum_amt_1 = sum_amt_1;
	}
	public BigDecimal getNumamt_ear_1() {
		return numamt_ear_1;
	}
	public void setNumamt_ear_1(BigDecimal numamt_ear_1) {
		this.numamt_ear_1 = numamt_ear_1;
	}
	public BigDecimal getNumamt_typ_1() {
		return numamt_typ_1;
	}
	public void setNumamt_typ_1(BigDecimal numamt_typ_1) {
		this.numamt_typ_1 = numamt_typ_1;
	}
	public BigDecimal getNumamt_ear_2() {
		return numamt_ear_2;
	}
	public void setNumamt_ear_2(BigDecimal numamt_ear_2) {
		this.numamt_ear_2 = numamt_ear_2;
	}
	public BigDecimal getNumamt_typ_2() {
		return numamt_typ_2;
	}
	public void setNumamt_typ_2(BigDecimal numamt_typ_2) {
		this.numamt_typ_2 = numamt_typ_2;
	}
	public String getTxtacct_flag() {
		return txtacct_flag;
	}
	public void setTxtacct_flag(String txtacct_flag) {
		this.txtacct_flag = txtacct_flag;
	}
	public String getTxtiendorsement2() {
		return txtiendorsement2;
	}
	public void setTxtiendorsement2(String txtiendorsement2) {
		this.txtiendorsement2 = txtiendorsement2;
	}
	public List<Rin1205VORespSub> getRin1205SubVo() {
		return rin1205SubVo;
	}
	public void setRin1205SubVo(List<Rin1205VORespSub> rin1205SubVo) {
		this.rin1205SubVo = rin1205SubVo;
	}
	public String getTreaty_year() {
		return treaty_year;
	}
	public void setTreaty_year(String treaty_year) {
		this.treaty_year = treaty_year;
	}
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	
}

package com.asi.huanan.vo.rin1301.res;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class Rin1301RinMainDataVoResp {
	
	@ApiModelProperty(value = "知會/更正號")
	private String slipNo;
	
	@ApiModelProperty(value = "合約號")
	private String cessionNo;
	
	@ApiModelProperty(value = "資料類型（知會,更正)")
	private String slipType; 
	
	@ApiModelProperty(value = "知會/更正號取號年度")
	private String slipNoGenYear;
	
	@ApiModelProperty(value = "合約號取號年度/合約年度")
	private String treatyYear;
	
	@ApiModelProperty(value = "合約名稱")
	private String cessionName;
	
	@ApiModelProperty(value = "前SLIP編號")
	private String preSlipNo;
	
	@ApiModelProperty(value = "前合約編號")
	private String preCessionNo;
	
	@JsonFormat(pattern="yyyy/MM/dd",timezone = "GMT+8")
	@ApiModelProperty(value = "生效期間起日")
	private Date treatyDbgn;
	
	@JsonFormat(pattern="yyyy/MM/dd",timezone = "GMT+8")
	@ApiModelProperty(value = "生效期間迄日")
	private Date treatyDend;
	
	@ApiModelProperty(value = "日數")
	private Short days;
	
	@JsonFormat(pattern="yyyy/MM/dd",timezone = "GMT+8")
	@ApiModelProperty(value = "立帳日")
	private Date treatyDate;
	
	@ApiModelProperty(value = "保批單作業序號")
	private String policyNoSeq;
	
	@ApiModelProperty(value = "臨分類型(1.一般2.超賠)")
	private String facType;
	
	@ApiModelProperty(value = "超賠起賠金額")
    private Long excessBgn;
	
	@ApiModelProperty(value = "超賠賠款上限")
    private Long excessLimit;

	@ApiModelProperty(value = "保批單號")
	 private String policyNo;
	
	@ApiModelProperty(value = "境外分入註記")
	private String mkovse;
	
	@ApiModelProperty(value = "同險被保險人等")
	 private String insurant;
	
	@ApiModelProperty(value = "同險被保險人住址")
	 private String address;
	
	@ApiModelProperty(value = "標的物使用性質")
	 private String useProp;

	@ApiModelProperty(value = "標的物建築等級")
	 private String construct;
	
	@ApiModelProperty(value = "險種")
	private String coverage;
	
	@ApiModelProperty(value = "自負額說明")
	 private String deductDesc;
	
	@ApiModelProperty(value = "更新者代號")
    private String lastupdateaccount;
	
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(value = "最後修改時間")
	private Date lastupdatedatetime;
	

	@ApiModelProperty(value = "幣別")
	private String currency;
	
	@ApiModelProperty(value = "匯率")
    private BigDecimal currencyexchangerate;
	
	@ApiModelProperty(value = "除外險種")
    private Set<String> excludeSet;
	
	@ApiModelProperty(value = "修改註記")
	private String logSeq;

	public String getSlipNo() {
		return slipNo;
	}

	public void setSlipNo(String slipNo) {
		this.slipNo = slipNo;
	}

	public String getCessionNo() {
		return cessionNo;
	}

	public void setCessionNo(String cessionNo) {
		this.cessionNo = cessionNo;
	}

	public String getSlipType() {
		return slipType;
	}

	public void setSlipType(String slipType) {
		this.slipType = slipType;
	}

	public String getSlipNoGenYear() {
		return slipNoGenYear;
	}

	public void setSlipNoGenYear(String slipNoGenYear) {
		this.slipNoGenYear = slipNoGenYear;
	}

	public String getTreatyYear() {
		return treatyYear;
	}

	public void setTreatyYear(String treatyYear) {
		this.treatyYear = treatyYear;
	}

	public String getCessionName() {
		return cessionName;
	}

	public void setCessionName(String cessionName) {
		this.cessionName = cessionName;
	}

	public String getPreSlipNo() {
		return preSlipNo;
	}

	public void setPreSlipNo(String preSlipNo) {
		this.preSlipNo = preSlipNo;
	}

	public String getPreCessionNo() {
		return preCessionNo;
	}

	public void setPreCessionNo(String preCessionNo) {
		this.preCessionNo = preCessionNo;
	}

	public Date getTreatyDbgn() {
		return treatyDbgn;
	}

	public void setTreatyDbgn(Date treatyDbgn) {
		this.treatyDbgn = treatyDbgn;
	}

	public Date getTreatyDend() {
		return treatyDend;
	}

	public void setTreatyDend(Date treatyDend) {
		this.treatyDend = treatyDend;
	}

	public Short getDays() {
		return days;
	}

	public void setDays(Short days) {
		this.days = days;
	}

	public Date getTreatyDate() {
		return treatyDate;
	}

	public void setTreatyDate(Date treatyDate) {
		this.treatyDate = treatyDate;
	}

	public String getPolicyNoSeq() {
		return policyNoSeq;
	}

	public void setPolicyNoSeq(String policyNoSeq) {
		this.policyNoSeq = policyNoSeq;
	}

	public String getFacType() {
		return facType;
	}

	public void setFacType(String facType) {
		this.facType = facType;
	}

	public Long getExcessBgn() {
		return excessBgn;
	}

	public void setExcessBgn(Long excessBgn) {
		this.excessBgn = excessBgn;
	}

	public Long getExcessLimit() {
		return excessLimit;
	}

	public void setExcessLimit(Long excessLimit) {
		this.excessLimit = excessLimit;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getMkovse() {
		return mkovse;
	}

	public void setMkovse(String mkovse) {
		this.mkovse = mkovse;
	}

	public String getInsurant() {
		return insurant;
	}

	public void setInsurant(String insurant) {
		this.insurant = insurant;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUseProp() {
		return useProp;
	}

	public void setUseProp(String useProp) {
		this.useProp = useProp;
	}

	public String getConstruct() {
		return construct;
	}

	public void setConstruct(String construct) {
		this.construct = construct;
	}

	public String getCoverage() {
		return coverage;
	}

	public void setCoverage(String coverage) {
		this.coverage = coverage;
	}

	public String getDeductDesc() {
		return deductDesc;
	}

	public void setDeductDesc(String deductDesc) {
		this.deductDesc = deductDesc;
	}

	public String getLastupdateaccount() {
		return lastupdateaccount;
	}

	public void setLastupdateaccount(String lastupdateaccount) {
		this.lastupdateaccount = lastupdateaccount;
	}

	public Date getLastupdatedatetime() {
		return lastupdatedatetime;
	}

	public void setLastupdatedatetime(Date lastupdatedatetime) {
		this.lastupdatedatetime = lastupdatedatetime;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getCurrencyexchangerate() {
		return currencyexchangerate;
	}

	public void setCurrencyexchangerate(BigDecimal currencyexchangerate) {
		this.currencyexchangerate = currencyexchangerate;
	}

	public Set<String> getExcludeSet() {
		return excludeSet;
	}

	public void setExcludeSet(Set<String> excludeSet) {
		this.excludeSet = excludeSet;
	}

	public String getLogSeq() {
		return logSeq;
	}

	public void setLogSeq(String logSeq) {
		this.logSeq = logSeq;
	}
	

	


	

}

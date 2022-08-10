package com.asi.huanan.vo.rin1301.req;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.asi.huanan.vo.rin1301.Rin1301SameRiskPolEndorseVo;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class Rin1301HandleDataVOReq {
	
	@ApiModelProperty(value = "知會/更正號")
	private String slipNo;
	
	@ApiModelProperty(value = "合約號")
	private String cessionNo;
	
	@ApiModelProperty(value = "作業型態(新增/臨分批單/臨分續保)")
	private String handleMode;
	
	@ApiModelProperty(value = "知會/更正號取號年度")
	private String slipNoGenYear;
	
	@ApiModelProperty(value = "合約號取號年度/合約年度")
	private String treatyYear;
	
	@ApiModelProperty(value = "資料類型（知會,更正)")
	private String slipType; 
	
	@JsonFormat(pattern="yyyy/MM/dd",timezone = "GMT+8")
	@ApiModelProperty(value = "生效期間起日")
	private Date treatyDbgn;
	
	@JsonFormat(pattern="yyyy/MM/dd",timezone = "GMT+8")
	@ApiModelProperty(value = "生效期間迄日")
	private Date treatyDend;
	
	@ApiModelProperty(value = "前SLIP編號")
	private String preSlipNo;
	
	@ApiModelProperty(value = "前合約編號")
	private String preCessionNo;
	
	@JsonFormat(pattern="yyyy/MM/dd",timezone = "GMT+8")
	@ApiModelProperty(value = "立帳日")
	private Date treatyDate;
	
	@ApiModelProperty(value = "合約名稱")
	private String cessionName;
	
	@ApiModelProperty(value = "日數")
	private Short days;
	
	@ApiModelProperty(value = "分保類型(1.同險2.逐單)")
	 private String riskType;
	
	@ApiModelProperty(value = "自負額說明")
	 private String deductDesc;
	
	@ApiModelProperty(value = "保批單號")
	 private String policyNo;
	
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
	
	@ApiModelProperty(value = "臨分類型(1.一般2.超賠)")
	private String facType;
	
	@ApiModelProperty(value = "超賠起賠金額")
    private Long excessBgn;
	
	@ApiModelProperty(value = "超賠賠款上限")
    private Long excessLimit;
	
	@ApiModelProperty(value = "保額合計")
	private Long amtSum;
	
	@ApiModelProperty(value = "是否出帳")
    private String treatyset;
	
	@ApiModelProperty(value = "幣別")
	private String currency;
	
	@ApiModelProperty(value = "匯率")
    private BigDecimal currencyexchangerate;
	
	@ApiModelProperty(value = "保批單作業序號")
	private String policyNoSeq;
	
	@ApiModelProperty(value = "更新者代號")
    private String lastupdateaccount;
	
	@ApiModelProperty(value = "境外分入註記")
	private String mkovse;
	
	@ApiModelProperty(value = "帳單列印否")
	private String acctFlag;
	
	@ApiModelProperty(value = "修改註記")
	private String logSeq;
	
	@ApiModelProperty(value = "同險代號及保批單")
	private Rin1301SameRiskPolEndorseVo sameriskPolEndorObj;
	
	@ApiModelProperty(value = "臨分分保明細")
	private List<Rin1301FacShareVoReq> facShareVoList;
	
	@ApiModelProperty(value = "臨分除外險種明細")
	private List<Rin1301FacExcludeVoReq> facExcludeVoList;
	
	@ApiModelProperty(value = "臨分經紀人資料明細")
	private List<Rin1301FacBrokerVoReq> facBrokerVoList;
	
	@ApiModelProperty(value = "臨分再保人資料明細")
	private List<Rin1301FacRinserVoReq> facRinserVoList;
	
	@ApiModelProperty(value = "臨分同險代號明細")
	private List<String> facSlipRiskVoList;
	
	@ApiModelProperty(value = "臨分費率明細")
	private List<Rin1301FacRateVoReq> facRateVoList;
	
	@ApiModelProperty(value = "臨分標的物明細")
	private List<Rin1301FacNpropVoReq> facNpropVoList;
	
	@ApiModelProperty(value = "臨分保批單明細")
	private List<Rin1301FacPolicyVoReq> facPolicyVoList;
	
	@ApiModelProperty(value = "會計轉檔狀況")
	private String conversionStatus;
	

	public String getHandleMode() {
		return handleMode;
	}

	public void setHandleMode(String handleMode) {
		this.handleMode = handleMode;
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

	public String getSlipType() {
		return slipType;
	}

	public void setSlipType(String slipType) {
		this.slipType = slipType;
	}

	public Rin1301SameRiskPolEndorseVo getSameriskPolEndorObj() {
		return sameriskPolEndorObj;
	}

	public void setSameriskPolEndorObj(Rin1301SameRiskPolEndorseVo sameriskPolEndorObj) {
		this.sameriskPolEndorObj = sameriskPolEndorObj;
	}

	public List<Rin1301FacShareVoReq> getFacShareVoList() {
		return facShareVoList;
	}

	public void setFacShareVoList(List<Rin1301FacShareVoReq> facShareVoList) {
		this.facShareVoList = facShareVoList;
	}

	public List<Rin1301FacExcludeVoReq> getFacExcludeVoList() {
		return facExcludeVoList;
	}

	public void setFacExcludeVoList(List<Rin1301FacExcludeVoReq> facExcludeVoList) {
		this.facExcludeVoList = facExcludeVoList;
	}

	public List<Rin1301FacBrokerVoReq> getFacBrokerVoList() {
		return facBrokerVoList;
	}

	public void setFacBrokerVoList(List<Rin1301FacBrokerVoReq> facBrokerVoList) {
		this.facBrokerVoList = facBrokerVoList;
	}



	public String getSlipNo() {
		return slipNo;
	}

	public void setSlipNo(String slipNo) {
		this.slipNo = slipNo;
	}


	public List<Rin1301FacRinserVoReq> getFacRinserVoList() {
		return facRinserVoList;
	}

	public void setFacRinserVoList(List<Rin1301FacRinserVoReq> facRinserVoList) {
		this.facRinserVoList = facRinserVoList;
	}

	public List<String> getFacSlipRiskVoList() {
		return facSlipRiskVoList;
	}

	public void setFacSlipRiskVoList(List<String> facSlipRiskVoList) {
		this.facSlipRiskVoList = facSlipRiskVoList;
	}

	public List<Rin1301FacRateVoReq> getFacRateVoList() {
		return facRateVoList;
	}

	public void setFacRateVoList(List<Rin1301FacRateVoReq> facRateVoList) {
		this.facRateVoList = facRateVoList;
	}

	public List<Rin1301FacNpropVoReq> getFacNpropVoList() {
		return facNpropVoList;
	}

	public void setFacNpropVoList(List<Rin1301FacNpropVoReq> facNpropVoList) {
		this.facNpropVoList = facNpropVoList;
	}


	public List<Rin1301FacPolicyVoReq> getFacPolicyVoList() {
		return facPolicyVoList;
	}

	public void setFacPolicyVoList(List<Rin1301FacPolicyVoReq> facPolicyVoList) {
		this.facPolicyVoList = facPolicyVoList;
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

	public Date getTreatyDate() {
		return treatyDate;
	}

	public void setTreatyDate(Date treatyDate) {
		this.treatyDate = treatyDate;
	}

	public String getCessionName() {
		return cessionName;
	}

	public void setCessionName(String cessionName) {
		this.cessionName = cessionName;
	}

	public Short getDays() {
		return days;
	}

	public void setDays(Short days) {
		this.days = days;
	}

	public String getRiskType() {
		return riskType;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public String getDeductDesc() {
		return deductDesc;
	}

	public void setDeductDesc(String deductDesc) {
		this.deductDesc = deductDesc;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
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
	

	public Long getAmtSum() {
		return amtSum;
	}

	public void setAmtSum(Long amtSum) {
		this.amtSum = amtSum;
	}

	public String getTreatyset() {
		return treatyset;
	}

	public void setTreatyset(String treatyset) {
		this.treatyset = treatyset;
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

	public String getPolicyNoSeq() {
		return policyNoSeq;
	}

	public void setPolicyNoSeq(String policyNoSeq) {
		this.policyNoSeq = policyNoSeq;
	}

	public String getLastupdateaccount() {
		return lastupdateaccount;
	}

	public void setLastupdateaccount(String lastupdateaccount) {
		this.lastupdateaccount = lastupdateaccount;
	}

	public String getMkovse() {
		return mkovse;
	}

	public void setMkovse(String mkovse) {
		this.mkovse = mkovse;
	}

	public String getAcctFlag() {
		return acctFlag;
	}

	public void setAcctFlag(String acctFlag) {
		this.acctFlag = acctFlag;
	}

	public String getCessionNo() {
		return cessionNo;
	}

	public void setCessionNo(String cessionNo) {
		this.cessionNo = cessionNo;
	}

	public String getLogSeq() {
		return logSeq;
	}

	public void setLogSeq(String logSeq) {
		this.logSeq = logSeq;
	}

	public String getConversionStatus() {
		return conversionStatus;
	}

	public void setConversionStatus(String conversionStatus) {
		this.conversionStatus = conversionStatus;
	}

}

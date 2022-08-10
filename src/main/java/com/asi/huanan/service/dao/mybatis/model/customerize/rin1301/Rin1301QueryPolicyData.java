package com.asi.huanan.service.dao.mybatis.model.customerize.rin1301;



import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1301查詢保批單資料")
public class Rin1301QueryPolicyData {
	
	@ApiModelProperty(value = "保單號")
	private String policyNo;

	@ApiModelProperty(value = "批單號")
	private String endorseNo;
	
	@ApiModelProperty(value = "合約號")
	private String cessionNo;
	
	@ApiModelProperty(value = "被保險人")
	private String cinsurant;
	
	@ApiModelProperty(value = "批改事由")
	private String endReason;
	
	@ApiModelProperty(value = "同險代號")
	private String riskNo;
	
	@ApiModelProperty(value = "地址序號")
	private String addrNo;
	
	@ApiModelProperty(value = "保額")
	private BigDecimal amt;
	
	@ApiModelProperty(value = "境外分入註記")
	private String mkovse;
	
	@ApiModelProperty(value = "使用性質名稱")
	private String usepropName;
	
	@ApiModelProperty(value = "建築等級")
	private String constClass;
	
	@ApiModelProperty(value = "標的物地址")
	private String propAddr;
	
	@ApiModelProperty(value = "附加險序號")
	private String additionSeq;
	
	@ApiModelProperty(value = "附加險代號")
	private String additionNo;
	
	@ApiModelProperty(value = "附加險中文名稱")
	private String additionName;
	
	@ApiModelProperty(value = "附加險英文名稱")
	private String additionEname;
	
	@ApiModelProperty(value = "標的物代號")
	private String propertyNo;
	
	@ApiModelProperty(value = "共保比率")
	private BigDecimal coinsRate;
	
	@ApiModelProperty(value = "共保保額")
	private BigDecimal coinsAmt;
	
	@ApiModelProperty(value = "標的物中文名稱")
	private String propertyName;
	
	@ApiModelProperty(value = "標的物英文簡稱")
	private String propertyEname;
	
	@ApiModelProperty(value = "保單始期")
	private Date policyDbgn;
	
	@ApiModelProperty(value = "保單終期")
	private Date policyDend;
	
	@ApiModelProperty(value = "保費")
	private BigDecimal prem;
	
	@ApiModelProperty(value = "佣金率")
	private BigDecimal commRate;
	
	@ApiModelProperty(value = "佣金")
	private BigDecimal comm;
	
	@ApiModelProperty(value = "共保保費")
	private BigDecimal coinsPrem;
	
	@ApiModelProperty(value = "險種代號")
	private String content;
	
	@ApiModelProperty(value = "計入保額")
	private String includeAmt;
	
	@ApiModelProperty(value = "計入保費")
	private String includePrem;
	
	@ApiModelProperty(value = "颱洪分保額")
	private BigDecimal amtTyp;
	
	@ApiModelProperty(value = "颱洪分保費")
	private BigDecimal premTyp;
	
	@ApiModelProperty(value = "地震分保額")
	private BigDecimal amtEar;
	
	@ApiModelProperty(value = "地震分保費")
	private BigDecimal premEar;
	
	@ApiModelProperty(value = "責任生效日")
	private Date dutyDbgn;
	
	@ApiModelProperty(value = "責任到期日")
	private Date dutyDend;


	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getEndorseNo() {
		return endorseNo;
	}

	public void setEndorseNo(String endorseNo) {
		this.endorseNo = endorseNo;
	}

	public String getCinsurant() {
		return cinsurant;
	}

	public void setCinsurant(String cinsurant) {
		this.cinsurant = cinsurant;
	}

	public String getEndReason() {
		return endReason;
	}

	public void setEndReason(String endReason) {
		this.endReason = endReason;
	}

	public String getRiskNo() {
		return riskNo;
	}

	public void setRiskNo(String riskNo) {
		this.riskNo = riskNo;
	}

	public String getAddrNo() {
		return addrNo;
	}

	public void setAddrNo(String addrNo) {
		this.addrNo = addrNo;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public String getMkovse() {
		return mkovse;
	}

	public void setMkovse(String mkovse) {
		this.mkovse = mkovse;
	}

	public String getUsepropName() {
		return usepropName;
	}

	public void setUsepropName(String usepropName) {
		this.usepropName = usepropName;
	}

	public String getConstClass() {
		return constClass;
	}

	public void setConstClass(String constClass) {
		this.constClass = constClass;
	}

	public String getPropAddr() {
		return propAddr;
	}

	public void setPropAddr(String propAddr) {
		this.propAddr = propAddr;
	}

	public String getAdditionSeq() {
		return additionSeq;
	}

	public void setAdditionSeq(String additionSeq) {
		this.additionSeq = additionSeq;
	}

	public String getAdditionNo() {
		return additionNo;
	}

	public void setAdditionNo(String additionNo) {
		this.additionNo = additionNo;
	}


	public String getAdditionName() {
		return additionName;
	}

	public void setAdditionName(String additionName) {
		this.additionName = additionName;
	}

	public String getPropertyNo() {
		return propertyNo;
	}

	public void setPropertyNo(String propertyNo) {
		this.propertyNo = propertyNo;
	}

	public BigDecimal getCoinsRate() {
		return coinsRate;
	}

	public void setCoinsRate(BigDecimal coinsRate) {
		this.coinsRate = coinsRate;
	}

	public BigDecimal getCoinsAmt() {
		return coinsAmt;
	}

	public void setCoinsAmt(BigDecimal coinsAmt) {
		this.coinsAmt = coinsAmt;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyEname() {
		return propertyEname;
	}

	public void setPropertyEname(String propertyEname) {
		this.propertyEname = propertyEname;
	}

	public Date getPolicyDbgn() {
		return policyDbgn;
	}

	public void setPolicyDbgn(Date policyDbgn) {
		this.policyDbgn = policyDbgn;
	}

	public Date getPolicyDend() {
		return policyDend;
	}

	public void setPolicyDend(Date policyDend) {
		this.policyDend = policyDend;
	}

	public BigDecimal getPrem() {
		return prem;
	}

	public void setPrem(BigDecimal prem) {
		this.prem = prem;
	}

	public BigDecimal getCommRate() {
		return commRate;
	}

	public void setCommRate(BigDecimal commRate) {
		this.commRate = commRate;
	}

	public BigDecimal getComm() {
		return comm;
	}

	public void setComm(BigDecimal comm) {
		this.comm = comm;
	}

	public BigDecimal getCoinsPrem() {
		return coinsPrem;
	}

	public void setCoinsPrem(BigDecimal coinsPrem) {
		this.coinsPrem = coinsPrem;
	}


	public String getIncludeAmt() {
		return includeAmt;
	}

	public void setIncludeAmt(String includeAmt) {
		this.includeAmt = includeAmt;
	}

	public String getIncludePrem() {
		return includePrem;
	}

	public void setIncludePrem(String includePrem) {
		this.includePrem = includePrem;
	}

	public BigDecimal getAmtTyp() {
		return amtTyp;
	}

	public void setAmtTyp(BigDecimal amtTyp) {
		this.amtTyp = amtTyp;
	}

	public BigDecimal getPremTyp() {
		return premTyp;
	}

	public void setPremTyp(BigDecimal premTyp) {
		this.premTyp = premTyp;
	}

	public BigDecimal getAmtEar() {
		return amtEar;
	}

	public void setAmtEar(BigDecimal amtEar) {
		this.amtEar = amtEar;
	}

	public BigDecimal getPremEar() {
		return premEar;
	}

	public void setPremEar(BigDecimal premEar) {
		this.premEar = premEar;
	}

	public String getAdditionEname() {
		return additionEname;
	}

	public void setAdditionEname(String additionEname) {
		this.additionEname = additionEname;
	}
	
	public Date getDutyDbgn() {
		return dutyDbgn;
	}

	public void setDutyDbgn(Date dutyDbgn) {
		this.dutyDbgn = dutyDbgn;
	}

	public Date getDutyDend() {
		return dutyDend;
	}

	public void setDutyDend(Date dutyDend) {
		this.dutyDend = dutyDend;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCessionNo() {
		return cessionNo;
	}

	public void setCessionNo(String cessionNo) {
		this.cessionNo = cessionNo;
	}


	
	
	
}

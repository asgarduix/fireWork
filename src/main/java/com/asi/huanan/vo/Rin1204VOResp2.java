package com.asi.huanan.vo;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1204執行自動分保，讀取待分保明細清單")
public class Rin1204VOResp2 {

	@ApiModelProperty(value = "保單列印日,(簽單日)")
	private Date policyDprt;

	@ApiModelProperty(value = "保單號")
	private String policyNo;

	@ApiModelProperty(value = "批單號")
	private String endorseNo;

	@ApiModelProperty(value = "保單類別")
	private String policyType;

	@ApiModelProperty(value = "累計總保額")
	private Long allAmt;

	@ApiModelProperty(value = "批加減異動公司分配保額")
	private Long amt;

	@ApiModelProperty(value = "共保比率")
	private BigDecimal coinsRate;

	@ApiModelProperty(value = "調整註記(附加險代號為0=’Y’)")
	private String changeFlag;

	@ApiModelProperty(value = "流動代號")
	private String ifloat;

	@ApiModelProperty(value = "續保單號")
	private String oldPolicy;

	@ApiModelProperty(value = "起保日期")
	private Date policyDbgn;

	@ApiModelProperty(value = "到期日期")
	private Date policyDend;

	@ApiModelProperty(value = "地址序號")
	private Short addrNo;

	@ApiModelProperty(value = "保單地址序號")
	private Short addrNoOri;

	@ApiModelProperty(value = "同險編號")
	private String riskNo;

	@ApiModelProperty(value = "異動總保額, 100%總保額=amt / coins_rate")
	private Long damt;

	@ApiModelProperty(value = "異動總保費")
	private Long prem;

	@ApiModelProperty(value = "標的物地址")
	private String propAddr;

	@ApiModelProperty(value = "郵遞區號")
	private String zipCode;

	@ApiModelProperty(value = "地段代碼")
	private String areaCode;

	@ApiModelProperty(value = "使用性質代號")
	private String usepropCode;

	@ApiModelProperty(value = "使用性質名稱")
	private String usepropName;

	@ApiModelProperty(value = "建築等級英文代號")
	private String constClass;

	@ApiModelProperty(value = "限額代號")
	private String limitNo;

	@ApiModelProperty(value = "限額")
	private Long limit;

	@ApiModelProperty(value = "流動貨物保額")
	private Long amtFlt;

	@ApiModelProperty(value = "流動貨物保費")
	private Long premFlt;

	@ApiModelProperty(value = "非流動貨物保額")
	private Long amtFix;

	@ApiModelProperty(value = "非流動貨物保額")
	private Long premFix;

	@ApiModelProperty(value = "颱洪本單總保額")
	private Long amtTyp;

	@ApiModelProperty(value = "颱洪本單年保費")
	private Long premTyp;

	@ApiModelProperty(value = "地震本單總保額")
	private Long amtEar;

	@ApiModelProperty(value = "地震本單年保費")
	private Long premEar;

	@ApiModelProperty(value = "同險註記,是否完成同險設定")
	private String riskFlag;

	@ApiModelProperty(value = "同險名稱")
	private String riskName;

	@ApiModelProperty(value = "限額比率")
	private BigDecimal limitRate;

	@ApiModelProperty(value = "保單限額")
	private Long limitOri;

	@ApiModelProperty(value = "保單註")
	private String endReason;

	@ApiModelProperty(value = "批改事由(若為 ‘R’ 表示整張單全退)")
	private String endReasonMain;

	@ApiModelProperty(value = "自動分保註記,是否自動分保")
	private String calcFlag;

	public Date getPolicyDprt() {
		return policyDprt;
	}

	public void setPolicyDprt(Date policyDprt) {
		this.policyDprt = policyDprt;
	}

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

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public Long getAllAmt() {
		return allAmt;
	}

	public void setAllAmt(Long allAmt) {
		this.allAmt = allAmt;
	}

	public Long getAmt() {
		return amt;
	}

	public void setAmt(Long amt) {
		this.amt = amt;
	}

	public BigDecimal getCoinsRate() {
		return coinsRate;
	}

	public void setCoinsRate(BigDecimal coinsRate) {
		this.coinsRate = coinsRate;
	}

	public String getChangeFlag() {
		return changeFlag;
	}

	public void setChangeFlag(String changeFlag) {
		this.changeFlag = changeFlag;
	}

	public String getIfloat() {
		return ifloat;
	}

	public void setIfloat(String ifloat) {
		this.ifloat = ifloat;
	}

	public String getOldPolicy() {
		return oldPolicy;
	}

	public void setOldPolicy(String oldPolicy) {
		this.oldPolicy = oldPolicy;
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

	public Short getAddrNo() {
		return addrNo;
	}

	public void setAddrNo(Short addrNo) {
		this.addrNo = addrNo;
	}

	public Short getAddrNoOri() {
		return addrNoOri;
	}

	public void setAddrNoOri(Short addrNoOri) {
		this.addrNoOri = addrNoOri;
	}

	public String getRiskNo() {
		return riskNo;
	}

	public void setRiskNo(String riskNo) {
		this.riskNo = riskNo;
	}

	public Long getDamt() {
		return damt;
	}

	public void setDamt(Long damt) {
		this.damt = damt;
	}

	public Long getPrem() {
		return prem;
	}

	public void setPrem(Long prem) {
		this.prem = prem;
	}

	public String getPropAddr() {
		return propAddr;
	}

	public void setPropAddr(String propAddr) {
		this.propAddr = propAddr;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getUsepropCode() {
		return usepropCode;
	}

	public void setUsepropCode(String usepropCode) {
		this.usepropCode = usepropCode;
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

	public String getLimitNo() {
		return limitNo;
	}

	public void setLimitNo(String limitNo) {
		this.limitNo = limitNo;
	}

	public Long getLimit() {
		return limit;
	}

	public void setLimit(Long limit) {
		this.limit = limit;
	}

	public Long getAmtFlt() {
		return amtFlt;
	}

	public void setAmtFlt(Long amtFlt) {
		this.amtFlt = amtFlt;
	}

	public Long getPremFlt() {
		return premFlt;
	}

	public void setPremFlt(Long premFlt) {
		this.premFlt = premFlt;
	}

	public Long getAmtFix() {
		return amtFix;
	}

	public void setAmtFix(Long amtFix) {
		this.amtFix = amtFix;
	}

	public Long getPremFix() {
		return premFix;
	}

	public void setPremFix(Long premFix) {
		this.premFix = premFix;
	}

	public Long getAmtTyp() {
		return amtTyp;
	}

	public void setAmtTyp(Long amtTyp) {
		this.amtTyp = amtTyp;
	}

	public Long getPremTyp() {
		return premTyp;
	}

	public void setPremTyp(Long premTyp) {
		this.premTyp = premTyp;
	}

	public Long getAmtEar() {
		return amtEar;
	}

	public void setAmtEar(Long amtEar) {
		this.amtEar = amtEar;
	}

	public Long getPremEar() {
		return premEar;
	}

	public void setPremEar(Long premEar) {
		this.premEar = premEar;
	}

	public String getRiskFlag() {
		return riskFlag;
	}

	public void setRiskFlag(String riskFlag) {
		this.riskFlag = riskFlag;
	}

	public String getRiskName() {
		return riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	public BigDecimal getLimitRate() {
		return limitRate;
	}

	public void setLimitRate(BigDecimal limitRate) {
		this.limitRate = limitRate;
	}

	public Long getLimitOri() {
		return limitOri;
	}

	public void setLimitOri(Long limitOri) {
		this.limitOri = limitOri;
	}

	public String getEndReason() {
		return endReason;
	}

	public void setEndReason(String endReason) {
		this.endReason = endReason;
	}

	public String getEndReasonMain() {
		return endReasonMain;
	}

	public void setEndReasonMain(String endReasonMain) {
		this.endReasonMain = endReasonMain;
	}

	public String getCalcFlag() {
		return calcFlag;
	}

	public void setCalcFlag(String calcFlag) {
		this.calcFlag = calcFlag;
	}

}
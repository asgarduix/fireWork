package com.asi.huanan.vo.rin1301.req;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Rin1301FacShareVoReq {
	
    private String policyNo;

    private String endorseNo;

    private Integer addrNo;
	
    private String riskNo;

	@JsonFormat(pattern="yyyy/MM/dd",timezone = "GMT+8")
    private Date dutyDbgn;
	@JsonFormat(pattern="yyyy/MM/dd",timezone = "GMT+8")
    private Date dutyDend;

    private Long amt;

    private Long prem;

    private Long amtTyp;

    private Long premTyp;

    private Long amtEar;

    private Long premEar;
    
    private String endReason;
    
    private BigDecimal coinsRate;

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

	public Integer getAddrNo() {
		return addrNo;
	}

	public void setAddrNo(Integer addrNo) {
		this.addrNo = addrNo;
	}

	public String getRiskNo() {
		return riskNo;
	}

	public void setRiskNo(String riskNo) {
		this.riskNo = riskNo;
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

	public Long getAmt() {
		return amt;
	}

	public void setAmt(Long amt) {
		this.amt = amt;
	}

	public Long getPrem() {
		return prem;
	}

	public void setPrem(Long prem) {
		this.prem = prem;
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

	public String getEndReason() {
		return endReason;
	}

	public void setEndReason(String endReason) {
		this.endReason = endReason;
	}

	public BigDecimal getCoinsRate() {
		return coinsRate;
	}

	public void setCoinsRate(BigDecimal coinsRate) {
		this.coinsRate = coinsRate;
	}
	
	
	
}

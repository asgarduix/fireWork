package com.asi.huanan.vo.rin1301.req;

import java.math.BigDecimal;

public class Rin1301FacRateVoReq {
	
	private String content;

	private Long amt;

    private Long prem;

    private Long facPrem;

    private BigDecimal premRate;

    private BigDecimal facPremRate;

    private BigDecimal commRate;

    private BigDecimal limitRate;

    private Long limit;

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

    public Long getFacPrem() {
        return facPrem;
    }

    public void setFacPrem(Long facPrem) {
        this.facPrem = facPrem;
    }

    public BigDecimal getPremRate() {
        return premRate;
    }

    public void setPremRate(BigDecimal premRate) {
        this.premRate = premRate;
    }

    public BigDecimal getFacPremRate() {
        return facPremRate;
    }

    public void setFacPremRate(BigDecimal facPremRate) {
        this.facPremRate = facPremRate;
    }

    public BigDecimal getCommRate() {
        return commRate;
    }

    public void setCommRate(BigDecimal commRate) {
        this.commRate = commRate;
    }

    public BigDecimal getLimitRate() {
        return limitRate;
    }

    public void setLimitRate(BigDecimal limitRate) {
        this.limitRate = limitRate;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}

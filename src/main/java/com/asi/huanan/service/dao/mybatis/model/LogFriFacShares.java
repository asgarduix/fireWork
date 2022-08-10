package com.asi.huanan.service.dao.mybatis.model;

import java.util.Date;

public class LogFriFacShares extends LogFriFacSharesKey {
    private String riskNo;

    private Date dutyDbgn;

    private Date dutyDend;

    private Long amt;

    private Long prem;

    private Long amtTyp;

    private Long premTyp;

    private Long amtEar;

    private Long premEar;

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
}
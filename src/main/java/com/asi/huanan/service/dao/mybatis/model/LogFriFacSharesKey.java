package com.asi.huanan.service.dao.mybatis.model;

public class LogFriFacSharesKey {
    private String slipNo;

    private String logSeq;

    private String policyNo;

    private String endorseNo;

    private Integer addrNo;

    public String getSlipNo() {
        return slipNo;
    }

    public void setSlipNo(String slipNo) {
        this.slipNo = slipNo;
    }

    public String getLogSeq() {
        return logSeq;
    }

    public void setLogSeq(String logSeq) {
        this.logSeq = logSeq;
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

    public Integer getAddrNo() {
        return addrNo;
    }

    public void setAddrNo(Integer addrNo) {
        this.addrNo = addrNo;
    }
}
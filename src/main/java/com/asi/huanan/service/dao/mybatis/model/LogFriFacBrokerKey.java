package com.asi.huanan.service.dao.mybatis.model;

public class LogFriFacBrokerKey {
    private String slipNo;

    private String logSeq;

    private String brokerId;

    private String rinComId;

    private String facType;

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

    public String getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(String brokerId) {
        this.brokerId = brokerId;
    }

    public String getRinComId() {
        return rinComId;
    }

    public void setRinComId(String rinComId) {
        this.rinComId = rinComId;
    }

    public String getFacType() {
        return facType;
    }

    public void setFacType(String facType) {
        this.facType = facType;
    }
}
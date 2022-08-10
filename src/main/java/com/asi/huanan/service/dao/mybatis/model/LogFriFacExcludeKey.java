package com.asi.huanan.service.dao.mybatis.model;

public class LogFriFacExcludeKey {
    private String slipNo;

    private String logSeq;

    private String rinComId;

    private String content;

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

    public String getRinComId() {
        return rinComId;
    }

    public void setRinComId(String rinComId) {
        this.rinComId = rinComId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
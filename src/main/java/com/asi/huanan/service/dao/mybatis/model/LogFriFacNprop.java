package com.asi.huanan.service.dao.mybatis.model;

public class LogFriFacNprop extends LogFriFacNpropKey {
    private String propertyName;

    private Long amt;

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Long getAmt() {
        return amt;
    }

    public void setAmt(Long amt) {
        this.amt = amt;
    }
}
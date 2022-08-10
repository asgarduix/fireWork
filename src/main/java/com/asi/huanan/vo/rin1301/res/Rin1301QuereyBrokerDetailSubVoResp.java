package com.asi.huanan.vo.rin1301.res;

import java.math.BigDecimal;

public class Rin1301QuereyBrokerDetailSubVoResp {
	
	private String brokerId;
	
	private String facType;
	
	private String rinComId;
	
    private BigDecimal brkprem100;
    
    private BigDecimal shareRate;
    
    private BigDecimal brkcommRate;
    
    private BigDecimal brktaxRate;

    private Long brkexcessBgn;

    private Long brkexcessLimit;
    
    private BigDecimal brkripremNt;
    
    private BigDecimal brkricommNt;
    
    private BigDecimal brkripremOrg;
    
    private BigDecimal brkricommOrg;
    
    private BigDecimal brkritaxNt;

    private BigDecimal brkritaxOrg;

	public String getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}

	public String getFacType() {
		return facType;
	}

	public void setFacType(String facType) {
		this.facType = facType;
	}

	public String getRinComId() {
		return rinComId;
	}

	public void setRinComId(String rinComId) {
		this.rinComId = rinComId;
	}

	public BigDecimal getBrkprem100() {
		return brkprem100;
	}

	public void setBrkprem100(BigDecimal brkprem100) {
		this.brkprem100 = brkprem100;
	}

	public BigDecimal getShareRate() {
		return shareRate;
	}

	public void setShareRate(BigDecimal shareRate) {
		this.shareRate = shareRate;
	}

	public BigDecimal getBrkcommRate() {
		return brkcommRate;
	}

	public void setBrkcommRate(BigDecimal brkcommRate) {
		this.brkcommRate = brkcommRate;
	}

	public BigDecimal getBrktaxRate() {
		return brktaxRate;
	}

	public void setBrktaxRate(BigDecimal brktaxRate) {
		this.brktaxRate = brktaxRate;
	}

	public Long getBrkexcessBgn() {
		return brkexcessBgn;
	}

	public void setBrkexcessBgn(Long brkexcessBgn) {
		this.brkexcessBgn = brkexcessBgn;
	}

	public Long getBrkexcessLimit() {
		return brkexcessLimit;
	}

	public void setBrkexcessLimit(Long brkexcessLimit) {
		this.brkexcessLimit = brkexcessLimit;
	}

	public BigDecimal getBrkripremNt() {
		return brkripremNt;
	}

	public void setBrkripremNt(BigDecimal brkripremNt) {
		this.brkripremNt = brkripremNt;
	}

	public BigDecimal getBrkricommNt() {
		return brkricommNt;
	}

	public void setBrkricommNt(BigDecimal brkricommNt) {
		this.brkricommNt = brkricommNt;
	}

	public BigDecimal getBrkripremOrg() {
		return brkripremOrg;
	}

	public void setBrkripremOrg(BigDecimal brkripremOrg) {
		this.brkripremOrg = brkripremOrg;
	}

	public BigDecimal getBrkricommOrg() {
		return brkricommOrg;
	}

	public void setBrkricommOrg(BigDecimal brkricommOrg) {
		this.brkricommOrg = brkricommOrg;
	}

	public BigDecimal getBrkritaxNt() {
		return brkritaxNt;
	}

	public void setBrkritaxNt(BigDecimal brkritaxNt) {
		this.brkritaxNt = brkritaxNt;
	}

	public BigDecimal getBrkritaxOrg() {
		return brkritaxOrg;
	}

	public void setBrkritaxOrg(BigDecimal brkritaxOrg) {
		this.brkritaxOrg = brkritaxOrg;
	}

}

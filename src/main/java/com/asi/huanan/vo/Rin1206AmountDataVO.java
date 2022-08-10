package com.asi.huanan.vo;

import java.math.BigDecimal;

//rin1206查各項費率
public class Rin1206AmountDataVO {
	private String rin_com_id;
	
	private BigDecimal riprem;
	
	private BigDecimal comm;
	private BigDecimal businesstax;
	private BigDecimal stamptax;
	private BigDecimal handlingFee;
	private BigDecimal agent;
	private BigDecimal ripaid;
	public String getRin_com_id() {
		return rin_com_id;
	}
	public void setRin_com_id(String rin_com_id) {
		this.rin_com_id = rin_com_id;
	}
	public BigDecimal getRiprem() {
		return riprem;
	}
	public void setRiprem(BigDecimal riprem) {
		this.riprem = riprem;
	}
	public BigDecimal getComm() {
		return comm;
	}
	public void setComm(BigDecimal comm) {
		this.comm = comm;
	}
	public BigDecimal getBusinesstax() {
		return businesstax;
	}
	public void setBusinesstax(BigDecimal businesstax) {
		this.businesstax = businesstax;
	}
	public BigDecimal getStamptax() {
		return stamptax;
	}
	public void setStamptax(BigDecimal stamptax) {
		this.stamptax = stamptax;
	}
	public BigDecimal getHandlingFee() {
		return handlingFee;
	}
	public void setHandlingFee(BigDecimal handlingFee) {
		this.handlingFee = handlingFee;
	}
	public BigDecimal getAgent() {
		return agent;
	}
	public void setAgent(BigDecimal agent) {
		this.agent = agent;
	}
	public BigDecimal getRipaid() {
		return ripaid;
	}
	public void setRipaid(BigDecimal ripaid) {
		this.ripaid = ripaid;
	}
	
	
}

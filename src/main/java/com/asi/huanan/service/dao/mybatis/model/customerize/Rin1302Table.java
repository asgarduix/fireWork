package com.asi.huanan.service.dao.mybatis.model.customerize;

import java.math.BigDecimal;
import java.util.Date;

public class Rin1302Table {
	
	//fri_fac A
	private String cession_no;
	private String insurant;
	private String use_prop;
	private Date treaty_dend;
	private String address;
	
	//fri_fac_rincom B
	private BigDecimal share_rate;
	
	//fri_fac_policy C
	private String policy_no;
	
	//fri_policy E
	private BigDecimal all_amt;
	private BigDecimal coins_rate;
	private BigDecimal amt;
	
	//fri_com F
	private String ename;
	
	public String getCession_no() {
		return cession_no;
	}

	public void setCession_no(String cession_no) {
		this.cession_no = cession_no;
	}

	public String getInsurant() {
		return insurant;
	}

	public void setInsurant(String insurant) {
		this.insurant = insurant;
	}

	public String getUse_prop() {
		return use_prop;
	}

	public void setUse_prop(String use_prop) {
		this.use_prop = use_prop;
	}

	public Date getTreaty_dend() {
		return treaty_dend;
	}

	public void setTreaty_dend(Date treaty_dend) {
		this.treaty_dend = treaty_dend;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getShare_rate() {
		return share_rate;
	}

	public void setShare_rate(BigDecimal share_rate) {
		this.share_rate = share_rate;
	}

	public String getPolicy_no() {
		return policy_no;
	}

	public void setPolicy_no(String policy_no) {
		this.policy_no = policy_no;
	}

	public BigDecimal getAll_amt() {
		return all_amt;
	}

	public void setAll_amt(BigDecimal all_amt) {
		this.all_amt = all_amt;
	}

	public BigDecimal getCoins_rate() {
		return coins_rate;
	}

	public void setCoins_rate(BigDecimal coins_rate) {
		this.coins_rate = coins_rate;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

}

package com.asi.huanan.service.dao.mybatis.model.customerize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1101A再保人基本資料明細")
public class Rin1101FricomJoinRicmpf1 {
	
	//fri_com 
	@ApiModelProperty (value = "再保人代號")
	private String rinComId;		
	@ApiModelProperty (value = "再保人英文名稱")
	private String ename;			
	@ApiModelProperty (value = "再保人中文名稱")
	private String cname;			
	@ApiModelProperty (value = "註銷日")
	private String usemrk;			
	@ApiModelProperty (value = "國別")
	private String acctArea;		
	@ApiModelProperty (value = "業務往來型態-1")
	private String favtyp;			
	@ApiModelProperty (value = "業務往來型態-2")
	private String inout;			
	@ApiModelProperty (value = "備註(再保)")
	private String remark1;			
	@ApiModelProperty (value = "適格")
	private String shige;           
	@ApiModelProperty (value = "觀察名單")
	private String watchto;			
	@ApiModelProperty (value = "另案簽報")
	private String signnok;			
	@ApiModelProperty (value = "封鎖起始日")
	private String blocklst;		
	
	//ricmpf1
	@ApiModelProperty (value = "保發代號")
	private String ocode;			
	@ApiModelProperty (value = "車險備註")
	private String carmrk;			
	@ApiModelProperty (value = "水險")
	private String marmrk;			
	@ApiModelProperty (value = "火險")
	private String firmrk;			
	@ApiModelProperty (value = "意外險")
	private String accmrk;			
	@ApiModelProperty (value = "健康險")
	private String ahmrk;			
	
	
	public String getRinComId() {
		return rinComId;
	}
	public void setRinComId(String rinComId) {
		this.rinComId = rinComId;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getAcctArea() {
		return acctArea;
	}
	public void setAcctArea(String acctArea) {
		this.acctArea = acctArea;
	}
	public String getFavtyp() {
		return favtyp;
	}
	public void setFavtyp(String favtyp) {
		this.favtyp = favtyp;
	}
	public String getInout() {
		return inout;
	}
	public void setInout(String inout) {
		this.inout = inout;
	}
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	public String getOcode() {
		return ocode;
	}
	public void setOcode(String ocode) {
		this.ocode = ocode;
	}
	public String getCarmrk() {
		return carmrk;
	}
	public void setCarmrk(String carmrk) {
		this.carmrk = carmrk;
	}
	public String getMarmrk() {
		return marmrk;
	}
	public void setMarmrk(String marmrk) {
		this.marmrk = marmrk;
	}
	public String getFirmrk() {
		return firmrk;
	}
	public void setFirmrk(String firmrk) {
		this.firmrk = firmrk;
	}
	public String getAccmrk() {
		return accmrk;
	}
	public void setAccmrk(String accmrk) {
		this.accmrk = accmrk;
	}
	public String getAhmrk() {
		return ahmrk;
	}
	public void setAhmrk(String ahmrk) {
		this.ahmrk = ahmrk;
	}
	public String getUsemrk() {
		return usemrk;
	}
	public void setUsemrk(String usemrk) {
		this.usemrk = usemrk;
	}
	public String getShige() {
		return shige;
	}
	public void setShige(String shige) {
		this.shige = shige;
	}
	public String getWatchto() {
		return watchto;
	}
	public void setWatchto(String watchto) {
		this.watchto = watchto;
	}
	public String getSignnok() {
		return signnok;
	}
	public void setSignnok(String signnok) {
		this.signnok = signnok;
	}
	public String getBlocklst() {
		return blocklst;
	}
	public void setBlocklst(String blocklst) {
		this.blocklst = blocklst;
	}
	
	
	
	
	
	
	
	
}

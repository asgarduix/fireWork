package com.asi.huanan.vo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Rin1204執行自動分保，寫入合約分保檔(update)")
public class Rin1204VOReq1 {

	private BigDecimal msShareAmt;

	private BigDecimal msPlyShareAmt;

	private Long msDamt;

	private Long msDprem;

	private Long msDamtTyp;

	private Long msDpremTyp;

	private Long msDamtEar;

	private Long msDpremEar;

	private BigDecimal retainAmt;

	private BigDecimal retainPrem;

	private String msDpolicyNo;

	private String msDendorseNo;

	private Short msDaddrNo;

	private String treatyYear;

	private String msDriskNo;

	public BigDecimal getMsShareAmt() {
		return msShareAmt;
	}

	public void setMsShareAmt(BigDecimal msShareAmt) {
		this.msShareAmt = msShareAmt;
	}

	public BigDecimal getMsPlyShareAmt() {
		return msPlyShareAmt;
	}

	public void setMsPlyShareAmt(BigDecimal msPlyShareAmt) {
		this.msPlyShareAmt = msPlyShareAmt;
	}

	public Long getMsDamt() {
		return msDamt;
	}

	public void setMsDamt(Long msDamt) {
		this.msDamt = msDamt;
	}

	public Long getMsDprem() {
		return msDprem;
	}

	public void setMsDprem(Long msDprem) {
		this.msDprem = msDprem;
	}

	public Long getMsDamtTyp() {
		return msDamtTyp;
	}

	public void setMsDamtTyp(Long msDamtTyp) {
		this.msDamtTyp = msDamtTyp;
	}

	public Long getMsDpremTyp() {
		return msDpremTyp;
	}

	public void setMsDpremTyp(Long msDpremTyp) {
		this.msDpremTyp = msDpremTyp;
	}

	public Long getMsDamtEar() {
		return msDamtEar;
	}

	public void setMsDamtEar(Long msDamtEar) {
		this.msDamtEar = msDamtEar;
	}

	public Long getMsDpremEar() {
		return msDpremEar;
	}

	public void setMsDpremEar(Long msDpremEar) {
		this.msDpremEar = msDpremEar;
	}

	public BigDecimal getRetainAmt() {
		return retainAmt;
	}

	public void setRetainAmt(BigDecimal retainAmt) {
		this.retainAmt = retainAmt;
	}

	public BigDecimal getRetainPrem() {
		return retainPrem;
	}

	public void setRetainPrem(BigDecimal retainPrem) {
		this.retainPrem = retainPrem;
	}

	public String getMsDpolicyNo() {
		return msDpolicyNo;
	}

	public void setMsDpolicyNo(String msDpolicyNo) {
		this.msDpolicyNo = msDpolicyNo;
	}

	public String getMsDendorseNo() {
		return msDendorseNo;
	}

	public void setMsDendorseNo(String msDendorseNo) {
		this.msDendorseNo = msDendorseNo;
	}

	public Short getMsDaddrNo() {
		return msDaddrNo;
	}

	public void setMsDaddrNo(Short msDaddrNo) {
		this.msDaddrNo = msDaddrNo;
	}

	public String getTreatyYear() {
		return treatyYear;
	}

	public void setTreatyYear(String treatyYear) {
		this.treatyYear = treatyYear;
	}

	public String getMsDriskNo() {
		return msDriskNo;
	}

	public void setMsDriskNo(String msDriskNo) {
		this.msDriskNo = msDriskNo;
	}

}

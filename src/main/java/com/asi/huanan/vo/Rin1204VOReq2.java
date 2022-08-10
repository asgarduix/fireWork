package com.asi.huanan.vo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Rin1204執行自動分保，寫入合約分保檔(insert)")
public class Rin1204VOReq2 {

	private String msDpolicyNo;

	private String msDendorseNo;

	private Short msDaddrNo;

	private String msTreatyYear;

	private String msDriskNo;

	private String msPolicyDBGN;

	private String msPolicyDEND;

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

	private String now;

	private String msPolicyPRT;

	private String msCtreatyNo;

	private String msCacctType;

	private Long msCamtTsi;

	private Long msSharePrem;

	private String msCtreatyMode;

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

	public String getMsTreatyYear() {
		return msTreatyYear;
	}

	public void setMsTreatyYear(String msTreatyYear) {
		this.msTreatyYear = msTreatyYear;
	}

	public String getMsDriskNo() {
		return msDriskNo;
	}

	public void setMsDriskNo(String msDriskNo) {
		this.msDriskNo = msDriskNo;
	}

	public String getMsPolicyDBGN() {
		return msPolicyDBGN;
	}

	public void setMsPolicyDBGN(String msPolicyDBGN) {
		this.msPolicyDBGN = msPolicyDBGN;
	}

	public String getMsPolicyDEND() {
		return msPolicyDEND;
	}

	public void setMsPolicyDEND(String msPolicyDEND) {
		this.msPolicyDEND = msPolicyDEND;
	}

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

	public String getNow() {
		return now;
	}

	public void setNow(String now) {
		this.now = now;
	}

	public String getMsPolicyPRT() {
		return msPolicyPRT;
	}

	public void setMsPolicyPRT(String msPolicyPRT) {
		this.msPolicyPRT = msPolicyPRT;
	}

	public String getMsCtreatyNo() {
		return msCtreatyNo;
	}

	public void setMsCtreatyNo(String msCtreatyNo) {
		this.msCtreatyNo = msCtreatyNo;
	}

	public String getMsCacctType() {
		return msCacctType;
	}

	public void setMsCacctType(String msCacctType) {
		this.msCacctType = msCacctType;
	}

	public Long getMsCamtTsi() {
		return msCamtTsi;
	}

	public void setMsCamtTsi(Long msCamtTsi) {
		this.msCamtTsi = msCamtTsi;
	}

	public Long getMsSharePrem() {
		return msSharePrem;
	}

	public void setMsSharePrem(Long msSharePrem) {
		this.msSharePrem = msSharePrem;
	}

	public String getMsCtreatyMode() {
		return msCtreatyMode;
	}

	public void setMsCtreatyMode(String msCtreatyMode) {
		this.msCtreatyMode = msCtreatyMode;
	}

}

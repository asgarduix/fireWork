package com.asi.huanan.vo.rin1301.req;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


public class Rin1301FacRinserVoReq {

	private String rinComId;
	
	private String shareStatus;
	
	private BigDecimal shareRate;

	private Long cedeAmt;

	private BigDecimal commRate;

	private BigDecimal taxRate;

//	private BigDecimal handlingRate;
//
//	private BigDecimal brokerRate;
//
//	private BigDecimal discountRate;

	private Long cedePrem;

	private String refNo;

	@JsonFormat(pattern="yyyy/MM/dd",timezone = "GMT+8")
	private Date answerDate;

	@JsonFormat(pattern="yyyy/MM/dd",timezone = "GMT+8")
	private Date paidDate;

	@JsonFormat(pattern="yyyy/MM/dd",timezone = "GMT+8")
	private Date paidDateExpect;

	private String acctFlag;

//	private String billNoExternal;
//
//	private String transferStatus;
//
//	@JsonFormat(pattern="yyyy/MM/dd",timezone = "GMT+8")
//	private Date transferDate;
//
//	@JsonFormat(pattern="yyyy/MM/dd",timezone = "GMT+8")
//	private Date printDate;
//
//
//	@JsonFormat(pattern="yyyy/MM/dd",timezone = "GMT+8")
//	private Date lastupdatedate;


	private BigDecimal orgprem;

	private BigDecimal orgcomm;

	private BigDecimal orgtax;
	
	private String treatyDBgn ;
	
	private String treatyDEnd ;
	


	public String getRinComId() {
		return rinComId;
	}

	public void setRinComId(String rinComId) {
		this.rinComId = rinComId;
	}

	public String getShareStatus() {
		return shareStatus;
	}

	public void setShareStatus(String shareStatus) {
		this.shareStatus = shareStatus;
	}

	public BigDecimal getShareRate() {
		return shareRate;
	}

	public void setShareRate(BigDecimal shareRate) {
		this.shareRate = shareRate;
	}



	public BigDecimal getCommRate() {
		return commRate;
	}

	public void setCommRate(BigDecimal commRate) {
		this.commRate = commRate;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

//	public BigDecimal getHandlingRate() {
//		return handlingRate;
//	}
//
//	public void setHandlingRate(BigDecimal handlingRate) {
//		this.handlingRate = handlingRate;
//	}
//
//	public BigDecimal getBrokerRate() {
//		return brokerRate;
//	}
//
//	public void setBrokerRate(BigDecimal brokerRate) {
//		this.brokerRate = brokerRate;
//	}
//
//	public BigDecimal getDiscountRate() {
//		return discountRate;
//	}
//
//	public void setDiscountRate(BigDecimal discountRate) {
//		this.discountRate = discountRate;
//	}


	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getAcctFlag() {
		return acctFlag;
	}

	public void setAcctFlag(String acctFlag) {
		this.acctFlag = acctFlag;
	}

//	public String getBillNoExternal() {
//		return billNoExternal;
//	}
//
//	public void setBillNoExternal(String billNoExternal) {
//		this.billNoExternal = billNoExternal;
//	}
//
//	public String getTransferStatus() {
//		return transferStatus;
//	}
//
//	public void setTransferStatus(String transferStatus) {
//		this.transferStatus = transferStatus;
//	}
//
//	
//
//	public String getRinComIdOld() {
//		return rinComIdOld;
//	}
//
//	public void setRinComIdOld(String rinComIdOld) {
//		this.rinComIdOld = rinComIdOld;
//	}

	public BigDecimal getOrgprem() {
		return orgprem;
	}

	public void setOrgprem(BigDecimal orgprem) {
		this.orgprem = orgprem;
	}

	public BigDecimal getOrgcomm() {
		return orgcomm;
	}

	public void setOrgcomm(BigDecimal orgcomm) {
		this.orgcomm = orgcomm;
	}

	public BigDecimal getOrgtax() {
		return orgtax;
	}

	public void setOrgtax(BigDecimal orgtax) {
		this.orgtax = orgtax;
	}



	public Date getAnswerDate() {
		return answerDate;
	}

	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

//	public Date getTransferDate() {
//		return transferDate;
//	}
//
//	public void setTransferDate(Date transferDate) {
//		this.transferDate = transferDate;
//	}
//
//	public Date getPrintDate() {
//		return printDate;
//	}
//
//	public void setPrintDate(Date printDate) {
//		this.printDate = printDate;
//	}

	public Date getPaidDateExpect() {
		return paidDateExpect;
	}

	public void setPaidDateExpect(Date paidDateExpect) {
		this.paidDateExpect = paidDateExpect;
	}

//	public Date getLastupdatedate() {
//		return lastupdatedate;
//	}
//
//	public void setLastupdatedate(Date lastupdatedate) {
//		this.lastupdatedate = lastupdatedate;
//	}

//	public Date getCloseDate() {
//		return closeDate;
//	}
//
//	public void setCloseDate(Date closeDate) {
//		this.closeDate = closeDate;
//	}

	public Long getCedeAmt() {
		return cedeAmt;
	}

	public void setCedeAmt(Long cedeAmt) {
		this.cedeAmt = cedeAmt;
	}

	public Long getCedePrem() {
		return cedePrem;
	}

	public void setCedePrem(Long cedePrem) {
		this.cedePrem = cedePrem;
	}

	public String getTreatyDBgn() {
		return treatyDBgn;
	}

	public void setTreatyDBgn(String treatyDBgn) {
		this.treatyDBgn = treatyDBgn;
	}

	public String getTreatyDEnd() {
		return treatyDEnd;
	}

	public void setTreatyDEnd(String treatyDEnd) {
		this.treatyDEnd = treatyDEnd;
	}

//	public String getRinserEname() {
//		return rinserEname;
//	}
//
//	public void setRinserEname(String rinserEname) {
//		this.rinserEname = rinserEname;
//	}



	

}

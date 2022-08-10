package com.asi.huanan.vo;

import java.util.List;

import com.asi.huanan.service.dao.mybatis.model.FriAccounting;
import com.asi.huanan.service.dao.mybatis.model.FriTreatyBroker;
import com.asi.huanan.service.dao.mybatis.model.Rin120602;
import com.asi.huanan.service.dao.mybatis.model.Rin120603;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1206 printData return")
public class Rin1206PrintDataVO {
	
	@ApiModelProperty (value = "列印號碼")
	private String rptId;
	
	@ApiModelProperty (value = "Rin1206總表明細")
	private List<Rin1206PrintModelVO> contractDetailList;
	
	@ApiModelProperty (value = "Rin120602總表")
	private List<Rin120602> rin120602List;
	
	@ApiModelProperty (value = "Rin1206再保人")
	private List<Rin120603> rin120603List;
	
	@ApiModelProperty (value = "帳單號碼清單")
	private List<String> billNoList;
	
	@ApiModelProperty (value = "帳單檔列印資料")
	List<FriAccounting> accountingList;
	
	@ApiModelProperty (value = "帳單檔列印資料")
	List<FriTreatyBroker> brokerList;

	public String getRptId() {
		return rptId;
	}

	public void setRptId(String rptId) {
		this.rptId = rptId;
	}

	public List<Rin1206PrintModelVO> getContractDetailList() {
		return contractDetailList;
	}

	public void setContractDetailList(List<Rin1206PrintModelVO> contractDetailList) {
		this.contractDetailList = contractDetailList;
	}

	public List<Rin120602> getRin120602List() {
		return rin120602List;
	}

	public void setRin120602List(List<Rin120602> rin120602List) {
		this.rin120602List = rin120602List;
	}

	public List<Rin120603> getRin120603List() {
		return rin120603List;
	}

	public void setRin120603List(List<Rin120603> rin120603List) {
		this.rin120603List = rin120603List;
	}

	public List<String> getBillNoList() {
		return billNoList;
	}

	public void setBillNoList(List<String> billNoList) {
		this.billNoList = billNoList;
	}

	public List<FriAccounting> getAccountingList() {
		return accountingList;
	}

	public void setAccountingList(List<FriAccounting> accountingList) {
		this.accountingList = accountingList;
	}

	public List<FriTreatyBroker> getBrokerList() {
		return brokerList;
	}

	public void setBrokerList(List<FriTreatyBroker> brokerList) {
		this.brokerList = brokerList;
	}

	
	
}

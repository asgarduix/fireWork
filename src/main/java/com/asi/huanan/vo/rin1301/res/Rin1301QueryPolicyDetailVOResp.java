package com.asi.huanan.vo.rin1301.res;

import java.util.List;

import com.asi.huanan.vo.rin1301.Rin1301SameRiskPolEndorseVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1301A頁面資料")
public class Rin1301QueryPolicyDetailVOResp {

	@ApiModelProperty(value = "保單基本資料頁籤明細主檔資料")
	Rin1301RinMainDataVoResp  mainData;
	
	@ApiModelProperty(value = "同險代號及查詢保批單清單資料")
	Rin1301SameRiskPolEndorseVo  sameRiskPolEndorseData;
	
	@ApiModelProperty(value = "保單基本資料頁籤明細表格1")
	List<Rin1301QueryPolicyDetailSubVOResp>  lsvPolicy1;
	
	@ApiModelProperty(value = "保單基本資料頁籤明細表格2")
	List<Rin1301QueryPolicyDetailSubVOResp>  lsvPolicy2;
	
	@ApiModelProperty(value = "保單基本資料頁籤明細表格3")
	List<Rin1301QueryPolicyDetailSubVOResp>  lsvPolicy3;
	
	@ApiModelProperty(value = "險種保額保費頁籤明細表格4")
	List<Rin1301QueryPolicyDetailSubVOResp>  lsvPolicy4;
	
	@ApiModelProperty(value = "險種保額保費頁籤代碼加總明細表格")
	List<Rin1301QueryPolicyDetailSubVOResp>  lsvPolicy6;
	
	@ApiModelProperty(value = "險種保額保費頁籤險種加總明細表格")
	List<Rin1301QueryPolicyDetailSubVOResp>  lsvPolicy7;
	
	@ApiModelProperty(value = "分保計算及說明頁籤明細計算前資料")
	List<Rin1301QueryPolicyDetailSubVOResp>  lsvPolicy10;
	
	@ApiModelProperty(value = "分保計算及說明頁籤明細計算後資料")
	List<Rin1301QueryPolicyDetailSubVOResp>  facSharesDetail;
	
	@ApiModelProperty(value = "再保人資料頁籤再保人明細")
	List<Rin1301QueryRinserDetailSubVoResp>  rinserDetail;
	
	@ApiModelProperty(value = "再保人資料頁籤經紀人明細")
	List<Rin1301QuereyBrokerDetailSubVoResp>  brokerDetail;
	
	@ApiModelProperty(value = "同險代號明細")
	List<String>  slipRiskDetail;

	

	public List<Rin1301QueryPolicyDetailSubVOResp> getLsvPolicy1() {
		return lsvPolicy1;
	}

	public void setLsvPolicy1(List<Rin1301QueryPolicyDetailSubVOResp> lsvPolicy1) {
		this.lsvPolicy1 = lsvPolicy1;
	}

	public List<Rin1301QueryPolicyDetailSubVOResp> getLsvPolicy2() {
		return lsvPolicy2;
	}

	public void setLsvPolicy2(List<Rin1301QueryPolicyDetailSubVOResp> lsvPolicy2) {
		this.lsvPolicy2 = lsvPolicy2;
	}

	public List<Rin1301QueryPolicyDetailSubVOResp> getLsvPolicy3() {
		return lsvPolicy3;
	}

	public void setLsvPolicy3(List<Rin1301QueryPolicyDetailSubVOResp> lsvPolicy3) {
		this.lsvPolicy3 = lsvPolicy3;
	}

	public List<Rin1301QueryPolicyDetailSubVOResp> getLsvPolicy4() {
		return lsvPolicy4;
	}

	public void setLsvPolicy4(List<Rin1301QueryPolicyDetailSubVOResp> lsvPolicy4) {
		this.lsvPolicy4 = lsvPolicy4;
	}

	public List<Rin1301QueryPolicyDetailSubVOResp> getLsvPolicy6() {
		return lsvPolicy6;
	}

	public void setLsvPolicy6(List<Rin1301QueryPolicyDetailSubVOResp> lsvPolicy6) {
		this.lsvPolicy6 = lsvPolicy6;
	}

	public List<Rin1301QueryPolicyDetailSubVOResp> getLsvPolicy7() {
		return lsvPolicy7;
	}

	public void setLsvPolicy7(List<Rin1301QueryPolicyDetailSubVOResp> lsvPolicy7) {
		this.lsvPolicy7 = lsvPolicy7;
	}

	public List<Rin1301QueryPolicyDetailSubVOResp> getLsvPolicy10() {
		return lsvPolicy10;
	}

	public void setLsvPolicy10(List<Rin1301QueryPolicyDetailSubVOResp> lsvPolicy10) {
		this.lsvPolicy10 = lsvPolicy10;
	}

	public List<Rin1301QueryRinserDetailSubVoResp> getRinserDetail() {
		return rinserDetail;
	}

	public void setRinserDetail(List<Rin1301QueryRinserDetailSubVoResp> rinserDetail) {
		this.rinserDetail = rinserDetail;
	}

	public List<Rin1301QuereyBrokerDetailSubVoResp> getBrokerDetail() {
		return brokerDetail;
	}

	public void setBrokerDetail(List<Rin1301QuereyBrokerDetailSubVoResp> brokerDetail) {
		this.brokerDetail = brokerDetail;
	}

	public Rin1301RinMainDataVoResp getMainData() {
		return mainData;
	}

	public void setMainData(Rin1301RinMainDataVoResp mainData) {
		this.mainData = mainData;
	}

	public Rin1301SameRiskPolEndorseVo getSameRiskPolEndorseData() {
		return sameRiskPolEndorseData;
	}

	public void setSameRiskPolEndorseData(Rin1301SameRiskPolEndorseVo sameRiskPolEndorseData) {
		this.sameRiskPolEndorseData = sameRiskPolEndorseData;
	}

	public List<Rin1301QueryPolicyDetailSubVOResp> getFacSharesDetail() {
		return facSharesDetail;
	}

	public void setFacSharesDetail(List<Rin1301QueryPolicyDetailSubVOResp> facSharesDetail) {
		this.facSharesDetail = facSharesDetail;
	}

	public List<String> getSlipRiskDetail() {
		return slipRiskDetail;
	}

	public void setSlipRiskDetail(List<String> slipRiskDetail) {
		this.slipRiskDetail = slipRiskDetail;
	}

	
	
	
	
}

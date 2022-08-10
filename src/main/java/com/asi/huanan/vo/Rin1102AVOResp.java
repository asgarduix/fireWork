package com.asi.huanan.vo;

import java.util.List;

import com.asi.huanan.service.dao.mybatis.model.FriTreaty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Rin1102A頁面所有欄位")
public class Rin1102AVOResp {

	@ApiModelProperty (value = "再保合約資料(main)")
	private FriTreaty friTreaty;		
	@ApiModelProperty (value = "合約再保人資料")
	private List<Rin1102ARincomVOResp> friTreatyRincomList;		
	@ApiModelProperty (value = "合約經紀人資料")
	private List<Rin1102ABrokerVOResp> friTreatyBrokerList;
	public FriTreaty getFriTreaty() {
		return friTreaty;
	}
	public void setFriTreaty(FriTreaty friTreaty) {
		this.friTreaty = friTreaty;
	}
	public List<Rin1102ARincomVOResp> getFriTreatyRincomList() {
		return friTreatyRincomList;
	}
	public void setFriTreatyRincomList(List<Rin1102ARincomVOResp> friTreatyRincomList) {
		this.friTreatyRincomList = friTreatyRincomList;
	}
	public List<Rin1102ABrokerVOResp> getFriTreatyBrokerList() {
		return friTreatyBrokerList;
	}
	public void setFriTreatyBrokerList(List<Rin1102ABrokerVOResp> friTreatyBrokerList) {
		this.friTreatyBrokerList = friTreatyBrokerList;
	}		

	
	
	
}

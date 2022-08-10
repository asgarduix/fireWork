package com.asi.huanan.vo;

import io.swagger.annotations.ApiModelProperty;

public class Rin1104Vo {
	
	@ApiModelProperty (value = "使用性質代碼")
	private String usePropId;
	@ApiModelProperty (value = "特一限額代號")
	private String special1Limit;
	@ApiModelProperty (value = "特二限額代號")
	private String special2Limit;
	@ApiModelProperty (value = "頭等限額代號")
	private String firstLimit;
	@ApiModelProperty (value = "二等限額代號")
	private String secondLimit;
	@ApiModelProperty (value = "三等限額代號")
	private String thirdLimit;
	@ApiModelProperty (value = "露天限額代號")
	private String outsideLimit;
	
	public String getUsePropId() {
		return usePropId;
	}
	public void setUsePropId(String usePropId) {
		this.usePropId = usePropId;
	}
	public String getSpecial1Limit() {
		return special1Limit;
	}
	public void setSpecial1Limit(String special1Limit) {
		this.special1Limit = special1Limit;
	}
	public String getSpecial2Limit() {
		return special2Limit;
	}
	public void setSpecial2Limit(String special2Limit) {
		this.special2Limit = special2Limit;
	}
	public String getFirstLimit() {
		return firstLimit;
	}
	public void setFirstLimit(String firstLimit) {
		this.firstLimit = firstLimit;
	}
	public String getSecondLimit() {
		return secondLimit;
	}
	public void setSecondLimit(String secondLimit) {
		this.secondLimit = secondLimit;
	}
	public String getThirdLimit() {
		return thirdLimit;
	}
	public void setThirdLimit(String thirdLimit) {
		this.thirdLimit = thirdLimit;
	}
	public String getOutsideLimit() {
		return outsideLimit;
	}
	public void setOutsideLimit(String outsideLimit) {
		this.outsideLimit = outsideLimit;
	}
}

package com.asi.huanan.vo;

import java.util.List;

/**
 * 
 * @author leo_lee
 *
 */
public class SampleVo {
	private String email;
	private String foo;
	private List<SampleVoChild> sampleRequestChildList;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFoo() {
		return foo;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}

	public List<SampleVoChild> getSampleRequestChildList() {
		return sampleRequestChildList;
	}

	public void setSampleRequestChildList(List<SampleVoChild> sampleRequestChildList) {
		this.sampleRequestChildList = sampleRequestChildList;
	}

}

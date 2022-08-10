package com.asi.mechanism.controller.vo;

import java.util.List;

/**
 * 
 * @author leo_lee
 *
 */
public class Func1Method1ReqVo {
	private String email;
	private String foo;
	private List<Func1Method1RespVoSub> sampleRequestChildList;

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

	public List<Func1Method1RespVoSub> getSampleRequestChildList() {
		return sampleRequestChildList;
	}

	public void setSampleRequestChildList(List<Func1Method1RespVoSub> sampleRequestChildList) {
		this.sampleRequestChildList = sampleRequestChildList;
	}

}

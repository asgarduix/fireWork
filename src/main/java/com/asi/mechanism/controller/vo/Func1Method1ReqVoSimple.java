package com.asi.mechanism.controller.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author leo_lee
 *
 */
public class Func1Method1ReqVoSimple {
	@ApiModelProperty(notes = "電子郵件",example = "foo@bar.org")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

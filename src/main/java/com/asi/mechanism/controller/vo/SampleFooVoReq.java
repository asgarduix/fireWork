package com.asi.mechanism.controller.vo;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class SampleFooVoReq {

	@Max(value = 100L)
	private Long num2;

	@Min(value = 100L)
	private Long num;

	@NotNull
	@NotEmpty
	@Length(min = 6, max = 20)
	private String foo;

	@NotNull
	@NotEmpty
	@NotBlank
//	@NotNull(message = "test - must not null")
//	@NotEmpty(message = "test - must not empty")
	private String bar;

//	@NotEmpty
//	@NotNull
	@Email
//	@NotBlank
	private String email;

	@Valid
	private SampleFooVoReq sub;

	public SampleFooVoReq getSub() {
		return sub;
	}

	public void setSub(SampleFooVoReq sub) {
		this.sub = sub;
	}

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

	public String getBar() {
		return bar;
	}

	public void setBar(String bar) {
		this.bar = bar;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public Long getNum2() {
		return num2;
	}

	public void setNum2(Long num2) {
		this.num2 = num2;
	}

}

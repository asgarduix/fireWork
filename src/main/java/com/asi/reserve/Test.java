package com.asi.reserve;

import com.asi.swissknife.EncryptAES;

public class Test {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Test test = new Test();
		test.foo();
	}

	/**
	 * 
	 */
	public void foo() {
		System.out.println(EncryptAES.encrypt("123456"));
	}

}

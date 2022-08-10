package com.asi.mechanism.controller.sysgrant.bean;

/**
 * lambda中處理為map的緩衝物件
 * 
 * @author biruh
 *
 */
public class MapBuffer {
	private String key;
	private Object val;// 使用物件，使用者因原程式中可以自行決定(知道)型態，使用強制轉型不會有其他問題

	public MapBuffer(String key, Object val) {
		this.key = key;
		this.val = val;
	}

	public String getKey() {
		return key;
	}

	public Object getVal() {
		return val;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setVal(Object val) {
		this.val = val;
	}

}
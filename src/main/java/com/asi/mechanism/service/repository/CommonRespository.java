package com.asi.mechanism.service.repository;

public class CommonRespository {
	public static boolean checkNotNull(Object checkData) {
		boolean rusult = false;

		if (checkData != null && !"".equals(checkData)) {
			rusult = true;
		}
		return rusult;
	}
}

package com.asi.mechanism.directory;

public enum DBConnectionEnum {
	/**
	 * 連線db
	 */
	DBConnection(true);

	private final boolean isStatus;

	DBConnectionEnum(boolean isStatus) {
		this.isStatus = isStatus;
	}

	public boolean isStatus() {
		return this.isStatus;
	}

}

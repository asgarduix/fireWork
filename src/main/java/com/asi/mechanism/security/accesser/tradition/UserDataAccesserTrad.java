package com.asi.mechanism.security.accesser.tradition;

import com.asi.mechanism.security.accesser.model.UserDataOpposite;

public interface UserDataAccesserTrad {

	public boolean permission(String username, String password);

	public UserDataOpposite permissionType2(String username, String password);

	public boolean checkIsAccExist(String username);

}

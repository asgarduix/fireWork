package com.asi.mechanism.security.accesser;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;

import com.asi.mechanism.security.accesser.model.UserData;
import com.asi.mechanism.service.SysAccountService;
import com.asi.mechanism.service.dao.mybatis.model.SysAccount;

public class UserDataAccesserImp implements UserDataAccesser {

	private static Logger log = LogManager.getLogger(UserDataAccesserImp.class);

	@Autowired
	private SysAccountService asiAccountService;

	@Override
	public List<UserData> fetchUserData(String akaid) throws Exception {
		log.debug(">>>here is impl<<<");

		SysAccount sysAccount = new SysAccount();
		sysAccount.setAkaId(akaid);
		List<SysAccount> usermanageList = this.asiAccountService.queryBySysAccount(sysAccount);
		log.debug("user_name:" + akaid + ", " + "size:" + usermanageList.size());

		if (usermanageList == null || usermanageList.size() == 0) {
			throw new BadCredentialsException("Bad credentials");
		}

		return usermanageList.stream().filter(tmp -> {
			if (StringUtils.isBlank(tmp.getUserName()) == true || StringUtils.isBlank(tmp.getCipher()) == true) {
				log.debug("account datas have error!");
				return false;
			}

			return true;
		}).map(tmp -> {
			UserData userData = new UserData();
			userData.setUserId(tmp.getAkaId());
			userData.setCipher(tmp.getCipher());
			return userData;
		}).collect(Collectors.toList());
	}
}

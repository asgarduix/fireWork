package com.asi.mechanism.security.accesser.tradition;

import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.asi.mechanism.security.accesser.model.UserDataOpposite;
import com.asi.mechanism.service.SysAccountService;
import com.asi.mechanism.service.dao.mybatis.model.SysAccount;
import com.asi.swissknife.Asiutil;
import com.google.gson.Gson;

/**
 * 僅class僅供Spring security使用
 * 
 * @author biruh
 *
 */
@Component
public class UserDataAccesserTradImp implements UserDataAccesserTrad {

	private static Logger log = LogManager.getLogger(UserDataAccesserTradImp.class);

	@Autowired
	private SysAccountService sysAccountService;

	@Value("${authentication.tradition.verify.type}")
	private String authenticationTraditionVerifyType;

	/**
	 * 
	 */
	@Override
	public boolean checkIsAccExist(String username) {
		try {
			SysAccount sysAccount = new SysAccount();
			sysAccount.setAkaId(username);
			List<SysAccount> accList = sysAccountService.queryBySysAccount(sysAccount);

			if (accList != null && accList.size() > 0) {
				return true;
			} else {
				log.debug("account:" + username + " have no data");
				return false;
			}
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
			return false;
		}
	}

	/**
	 * 
	 */
	@Override
	public boolean permission(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 
	 */
	@Override
	public UserDataOpposite permissionType2(String username, String cipher) {
		UserDataOpposite userDataJwt = new UserDataOpposite();

		try {
			log.debug("permission with tradition");
			// 因使用傳統方式，故這邊必須要decode passwd
			// String password = EncryptAES.decrypt(password);

			// 傳統用資料庫驗證
			if ("db_access".equals(this.authenticationTraditionVerifyType)) {
				Asiutil util = new Asiutil();
				String encodePasswd = util.encrypt(cipher, util.genalgstr());

				SysAccount sysAccount = new SysAccount();
				sysAccount.setAkaId(username);
				sysAccount.setCipher(encodePasswd);

				List<SysAccount> accountList = sysAccountService.queryBySysAccount(sysAccount);

				if (accountList == null) {
					log.debug("access user:" + username + " " + "have serious error! data size: 0");
					userDataJwt.setBooAuthen(false);
					return userDataJwt;
				}

				if (accountList != null && accountList.size() != 1) {
					log.debug("access user:" + username + " " + "have serious error! data size:" + accountList.size());
					userDataJwt.setBooAuthen(false);
					return userDataJwt;
				}

				userDataJwt.setBooAuthen(true);
				return userDataJwt;
			}

			// 串接驗證主機
			boolean isok = false;
			String authenType = this.authenticationTraditionVerifyType;
			String oppositeOriginData = null;

			switch (authenType) {
			case "ldap":
				log.debug("permission with tradition - ldap");
				// isok = ldapAuthen.permission(username, password);
				break;
			case "webservice":
				// 專案無使用webservice，不實作相關程式碼
				log.debug("permission with tradition - webservice");
				break;
			}

			userDataJwt.setBooAuthen(isok);
			userDataJwt.setOppositeOriginData(oppositeOriginData);
			return userDataJwt;
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
		}

		// 上方程式如果有任何執行錯誤，則一律回傳false
		userDataJwt.setBooAuthen(false);
		return userDataJwt;
	}

	/**
	 * 供串接其他驗證主機後需要的程序(新增資料、角色、基本權限等資料給該帳號
	 * 
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public void accessAuthenWithSysTable(String userName, String password) throws Exception {
		SysAccount sysAccount = new SysAccount();
		sysAccount.setUserName(userName);

		List<SysAccount> accountList = sysAccountService.queryBySysAccount(sysAccount);
		if (accountList != null && accountList.size() > 0) {
			log.debug("帳號已存在：" + userName);
			return;
		}

		sysAccount.setAkaId(userName);
		sysAccount.setUserName(userName);

		// Date entrydate = new Date();
		// sysAccount.setPassword(password);
		// sysAccount.setCrtDate(entrydate);
		// sysAccount.setUserRole(this.getDefaultRole());
		// sysAccount.setAreaid(areaid);
		// sysAccount.setCrtAkaId(userName);
		// sysAccount.setDeptid(deptid);
		// sysAccount.setEnableMark(enableMark);
		// sysAccount.setEntryDate(entrydate);
		// sysAccount.setEntryAkaId(userName);
		// sysAccount.setError(error);
		// sysAccount.setMail(mail);
		// sysAccount.setStatus(status);

		int insertVal = sysAccountService.insert(sysAccount);

		if (insertVal != 1) {
			throw new Exception("create account have serious error!");
		}

		log.debug("帳號-" + userName + "已新增");
	}

//	/**
//	 * 
//	 * @return
//	 * @throws Exception
//	 */
//	public String getDefaultRole() throws Exception {
//
//		SysRole sysRole = new SysRole();
//		sysRole.setDefRoleFlg("Y");
//		List<SysRole> roleList = sysRoleService.queryBySysRole(sysRole);
//		// 資料庫應只有1筆
//		if (roleList == null || roleList.size() == 0) {
//			throw new Exception("資料庫未設定預設Role");
//		}
//
//		String userRole = roleList.get(0).getUserRole();
//
//		return userRole;
//	}

	/**
	 * 
	 * @author biruh
	 *
	 */
	public class Token {
		private String access_token;
		private String expires_in;
		private String refresh_expires_in;
		private String refresh_token;
		private String token_type;
		private String not_before_policy;
		private String session_state;
		private String scope;

		public String getAccess_token() {
			return access_token;
		}

		public void setAccess_token(String access_token) {
			this.access_token = access_token;
		}

		public String getExpires_in() {
			return expires_in;
		}

		public void setExpires_in(String expires_in) {
			this.expires_in = expires_in;
		}

		public String getRefresh_expires_in() {
			return refresh_expires_in;
		}

		public void setRefresh_expires_in(String refresh_expires_in) {
			this.refresh_expires_in = refresh_expires_in;
		}

		public String getRefresh_token() {
			return refresh_token;
		}

		public void setRefresh_token(String refresh_token) {
			this.refresh_token = refresh_token;
		}

		public String getToken_type() {
			return token_type;
		}

		public void setToken_type(String token_type) {
			this.token_type = token_type;
		}

		public String getNot_before_policy() {
			return not_before_policy;
		}

		public void setNot_before_policy(String not_before_policy) {
			this.not_before_policy = not_before_policy;
		}

		public String getSession_state() {
			return session_state;
		}

		public void setSession_state(String session_state) {
			this.session_state = session_state;
		}

		public String getScope() {
			return scope;
		}

		public void setScope(String scope) {
			this.scope = scope;
		}

	}

	/**
	 *
	 */
	public class Chunks2 {
		private String exp;
		private String iat;
		private String jti;
		private String iss;
		private String aud;
		private String sub;
		private String typ;
		private String azp;
		private String session_state;
		private String acr;
		private Sub1 realm_access;
		private Sub2 resource_access;
		private String scope;
		private String sid;
		private String email_verified;
		private String name;
		private String preferred_username;
		private String given_name;
		private String family_name;

		public String getExp() {
			return exp;
		}

		public void setExp(String exp) {
			this.exp = exp;
		}

		public String getIat() {
			return iat;
		}

		public void setIat(String iat) {
			this.iat = iat;
		}

		public String getJti() {
			return jti;
		}

		public void setJti(String jti) {
			this.jti = jti;
		}

		public String getIss() {
			return iss;
		}

		public void setIss(String iss) {
			this.iss = iss;
		}

		public String getAud() {
			return aud;
		}

		public void setAud(String aud) {
			this.aud = aud;
		}

		public String getSub() {
			return sub;
		}

		public void setSub(String sub) {
			this.sub = sub;
		}

		public String getTyp() {
			return typ;
		}

		public void setTyp(String typ) {
			this.typ = typ;
		}

		public String getAzp() {
			return azp;
		}

		public void setAzp(String azp) {
			this.azp = azp;
		}

		public String getSession_state() {
			return session_state;
		}

		public void setSession_state(String session_state) {
			this.session_state = session_state;
		}

		public String getAcr() {
			return acr;
		}

		public void setAcr(String acr) {
			this.acr = acr;
		}

		public Sub1 getRealm_access() {
			return realm_access;
		}

		public void setRealm_access(Sub1 realm_access) {
			this.realm_access = realm_access;
		}

		public Sub2 getResource_access() {
			return resource_access;
		}

		public void setResource_access(Sub2 resource_access) {
			this.resource_access = resource_access;
		}

		public String getScope() {
			return scope;
		}

		public void setScope(String scope) {
			this.scope = scope;
		}

		public String getSid() {
			return sid;
		}

		public void setSid(String sid) {
			this.sid = sid;
		}

		public String getEmail_verified() {
			return email_verified;
		}

		public void setEmail_verified(String email_verified) {
			this.email_verified = email_verified;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPreferred_username() {
			return preferred_username;
		}

		public void setPreferred_username(String preferred_username) {
			this.preferred_username = preferred_username;
		}

		public String getGiven_name() {
			return given_name;
		}

		public void setGiven_name(String given_name) {
			this.given_name = given_name;
		}

		public String getFamily_name() {
			return family_name;
		}

		public void setFamily_name(String family_name) {
			this.family_name = family_name;
		}

	}

	/**
	 * 
	 * @author biruh
	 *
	 */
	public class Sub1 {
		private List<String> roles;

		public List<String> getRoles() {
			return roles;
		}

		public void setRoles(List<String> roles) {
			this.roles = roles;
		}
	}

	/**
	 * 
	 * @author biruh
	 *
	 */
	public class Sub2 {
		public Sub1 getAccount() {
			return account;
		}

		public void setAccount(Sub1 account) {
			this.account = account;
		}

		private Sub1 account;
	}

}

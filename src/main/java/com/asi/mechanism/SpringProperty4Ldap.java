package com.asi.mechanism;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpringProperty4Ldap {
	/**
	 * retrieve parameters of spring boot from property file
	 */

	private static Logger log = LogManager.getLogger(SpringProperty4Ldap.class);

	private static String ldapurls;
	private static String ldapbasedn;
	private static String ldapfactory;

	public static String getLdapurls() {
		return ldapurls;
	}

	@Value("${ldap.urls}")
	public void setLdapurls(String ldapurls) {
		SpringProperty4Ldap.ldapurls = ldapurls;
	}

	public static String getLdapbasedn() {
		return ldapbasedn;
	}

	@Value("${ldap.base.dn}")
	public void setLdapbasedn(String ldapbasedn) {
		SpringProperty4Ldap.ldapbasedn = ldapbasedn;
	}

	public static String getLdapfactory() {
		return ldapfactory;
	}

	@Value("${ldap.factory}")
	public void setLdapfactory(String ldapfactory) {
		SpringProperty4Ldap.ldapfactory = ldapfactory;
	}

	/**
	 * 
	 */
	public SpringProperty4Ldap() {

	}

	@PostConstruct
	public void init() {
		Class<? extends SpringProperty4Ldap> clazz = this.getClass();

		List<String> props = Arrays.asList(clazz.getFields()).stream().map(filed -> {
			String name = filed.getName();
			String getMethod = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
			Object val = null;

			try {
				val = clazz.getMethod(getMethod).invoke(this);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				log.info(e.toString());
				Arrays.asList(e.getStackTrace()).stream().forEach(sub -> System.out.println(sub.toString()));
				return "name:" + "{have_exception}";
			}

			// 參數為字串資料，先轉字串即可
			return name + ":" + String.valueOf(val);
		}).collect(Collectors.toList());

		String propStr = props.stream().collect(Collectors.joining(", "));
		log.debug("initial_to_load_properties-" + propStr);
	}

}

package com.asi.mechanism;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "application")
public class SpringProperty {
	/**
	 * 
	 * retrieve parameters of spring boot from property file
	 */

	// 這裡的命名規則可以推測application.yml的結構樹
	// 例:environmentsProjectName
	// 可以推測有結構
	// environments
	// |
	// -Project
	// |
	// --Name

	private static Logger log = LogManager.getLogger(SpringProperty.class);
	public static String environmentsProjectName;
	public static String environmentsProjectUrl;
	public static String environmentsProjectProtocol;
	public static String environmentsProjectServiceType;
	public static String environmentsSecurityAllowCorsSwitcher;
	public static String environmentsProjectAuthorizationServerDomain;
	public static String environmentsProjectAuthorizationServerAuthen;
	public static String environmentsProjectAuthorizationServerCheckToken;
	public static String environmentsProjectAuthorizationServerRefreshToken;
	public static String environmentsProjectAuthorizationServerRefreshTokenChecktime;
	public static String environmentsProjectAuthorizationServerType;
	public static String serverPort;
	public static String apiDomaininformixapi;

	public static String getApiDomaininformixapi() {
		return apiDomaininformixapi;
	}

	public static void setApiDomaininformixapi(String apiDomaininformixapi) {
		SpringProperty.apiDomaininformixapi = apiDomaininformixapi;
	}

	public static String getEnvironmentsProjectAuthorizationServerType() {
		return environmentsProjectAuthorizationServerType;
	}

	@Value("${environments.project.authorization.server.type}")
	public void setEnvironmentsProjectAuthorizationServerType(String environmentsProjectAuthorizationServerType) {
		SpringProperty.environmentsProjectAuthorizationServerType = environmentsProjectAuthorizationServerType;
	}

	public static String getEnvironmentsSecurityAllowCorsSwitcher() {
		return environmentsSecurityAllowCorsSwitcher;
	}

	@Value("${environments.security.allowCors.switcher}")
	public void setEnvironmentsSecurityAllowCorsSwitcher(String environmentsSecurityAllowCorsSwitcher) {
		SpringProperty.environmentsSecurityAllowCorsSwitcher = environmentsSecurityAllowCorsSwitcher;
	}

	public static String getEnvironmentsProjectAuthorizationServerRefreshTokenChecktime() {
		return environmentsProjectAuthorizationServerRefreshTokenChecktime;
	}

	@Value("${environments.project.authorization.server.refreshtokenchecktime}")
	public void setEnvironmentsProjectAuthorizationServerRefreshTokenChecktime(
			String environmentsProjectAuthorizationServerRefreshTokenChecktime) {
		SpringProperty.environmentsProjectAuthorizationServerRefreshTokenChecktime = environmentsProjectAuthorizationServerRefreshTokenChecktime;
	}

	public static String getEnvironmentsProjectAuthorizationServerDomain() {
		return environmentsProjectAuthorizationServerDomain;
	}

	@Value("${environments.project.authorization.server.domain}")
	public void setEnvironmentsProjectAuthorizationServerDomain(String environmentsProjectAuthorizationServerDomain) {
		SpringProperty.environmentsProjectAuthorizationServerDomain = environmentsProjectAuthorizationServerDomain;
	}

	public static String getEnvironmentsProjectAuthorizationServerAuthen() {
		return environmentsProjectAuthorizationServerAuthen;
	}

	@Value("${environments.project.authorization.server.authen}")
	public void setEnvironmentsProjectAuthorizationServerAuthen(String environmentsProjectAuthorizationServerAuthen) {
		SpringProperty.environmentsProjectAuthorizationServerAuthen = environmentsProjectAuthorizationServerAuthen;
	}

	public static String getEnvironmentsProjectAuthorizationServerCheckToken() {
		return environmentsProjectAuthorizationServerCheckToken;
	}

	@Value("${environments.project.authorization.server.checktoken}")
	public void setEnvironmentsProjectAuthorizationServerCheckToken(
			String environmentsProjectAuthorizationServerCheckToken) {
		SpringProperty.environmentsProjectAuthorizationServerCheckToken = environmentsProjectAuthorizationServerCheckToken;
	}

	public static String getEnvironmentsProjectAuthorizationServerRefreshToken() {
		return environmentsProjectAuthorizationServerRefreshToken;
	}

	@Value("${environments.project.authorization.server.refreshtoken}")
	public void setEnvironmentsProjectAuthorizationServerRefreshToken(
			String environmentsProjectAuthorizationServerRefreshToken) {
		SpringProperty.environmentsProjectAuthorizationServerRefreshToken = environmentsProjectAuthorizationServerRefreshToken;
	}

	public static String getThisProjectDomain() {
		return environmentsProjectProtocol + environmentsProjectUrl + ":" + serverPort;
	}

	public static String getEnvironmentsProjectServiceType() {
		return environmentsProjectServiceType;
	}

	@Value("${environments.project.serviceType}")
	public void setEnvironmentsProjectServiceType(String environmentsProjectServiceType) {
		SpringProperty.environmentsProjectServiceType = environmentsProjectServiceType;
	}

	public static String getEnvironmentsProjectProtocol() {
		return environmentsProjectProtocol;
	}

	@Value("${environments.project.protocol}")
	public void setEnvironmentsProjectProtocol(String environmentsProtocol) {
		SpringProperty.environmentsProjectProtocol = environmentsProtocol;
	}

	public static String getEnvironmentsProjectName() {
		return environmentsProjectName;
	}

	@Value("${environments.project.name}")
	public void setEnvironmentsProjectName(String environmentsProjectName) {
		SpringProperty.environmentsProjectName = environmentsProjectName;
	}

	public static String getEnvironmentsProjectUrl() {
		return environmentsProjectUrl;
	}

	@Value("${environments.project.url}")
	public void setEnvironmentsProjectUrl(String environmentsProjectUrl) {
		SpringProperty.environmentsProjectUrl = environmentsProjectUrl;
	}

	public static String getServerPort() {
		return serverPort;
	}

	@Value("${server.port}")
	public void setServerPort(String serverPort) {
		SpringProperty.serverPort = serverPort;
	}

	/**
	 * 
	 */
	public SpringProperty() {

	}

	@PostConstruct
	public void init() {
		Class<? extends SpringProperty> clazz = this.getClass();

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

	/**
	 * 
	 */
	public static String uploadURL;

	@Value("${path.url}")
	public void setUploadURL(String uploadURL) {
		SpringProperty.uploadURL = uploadURL;
	}

	public static String getUploadURL() {
		return uploadURL;
	}

	/**
	 * mailServer
	 */
	public static String mailUsername;
	public static String mailPassword;
	public static String mailHost;
	public static int mailPort;
	public static String mailAuth;
	public static String mailStarttlsEnable;

	@Value("${mailServer.username}")
	public void setMailUsername(String mailUsername) {
		SpringProperty.mailUsername = mailUsername;
	}

	public static String getMailUsername() {
		return mailUsername;
	}

	@Value("${mailServer.password}")
	public void setMailPassword(String mailPassword) {
		SpringProperty.mailPassword = mailPassword;
	}

	public static String getMailPassword() {
		return mailPassword;
	}

	@Value("${mailServer.host}")
	public void setMailHost(String mailHost) {
		SpringProperty.mailHost = mailHost;
	}

	public static String getMailHost() {
		return mailHost;
	}

	@Value("${mailServer.port}")
	public void setMailPort(int mailPort) {
		SpringProperty.mailPort = mailPort;
	}

	public static int getMailPort() {
		return mailPort;
	}

	@Value("${mailServer.auth}")
	public void setMailAuth(String mailAuth) {
		SpringProperty.mailAuth = mailAuth;
	}

	public static String getMailAuth() {
		return mailAuth;
	}

	@Value("${mailServer.starttlsEnable}")
	public void setMailStarttlsEnable(String mailStarttlsEnable) {
		SpringProperty.mailStarttlsEnable = mailStarttlsEnable;
	}

	public static String getMailStarttlsEnable() {
		return mailStarttlsEnable;
	}

	/**
	 * 報表檔案輸出設定位置(local)
	 * localFileSetting
	 */
	public static String localFilePath;
	public static String localFileForPdf;
	public static String localFileForExcel;

	public static String getLocalFilePath() {
		return localFilePath;
	}

	@Value("${localFileSetting.uploadPath}")
	public void setLocalFilePath(String localFilePath) {
		SpringProperty.localFilePath = localFilePath;
	}

	public static String getLocalFileForPdf() {
		return localFileForPdf;
	}

	@Value("${localFileSetting.uploadPathForPdf}")
	public void setLocalFileForPdf(String localFileForPdf) {
		SpringProperty.localFileForPdf = localFileForPdf;
	}

	public static String getLocalFileForExcel() {
		return localFileForExcel;
	}

	@Value("${localFileSetting.uploadPathForExcel}")
	public void setLocalFileForExcel(String localFileForExcel) {
		SpringProperty.localFileForExcel = localFileForExcel;
	}

	
	/**
	 * Rin1203同險設定_每日增刪FriTemparea時間設定
	 * 
	 */
	public static Integer SetYear;
	public static String SetRunTime;

	public static Integer getSetYear() {
		return SetYear;
	}

	
	@Value("${environments.schedule.func.rin1203_var_year}")
//	@Value("${environments.schedule.rin1203.friTempareaSetting.year}")
	public void setSetYear(Integer setYear) {
		SpringProperty.SetYear = setYear;
	}

	public static String getSetRunTime() {
		return SetRunTime;
	}

	@Value("${environments.schedule.func.rin1203_var_runtime}")
	public void setSetRunTime(String setRunTime) {
		SpringProperty.SetRunTime = setRunTime;
	}


	
}

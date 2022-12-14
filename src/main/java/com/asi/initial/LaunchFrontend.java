package com.asi.initial;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.asi.mechanism.SpringProperty;
import com.asi.mechanism.SpringPropertyNewMechanismTest;
import com.asi.mechanism.security.jwtref.JwtRequestFilter;

@SpringBootApplication
@ComponentScan({ "com.asi.huanan.controller", "com.asi.huanan.controller.api", "com.asi.huanan.controller.api.common",
		"com.asi.huanan.controller.api.common", "com.asi.huanan.service", "com.asi.huanan.service",
		"com.asi.huanan.service.connector", "com.asi.huanan.service.dao", "com.asi.huanan.service.dao.mybatis.mapper",
		"com.asi.huanan.service.dao.mybatis.model", "com.asi.huanan.service.repository", "com.asi.mechanism",
		"com.asi.mechanism.controller", "com.asi.mechanism.handler", "com.asi.mechanism.security",
		"com.asi.mechanism.security.acc", "com.asi.mechanism.security.jwtref", "com.asi.mechanism.service",
		"com.asi.mechanismn.service", "com.asi.mechanismn.service.dao", "com.asi.mechanismn.service.dao.mybatis.mapper",
		"com.asi.mechanismn.service.repository", "com.asi.schedule", "com.asi.schedule.job" })
@Configuration
public class LaunchFrontend extends SpringBootServletInitializer {
	private static Logger logger = LogManager.getLogger(LaunchFrontend.class);

	/**
	 * allocate this class to spring boot
	 */
	private static Class<LaunchFrontend> controller = LaunchFrontend.class;

	/**
	 * override method in SpringBootServletInitializer
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(controller);
	}

	@Autowired
	private SpringPropertyNewMechanismTest springPropertyNewMechanismTest;

	/**
	 * Spring main method to execute for initial
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(LaunchFrontend.class, args);

		LaunchFrontend launch = new LaunchFrontend();
		launch.initial();

		logger.debug(
				SpringProperty.getEnvironmentsProjectName() + "-" + "server_type:" + JwtRequestFilter.getServerType());
	}

	public void initial() {
		// ????????????
		JwtRequestFilter.setServerType(SpringProperty.getEnvironmentsProjectServiceType());

		// ??????initial?????????
//				Initiator.frontendControllerContext = "com.asi.huanan.controller";

		// ???????????????????????????
//		JwtRequestFilter.setAuthorizationServerDomain(springPropertyNewMechanismTest.getEnvironment().getProject()
//				.getAuthorization().getServer().getDomain());
		JwtRequestFilter.setAuthorizationServerDomain(SpringProperty.getEnvironmentsProjectAuthorizationServerDomain());
		JwtRequestFilter.setAuthorizationServerAuthen(SpringProperty.getEnvironmentsProjectAuthorizationServerAuthen());
		JwtRequestFilter
				.setAuthorizationServerCheckToken(SpringProperty.getEnvironmentsProjectAuthorizationServerCheckToken());
		JwtRequestFilter.setAuthorizationServerRefreshToken(
				SpringProperty.getEnvironmentsProjectAuthorizationServerRefreshToken());
	}
}

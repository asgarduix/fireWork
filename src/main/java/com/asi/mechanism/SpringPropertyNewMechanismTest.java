package com.asi.mechanism;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 使用物件方式測試映射設定
 * 
 * @author biruh
 *
 */
//@Configuration
//@EnableConfigurationProperties
//@ConfigurationProperties
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "application")
public class SpringPropertyNewMechanismTest {

//	private String name;
	private Environments environments;

//	private boolean enabled;
//	private List<String> servers = new ArrayList<>();

	// standard getters and setters
//	environmentsProjectAuthorizationServerDomain	

	public Environments getEnvironments() {
		return environments;
	}

	public void setEnvironments(Environments environments) {
		this.environments = environments;
	}

	/**
	 * 
	 * @author biruh
	 *
	 */
	public class Environments {
		private Project project;

		public Project getProject() {
			return project;
		}

		public void setProject(Project project) {
			this.project = project;
		}

	}

	/**
	 * 
	 * @author biruh
	 *
	 */
	public class Project {
		private Authorization authorization;

		public Authorization getAuthorization() {
			return authorization;
		}

		public void setAuthorization(Authorization authorization) {
			this.authorization = authorization;
		}

	}

	/**
	 * 
	 * @author biruh
	 *
	 */
	public class Authorization {
		private Server server;

		public Server getServer() {
			return server;
		}

		public void setServer(Server server) {
			this.server = server;
		}

	}

	/**
	 * 
	 * @author biruh
	 *
	 */
	public class Server {
		private String domain;

		public String getDomain() {
			return domain;
		}

		public void setDomain(String domain) {
			this.domain = domain;
		}

	}

}

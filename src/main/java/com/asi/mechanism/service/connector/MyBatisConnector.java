package com.asi.mechanism.service.connector;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.asi.huanan.service.dao.mybatis.mapper.customerize.Rin1304Mapper;

@Configuration
public class MyBatisConnector {

	private Logger log = LogManager.getLogger(MyBatisConnector.class);

	// @Autowired
	// @Qualifier("seccondDataSource")
	// private DataSource dataSource;
	
	@Value("${spring.datasource.first.username}")
	private String firstDBUserName;
	
	@Value("${spring.datasource.first.password}")
	private String firstDBPassword;
	
	
	@Bean
	@Primary
	@ConfigurationProperties("spring.datasource.first")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "datasource")
	@Primary
	@ConfigurationProperties("spring.datasource.first.hikari")
	public DataSource dataSource() {
		DataSourceProperties dsp= dataSourceProperties();
		dsp.setUsername(firstDBUserName);
		dsp.setPassword(firstDBPassword);
		return dsp.initializeDataSourceBuilder().build();
	}

	/**
	 * 
	 */
	@Bean(name = "transaction")
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(this.dataSource());
	}

	/**
	 * 
	 */
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory createSqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(this.dataSource());
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:" + "mapper" + "/*.xml"));

		// 註冊customer（客製化SQL）的Mapper
		org.apache.ibatis.session.Configuration config = sqlSessionFactoryBean.getObject().getConfiguration();
		config.addMapper(Rin1304Mapper.class);

		return sqlSessionFactoryBean.getObject();
	}

	// =====second=====

	// @Autowired
	// @Qualifier("dataSourceSec")
	// private DataSource dataSource;

//	@Bean(name = "dataSourcePropertySec")
//	@ConfigurationProperties("spring.datasource.second")
//	public DataSourceProperties dataSourcePropertiesSec() {
//		return new DataSourceProperties();
//	}
//
//	@Bean(name = "dataSourceSec")
//	@ConfigurationProperties("spring.datasource.second.hikari")
//	public DataSource dataSourceSec() {
//		return dataSourcePropertiesSec().initializeDataSourceBuilder().build();
//	}
//
//	/**
//	 * 
//	 */
//	@Bean(name = "transactionSec")
//	public PlatformTransactionManager transactionManagerSec() {
//		return new DataSourceTransactionManager(this.dataSourceSec());
//	}
//
//	/**
//	 * 
//	 */
//	@Bean(name = "sqlSessionFactorySec")
//	public SqlSessionFactory createSqlSessionFactorySec() throws Exception {
//		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//		sqlSessionFactoryBean.setDataSource(this.dataSourceSec());
//		return sqlSessionFactoryBean.getObject();
//	}
//
//	// =====third=====
//
//	@Bean(name = "dataSourceProperty3rd")
//	@ConfigurationProperties("spring.datasource.third")
//	public DataSourceProperties dataSourceProperties3rd() {
//		return new DataSourceProperties();
//	}
//
//	@Bean(name = "dataSource3rd")
//	@ConfigurationProperties("spring.datasource.third.hikari")
//	public DataSource dataSource3rd() {
//		return dataSourceProperties3rd().initializeDataSourceBuilder().build();
//	}
//
//	/**
//	 * 
//	 */
//	@Bean(name = "transaction3rd")
//	public PlatformTransactionManager transactionManager3rd() {
//		return new DataSourceTransactionManager(this.dataSource3rd());
//	}
//
//	/**
//	 * 
//	 */
//	@Bean(name = "sqlSessionFactory3rd")
//	public SqlSessionFactory createSqlSessionFactory3rd() throws Exception {
//		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//		sqlSessionFactoryBean.setDataSource(this.dataSource3rd());
//		return sqlSessionFactoryBean.getObject();
//	}
//
//	// =====test=====
//	
//	/**
//	 * 注意!僅供測試，不建議使用
//	 * 
//	 * 測試DataSource使用靜態方式宣告和使用
//	 */
//	public static DataSource datasource4Sqlite = null;
//
//	/**
//	 * 注意!僅供測試，不建議使用
//	 * 
//	 * @return
//	 */
//	@Bean(name = "dataSourceTest")
////	@ConfigurationProperties("spring.datasource.third.hikari")
//	public DataSource dataSourceTest() {
//		if (datasource4Sqlite == null) {
//			log.debug("datasource is null!");
//			datasource4Sqlite = DataSourceBuilder.create().driverClassName("org.sqlite.JDBC")
//					.url("jdbc:sqlite::memory:").build();
//			return datasource4Sqlite;
//		}
//
//		log.debug("use static datasource");
//		return datasource4Sqlite;
//	}
//
//	/**
//	 * 注意!僅供測試，不建議使用
//	 */
//	@Bean(name = "sqlSessionFactoryTest")
//	public SqlSessionFactory createSqlSessionFactoryTest() throws Exception {
//		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//		sqlSessionFactoryBean.setDataSource(this.dataSource3rd());
//		return sqlSessionFactoryBean.getObject();
//	}
}

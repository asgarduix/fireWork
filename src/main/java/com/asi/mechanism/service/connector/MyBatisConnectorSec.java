package com.asi.mechanism.service.connector;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

//TODO 現場測試informix，暫無測試DB，現場測試再處理
//@Configuration
public class MyBatisConnectorSec {

//	private Logger log = LogManager.getLogger(MyBatisConnectorSec.class);
//
////	@Autowired
////	@Qualifier("dataSourceSec")
////	private DataSource dataSource;
//
//	@Bean(name = "dataSourcePropertySec")
////	@Primary
//	@ConfigurationProperties("spring.datasource.second")
//	public DataSourceProperties firstDataSourceProperties() {
//		return new DataSourceProperties();
//	}
//
//	@Bean(name = "dataSourceSec")
////	@Primary
//	@ConfigurationProperties("spring.datasource.second.hikari")
//	public DataSource dataSource() {
//		return firstDataSourceProperties().initializeDataSourceBuilder().build();
//	}
//
//	/**
//	 * 
//	 */
//	@Bean(name = "transactionSec")
//	public PlatformTransactionManager transactionManager() {
//		return new DataSourceTransactionManager(this.dataSource());
//	}
//
//	/**
//	 * 
//	 */
//	@Bean(name = "sqlSessionFactorySec")
//	public SqlSessionFactory createSqlSessionFactory() throws Exception {
//		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//		sqlSessionFactoryBean.setDataSource(this.dataSource());
//		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:" + "mapper" + "/*.xml"));
//		return sqlSessionFactoryBean.getObject();
//	}
}

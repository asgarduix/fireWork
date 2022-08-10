package com.asi.mechanism;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySources;

@Configuration
@EnableEncryptableProperties
//@EncryptablePropertySource("classpath:application-dev.yml")
//@EncryptablePropertySources(
   //{@EncryptablePropertySource("classpath:application-dev.yml"),
   //@EncryptablePropertySource("classpath:application-uat.yml")})
public class JasyptConfig {
	
//	  @Bean("jasyptStringEncryptor")
//	    public StringEncryptor stringEncryptor() {
//	        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
//	        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
//	        config.setPassword("xxxx");
//	        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
//	        config.setKeyObtentionIterations("1000");
//	        config.setPoolSize("1");
//	        config.setProviderName("SunJCE");
//	        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
//	        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
//	        config.setStringOutputType("base64");
//	        encryptor.setConfig(config);
//	        return encryptor;
//	    }
	  
}

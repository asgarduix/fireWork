package com.asi.mechanism;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
@ComponentScan
public class ValidationConfig {
	/**
	 * 註冊MethodValidationPostProcessor bean，後續Controller
	 * class開頭加的@Validated和method使用的@RequestParam annotation中加入validate
	 * annotation(e.g. @NotBlank, @NotNull ...etc)才會生效
	 * 
	 * 沒有註冊時程式碼裡有加這些 annotation依舊不會有作用
	 *
	 * @return
	 */
	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}
}
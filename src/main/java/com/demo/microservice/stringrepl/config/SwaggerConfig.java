package com.demo.microservice.stringrepl.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration for Swagger2.
 * 
 * @author sofia
 * @date 2019-05-11
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	protected static final String GROUP_NAME = "string-replacement-service-api";
	protected static final String PATH = "/string-repl.*";
	protected static final String TITLE = "String Replacement Service API";
	protected static final String DESCRIPTION = "REST API for performing string replacement and reversal";
	protected static final String TOS_URL = "http://microservice.demo.com";
	protected static final String LICENSE = "Apache License";
	protected static final String LICENSE_URL = "http://microservice.demo.com/license";
	protected static final String VERSION = "1.0";
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName(GROUP_NAME)
				.apiInfo(apiInfo())
				.select()
				.paths(paths())
				.build();
	}

	protected ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title(TITLE)
				.description(DESCRIPTION)
				.termsOfServiceUrl(TOS_URL)
				.license(LICENSE)
				.licenseUrl(LICENSE_URL)
				.version(VERSION)
				.build();
	}
	
	private Predicate<String> paths() {
		return regex(PATH);
	}

}

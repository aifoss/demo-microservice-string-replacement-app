package com.demo.microservice.stringrepl.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author sofia
 * @date 2019-05-11
 */
@SpringBootApplication
@EnableSwagger2
@ComponentScan("com.demo.microservice.stringrepl")
public class StringReplacementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StringReplacementApplication.class, args);
	}

}

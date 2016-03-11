package com.gergo.takacs.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableAutoConfiguration
@ServletComponentScan(basePackages = "com.gergo.takacs.rest")
@Configuration
@ImportResource(locations = "classpath*:applicationContext*.xml")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

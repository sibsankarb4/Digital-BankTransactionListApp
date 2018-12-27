package com.transactions.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * This is a common configuration class for Spring
 * 
 * @author Sibsankar Bera
 * @version 1.0
 * @since 2018-08-31
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.transactions")
@PropertySource("classpath:application.properties")
public class AppConfig {

}
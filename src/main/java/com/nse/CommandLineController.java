package com.nse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@PropertySource("file:/home/saurabh/data/conf/nse.properties")
@Configuration
@ComponentScan
@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration
public class CommandLineController  {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CommandLineController.class, args);
	}
}

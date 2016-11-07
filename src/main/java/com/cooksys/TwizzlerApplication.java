package com.cooksys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.cooksys.twizzler_controller","com.cooksys.service"})
@EnableAutoConfiguration
public class TwizzlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwizzlerApplication.class, args);
	}
}

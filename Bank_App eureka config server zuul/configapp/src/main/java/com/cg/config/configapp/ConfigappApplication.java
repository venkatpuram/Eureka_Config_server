package com.cg.config.configapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigappApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigappApplication.class, args);
	}

}


package com.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import com.spring.rest.service.StorageService;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class SpringMain implements CommandLineRunner{
	
	@Autowired
	StorageService storageService;
	
	@Autowired
	StorageService storageService1;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringMain.class, args);

		
	}

	@Override
	public void run(String... args) throws Exception {
		storageService.init();
	}
}

package com.icarodebarros.learnjasperreport.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.icarodebarros.learnjasperreport.service.DBService;

@Configuration
public class Config {
	
	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		if (strategy == null || !strategy.equals("create")) {
			return false;
		}
		dbService.instantiateTestDatabase();			
		
		return true;
	}

}

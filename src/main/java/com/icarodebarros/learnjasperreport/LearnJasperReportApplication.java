package com.icarodebarros.learnjasperreport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.icarodebarros.learnjasperreport.config.FileStorageProperties;

@EnableConfigurationProperties({
    FileStorageProperties.class
})
@SpringBootApplication
public class LearnJasperReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnJasperReportApplication.class, args);
	}

}

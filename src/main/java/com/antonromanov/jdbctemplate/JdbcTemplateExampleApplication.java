package com.antonromanov.jdbctemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;

@SpringBootApplication
public class JdbcTemplateExampleApplication {

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "jdbcexample.datasource")
	public DataSource primaryDataSource(){
		return DataSourceBuilder.create().build();
	}

	public static void main(String[] args) {
		SpringApplication.run(JdbcTemplateExampleApplication.class, args);
	}

}

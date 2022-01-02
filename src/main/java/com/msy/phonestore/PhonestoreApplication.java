package com.msy.phonestore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.msy.phonestore.mapper")
public class PhonestoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhonestoreApplication.class, args);
	}
}

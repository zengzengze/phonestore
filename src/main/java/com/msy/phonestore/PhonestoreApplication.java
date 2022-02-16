package com.msy.phonestore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.msy.phonestore.mapper")
public class PhonestoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhonestoreApplication.class, args);
	}
}

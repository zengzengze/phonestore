package com.msy.phonestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class PhonestoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhonestoreApplication.class, args);
	}

}

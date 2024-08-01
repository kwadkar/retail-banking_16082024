package com.academy.miniproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class RetailBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailBankingApplication.class, args);
		System.out.println("Hello tested");
		
	}
	

}

package com.moneymoney.app.accountservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.moneymoney.app.accountservice.entity.CurrentAccount;
import com.moneymoney.app.accountservice.entity.SavingsAccount;
import com.moneymoney.app.accountservice.repo.AccountRepository;

@EnableDiscoveryClient
@SpringBootApplication
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner populateData(AccountRepository accountRepository) {
		return (args) -> {accountRepository.save(new SavingsAccount(101, "venkat", 50000.0, true));
			accountRepository.save(new SavingsAccount(102, "nikil", 9000.0, true));
			accountRepository.save(new SavingsAccount(103, "pavan", 17000.0, true));
			accountRepository.save(new CurrentAccount(104, "harish", 10000.0, 1000.0));
			accountRepository.save(new CurrentAccount(105, "prudhvi", 12000.0, 1200.0));
			accountRepository.save(new CurrentAccount(106, "guru", 100000.0, 10000.0));
		};
	}
}


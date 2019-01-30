package com.moneymoney.app.accountservice.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moneymoney.app.accountservice.entity.Account;
import com.moneymoney.app.accountservice.entity.SavingsAccount;
import com.moneymoney.app.accountservice.service.AccountService;

@RefreshScope
@RestController
@RequestMapping("/accounts")
public class AccountResource {

	@Autowired
	private AccountService service;

	@PostMapping
	public void addNewAccount(@RequestBody SavingsAccount account) {
		service.addNewAccount(account);
	}

	@GetMapping
	public ResponseEntity<List<Account>> getAllAccounts() {
		List<Account> account = service.getAllAccounts();
		return new ResponseEntity<>(account, HttpStatus.OK);
	}

	@GetMapping("/{accountNumber}")
	public ResponseEntity<Account> getAccountById(@PathVariable int accountNumber) {
		Optional<Account> optionalaccount = service.getAccountById(accountNumber);
		Account account = optionalaccount.get();
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}

	@GetMapping("/{accountNumber}/balance")
	public ResponseEntity<Double> getCurrentBalance(@PathVariable int accountNumber) {
		Optional<Account> optionalaccount = service.getAccountById(accountNumber);
		double currentBalance = optionalaccount.get().getCurrentBalance();
		if (optionalaccount.get() == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		System.out.println("current Balance" + currentBalance);
		return new ResponseEntity<>(currentBalance, HttpStatus.OK);
	}

	@PutMapping
	public void updateAccount(@RequestBody SavingsAccount account) {
		service.updateAccount(account);
	}

	@DeleteMapping("/{accountNumber}")
	public void deleteAccount(@PathVariable int accountNumber) {
		service.deleteAccount(accountNumber);
	}

	/*
	 * @PutMapping("/{accountNumber}") public ResponseEntity<Account>
	 * updateBalance(@RequestParam("currentBalance") double currentBalance,
	 * 
	 * @PathVariable int accountNumber) { Optional<Account> optional =
	 * service.getAccountById(accountNumber); Account account = optional.get(); if
	 * (account == null) {
	 * 
	 * return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	 * 
	 * } account.setCurrentBalance(currentBalance); service.updateBalance(account);
	 * return new ResponseEntity<Account>(account, HttpStatus.OK); }
	 */
	
	public void updateBalance(int accountId, double currentBalance) {
		Optional<Account> optional = service.getAccountById(accountId);
		Account account=optional.get();
		account.setCurrentBalance(currentBalance);
		service.updateBalance(account);
	}
}

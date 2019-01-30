package com.moneymoney.app.accountservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moneymoney.app.accountservice.entity.Account;
import com.moneymoney.app.accountservice.entity.SavingsAccount;
import com.moneymoney.app.accountservice.repo.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository repository;

	@Override
	public Optional<Account> getAccountById(int accountNumber) {
		return repository.findById(accountNumber);

	}

	@Override
	public List<Account> getAllAccounts() {
		return repository.findAll();
	}

	@Override
	public void addNewAccount(SavingsAccount account) {
		repository.save(account);
		
	}

	@Override
	public void updateAccount(SavingsAccount account) {
		repository.save(account);
		
	}

	@Override
	public void deleteAccount(int accountNumber) {
		repository.deleteById(accountNumber);
		
	}

	@Override
	public void updateBalance(Account account) {
		repository.save(account);
		
	}

	
}

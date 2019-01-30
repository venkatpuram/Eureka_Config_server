package com.moneymoney.app.accountservice.service;

import java.util.List;
import java.util.Optional;

import com.moneymoney.app.accountservice.entity.Account;
import com.moneymoney.app.accountservice.entity.SavingsAccount;

public interface AccountService {

	

	Optional<Account> getAccountById(int accountNumber);

	List<Account> getAllAccounts();

	void addNewAccount(SavingsAccount account);

	void updateAccount(SavingsAccount account);

	void deleteAccount(int accountNumber);

	void updateBalance(Account account);

	
	
	

}

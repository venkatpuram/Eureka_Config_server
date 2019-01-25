package com.moneymoney.app.transactionservice.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moneymoney.app.transactionservice.entity.Transaction;
import com.moneymoney.app.transactionservice.entity.TransactionType;
import com.moneymoney.app.transactionservice.repo.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionRepository repository;
	@Override
	public Double deposit(Integer accountNumber, String transactionDetails, double currentBalance, Double amount) {
		Transaction transaction=new Transaction();
		transaction.setAccountNumber(accountNumber);
		transaction.setTransactionDetails(transactionDetails);
		currentBalance +=amount;
		transaction.setCurrentBalance(currentBalance);
		transaction.setAmount(amount);
		transaction.setTransactionDate(LocalDateTime.now());
		transaction.setTransactionType(TransactionType.DEPOSIT);
		repository.save(transaction);
		return currentBalance;
		
	}
	@Override
	public Double withDraw(Integer accountNumber, String transactionDetails, double currentBalance, Double amount) {
		Transaction transaction=new Transaction();
		transaction.setAccountNumber(accountNumber);
		transaction.setTransactionDetails(transactionDetails);
		currentBalance -=amount;
		transaction.setCurrentBalance(currentBalance);
		transaction.setAmount(amount);
		transaction.setTransactionDate(LocalDateTime.now());
		transaction.setTransactionType(TransactionType.WITHDRAW);
		repository.save(transaction);
		return currentBalance;
	}
	@Override
	public List<Transaction> getStatement() {
		return repository.findAll();
	}

}

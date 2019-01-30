package com.moneymoney.app.transactionservice.service;

import java.util.List;

import com.moneymoney.app.transactionservice.entity.Transaction;

public interface TransactionService {

	Double deposit(Integer accountNumber, String transactionDetails, double currentBalance, Double amount);

	Double withDraw(Integer accountNumber, String transactionDetails, double currentBalance, Double amount);

	List<Transaction> getStatement();

}

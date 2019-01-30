package com.moneymoney.app.accountservice.receiver;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.moneymoney.app.accountservice.resource.AccountResource;
import com.moneymoney.app.transactionservice.entity.Transaction;

@Component

public class Receiver {

	@Autowired
	private AccountResource resource;

	@Bean
	public Queue queue() {
		return new Queue("transactionQ", false);
	}

	@RabbitListener(queues = "transactionQ")
	public void processMessage(Transaction transaction) throws ListenerExecutionFailedException {
		System.out.println(transaction.toString());
		resource.updateBalance(transaction.getAccountNumber(), transaction.getCurrentBalance());
	}
}

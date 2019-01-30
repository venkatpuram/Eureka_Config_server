package com.moneymoney.app.transactionservice.sender;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.moneymoney.app.transactionservice.entity.Transaction;

@Component
public class Sender {

	@Autowired
	private RabbitMessagingTemplate template;
	
	@Bean
	public Queue queue()
	{
		return new Queue("transactionQ",false);
	}
	
	public void send(Transaction transaction)
	{
		template.convertAndSend("transactionQ", transaction);
	}
	
}

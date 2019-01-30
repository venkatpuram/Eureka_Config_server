package com.cg.message.messagesender.sender;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component

public class Sender {

	@Autowired
	RabbitMessagingTemplate messagingTemplate;

	@Bean
	public Queue queue() {
		return new Queue("message", false);
	}
	
	public void send(String message)
	{
		messagingTemplate.convertAndSend("message", message);
	}

}

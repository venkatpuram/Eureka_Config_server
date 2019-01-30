package com.cg.message.messagereceiver.receiver;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	
	@Bean
	public Queue queue() {
		return new Queue("message", false);
	}
	
	@RabbitListener(queues = "message")
	public void processMessage(String email) {
		System.out.println(email);
		
	}
}

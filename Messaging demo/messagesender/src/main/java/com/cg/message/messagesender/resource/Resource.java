package com.cg.message.messagesender.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.message.messagesender.sender.Sender;

@RestController
public class Resource {

	@Autowired
	private Sender sender;
	
	@RequestMapping
	public void display()
	{
		sender.send("Hello World");
	}
}

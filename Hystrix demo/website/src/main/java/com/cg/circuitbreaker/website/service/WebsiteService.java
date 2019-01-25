package com.cg.circuitbreaker.website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class WebsiteService {
	
	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "reliable")
	public String display(Model model) {
		ResponseEntity<String> entity=restTemplate.getForEntity("http://localhost:8383/hello", String.class);
		String hello=entity.getBody();
		model.addAttribute("hello",hello);
		return "hello";
	}

	public String reliable(Model model) {
		String hello="hello everyone";
		model.addAttribute("hello",hello);
		return "hello";
	}
}

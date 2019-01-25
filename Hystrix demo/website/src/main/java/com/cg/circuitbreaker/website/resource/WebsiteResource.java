package com.cg.circuitbreaker.website.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.cg.circuitbreaker.website.service.WebsiteService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Controller

public class WebsiteResource {

	@Autowired
	private WebsiteService service;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/hello")

	public String display(Model model) {

		return service.display(model);
	}

}

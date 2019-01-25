package com.cg.circuitbreaker.helloworld.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceResource {

	@RequestMapping("/hello")
	  public String readingList(){
	    return "Hello World";
	  }
}

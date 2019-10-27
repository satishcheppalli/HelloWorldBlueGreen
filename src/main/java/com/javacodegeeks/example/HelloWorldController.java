package com.javacodegeeks.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping("/hello")
	public String sayHello() {
		return "<font size='4' face='arial' color='#1c87c9'><b>Blue Deployment</b></font>";
		//return "<font size='4' face='arial' color='#8ebf42'><b>Green Deployment</b></font>";
	}
}

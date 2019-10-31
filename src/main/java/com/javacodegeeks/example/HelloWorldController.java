package com.javacodegeeks.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping("/hello")
	public String sayHello() throws InterruptedException {
		for(int i=0; i<=5000;i++){
			System.out.println(i);
		}
		Thread.sleep(30 * 1000);
		//return "<font size='4' face='arial' color='#1c87c9'><b>Blue Deployment</b></font>";
		return "<font size='4' face='arial' color='#8ebf42'><b>Green Deployment</b></font>";
		//return "Hello BlueGreen";
	}
}

package com.tech.SampleSpringProject.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
	
	@GetMapping("/hello")
	public String home() {
		return "Hello World";
		}	
	
}

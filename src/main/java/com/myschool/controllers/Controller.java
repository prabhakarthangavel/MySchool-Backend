package com.myschool.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myschool.models.Response;

@RestController
public class Controller {
	
	@GetMapping("/welcome")
	public Response save() {
		return new Response("Hello World");
	}
}
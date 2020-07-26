package com.myschool.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myschool.entity.Test;
import com.myschool.models.Response;
import com.myschool.repository.TestRepo;

@RestController
public class Controller {
	
	@Autowired
	private TestRepo testRepo;
	
	@GetMapping("/welcome")
	public Response save() {
		return new Response("Hello World");
	}
	
	@GetMapping("/testDB")
	public Response getDb() {
		Optional<Test> test = testRepo.findById(123);
		return new Response(test.get().getMessage());
	}
}
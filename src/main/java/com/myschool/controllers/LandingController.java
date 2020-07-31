package com.myschool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myschool.models.request.AssignmentRequest;
import com.myschool.models.request.AttendanceRequest;
import com.myschool.models.response.Response;
import com.myschool.serviceImpl.TeachersServiceImpl;

@RestController
public class LandingController {
	
	@Autowired
	private TeachersServiceImpl componentService;
	
	@PostMapping("/setAttandance")
	public ResponseEntity<Response> setAttandance(@RequestBody AttendanceRequest request) {
		return ResponseEntity.ok(new Response(componentService.saveAttendance(request)));
	}
	
	@PostMapping("/setAssignments")
	public ResponseEntity<Response> setTimetable(@RequestBody AssignmentRequest request){
		return ResponseEntity.ok(new Response(componentService.saveAssginments(request)));
	}
}

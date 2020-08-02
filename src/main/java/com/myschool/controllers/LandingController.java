package com.myschool.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myschool.entity.ClassTable;
import com.myschool.models.request.AssignmentRequest;
import com.myschool.models.request.AttendanceRequest;
import com.myschool.models.response.ClassList;
import com.myschool.models.response.Response;
import com.myschool.repository.ClassesRepo;
import com.myschool.serviceImpl.TeachersServiceImpl;

@RestController
public class LandingController {

	@Autowired
	private TeachersServiceImpl componentService;

	@Autowired
	private ClassesRepo classRepo;

	@PostMapping("/setAttandance")
	public ResponseEntity<Response> setAttandance(@RequestBody AttendanceRequest request) {
		return ResponseEntity.ok(new Response(componentService.saveAttendance(request)));
	}

	@PostMapping("/setAssignments")
	public ResponseEntity<Response> setTimetable(@RequestBody AssignmentRequest request) throws ParseException {	    
		return ResponseEntity.ok(new Response(componentService.saveAssginments(request)));
	}
	
	@GetMapping("/getClasses")
	public ResponseEntity<List<Integer>> getSection(){
		return ResponseEntity.ok(classRepo.findClasses());
	}

	@GetMapping("/getSection/{classes}")
	public ResponseEntity<ClassList> getSection(@PathVariable int classes) {
		ClassTable entity = classRepo.findByClass(classes);
		List<String> section = Arrays.asList(entity.getSection().split(","));
		List<String> subjects = Arrays.asList(entity.getSubjects().split(","));
		return ResponseEntity.ok(new ClassList(section, subjects));
	}
}

package com.myschool.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myschool.models.response.AttendanceResponse;
import com.myschool.serviceImpl.StudentsServiceImpl;

@RestController
@RequestMapping("/student")
public class StudentsController {
	
	@Autowired
	private StudentsServiceImpl studentsService;
	
	@GetMapping("/getAttendance/{student_id}")
	public ResponseEntity<List<AttendanceResponse>> getAttandance(@PathVariable String student_id){
		List<AttendanceResponse> response = studentsService.getAttendance(student_id);
		return ResponseEntity.ok(response);
	}
}

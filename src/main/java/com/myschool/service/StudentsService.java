package com.myschool.service;

import java.util.List;

import com.myschool.models.response.AttendanceResponse;

public interface StudentsService {
	public List<AttendanceResponse> getAttendance(String student_id);
}	

package com.myschool.service;

import com.myschool.models.request.AssignmentRequest;
import com.myschool.models.request.AttendanceRequest;

public interface TeachersService {
	public String saveAttendance(AttendanceRequest request);
	public String saveAssginments(AssignmentRequest request);
}

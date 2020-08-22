package com.myschool.service;

import java.util.List;

import com.myschool.models.request.AssignmentRequest;
import com.myschool.models.response.AttendanceResponse;
import com.myschool.models.response.MessagesResponse;

public interface StudentsService {
	public List<AttendanceResponse> getAttendance(String student_id);
	public List<AssignmentRequest> getAssignments(String student_id);
	public List<MessagesResponse> getMessagesClass(int clas);	
	public int getClas(String student_id);
	public List<MessagesResponse> getMessagesById(String student_id);	
}	

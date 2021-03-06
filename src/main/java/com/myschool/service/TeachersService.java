package com.myschool.service;

import java.util.List;

import com.myschool.models.request.AssignmentRequest;
import com.myschool.models.request.AttendanceRequest;
import com.myschool.models.request.HolidayListRequest;
import com.myschool.models.request.MessagesRequest;
import com.myschool.models.request.PerformanceRequest;
import com.myschool.models.request.UserRequest;
import com.myschool.models.response.StudentsList;

public interface TeachersService {
	public String saveAttendance(AttendanceRequest request);
	public String saveAssginments(AssignmentRequest request);
	public String saveMessages(MessagesRequest request);
	public List<StudentsList> getStudentList(String studentId);
	public String setPerformance(PerformanceRequest request);
	public String SetHoliday(HolidayListRequest request);
	public String saveUser(UserRequest request);
	public String getRole(String id);
	public String getfistName(String id);
}

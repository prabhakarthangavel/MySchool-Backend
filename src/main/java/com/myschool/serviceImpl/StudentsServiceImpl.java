package com.myschool.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myschool.entity.AttendanceList;
import com.myschool.models.response.AttendanceResponse;
import com.myschool.repository.AttendanceRepo;
import com.myschool.service.StudentsService;

@Service
public class StudentsServiceImpl implements StudentsService {
	
	@Autowired
	private AttendanceRepo attenListRepo;
	
	@Override
	public List<AttendanceResponse> getAttendance(String student_id) {
		List<AttendanceResponse> response = new ArrayList<AttendanceResponse>();
		List<AttendanceList> entities = attenListRepo.findStudentId(student_id);
		for(AttendanceList source:entities) {
			AttendanceResponse target = new AttendanceResponse();
			BeanUtils.copyProperties(source, target);
			response.add(target);
		}
		return response;
	}
	
}

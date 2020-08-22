package com.myschool.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myschool.entity.Assignments;
import com.myschool.entity.AttendanceList;
import com.myschool.entity.MessagesTable;
import com.myschool.entity.StudentsTable;
import com.myschool.models.request.AssignmentRequest;
import com.myschool.models.response.AttendanceResponse;
import com.myschool.models.response.MessagesResponse;
import com.myschool.repository.AssignmentsRepo;
import com.myschool.repository.AttendanceRepo;
import com.myschool.repository.MessagesRepo;
import com.myschool.repository.StudentsRepo;
import com.myschool.service.StudentsService;

@Service
public class StudentsServiceImpl implements StudentsService {

	@Autowired
	private AttendanceRepo attenListRepo;

	@Autowired
	private AssignmentsRepo assignmentRepo;

	@Autowired
	private StudentsRepo studentsRepo;
	
	@Autowired
	private MessagesRepo messageRepo;

	@Override
	public List<AttendanceResponse> getAttendance(String student_id) {
		List<AttendanceResponse> response = new ArrayList<AttendanceResponse>();
		List<AttendanceList> entities = attenListRepo.findStudentId(student_id);
		for (AttendanceList source : entities) {
			AttendanceResponse target = new AttendanceResponse();
			BeanUtils.copyProperties(source, target);
			response.add(target);
		}
		return response;
	}

	@Override
	public List<AssignmentRequest> getAssignments(String student_id) {
		List<AssignmentRequest> result = new ArrayList<AssignmentRequest>();
		StudentsTable entity = getClass(student_id);
		List<Assignments> assign = assignmentRepo.getItems(entity.getClas(), entity.getSection());
		for (Assignments source : assign) {
			AssignmentRequest target = new AssignmentRequest();
			BeanUtils.copyProperties(source, target);
			result.add(target);
		}
		return result;
	}

	private StudentsTable getClass(String student_id) {
		return studentsRepo.findFirstname(student_id);
	}

	@Override
	public List<MessagesResponse> getMessagesClass(int clas) {
		List<MessagesResponse> list = new ArrayList<MessagesResponse>();
		List<MessagesTable> entity = messageRepo.findByClass(clas);
		for(MessagesTable source:entity) {
			MessagesResponse target = new MessagesResponse();
			target.setMessage(source.getMessage());
			target.setCreated_on(source.getCreated_on());
			list.add(target);
		}
		return list;
	}

	@Override
	public int getClas(String student_id) {
		return studentsRepo.findclas(student_id);
	}

	@Override
	public List<MessagesResponse> getMessagesById(String student_id) {
		List<MessagesResponse> list = new ArrayList<MessagesResponse>();
		List<MessagesTable> entity = messageRepo.findByStudent(student_id);
		for(MessagesTable source:entity) {
			MessagesResponse target = new MessagesResponse();
			target.setMessage(source.getMessage());
			target.setCreated_on(source.getCreated_on());
			list.add(target);
		}
		return list;
	}
}

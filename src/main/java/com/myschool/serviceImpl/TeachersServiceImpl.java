package com.myschool.serviceImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myschool.entity.Assignments;
import com.myschool.entity.AttendanceList;
import com.myschool.models.request.AssignmentRequest;
import com.myschool.models.request.AttendanceRequest;
import com.myschool.repository.AssignmentsRepo;
import com.myschool.repository.AttendanceRepo;
import com.myschool.service.TeachersService;

@Service
public class TeachersServiceImpl implements TeachersService {

	@Autowired
	private AttendanceRepo attenListRepo;
	
	@Autowired
	private AssignmentsRepo assignmentRepo;

	@Override
	public String saveAttendance(AttendanceRequest request) {
		List<AttendanceList> entity = attenListRepo.findStudentId(request.getStudent_id());
		String response = null;
		if (!entity.isEmpty()) {
			boolean month = entity.stream().map(ls -> ls.getMonth()).filter(tm -> tm.equals(request.getMonth())).findFirst().isPresent();
			boolean year = entity.stream().map(ls -> ls.getYear()).filter(tm -> tm == request.getYear()).findFirst().isPresent();
			if (month && year) {
				response = "Student ID and Year already exist";
			} else if ((month && !year) || (!month && year)) {
				response = save(request);
			} 
		} else {
			response = save(request);
		}
		return response;
	}
	
	private String save(AttendanceRequest request) {
		AttendanceList attend = new AttendanceList();
		attend.setAbsent(request.getWorking_days() - request.getPresent());
		attend.setPresent(request.getPresent());
		attend.setMonth(request.getMonth());
		attend.setWorking_days(request.getWorking_days());
		attend.setStudent_id(request.getStudent_id());
		attend.setYear(request.getYear());
		attend.setPercentage(request.getPresent()*100/request.getWorking_days());
		attenListRepo.save(attend);
		return "Saved";
	}

	@Override
	public String saveAssginments(AssignmentRequest request) {
		Assignments entity = new Assignments();
		BeanUtils.copyProperties(request, entity);
		assignmentRepo.save(entity);
		return "Submited";
	}

}

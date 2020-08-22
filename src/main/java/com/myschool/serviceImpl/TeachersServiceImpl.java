package com.myschool.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myschool.entity.Assignments;
import com.myschool.entity.AttendanceList;
import com.myschool.entity.HolidayTable;
import com.myschool.entity.MessagesTable;
import com.myschool.entity.PerformanceTable;
import com.myschool.entity.RolesTable;
import com.myschool.entity.StudentsTable;
import com.myschool.entity.UsersTable;
import com.myschool.models.request.AssignmentRequest;
import com.myschool.models.request.AttendanceRequest;
import com.myschool.models.request.HolidayListRequest;
import com.myschool.models.request.MessagesRequest;
import com.myschool.models.request.PerformanceRequest;
import com.myschool.models.request.UserRequest;
import com.myschool.models.response.StudentsList;
import com.myschool.repository.AssignmentsRepo;
import com.myschool.repository.AttendanceRepo;
import com.myschool.repository.HolidayRepo;
import com.myschool.repository.MessagesRepo;
import com.myschool.repository.PerformanceRepo;
import com.myschool.repository.StudentsRepo;
import com.myschool.repository.UsersRepo;
import com.myschool.service.TeachersService;
import com.myschool.utils.ResponseConstants;

@Service
public class TeachersServiceImpl implements TeachersService {

	@Autowired
	private AttendanceRepo attenListRepo;

	@Autowired
	private AssignmentsRepo assignmentRepo;

	@Autowired
	private MessagesRepo messageRepo;

	@Autowired
	private StudentsRepo studentsRepo;

	@Autowired
	private PerformanceRepo performanceRepo;

	@Autowired
	private HolidayRepo holidayRepo;

	@Autowired
	private UsersRepo usersRepo;

	@Override
	public String saveAttendance(AttendanceRequest request) {
		List<AttendanceList> entity = attenListRepo.findStudentId(request.getStudent_id());
		String response = null;
		if (!entity.isEmpty()) {
			boolean month = entity.stream().map(ls -> ls.getMonth()).filter(tm -> tm.equals(request.getMonth()))
					.findFirst().isPresent();
			boolean year = entity.stream().map(ls -> ls.getYear()).filter(tm -> tm == request.getYear()).findFirst()
					.isPresent();
			if (month && year) {
				response = "Data already exist for student for this year and month";
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
		attend.setPercentage(request.getPresent() * 100 / request.getWorking_days());
		attend.setCreated_by(request.getCreated_by());
		attenListRepo.save(attend);
		return ResponseConstants.saved;
	}

	@Override
	public String saveAssginments(AssignmentRequest request) {
		String response = null;
		Assignments entity = assignmentRepo.findItem(request.getClas(), request.getDescription(), request.getDueDate(),
				request.getSection(), request.getSubject());
		if (entity != null) {
			response = "Duplicate Assignment Request!";
		} else {
			Assignments entity1 = new Assignments();
			BeanUtils.copyProperties(request, entity1);
			assignmentRepo.save(entity1);
			response = ResponseConstants.submited;
		}
		return response;
	}

	@Override
	public String saveMessages(MessagesRequest request) {
		String response = null;
		List<MessagesTable> entity = null;
		if (request.getClas() != 0) {
			entity = messageRepo.findByClass(request.getClas());
			response = message(entity, request);
		} else if (request.getStudent_id() != null) {
			entity = messageRepo.findByStudent(request.getStudent_id());
			response = message(entity, request);
		}
		return response;
	}

	public String message(List<MessagesTable> entity, MessagesRequest request) {
		String response = null;
		if (!entity.isEmpty()) {
			boolean message = entity.stream().map(ls -> ls.getMessage()).filter(tm -> tm.equals(request.getMessage()))
					.findFirst().isPresent();
			if (message) {
				response = "Duplicate Message!";
			} else {
				MessagesTable entitys = new MessagesTable();
				BeanUtils.copyProperties(request, entitys);
				messageRepo.save(entitys);
				response = ResponseConstants.messages;
			}
		} else {
			MessagesTable entitys = new MessagesTable();
			BeanUtils.copyProperties(request, entitys);
			messageRepo.save(entitys);
			response = ResponseConstants.messages;
		}
		return response;
	}

	@Override
	public List<StudentsList> getStudentList(String studentId) {
		List<StudentsList> response = new ArrayList<StudentsList>();
		List<StudentsTable> studentList = studentsRepo.findByStudentsID(studentId);
		for (StudentsTable source : studentList) {
			StudentsList target = new StudentsList();
			BeanUtils.copyProperties(source, target);
			response.add(target);
		}
		return response;
	}

	@Override
	public String setPerformance(PerformanceRequest request) {
		String response = null;
		PerformanceTable table = performanceRepo.findPerformances(request.getStudent_id(), request.getYear(),
				request.getExam());
		if (table == null) {
			PerformanceTable entity = new PerformanceTable();
			BeanUtils.copyProperties(request, entity);
			performanceRepo.save(entity);
			response = ResponseConstants.saved;
		} else {
			response = ResponseConstants.alreadyExist;
		}
		return response;
	}

	@Transactional
	@Override
	public String SetHoliday(HolidayListRequest request) {
		List<String> event = request.getEvent();
		List<Date> date = request.getHoliday();
		for (int i = 0; i < event.size(); i++) {
			HolidayTable table = holidayRepo.findDate(date.get(i));
			if (table != null) {
				holidayRepo.updateEvent(event.get(i), date.get(i));
			} else {
				HolidayTable entity = new HolidayTable();
				entity.setHoliday(date.get(i));
				entity.setEvent(event.get(i));
				holidayRepo.save(entity);
			}
		}
		return ResponseConstants.saved;
	}

	@Transactional
	@Override
	public String saveUser(UserRequest user) {
		String response = null;
		ModelMapper mapper = new ModelMapper();
		java.lang.reflect.Type source = new TypeToken<UsersTable>() {
		}.getType();
		UsersTable userEntity = mapper.map(user, source);
		if (usersRepo.findusername(user.getUsername()) != null) {
			response = "Username already exist try different name!";
		} else {
			usersRepo.save(userEntity);
			response = ResponseConstants.saved;
		}
		return response;
	}

	@Override
	public String getRole(String username) {
		String role = null;
		UsersTable entity = usersRepo.findusername(username);
		Iterator<RolesTable> set = entity.getRoles().iterator();
		while(set.hasNext()) {
			role = set.next().getRole();
		}
		return role;
	}

	@Override
	public String getfistName(String id) {
		StudentsTable entity = studentsRepo.findFirstname(id);
		return entity.getFirst_name()+ " "+entity.getLast_name();
	}

}

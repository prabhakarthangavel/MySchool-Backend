package com.myschool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myschool.entity.AttendanceList;

@Repository
public interface AttendanceRepo extends CrudRepository<AttendanceList, Integer> {

	@Query(value = "select * from attendance where student_id = ?1", nativeQuery = true)
	List<AttendanceList> findStudentId(String student_id);

}

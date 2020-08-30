package com.myschool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myschool.entity.PerformanceTable;

@Repository
public interface PerformanceRepo extends CrudRepository<PerformanceTable, Integer> {
	@Query(value = "select * from performances where student_id = ?1 and year = ?2 and exam = ?3" ,nativeQuery = true)
	PerformanceTable findPerformances(String studentId, int year, String exam);
	
	@Query(value = "select * from performances where student_id = ?1" ,nativeQuery = true)
	List<PerformanceTable> findByStudent(String student_id);
}

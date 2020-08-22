package com.myschool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myschool.entity.StudentsTable;

@Repository
public interface StudentsRepo extends CrudRepository<StudentsTable, Integer> {
	
	@Query(value = "select * from students where student_id like ?1%", nativeQuery = true)
	List<StudentsTable> findByStudentsID(String studentId);

	@Query(value = "select * from students where student_id = ?1", nativeQuery = true)
	StudentsTable findFirstname(String id);
	
	@Query(value = "select class from students where student_id = ?1", nativeQuery = true)
	int findclas(String id);
}

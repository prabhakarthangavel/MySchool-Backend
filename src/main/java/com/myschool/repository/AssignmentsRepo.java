package com.myschool.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myschool.entity.Assignments;

@Repository
public interface AssignmentsRepo extends CrudRepository<Assignments, Integer> {

	@Query(value = "select * from assignments where class = ?1 and description = ?2 and due_date = ?3 and section = ?4 and subject =?5", nativeQuery = true)
	Assignments findItem(String clas, String description, String due_date, String section, String subject);
}

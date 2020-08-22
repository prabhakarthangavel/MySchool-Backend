package com.myschool.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myschool.entity.Assignments;

@Repository
public interface AssignmentsRepo extends CrudRepository<Assignments, Integer> {

	@Query(value = "select * from assignments where class = ?1 and description = ?2 and due_date = ?3 and section = ?4 and subject =?5", nativeQuery = true)
	Assignments findItem(int clas, String description, Date due_date, String section, String subject);

	@Query(value = "select * from assignments where class = ?1 and section = ?2 order by due_date desc", nativeQuery = true)
	List<Assignments> getItems(int clas, String section);
}

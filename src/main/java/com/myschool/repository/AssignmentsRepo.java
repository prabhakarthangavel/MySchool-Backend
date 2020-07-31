package com.myschool.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myschool.entity.Assignments;

@Repository
public interface AssignmentsRepo extends CrudRepository<Assignments, Integer> {
	
}

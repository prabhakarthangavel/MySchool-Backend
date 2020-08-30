package com.myschool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myschool.entity.ClassTable;

@Repository
public interface ClassesRepo extends CrudRepository<ClassTable, Integer> {

	@Query(value = "select * from classes where class = ?1", nativeQuery = true)
	ClassTable findByClass(int classes);

	@Query(value = "select class from classes", nativeQuery = true)
	List<Integer> findClasses();

	@Query(value= "select subjects from classes where class = ?1", nativeQuery = true)
	String findSubjects(int clas);
}

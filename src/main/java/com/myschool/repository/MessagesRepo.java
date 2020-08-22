package com.myschool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myschool.entity.MessagesTable;

@Repository
public interface MessagesRepo extends CrudRepository<MessagesTable, Integer> {

	@Query(value = "select * from messages where class = ?1", nativeQuery = true)
	List<MessagesTable> findByClass(int clas);

	@Query(value = "select * from messages where student_id = ?1", nativeQuery = true)
	List<MessagesTable> findByStudent(String studentId);

}

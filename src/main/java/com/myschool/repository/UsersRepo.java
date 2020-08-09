package com.myschool.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myschool.entity.UsersTable;

@Repository
public interface UsersRepo extends CrudRepository<UsersTable, Integer> {
	
	@Query(value = "select * from users where username = ?1", nativeQuery = true)
	UsersTable findusername(String username);
}

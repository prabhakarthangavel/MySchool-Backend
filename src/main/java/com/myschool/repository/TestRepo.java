package com.myschool.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myschool.entity.Test;

@Repository
public interface TestRepo extends CrudRepository<Test, Integer> {

}

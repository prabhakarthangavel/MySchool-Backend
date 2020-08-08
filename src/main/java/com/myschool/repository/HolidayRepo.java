package com.myschool.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myschool.entity.HolidayTable;

@Repository
public interface HolidayRepo extends CrudRepository<HolidayTable, Integer> {

	@Query(value = "select * from holiday where holiday = ?1", nativeQuery = true)
	HolidayTable findDate(Date date);

	@Modifying(clearAutomatically = true)
	@Query(value = "update holiday set event = ?1 where holiday =?2", nativeQuery = true)
	void updateEvent(String string, Date date);

}

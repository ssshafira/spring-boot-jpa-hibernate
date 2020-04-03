package com.lawencon.app.dao.impl.jpa;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.app.model.CheckIn;

@Repository
public interface CheckInRepo extends JpaRepository<CheckIn, Integer> {

	@Query("Select i from CheckIn i where plat = ?1 and timeIn = ?2")
	public List<CheckIn> findByPlatAndDate(String plat, Date time);
}

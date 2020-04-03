package com.lawencon.app.dao.impl.jpa;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.app.model.CheckOut;

@Repository
public interface CheckOutRepo extends JpaRepository<CheckOut, Integer> {

	@Query("select timeIn from CheckIn where idIn = ?1")
	abstract Date findDateInById(Integer id);
}

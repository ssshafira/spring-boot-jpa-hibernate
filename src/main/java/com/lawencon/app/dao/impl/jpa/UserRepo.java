package com.lawencon.app.dao.impl.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.app.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

	@Query("select count(i) from User i where uname = ?1 and pwd = ?2")
	public Long cekValid(String uname, String pwd);
}

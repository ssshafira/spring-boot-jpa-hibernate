package com.lawencon.app.dao.impl.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.app.dao.UserDao;

@Repository("user_repo_jpa")
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private UserRepo uRepo;

	@Override
	public Long cekValid(String uname, String pwd) {
		return uRepo.cekValid(uname, pwd);
	}

}

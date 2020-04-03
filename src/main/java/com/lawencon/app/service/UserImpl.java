package com.lawencon.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.app.dao.UserDao;

@Service
@Transactional
public class UserImpl implements UserService {
	
	@Autowired
	@Qualifier("user_repo_jpa") //jika pingin ganti jpa ganti qualifiernya jadi mhs_repo_jpa
	private UserDao ud;

	@Override
	public Long cekValid(String uname, String pwd) {
		return ud.cekValid(uname, pwd);
	}

	
}

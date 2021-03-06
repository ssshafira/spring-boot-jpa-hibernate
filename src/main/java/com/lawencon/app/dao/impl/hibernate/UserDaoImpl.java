package com.lawencon.app.dao.impl.hibernate;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.app.dao.UserDao;

@Repository("user_repo_hibernate")
public class UserDaoImpl extends CustomRepo implements UserDao {

	@Override
	public Long cekValid(String uname, String pwd) {
		Query q = em.createQuery("select count(*) from User where uname =: uParam and pwd =: pParam");
		q.setParameter("uParam", uname);
		q.setParameter("pParam", pwd);
		return (Long) q.getSingleResult();
	}

}

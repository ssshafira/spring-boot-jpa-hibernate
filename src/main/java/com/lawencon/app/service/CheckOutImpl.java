package com.lawencon.app.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.app.dao.CheckOutDao;
import com.lawencon.app.dao.UserDao;
import com.lawencon.app.dao.impl.hibernate.CustomRepo;
import com.lawencon.app.model.CheckOut;

@Service
public class CheckOutImpl extends CustomRepo implements CheckOutService {

	@Autowired
	@Qualifier("cekout_repo_jpa") // jika pingin ganti jpa ganti qualifiernya jadi mhs_repo_jpa
	private CheckOutDao coDao;

	@Autowired
	UserDao uDao;

	@Override
	public List<CheckOut> findAll(String au0, String au1) throws Exception {
		if (uDao.cekValid(au0, au1) == 1)
			return coDao.findAll();
		else
			return null;
	}

	@Override
	public CheckOut findById(Integer id) {
		return coDao.findById(id);
	}

	@Override
	public Date findDateInById(Integer id) {
		return coDao.findDateInById(id);
	}

	@Override
	@Transactional
	public boolean insert(CheckOut co, String au0, String au1) throws Exception {
		if (uDao.cekValid(au0, au1) == 1)
			return coDao.insert(co);
		else
			return false;
	}

	@Override
	@Transactional
	public void update(CheckOut co, String au0, String au1) throws Exception {
		if (uDao.cekValid(au0, au1) == 1)
			coDao.update(co);
	}

	@Override
	@Transactional
	public void delete(CheckOut co, String au0, String au1) throws Exception {
		if (uDao.cekValid(au0, au1) == 1)
			coDao.delete(co);
	}

}

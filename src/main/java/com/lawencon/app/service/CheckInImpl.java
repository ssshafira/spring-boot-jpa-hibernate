package com.lawencon.app.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.app.dao.CheckInDao;
import com.lawencon.app.dao.UserDao;
import com.lawencon.app.dao.impl.hibernate.CustomRepo;
import com.lawencon.app.model.CheckIn;

@Service
@Transactional
public class CheckInImpl extends CustomRepo implements CheckInService {

	@Autowired
	@Qualifier("cekin_repo_jpa") // jika pingin ganti jpa ganti qualifiernya jadi mhs_repo_jpa
	private CheckInDao ciDao;

	@Autowired
	private UserDao uDao;

	@Override
	public List<CheckIn> findAll(String au0, String au1) throws Exception {
		if (uDao.cekValid(au0, au1) == 1)
			return ciDao.findAll();
		else
			return null;
	}

	@Override
	public List<CheckIn> findByPlatAndDate(String plat, Date time) {
		return ciDao.findByPlatAndDate(plat, time);
	}

	@Override
	@Transactional
	public boolean insert(CheckIn ci, String au0, String au1) throws Exception {
		if (uDao.cekValid(au0, au1) == 1)
			return ciDao.insert(ci);
		else
			return false;
	}

	@Override
	@Transactional
	public void update(CheckIn ci, String au0, String au1) throws Exception {
		if (uDao.cekValid(au0, au1) == 1)
			ciDao.update(ci);
	}

	@Override
	@Transactional
	public void delete(CheckIn ci, String au0, String au1) throws Exception {
		if (uDao.cekValid(au0, au1) == 1)
			ciDao.delete(ci);
	}

	@Override
	public CheckIn findById(Integer id) {
		return ciDao.findById(id);
	}

}

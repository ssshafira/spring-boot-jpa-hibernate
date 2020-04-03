package com.lawencon.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.app.dao.JenisKendaraanDao;
import com.lawencon.app.dao.UserDao;
import com.lawencon.app.model.JenisKendaraan;

@Service
@Transactional
public class JenisKendaraanImpl implements JenisKendaraanService {

	@Autowired
	@Qualifier("jenis_repo_jpa")
	private JenisKendaraanDao jkDao;

	@Autowired
	private UserDao uDao;

	@Override
	public List<JenisKendaraan> findAll(String au0, String au1) throws Exception {
		if (uDao.cekValid(au0, au1) == 1)
			return jkDao.findAll();
		else
			return null;
	}

	@Override
	public void insert(JenisKendaraan jk, String au0, String au1) throws Exception {
		if (uDao.cekValid(au0, au1) == 1)
			jkDao.insert(jk);
	}

}

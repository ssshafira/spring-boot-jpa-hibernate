package com.lawencon.app.dao.impl.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.app.dao.JenisKendaraanDao;
import com.lawencon.app.model.JenisKendaraan;

@Repository("jenis_repo_jpa")
public class JenisKendaraanDaoImpl implements JenisKendaraanDao {

	@Autowired
	private JenisKendaraanRepo jkRepo;
	
	@Override
	public List<JenisKendaraan> findAll() throws Exception {
		return jkRepo.findAll();
	}
	
	@Override
	public void insert(JenisKendaraan jk) throws Exception {
		jkRepo.save(jk);
	}

}

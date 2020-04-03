package com.lawencon.app.dao.impl.jpa;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.app.dao.CheckOutDao;
import com.lawencon.app.dao.impl.hibernate.CustomRepo;
import com.lawencon.app.model.CheckOut;
import com.lawencon.app.service.CheckOutService;

@Repository("cekout_repo_jpa")
public class CheckOutDaoImpl extends CustomRepo implements CheckOutDao {
	
	@Autowired
	private CheckOutRepo coRepo;
	
	@Autowired
	private CheckOutService coService;

	@Override
	public List<CheckOut> findAll() throws Exception {
		return coRepo.findAll();
	}

	@Override
	public CheckOut findById(Integer id) {
		return coRepo.findById(id).orElse(null);
	}

	@Override
	public Date findDateInById(Integer id) {
		return coRepo.findDateInById(id);
	}

	@Override
	public boolean insert(CheckOut co) throws Exception {
		boolean stat = false;
		Date dt = coService.findDateInById(co.getCheckIn().getIdIn());
		if (dt.before(co.getTimeOut())) {
			CheckOut cek = new CheckOut();
			cek.setCheckIn(co.getCheckIn());
			cek.setTimeOut(co.getTimeOut());
			em.persist(cek);
			stat = true;
		} else stat = false;
		return stat;
	}

	@Override
	public void update(CheckOut co) throws Exception {
		CheckOut cek = new CheckOut();
		cek = findById(co.getIdOut());
		cek.setCheckIn(co.getCheckIn());
		cek.setTimeOut(co.getTimeOut());
		em.merge(cek);
	}

	@Override
	public void delete(CheckOut co) throws Exception {
		CheckOut cek = new CheckOut();
		cek = findById(co.getIdOut());
		em.remove(cek);
	}
	
	

}

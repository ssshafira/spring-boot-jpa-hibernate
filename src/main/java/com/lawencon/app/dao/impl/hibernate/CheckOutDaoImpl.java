package com.lawencon.app.dao.impl.hibernate;

import java.sql.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.app.dao.CheckOutDao;
import com.lawencon.app.model.CheckIn;
import com.lawencon.app.model.CheckOut;
import com.lawencon.app.service.CheckOutService;

@Repository("cekout_repo_hibernate")
public class CheckOutDaoImpl extends CustomRepo implements CheckOutDao {

	@Autowired
	private CheckOutService coService;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CheckOut> findAll() throws Exception {
		Query q = em.createQuery(" from CheckOut");
		return q.getResultList();
	}

	@Override
	public CheckOut findById(Integer id) {
		Query q = em.createQuery(" from CheckOut where idOut =:idParam");
		q.setParameter("idParam", id);
		return (CheckOut) q.getResultList();
	}

	@Override
	public Date findDateInById(Integer id) {
		Query q = em.createQuery("select timeIn from CheckIn where idIn =:idParam");
		q.setParameter("idParam", id);
		return (Date) q.getSingleResult();
	}

	@Override
	public boolean insert(CheckOut co) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(CheckOut co) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CheckOut co) throws Exception {
		// TODO Auto-generated method stub
		
	}

}

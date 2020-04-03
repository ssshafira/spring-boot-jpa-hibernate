package com.lawencon.app.dao.impl.hibernate;

import java.sql.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.app.dao.CheckOutDao;
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
		return (CheckOut) q.getSingleResult();
	}

	@Override
	public Date findDateInById(Integer id) {
		Query q = em.createQuery("select timeIn from CheckIn where idIn =:idParam");
		q.setParameter("idParam", id);
		return (Date) q.getSingleResult();
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
		} else
			stat = false;
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

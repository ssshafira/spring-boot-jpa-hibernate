package com.lawencon.app.dao.impl.hibernate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.app.dao.CheckInDao;
import com.lawencon.app.model.CheckIn;
import com.lawencon.app.service.CheckInService;

@Repository("cekin_repo_hibernate")
public class CheckInDaoImpl extends CustomRepo implements CheckInDao {
	
	@Autowired
	private CheckInService ciService;

	@SuppressWarnings("unchecked")
	@Override
	public List<CheckIn> findAll() throws Exception {
		Query q = em.createQuery(" from CheckIn");
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CheckIn> findByPlatAndDate(String plat, Date time) {
		Query q = em.createQuery(" from CheckIn where plat =:platParam and timeIn =:timeParam");
		q.setParameter("platParam", plat);
		q.setParameter("timeParam", time);
		return q.getResultList();
	}

	@Override
	public CheckIn findById(Integer id) {
		Query q = em.createQuery(" from CheckIn where idIn =:idParam");
		q.setParameter("idParam", id);
		return (CheckIn) q.getSingleResult();
	}

	@Override
	public boolean insert(CheckIn ci) throws Exception {
		boolean stat = false;
		List<CheckIn> listData = new ArrayList<CheckIn>();
		listData = ciService.findByPlatAndDate(ci.getPlat(), ci.getTimeIn());
		if (listData.isEmpty()) {
			if (cekPlat(ci.getPlat())) {
				CheckIn cek = new CheckIn();
				cek.setJenis(ci.getJenis());
				cek.setPlat(ci.getPlat());
				cek.setTimeIn(ci.getTimeIn());
				em.persist(cek);
				stat = true;
			} else stat = false;
		} else stat = false;
		return stat;
	}

	@Override
	public void update(CheckIn ci) throws Exception {
		List<CheckIn> listData = new ArrayList<CheckIn>();
		listData = ciService.findByPlatAndDate(ci.getPlat(), ci.getTimeIn());		
		if (listData.isEmpty()) {
			if (cekPlat(ci.getPlat())) {
				CheckIn cek = new CheckIn();
				cek = findById(ci.getIdIn());
				cek.setJenis(ci.getJenis());
				cek.setPlat(ci.getPlat());
				cek.setTimeIn(ci.getTimeIn());
				em.merge(cek);
			}
		}	
	}

	@Override
	public void delete(CheckIn ci) throws Exception {
		CheckIn cek = new CheckIn();
		cek = findById(ci.getIdIn());
		em.remove(cek);
	}

	public static boolean isParsable(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean cekPlat(String plat) {
		boolean stat = false;
		String[] tempPlat;
		tempPlat = plat.split(" ");
		if (tempPlat[0].equalsIgnoreCase("B") && (tempPlat.length == 3)) {
			if (tempPlat[1].length() > 0 && tempPlat[1].length() <= 4 && isParsable(tempPlat[1])) {
				char[] chars = tempPlat[2].toCharArray();
				for (char c : chars) {
					if (Character.isDigit(c)) {
						stat = false;
						break;
					} else
						stat = true;
				}
				if (tempPlat[2].length() > 0 && tempPlat[2].length() <= 3 && stat) {
					return stat;
				} else {
					stat = false;
					return stat;
				}
			}
		}
		return stat;
	}
}

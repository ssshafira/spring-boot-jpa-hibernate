package com.lawencon.app.service;

import java.sql.Date;
import java.util.List;

import com.lawencon.app.model.CheckOut;

public interface CheckOutService {

	abstract List<CheckOut> findAll(String au0, String au1) throws Exception;
	
	abstract CheckOut findById(Integer id);
	abstract Date findDateInById(Integer id);
	
	abstract boolean insert(CheckOut co, String au0, String au1) throws Exception;
	abstract void update(CheckOut co, String au0, String au1) throws Exception;
	abstract void delete(CheckOut co, String au0, String au1) throws Exception;
}

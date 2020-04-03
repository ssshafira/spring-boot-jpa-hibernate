package com.lawencon.app.dao;

import java.sql.Date;
import java.util.List;

import com.lawencon.app.model.CheckOut;

public interface CheckOutDao {

abstract List<CheckOut> findAll() throws Exception;
	
	abstract CheckOut findById(Integer id);
	abstract Date findDateInById(Integer id);
	
	abstract boolean insert(CheckOut co) throws Exception;
	abstract void update(CheckOut co) throws Exception;
	abstract void delete(CheckOut co) throws Exception;
}

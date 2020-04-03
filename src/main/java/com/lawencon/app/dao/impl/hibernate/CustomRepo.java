package com.lawencon.app.dao.impl.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class CustomRepo {

	@PersistenceContext
	protected EntityManager em;

}

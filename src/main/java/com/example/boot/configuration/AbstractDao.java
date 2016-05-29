package com.example.boot.configuration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author gimbyeongsu
 * 
 */
public abstract class AbstractDao {
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		// return sessionFactory.openSession();
		return sessionFactory.getCurrentSession();
	}
}
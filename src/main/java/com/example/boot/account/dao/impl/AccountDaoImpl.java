package com.example.boot.account.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.example.boot.account.dao.AccountDao;
import com.example.boot.account.model.Account;
import com.example.boot.configuration.AbstractDao;

/**
 * @author gimbyeongsu
 * 
 */
@Repository("accountDao")
public class AccountDaoImpl extends AbstractDao implements AccountDao {

	@Override
	public Account findById(int id) {
		return getSession().get(Account.class, id);
	}

	@Override
	public void save(Account entity) {
		getSession().save(entity);
	}

	@Override
	public void persist(Account entity) {
		getSession().persist(entity);
	}

	@Override
	public void delete(Account entity) {
		getSession().delete(entity);
	}

	@Override
	public Criteria createEntityCriteria() {
		return getSession().createCriteria(Account.class);
	}
}

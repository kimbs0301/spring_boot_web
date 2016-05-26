package com.example.boot.account.dao;

import org.hibernate.Criteria;

import com.example.boot.account.model.Account;

public interface AccountDao {
	public Account findById(int id);
	
	public void save(Account entity);
	
	public void persist(Account entity);
	
	public void delete(Account entity);
	
	public Criteria createEntityCriteria();
}

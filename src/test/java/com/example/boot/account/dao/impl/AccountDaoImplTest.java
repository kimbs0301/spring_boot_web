package com.example.boot.account.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.boot.account.dao.AccountDao;
import com.example.boot.account.model.Account;
import com.example.config.test.JunitConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { JunitConfig.class })
@WebAppConfiguration
@ActiveProfiles(profiles = { "junit" })
@TestPropertySource(locations = "classpath:application-junit.properties")
@Transactional
public class AccountDaoImplTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountDaoImplTest.class);
	
	@Autowired
	private AccountDao accountDao;

	@Test
	public void test_get() throws Exception {
		Account entity = new Account();
		entity.setName("test");
		entity.setSsn("1");
		accountDao.save(entity);

		Account account = accountDao.findById(18);
		LOGGER.debug("{}", account);
	}

	@Test
	@Rollback(true)
	public void test_save() throws Exception {
		Account entity = new Account();
		entity.setName("test");
		entity.setSsn("1");
		accountDao.save(entity);
	}
}

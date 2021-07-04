package com.example.doordelights.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.example.doordelights.dao.UserAccountDao;
import com.example.doordelights.entity.UserAccount;
@Component
public class UserAccountServiceImpl implements UserAccountService{
	@Resource 
	UserAccountDao userAccountDao;
	@Override
	public List<UserAccount> findAll() {
		return userAccountDao.findAll();
	}
	@Override
	public void insertUserAccount(UserAccount userAccount) {
		userAccountDao.createCustomerUserAccount(userAccount);
		
	}
	@Override
	public void updateUserAccount(UserAccount userAccount) {
		userAccountDao.updateUserAccount(userAccount);
		
	}
	@Override
	public void executeUpdateUserAccount(UserAccount userAccount) {
		userAccountDao.executeUpdateUserAccount(userAccount);
		
	}

	@Override
	public void deleteUserAccount(UserAccount userAccount) {
		userAccountDao.deleteUserAccount(userAccount);
		
	}
}
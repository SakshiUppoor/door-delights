package com.example.doordelights.service;

import java.util.List;

import com.example.doordelights.entity.UserAccount;

public interface UserAccountService {
	List<UserAccount> findAll();

	void insertUserAccount(UserAccount userAccount);

	void updateUserAccount(UserAccount userAccount);

	void executeUpdateUserAccount(UserAccount userAccount);

	void deleteUserAccount(UserAccount userAccount);
	
}
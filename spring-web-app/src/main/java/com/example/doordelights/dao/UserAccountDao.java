package com.example.doordelights.dao;

import java.util.List;

import com.example.doordelights.entity.UserAccount;

public interface UserAccountDao {

	List<UserAccount> findAll();

	void createCustomerUserAccount(UserAccount userAccount);

	void updateUserAccount(UserAccount userAccount);

	void executeUpdateUserAccount(UserAccount userAccount);

	public void deleteUserAccount(UserAccount userAccount);
}
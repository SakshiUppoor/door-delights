package com.example.doordelights.service;

import java.util.List;

import com.example.doordelights.entity.MenuItem;

public interface MenuItemService {
	List<MenuItem> findAll();

	void insertMenuItem(MenuItem menuItem);

	void updateMenuItem(MenuItem menuItem);

	void executeUpdateMenuItem(MenuItem menuItem);

	void deleteMenuItem(MenuItem menuItem);
	
}
package com.example.doordelights.dao;

import java.util.List;

import com.example.doordelights.entity.MenuItem;

public interface MenuItemDao {

	List<MenuItem> findAll();

	void insertMenuItem(MenuItem menuItem);

	void updateMenuItem(MenuItem menuItem);

	void executeUpdateMenuItem(MenuItem menuItem);

	public void deleteMenuItem(MenuItem menuItem);
}
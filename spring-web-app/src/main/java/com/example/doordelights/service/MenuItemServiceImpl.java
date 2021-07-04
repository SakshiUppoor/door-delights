package com.example.doordelights.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.example.doordelights.dao.MenuItemDao;
import com.example.doordelights.entity.MenuItem;
@Component
public class MenuItemServiceImpl implements MenuItemService{
	@Resource 
	MenuItemDao menuItemDao;
	@Override
	public List<MenuItem> findAll() {
		return menuItemDao.findAll();
	}
	@Override
	public void insertMenuItem(MenuItem menuItem) {
		menuItemDao.insertMenuItem(menuItem);
		
	}
	@Override
	public void updateMenuItem(MenuItem menuItem) {
		menuItemDao.updateMenuItem(menuItem);
		
	}
	@Override
	public void executeUpdateMenuItem(MenuItem menuItem) {
		menuItemDao.executeUpdateMenuItem(menuItem);
		
	}

	@Override
	public void deleteMenuItem(MenuItem menuItem) {
		menuItemDao.deleteMenuItem(menuItem);
		
	}
}
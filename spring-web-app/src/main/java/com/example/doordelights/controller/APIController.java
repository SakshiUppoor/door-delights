package com.example.doordelights.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.doordelights.entity.MenuItem;
import com.example.doordelights.service.MenuItemService;

import com.example.doordelights.entity.UserAccount;
import com.example.doordelights.service.UserAccountService;

@RestController
@RequestMapping("/api")
public class APIController {

	@Resource 
	MenuItemService menuItemService;
	
	
	@GetMapping(value = "/menuItemsList")
	public List<MenuItem> getMenuItems() {
		return menuItemService.findAll();
	
	}
	
	@PostMapping(value = "/createMenuItem")
	public void createMenuItem(@RequestBody MenuItem menuItem) {
		 menuItemService.insertMenuItem(menuItem);
	
	}
	@PutMapping(value = "/updateMenuItem")
	public void updateMenuItem(@RequestBody MenuItem menuItem) {
		 menuItemService.updateMenuItem(menuItem);
	
	}
	@PutMapping(value = "/executeUpdateMenuItem")
	public void executeUpdateMenuItem(@RequestBody MenuItem menuItem) {
		 menuItemService.executeUpdateMenuItem(menuItem);
	
	}
	
	@DeleteMapping(value = "/deleteMenuItemById")
	public void deleteMenuItem(@RequestBody MenuItem menuItem) {
		 menuItemService.deleteMenuItem(menuItem);
	
	}
	
	@Resource 
	UserAccountService userAccountService;
	
	
	@GetMapping(value = "/userAccountsList")
	public List<UserAccount> getUserAccounts() {
		return userAccountService.findAll();
	
	}
	
	@PostMapping(value = "/createUserAccount")
	public String createUserAccount(@RequestBody UserAccount userAccount) {
		try {
			userAccountService.insertUserAccount(userAccount);
			return "User created successfully";
		} catch (Exception e) {
			return e.getMessage();
		}
	
	}
	@PutMapping(value = "/updateUserAccount")
	public void updateUserAccount(@RequestBody UserAccount userAccount) {
		 userAccountService.updateUserAccount(userAccount);
	
	}
	@PutMapping(value = "/executeUpdateUserAccount")
	public void executeUpdateUserAccount(@RequestBody UserAccount userAccount) {
		 userAccountService.executeUpdateUserAccount(userAccount);
	
	}
	
	@DeleteMapping(value = "/deleteUserAccountById")
	public void deleteUserAccount(@RequestBody UserAccount userAccount) {
		 userAccountService.deleteUserAccount(userAccount);
	
	}
	
	
}
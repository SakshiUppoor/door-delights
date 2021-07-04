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

@Controller
@RequestMapping("/")
public class ApplicationController {
	
	@Resource 
	MenuItemService menuItemService;

	@GetMapping("/")
	public String greeting(Model model) {
		model.addAttribute("items", menuItemService.findAll());
		return "index";
	}
}
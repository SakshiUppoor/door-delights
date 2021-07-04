package com.example.doordelights.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.doordelights.entity.MenuItem;

public class MenuItemRowMapper implements RowMapper<MenuItem> {

	@Override
	public MenuItem mapRow(ResultSet rs, int arg1) throws SQLException {
		MenuItem menuItem = new MenuItem();
		menuItem.setMenuItemId(rs.getString("id"));
		menuItem.setMenuItemName(rs.getString("name"));
		menuItem.setMenuItemDescription(rs.getString("description"));
		menuItem.setMenuItemPhoto(rs.getString("photo"));
		menuItem.setMenuItemType(rs.getString("meal_type"));
 
        return menuItem;
	}


}
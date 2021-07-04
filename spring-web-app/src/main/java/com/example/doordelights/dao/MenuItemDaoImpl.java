package com.example.doordelights.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.doordelights.entity.MenuItem;
import com.example.doordelights.mapper.MenuItemRowMapper;
@Repository
public class MenuItemDaoImpl implements MenuItemDao{
	
	public MenuItemDaoImpl(NamedParameterJdbcTemplate template) {  
        this.template = template;  
}  
	NamedParameterJdbcTemplate template;  

	@Override
	public List<MenuItem> findAll() {
		return template.query("select * from menu_item", new MenuItemRowMapper());
	}
	@Override
	public void insertMenuItem(MenuItem menuItem) {
		 final String sql = "insert into menu_item(name , photo,description, meal_type) values(:name,:description,:photo,:meal_type)";
		 
	        KeyHolder holder = new GeneratedKeyHolder();
	        SqlParameterSource param = new MapSqlParameterSource()
					.addValue("name", menuItem.getMenuItemName())
					.addValue("description", menuItem.getMenuItemDescription())
					.addValue("photo", menuItem.getMenuItemPhoto())
					.addValue("meal_type", menuItem.getMenuItemType());
	        template.update(sql,param, holder);
	 
	}
	
	@Override
	public void updateMenuItem(MenuItem menuItem) {
		 final String sql = "update menu_item set name=:name, photo=:photo, description=:description, meal_type=:meal_type where id=:id";
		 
	        KeyHolder holder = new GeneratedKeyHolder();
	        SqlParameterSource param = new MapSqlParameterSource()
					.addValue("id", menuItem.getMenuItemId())
					.addValue("name", menuItem.getMenuItemName())
					.addValue("description", menuItem.getMenuItemDescription())
					.addValue("photo", menuItem.getMenuItemPhoto())
					.addValue("meal_type", menuItem.getMenuItemType());
	        template.update(sql,param, holder);
	 
	}
	
	@Override
	public void executeUpdateMenuItem(MenuItem menuItem) {
		 final String sql = "update menu_item set name=:name, photo=:photo, description=:description, meal_type=:meal_type where id=:id";
			 

		 Map<String,Object> map=new HashMap<String,Object>();  
		 map.put("id", menuItem.getMenuItemId());
		 map.put("name", menuItem.getMenuItemName());
		 map.put("description", menuItem.getMenuItemDescription());
		 map.put("photo", menuItem.getMenuItemPhoto());
		 map.put("meal_type", menuItem.getMenuItemType());
	
		 template.execute(sql,map,new PreparedStatementCallback<Object>() {  
			    @Override  
			    public Object doInPreparedStatement(PreparedStatement ps)  
			            throws SQLException, DataAccessException {  
			        return ps.executeUpdate();  
			    }  
			});  

	 
	}
	
	@Override
	public void deleteMenuItem(MenuItem menuItem) {
		 final String sql = "delete from menu_item where id=:id";
			 

		 Map<String,Object> map=new HashMap<String,Object>();  
		 map.put("id", menuItem.getMenuItemId());
	
		 template.execute(sql,map,new PreparedStatementCallback<Object>() {  
			    @Override  
			    public Object doInPreparedStatement(PreparedStatement ps)  
			            throws SQLException, DataAccessException {  
			        return ps.executeUpdate();  
			    }  
			});  

	 
	}
	
}
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
// import org.postgresql.util.PSQLException;

import com.example.doordelights.entity.UserAccount;
import com.example.doordelights.mapper.UserAccountRowMapper;
@Repository
public class UserAccountDaoImpl implements UserAccountDao{
	
	public UserAccountDaoImpl(NamedParameterJdbcTemplate template) {  
        this.template = template;  
}  
	NamedParameterJdbcTemplate template;  

	@Override
	public List<UserAccount> findAll() {
		return template.query("select * from user_account", new UserAccountRowMapper());
	}
	@Override
	public void createCustomerUserAccount(UserAccount userAccount) {
		 final String sql = "insert into user_account(email, hashed_password, first_name, last_name, user_type) values(:email, :hashed_password, :first_name, :last_name, 'customer')";
	        KeyHolder holder = new GeneratedKeyHolder();
	        SqlParameterSource param = new MapSqlParameterSource()
					.addValue("email", userAccount.getUserAccountEmail())
					.addValue("hashed_password", userAccount.getUserAccountHashedPassword())
					.addValue("first_name", userAccount.getUserAccountFirstName())
					.addValue("last_name", userAccount.getUserAccountLastName());
	        template.update(sql,param, holder);
	}
	
	@Override
	public void updateUserAccount(UserAccount userAccount) {
		 final String sql = "update user_account set email=:email, hashed_password=:hashed_password, first_name=:first_name, last_name=:last_name, user_type=:user_type where id=:id";
		 
	        KeyHolder holder = new GeneratedKeyHolder();
	        SqlParameterSource param = new MapSqlParameterSource()
					.addValue("id", userAccount.getUserAccountId())
					.addValue("email", userAccount.getUserAccountEmail())
					.addValue("hashed_password", userAccount.getUserAccountHashedPassword())
					.addValue("first_name", userAccount.getUserAccountFirstName())
					.addValue("last_name", userAccount.getUserAccountLastName())
					.addValue("user_type", userAccount.getUserAccountType());
	        template.update(sql,param, holder);
	 
	}
	
	@Override
	public void executeUpdateUserAccount(UserAccount userAccount) {
		 final String sql = "update user_account set email=:email, hashed_password=:hashed_password, first_name=:first_name, last_name=:last_name, user_type=:user_type where id=:id";
			 

		 Map<String,Object> map=new HashMap<String,Object>();  
		 map.put("id", userAccount.getUserAccountId());
		 map.put("email", userAccount.getUserAccountEmail());
		 map.put("hashed_password", userAccount.getUserAccountHashedPassword());
		 map.put("first_name", userAccount.getUserAccountFirstName());
		 map.put("last_name", userAccount.getUserAccountLastName());
		 map.put("user_type", userAccount.getUserAccountType());
	
		 template.execute(sql,map,new PreparedStatementCallback<Object>() {  
			    @Override  
			    public Object doInPreparedStatement(PreparedStatement ps)  
			            throws SQLException, DataAccessException {  
			        return ps.executeUpdate();  
			    }  
			});  

	 
	}
	
	@Override
	public void deleteUserAccount(UserAccount userAccount) {
		 final String sql = "delete from user_account where id=:id";
			 

		 Map<String,Object> map=new HashMap<String,Object>();  
		 map.put("id", userAccount.getUserAccountId());
	
		 template.execute(sql,map,new PreparedStatementCallback<Object>() {  
			    @Override  
			    public Object doInPreparedStatement(PreparedStatement ps)  
			            throws SQLException, DataAccessException {  
			        return ps.executeUpdate();  
			    }  
			});  

	 
	}
	
}
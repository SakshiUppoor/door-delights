package com.example.doordelights.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.doordelights.entity.UserAccount;

public class UserAccountRowMapper implements RowMapper<UserAccount> {

	@Override
	public UserAccount mapRow(ResultSet rs, int arg1) throws SQLException {
		UserAccount userAccount = new UserAccount();
		userAccount.setUserAccountId(rs.getString("id"));
		userAccount.setUserAccountEmail(rs.getString("email"));
		userAccount.setUserAccountHashedPassword(rs.getString("hashed_password"));
		userAccount.setUserAccountFirstName(rs.getString("first_name"));
		userAccount.setUserAccountLastName(rs.getString("last_name"));
		userAccount.setUserAccountType(rs.getString("user_type"));
 
        return userAccount;
	}


}
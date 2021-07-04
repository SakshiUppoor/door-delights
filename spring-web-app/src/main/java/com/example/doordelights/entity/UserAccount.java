
package com.example.doordelights.entity;

public class UserAccount {

	String userAccountId;
	String userAccountEmail;
	String userAccountHashedPassword;
	String userAccountFirstName;
	String userAccountLastName;
	String userAccountType;
	
	public String getUserAccountHashedPassword() {
		return userAccountHashedPassword;
	}
	public void setUserAccountHashedPassword(String userAccountHashedPassword) {
		this.userAccountHashedPassword = userAccountHashedPassword;
	}
	public String getUserAccountId() {
		return userAccountId;
	}
	public void setUserAccountId(String userAccountId) {
		this.userAccountId = userAccountId;
	}
	public String getUserAccountEmail() {
		return userAccountEmail;
	}
	public void setUserAccountEmail(String userAccountEmail) {
		this.userAccountEmail = userAccountEmail;
	}
	public String getUserAccountFirstName() {
		return userAccountFirstName;
	}
	public void setUserAccountFirstName(String userAccountFirstName) {
		this.userAccountFirstName = userAccountFirstName;
	}
	public String getUserAccountLastName() {
		return userAccountLastName;
	}
	public void setUserAccountLastName(String userAccountLastName) {
		this.userAccountLastName = userAccountLastName;
	}
	public String getUserAccountType() {
		return userAccountType;
	}
	public void setUserAccountType(String userAccountType) {
		this.userAccountType = userAccountType;
	}
	
	
}
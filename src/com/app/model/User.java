package com.app.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 * User Class with informative attributes to distinguish users from each other and validate their accounts.
 * @author Matt & Joey
 *
 */
public class User 
{
	@NotNull(message="Username cannot be null.")
	@Size(min=5, max=30, message="Username must be between 5 and 30 characters.")
	private String username;
	
	@NotNull(message="Password cannot be null.")
	@Size(min=5, max=30, message="Password must be between 5 and 30 characters.")
	private String password;
	
	@Size(max=30, message="First name can be up to 30 characters.")
	private String firstName;
	
	@Size(max=30, message="Last name can be up to 30 characters.")
	private String lastName;
	
	@Size(max=30, message="Email address can be up to 30 characters.")
	private String email;
	
	@Size(max=14, message="Phone number must 10 digits. Include Region #")
	private String phone;
	
	@Size(max=100, message="User has too much power.")
	private String permission;
	
	public User()
	{
		firstName="";
		lastName="";
		email="";
		phone="";
		permission="";
	}
	
	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, String firstName, String lastName, String email, String phone, String permission) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.permission = permission;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", phone=" + phone + ", permission=" + permission + "]";
	}
	
	public static User getSqlRowSet(SqlRowSet srs)
	{
		User user = new User(
				srs.getString("USERNAME"),
				srs.getString("PASSWORD"),
				srs.getString("FIRSTNAME"),
				srs.getString("LASTNAME"),
				srs.getString("EMAIL"),
				srs.getString("PHONE"),
				srs.getString("PERMISSION")
				);
		return user;
	}
	
	public static String getSqlParams()
	{
		return 	  "USERNAME, "
				+ "PASSWORD, "
				+ "FIRSTNAME, "
				+ "LASTNAME, "
				+ "EMAIL, "
				+ "PHONE, "
				+ "PERMISSION";
	}
	
	public static String getSqlValues(User user)
	{
		return  "'" + user.getUsername() + "', " +
				"'" + user.getPassword() + "', " +
				"'" + user.getFirstName() + "', " +
				"'" + user.getLastName() + "', " +
				"'" + user.getEmail() + "', " +
				"'" + user.getPhone() + "', " +
				"'" + user.getPermission() + "'";
	}
}

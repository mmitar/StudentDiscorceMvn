package com.app.data;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.app.model.User;

public class UserDataService implements DataAccessInterface<User>{

	/**
	 * Spring JDBC Dependency Setter Injection
	 */
	private JdbcTemplate jdbcTemplateObject;
	
	@Autowired
	public void setDataSource(DataSource dataSource)
	{
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	/**
	 * Default Constructor
	 */
	public UserDataService() {}

	/**
	 * READ Method
	 * Validation Login query checks if username exists in the database.
	 * 
	 * @param User user
	 * @return User user || null
	 */
	@Override
	public User findBy(User user)
	{
		// READ query to identify the user by username and password
		String sql = "SELECT * FROM studisc.users WHERE USERNAME = '"+user.getUsername()+"' AND PASSWORD = '"+user.getPassword()+"'";
		try
		{
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			
			// Goes to the Last Row of the Results
			srs.beforeFirst();
			srs.last();
			
			// Checks the Size of the Results. If anything other than 1, return null
			if(srs.getRow() != 1)
			{
				return null;
			}
			
			// Last Row should still be the First, and return the user
			return User.getSqlRowSet(srs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		// Returns null if Exception is thrown
		return null;
	}

	/**
	 * CREATE Method
	 * Checks first whether username already exists. If not add to DB.
	 * 
	 * @param User user
	 * @return boolean
	 */
	@Override
	public boolean create(User user) 
	{
		// READ query to see if username is already taken
		String sql_1 = "SELECT * FROM studisc.users WHERE USERNAME = '"+user.getUsername()+"'";
		SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql_1);
		
		// If first Row exists, the username is taken.
		if(srs.next())
		{
			return false;
		}
		
		// Create query that adds the user to the DB
		String sql = "INSERT INTO studisc.users (" + User.getSqlParams() + ") VALUES (" + User.getSqlValues(user) + ")";
		try
		{
			// Execute SQL Insert
			int rows = jdbcTemplateObject.update(sql);
			
			// Return Result of Insert
			return rows == 1 ? true : false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		// Returns false if Exception is thrown
		return false;
	}

	@Override
	public boolean update(User t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(User t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<User> findAll() 
	{
		return null;
	}
	
	@Override 
	public List<User> findAll(User user)
	{
		return null;
	}
}

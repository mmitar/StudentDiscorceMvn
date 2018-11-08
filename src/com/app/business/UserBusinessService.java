package com.app.business;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.data.DataAccessInterface;
import com.app.model.User;

public class UserBusinessService implements UserBusinessInterface 
{
	/**
	 * Dependency Injected
	 */
	@Autowired
	private DataAccessInterface<User> userDAO;
	
	/**
	 * Default Constructor
	 */
	public UserBusinessService() {}
	
	/**
	 * Validation Method that forwards the request to the DAO to find the User.
	 * 
	 * @param User user
	 * @return User
	 */
	public User findBy(User user)
	{
		// return results from UserDataService.findBy(user)
		return this.userDAO.findBy(user);
	}
	
	/**
	 * Creation Method that forwards the request to the DAO to create a new User.
	 * 
	 * @param User user
	 * @returm boolean
	 */
	public boolean create(User user)
	{
		// return results from UserDataService.create(user)
		return this.userDAO.create(user);
	}
}

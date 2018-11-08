package com.app.business;

import com.app.model.User;

public interface UserBusinessInterface 
{
	/**
	 * Validation Method that forwards the request to the DAO to find the User.
	 * 
	 * @param User user
	 * @return User
	 */
	public User findBy(User user);
	
	/**
	 * Creation Method that forwards the request to the DAO to create a new User.
	 * 
	 * @param User user
	 * @returm boolean
	 */
	public boolean create(User user);
}

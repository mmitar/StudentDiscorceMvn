package com.app.business;

import com.app.exceptions.UserErrorException;
import com.app.exceptions.UserFoundException;
import com.app.exceptions.UserNotFoundException;
import com.app.model.User;

/**
 * Contracted with the UserBusinessService and used for CDI
 */
public interface UserBusinessInterface 
{
	/**
	 * Validation Method that forwards the request to the DAO to find the User.
	 * 
	 * @throws UserNotFoundException
	 * @param User user
	 * @return User
	 */
	public User authenticateUser(User user) throws UserNotFoundException;
	
	/**
	 * Creation Method that forwards the request to the DAO to create a new User.
	 * 
	 * @throws UserFoundException, UserErrorException
	 * @param User user
	 * @return boolean
	 */
	public boolean addUser(User user) throws UserFoundException, UserErrorException;
}

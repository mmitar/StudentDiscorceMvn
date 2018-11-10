package com.app.business;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.data.DataAccessInterface;
import com.app.exceptions.UserErrorException;
import com.app.exceptions.UserFoundException;
import com.app.exceptions.UserNotFoundException;
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
	 * @throws UserNotFoundException
	 * @param User user
	 * @return User
	 */
	public User authenticateUser(User user) throws UserNotFoundException
	{
		user = userDAO.findBy(user);
		if(user == null)
		{
			throw new UserNotFoundException();
		}
	
		// return results from UserDataService.findBy(user)
		return user;
	}
	
	/**
	 * Creation Method that forwards the request to the DAO to create a new User.
	 * 
	 * @throws UserFoundException
	 * @param User user
	 * @return boolean
	 */
	public boolean addUser(User user) throws UserFoundException, UserErrorException
	{
		User exists = userDAO.findBy(user);
		if(exists != null)
		{
			throw new UserFoundException();
		}
		
		boolean result = userDAO.create(user);
		if(result == false)
		{
			throw new UserErrorException();
		}
		
		return result;
	}
}

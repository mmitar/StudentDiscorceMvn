package com.app.util;

import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

import com.app.model.User;

/**
 * User Session Cache
 * A Session state caching singleton class that is currently unimplemented.
 * Will be used to handle view consistency and retain some User session data.
 */
@Singleton
public class UserSession {

		private HashMap<String, User> session;
		
		@PostConstruct
		private void init() {
			session = new HashMap<String, User>();
		}
		
		/**
		 * Gets the value from the key
		 * 
		 * @param User user
		 * @return User user
		 */
		public User getObject(User user)
		{
			// States the key by username
			String key = user.getUsername();
			
			// If cache has the 'key' username
			if(session.containsKey(key))
			{
				System.out.println("Cache hit for " + key);
				return session.get(key);
			}
			// user does not exist in the cache
			else
			{
				System.out.println("Cache miss for " + key);
				return null;
			}
		}
		
		/**
		 * Puts the object in the cache by key->value pair. Username is the key.
		 * 
		 * @param User user
		 */
		public void putObject(User user)
		{
			String key = user.getUsername();
			session.put(key, user);
			System.out.println("Cache put for " + key);
		}
}

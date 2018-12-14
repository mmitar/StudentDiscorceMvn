package com.app.util;

import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

import com.app.model.Course;
import com.app.model.User;

/**
 * User Session Cache
 * A Session state caching singleton class that is currently unimplemented.
 * Will be used to handle view consistency and retain some User session data.
 */
@Singleton
public class UserSession {

		// HashMaps that act as caches
		private static HashMap<String, User> session;
		private static HashMap<String, List<Course>> courseList;
		private static HashMap<String, Course> courses;
		
		// Build single instance of each cache, private so we can not re-initialize it.
		@PostConstruct
		private void init() {
			session = new HashMap<String, User>();
			courseList = new HashMap<String, List<Course>>();
			courses = new HashMap<String, Course>();
		}
		
		/**
		 * Gets the user that stores in the cache by user ID.
		 * 
		 * @param User user
		 * @return User
		 */
		public static User getUser()
		{
			// States the key by username
			String key = "username";
			
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
		public static void setUser(User user)
		{
			// Sets the key as the user's username
			String key = user.getUsername();
			// sets the use in the cache by name value pair
			session.put(key, user);
			System.out.println("Cache put for " + key);
		}
		
		/**
		 * Gets the course List from the course. There should be one instance of it
		 * 
		 * @return List<Course>
		 */
		public static List<Course> getCourseList()
		{
			// States the key by username
			String key = "courseList";
			
			// If cache has the 'key' username
			if(courseList.containsKey(key))
			{
				System.out.println("Cache hit for " + key);
				return  courseList.get(key);
			}
			// user does not exist in the cache
			else
			{
				System.out.println("Cache miss for " + key);
				return null;
			}
		}
		
		/**
		 * Sets the Course List to a cache
		 * 
		 * @param cslst List<Course>
		 */
		public static void setCourseList(List<Course> cslst)
		{
			// Sets the key as courseList. Generic cuz there is one instance.
			String key = "courseList";
			// puts the list in the hashmap by name value pair
			courseList.put(key, cslst);
			System.out.println("Cache put for " + key);
		}
		
		/**
		 * Gets the selected course out from the cache
		 * 
		 * @param cs Course
		 * @return Course
		 */
		public static Course getCourse(Course cs)
		{
			// States the key by username
			String key = cs.getCourseID();
			
			// If cache has the 'key' username
			if(courses.containsKey(key))
			{
				System.out.println("Cache hit for " + key);
				return courses.get(key);
			}
			// user does not exist in the cache
			else
			{
				System.out.println("Cache miss for " + key);
				return null;
			}
		}
		
		/**
		 * Sets the course inside the cache 
		 * 
		 * @param cs Course
		 */
		public static void setCourse(Course cs)
		{
			// sets the key to the course ID
			String key = cs.getCourseID();
			// puts the course the cache by name value pair
			courses.put(key, cs);
			System.out.println("Cache put for " + key);
		}
}

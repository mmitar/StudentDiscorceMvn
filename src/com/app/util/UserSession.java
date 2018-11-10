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
 * @param <T>
 */
@Singleton
public class UserSession {

		private static HashMap<String, User> session;
		private static HashMap<String, List<Course>> courseList;
		private static HashMap<String, Course> courses;
		
		@PostConstruct
		private void init() {
			session = new HashMap<String, User>();
			courseList = new HashMap<String, List<Course>>();
			courses = new HashMap<String, Course>();
		}
		
		/**
		 * Gets the value from the key
		 * 
		 * @param User user
		 * @return User user
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
			String key = user.getUsername();
			session.put(key, user);
			System.out.println("Cache put for " + key);
		}
		
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
		
		public static void setCourseList(List<Course> cslst)
		{
			String key = "courseList";
			courseList.put(key, cslst);
			System.out.println("Cache put for " + key);
		}
		
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
		
		public static void setCourse(Course cs)
		{
			String key = cs.getCourseID();
			courses.put(key, cs);
			System.out.println("Cache put for " + key);
		}
}

package com.app.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.app.exceptions.DatabaseException;
import com.app.model.Course;
import com.app.model.User;

/**
 * DAO to access persistence data regarding course information
 * 
 * @param <Course>
 */
public class CourseDataService implements DataAccessInterface<Course>{

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
	public CourseDataService() {}
	
	/**
	 * READ Method
	 * Gets up to 100 courses and all users related to each course
	 * 
	 * @return List<Course>
	 * @throws DatabaseException
	 */
	@Override
	public List<Course> findAll()
	{
		List<Course> courses = new ArrayList<Course>();
		List<User> instructors = new ArrayList<User>();
		List<User> tutors = new ArrayList<User>();
		List<User> students = new ArrayList<User>();
		
		//READ query that searches approximate matches for courses searched
		
		// Derby Dialect
		//String sql_1 = "SELECT * FROM studisc.courses FETCH FIRST 100 ROWS ONLY";
		
		// MySQL Dialect
		String sql_1 = "SELECT * FROM studisc.courses LIMIT 100";

		
		try
		{
			SqlRowSet srs_1 = jdbcTemplateObject.queryForRowSet(sql_1);
			
			// Iterate through all the results of the courses found
			while(srs_1.next())
			{
				Course newCourse = Course.getSqlRowSet(srs_1);
				
				// READ query to get the users affiliated with the course that was found.
				String sql_2 = "SELECT * FROM studisc.users WHERE PERMISSION LIKE '%"+newCourse.getCourseID()+"%'";
				SqlRowSet srs_2 = jdbcTemplateObject.queryForRowSet(sql_2);
				
				// Iterate through all results of users affiliated with the course
				while(srs_2.next())
				{
					User user = User.getSqlRowSet(srs_2);
					String[] perms;
					
					// If there are multiple delimiters, split, if not, assign.
					if(user.getPermission().contains("||"))
					{
						// For Multiple: Split Key->Value pairs from each other
						perms = user.getPermission().split("\\|\\|");
					}else 
					{
						// For Single: Assign [0]
						perms = new String[]{user.getPermission()};
					}
					
					// Iterate through all permissions the user may have
					for(String perm: perms) {
						
						// Split the Permission Key from Value. Key is CourseID -> Value is Authority level.
						String[] permHash = perm.split("\\:");
						
						// If the permission 'Key' is related to the course, add them based on its level. User may have multiple levels.
						if(permHash[0].equals(newCourse.getCourseID()))
						{
							if(permHash[1].equals("2"))
							{
								instructors.add(user);
							}
							if(permHash[1].equals("1"))
							{
								tutors.add(user);
							}
							if(permHash[1].equals("0"))
							{
								students.add(user);
							}
						}
						
					} // For Each Loop
						
				} // While next user
				
				// Add the Lists of User Permission Types to the course
				newCourse.setInstructors(instructors);
				newCourse.setTutors(tutors);
				newCourse.setStudents(students);
				
				// Add the compiled course to the List of Courses and continue iterating
				courses.add(newCourse);
				
			} // While next course
			
			return courses;
		}
		// Catches SQL / DB Connection Issues.
		catch(Exception e)
		{
			// Throw Custom DB Exception
			throw new DatabaseException(e);
		}
	}
	
	/**
	 * READ Method
	 * Gets all classes relative to the search params and compiles the courses
	 * 		as well as users affiliated to each class.
	 * 
	 * @param Course course
	 * @return List<Course>
	 * @throws DatabaseException
	 */
	@Override
	public List<Course> findAll(Course course)
	{
		List<Course> courses = new ArrayList<Course>();
		List<User> instructors = new ArrayList<User>();
		List<User> tutors = new ArrayList<User>();
		List<User> students = new ArrayList<User>();
		
		// READ query that searches approximate matches for courses searched
		String sql_1 = "SELECT * FROM studisc.courses WHERE COURSE_ID LIKE '%"+course.getCourseID()+"%'"
														+ " OR TITLE LIKE '%"+course.getTitle()+"%'";
		try
		{
			SqlRowSet srs_1 = jdbcTemplateObject.queryForRowSet(sql_1);
			
			// Iterate through all the results of the courses found
			while(srs_1.next())
			{
				Course newCourse = Course.getSqlRowSet(srs_1);
				
				// READ query to get the users affiliated with the course that was found.
				String sql_2 = "SELECT * FROM studisc.users WHERE PERMISSION LIKE '%"+course.getCourseID()+"%'";
				SqlRowSet srs_2 = jdbcTemplateObject.queryForRowSet(sql_2);
				
				// Iterate through all results of users affiliated with the course
				while(srs_2.next())
				{
					User user = User.getSqlRowSet(srs_2);
					String[] perms;
					
					// If there are multiple delimiters, split, if not, assign.
					if(user.getPermission().contains("||"))
					{
						// For Multiple: Split Key->Value pairs from each other
						perms = user.getPermission().split("\\|\\|");
					}else 
					{
						// For Single: Assign [0]
						perms = new String[]{user.getPermission()};
					}
					
					// Iterate through all permissions the user may have
					for(String perm: perms) {
						
						// Split the Permission Key from Value. Key is CourseID -> Value is Authority level.
						String[] permHash = perm.split("\\:");
						
						// If the permission 'Key' is related to the course, add them based on its level. User may have multiple levels.
						if(permHash[0].equals(newCourse.getCourseID()))
						{
							if(permHash[1].equals("2"))
							{
								instructors.add(user);
							}
							if(permHash[1].equals("1"))
							{
								tutors.add(user);
							}
							if(permHash[1].equals("0"))
							{
								students.add(user);
							}
						}
						
					} // For Each Loop
					
				} // While next user
				
				// Add the Lists of User Permission Types to the course
				newCourse.setInstructors(instructors);
				newCourse.setTutors(tutors);
				newCourse.setStudents(students);
				
				// Add the compiled course to the List of Courses and continue iterating
				courses.add(newCourse);
				
			} // While next course
			
			return courses;
		}
		// Catches SQL / DB Connection Issues.
		catch(Exception e)
		{
			// Throw Custom DB Exception
			throw new DatabaseException(e);
		}
	}

	/**
	 * READ Method
	 * Gets the course selected by the Course ID and compiled the course with affiliated users
	 * 
	 * @param Course course
	 * @return Course
	 * @throws DatabaseException
	 */
	@Override
	public Course findBy(Course course)
	{
		List<User> instructors = new ArrayList<User>();
		List<User> tutors = new ArrayList<User>();
		List<User> students = new ArrayList<User>();
		
		try
		{
			// READ query to select the course being called. Case insensitive.
			String sql = "SELECT * FROM studisc.courses WHERE COURSE_ID = TRIM('" + course.getCourseID() + "')";
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			
			// Return if there are no results
			if(srs.next() == false)
			{
				return null;
			}
			
			course = Course.getSqlRowSet(srs);
			
			// READ query to get the users affiliated with the course that was found.
			String sql_2 = "SELECT * FROM studisc.users WHERE PERMISSION LIKE '%"+course.getCourseID()+"%'";
			SqlRowSet srs_2 = jdbcTemplateObject.queryForRowSet(sql_2);
			
			// Iterate through all results of users affiliated with the course
			while(srs_2.next())
			{
				User user = User.getSqlRowSet(srs_2);
				String[] perms;
				
				// If there are multiple delimiters, split, if not, assign.
				if(user.getPermission().contains("||"))
				{
					// For Multiple: Split Key->Value pairs from each other
					perms = user.getPermission().split("\\|\\|");
				}else 
				{
					// For Single: Assign [0]
					perms = new String[]{user.getPermission()};
				}
				
				// Iterate through all permissions the user may have
				for(String perm: perms) {
					
					// Split the Permission Key from Value. Key is CourseID -> Value is Authority level.
					String[] permHash = perm.split("\\:");
					
					// If the permission 'Key' is related to the course, add them based on its level. User may have multiple levels.
					if(permHash[0].equals(course.getCourseID()))
					{
						if(permHash[1].equals("2"))
						{
							instructors.add(user);
						}
						if(permHash[1].equals("1"))
						{
							tutors.add(user);
						}
						if(permHash[1].equals("0"))
						{
							students.add(user);
						}
					}
					
				} // For Each Loop
				
			} // While next user
			
			// Add the Lists of User Permission Types to the course
			course.setInstructors(instructors);
			course.setTutors(tutors);
			course.setStudents(students);
			
			return course;
		}
		// Catches SQL / DB Connection Issues.
		catch(Exception e)
		{
			// Throw Custom DB Exception
			throw new DatabaseException(e);
		}
	}
	
	/**
	 * CREATE Method
	 * Adds new course to the database.
	 * 
	 * @param Course course
	 * @return boolean
	 * @throws DatabaseException
	 */
	@Override
	public boolean create(Course course) 
	{
		try
		{
			// CREATE query that adds the course to the database. Upper-Cases Course ID.
			String sql = "INSERT INTO studisc.courses ("+ Course.getSqlParams() +") VALUES ("+ Course.getSqlValues(course) +")";
		
			// Execute SQL Insert
			int rows = jdbcTemplateObject.update(sql);
			
			// Return Result of Insert
			return rows == 1 ? true : false;
		}
		// Catches SQL / DB Connection Issues.
		catch(Exception e)
		{
			// Throw Custom DB Exception
			throw new DatabaseException(e);
		}
	}
	
	/**
	 * UPDATE Method
	 * Updates all fields of selected course (except course ID) by Course ID.
	 * 
	 * @param Course course
	 * @return boolean
	 * @throws DatabaseException
	 */
	@Override
	public boolean update(Course course)
	{
		try
		{
			// UPDATE query that updates the selected course params by Course ID
			String sql = "UPDATE studisc.courses SET "+ Course.getSqlSet(course) +" WHERE COURSE_ID = '" + course.getCourseID() + "'";
			
			// Execute SQL Update
			int rows = jdbcTemplateObject.update(sql);
			
			// Return Result of Update
			return rows == 1 ? true : false;
		}
		// Catches SQL / DB Connection Issues.
		catch(Exception e)
		{
			// Throw Custom DB Exception
			throw new DatabaseException(e);
		}
	}

	/**
	 * DELETE Method
	 * Removes the selected course by the Course ID.
	 * 
	 * @param Course course
	 * @return boolean
	 * @throws DatabaseException
	 */
	@Override
	public boolean delete(Course course) 
	{
		try
		{
			// DELETE query that removes the course by Course ID.
			String sql = "DELETE FROM studisc.courses WHERE COURSE_ID = '" + course.getCourseID() + "'";
					
			// Execute SQL Update
			int rows = jdbcTemplateObject.update(sql);
			
			// Return Result of Update
			return rows == 1 ? true : false;
		}
		// Catches SQL / DB Connection Issues.
		catch(Exception e)
		{
			// Throw Custom DB Exception
			throw new DatabaseException(e);
		}
	}
	
	/**
	 * READ Method
	 * Checks if there is any variance of the course in the database. Case insensitive.
	 * 
	 * @param Course course
	 * @return boolean
	 * @throws DatabaseException
	 */
	@Override
	public boolean findIfExists(Course course)
	{
		try
		{
			// READ query to identify by course ID. Case Insensitive.
			String sql = "SELECT * FROM studisc.courses WHERE "
					+ "UPPER(COURSE_ID) LIKE TRIM(UPPER('" + course.getCourseID() + "'))";

			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			
			// Goes to the Last Row of the Results
			srs.beforeFirst();
			srs.last();
			
			// Checks the Size of the Results. If anything was found, return true
			if(srs.getRow() > 0)
			{
				return true;
			}
			
			// if no results were found, nothing exists.
			return false;
		}
		// Catches SQL / DB Connection Issues.
		catch(Exception e)
		{
			// Throw Custom DB Exception
			throw new DatabaseException(e);
		}
	}
	

	/**
	 * Inactive.
	 */
	@Override
	public Course findById(int id) 
	{
		return null;
	}
	
}

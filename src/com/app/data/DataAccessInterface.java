package com.app.data;

import java.util.List;

public interface DataAccessInterface <T> 
{
	/**
	 * READ Method
	 * 
	 * @return List<T>
	 */
	public List<T> findAll();
	
	/**
	 * READ Method
	 * 
	 * @param T t
	 * @return List<T>
	 */
	public List<T> findAll(T t);
	
	/**
	 * READ Method
	 * 
	 * @param T t
	 * @return T
	 */
	public T findBy(T t);
	
	/**
	 * READ Method
	 * @param int id
	 * @return T
	 */
	public T findById(int id);
	
	/**
	 * CREATE Method
	 * 
	 * @param T t
	 * @return boolean
	 */
	public boolean create(T t);
	
	/**
	 * UPDATE Method
	 * 
	 * @param T t
	 * @return boolean
	 */
	public boolean update(T t);
	
	/**
	 * DELETE Method
	 * 
	 * @param T t
	 * @return boolean
	 */
	public boolean delete(T t);
	
	/**
	 * READ Method
	 * 
	 * @param T t
	 * @return boolean
	 */
	public boolean findIfExists(T t); 
}

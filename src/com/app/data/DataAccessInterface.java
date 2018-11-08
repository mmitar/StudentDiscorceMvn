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
	 * @param t
	 * @return
	 */
	public List<T> findAll(T t);
	
	/**
	 * READ Method
	 * 
	 * @param t
	 * @return
	 */
	public T findBy(T t);
	
	/**
	 * READ Method
	 * @param id
	 * @return
	 */
	public T findById(int id);
	
	/**
	 * CREATE Method
	 * 
	 * @param t
	 * @return
	 */
	public boolean create(T t);
	
	/**
	 * UPDATE Method
	 * 
	 * @param t
	 * @return
	 */
	public boolean update(T t);
	
	/**
	 * DELETE Method
	 * 
	 * @param t
	 * @return
	 */
	public boolean delete(T t);
}

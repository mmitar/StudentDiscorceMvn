package com.app.exceptions;

/**
 * Exception in DAO's for declaring an issue regarding: database connectivity
 */
public class DatabaseException extends RuntimeException
{
	private static final long serialVersionUID = 0L;
	
	public DatabaseException(Throwable e)
	{
		super(e);
	}
}

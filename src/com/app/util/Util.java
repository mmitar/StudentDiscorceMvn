package com.app.util;

/**
 * A utility class that includes functions to help the app operate safely
 */
public class Util {
	
	/**
	 * Primarily used for SQL. Apostrophes are unsafe characters for derby and can't parse them properly
	 * This function makes the string safe and you can use the full range of characters.
	 * 
	 * @param param String
	 * @return String
	 */
	public static String typeSafe(String param)
	{
		// For SQL, change the apostrophe to a safe character
		return param.replace("'","''");
	}
}

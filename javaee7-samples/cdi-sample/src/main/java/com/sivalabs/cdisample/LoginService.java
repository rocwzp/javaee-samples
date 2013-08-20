/**
 * 
 */
package com.sivalabs.cdisample;

/**
 * @author skatam
 *
 */
public class LoginService {

	public boolean login(String userName, String password)
	{
		return "admin".equals(userName) && "admin".equals(password);
	}
}

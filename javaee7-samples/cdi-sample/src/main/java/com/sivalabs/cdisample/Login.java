/**
 * 
 */
package com.sivalabs.cdisample;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author skatam
 *
 */
public class Login 
{
	@NotNull(message="UserName should not be null")
	@Size(min=3, message="UserName should be min 3 chars")
	private String userName;
	@NotNull(message="Password should not be null")
	@Size(min=4, message="Password should be min 4 chars")
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

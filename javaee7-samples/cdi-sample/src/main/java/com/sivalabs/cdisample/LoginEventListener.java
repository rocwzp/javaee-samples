/**
 * 
 */
package com.sivalabs.cdisample;

import javax.enterprise.event.Observes;

/**
 * @author skatam
 *
 */
public class LoginEventListener {

	public void handleLoginEvent(@Observes LoginEvent loginEvent){
		System.err.println("Somebody loggedin with userName :"+loginEvent.getUserName());
	}
}

/**
 * 
 */
package com.sivalabs.hellojavaee;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * @author Siva
 *
 */
@Named
@RequestScoped
public class MessageController {
	private String name;
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
}

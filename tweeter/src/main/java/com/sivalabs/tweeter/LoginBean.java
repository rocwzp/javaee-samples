package com.sivalabs.tweeter;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
@SessionScoped
public class LoginBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private String userName;
	private String password;
	
    public String login()
    {
    	logger.debug("UserName :{}, Password: {}", userName, password);
    	String view="index";
    	if("admin".equals(userName) && "admin".equals(password)){
    		view = "welcome.jsf?faces-redirect=true";
    	} else {
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Invalid UserName and Password"));
    	}
    	return view;
    }

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
    
    
}

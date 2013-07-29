package com.sivalabs.tweeter.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sivalabs.tweeter.ejbs.UserEJB;
import com.sivalabs.tweeter.entities.User;

@Named
@SessionScoped
public class LoginBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	private UserEJB userEJB;
	
	private User loginUser;
	private String email;
	private String password;
	
    public String login()
    {
    	logger.debug("email :{}, Password: {}", email, password);
    	String view="index";
    	User user = userEJB.login(email, password);
    	if(user != null){
    		view = "home.jsf?faces-redirect=true";
    	} else {
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid UserName and Password", null));
    	}
    	return view;
    }

	public User getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(User loginUser) {
		this.loginUser = loginUser;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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

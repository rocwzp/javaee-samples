package com.sivalabs.tweeter.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sivalabs.tweeter.ejbs.TweeterServiceBean;
import com.sivalabs.tweeter.entities.User;
import com.sivalabs.tweeter.qualifiers.LoggedinUser;
import com.sivalabs.tweeter.view.ChangePasswordBean;

@Named
@SessionScoped
public class UserController implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	private TweeterServiceBean tweeterServiceBean;
	
	private User loginUser;
	private User registrationUser;
	private User updateUser;
	
	private String loginId;
	private String password;
	private ChangePasswordBean changePasswordBean;
	
    public String login()
    {
    	logger.debug("loginId :{}, Password: {}", loginId, password);
    	String view="index";
    	User user = tweeterServiceBean.login(loginId, password);
    	if(user != null){
    		loginUser = user;
    		updateUser = this.tweeterServiceBean.getUserById(loginUser.getId());
    		view = "home.jsf?faces-redirect=true";
    	} else {
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid UserName and Password", null));
    	}
    	return view;
    }

    public String logout()
    {
    	((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
    	return "index.jsf?faces-redirect=true";
    }
    public String register()
    {
    	String view="registration";
    	User user = tweeterServiceBean.createUser(registrationUser);
    	if(user != null){
    		view = "index.jsf?faces-redirect=true";
    		registrationUser = null;
    		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Registered", null));
    	} else {
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid UserName and Password", null));
    	}
    	return view;
    }
    
    public String updateUser()
    {
    	User user = tweeterServiceBean.updateUser(updateUser);
    	if(user != null){
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Updated", null));
    	} else {
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "User Update Failed", null));
    	}
    	return null;
    }
    
    public String changePwd()
    {
    	boolean success = tweeterServiceBean.updatePassword(changePasswordBean);
    	if(success){
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Password Changed Successfully", null));
    		changePasswordBean = null;
    	}else {
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password Change Unsuccessful", null));
    	}
    	return null;
    }
    
    @Named("loginUser")
    @LoggedinUser
    @Produces
	public User getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(User loginUser) {
		this.loginUser = loginUser;
	}
	
	public User getRegistrationUser()
	{
		if(registrationUser == null){
			registrationUser = new User();
		}
		return registrationUser;
	}

	public void setRegistrationUser(User registrationUser)
	{
		this.registrationUser = registrationUser;
	}
	
	public String getLoginId()
	{
		return loginId;
	}

	public void setLoginId(String loginId)
	{
		this.loginId = loginId;
	}

	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}

	public User getUpdateUser()
	{
		if(loginUser == null)
		{
			updateUser = new User();
		}
		return updateUser;
	}

	public void setUpdateUser(User updateUser)
	{
		this.updateUser = updateUser;
	}

	public ChangePasswordBean getChangePasswordBean()
	{
		if(changePasswordBean == null){
			changePasswordBean = new ChangePasswordBean();
			changePasswordBean.setUserId(loginUser.getId());
		}
		return changePasswordBean;
	}

	public void setChangePasswordBean(ChangePasswordBean changePasswordBean)
	{
		this.changePasswordBean = changePasswordBean;
	}
	
}

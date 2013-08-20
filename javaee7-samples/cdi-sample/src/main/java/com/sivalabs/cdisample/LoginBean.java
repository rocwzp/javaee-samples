/**
 * 
 */
package com.sivalabs.cdisample;

import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * @author skatam
 *
 */
@ManagedBean
@RequestScoped
public class LoginBean {

	@Inject
	private LoginService loginService;
	
	private Login login;
	
	@Inject
	private Event<LoginEvent> event;
	
	public String doLogin()
	{
		boolean loginSuccess = loginService.login(login.getUserName(), login.getPassword());
		if(loginSuccess){
			LoginEvent loginEvent = new LoginEvent();
			loginEvent.setUserName(login.getUserName());
			event.fire(loginEvent);			
			return "home.jsf?faces-redirect=true";
		} else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login failed"));
			return null;
		}
	}
	
	public Login getLogin() {
		if(login == null){
			login = new Login();
		}
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	
	
	
}

package com.sivalabs.bookstore.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author Siva
 *
 */
public class JSFUtils
{
	public static void addInfoMessage(String msg)
	{
		FacesContext.getCurrentInstance().
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg,null));
	}
	
	public static void addErrorMessage(String msg)
	{
		FacesContext.getCurrentInstance().
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg,null));
	}
}

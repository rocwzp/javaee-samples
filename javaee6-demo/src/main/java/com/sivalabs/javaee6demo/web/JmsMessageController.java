package com.sivalabs.javaee6demo.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import com.sivalabs.javaee6demo.bo.JmsBO;

/**
 * @author Siva
 *
 */
@ManagedBean
@SessionScoped
public class JmsMessageController
{
	@Inject
	private JmsBO jmsBO;
	
	private String queueName="test";
	private String message;
	public String getQueueName()
	{
		return queueName;
	}
	public void setQueueName(String queueName)
	{
		this.queueName = queueName;
	}
	public String getMessage()
	{
		return message;
	}
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	public void sendMessage()
	{
		jmsBO.sendMessage(queueName, message);
	}
}

package com.sivalabs.javaee6demo;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * @author Siva
 *
 */
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class AppConfiguration
{
	private String orderXmlDir = null;
	private String orderXmlArchiveDir = null;
	
	@PostConstruct
	public void	init()
	{
		System.err.println("******* AppConfiguration.init() ********** ");
		orderXmlDir = "D:/OrderXmlRepo/";
		orderXmlArchiveDir = "D:/OrderXmlRepo/archive/";
		System.err.println("Initialized.....");
	}
	
	public String getOrderXmlDir()
	{
		return orderXmlDir;
	}
	
	public String getOrderXmlArchiveDir()
	{
		return orderXmlArchiveDir;
	}
}

package com.sivalabs.javaee6demo.entities;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Siva
 *
 */
@XmlRootElement
public class Order
{
	private String orderId;
	private String description;
	private Date createdOn;
	
	@Override
	public String toString()
	{
		return "Order [orderId=" + orderId + ", description=" + description
				+ ", createdOn=" + createdOn + "]";
	}
	public String getOrderId()
	{
		return orderId;
	}
	public void setOrderId(String orderId)
	{
		this.orderId = orderId;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public Date getCreatedOn()
	{
		return createdOn;
	}
	public void setCreatedOn(Date createdOn)
	{
		this.createdOn = createdOn;
	}
	
	
}

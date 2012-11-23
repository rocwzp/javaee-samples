package com.sivalabs.javaee6demo.events;

import com.sivalabs.javaee6demo.entities.Order;

/**
 * @author Siva
 *
 */
public class NewOrderEvent
{
	private Order order;
	public void setOrder(Order order)
	{
		this.order = order;
	}
	public Order getOrder()
	{
		return order;
	}
}

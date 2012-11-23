package com.sivalabs.javaee6demo.bo;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.sivalabs.javaee6demo.entities.Order;

/**
 * @author Siva
 *
 */
@Stateless
public class OrderBO
{
	@Inject
    private JmsBO jmsBO;

	public void placeOrder(Order order)
	{
		System.out.println("inserting order into ORDER JMS Queue");
		try
	      {
			jmsBO.sendMessage("test", order.toString());
	      }
	      catch (Exception e)
	      {
	         e.printStackTrace();
	      }
		
	}
}

package com.sivalabs.javaee6demo.events;

import javax.enterprise.event.Observes;


public class OrderReceivingObserver
{
	public void processNewOrder(@Observes NewOrderEvent newOrderEvent)
	{
		System.err.println("A new Order[Id:"+newOrderEvent.getOrder()+"] arrived.");
	}
}

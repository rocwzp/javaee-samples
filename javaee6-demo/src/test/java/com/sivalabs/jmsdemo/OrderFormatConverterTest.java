package com.sivalabs.jmsdemo;

import java.util.Date;

import com.sivalabs.javaee6demo.entities.Order;
import com.sivalabs.javaee6demo.utils.OrderFormatConverter;

/**
 * @author Siva
 *
 */
public class OrderFormatConverterTest
{
	public static void main(String[] args)
	{
		Order order = new Order();
		order.setOrderId(String.valueOf(System.currentTimeMillis()));
		order.setDescription("A new order placed at "+new Date());
		order.setCreatedOn(new Date());
		System.out.println(OrderFormatConverter.fromJavaToXml(order));
	}
	
}

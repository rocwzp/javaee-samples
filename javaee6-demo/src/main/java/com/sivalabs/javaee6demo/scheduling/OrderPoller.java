package com.sivalabs.javaee6demo.scheduling;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;

import com.sivalabs.javaee6demo.Constants;
import com.sivalabs.javaee6demo.bo.OrderBO;
import com.sivalabs.javaee6demo.entities.Order;
import com.sivalabs.javaee6demo.events.NewOrderEvent;
import com.sivalabs.javaee6demo.utils.OrderFormatConverter;

/**
 * @author Siva
 *
 */
@Stateless
public class OrderPoller
{
	@Inject
	private Event<NewOrderEvent> newOrderEvent;
	@Inject
	private OrderBO orderBO;
	
	@Schedule(minute="*/15", hour="*")
	public void pollForNewOrders()
	{
		System.err.println("Polling for new Orders at "+new Date());
		Collection<File> orderXmlFiles = getOrderXmlFiles();
		if(orderXmlFiles.isEmpty())
		{
			System.err.println("No New Orders found!!");
			return;
		}
		for (File file : orderXmlFiles)
		{
			Order order = OrderFormatConverter.fromFileToJava(file);
			placeOrder(order);
			archiveFile(file);
		}
		
	}
	
	private void placeOrder(Order order){
		orderBO.placeOrder(order);
		NewOrderEvent newOrder = new NewOrderEvent();
		newOrder.setOrder(order);
		newOrderEvent.fire(newOrder);
	}
	
	private Collection<File> getOrderXmlFiles()
	{
		return FileUtils.listFiles(new File(Constants.ORDER_XML_DIR), new String[]{"xml"}, false);
	}
	
	private void archiveFile(File file)
	{
		File dir = new File(Constants.ORDER_XML_ARCHIVE_DIR);
		try
		{
			FileUtils.moveFile(file, new File(dir, file.getName()));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

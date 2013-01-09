package com.sivalabs.javaee6demo.scheduling;

import java.io.File;
import java.util.Collection;
import java.util.Date;

import javax.ejb.Schedule;
import javax.ejb.Stateless;

import org.apache.commons.io.FileUtils;

import com.sivalabs.javaee6demo.Constants;
import com.sivalabs.javaee6demo.entities.Order;
import com.sivalabs.javaee6demo.utils.OrderFormatConverter;

/**
 * @author Siva
 *
 */
@Stateless
public class OrderGenerator
{

	@SuppressWarnings("unused")
	@Schedule(second="0", minute="*/5", hour="*")
	public void generateOrders() throws Exception
	{
		if(!Constants.GENERATE_TEST_ORDERS){
			return;
		}
		long id = System.currentTimeMillis();
		System.err.println("Generating sample Order with Id: "+id);
		Order order = new Order();
		order.setOrderId(String.valueOf(id));
		order.setDescription("A new order placed at "+new Date(id));
		order.setCreatedOn(new Date());
		
		String orderXml = OrderFormatConverter.fromJavaToXml(order);
		FileUtils.write(new File(Constants.ORDER_XML_DIR+"order_xml_"+id+".xml"), orderXml);

	}
	
	@Schedule(minute="*/30", hour="*")
	public void purgeOrderXmls() throws Exception
	{
		System.err.println("######### Deleting Archive Directory ##########");
		Collection<File> listFiles = FileUtils.listFiles(new File(Constants.ORDER_XML_ARCHIVE_DIR), null, false) ;
		for (File file : listFiles)
		{
			FileUtils.deleteQuietly(file);
		}
		
		
	}

}

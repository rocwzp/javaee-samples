package com.sivalabs.javaee6demo.utils;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.sivalabs.javaee6demo.entities.Order;

/**
 * @author Siva
 *
 */
public class OrderFormatConverter
{
	private OrderFormatConverter()
	{
	}
	public static Order fromXmlToJava(String xmlContent)
	{
		Order order = null;
		order = unmarshalOrder(xmlContent);
		return order;
	}
	
	public static Order fromFileToJava(File file)
	{
		Order order = null;
		order = unmarshalOrder(file);
		return order;
	}
	
	public static String fromJavaToXml(Order order)
	{
		String xml = null;
		xml = marshalOrder(order);
		return xml;
	}
	
	private static String marshalOrder(Order order){
		StringWriter sw = new StringWriter();
		try
		{
			JAXBContext context = JAXBContext.newInstance(Order.class);
		    Marshaller m = context.createMarshaller();
		    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(order, sw);
		} catch (JAXBException e)
		{
			e.printStackTrace();
		}
		return sw.toString();
	}
	
	private static Order unmarshalOrder(File xml)
	{
		Order order = null;
		try
		{
			JAXBContext context = JAXBContext.newInstance(Order.class);
		    Unmarshaller um = context.createUnmarshaller();
		    //um.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			order = (Order) um.unmarshal(xml);
		} catch (JAXBException e)
		{
			e.printStackTrace();
		}
		return order;
	}
	
	private static Order unmarshalOrder(String xml)
	{
		Order order = null;
		try
		{
			JAXBContext context = JAXBContext.newInstance(Order.class);
		    Unmarshaller um = context.createUnmarshaller();
		    //um.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			order = (Order) um.unmarshal(new StringReader(xml));
		} catch (JAXBException e)
		{
			e.printStackTrace();
		}
		return order;
	}
}

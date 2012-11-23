package com.sivalabs.javaee6demo.mdb;

import java.util.Enumeration;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author Siva
 * 
 */
@MessageDriven(activationConfig =
{
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/test"),
		//@ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "MessageFormat = 'Version 3.4'"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "AUTO_ACKNOWLEDGE") })

public class OrderProcessingMDB implements MessageListener
{

	@Override
	public void onMessage(Message msg)
	{
		String content = null;
		try
		{
			content = ((TextMessage)msg).getText();
			System.err.println("Received Message :"+content);
			printJmsMessageHeaders(msg);
			
		} catch (JMSException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void printJmsMessageHeaders(Message msg)
	{
		try
		{
			String jmsCorrelationID = msg.getJMSCorrelationID();
			System.out.println("jmsCorrelationID : "+jmsCorrelationID);
			String jmsMessageID = msg.getJMSMessageID();
			System.out.println("jmsMessageID : "+jmsMessageID);
			long jmsExpiration = msg.getJMSExpiration();
			System.out.println("jmsExpiration : "+jmsExpiration);
			int jmsPriority = msg.getJMSPriority();
			System.out.println("jmsPriority : "+jmsPriority);
			int jmsDeliveryMode = msg.getJMSDeliveryMode();
			System.out.println("jmsDeliveryMode : "+jmsDeliveryMode);
			Destination jmsDestination = msg.getJMSDestination();
			System.out.println("jmsDestination : "+jmsDestination);
			boolean jmsRedelivered = msg.getJMSRedelivered();
			System.out.println("jmsRedelivered : "+jmsRedelivered);
			Destination jmsReplyTo = msg.getJMSReplyTo();
			System.out.println("jmsReplyTo : "+jmsReplyTo);
			long jmsTimestamp = msg.getJMSTimestamp();
			System.out.println("jmsTimestamp : "+jmsTimestamp);
			String jmsType = msg.getJMSType();
			System.out.println("jmsType : "+jmsType);
			
		} catch (JMSException e)
		{
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void printMessageProperties(Message msg)
	{
		try
		{
			Enumeration propertyNames = msg.getPropertyNames();
			while(propertyNames.hasMoreElements()) 
			{
				String name = (String)propertyNames.nextElement();
				Object value = msg.getObjectProperty(name);
				System.out.println("\nname="+value);
			}
		} catch (JMSException e)
		{
			e.printStackTrace();
		}
	}

}

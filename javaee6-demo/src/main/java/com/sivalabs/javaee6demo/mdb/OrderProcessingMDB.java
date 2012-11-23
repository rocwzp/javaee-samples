package com.sivalabs.javaee6demo.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
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
		} catch (JMSException e)
		{
			e.printStackTrace();
		}
		System.err.println("Received Message :"+content);
	}

}

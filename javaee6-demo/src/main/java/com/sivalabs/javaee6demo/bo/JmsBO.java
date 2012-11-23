package com.sivalabs.javaee6demo.bo;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @author Siva
 *
 */
@Stateless
public class JmsBO
{
	@Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;
 
	@Resource(mappedName = "java:/queue/test")
    private Queue queue;
	
	public void sendMessage(String queueName, String msg)
	{

		System.out.println("inserting message : "+msg);
		try
	      {
	         Connection connection = connectionFactory.createConnection();
	         Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
	         MessageProducer messageProducer = session.createProducer(queue);
	         connection.start();
	        
	         TextMessage message = session.createTextMessage();
             message.setText(msg);
             messageProducer.send(message);
	 
	         System.out.println("Message sent successfully");
	         session.close();
	         connection.close();
	      }
	      catch (Exception e)
	      {
	         e.printStackTrace();
	      }
		
	
	}
}

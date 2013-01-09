package com.sivalabs.javaee6demo.scheduling;

import java.util.Date;
import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Stateless;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * @author Siva
 *
 */
@Stateless
public class TwitterFeedPoller
{
	

	//@Schedule(/*second="5",*/ minute="*", hour="*")
	@Schedule(second="0", minute="*/15", hour="*")
	public void scheduleTest()
	{
		System.err.println("====================Scheduler Test:"+new Date()+"=========================");
	
	}
	@Schedule(second="15", minute="*", hour="*")
	public void pollTweets()
	{
		System.err.println("====================Getting Twitter Feed :"+new Date()+"=========================");
		// The factory instance is re-useable and thread safe.
	    Twitter twitter = TwitterFactory.getSingleton();
	    List<Status> statuses;
		try
		{
			statuses = twitter.getHomeTimeline();
			System.out.println("Showing home timeline.");
		    for (Status status : statuses) {
		        System.out.println(status.getUser().getName() + ":" +status.getText());
		    }
		} catch (TwitterException e)
		{
			e.printStackTrace();
		}
	    
	}
	
}

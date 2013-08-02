package com.sivalabs.tweeter.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sivalabs.tweeter.ejbs.TweeterServiceBean;
import com.sivalabs.tweeter.entities.Tweet;
import com.sivalabs.tweeter.entities.User;
import com.sivalabs.tweeter.qualifiers.LoggedinUser;

/**
 * @author Siva
 *
 */
@Named
@RequestScoped
public class TweeterController implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject @LoggedinUser
	private User loginUser;
	
	@Inject
	private TweeterServiceBean tweeterServiceBean;
	
	private Tweet newTweet;
	
	private List<Tweet> tweets;
	private List<Tweet> myTweets;
	
	@PostConstruct
	void init()
	{
		logger.info("Loading all Tweets..");
		tweets = tweeterServiceBean.getTweets();
		if(loginUser != null){
			logger.info("Getting Tweets for UserId :"+loginUser.getId());
			myTweets = tweeterServiceBean.getUserTweets(loginUser.getId());
		}
	}
	
	public void saveTweet()
	{
		//System.err.println("----------->"+newTweet);
		tweeterServiceBean.createTweet(newTweet);
		newTweet = null;
		logger.info("Loading all Tweets..");
		tweets = tweeterServiceBean.getTweets();
		if(loginUser != null){
			logger.info("Getting Tweets for UserId :"+loginUser.getId());
			myTweets = tweeterServiceBean.getUserTweets(loginUser.getId());
		}
		
	}
	
	public Tweet getNewTweet()
	{
		if(newTweet == null){
			newTweet = new Tweet();
			newTweet.setCreatedBy(loginUser);
		}
		//System.err.println("getNewTweet----------->"+newTweet);
		return newTweet;
	}
	public void setNewTweet(Tweet newTweet)
	{
		this.newTweet = newTweet;
	}
	public List<Tweet> getTweets()
	{
		return tweets; 
	}
	
	public List<Tweet> getMyTweets()
	{
		return myTweets; 
	}
	
}

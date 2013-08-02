/**
 * 
 */
package com.sivalabs.tweeter.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Siva
 *
 */
@Entity
@Table(name="USERS")
public class User implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(unique=true, nullable=false)
	private String userName;
	@Column(nullable=false)
	private String password;
	private String name;
	@Column(unique=true, nullable=false)
	private String email;
	@Temporal(TemporalType.DATE)
	private Date dob;
	private boolean disabled;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="createdBy")
	private Set<Tweet> tweets = null;
	
	public User()
	{
	}
	
	public User(Integer id)
	{
		this.id = id;
	}
	
	public User(Integer id, String userName, String password, String name, String email)
	{
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.email = email;
	}
	
	public User(Integer id, String userName, String password, String name,
			String email, Date dob, boolean disabled)
	{
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.email = email;
		this.dob = dob;
		this.disabled = disabled;
	}

	@Override
	public String toString()
	{
		return "User [id=" + id + ", userName=" + userName + ", password="
				+ password + ", name=" + name + ", email=" + email + ", dob="
				+ dob + ", disabled=" + disabled + "]";
	}

	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public Date getDob()
	{
		return dob;
	}
	public void setDob(Date dob)
	{
		this.dob = dob;
	}
	public boolean isDisabled()
	{
		return disabled;
	}
	public void setDisabled(boolean disabled)
	{
		this.disabled = disabled;
	}
	public Set<Tweet> getTweets()
	{
		if(tweets == null){
			tweets = new HashSet<Tweet>();
		}
		return tweets;
	}
	public void setTweets(Set<Tweet> tweets)
	{
		this.tweets = tweets;
	}
	public void addTweet(Tweet tweet)
	{
		this.getTweets().add(tweet);
	}
	public void addTweets(Set<Tweet> tweets)
	{
		this.getTweets().addAll(tweets);
	}
	
	
}

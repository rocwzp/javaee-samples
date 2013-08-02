package com.sivalabs.tweeter.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Siva
 *
 */
@Entity
@Table(name="TWEETS")
public class Tweet implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable=false, length=512)
	private String message;
	@ManyToOne
	@JoinColumn(name="created_by", nullable=false)
	private User createdBy;
	@Column(name="created_on", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn = new Date();
	
	public Tweet()
	{
	}
	
	public Tweet(Integer id)
	{
		this.id = id;
	}
	
	public Tweet(Integer id, String message, User createdBy, Date createdOn)
	{
		this.id = id;
		this.message = message;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}

	@Override
	public String toString()
	{
		return "Tweet [id=" + id + ", message=" + message + ", createdBy="
				+ createdBy + ", createdOn=" + createdOn + "]";
	}

	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getMessage()
	{
		return message;
	}
	public void setMessage(String message)
	{
		this.message = message;
	}
	public User getCreatedBy()
	{
		return createdBy;
	}
	public void setCreatedBy(User createdBy)
	{
		this.createdBy = createdBy;
	}
	public Date getCreatedOn()
	{
		return createdOn;
	}
	public void setCreatedOn(Date createdOn)
	{
		this.createdOn = createdOn;
	}

}

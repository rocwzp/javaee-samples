package com.sivalabs.tweeter.view;

import java.io.Serializable;

/**
 * @author Siva
 *
 */
public class ChangePasswordBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String oldPwd;
	private String newPwd;
	private String confPwd;
	
	public Integer getUserId()
	{
		return userId;
	}
	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}
	public String getOldPwd()
	{
		return oldPwd;
	}
	public void setOldPwd(String oldPwd)
	{
		this.oldPwd = oldPwd;
	}
	public String getNewPwd()
	{
		return newPwd;
	}
	public void setNewPwd(String newPwd)
	{
		this.newPwd = newPwd;
	}
	public String getConfPwd()
	{
		return confPwd;
	}
	public void setConfPwd(String confPwd)
	{
		this.confPwd = confPwd;
	}
	
}

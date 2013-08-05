/**
 * 
 */
package com.sivalabs.tweeter.ejbs;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.sivalabs.tweeter.entities.Tweet;
import com.sivalabs.tweeter.entities.User;
import com.sivalabs.tweeter.exceptions.TweeterException;
import com.sivalabs.tweeter.view.ChangePasswordBean;

/**
 * @author Siva
 *
 */
@Stateless
public class TweeterServiceBean
{

	@PersistenceContext
	private EntityManager em;
	
	public User login(String loginId, String password)
	{
		String sql = "select u from User u where (u.userName=:loginId or u.email=:loginId) and u.password=:password " +
					 " and (u.disabled is null or u.disabled= false)";
		TypedQuery<User> query = em.createQuery(sql, User.class);
		query.setParameter("loginId", loginId);
		query.setParameter("password", password);
		List<User> list = query.getResultList();
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}
	
	public User createUser(User user)
	{
		boolean userNameExists = em.createQuery("select count(u) from User u where u.userName = '"+user.getUserName()+"'", Long.class).getSingleResult() > 0;
		if(userNameExists){
			throw new TweeterException("UserName ["+user.getUserName()+"] already exists.");
		}
		boolean emailExists = em.createQuery("select count(u) from User u where u.email = '"+user.getEmail()+"'", Long.class).getSingleResult() > 0;
		if(emailExists){
			throw new TweeterException("Email ["+user.getEmail()+"] already exists.");
		}
		
		em.persist(user);
		return user;
	}
	
	public User getUserById(Integer userId)
	{
		return em.find(User.class, userId);
	}
	
	public User getUserByUserName(String userName)
	{
		TypedQuery<User> query = em.createQuery("select u from User u where u.userName=:userName", User.class);
		query.setParameter("userName", userName);
		List<User> list = query.getResultList();
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}
	
	public User getUserByEmail(String email)
	{
		TypedQuery<User> query = em.createQuery("select u from User u where u.email=:email", User.class);
		query.setParameter("email", email);
		List<User> list = query.getResultList();
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}
	
	public Tweet createTweet(Tweet tweet)
	{
		em.persist(tweet);
		return tweet;
	}
	
	public List<Tweet> getTweets()
	{
		return em.createQuery("select t from Tweet t order by createdOn desc", Tweet.class).getResultList();
	}
	
	public List<Tweet> getUserTweets(Integer userId)
	{
		User user = this.getUserById(userId);
		if(user == null){
			return null;
		}
		return new ArrayList<Tweet>(user.getTweets());
	}

	public boolean updatePassword(ChangePasswordBean changePasswordBean)
	{
		Query query = em.createQuery("update User u set u.password=:newPwd where u.id=:id and u.password=:oldPwd");
		query.setParameter("id", changePasswordBean.getUserId());
		query.setParameter("oldPwd", changePasswordBean.getOldPwd());
		query.setParameter("newPwd", changePasswordBean.getNewPwd());
		
		return query.executeUpdate()>0;
	}

	public User updateUser(User updateUser)
	{
		if(this.getUserById(updateUser.getId()) != null){
			em.merge(updateUser);
			return updateUser;
		}
		return null;
		
	}
	
}

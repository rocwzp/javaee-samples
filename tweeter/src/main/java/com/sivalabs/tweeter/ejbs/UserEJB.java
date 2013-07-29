/**
 * 
 */
package com.sivalabs.tweeter.ejbs;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.sivalabs.tweeter.entities.User;

/**
 * @author Siva
 *
 */
@Stateless
public class UserEJB {

	@PersistenceContext
	private EntityManager em;
	
	/*
	@PostConstruct
	void init()
	{
		try {
			User user = new User();
			user.setEmail("admin@gmail.com");
			user.setPassword("admin");
			user.setName("Administrator");
			em.persist(user);
			System.err.println("Created Admin User successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	public User login(String email, String password)
	{
		TypedQuery<User> query = em.createQuery("select u from User u where u.email=?1 and u.password=?2", User.class);
		query.setParameter(1, email);
		query.setParameter(2, password);
		List<User> list = query.getResultList();
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}
	
	public void createUser(User user)
	{
		em.persist(user);
	}
}

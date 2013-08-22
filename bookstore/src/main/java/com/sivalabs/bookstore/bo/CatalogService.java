package com.sivalabs.bookstore.bo;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sivalabs.bookstore.entities.Category;

/**
 * @author Siva
 *
 */
@Stateless
public class CatalogService
{
	@PersistenceContext
	private EntityManager em;
	
	public List<Category> getCategories()
	{
		return em.createQuery("select distinct c from Category c join fetch c.products", Category.class).getResultList();
	}
	
	public Category getCategory(Integer id)
	{
		return em.find(Category.class, id);
	}
		
}

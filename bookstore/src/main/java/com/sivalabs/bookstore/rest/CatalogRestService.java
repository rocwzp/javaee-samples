/**
 * 
 */
package com.sivalabs.bookstore.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sivalabs.bookstore.entities.Category;

/**
 * @author Siva
 *
 */
@Stateless
@Path("/catalog")
public class CatalogRestService 
{
	@PersistenceContext
	private EntityManager em;
	
	@Path("/")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getCatalog()
	{
		List<Category> categories = em.createQuery("select distinct c from Category c join fetch c.products", Category.class).getResultList();
		/*List<Category> categoriesClone = new ArrayList<Category>();
		for (Category category : categories) {
			categoriesClone.add(category.getCopy());
		}*/
		CatalogResponse response = new CatalogResponse();
		//response.setCategories(categoriesClone);
		response.setCategories(categories);
		return Response.ok().entity(response).build();
	}
}

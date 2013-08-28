/**
 * 
 */
package com.sivalabs.bookstore.rest;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.sivalabs.bookstore.entities.Category;

/**
 * @author Siva
 *
 */
@XmlRootElement
public class CatalogResponse {

	
	private List<Category> categories;
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	@XmlElement(name="category")
	@XmlElementWrapper(name="categories")
	public List<Category> getCategories() {
		return categories;
	}
}

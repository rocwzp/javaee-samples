package com.sivalabs.bookstore.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.sivalabs.bookstore.bo.CatalogService;
import com.sivalabs.bookstore.entities.Category;

/**
 * @author Siva
 *
 */
@ManagedBean
@ViewScoped
public class CatalogController implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private CatalogService catalogService;
	
	private List<Category> categories;
	
	@PostConstruct
	void init()
	{
		try
		{
			categories = catalogService.getCategories();
		} catch (Exception e)
		{
			categories = new ArrayList<Category>();
			e.printStackTrace();
		}
	}
	
	public List<Category> getCategories()
	{
		return categories;
	}
}

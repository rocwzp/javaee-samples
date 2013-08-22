package com.sivalabs.bookstore.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.sivalabs.bookstore.entities.Product;
import com.sivalabs.bookstore.model.LineItem;
import com.sivalabs.bookstore.model.ShoppingCart;

/**
 * @author Siva
 *
 */
@ManagedBean
@SessionScoped
public class CartController implements Serializable
{
	private static final long serialVersionUID = 1L;
	private ShoppingCart shoppingCart = new ShoppingCart();
	
	public ShoppingCart getShoppingCart()
	{
		return shoppingCart;
	}
	public int getCartItemCount()
	{
		return shoppingCart.getProductsCount();
	}
	public void addItem(Product product)
	{
		LineItem item = shoppingCart.getLineItem(product.getId());
		if(item!= null){
			item.setQuantity(item.getQuantity()+1);
			JSFUtils.addInfoMessage("Increased Product '"+product.getName()+"' quantity to "+item.getQuantity());
		} else {
			item = new LineItem();
			item.setProduct(product);
			item.setQuantity(1);
			shoppingCart.addItem(item);
			JSFUtils.addInfoMessage("Added Product '"+product.getName()+"' to Cart.");
		}
	}
}


package com.shoppingApplication.module.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import com.shoppingApplication.module.shoppingitems.Item;


public class ShoppingCart {

	private List<Item> items;
	
	public ShoppingCart() {
		items=new ArrayList<Item>();
	}
	
	public void addItem(Item item) {
		items.add(item);
	}

	public List<Item> getItems() {
		return items;
	}
}

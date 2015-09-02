
package com.shoppingApplication.service;

import java.io.IOException;

import com.shoppingApplication.module.shoppingcart.ShoppingCart;
import com.shoppingApplication.module.shoppingitems.Item;

public interface ICashCounterService {

	public void processShoppingcartAndGenerateReciept(ShoppingCart shoppingCart)throws IOException ;
	public void cashOutItem(Item item);
}

/**
 * This factory class creates Item using the
 * item description from file. It sets the item 
 * categories and adds {@link TaxCategories} as 
 * per eligibility of tax exemption.
 */
package com.shoppingApplication.module.shoppingitems;

import java.util.Set;

import com.shoppingApplication.types.ItemCategories;
import com.shoppingApplication.types.TaxCategories;
import com.shoppingApplication.utils.InvalidItemException;
import com.shoppingApplication.utils.ItemNotFoundException;

public class ItemFactory {
	/**
	 * 
	 * @param itemDescription - this is the line read 
	 * by ShoppingList.
	 * @return
	 * @throws InvalidItemException
	 * @throws ItemNotFoundException 
	 */
	public static Item createItem(String itemDescription)
			throws InvalidItemException, ItemNotFoundException {
		Item item = null;
		Set<String> keySet = ItemCategories.getItemVsItemCategories()
		.keySet();
		boolean itemFound=false;
		String itemDes=null;
		for (String string : keySet) {
			if (itemDescription.contains(string)){
				itemFound=true;
				itemDes=string;
				break;
			}
		}
		if (!itemFound) {
			throw new ItemNotFoundException("Item Not Found:: " + itemDescription);
		}
		String[] strings = itemDescription.split(" ");
		try {
			item = new Item();
			item.setQuantity(Integer.parseInt(strings[0].trim())); 
			item.setPrice(Float.parseFloat(strings[strings.length - 1]));
			for (int i = 1; i < strings.length - 2; i++) {
				item
						.setDescription(item.getDescription() == null ? ""
								+ strings[i] : item.getDescription() + " "
								+ strings[i]);
			}
			if (itemDescription.indexOf("imported") > 0) {
				item.getTaxCategories().add(TaxCategories.ADDITIONAL);
			}
			
				if (itemFound
						&& ItemCategories.getItemVsItemCategories().get(itemDes) != null
						&& !ItemCategories.getItemVsItemCategories()
								.get(itemDes).isBasicTaxExempted()) {
					item.getTaxCategories().add(TaxCategories.BASIC);
				}

				if (itemFound) {
					item.setCategory(ItemCategories.getItemVsItemCategories()
							.get(itemDes));
			}
		} catch (NumberFormatException e) {
			InvalidItemException invalidItemException = new InvalidItemException(
					"Invalid Item :: " + itemDescription);
			invalidItemException.setSourceException(e);
			throw invalidItemException;
		}
		return item;
	}

}

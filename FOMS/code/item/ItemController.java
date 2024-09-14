package item;

import enumpack.Category;

/**
 * Provides functions to edit the properties of a menu item, including its item ID, price, and category.
 * @author FDAA Grp 5
 */
public class ItemController {
    private Item item;

    /**
     * Constructs an ItemEditor associated with a specific Item.
     * @param item the menu Item to be edited
     */
    public ItemController(Item item) {
        this.item = item;
    }

    /**
     * Edits the item ID of the associated item.
     * @param itemId the new item ID to set for the item
     * @return true if the item ID was changed successfully, false if the new ID is the same as the current ID or if the item is null.
     */
    public boolean editId(String itemId) {
        
    	if (this.item == null || itemId == this.item.getId()) {
            return false;
        }

        this.item.setId(itemId);
       
        return true;
    
    }

    /**
     * Edits the price of the associated item.
     * @param price the new price to set for the item
     * @return true if the price was changed successfully, false if the new price is the same as the current price or if the item is null.
     */
    public boolean editPrice(double price) {
        
    	if (this.item == null || price == this.item.getPrice()) {
            return false;
        }

        this.item.setPrice(price);
       
        return true;
    
    }

    /**
     * Edits the category of the associated item.
     * @param category the new category to set for the item
     * @return true if the category was changed successfully, false if the new category is the same as the current category or if the item is null.
     */
    public boolean editCategory(Category category) {
       
    	if (this.item == null || category == this.item.getCategory()) {
            return false;
        }

        this.item.setCategory(category);
        
        return true;
    }
}

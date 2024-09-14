package branch;

import item.Item;

/**
 * Manages item operations within a branch, including adding, retrieving, and removing items.
 * @author FDAA Grp 5
 */
public class ItemController {
    private Branch branch;

    
    /**
     * Constructs an ItemController object associated with a specific branch.
     * @param branch the branch this ItemController is associated with
     */
    public ItemController(Branch branch) {
        
    	this.branch = branch;
    }

    /**
     * returns the item based on the itemID
     * @param itemId refers to the actual itme in the csv file
     * @return the item, if found otherwise return null
     */
    public Item getItem(String itemId) {
        
    	for (Item item : this.branch.getItemList()) {
           
    		if (itemId.equals(item.getId())) {
                return item;
            }
        }
        
    	return null;
    }

    /**
     * allowed to add an item to the branch list if it doesnt already exist
     * @param item refers to the new item to be added
     * @return the item added if id actually exits, otherwise returns null
     */
    public boolean addItem(Item item) {
        
    	if (getItem(item.getId()) != null) {
            return false;
        }
        
        this.branch.itemList.add(item);
        
        return true;
    }

    
    /**
     * allowed to remove an item to the branch list
     * @param itemId gived us the id of the item that we have to remove
     * @return the item removed ad return null when the id is not found
     */
    public Item removeItem(String itemId) {
        Item item = getItem(itemId);
        
        if (item == null) {
            return null;
        }
        
        this.branch.itemList.remove(item);
        
        return item;
    }
}

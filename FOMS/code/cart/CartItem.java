package cart;

import item.Item;

/**
 * Represents an item within a order cart, extending the basic properties of an item with the addition of quantity.
 * @author FDAA Grp 5
 */
public class CartItem extends Item {
	
	/**
	 * quantity of the items in the cart
	 */
    private int quantity;
    
    /**
     *  Constructs a new CartItem based on an existing Item object and a specified quantity.
     * @param item the item ot be added to the cart
     * @param quantity the qunatity of the item to be added to the caart
     */
    public CartItem(Item item, int quantity) {
       
    	super(item.getId(), item.getName(), item.getPrice(), item.getCategory(), item.getDescription());
        this.quantity = quantity;
    
    }

    /**
     * returns the quantity of the items in the cart
     * @return  quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * sets the quantity of items in the cart
     * @param quantity is set
     */
    public void setQuantity(int quantity) {
       
    	this.quantity = quantity;
   
    }

}

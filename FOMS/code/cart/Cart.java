package cart;

import java.util.ArrayList;

/**
 * Represents a order cart which holds a list of menu items chosen by the customer.
 * @author FDAA Grp 5
 */
public class Cart {
	
	/**
	 * List of items in the cart
	 */
    protected ArrayList<CartItem> cartItemList;

    /**
     * to insantiate the list of items in the cart
     */
    public Cart() {
        
    	this.cartItemList = new ArrayList<CartItem>();
    }

    /**
     * returns the list of items in the cart
     * @return list of items in the cart
     */
    public ArrayList<CartItem> getCartItemList() {
        return cartItemList;
    }

    /**
     * returns the total price of items in the cart
     * @return total price of items in cart
     */
    public double getTotalPrice() {
        
    	double totalPrice = 0;
        
    	for (CartItem cartItem : cartItemList) {
            totalPrice += cartItem.getPrice() * cartItem.getQuantity();
        }
        
    	return totalPrice;
    }

    /**
     * checks if the cart is empty
     * @return true if the cart is empty otherwise returns false
     */
    public boolean isEmpty() {
       
    	return cartItemList.isEmpty();
    
    }

    /**
     * clears all the items from the cart
     */
    public void clearCart() {
       
    	cartItemList.clear();
   
    }
}
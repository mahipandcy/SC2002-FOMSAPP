package cart;

/**
 * Manages the operations related to the cart, such as adding, retrieving, and removing cart items.
 * @author FDAA Grp 5
 */
public class CartController {
	
	/**
	 * cart is instantiated
	 */
    private Cart cart;

    /**
     * constructs an object to manage a specific cart
     * @param cart the cart to be managed
     */
    public CartController(Cart cart) {
        this.cart = cart;
    }

    /**
     * returns the id of the item in the cart
     * @param itemId the itemID of the cart
     * @return the item in the card if the id is valid otherwise return null
     */
    public CartItem getCartItem(String itemId) {
        
    	for (CartItem cartItem: this.cart.cartItemList) {
           
    		if (itemId.equals(cartItem.getId())) {
                return cartItem;
            }
        }
        
    	return null;
    }

    /**
     * adds an item to the cart
     * @param newCartItem the new item to be added to the cart
     */
    public void addCartItem(CartItem newCartItem) {
        
    	for (CartItem cartItem: this.cart.cartItemList) {
           
    		if (newCartItem.getId() == cartItem.getId()) {
                cartItem.setQuantity(cartItem.getQuantity() + newCartItem.getQuantity());
                return;
            }
        }
       
    	this.cart.cartItemList.add(newCartItem);
    }

    /**
     * removes an item from the cart
     * @param itemId the itemID of the item to be removeed form the cart
     * @return the removed cartitem if found otherwise return null
     */
    public CartItem removeCartItem(String itemId) {
       
    	CartItem cartItem = getCartItem(itemId);
       
    	if (cartItem == null) {
            return null;
        }
        
    	this.cart.cartItemList.remove(cartItem);
        
    	return cartItem;
    }
}

package operation.customerui;

import java.util.InputMismatchException;
import java.util.Scanner;

import cart.Cart;
import cart.CartItem;
import cart.CartController;
import exceptionhandling.InputOutOfRangeException;

/**
 * Provides an interactive user interface for managing the order cart.
 * This includes viewing order cart items, editing their quantity, removing items, or clearing the cart.
 * @author FDAA Grp 5
 */

class CartView {
	
    private Scanner sc;
    
    private Cart cart;
    
    private CartController cartManagement;

    
    /**
     * constructs the view of the cart providing an interface for management
     * @param sc scanner reads in the users input
     * @param cart the cart to be managed
     */
    CartView(Scanner sc, Cart cart) {
        
    	this.sc = sc;
        this.cart = cart;
        this.cartManagement = new CartController(cart);
        System.out.println("---CART---");
        this.displayCart();
        System.out.println("Please enter your action:");
        System.out.println("1. Edit  the item in Cart");
        System.out.println("2. Remove the item from Cart");
        System.out.println("3. Clear Cart");
        System.out.println("4. Go Back");
        int cartActionChoice = sc.nextInt();
       
        try {
            switch (cartActionChoice) {
               
            case 1:
                    this.editCartItem();
                    break;
                
                case 2:
                    this.removeCartItem();
                    break;
                
                case 3:
                    this.cart.clearCart();
                    break;
                
                case 4:
                    return;
               
                default:
                    throw new InputOutOfRangeException();
            }
        }
        
        catch (InputMismatchException e) {
            System.out.println("Invalid input has been entered. Are you sure you entered the right one ?");
            sc.next();
        }
       
        catch (InputOutOfRangeException e) {
            System.out.println("Invalid input has been entered. Are you sure you entered the right one ?");
        }
       
        finally {
            System.out.println();
        }
    }

    /**
     * displays the cart along with the subtotal of the items in the cart
     */
    
    private void displayCart() {
    	
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Cart Items:");
        for (CartItem cartItem : this.cart.getCartItemList()) {
            System.out.println("Item ID:\t" + cartItem.getId());
            System.out.println("Name:\t" + cartItem.getName());
            System.out.println("Price:\t$" + cartItem.getPrice());
            System.out.println("Category:\t" + cartItem.getCategory());
            System.out.println("Quantity:\t" + cartItem.getQuantity());
            System.out.println();
        }
        System.out.println("Total Price: " + this.cart.getTotalPrice());
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println();
    }

    /**
     * provides an interface for editing the quantity of the items in the cart
     * if the new quantity is zero the items is removed from the cart
     */
    
    private void editCartItem() {
    	
        System.out.println("---Edit Item from the cart---");
        System.out.print("Enter Item ID: ");
        String itemId = this.sc.next();
        CartItem editedCartItem = this.cartManagement.getCartItem(itemId);
       
        if (editedCartItem != null) {
           
        	System.out.print("Enter new Quantity: ");
            int newQuantity = sc.nextInt();
           
            try {
                if (newQuantity < 0) {
                    throw new InputOutOfRangeException();
                }
                if (newQuantity == 0) {
                    this.cartManagement.removeCartItem(itemId);
                }
                else {
                    editedCartItem.setQuantity(newQuantity);
                }
            }
           
            catch (InputMismatchException e) {
                System.out.println("Invalid input has been entered. Are you sure you entered the right one ?");
                sc.next();
            }
           
            catch (InputOutOfRangeException e) {
                System.out.println("Invalid input has been entered. Are you sure you entered the right one ?");
            }
            
            finally {
                System.out.println();
            }
            
            return;
        }
       
        System.out.println("Item ID cannot be found!");
    }
    
    /**
     * provides an interface to remove an item from the cart
     * the user is prompted to enter the item to be removed
     */
    

    private void removeCartItem() {
    	
        System.out.println("---Remove Item from Cart---");
        System.out.print("Enter Item ID: ");
        String itemId = this.sc.next();
        CartItem removedCartItem = this.cartManagement.getCartItem(itemId);
        
        if (removedCartItem != null) {
            
        	this.cartManagement.removeCartItem(itemId);
            System.out.println(removedCartItem.getQuantity() + " " + removedCartItem.getName() + "(s) has been successfully removed from your Cart!");
            return;
        }
        
        System.out.println("Item ID cannot be found!");
    }
}

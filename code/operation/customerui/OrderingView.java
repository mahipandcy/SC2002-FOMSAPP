package operation.customerui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import cart.Cart;
import exceptionhandling.InputOutOfRangeException;
import item.Item;

/**
 * Manages the customer order interface allowing to viewing the menu, managing the cart and checking out for payment
 * @author FDAA 5
 */

class OrderingView {
	
    private boolean checkedOut = false;
    private Cart cart = new Cart();
    
    /**
     * constructs an ordering view that handles the flow of the customer actions including adding items to the cart, viewing the cart and checking out
     * @param sc scanner reads in input from the user
     * @param menu menu is the list of items to order from
     */
    

    OrderingView(Scanner sc, ArrayList<Item> menu) {
    	
        while (true) {
            System.out.println("Please choose your action:");
            System.out.println("1. Go to Menu");
            System.out.println("2. Go to Cart");
            System.out.println("3. Checkout to make payment");
            System.out.println("4. Go Back");
            System.out.print("Enter your choice: ");
            try {
            	
                int customerActionChoice = sc.nextInt();
                System.out.println();
                switch (customerActionChoice) {
                
                    case 1:
                    	
                        new MenuView(sc, menu, this.cart);
                        break;
                        
                    case 2:
                    	
                        new CartView(sc, this.cart);
                        System.out.println("");
                        break;
                        
                    case 3:
                    	
                        if (this.cart.isEmpty()) {
                        	
                            System.out.println("You have no item in your cart! Please try again.");
                            break;
                        }
                        
                        else {
                        	
                            checkedOut = true;
                            return;
                        }
                        
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
    }
    
    /**
     * allows you to exit once you are done adding items to the cart
     * @return
     */
    

    public boolean isCheckedOut() {
        return checkedOut;
    }
    
    /**
     * returns the cart 
     * @return return cart
     */
    

    public Cart getCart() {
        return cart;
    }
}

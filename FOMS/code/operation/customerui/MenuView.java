package operation.customerui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import cart.Cart;
import cart.CartItem;
import cart.CartController;
import exceptionhandling.InputOutOfRangeException;
import item.Item;


/**
 * /**
 * Provides a user interface for displaying a menu of items and managing interactions for adding items to the cart.
 * @author FDAA 5
 */

class MenuView {
	
    private Scanner sc;
    private ArrayList<Item> menu;
    private CartController cartManagement;

    /**
     * defualt constructor for initialising a menu view without setting any intial properties
     */
    
    MenuView() {
    	
    }
    
    /**
     * constructs a menu view with the tiem list of the menu and the cart 
     * @param sc scanner reads in all the input
     * @param menu consists if the list of itesm for the customers choose from
     * @param cart the cart has the list of items chosen by the customer from the mneu
     */
    
    MenuView(Scanner sc, ArrayList<Item> menu, Cart cart) {
    	
        this.sc = sc;
        this.menu = menu;
        this.cartManagement = new CartController(cart);
        this.displayMenu();
        System.out.println("Please choose your action:");
        System.out.println("1. Add item to the cart");
        System.out.println("2. Go back");
        System.out.print("Enter your choice: ");
        
        try {
        	
            int menuActionChoice = sc.nextInt();
            System.out.println();
            switch (menuActionChoice) {
                case 1:
                    this.addItemToCart();
                    break;
                case 2:
                    return;
                
                default:
                    throw new InputOutOfRangeException();
                }
        }
        
        catch (InputMismatchException e) {
        	
            System.out.println("Invalid input has been entered. Are you sure you entered the right input ?");
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
     * displays the menu in the formatted options of id,name,price,category and description
     */
    

    private void displayMenu() {
    	
        System.out.println("---MENU---");
        System.out.format("%-10s %-30s %-10s %-20s %-50s%n", "ID", "Name", "Price", "Category", "Description");

        for (Item item : this.menu) {
        	
            System.out.format("%-10s %-30s %-10.2f %-20s %-50s%n", 
                    item.getId(), 
                    item.getName(), 
                    item.getPrice(), 
                    item.getCategory(), 
                    item.getDescription());
    }
    System.out.println();


    }
    
    /**
     * adds an items from the menu to the cart based on user input
     * prompts the user to specify the items id of the item to be added to the cart
     */
    

    private void addItemToCart() {
    	
        System.out.println("---Add item to cart---");
        System.out.print("Enter Item ID: ");
        String itemId = sc.next();
        for (Item item: this.menu) {
        	
            if (itemId.equals(item.getId())) {
            	
                System.out.print("Enter quantity: ");
                int quantity = sc.nextInt();
                try {
                	
                    if (quantity == 0) {
                    	
                        throw new InputOutOfRangeException();
                    }
                    
                    this.cartManagement.addCartItem(new CartItem(item, quantity));
                    System.out.println("Item added successfully!");
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
        }
        System.out.println("Item ID cannnot be found!");
    }
}

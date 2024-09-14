package operation.staffui;

import java.util.ArrayList;
import java.util.Scanner;

import branch.Branch;
import branch.ItemController;
import branch.OrderController;
import item.Item;
import order.Order;
import order.OrderDisplay;
import order.OrderProcessor;
import enumpack.OrderStatus;
import staff.Manager;
import staff.Staff;

/**
 * provides an interface for branch managers to perfom management tasks
 * @author FDAA 5
 */


class ManagerView {
	
	private Branch branch;
    private ArrayList<Order> orderList;
    private OrderController orderManagement;
    
    /**
     * constructs a manager vieew to provide management functionalities at a branch level 
     * @param sc scanner used to read in the user input
     * @param manager the manager logged in 
     * @param branch the brranch at which the maanger operates
     */
    
    
    ManagerView(Scanner sc, Manager manager, Branch branch) {
    	
        this.branch = branch;
        this.orderList = this.branch.getOrderList();
        this.orderManagement = new OrderController(this.branch);
        
        while (true) {
        	
        	System.out.println("\n--------------------");
            System.out.println("MANAGER LOGIN");
            System.out.println("--------------------");

            System.out.println("Choose your action:");
            System.out.println("1. Display new Orders");
            System.out.println("2. View the details of a particular Order");
            System.out.println("3. Process Order");
            System.out.println("4. Change Password");
            System.out.println("5. Display the Staff List");
            System.out.println("6. Add a new menu item");
            System.out.println("7. Update a menu item");
            System.out.println("8. Remove a menu item");
            System.out.println("9. Return to Start");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            
            switch (choice) {
            
                case 1:
                	
                    System.out.println("---Display new Orders---");
                    this.displayNewOrders();
                    break;
                    
                case 2:
                	
                    System.out.println("---View the details of a particular Order---");
                    System.out.print("Enter the Order ID: ");
                    String orderIdToView = sc.next();
                    this.viewOrder(orderIdToView);
                    break;
                    
                case 3:
                	
                    System.out.println("---Process Order---");
                    System.out.print("Enter the Order ID: ");
                    String orderIdToProcess = sc.next();
                    processOrder(orderIdToProcess);
                    break;
                    
                case 4:
                	
                	System.out.println("---Change Password---");
                	System.out.println("Enter Old Password: ");
                	String oldPassword = sc.next();
                	
                	if (!oldPassword.equals(manager.getPassword())) {
                		
                		System.out.println("Incorrect Old Password");
                		break;
                	}
                	
                	System.out.println("Enter New Password: ");
                	String newPassword1 = sc.next();
                	System.out.println("Confirm New Password: ");
                	String newPassword2 = sc.next();
                	
                	if (newPassword1.equals(newPassword2)) {
                		
                		manager.setPassword(newPassword2);
                		System.out.println("Password has been changed");
                	}
                	
                	else System.out.println("New passwords do not match. Are you sure you entered the right one ?");
                	break;
                	
                case 5:
                	
                	displayStaffList();
                	break;
                	
                case 6:
                	
                	addNewMenuItem(sc);
                	break;
                	
                case 7:
                	
                	updateMenuItem(sc);
                	break;
                	
                case 8:
                	
                	removeMenuItem(sc);
                	break;
                	
                case 9:
                	
                    System.out.println("Returning to the start");
                    return;
                    
                default:
                	
                    break;
            }
        }
    }
    
    /**
     * displays orders with the staus NEW to manager
     */
    

    private void displayNewOrders() {
    	
    	if (this.orderList.isEmpty()) System.out.println("No pending orders are there");
    	
        for (Order order : this.orderList) {
        	
            if (order.getOrderStatus() == OrderStatus.NEW) {
            	
                new OrderDisplay(order);
            }
        }
    }
    
    /**
     * allows the maanger to view the detailed information about a  specific order
     * @param orderId the order id of the order to be viewed by the amanger 
     */
    

    private void viewOrder(String orderId) {
    	
        Order order = this.orderManagement.getOrder(orderId);
        
        if (order == null) {
        	
            System.out.println("Order ID " + orderId + " cannot be found.");
            return;
        }
        
        new OrderDisplay(this.orderManagement.getOrder(orderId));
    }
    
    /**
     * processes the order by updating its status from NEW to READY_TO_PICKUP
     * @param orderId the orderid of the order which is ready to be picked up
     */
    

    private void processOrder(String orderId) {
    	
        Order order = this.orderManagement.getOrder(orderId);
        
        if (order == null) {
        	
            System.out.println("Order ID " + orderId + " cannot be found.");
            return;
        }
        
        OrderProcessor orderProcessor = new OrderProcessor(order);
        
        if (orderProcessor.setReadyToPickup() == false) {
        	
            System.out.println("Order ID " + orderId + " has already been processed.");
        }
        
        else {
        	
            System.out.println("Order ID " + orderId + " has been processed successfully.");
        }

    }
    /**
     * displays a list of al staff members along with their details 
     */
    
    
    private void displayStaffList() {
    	
    	if (branch.getStaffList().isEmpty()) System.out.println("No staff can be found");
    	
    	for (Staff staff : branch.getStaffList()) {
    		
    		System.out.println("Name: " + staff.getName() + ", Age " + staff.getAge() + ", Gender: " + staff.getGender());
    	}
    }
    
    /**
     * handles the addition of a new menu item based on the user inputs
     * @param sc the scanner reads in the user input
     */
    
    
    private void addNewMenuItem(Scanner sc) {
    	
    	System.out.println("Enter the new item in the following format:\nItemId,Name,Price,Category,Description");

    	if (sc.hasNextLine()) sc.nextLine();
    	
    	String itemString = sc.nextLine();
    	String[] itemParts = itemString.split(",");
    	
    	if (itemParts.length != 5) {
    		
    		System.err.println("Error adding a new menu item: Invalid format");
    		return;
    	}
    	
    	try {
    		
    		String itemId = itemParts[0].trim();
        	String name = itemParts[1].trim();
        	Double price = Double.parseDouble(itemParts[2].trim());
        	String category = itemParts[3].trim().toUpperCase();
        	String description = itemParts[4].trim();
        	new ItemController(branch).addItem(new Item(itemId, name, price, category, description));
        	System.out.println(itemId + " has been successfully added to the menu");
    	} 
    	
    	catch (NullPointerException e) {
    		
    		System.err.println("Error adding a new menu item: " + e.getMessage());
    	} 
    	
    	catch (NumberFormatException e) {
    		
    		System.err.println("Error adding a new menu item: " + e.getMessage());
    	} 
    	
    	catch (Exception e) {
    		
    		System.err.println("Error adding a new menu item: " + e.getMessage());
    	}
    }
    
    /**
     * handles updating the existing menu items based on the user inputs 
     * @param sc the scanner used to take user input for updating item details
     */
    
    
    private void updateMenuItem(Scanner sc) {
    	
    	System.out.println("---MENU---");
        System.out.println("ID\t\t\tName\t\t\tPrice\t\t\tCategory\t\t\tDescription");
        
        for (Item item: branch.getItemList()) {
        	
            System.out.println(item.getId() + "\t\t\t" + item.getName() + "\t\t\t" + item.getPrice() + 
              "\t\t\t" + item.getCategory() + "\t\t\t" + item.getDescription());
        }
        
        System.out.print("\nSelect Item ID: ");


        if (sc.hasNextLine()) sc.nextLine();
        
        String itemId = sc.next().trim();        
        Item item = findItem(itemId);
        
        if (item == null) {
        	
        	System.out.println("Item not found");
        	return;
        }
        
        System.out.println("What do you want to update regarding the item ?");
		System.out.println("1. ID");
		System.out.println("2. Name");
		System.out.println("3. Price");
		System.out.println("4. Description");
		System.out.println("5. Cancel");
		
		int choice = sc.nextInt();
		if (sc.hasNextLine()) sc.nextLine();
		
		try {
			
			switch (choice) {
			
    			case 1:
    				
    				System.out.println("Enter a new ID: ");
    				String newId = sc.next().trim();
    				item.setId(newId);
    				System.out.println("ID has been updated");
    				break;
    				
    			case 2:
    				
    				System.out.println("Enter a new Name: ");
    				String newName = sc.nextLine().trim();
    				item.setName(newName);
    				System.out.println("Name has been updated");
    				break;
    				
    			case 3:
    				
    				System.out.println("Enter a new Price: ");
    				Double newPrice = sc.nextDouble();
    				item.setPrice(newPrice);
    				System.out.println("Price has been updated");
    				break;
    				
    			case 4:
    				
    				System.out.println("Enter a new Description: ");
    				String newDescription = sc.nextLine().trim();
    				item.setDescription(newDescription);
    				System.out.println("Description  has been updated");
    				break;
    				
    			case 5:
    				
				default:
					
    				return;
    		}
		} 
		
		catch (Exception e) {
			
			System.err.println("Error in updating the item: " + e.getMessage());
		}
    }
    
    /**
     * Handles the removal of a menu item based on the user confirmation 
     * @param sc the scanner reads in the user  input
     */
    
    
    private void removeMenuItem(Scanner sc) {
    	
    	System.out.println("---MENU---");
        System.out.println("ID\t\t\tName\t\t\tPrice\t\t\tCategory\t\t\tDescription");
        
        for (Item item: branch.getItemList()) {
        	
            System.out.println(item.getId() + "\t\t\t" + item.getName() + "\t\t\t" + item.getPrice() + 
              "\t\t\t" + item.getCategory() + "\t\t\t" + item.getDescription());
        }
        System.out.print("\nSelect Item ID: ");

        if (sc.hasNextLine()) sc.nextLine();
        
        String itemId = sc.next().trim();        
        Item item = findItem(itemId);
        
        if (item == null) {
        	
        	System.out.println("Item cannot be found");
        	return;
        }
        System.out.println(item.getId() + "\t\t\t" + item.getName() + "\t\t\t" + item.getPrice() + 
          "\t\t\t" + item.getCategory() + "\t\t\t" + item.getDescription());
        System.out.println("Confirm remove?\n1. Yes\n2. No");

        if (sc.hasNextLine()) sc.nextLine();
        
        int choice = sc.nextInt();
        
        switch (choice) {
        
        	case 1:
        		
        		new ItemController(branch).removeItem(itemId);
        		System.out.println("Item has been removed");
        		break;
        		
        	case 2:
        		
    		default:
    			
    			return;
        }
    }
    
    /**
     * finds and returns an item from the branch's menu list based on an itemID 
     * @param itemId the ID of the item to find 
     * @return the item if found otherwise return null
     */
    
    private Item findItem(String itemId) {
    	
    	ArrayList<Item> itemList = branch.getItemList();
    	
    	if (itemList.isEmpty()) return null;
    	
    	for (Item item : itemList) {
    		
    		if (itemId.equals(item.getId())) return item;
    		
    	}
    	
    	return null;
    }
}

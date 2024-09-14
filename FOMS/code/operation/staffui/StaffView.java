package operation.staffui;

import java.util.ArrayList;
import java.util.Scanner;

import branch.Branch;
import branch.OrderController;
import order.Order;
import order.OrderDisplay;
import order.OrderProcessor;
import staff.Staff;
import enumpack.OrderStatus;

/**
 * provides an interactive interface for staff members to manage orders and perform other tasks 
 * @author FDAA 5
 */

class StaffView {
	
    private Branch branch;
    private ArrayList<Order> orderList;
    private OrderController orderManagement;
    
    /**
     * Constructs a StaffView that enables a staff member to manage orders and personal settings at a specific branch.
     *
     * @param sc the scanner used for input collection
     * @param staff the staff member logged into the system
     * @param branch the branch at which the staff operates
     */
    
    
    StaffView(Scanner sc, Staff staff, Branch branch) {
    	
        this.branch = branch;
        this.orderList = this.branch.getOrderList();
        this.orderManagement = new OrderController(this.branch);
        
        while (true) {
        	
        	System.out.println("\n--------------------");
            System.out.println("STAFF LOGIN");
            System.out.println("--------------------");

            System.out.println("Choose your action:");
            System.out.println("1. Display new Orders");
            System.out.println("2. View the details of a particular Order");
            System.out.println("3. Process an Order");
            System.out.println("4. Change your Password");
            System.out.println("5. Return to Start");
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
                	
                	if (!oldPassword.equals(staff.getPassword())) {
                		
                		System.out.println("Incorrect Old Password");
                		break;
                		
                	}
                	
                	System.out.println("Enter New Password: ");
                	String newPassword1 = sc.next();
                	System.out.println("Confirm New Password: ");
                	String newPassword2 = sc.next();
                	
                	if (newPassword1.equals(newPassword2)) {
                		
                		staff.setPassword(newPassword2);
                		System.out.println("Password has been successfully changed");
                	}
                	
                	else System.out.println("New passwords do not match.Are you sure you entered the right one ?");
                	
                	break;
                	
                case 5:
                	
                    System.out.println("Returning to the start");
                    return;
                    
                default:
                	
                    break;
            }
        }
    }
    
    /**
     * displays a list of new orders that have not yet been processed
     */
    

    private void displayNewOrders() {
    	if (this.orderList.isEmpty()) System.out.println("No pending orders are there.");
        for (Order order : this.orderList) {
            if (order.getOrderStatus() == OrderStatus.NEW) {
                new OrderDisplay(order);
            }
        }
    }
    
    /**
     * provides detailed views of a specific order based on the order id provided by the staff
     * @param orderId the id of the order to view
     */
    

    private void viewOrder(String orderId) {
    	
        Order order = this.orderManagement.getOrder(orderId);
        
        if (order == null) {
        	
            System.out.println("Order ID " + orderId + " cannnot be found.");
            return;
        }
        
        new OrderDisplay(this.orderManagement.getOrder(orderId));
    }
    
    /**
     * processes an order to change its status to read to pick up if aplicable
     * @param orderId the id of the order that has to be processed
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
}

package operation.customerui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import branch.Branch;
import branch.OrderController;
import exceptionhandling.InputOutOfRangeException;
import order.Order;
import order.OrderProcessor;
import enumpack.OrderStatus;;

/**
 * provides an interface for the customers to view the orders by their status and manage pick up actions
 * @author FDAA 5
 */

class View {
	
	/**
     * Initializes the view interface for order management, allowing customers to view orders
     * and initiate pick-up if orders are ready.
     * @param sc the scanner used for input collection
     * @param branch the branch from which the orders are managed
     */
    
    View (Scanner sc, Branch branch) {
    	
        ArrayList<Order> orderList = branch.getOrderList();
        System.out.println("List of Orders:");
        System.out.println("NEW:");
        
        for (Order order : orderList) {
        	
            if (order.getOrderStatus() == OrderStatus.NEW) {
            	
                System.out.println(order.getOrderId());
            }
        }
        
        System.out.println();
        System.out.println("READY TO PICK UP:");
        for (Order order : orderList) {
        	
            if (order.getOrderStatus() == OrderStatus.READY_TO_PICKUP) {
            	
                System.out.println(order.getOrderId());
                
            }
            
        }
        
        System.out.println();
        System.out.println("Do you want to pick up your order?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.print("Enter your choice: ");
        
        int pickUpChoice = sc.nextInt();
        System.out.println();
        
        try {
        	
            switch (pickUpChoice) {
            
                case 1:
                	
                    System.out.print("Enter order ID: ");
                    String orderId = sc.next();
                    
                    for (Order order : orderList) {
                    	
                        if (order.getOrderId().equals(orderId)) {
                        	
                            if (order.getOrderStatus().equals(OrderStatus.READY_TO_PICKUP)) {
                            	
                                order = new OrderController(branch).getOrder(orderId);
                                new OrderProcessor(order).setPickedUp();
                                System.out.println("Pick up successfully!");
                                return;
                            }
                            
                            System.out.println("Your order is not ready yet to be picked up. Please wait for sometime. Sorry for the inconvenience caused .");
                            return;
                        }
                    }
                    System.out.println("Order cannot be found!");
                    return;
                    
                case 2:
                	
                    return;
                    
                default:
                	
                    throw new InputOutOfRangeException();
            }
        } 
        
        catch (InputOutOfRangeException e) {
        	
            System.out.println("Invalid Input has been entered. Are you sure you entered the right one ?");
        } 
        
        catch (InputMismatchException e) {
        	
            System.out.println("Invalid Input has been entered. Are you sure you entered the right one ");
            sc.next();
        }
    }
}

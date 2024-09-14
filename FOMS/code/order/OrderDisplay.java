package order;

import cart.CartItem;

/**
 * displays the details of an order in a formatted text output to the console
 * @author FDAA 5
 */

public class OrderDisplay {
	
	/**
	 * constructs and order display object that ooutputs the details of a specified order to the console
	 * @param order the order whose detials are to be displayed
	 */
    
	
    public OrderDisplay(Order order) {
    	
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("-------------");
        System.out.println("Item has been successfully Ordered:");
        
        for (int i = 0; i < order.getItemOrdered().size(); i++) {
        	
            CartItem cartItem = order.getItemOrdered().get(i);
            System.out.println(cartItem.getName());
        }
        
        System.out.println("-------------");
        System.out.println("Order Status:\t" + order.getOrderStatus().toString());
        System.out.println("Dining Option:\t" + order.getDiningOption().toString());
        System.out.println("Payment Mode:\t" + order.getPaymentMode());
    }
}
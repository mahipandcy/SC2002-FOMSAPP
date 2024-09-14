package operation.customerui;

import order.Order;
import order.OrderDisplay;

/**
 * /**
 * Provides a straightforward mechanism for displaying a receipt for an order.
 * This class is designed to instantiate an OrderDisplay to show order details.
 *@author FDAA 5
 */

class Receipt {
	
	/**
	 * constructs a receipt display for the customer based on the order
	 * @param order  of the customer
	 */
    
    Receipt(Order order) {
        new OrderDisplay(order);
    }
}

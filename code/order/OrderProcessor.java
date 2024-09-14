package order;

import enumpack.OrderStatus;

/**
 * manages the processing stage of an order, including setting it as ready to pick up or marking it as picked up
 * @author FDAA 5
 */

public class OrderProcessor {
	
    private Order order;
    
    /**
     * constructs an order processor for a specific order
     * @param order the order to be processed 
     */
    

    public OrderProcessor(Order order) {
    	
        this.order = order;
    }
    
    /**
     * sets the status of the order as ready to pick up if the order status is still new
     * @return true if the order status was successful otherwise return false
     */
    

    public boolean setReadyToPickup() {
    	
        if (this.order == null || this.order.getOrderStatus() != OrderStatus.NEW) {
        	
            return false;
        }

        this.order.setOrderStatus(OrderStatus.READY_TO_PICKUP);
        
        return true;
    }
    
    /**
     * sets the order status as picked up if the the order staus was still ready to pick up
     * @return true if the order status was successful otherwise return false
     */
    

    public boolean setPickedUp() {
    	
        if (this.order == null || this.order.getOrderStatus() != OrderStatus.READY_TO_PICKUP) {
        	
            return false;
        }

        this.order.setOrderStatus(OrderStatus.PICKED_UP);
        
        return true;
    }
}

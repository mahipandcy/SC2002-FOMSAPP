package enumpack;

/**
 * Defines the various statuses an order can have throughout its lifecycle, 
 * ranging from newly placed to ready for pickup, picked up, or cancelled.
 * @author FDAA Grp 5
 */
public enum OrderStatus {
    
	NEW,
	
	READY_TO_PICKUP,
	
	PICKED_UP ,
	
	CANCELLED
	
} 

package branch;

import order.Order;

/**
 * Manages order operations within a branch, including adding, retrieving, removing, and checking orders.
 * @author FDAA Grp 5
 */
public class OrderController {
    private Branch branch;

    /**
     * Constructs an OrderController object associated with a specific branch.
     * @param branch the branch this OrderController is associated with
     */
    public OrderController(Branch branch) {
        this.branch = branch;
    }

    
    /**
     * Retrieves an order by its ID from the branch's order list.
     * @param orderId the ID of the order to retrieve
     * @return the Order if found, null otherwise
     */
    public Order getOrder(String orderId) {
        
    	for (Order order : this.branch.getOrderList()) {
            
    		if (orderId.equals(order.getOrderId())) {                
            	return order;
            }
        }
       
    	return null;
    }

    /**
     * Adds a new order to the branch's order list if it does not already exist and increments the order ID.
     * @param order the order to be added
     * @return true if the order was added successfully, false if it already exists in the list
     */
    public boolean addOrder(Order order) {
       
    	if (getOrder(order.getOrderId()) != null) {
            return false;
        }
        
        this.branch.orderList.add(order);
        this.branch.increaseOrderId();
        
        return true;
    }

    /**
     * Removes an order from the branch's order list by its ID.
     * @param orderId the ID of the order to be removed
     * @return the removed Order if successful, null if the order does not exist
     */
    public Order removeOrder(String orderId) {
        
    	Order order = getOrder(orderId);
       
    	if (order == null) {
            return null;
        }
        
        this.branch.orderList.remove(order);
        
        return order;
    }

    /**
     * Checks an order for its cancellation status.
     * @param orderId the ID of the order to check
     * This method prints the cancellation status of the order directly.
     */
    public void checkOrder(String orderId){
       
    	Order order = getOrder(orderId);
        
    	if(order.checkForCancellation()){
            System.out.println("Order" + order.getOrderId() + "has been cancelled");
       }
   
    }

    
}

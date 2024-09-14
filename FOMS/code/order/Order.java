package order;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.Duration;
import enumpack.OrderStatus;
import enumpack.DiningOption;
import cart.CartItem;

/**
 * represents an order placed by a customer, including detials about the items ordered
 * @author FDAA 5
 */

public class Order {

    private String orderId;
    private ArrayList<CartItem> itemOrdered;
    private OrderStatus orderStatus = OrderStatus.NEW;
    private DiningOption diningOption;
    private String paymentMode;
    private LocalDateTime orderTime;
    
    /**
     * constructs a new order with specified details
     * @param orderId the unique identifiers for the order
     * @param itemOrdered th list of the cart items that were ordered
     * @param diningOption the dining option selected for the order
     * @param paymentMode the payment mode selected for the order
     */
    

    public Order(String orderId, ArrayList<CartItem> itemOrdered, DiningOption diningOption, String paymentMode) {
    	
        this.orderId = orderId;
        this.itemOrdered = itemOrdered;
        this.diningOption = diningOption;
        this.paymentMode = paymentMode;
        this.orderTime = LocalDateTime.now();
    }
    
    /**
     * returns the orderid of the particular order
     * @return the orderid
     */
    

    public String getOrderId() {
    	
        return this.orderId;
    }
    
    /**
     * returns the item ordered
     * @return the item ordered
     */
    

    public ArrayList<CartItem> getItemOrdered() {
    	
        return this.itemOrdered;
    }
    
        
    /**
     * returns the dining option of the particular order
     * @return the dining option
     */
    

    public DiningOption getDiningOption() {
    	
        return this.diningOption;
    }
    
    /**
     * returns the mode of payment customer wishes to pay by
     * @return the payment mode
     */
    

    public String getPaymentMode() {
    	
        return this.paymentMode;
    }
    
    /**
     * returns the status of the order
     * @return the order status 
     */
    

    public OrderStatus getOrderStatus() {
    	
        return this.orderStatus;
    }

    
    /**
     * sets the sttus of the order 
     * @param orderStatus the new status to set for the order
     */


    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    /**
     * checks if the order can be cancelled based on the time elapsed since the making of the order
     * @return the orderstaus as cancelled if true otherwise return false
     */
    

    public boolean checkForCancellation(){
        if(orderStatus == OrderStatus.READY_TO_PICKUP && Duration.between(orderTime, LocalDateTime.now()).toMinutes() > 30){
            this.orderStatus = OrderStatus.CANCELLED;
            return true;
        }
        return false;
    }
}

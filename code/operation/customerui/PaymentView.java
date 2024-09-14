package operation.customerui;

import java.util.Random;
import java.util.Scanner;

import cart.Cart;
import order.Order;
import payment.PaymentProcessor; 
import enumpack.DiningOption;

/**
 * /**
 * Manages the payment interface for customers, allowing them to select dining options, payment modes,
 * and proceed to finalize their order.
 *@author FDAA 5
 */

class PaymentView {
	
    private DiningOption diningOption;
    private String paymentMode;
    private boolean successPayment = false;
    private Order order;
    
    /**
     * constructs a payment view that handles the selection of dining and payment options
     * @param sc scanner reads in the input from the user
     * @param cart the cart containing the sleected items
     */
    

    PaymentView(Scanner sc, Cart cart) {
    	
        System.out.println("Please choose your dining option:");
        System.out.println("1. Dine In");
        System.out.println("2. Takeaway");
        System.out.print("Enter your choice: ");
        int diningOptionChoice = sc.nextInt();
        
        switch (diningOptionChoice) {
        
            case 1:
            	
                diningOption = DiningOption.DINE_IN;
                break;
                
            case 2:
            	
                diningOption = DiningOption.TAKEAWAY;
                break;
        }

        PaymentProcessor paymentProcessor = new PaymentProcessor(); 
        System.out.println("Please proceed to payment:");
        System.out.println("Available Payment Methods:");
        
        for (String method : paymentProcessor.getPaymentMethods()) {
        	
            System.out.println(method);
        } 
        System.out.println("Please enter the method: ");
        paymentMode = sc.next(); 
        successPayment = paymentProcessor.processPayment(paymentMode);
        
        if (successPayment) {
        	
            System.out.println("Successful payment");
        } 
        
        else {
        	
            System.out.println("Please try again");
        }
        
        this.order = new Order(this.generateOrderId(5), cart.getCartItemList(), diningOption, paymentMode);
    }
    
    /**
     * returns whether or not the payment is successful or not
     * @return
     */
    

    public boolean isSuccessPayment() {
        return successPayment;
    }
    
    /**
     * returns the order
     * @return the order
     */
    

    public Order getOrder() {
        return order;
    }
    
    /**
     * generates a random numeric order id of specified length
     * @param orderIdLength provides the length of the randomly generated order id
     * @return the order id generated
     */
    
    public String generateOrderId(int orderIdLength) {
       
    	int leftLimit = 48; // number '0'
        int rightLimit = 57; // number '9'

        Random random = new Random();
        StringBuilder buffer = new StringBuilder(orderIdLength);

        // Generate the numeric characters
        for (int i = 0; i < orderIdLength; i++) {
        	
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }

        String generatedString = buffer.toString();
        return generatedString;
    }
}

package payment;

/**
 * this class implements the interface to show the various payment methods
 * @author FDAA 5
 */
public class PaymentMethod implements IPayment {
    private boolean available;

    /**
     * constructor to initialize the payment method 
     * if the payment method is avilable it assigns true
     */
    public PaymentMethod() {
        this.available = true; // Payment method is available by default
    }

    /**
     * processes payment through the chosen payment method 
     * @return true if successful otherwise returns false
     */
    public boolean processPayment() {
        // Placeholder logic for processing payment
        System.out.println("Payment Successful");
        return true; // Placeholder return value
    }

    /**
     * sets the availability as available for the specified payment method
     */
    public void setAvailability(boolean available) {
        this.available = available;
    }

    /**
     * checks if the payment method is available or not
     * @return available if the payment method is available
     */
    public boolean isAvailable() {
        return available;
    }

}

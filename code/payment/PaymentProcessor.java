package payment;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * this processes the payment made by the customer
 * @author FDAA 5
 */
public class PaymentProcessor {
    private Map<String, IPayment> paymentMethods;

    /**
     * through the hashmap and according to the cutomer interface the appropriate payment method is utilized
     */
    public PaymentProcessor() {
        paymentMethods = new HashMap<>();
        // Initialize with default payment methods
        paymentMethods.put("card", new PaymentMethod());
        paymentMethods.put("online", new PaymentMethod());
    }

    /**
     * allows you to add a new payment method
     * @param method the payment method to be added
     */
    public void addPaymentMethod(String method) {
        paymentMethods.put(method, new PaymentMethod());
        System.out.println("Payment method added!");
    }

    /**
     * processes the payment after the payment method has been specified
     * @param method the method chosen by the customer
     * @return true if payment is successful otherwise return false
     */
    public boolean processPayment(String method) {
        IPayment payment = paymentMethods.get(method);
        if (payment != null && payment.isAvailable()) {
            return payment.processPayment();
        }
        return false;
    }

    /**
     * sets the availability of the payment method
     * @param method the method which is checked for its availability
     * @param available the availability of the payment method 
     */
    public void setAvailability(String method, boolean available) {
        IPayment payment = paymentMethods.get(method);
        if (payment != null) {
            payment.setAvailability(available);
        }
    }

    /**
     * returns the payment methods
     * @return the payment method
     */
    public Set<String> getPaymentMethods() {
        return paymentMethods.keySet();
    }
}

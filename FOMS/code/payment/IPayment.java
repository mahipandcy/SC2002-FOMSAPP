package payment;

/**
 * Implements the IPayment interface to provide functionality for processing payments using an online payment method.
 * This class allows toggling the availability of online payment and handles payment transactions if online payment is available.
 * @author FDAA 5
 */
public interface IPayment {
	
	/**
	 * Processes a payment transaction using online payment mode.
	 * @param amount the amount to be paid
	 * @return true if the payment was successful otherwise return false
	 */
    public boolean processPayment();
    
    /**
     * sets the availability of the online payment mode
     * @param available the availability status to set for the online payment mode
     */
    public void setAvailability(boolean available);
    
    /**
     * checks if the online payment mode is currently available
     * @return true if the online payment mode is available otherwise return false
     */
    public boolean isAvailable();
}


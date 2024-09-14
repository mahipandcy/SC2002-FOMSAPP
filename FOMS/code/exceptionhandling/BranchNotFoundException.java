package exceptionhandling;

/**
 * Exception thrown when a specific branch cannot be found.
 * This can be used to handle error scenarios where a branch lookup fails
 * due to non-existence in the system's records.
 */
public class BranchNotFoundException extends Exception {

	/**
     * Constructs a BranchNotFoundException with a default message.
     */
	public BranchNotFoundException() {
		
		super("Branch not found");
	
	}
	
	/**
     * Constructs a BranchNotFoundException with a custom message.
     * @param message the detail message.
     */
	public BranchNotFoundException(String message) {
		
		super(message);
	}
}
	
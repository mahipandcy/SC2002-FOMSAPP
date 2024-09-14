package exceptionhandling;

/**
 * Exception thrown when an input value is outside the expected range.
 * This can be used to signal errors during validation processes where specific input
 * values are required to meet defined criteria or limits.
 */
public class InputOutOfRangeException extends Exception {
   
	/**
     * Constructs an InputOutOfRangeException with no detail message.
     */
	public InputOutOfRangeException() {
       
		super();
    
	}
	
	/**
     * Constructs an InputOutOfRangeException with a specific detail message.
     * @param errorMessage the detail message to provide more context about the exception cause.
     */
    public InputOutOfRangeException(String errorMessage) {
        
    	super(errorMessage);
    
    }

}

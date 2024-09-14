package branch;

/**
 * Provides functionality to manage and validate opening and closing hours for a branch.
 * @author FDAA Grp 5
 */

public class OpeningTimeController {
    
	private Branch branch;

    /**
     * /**
     * Constructs an OpeningTimeController associated with a specific branch.
     * @param branch the branch whose opening times are to be managed
     */
    public OpeningTimeController(Branch branch) {
        
    	this.branch = branch;
    }

    
    /**
     * validates the time of opening
     * @param hour specifies the time of opening
     * @return true if hour is valid otherwise return false
     */
    private boolean isValidHour(int hour) {
        
    	if (hour < 0 || hour > 2400) {
            return false;
        }
       
    	return true;
    }

    /**
     * validates the time span
     * @param beginHour shows the starting hour
     * @param endHour shows the ending hour
     * @return true if time span is valid otherwise return false
     */
    private boolean isValidTimeSpan(int beginHour, int endHour) {
       
    	if (beginHour > endHour) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Sets the opening and closing hours for the branch if the provided times are valid.
     * @param openingHour the new opening hour, must be less than the closing hour and within 0 to 2400
     * @param closingHour the new closing hour, must be greater than the opening hour and within 0 to 2400
     * @return true if the hours were set successfully, false if the provided times are invalid
     */
    public boolean setOpeningTime(int openingHour, int closingHour) {
       
    	if (!isValidHour(openingHour) || !isValidHour(closingHour) || 
        !isValidTimeSpan(openingHour, closingHour)) {
            
    		return false;
        }
        
        this.branch.setOpeningHour(openingHour);
        this.branch.setClosingHour(closingHour);
        
        return true;
    }
}

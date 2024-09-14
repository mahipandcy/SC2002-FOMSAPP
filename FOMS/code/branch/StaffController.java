package branch;

import staff.Staff;


/**
 * Manages staff operations within a branch, including adding, retrieving, and removing staff members.
 * @author FDAA Grp 5
 */
public class StaffController {
    private Branch branch;

    /**
     * Constructs a StaffController object associated with a specific branch.
     * @param branch the branch this StaffController is associated with
     */
    public StaffController(Branch branch) {
        this.branch = branch;
    }

    /**
     * Retrieves a staff member by their user ID from the branch's staff list.
     * @param staffId the ID of the staff member to retrieve
     * @return the Staff if found, null otherwise
     */
    public Staff getStaff(String staffId) {
        
    	for (Staff staff : this.branch.staffList) {
            
    		if (staffId.equals(staff.getUserId())) {
                return staff;
            }
        }
        
    	return null;
    }

    /**
     * Adds a new staff member to the branch's staff list if the staff quota has not been reached
     * and the staff member does not already exist.
     * @param staff the staff member to be added
     * @return true if the staff member was added successfully, false if the staff quota has been reached or the staff member already exists
     */
    public boolean addStaff(Staff staff) {
        
    	if (this.branch.staffList.size() == this.branch.getStaffQuota() ||
                this.getStaff(staff.getUserId()) != null) {  
            
    		return false;
        }

        this.branch.staffList.add(staff);
        
        return true;
    }

    /**
     * Removes a staff member from the branch's staff list by their user ID.
     * @param staffId the ID of the staff member to be removed
     * @return the removed Staff if successful, null if the staff member does not exist
     */
    public Staff removeStaff(String staffId) {
       
    	Staff staff = this.getStaff(staffId);
        
    	if (staff == null) {
            return null;
        }

        this.branch.staffList.remove(staff);
        
        return staff;
    }
}


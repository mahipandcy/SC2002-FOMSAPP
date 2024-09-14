package staff;

import branch.Branch;
import enumpack.Gender;

/**
 * Provides functionalities for editing various attributes of a Staff member.
 * @author FDAA 5
 */
public class StaffEditor {
	
    private Staff staff;

    /**
     * constructs a staff editor associated with a specific staff member
     * @param staff the staff member whose attributes are to be edited
     */
    public StaffEditor(Staff staff) {
        this.staff = staff;
    }

    /**
     * edits the branch of the staff
     * @param branch the new branhc to be assigned to the staff
     * @return true if the branch was successful otherwise return false
     */
    public boolean editBranch(Branch branch) {
        if (this.staff == null || branch == this.staff.getBranch()) {
            return false;
        }
        
        this.staff.setBranch(branch);
        return true;
    }
    
    /**
     * edits the user id of the staff
     * @param userId the new user id to be assigned to the staff
     * @return true if the new id was successful otherwise return false
     */
    public boolean editUserId(String userId) {
        if (this.staff == null || userId == this.staff.getUserId()) {
            return false;
        }
        
        this.staff.setUserId(userId);
        return true;
    }

    /**
     * edits the name of the staff
     * @param name the new name of the staff
     * @return true if the new name was successful othewise return false
     */
    public boolean editName(String name) {
        if (this.staff == null || name == this.staff.getName()) {
            return false;
        }
        
        this.staff.setName(name);
        return true;
    }

    /**
     * edits the gender of the staff
     * @param gender the new gender of the staff
     * @return true if the new gender was successful otherwise return false
     */
    public boolean editGender(Gender gender) {
        if (this.staff == null || gender == this.staff.getGender()) {
            return false;
        }

        this.staff.setGender(gender);
        return true;    
    }

    /**
     * edits the age of the staff
     * @param age the new age of the staff
     * @return true if the new age was successful otherwise return false
     */
    public boolean editAge(int age) {
        if (this.staff == null || age == this.staff.getAge()) {
            return false;
        }

        this.staff.setAge(age);
        return true;    
    }
}

package staff;

import system.User;
import branch.Branch;
import branch.OrderController;
import enumpack.UserType;
import enumpack.Gender;

/**
 * /**
 * Represents a staff member within a branch
 * @author FDAA 5
 */

public class Staff extends User {
	
    protected Branch branch;
    protected OrderController orderManagement;

    /**
     * constructs a staff member with the specified format
     * @param branch the branch of the staff
     * @param staffId the unique id for each staff
     * @param password the login password for each staff
     * @param name the name of the staff
     * @param gender the gender of the staff
     * @param age the age of the staff
     */
    
    public Staff(Branch branch, String staffId, String password, String name, Gender gender, int age) {
        super(UserType.S, staffId, password, name, gender, age, branch.getBranchName());
        this.branch = branch;
        this.orderManagement = new OrderController(branch);
    }

    /**
     * gets the branch location of the staff
     * @return the branch 
     */
    
    public Branch getBranch() {
        return this.branch;
    }

    /**
     * sets the branch of the staff
     * @param branch the braanch the staff is at
     */
    public void setBranch(Branch branch) {
        this.branch = branch;
        this.branchName = branch.getBranchName();
    }

    /**
     * displays a list of all the orders within the branch
     */
    public void displayOrderList() {
        for (int i = 0; i <= this.branch.getOrderList().size(); i++) {
        }
    }

    /**
     * provides a detialed view of the details of the order
     * @param orderId the id of the order to be viewed
     * @return true if the order is found and return false otherwise
     */
    public boolean viewOrder(int orderId) {
        return true;
    }

    /**
     * processs a specific order by changing its staus or handling other actions
     * @param orderId the id of the order to be processed
     * @return true if the order is successfully processed and return false otherwise
     */
    public boolean processOrder(int orderId) {
        return true;
    }
}


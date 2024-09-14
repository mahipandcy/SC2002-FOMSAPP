package system;

import branch.Branch;


/**
 * controls branches within a system, facilitating the addition, removal, and retrieval of branch information.
 * @author FDAA 5
 */
public class BranchController {
	
	/**
     * Constructs a BranchManagement object associated with a specific database.
     * @param database The database containing branch information.
     */
	
    public BranchController(Database database) {
    }

    /**
     * Retrieves a branch from the database based on the branch name.
     * @param branchName The name of the branch to retrieve.
     * @return The Branch object if found, null otherwise.
     */
    public Branch getBranch(String branchName) {
        for (Branch branch : Database.branchList) {
        	
            if (branchName.equals(branch.getBranchName())) {
            	
                return branch;
            }
        }
        return null;
    }

    /**
     * adds a branch from the database based on the branch name
     * @param branch the branch to be added
     * @return ture if successful in adding the branch otherwise return false
     */
    public boolean addBranch(Branch branch) {
        if (this.getBranch(branch.getBranchName()) != null) {
            return false;
        }

        Database.branchList.add(branch);
        return true;
    }

    /**
     * removes the branch from the database bsaed on the branch name
     * @param branchName the name of the branch to be removed
     * @return the removed branch if successful , otherwise return null
     */
    
    public Branch removeBranch(String branchName) {
        Branch branch = this.getBranch(branchName);
        
        if (branch == null) {
            return null;
        }

        Database.branchList.remove(branch);
        return branch;
    }
}

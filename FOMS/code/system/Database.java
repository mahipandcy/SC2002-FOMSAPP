package system;
import java.io.IOException;
import java.util.ArrayList;
import branch.Branch;
import exceptionhandling.BranchNotFoundException;

/**
 * Handles data storage and retrieval for the system, managing branches and user accounts.
 * @author FDAA 5
 */

public class Database {
    static ArrayList<Branch> branchList = new ArrayList<Branch>();
    static ArrayList<User> accountList = new ArrayList<User>();

    /**
     * Constructs a new Database object, initializing lists for storing branches and user accounts.
     */
    public Database() {

    }
    
    /**
     * Loads data from files into the system's branch and account lists.
     * This method reads branches, accounts, and menu items from CSV files.
     */
    public void loadFiles() {
    	try {
			FileRead.loadBranches("branch_list.csv", branchList);
			FileRead.loadAccounts("staff_list.csv", accountList);
			FileRead.loadItems("menu_list.csv", branchList);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
    }
    
    /**
     * Saves data from the system's lists to files.
     * This method writes branches, accounts, and menu items to CSV files.
     */
    public void saveFiles() {
    	try {
			FileWrite.saveBranches("branch_list.csv");
			FileWrite.saveAccounts("staff_list.csv");
			FileWrite.saveItems("menu_list.csv");
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
    }
    
    /**
     * searches for a branch by its name
     * @param branchName the name of the branch to be found
     * @return the found branch object
     * @throws BranchNotFoundException If no branch with the given name is found. 
     */
    
    public static Branch findBranch(String branchName) throws BranchNotFoundException {
    	
    	for (Branch branch : branchList) {
    		
    		if (branch.getBranchName().equals(branchName)) return branch;
    	}
    	throw new BranchNotFoundException("Branch :\"" + branchName + "\" cannot be found");
    }

    /**
     * retireves the list of branches
     * @return the branch list
     */
    public ArrayList<Branch> getBranchList() {
        return branchList;
    }

    /**
     * retrieves the list of accounts
     * @return the account list
     */
    public ArrayList<User> getAccountList() {
        return accountList;
    }
}

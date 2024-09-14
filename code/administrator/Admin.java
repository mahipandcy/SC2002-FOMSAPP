package administrator;

import java.util.ArrayList;
import java.util.Scanner;
import enumpack.OperationStatus;
import enumpack.UserType;
import branch.Branch;
import branch.ManagerController;
import branch.StaffController;
import filter.Filter;
import staff.Manager;
import staff.Staff;
import system.AccountController;
import system.BranchController;
import system.Database;
import system.User;
import payment.PaymentProcessor; 
import enumpack.Gender;

/**
 * Represents an admin with capabilities to manage users, staff, branches, and payments.
 * The Admin class can perform all the action that the staff and manager can do as well.
 * This class includes functionality for managing accounts, filtering staff accounts, promoting staff, transferring staff 
 * to different branches,
 * and managing payments as well as branch operations.
 * 
 * 
 * @author FDAA Grp 5
 */

public class Admin extends User {
	/**
	 * This is a constructor for admin and creates an instance of the admin class.
	 * @param adminId This is the login ID for the admin instance.
	 * @param password This is the default password to successfully login
	 * @param name This requires the Admin's name
	 * @param gender The admins gender
	 * @param age the admins age 
	 * @param branchName the name of the brranch associated the admin
	 */
    public Admin(String adminId, String password, String name, Gender gender, int age, String branchName) {
    	super(UserType.A, adminId, password, name, gender, age, branchName);
    }
    
    /**
     * this class is used to display accounts of all the staff
     * @param accounts
     * @return it returns true if accounts are not empty and are displayed false otherwise
     */
    public boolean displayStaffAccounts(ArrayList<User> accounts) {
    	if (accounts.isEmpty()) {
    		System.out.println("No accounts have been found");
    		return false;
    	}
    	for (User user : accounts) {
    		System.out.println("Name: " + user.getName() + 
    				", Login Id: " + user.getUserId() +
					", Age: " + user.getAge() +
					", Gender: " + user.getGender() +
    				", Role: " + user.getUserType() +
    				", Branch: " + user.getBranchName());
    	}
    	return true;
    }
    
    /**
     * allows the admin to actually choose the filter to categorize
     * @param sc the scanner sused to read the user input 
     * @param accounts the list of user(all staff) accounts availabale for filtering
     * @param database this is the database containing the user and branch information
     */
    public void chooseFilter(Scanner sc, ArrayList<User> accounts, Database database) {
    	ArrayList<User> filteredList = new ArrayList<User>();
    	Filter filter = new Filter(sc, accounts, database);
    	while (true) {
    		System.out.println("Choose how you want to filter the staff list:");
        	System.out.println("1. Role");
        	System.out.println("2. Branch");
        	System.out.println("3. Gender");
        	System.out.println("4. Age");
        	System.out.println("5. Go Back");
        	
        	if (sc.hasNextLine()) sc.nextLine();
        	int choice = sc.nextInt();
        	switch (choice) {
        		case 1:
        	    	filteredList = filter.roleFilter(); 
        	    	displayStaffAccounts(filteredList);
        	    	break;
        		case 2:
        			filteredList = filter.branchFilter();
        			displayStaffAccounts(filteredList);
        			break;
        		case 3:
        			filteredList = filter.genderFilter();
        			displayStaffAccounts(filteredList);
        			break;
        		case 4:
        			filteredList = filter.ageFilter();
        			displayStaffAccounts(filteredList);
        			break;
        		case 5:
    			default:
    				return;
        	}
    	}
    }
    
    
    /**
     * allows the admin to manage the accounts
     * @param sc scanner is used to read the user input 
     * @param accounts is used to list out all the accounts
     * @param database this is database containing the user and branch information
     */
    public void manageAccounts(Scanner sc, ArrayList<User> accounts, Database database) {
    	AccountController accountManager = new AccountController(sc, accounts, database);
    	while (true) {
    		System.out.println("Choose your action:");
        	System.out.println("1. Add account ");
        	System.out.println("2. Edit account");
        	System.out.println("3. Remove account");
        	System.out.println("4. Go Back");
        	int choice = sc.nextInt();
        	switch (choice) {
        		case 1:
        			accountManager.addAccount();
        			break;
        		case 2:
        			displayStaffAccounts(accounts);
        			accountManager.editAccount();
        			break;
        		case 3:
        	    	displayStaffAccounts(accounts);
        	    	accountManager.removeAccount();
        			break;
        		case 4:
    			default:
    				return;
        	}
    	}
    }
    
    /**
     * this allows the admin to promote the position of the staff to a higher postion
     * @param sc scanner is used to enter the input
     * @param accounts are sued to display the list of account
     * @param database this is the database which contains user and branch inforamtion
     */
    public void promoteStaff(Scanner sc, ArrayList<User> accounts, Database database) {
    	
    	ArrayList<User> staffList = new ArrayList<User>();
    	
    	for (User user : accounts) {
    		if (user.getUserType() == UserType.S) staffList.add(user);
    	}
    	
    	displayStaffAccounts(staffList);
    	System.out.print("Enter Login Id of the Staff you wish to promote: ");

    	if (sc.hasNextLine()) sc.nextLine();
    	String loginId = sc.nextLine();
    	AccountController accountManager = new AccountController(accounts);
    	Staff user = (Staff)accountManager.getUser(loginId);
    	
    	if (user == null) {
        	System.out.println("No staff found with Login Id: " + loginId);
        	return;
    	}
    	
    	Branch branch = user.getBranch();
    	Manager manager = new Manager(branch, user.getUserId(), user.getPassword(), user.getName(), user.getGender(), user.getAge());
    	ManagerController mm = new ManagerController(branch);
    	
    	if (mm.addManager(manager) == false) {
			System.out.println("Unable to promote staff: Manager already exist in branch or Manager quota exceeded");
			return;
    	}
    	
    	accountManager.removeUser(loginId);
    	accountManager.addUser(manager);
    	StaffController sm = new StaffController(manager.getBranch());
    	sm.removeStaff(loginId);
		System.out.println(manager.getName() + " promoted to Manager");
    }
    
    /**
     * this method allows the admin to transfer the staff
     * @param sc scanner reads in the input
     * @param accounts used to display the list of accounts
     * @param database this is the databse which contains the user and branch information
     */
    public void transferStaff(Scanner sc, ArrayList<User> accounts, Database database) {
    	
    	System.out.print("Enter Login Id of Staff/Manager to transfer: ");
    	
    	if (sc.hasNextLine()) sc.nextLine();
    	
    	String loginId = sc.nextLine();
    	AccountController accountManager = new AccountController(accounts);
    	User user = accountManager.getUser(loginId);
    	
    	if (user == null) {
        	System.out.println("No staff found with Login Id: " + loginId);
        	return;
    	}
    	System.out.println("Select branch to transfer to:");
    	int i = 1;
    	ArrayList<Branch> branchList = new ArrayList<>(database.getBranchList());    	
    	branchList.remove(((Staff)user).getBranch());
    	
    	for (Branch branch : branchList) {
    		System.out.println(i++ + ". " + branch.getBranchName());
    	}
    	System.out.println(i + ". Go Back");
    	int choice = sc.nextInt();
    	
    	if (choice > branchList.size() || choice < 1) return;
    	Branch branch = branchList.get(choice-1);
    	
    	if (user.getUserType() == UserType.S) {
    		StaffController sm = new StaffController(branch);
    		Staff staff = (Staff)user;
    		if (sm.addStaff(staff) == false) {
    			System.out.println("Unable to transfer staff: Staff already exist in branch or Staff quota exceeded");
				return;
    		}
    		sm = new StaffController(staff.getBranch());
    		sm.removeStaff(loginId);
    		staff.setBranch(branch);
			System.out.println("Staff transferred to " + branch.getBranchName());
    	} else if (user.getUserType() == UserType.M) {
        	ManagerController mm = new ManagerController(branch);
        	Manager manager = (Manager)user;
        	
        	if (mm.addManager(manager) == false) {
        		System.out.println("Unable to transfer manager: Manager already exist in branch or Manager quota exceeded");
				return;
        	}
        	mm = new ManagerController(manager.getBranch());
        	mm.removeManager(loginId);
        	manager.setBranch(branch);
			System.out.println("Manager transferred to " + branch.getBranchName());
    	}
    }
    
    /**
     * allows you to add or remove payment
     * @param sc scanner allows you to read in the input
     */
    public void addRemovePayment(Scanner sc) {
    	
    	PaymentProcessor pm = new PaymentProcessor();
    	System.out.println("Add/ remove payment:");
    	System.out.println("1. Add");
    	System.out.println("2. Remove");
    	System.out.println("3. Go Back");
    	
    	int choice = sc.nextInt();
    	
    	switch (choice) {
    		
    		case 1:
    			System.out.println("Please input your payment method you want to add: ");
				String addMethod = sc.next(); 
				pm.addPaymentMethod(addMethod);
				break;
    		
    		case 2:
    			System.out.println("Please input your payment method you want to remove: ");
				String removeMethod = sc.next(); 
				pm.setAvailability(removeMethod, false);
				System.out.println("List of payment methods now: ");
				
				for (String method : pm.getPaymentMethods()) {
            		System.out.println(method);
        		} 
				break;
    		
    		case 3:
			
    		default:
				break;
    	}
    }
    
    /**
     * allows admin to open or close a branch
     * @param sc scanner allows you to read in the input
     * @param database contains the user and branch information
     */
    public void openCloseBranch(Scanner sc, Database database) {
    	
    	System.out.println("Select branch:");
    	int i = 1;
    	ArrayList<Branch> branchList = database.getBranchList();
    	
    	for (Branch branch : branchList) {
    		System.out.println(i++ + ". " + branch.getBranchName());
    	}
    	
    	System.out.println(i + ". Go Back");
    	int choice = sc.nextInt();
    	
    	if (choice > branchList.size() || choice < 1) return;
    	
    	Branch branch = branchList.get(choice-1);

		if (branch.getOperationStatus() == OperationStatus.CLOSE) {
			System.out.println("Branch: " + branch.getBranchName() + " is CLOSED.");
			System.out.println("1. Open Branch");
			System.out.println("2. Go Back");

			if (sc.hasNextLine()) sc.nextLine();
			if (sc.nextInt() != 1) return;
			
			branch.setOperationStatus(OperationStatus.OPEN);
			System.out.println("Branch: " + branch.getBranchName() + " is now OPEN.");
		} else if (branch.getOperationStatus() == OperationStatus.OPEN) {
			System.out.println("Branch: " + branch.getBranchName() + " is OPEN.");
			System.out.println("1. Close Branch");
			System.out.println("2. Go Back");

			if (sc.hasNextLine()) sc.nextLine();
			
			if (sc.nextInt() != 1) return;
			
			branch.setOperationStatus(OperationStatus.CLOSE);
			System.out.println("Branch: " + branch.getBranchName() + " is now CLOSED.");
		}
    }
    
    /**
     * allows you to add ir remvoe a branch
     * @param sc scanner allows you to read in the input
     * @param database has the user and branch information
     */
    public void addRemoveBranch(Scanner sc, Database database) {
		
    	BranchController bm = new BranchController(database);
		
    	while (true) {
			System.out.println("Choose your action:");
	    	System.out.println("1. Add Branch");
	    	System.out.println("2. Remove Branch");
	    	System.out.println("3. Go Back");
	    	int choice = sc.nextInt();
	    	
	    	switch (choice) {
	    		case 1:
	    			Branch branch = new Branch("Clementi", "Clementi Mall", 5);
	    			if (bm.addBranch(branch) == false) {
	    				System.out.println("Branch already exists");
	    				break;
	    			}
	    			System.out.println("New branch added");
	    			break;
	    		
	    		case 2:
	    			System.out.println("Select branch to remove:");
	    	    	int i = 1;
	    	    	ArrayList<Branch> branchList = database.getBranchList();
	    	    	
	    	    	for (Branch b : branchList) {
	    	    		System.out.println(i++ + ". " + b.getBranchName());
	    	    	}
	    	    	
	    	    	System.out.println(i + ". Go Back");
	    	    	int c = sc.nextInt();
	    	    	
	    	    	if (c < 1 || c >= i) break;
	    	    	
	    	    	Branch b = branchList.get(c-1);
	    	    	System.out.println("Removing " + b.getBranchName() + " branch will also remove all items and accounts associated with it.");
	    	    	System.out.println("Please confirm to close this branch");
	    	    	System.out.println("1. Yes");
	    	    	System.out.println("2. No");
	    	    	c = sc.nextInt();
	    	    	
	    	    	if (c != 1) break;
	    	    	
	    	    	bm.removeBranch(b.getBranchName());
	    	    	ArrayList<User> accountList = new ArrayList<User>(database.getAccountList());
	    	    	AccountController am = new AccountController(database.getAccountList());
	    	    	
	    	    	for (User user : accountList) {
	    	    		if (user.getBranchName().equals(b.getBranchName()))
	    	    			am.removeUser(user.getUserId());
	    	    	}
	    	    	
	    	    	System.out.println("Branch has been successfully removed");
	    	    	break;
	    		
	    		case 3:
				
	    		default:
					return;
	    	}	
		}
    }
    
    /**
     * allows you to change the password
     * @param sc scanner allows you to read in the input
     */
    public void changePassword(Scanner sc) {
    	
    	System.out.println(" Please Change your Password");
    	System.out.println("Enter Old(previous) Password: ");
    	String oldPassword = sc.next();
    	
    	if (!oldPassword.equals(this.getPassword())) {
    		System.out.println("Incorrect Old Password");
    		return;
    	}
    	
    	System.out.println("Enter New Password: ");
    	String newPassword1 = sc.next();
    	System.out.println("Confirm New Password: ");
    	String newPassword2 = sc.next();
    	
    	if (newPassword1.equals(newPassword2)) {
    		this.setPassword(newPassword2);
    		System.out.println("Password has been successfully changed");
    	}
    	
    	else System.out.println("The new passwords do not match. Are you sure you entered the right one ?");
    }
}

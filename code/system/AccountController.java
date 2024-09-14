package system;

import java.util.ArrayList;
import java.util.Scanner;

import administrator.Admin;
import branch.Branch;
import branch.ManagerController;
import branch.StaffController;
import staff.Manager;
import staff.Staff;
import enumpack.Gender;
import enumpack.UserType;

/**
 * controls user accounts for an application, including the creation, modification, and deletion of user accounts.
 * @author FDAA 5
 */
public class AccountController {
    private ArrayList<User> accountList;
    private Scanner sc;
    private Database database;

    /**
     * constructs an account controller object in the specified format
     * @param accountList the list of user accounts to manage
     */
    public AccountController(ArrayList<User> accountList) {
        this.accountList = accountList;
    }
    
    /**
     * constructs an account controller object in the specified format
     * @param sc scnner to read in the users input
     * @param accounts the list of user accounts to manage
     * @param database the database conected for storing user data
     */
    public AccountController(Scanner sc, ArrayList<User> accounts, Database database) {
    	this.accountList = accounts;
    	this.sc = sc;
    	this.database = database;
    }

    /**
     * retrieves the user by the userid
     * @param userId the userid to search for
     * @return account if the aaccount found based on the userid, otherwise return null
     */
    public User getUser(String userId) {
        for (User account : accountList) {
            if (userId.equals(account.getUserId())) {
                return account;
            }
        }
        return null;
    }

    /**
     * adds a new user to the account list if the user does not already exist
     * @param user the user object to add
     * @return true if the user was added successfully, otherwise return false 
     */
    public boolean addUser(User user) {
        if (this.getUser(user.getUserId()) != null) {
            return false;
        }

        this.accountList.add(user);
        return true;
    }

    /**
     * remove the user form the account lsit
     * @param userId the userid of the user to be rmeoved
     * @return the removed user object or null if the user could not be removed
     */
    public User removeUser(String userId) {
        User user = this.getUser(userId);
        
        if (user == null || user instanceof Admin) {
            return null;
        }

        this.accountList.remove(user);
        return user;
    }

    /**
    * allows the addition of a new account
    */
    public void addAccount() {
    	System.out.println("Enter the account name in the following format:\nName,Login Id,Password,Role,Gender,Age,Branch");
    	
    	if (sc.hasNextLine()) sc.nextLine();
    	String accountString = sc.nextLine();
    	String[] accountParts = accountString.split(",");
    	
    	if (accountParts.length != 7) {
    		System.out.println("Error adding in a new account: Invalid format");
    		return;
    	}
    	try {
    		String name = accountParts[0].trim();
    		String loginId = accountParts[1].trim();
    		String password = accountParts[2].trim();
    		UserType role = UserType.valueOf(accountParts[3].trim().toUpperCase());
    		Gender gender = Gender.valueOf(accountParts[4].trim().toUpperCase());
    		int age = Integer.parseInt(accountParts[5].trim());
    		String branch = accountParts[6].trim();
    		BranchController bm = new BranchController(database);
    		Branch b = bm.getBranch(branch);
    		
    		if (role == UserType.S) {
				Staff user = new Staff(b, loginId, password, name, gender, age);
				StaffController staffManager = new StaffController(b);
				if (staffManager.addStaff(user) == false) {
    				System.out.println("Unable to add staff: Staff already exists in branch or Staff quota has been exceeded");
    				return;
				}
				addUser(user);
				System.out.println("Staff account has been successfully added.");
				return;
			} else if (role == UserType.M) {
				Manager user = new Manager(b, loginId, password, name, gender, age);
				ManagerController managerManagement = new ManagerController(b);
				if (managerManagement.addManager(user) == false) {
    				System.out.println("Unable to add manager: Manager already exists in branch or Manager quota has been exceeded");
    				return;
				}
				addUser(user);
				System.out.println("Manager account has been added");
				return;
			} else if (role == UserType.A) {
				System.out.println("Admin account already exists");
			}
    		System.out.println(branch + " no branch found. Pleasae add a new branch first before adding account.");
    	} catch (Exception e) {
    		System.err.println("Error in an creating account: " + e.getMessage());
    	}
    }

    
    /**
     * allows the editing of an existing user account
     */
    
    public void editAccount() {
    	System.out.print("Enter Login Id of the account you wish to edit: ");
    	
    	if (sc.hasNextLine()) sc.nextLine();
    	
    	String loginId = sc.nextLine();
    	for (User user : accountList) {
    		
    		if (loginId.equals(user.getUserId())) {
    			System.out.println("Choose what domain you wish to edit:");
    			System.out.println("1. Name");
    			System.out.println("2. Login Id");
    			System.out.println("3. Gender");
    			System.out.println("4. Age");
    			System.out.println("5. Go Back");
    			int choice = sc.nextInt();
    			
    			switch (choice) {
    				case 1:
    					editName(sc, user);
    					return;
    				case 2:
    					editLoginId(sc, user);
    					return;
    				case 3:
    					editGender(sc, user);
    					return;
    				case 4:
    					editAge(sc, user);
    					return;
    				case 5:
    				default:
    					return;
    			}
    		}
    	}
    	System.out.println("No staff found with Login Id: " + loginId + "Are you sure you entered the correct loginId ?");
    }
    
    /**
     * allows for modification of the users name 
     * @param sc scanner reads in th user input
     * @param user the user whose name has to be modified
     */
    
    public void editName(Scanner sc, User user) {
    	System.out.print("Enter new Name: ");
    	
    	if (sc.hasNextLine()) sc.nextLine();
    	String newName = sc.nextLine();
    	System.out.println("Old: " + user.getName() + " > New: " + newName);
    	System.out.println("Confirm to change the name: ");
    	System.out.println("1. Yes");
    	System.out.println("2. No");
    	int choice = sc.nextInt();
    	
    	if (choice != 1) return;
    	Staff staff = getStaff(user);
    	
    	if (staff == null) {
    		System.err.println("Error in changing Name: Staff cannnot be found in this branch");
    		return;
    	}
    	staff.setName(newName);
    	user.setName(newName);
    	System.out.println("Name has been successfully changed");
    }
    
    /**
     * allows the eidting of the loginid of the user
     * @param sc scanner reads in the users input
     * @param user the user whose login id has to be ediited
     */
    public void editLoginId(Scanner sc, User user) {
    	System.out.print("Enter a new Login ID: ");
    	
    	if (sc.hasNextLine()) sc.nextLine();
    	String newId = sc.nextLine();
    	System.out.println("Old: " + user.getUserId() + " > New: " + newId);
    	System.out.println("Confirm to change the loginId ");
    	System.out.println("1. Yes");
    	System.out.println("2. No");
    	int choice = sc.nextInt();
    	
    	if (choice != 1) return;
    	Staff staff = getStaff(user);
    	
    	if (staff == null) {
    		System.err.println("Error in changing the Login ID: Staff cannot be found in this branch");
    		return;
    	}
    	staff.setUserId(newId);
    	user.setUserId(newId);
    	System.out.println("Login ID has been successfully changed");
    }

    
    /**
     * allows the editing of the gender of the user
     * @param sc scanner reads in the user input
     * @param user the user whose gender has to be eidted
     */
    public void editGender(Scanner sc, User user) {
    	
    	if (user.getGender() == Gender.MALE) {
    		System.out.println("Please confirm to change the gender to FEMALE?");
        	System.out.println("1. Yes");
    		System.out.println("2. No");
    		int choice = sc.nextInt();
    		
    		if (choice != 1) return;
    		Staff staff = getStaff(user);
    		
    		if (staff == null) {
        		System.err.println("Error in changing Gender: Staff cannot  be found in branch");
        		return;
        	}
    		staff.setGender(Gender.FEMALE);
    		user.setGender(Gender.FEMALE);
    		System.out.println("Gender has been succesfully changed");
    	} else if (user.getGender() == Gender.FEMALE) {
    		System.out.println("Please confirm to switch the gender to MALE?");
        	System.out.println("1. Yes");
    		System.out.println("2. No");
    		int choice = sc.nextInt();
    		
    		if (choice != 1) return;
    		Staff staff = getStaff(user);
    		
    		if (staff == null) {
        		System.err.println("Error in changing Gender: Staff cannot be found in the branch");
        		return;
        	}
    		staff.setGender(Gender.MALE);
    		user.setGender(Gender.MALE);
    		System.out.println("Gender has been successfully changed");
    	}
    }
    
    /**
     * allows the eiditng of the age of the user
     * @param sc scanner reads in the user input
     * @param user the user whose age has to be edited
     */
    public void editAge(Scanner sc, User user) {
    	System.out.print("Enter the new Age: ");
    	
    	if (sc.hasNextLine()) sc.nextLine();
    	int newAge = sc.nextInt();
    	System.out.println("Old: " + user.getAge() + " > New: " + newAge);
    	System.out.println("Confirm to change the age");
    	System.out.println("1. Yes");
    	System.out.println("2. No");
    	int choice = sc.nextInt();
    	
    	if (choice != 1) return;
    	Staff staff = getStaff(user);
    	
    	if (staff == null) {
    		System.err.println("Error in changing the Age: Staff cannot be found in the branch");
    		return;
    	}
    	staff.setAge(newAge);
    	user.setAge(newAge);
    	System.out.println("Age has been successfully changed");
    }

    /**
     * Finds and returns a Staff object if the user belongs to a specific branch; returns null otherwise.
     * @param user The user to find within the branch staff list.
     * @return The Staff object if found, null otherwise.
     */
    public Staff getStaff(User user) {
    	Staff staff = (Staff)user;
		Branch branch = staff.getBranch();
    	
		if (user.getUserType() == UserType.S) {
			int index = branch.getStaffList().indexOf(staff);
			
			if (index != -1) {
				return branch.getStaffList().get(index);
			}
		} else if (user.getUserType() == UserType.M) {
			int index = branch.getManagerList().indexOf(staff);
			
			if (index != -1) {
				return branch.getManagerList().get(index);
			}
		}
    	return null;
    }
    
    
    /**
     * allows the removal of an account
     */
    public void removeAccount() {
    	System.out.println("Enter Login Id of the account you wish to remove: ");
    	
    	if (sc.hasNextLine()) sc.nextLine();
    	String loginId = sc.nextLine();
    	
    	User user = getUser(loginId);
    	
    	if (user == null) {
         	System.out.println("No staff cannot be found with Login Id: " + loginId);
         	return;
     	} else if (user.getUserType() == UserType.A) {
    		System.out.println("Unable to remove the Admin");
    		return;
    	}
    	System.out.println("Confirm to remove the Admin");
    	System.out.println("1. Yes");
    	System.out.println("2. No");
    	int choice = sc.nextInt();
    	
    	if (choice != 1) return;
    	
    	Staff staff = (Staff)user;
		Branch b = staff.getBranch();
		
		if (staff.getUserType() == UserType.S) {
			b.getStaffList().remove(staff);
			accountList.remove(staff);
			System.out.println("Staff account has been succesfully removed");
			return;
		} else if (staff.getUserType() == UserType.M) {
			b.getManagerList().remove(staff);
			accountList.remove(staff);
			System.out.println("Staff account has been successfully removed");
			return;
		}
    }
}

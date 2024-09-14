package filter;

import java.util.ArrayList;
import java.util.Scanner;

import branch.Branch;
import system.Database;
import system.User;
import enumpack.Gender;
import enumpack.UserType;

/**
 * Provides methods to filter user accounts based on different criteria like role, branch, gender, and age.
 * @author FDAA Grp 5
 */
public class Filter {
	private Scanner sc;
	private ArrayList<User> accounts;
	private Database database;
	
	/**
     * Constructs a Filter object with dependencies needed for filtering operations.
     * @param sc the Scanner object for input 
     * @param accounts the list of staff to be filtered
     * @param database the database object containing additional data pf staff needed for filtering
     */
	public Filter(Scanner sc, ArrayList<User> accounts, Database database) {
		
		this.sc = sc;
		this.accounts = accounts;
		this.database = database;
	}
	
	/**
     * Filters the list of users based on their role.
     * @return a filtered list of users according to role, or all users if no role is selected.
     */
	public ArrayList<User> roleFilter() {
    	
		ArrayList<User> filteredList = new ArrayList<User>();
    	
    	System.out.println("Select the role you wish to categorize by: ");
		System.out.println("1. Staff");
		System.out.println("2. Manager");
		System.out.println("3. Cancel");
    	
		if (sc.hasNextLine()) sc.nextLine();
    	int filter = sc.nextInt();
    	
    	switch (filter) {
    		case 1:
    			
    			for (User user : accounts) {
    				if (user.getUserType() == UserType.S)
    					filteredList.add(user);
    			}
    			return filteredList;
    		
    		case 2:
    			
    			for (User user : accounts) {
    				if (user.getUserType() == UserType.M)
    					filteredList.add(user);
    			}
    			return filteredList;
			
    		default:
				return accounts;
    	}
    }
    
	/**
     * Filters the list of users based on their associated branch.
     * @return a filtered list of users who belong to the selected branch, or all users if the selection is cancelled.
     */
    public ArrayList<User> branchFilter() {
    	
    	ArrayList<User> filteredList = new ArrayList<User>();
    	ArrayList<String> branches = new ArrayList<String>();
    	System.out.println("Select the branch you wish to categorize by: ");
		int i = 1;
		
		for (Branch branch : database.getBranchList()) {
			String branchName = branch.getBranchName();
			System.out.println(i++ + ". " + branchName);
			branches.add(branchName);
		}
		
		System.out.println(i + ". Cancel");
		if (sc.hasNextLine()) sc.nextLine();
		
		int choice = sc.nextInt() - 1;
		
		if (choice < 0 || choice >= branches.size()) return accounts;
		
		for (User user : accounts) {
			if (user.getBranchName().equals(branches.get(choice))) filteredList.add(user);
		}
		
		return filteredList;
    }
    
    /**
     * Filters the list of users based on their gender.
     * @return a filtered list of users who match the selected gender, or all users if the selection is cancelled.
     */
    public ArrayList<User> genderFilter() {
    	
    	ArrayList<User> filteredList = new ArrayList<User>();
    	
    	System.out.println("Select the Gender you wish to categorize by: ");
		System.out.println("1. Male");
		System.out.println("2. Female");
		System.out.println("3. Cancel");
		
		if (sc.hasNextLine()) sc.nextLine();
    	int filter = sc.nextInt();
    	
    	switch (filter) {
    		case 1:
    			for (User user : accounts) {
    				if (user.getGender() == Gender.MALE)
    					filteredList.add(user);
    			}
    			return filteredList;
    		
    		case 2:
    			for (User user : accounts) {
    				if (user.getGender() == Gender.FEMALE)
    					filteredList.add(user);
    			}
    			return filteredList;
			
    		default:
				return accounts;
    	}
    }
    
    /**
     * Filters the list of users based on a specified age limit.
     * @return a filtered list of users who are younger than or equal to the specified age.
     */
    public ArrayList<User> ageFilter() {
    	
    	ArrayList<User> filteredList = new ArrayList<User>();
    	
    	System.out.print("Enter the Age limit you wish to categorize by: ");
		
    	if (sc.hasNextLine()) sc.nextLine();
		int age = sc.nextInt();
    	
		for (User user : accounts) {
    		if (user.getAge() <= age) filteredList.add(user);
    	}
    	
		return filteredList;
    }

}

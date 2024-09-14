package system;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import enumpack.Category;

import administrator.Admin;
import branch.Branch;
import branch.ItemController;
import enumpack.OperationStatus;
import exceptionhandling.BranchNotFoundException;
import item.Item;

import staff.Manager;
import staff.Staff;
import enumpack.Gender;

/**
 * Provides functionality for loading data from CSV files into the application.
 * Handles loading of branches, user accounts, and items into their respective lists.
 * @author FDAA 5
 */
public class FileRead {
	private static final String directory = System.getProperty("user.dir") + "/csvfiles/";
	private static final String SEPARATOR = ",";
	
	/**
	 * Loads branch data from a CSV file and populates a list of Branch objects.
	 * @param filename the name of the file from which we have to load branch data
	 * @param branchList the list to populate with the loaded branches
	 * @throws IOException if an error occurs during file reading
	 */
	public static void loadBranches(String filename, ArrayList<Branch> branchList) throws IOException {
		ArrayList<String> stringArray = (ArrayList<String>)read(filename);
		
		for (int i = 1; i < stringArray.size(); i++) {
			String st = (String)stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st, SEPARATOR);
			
			String name = star.nextToken().trim();
			String location = star.nextToken().trim();
			int staffQuota = Integer.parseInt(star.nextToken().trim());
			String operationStatus = star.nextToken().trim().toUpperCase();
			
			try {
				OperationStatus os = OperationStatus.valueOf(operationStatus);
				Branch branch = new Branch(name, location, staffQuota, os);
				branchList.add(branch);
			} catch (IllegalArgumentException e) {
				System.err.println("Error in creating the Branch object: " + e.getMessage());
			}
		}
	}
	
	/**
	 * loads user account data from a csv file and populates a list of user objects
	 * @param filename the name of the file from which to load account data
	 * @param accountList the list to populate with loaded user accounts
	 * @throws IOException if an error occurs during file reading 
	 */
	public static void loadAccounts(String filename, ArrayList<User> accountList) throws IOException {
		ArrayList<String> stringArray = (ArrayList<String>)read(filename);
		
		for (int i = 1; i < stringArray.size(); i++) {
			String st = (String)stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st, SEPARATOR);
			
			String name = star.nextToken().trim();
			String loginId = star.nextToken().trim();
			String password = star.nextToken().trim();
			String role = star.nextToken().trim().toUpperCase();
			char roleChar = role.toCharArray()[0];
			String gender = star.nextToken().trim();
			int age = Integer.parseInt(star.nextToken().trim());
			String branch = star.nextToken().trim();
			
			if (loginId.equalsIgnoreCase("boss")) {
				try {
					Admin admin = new Admin(loginId, password, name, Gender.valueOf(gender), age, branch);
					accountList.add(admin);
					continue;
				} catch (IllegalArgumentException e) {
					System.err.println("Error creating Admin object: " + e.getMessage());
				}
			}
			
			try {
				Branch b = Database.findBranch(branch);
				Gender g = Gender.valueOf(gender);

				switch (roleChar) {
					case 'S':
						Staff staff = new Staff(b, loginId, password, name, g, age);
						accountList.add(staff);
						b.getStaffList().add(staff);
						break;
					case 'M':
						Manager manager = new Manager(b, loginId, password, name, g, age);
						accountList.add(manager);
						b.getManagerList().add(manager);
						break;
				}
			} catch (IllegalArgumentException e) {
				System.err.println("Error creating Staff object: " + e.getMessage());
			} catch (BranchNotFoundException e) {
				System.err.println("Error creating Staff object: " + e.getMessage());
			}
		}
	}
	
	/**
	 * Loads item data from a CSV file and populates the items list of respective Branches.
	 * @param filename The name of the file from which to load item data.
	 * @param branchList The list of branches to which the items will be added.
	 * @throws IOException if an error occurs during file reading
	 */
	
	public static void loadItems(String filename, ArrayList<Branch> branchList) throws IOException {
		ArrayList<String> stringArray = (ArrayList<String>)read(filename);
		
		for (int i = 1; i < stringArray.size(); i++) {
			String st = (String)stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st, SEPARATOR);
			
			String itemId = star.nextToken().trim();
			String name = star.nextToken().trim();
			Double price = Double.parseDouble(star.nextToken().trim());
			String branch = star.nextToken().trim();
			String category = star.nextToken().trim().toUpperCase();
			String description = star.nextToken().trim();
			
			try {
				Branch b = Database.findBranch(branch);
				Category c = Category.valueOf(category);
				Item item = new Item(itemId, name, price, c, description);
				ItemController itemManagement = new ItemController(b);
				itemManagement.addItem(item);
			} catch (IllegalArgumentException e) {
				System.err.println("Error in creating the Staff object: " + e.getMessage());
			} catch (BranchNotFoundException e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Reads data from a specified file and returns it as a list of strings, where each string represents a line in the file.
	 * @param fileName The name of the file to read, relative to the predefined directory for CSV files.
	 * @return A List of strings, each representing a line from the file.
	 * @throws IOException if there is an error during file reading 
	 */
	
	private static List<String> read(String fileName) throws IOException {
		List<String> data = new ArrayList<String>();
		Scanner scanner = new Scanner(new FileInputStream(directory + fileName));
		try {
			while (scanner.hasNextLine()) data.add(scanner.nextLine());
		} finally {
			scanner.close();
		}
		return data;
	}
}

package operation;

import java.util.InputMismatchException;
import java.util.Scanner;

import exceptionhandling.InputOutOfRangeException;
import operation.customerui.CustomerView;
import operation.staffui.StaffInterface;
import system.Database;

/**
 * The main class for the Front Office Management System (FOMS),
 * providing an entry point for users to log in as customers or staff.
 * @author FDAA 5
 */
public class FOMSApp {
    
	/**
     * The main method to run the FOMS application, handling user login and directing
     * them to appropriate interfaces based on their role.
     *
     * @param args command-line arguments (not used in this application)
     */
	public static void main(String[] args) {
       
		Database database = new Database();
        database.loadFiles();
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("---------------------------------------");
            System.out.println(" WELCOME TO THE FOMS APPLICATION");
            System.out.println("---------------------------------------");
            System.out.println("Please choose your login option:");
            System.out.println("1. Customer");
            System.out.println("2. Staff");
            System.out.println("3. Quit");
            System.out.print("Enter your choice: ");

            try {
                int loginChoice = sc.nextInt();
                System.out.println();
                switch (loginChoice) {
                    case 1:
                        new CustomerView(sc, database.getBranchList());
                        break;
                    case 2:
                        new StaffInterface(sc, database);
                        break;
                    case 3:
                        sc.close();
                        database.saveFiles();
                        System.exit(0);
                        break;
                    default:
                        throw new InputOutOfRangeException();
                }
            } 
            catch (InputMismatchException e) {
                System.out.println("Invalid input has been entered. Are you sure you entered the right one ?");
                sc.next();
            }
            catch (InputOutOfRangeException e) {
                System.out.println("Invalid input has been entered. Are you sure you entered the right one ?");
            }
            catch (Exception e) {
            	System.out.println(e.getMessage());
            	database.saveFiles();
            }
            finally {
                System.out.println();
            }
        }
    }

	 /**
     * displays the welcome message and the login options to the user
     */
    public void displayWelcome()
    {
        System.out.println("---------------------------------------");
        System.out.println(" WELCOME TO THE FOMS APPLICATION");
        System.out.println("---------------------------------------");
        System.out.println("Please choose your login option:");
        System.out.println("1. Customer");
        System.out.println("2. Staff");
        System.out.println("3. Quit");
        System.out.print("Enter your choice: ");
    }
}

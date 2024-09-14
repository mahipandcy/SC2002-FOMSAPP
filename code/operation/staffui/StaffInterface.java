package operation.staffui;

import java.util.Scanner;
import administrator.Admin;
import staff.Manager;
import staff.Staff;
import system.BranchController;
import system.Database;

/**
 * Facilitates interaction among different staff user types with the system, directing them to specific
 * interfaces according to their roles.
 * @author FDAA 5
 */

public class StaffInterface {
	
	/**
     * Initializes the staff interface, handles user login, and redirects users to their specific interfaces
     * based on their role within the organization (Admin, Manager, or Staff).
     * 
     * @param sc the scanner used for input collection
     * @param database the system database containing all user accounts and other necessary data
     */
    

    public StaffInterface(Scanner sc, Database database) {
    	
        LoginView loginPage = new LoginView(sc, database.getAccountList(), 3);
        
        if (!loginPage.isSuccessLogin()) {
        	
            System.out.println("Login failed.");
            return;
            
        }
        
        System.out.println("Login successful.");

        BranchController branchManagement = new BranchController(database);
        
        switch (loginPage.user.getUserType()) {
        
            case A:
            	
                Admin admin = (Admin) loginPage.user;
                new AdminView(sc, admin, database);
                break;
                
            case M:
            	
                Manager manager = (Manager) loginPage.user;
                new ManagerView(sc, manager, branchManagement.getBranch(manager.getBranch().getBranchName()));
                break;
                
            case S:
            	
                Staff staff = (Staff) loginPage.user;
                new StaffView(sc, staff, branchManagement.getBranch(staff.getBranch().getBranchName()));
                break;
                
            default:
            	
                System.out.println("Invalid staff type has been entered.");
                break;
        }
    }
    
    /**
     * A placeholder method to potentially show the initial interface or instructions for staff interactions.
     */
    
    
    public static void showStaffInterface() {
    	
    }
}

package operation.staffui;

import java.util.Scanner;

import administrator.Admin;
import system.Database;

/**
 * provides an interface for administrators to display the staff list, add edit or rmeove staff accounts, open or close  branch and so many other management actions
 * @author FDAA 5
 */

class AdminView {
	
    AdminView(Scanner sc, Admin admin, Database database) {
    	
    	/**
         * Initializes the administrative interface, allowing an admin to perform various administrative tasks.
         * The interface includes options for managing staff, branches, and system settings.
         *
         * @param sc the scanner used for input collection
         * @param admin the admin object that will perform the operations
         * @param database the database containing all relevant data for operations
         */
        

    	while (true) {
    		
        	System.out.println("\n--------------------");
            System.out.println("ADMIN LOGIN");
            System.out.println("--------------------");

            System.out.println("Choose your action:");
            System.out.println("1. Display the Staff List");
            System.out.println("2. Add, Edit or Remove Staff Accounts");
            System.out.println("3. Promote Staff");
            System.out.println("4. Transfer Staff/Manager");
            System.out.println("5. Add/Remove Payment Method");
            System.out.println("6. Open/Close Branch");
            System.out.println("7. Add/Remove Branch");
            System.out.println("8. Change Password");
            System.out.println("9. Return to Start");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            
            switch (choice) {
            
            	case 1:
            		
            		admin.displayStaffAccounts(database.getAccountList());
            		admin.chooseFilter(sc, database.getAccountList(), database);
            		break;
            		
                case 2:
                	
                	admin.manageAccounts(sc, database.getAccountList(), database);
                	break;
                	
                case 3:
                	
                	admin.promoteStaff(sc, database.getAccountList(), database);
                	break;
                	
                case 4:
                	
            		admin.displayStaffAccounts(database.getAccountList());
                	admin.transferStaff(sc, database.getAccountList(), database);
                	break;
                	
                case 5:
                	
                	admin.addRemovePayment(sc);
                	break;
                	
                case 6:
                	
                	admin.openCloseBranch(sc, database);
                	break;
                	
                case 7:
                	
                	admin.addRemoveBranch(sc, database);
                	break;
                	
                case 8:
                	
                	admin.changePassword(sc);
                	break;
                	
                case 9:
                	
                    System.out.println("Returning to the Start page");
                    return;
                    
                default:
                	
                    break;
            }

        }
    }
}

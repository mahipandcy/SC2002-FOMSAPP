package operation.staffui;

import java.util.ArrayList;
import java.util.Scanner;

import system.AccountController;
import system.User;

/**
 * Provides an interface for user login, validating user credentials and managing password input attempts.
 * @author FDAA 5
 */

class LoginView {
	
    private boolean successLogin = false;
    User user = null;
    
    /**
     * Constructs a login view that handles user authentication
     * it promopts for useer ID and password and checks these against the provided list of accounts
     * @param sc scanner eads in the input 
     * @param accountList shows the loist of accounts available to lg into
     * @param passwordTrial the maximum number of password attempts allowed
     */
    
    
    LoginView(Scanner sc, ArrayList<User> accountList, int passwordTrial) {
    	
        AccountController accountManagement = new AccountController(accountList);

        String userId = null;
        
        while (this.user == null) {
        	
            System.out.print("Enter ID: ");
            userId = sc.next();
            this.user = accountManagement.getUser(userId);
            
            if (this.user == null) {
            	
                System.out.println("Unknown User! Please enter a different ID.");
                return;
            }
        }

        String password;
        int userTrial = 0;
        
        while (userTrial < passwordTrial) {
        	
            System.out.print("Enter Password: ");
            password = sc.next();
            
            if (password.equals(user.getPassword())) {
            	
                successLogin = true;
                break;
            }
            
            userTrial++;
            System.out.println("Incorrect password. " + (passwordTrial - userTrial) + " trial(s) left.");
        }
        
        if (userTrial == passwordTrial) {
        	
            this.user = null;
            System.out.println(" Sorry your login has Failed! Too many incorrect attempts have been made");
        }
    }
    
    /**
     * returns whether the suer has successfully logged in or not
     * @return the success login
     */
    

    public boolean isSuccessLogin() {
    	
        return successLogin;
    }
}

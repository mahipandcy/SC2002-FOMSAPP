package operation.customerui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import branch.Branch;
import branch.OrderController;
import exceptionhandling.InputOutOfRangeException;


/**
 * Provides an interactive interface for customers to interact with various aspects of the system.
 * This includes selecting a branch, viewing order statuses, making new orders, and navigating payment processes.
 * @author FDAA 5
 */
public class CustomerView {
	
	/**
	 * initializes the customer interface and manages the interaction flow such as branch selection and order management
	 * @param sc scanner reads in the input from the user
	 * @param branchList is a list of all the branch options available to the customer
	 */
    public CustomerView(Scanner sc, ArrayList<Branch> branchList) {
       
    	System.out.println("------------------");
        System.out.println("CUSTOMER LOGIN");
        System.out.println("------------------");
        
        Branch currentBranch = null;
        
        while (currentBranch == null) {
            
        	BranchSelection branchSelectionPage = new BranchSelection(sc, branchList);
            currentBranch = branchSelectionPage.getCurrentBranch();
           
            if (currentBranch == null) {
                break;
            }
            
            System.out.println("WELCOME TO " + currentBranch.getBranchName() + "!");
            System.out.println();
            
            while (currentBranch != null) {
                
            	System.out.println("Please choose your option as a customer:");
                System.out.println("1. View Order Status");
                System.out.println("2. Make a new Order");
                System.out.println("3. Go back");
                System.out.print("Enter your choice: ");
                int customerChoice = sc.nextInt();
                System.out.println();
               
                try {
                    switch (customerChoice) {
                        case 1:
                            new View(sc, currentBranch);
                            return;
                        
                        case 2:
                            OrderController orderManagement = new OrderController(currentBranch);

                            OrderingView orderingPage = new OrderingView(sc, currentBranch.getItemList());
                            if (!orderingPage.isCheckedOut()) {
                                currentBranch = null;
                                continue;
                            }

                            PaymentView paymentPage = new PaymentView(sc, orderingPage.getCart());
                            if (!paymentPage.isSuccessPayment()) {
                                continue;
                            }
                            orderManagement.addOrder(paymentPage.getOrder());
                            new Receipt(paymentPage.getOrder());
                            return;
                        
                        case 3:
                            currentBranch = null;
                            break;
                       
                        default:
                       
                        	throw new InputOutOfRangeException();
                    }
                } 
                
                catch (InputOutOfRangeException e) {
                    System.out.println("Invalid input has been entered. Are you sure you entered the right one ?");
                } 
               
                catch (InputMismatchException e) {
                    System.out.println("Invalid input has been entered. are you sure you entered the right one ?");
                    sc.next();
                }
            }
        }
    }
}

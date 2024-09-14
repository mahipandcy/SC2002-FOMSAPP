package operation.customerui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import enumpack.OperationStatus;

import branch.Branch;

/**
 * Provides an interface for selecting a branch from a list of branches.
 * This class ensures that only open branches are selectable, and handles user input errors.
 * @author FDAA Grp 5
 */
class BranchSelection {
	
    private Branch currentBranch = null;
    

    /**
     * Constructs a BranchSelection and initiates the selection process.
     * Users are prompted to select a branch from a list of open branches. If the user chooses to return
     * to the start or enters an invalid option, appropriate feedback is given and re-prompt occurs.
     * 
     * @param sc the scanner used to read user input
     * @param branchList the list of branches from which to select
     */
    
    
        BranchSelection(Scanner sc, ArrayList<Branch> branchList) {
            while (this.currentBranch == null) {
                try {
                    System.out.println("Please choose your current branch:");
                    for (int i = 0; i < branchList.size(); i++) {
                        if (branchList.get(i).getOperationStatus() == OperationStatus.OPEN) {
                            System.out.println((i + 1) + ". " + branchList.get(i).getBranchName());
                        }
                    }
                    System.out.println((branchList.size() + 1) + ". Return to Start");     
                    System.out.print("Enter your preferred choice: ");

                    int branchChoice = sc.nextInt();
        
                    if (branchChoice < 1 || branchChoice > branchList.size() + 1) {
                        throw new InputMismatchException();
                    }
                    if (branchChoice == branchList.size() + 1) {
                        return;
                    }
        
                    currentBranch = branchList.get(branchChoice - 1);
                    System.out.println();
                } 
                catch (InputMismatchException e) {
                    System.out.println("Invalid input has been given. Are you sure you entered the right name ?");
                    System.out.println();
                    sc.next();
                }
           
            }
        }
     
        /**
         * Returns the currently selected branch.
         * @return the selected branch, or null if no valid selection was made
         */  
    public Branch getCurrentBranch() {
        return currentBranch;
    }
}

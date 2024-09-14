package branch;
import enumpack.OperationStatus;

/**
 * Manages the operational status of a branch, allowing it to be opened or closed.
 * @author FDAA Grp 5
 */
public class OperationStatusController {
    private Branch branch;

    /**
     * Constructs an OperationStatusController with a reference to a specific branch.
     * @param branch the branch whose operation status is to be managed
     */
    public OperationStatusController(Branch branch) {
        
    	this.branch = branch;
    }

    /**
     * Attempts to close the branch if it is currently open.
     * @return true if the branch was successfully closed, false if the branch was already closed or could not be closed.
     */
    public boolean close() {
        
    	if (this.branch.getOperationStatus() != OperationStatus.OPEN) { 
    		return false;
        }

        this.branch.setOperationStatus(OperationStatus.CLOSE);
        
        return true;
    }

    /**
     * Attempts to open the branch if it is currently closed.
     * @return true if the branch was successfully opened, false if the branch was already open or could not be opened.
     */
    public boolean open() {
       
    	if (this.branch.getOperationStatus() != OperationStatus.CLOSE) {
            return false;
        }

        this.branch.setOperationStatus(OperationStatus.OPEN);
        
        return true;
    }
}
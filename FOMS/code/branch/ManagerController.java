package branch;

import staff.Manager;


/**
 * Manages manager operations within a branch, including adding, retrieving, and removing managers.
 * @author FDAA Grp 5
 */
public class ManagerController {
    private Branch branch;

    /**
     * Constructs a ManagerController object associated with a specific branch.
     * @param branch the branch this ManagerController is associated with
     */
    public ManagerController(Branch branch) {
        this.branch = branch;
    }

    /**
     * returns the manager with the id
     * @param managerId is the id of manager
     * @return the manager if the id exists ottherwise return null
     */
    public Manager getManager(String managerId) {
        
    	for (Manager manager : this.branch.getManagerList()) {
           
    		if (managerId.equals(manager.getUserId())) {
                return manager;
            }
        }
        
    	return null;
    }

    /**
     * Adds a new manager to the branch's manager list if the manager quota has not been reached
     * and the manager does not already exist.
     * @param manager the manager to be added
     * @return true if the manager was added successfully, false if the manager quota has been reached or the manager already exists
     */
    public boolean addManager(Manager manager) {
        
    	if (this.branch.managerList.size() == this.branch.getManagerQuota() ||
                getManager(manager.getUserId()) != null) {
            System.out.println("Cannot add manager. Manager quota has been reached or the manager already exists.");
            
            return false;
        }

        this.branch.managerList.add(manager);
        System.out.println("Manager has been successfully added: " + manager.getName());
        
        return true;
    }

    /**
     * Removes a manager from the branch's manager list by their user ID.
     * @param managerId the ID of the manager to be removed
     * @return the removed Manager if successful, null if the manager does not exist
     */
    public Manager removeManager(String managerId) {
       
    	Manager manager = getManager(managerId);
        
    	if (manager == null) {
            System.out.println("Manager with ID " + managerId + " cannot be found.");
            return null;
        }

        this.branch.managerList.remove(manager);
        System.out.println("Manager has been removed: " + manager.getName());
        
        return manager;
    }
}

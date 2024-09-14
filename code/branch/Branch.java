package branch;

import java.util.ArrayList;
import item.Item;
import order.Order;
import staff.Staff;
import staff.Manager;
import enumpack.OperationStatus;

/**
 * represents a branch of the FOMS
 *  @author FDAA Grp 5
 */
public class Branch {

    private String branchName;
    private String branchLocation;

    private int staffQuota;
    private int managerQuota = 0;

    private OperationStatus operationStatus = OperationStatus.OPEN;
    private int openingHour = 800, closingHour = 2200;
        
    ArrayList<Staff> staffList = new ArrayList<Staff>();
    ArrayList<Manager> managerList = new ArrayList<Manager>();
    ArrayList<Item> itemList = new ArrayList<Item>();
    ArrayList<Order> orderList = new ArrayList<Order>();
    private int orderId = 0;

    
    /** a constructor of branch class
	 * @param branchName the name of the branch
     * @param branchLocation the physical location of the branch
     * @param staffQuota the maximum number of staff members the branch can accommodate
     */
    public Branch(String branchName, String branchLocation, int staffQuota) {
        
    	this.branchName = branchName;
        this.branchLocation = branchLocation;
        this.staffQuota = staffQuota;
        calculateManagerQuota();
    }
    
    
    /** another constructor of branch class 
	 * @param branchName the name of the branch
     * @param branchLocation the physical location of the branch
     * @param staffQuota the maximum number of staff members the branch can accommodate
     * @param operationStatus the initial operational status of the branch
     */
    public Branch(String branchName, String branchLocation, int staffQuota, OperationStatus operationStatus) {
		
    	this.operationStatus = operationStatus;
		this.branchName = branchName;
    	this.branchLocation = branchLocation;
    	this.staffQuota = staffQuota;
    	calculateManagerQuota();
    }

    
    /**
     * calculates the staff quota per manager
     */
    private void calculateManagerQuota() {
        
    	int[] managerQuotaThreshold = { 1, 5, 9 };
        int[] managerQuotaList = { 1, 2, 3 };
        this.managerQuota = 0;
       
        for (int i = 0; i < managerQuotaThreshold.length; i++) {
            if (this.staffQuota <= managerQuotaThreshold[i]) {
                this.managerQuota = managerQuotaList[i];
                break;
            }
        }
       
        if (this.managerQuota == 0) {
            this.managerQuota = managerQuotaList[managerQuotaList.length - 1];
        }
    }

    /**
     * allows us to increase the orderID to consider it as the next order
     */
    public void increaseOrderId() {
    	this.orderId++;
    }
 

    /**
     * returns the name of  branch
     * @return the branch name
     */
    public String getBranchName() {
        
    	return this.branchName;
    }

    /**
     * returns the location of the branch
     * @return the branch location
     */
    public String getBranchLocation() {
    	return this.branchLocation;
    }

    /**
     * returns the staff quota of the branch
     * @return staff quota
     */
    public int getStaffQuota() {
        return this.staffQuota;
    }

    
    /**
     * returns the manager quota of the branch
     * @return manager quota
     */
    public int getManagerQuota() {
        return this.managerQuota;
    }

    /**
     * returns the operational status of the branch
     * @return operational status
     */
    public OperationStatus getOperationStatus() {
        return this.operationStatus;
    }
    
    /**
     * sets the status of branch
     * @param operationStatus allows us to set the status
     */
    public void setOperationStatus(OperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }

    /**
     * returns the opening hour of the branch
     * @return opening hour
     */
    public int getOpeningHour() {
        return this.openingHour;
    }

    /**
     * sets the opening hour of the branch
     * @param openingHour sets the opening time of branch
     */
    public void setOpeningHour(int openingHour) {
        this.openingHour = openingHour;
    }
    
    /**
     * returns the closing hour of the branch
     * @return the closing hour
     */
    public int getClosingHour() {
        return this.closingHour;
    }

    /**
     * sets the closing hour of the branch
     * @param closingHour sets the closing time of the branch
     */
    public void setClosingHour(int closingHour) {
        this.closingHour = closingHour;
    }
    
    /**
     * returns the list of staff
     * @return the staff list
     */
    public ArrayList<Staff> getStaffList() {
        return this.staffList;
    }

    /**
     * returns the list of mananger
     * @return manager list
     */
    public ArrayList<Manager> getManagerList() {
        return this.managerList;
    }

    
    /**
     * returns the list of items
     * @return the item lsit
     */
    public ArrayList<Item> getItemList() {
        return this.itemList;
    }

    
    /**
     * returns the list of orders
     * @return order list
     */
    public ArrayList<Order> getOrderList() {
        return this.orderList;
    }
    
    /**
     * returns the ID of the order
     * @return orderID
     */
    public int getOrderId() {
    	return this.orderId;
    }

 }

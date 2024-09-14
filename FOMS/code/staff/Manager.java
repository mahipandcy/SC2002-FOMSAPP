package staff;
import java.util.ArrayList;

import branch.Branch;
import branch.ItemController;
import item.Item;
import enumpack.Category;
import enumpack.UserType;
import enumpack.Gender;

/**
 * Represents a manager who has additional responsibilities compared to regular staff, 
 * such as managing staff and handling items
 * @author FDAA 5
 */
public class Manager extends Staff {
    private ItemController itemManagement = new ItemController(this.branch);

    /**
     * Constructs a Manager with access to a specific branch's details and item management actions.
     *
     * @param branch The branch where the manager operates.
     * @param managerId The unique identifier for the manager.
     * @param password The manager's login password.
     * @param staffName The manager's name.
     * @param gender The manager's gender.
     * @param age The manager's age.
     */
    public Manager(Branch branch, String managerId, String password, String staffName, Gender gender, int age) {
        super(branch, managerId, password, staffName, gender, age);
        this.userType = UserType.M;
    }

    /**
     * displays a list of staff members at the braanch
     */
    public void displayStaffList() {
        ArrayList<Staff> staffList = this.branch.getStaffList();
        for (int i = 0; i <= staffList.size(); i++) {
            System.out.println("Staff No." + (i + 1));
            System.out.println("\tStaff Login ID:\t" + staffList.get(i).getUserId());
            System.out.println("\tGender:\t" + staffList.get(i).getGender());
            System.out.println("\tAge:\t" + staffList.get(i).getAge());
        }
    }

    /**
     * adds a new item to the list at the branch
     * @param itemId the unique identifier for the item
     * @param itemName the name of the item 
     * @param price the price of the item
     * @param quantity the quantity to add
     * @param category the category of the item to be added
     * @param description the description of the item to be added
     * @return the added item
     */
    public boolean addItem(String itemId, String itemName, double price, int quantity, Category category, String description) {
        Item item = new Item(itemId, itemName, price, category, description);
        return itemManagement.addItem(item);
    }

    /**
     * edits an existing item in the menu
     * @param itemId the item id of the item to be edited
     * @param newItemId the new id for the item
     * @param newPrice the new price for the item
     * @param newDescription the new description for the item
     * @return true if the item was successfully edited toherwise return false
     */
    public boolean editItem(String itemId, int newItemId, double newPrice, String newDescription) {
        return true;
    }

    /**
     * remove an existing item in the menu
     * @param itemId the id of the item to be removed
     * @return the removed item
     */
    public Item removeItem(String itemId) {
        return itemManagement.removeItem(itemId);
    }
}


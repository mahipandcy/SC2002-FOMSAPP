package item;

import enumpack.Category;

/**
 * Represents a menu item with attriutes like item ID, name, price, category, and description.
 * @author FDAA Grp 5
 */
public class Item {
    
    private String itemId;
    private String name;
    private double price;
    private Category category;
    private String description;

    /**
     * Constructs a new Item with the specified details.
     * @param itemId the unique id for the menu item
     * @param name the name of the menu item
     * @param price the price of the menu item
     * @param category the category of the menu item, as defined in the Category enum
     * @param description a brief description of the menu item
     */
    public Item(String itemId, String name, double price, Category category, String description) {
        
    	this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
    
    }
    
    /**
     * Alternative constructor that accepts a category name as a string and converts it to a Category enum.
     * @param itemId the unique identifier for the item
     * @param name the name of the item
     * @param price the price of the item
     * @param category the name of the category, which is converted to a Category enum
     * @param description a brief description of the item
     */
    public Item(String itemId, String name, double price, String category, String description) {
       
    	this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.category = Category.valueOf(category);
        this.description = description;
    
    }

    /**
     * Returns the item ID.
     * @return the item ID
     */
    public String getId() {
        return this.itemId;
    }
    
    /**
     * Sets the item ID.
     * @param itemId the new ID for the item
     */
    public void setId(String itemId) {
        this.itemId = itemId;
    }
    
    /**
     * Returns the item name.
     * @return the name of the item
     */
    public String getName() {
        return this.name;
    }

    
    /**
     * Sets the item name.
     * @param name the new name for the item
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
    /**
     * Returns the item price.
     * @return the price of the item
     */
    public double getPrice() {
        return this.price;
    }


    /**
     * Sets the item price.
     * @param price the new price for the item
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * Returns the category of the item.
     * @return the category of the item
     */
    public Category getCategory() {
        return category;
    }
    

    /**
     * Sets the category of the item.
     * @param category the new category for the item
     */
    public void setCategory(Category category) {
        this.category = category;
    }
    
    /**
     * Returns the description of the item.
     * @return the description of the item
     */
    public String getDescription() {
    	return description;
    }
   
    /**
     * Sets the description for the item.
     * @param description the new description of the item
     */
    public void setDescription(String description) {
    	this.description = description;
    }
}


package system;
import enumpack.UserType;
import enumpack.Gender;

/**
 * class representing a generic user in the system.
 * This class provides a common framework to handle different types of users.
 * @author FDAA 5
 */
public class User {

    protected UserType userType;
    protected String userId;
    protected String password = "password";
    protected String name;
    protected Gender gender;
    protected int age;
    protected String branchName;
    

    /**
     * Constructs a User with specified attributes.
     *
     * @param userType The type of user (e.g., ADMIN, STAFF, MANAGER).
     * @param accountId The unique identifier for the user.
     * @param password The user's password for system access.
     * @param name The user's full name.
     * @param gender The user's gender.
     * @param age The user's age.
     * @param branchName The name of the branch to which the user is assigned.
     */
    
    public User(UserType userType, String accountId, String password, String name, Gender gender, int age, String branchName) {
        this.userType = userType;
        this.userId = accountId;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.branchName = branchName;
    }

    /**
     * gets the type of user
     * @return the user type
     */
    
    public UserType getUserType() {
        return userType;
    }

    /**
     * sets the user's type
     * @param userType the new user type
     */
    
    public void setUserType(UserType userType) {
    	this.userType = userType;
    }
    
    /**
     * returns the users unique id
     * @return the user id 
     */
    
    public String getUserId() {
        return userId;
    }

    /**
     * sets the id of the user
     * @param accountId the new user id
     */
    
    public void setUserId(String accountId) {
        this.userId = accountId;
    }
    
    /**
     * returns the user's password
     * @return the user's password
     */
    
    public String getPassword() {
        return password;
    }
    
    /**
     * sets the user's password
     * @param password the user password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * returns name of the user
     * @return the user's name
     */
    
    public String getName() {
        return name;
    }

    /**
     * sets the user's name
     * @param name the user's name
     */
    
    public void setName(String name) {
        this.name = name;
    }

    /**
     * returns the gender of the user
     * @return the gender
     */
    
    public Gender getGender() {
        return gender;
    }
    
    /**
     * sets the gender of the user
     * @param gender the users gender
     */
    
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    

    /**
     * returns the age of the user
     * @return the age
     */
    
    public int getAge() {
        return age;
    }
    
    /**
     * sets the age of the user
     * @param age the users age
     */
    
    public void setAge(int age) {
        this.age = age;
    }
    
    /**
     * returns the branch name of the user
     * @return the branch name
     */
    
    public String getBranchName() {
    	return branchName;
    }
    
    /**
     * sets the branch of the user
     * @param branchName the name fo the branch
     */
    public void setBranchName(String branchName) {
    	this.branchName = branchName;
    }
}

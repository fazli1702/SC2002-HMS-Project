package user;

/**
 * User class is class that stores information of anyone that has an account in HMS
 */
public class User {
    private int hospitalID;
    private String name;
    private String password;
    private String email;
    private UserType userType;
    private int phoneNumber;
    private int age;
    private Gender gender;

    /**
     * Constructor
     * @param hospitalID hospitalID of user
     * @param name name of user
     * @param email emial of user
     * @param userType user type of user (PATIENT,DOCTOR,PHARMACIST,ADMINISTRATOR)
     * @param phoneNumber phone number of user
     * @param age age of user
     * @param gender gender of user
     */
    public User(int hospitalID, String name, String email, UserType userType, int phoneNumber, int age, Gender gender){
        this.hospitalID = hospitalID;
        this.name = name;
        this.password = "password";  // default possword
        this.email = email;
        this.userType = userType;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.gender = gender;
    }


    /**
     * 
     * @return hospital ID of user
     */
    public int getHospitalID() {
        return this.hospitalID;
    }

    /**
     * Sets hospitalID of user
     * @param hospitalID
     */
    public void setHospitalID(int hospitalID) {
        this.hospitalID = hospitalID;
    }

    /**
     * 
     * @return name of user
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets name of user
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return password of user
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets password of user
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Verifies password of user given an input password
     * @param password
     * @return
     */
    public Boolean verifyPassowrd(String password){
        return this.password.equals(password);
    }

    /**
     * 
     * @return email of user
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * sets email of user
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return user type of user
     */
    public UserType getUserType() {
        return this.userType;
    }

    /**
     * sets user type of user
     * @param userType
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    /**
     * 
     * @return phone number of user
     */
    public int getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Sets phone number of patient
     * @param phoneNumber
     */
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 
     * @return age of user
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Sets age of user 
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 
     * @return gender of user
     */
    public Gender getGender() {
        return this.gender;
    }

    /**
     * Sets gender of user
     * @param gender
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Prints user info with formatting
     */
    public void viewUser(){
        System.out.println(String.format("%-5d %-15s %-15s %-15s", hospitalID, name, userType, password));
    }
}

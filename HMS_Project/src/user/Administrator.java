package user;

import user.*;

/**
 * Administrator class contains a constructor and contains a constructor
 */
public class Administrator extends Staff{

    /**
     * Constructor of Administrator
     * @param name  Name of user
     * @param hospitalID  hospitalID of user
     * @param email   email of user
     * @param phoneNumber  phone number of user
     * @param age age of user
     * @param gender gender of user
     * @param salary salary of user
     */
    public Administrator(String name, int hospitalID, String email, int phoneNumber, int age, Gender gender, int salary){
        super(hospitalID, name, email, UserType.ADMINISTRATOR, phoneNumber, age, gender, salary);
    }

    
}
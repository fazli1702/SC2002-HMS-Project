package user;

import java.util.*;

import timeslot.DoctorAvailabilityManager;

/**
 * Manager that stores and manage all users
 */
public class UserManager {

    private int nextHospitalID = 1001;
    private DoctorAvailabilityManager doctorAvailabilityManager;
    private ArrayList<User> userDB;

    /**
     * Constructor
     * @param doctorAvailabilityManager instance of DoctorAvailabilityManager
     */
    public UserManager(DoctorAvailabilityManager doctorAvailabilityManager){
        this.userDB = new ArrayList<>();
        this.doctorAvailabilityManager = doctorAvailabilityManager;
    }

    /**
     * find the max hospitalID in userDB to get the new nextHospitalID
     */
    public void updateNextHospitalID(){
        int maxHospitalID = -1;
        for (User user : userDB){
            if (user.getHospitalID() > maxHospitalID){
                maxHospitalID = user.getHospitalID();
            }
        }
        this.nextHospitalID = maxHospitalID + 1;
    }

    /**
     * Checks if users exist within database
     * @param hospitalID
     * @return true if exist else false
     */
    public Boolean userExist(int hospitalID){
        for (int i = 0; i < this.userDB.size(); i++){  
            if (this.userDB.get(i).getHospitalID() == hospitalID)
                return true;
        }
        return false;
    }

    /**
     * 
     * @return databse of all users
     */
    public ArrayList<User> getUserDB(){
        return this.userDB;
    }

    /**
     * 
     * @param hospitalID hospitalID of patient 
     * @return instance of user with particular hospitalID
     */
    public User getUser(int hospitalID){
        for (int i = 0; i < this.userDB.size(); i++){
            if (userDB.get(i).getHospitalID() == hospitalID){
                return userDB.get(i);
            }
        }
        return null;
    }

    /**
     * Adds a patient to the database by creating a new Patient instance and adding it to the DB
     * @param hospitalID hospitalID of patient
     * @param name name of patient
     * @param email email of patient
     * @param phoneNumber phone number of patient
     * @param age age of patient
     * @param gender gender of patient
     * @param DOB date of birth of patient
     * @param bloodType blood type of patient
     */
    public void addPatient(int hospitalID, String name, String email, int phoneNumber, int age, Gender gender, String DOB, String bloodType){
        if (hospitalID == 0){
            hospitalID = this.nextHospitalID;
        }
        Patient patient = new Patient(name, hospitalID, email, phoneNumber, age, gender, DOB, bloodType);
        userDB.add(patient);
        // System.out.println("patient added");
        this.updateNextHospitalID();
    }

    /**
     * Adds a patient to the database by creating a new Doctor/Pharmacist/Administrator instance and adding it to the DB
     * @param hospitalID
     * @param name
     * @param email
     * @param userType
     * @param phoneNumber
     * @param age
     * @param gender
     * @param salary
     */
    public void addStaff(int hospitalID, String name, String email, UserType userType, int phoneNumber, int age, Gender gender, int salary){
        if (hospitalID == 0){
            hospitalID = this.nextHospitalID;
        }


        if (userType == UserType.DOCTOR){
            Doctor doctor = new Doctor(name, hospitalID, email, phoneNumber, age, gender, salary, doctorAvailabilityManager);
            userDB.add(doctor);
            // System.out.println("doctor added");
        }
        else if (userType == UserType.PHARMACIST){
            Pharmacist pharmacist = new Pharmacist(name, hospitalID, email, phoneNumber, age, gender, salary);
            userDB.add(pharmacist);
            // System.out.println("pharmacist added");
        }
        else if (userType == UserType.ADMINISTRATOR){
            Administrator admin = new Administrator(name, hospitalID, email, phoneNumber, age, gender, salary);
            userDB.add(admin);
            // System.out.println("Admin added");
        }
        this.updateNextHospitalID();
    }

    /**
     * Removes particular user from databse
     * @param hospitalID hospitalID of user to be removed
     */
    public void removeUser(int hospitalID){
        for (int i = 0; i < this.userDB.size(); i++){
            if (userDB.get(i).getHospitalID() == hospitalID){
                userDB.remove(i);
            }
        } 
    }

    /**
     * Prints all users info with formatting
     */
    public void viewAllUsers(){
        System.out.println(String.format("%-5s %-15s %-15s %-15s", "ID", "Name", "UserType", "Password"));
        System.out.println("----------------------------------------------------");
        for (User user : userDB){
            user.viewUser();
        }
        System.out.println("Next Hospital ID: " + this.nextHospitalID);
        System.out.println("----------------------------------------------------");
    }   

    /**
     * Prints all staff info with formatting
     */
    public void viewAllStaff(){
        System.out.println(String.format("%-5s %-15s %-15s %-15s", "ID", "Name", "UserType", "Password"));
        for (User user : userDB){
            if (user.getUserType() != UserType.PATIENT){
                user.viewUser();
            }
        }
    }

    /**
     * Prints all patient info with formatting
     */
    public void viewAllPatient(){
        System.out.println(String.format("%-5s %-15s %-10s %-15s", "ID", "Name", "UserType", "Password"));
        System.out.println("----------------------------------------------------");
        for (User user : userDB){
            if (user.getUserType() == UserType.PATIENT){
                user.viewUser();
            }
        }
    }
}

package user;

// import java.io.*;
import java.util.*;

/**
 * Stores medical record of particular patient 
 * and contain methods related to medical record
 */
public class MedicalRecord {

    private int patientID;
    private String name;
    private String DOB;
    private Gender gender;
    private String email;
    private int phoneNumber;
    private String bloodType;
    private ArrayList<String> Treatments;
    private ArrayList<String> Diagnosis;
    private ArrayList<String> prescribedMedication;

    /**
     * Constructor
     * @param patientID hospitalID of patient
     * @param name name of patient
     * @param DOB date of birth of patient
     * @param gender gender of patient
     * @param phoneNumber phone number of patient
     * @param email email of patient
     * @param bloodType blood type of patient
     */
    public MedicalRecord(int patientID, String name, String DOB, Gender gender, int phoneNumber, String email, String bloodType){
        this.patientID = patientID;
        this.name = name;
        this.DOB = DOB;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bloodType = bloodType;
        this.Treatments = new ArrayList<>();
        this.Diagnosis = new ArrayList<>();
        this.prescribedMedication = new ArrayList<>();
    }

    /**
     * 
     * @return patientID
     */
    public int getPatientID(){
        return this.patientID;
    }

    /**
     * Sets the patientID
     * @param patientID
     */
    public void setPatientID(int patientID){
        this.patientID = patientID;
    }

    /**
     * 
     * @return name of patient
     */
    public String getName(){
        return this.name;
    }

    /**
     * Sets name of patient
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * 
     * @return date of birth of patient
     */
    public String getDOB(){
        return this.DOB;
    }   

    /**
     * Sets date of birth of patient
     * @param DOB
     */
    public void setDOB(String DOB){
        this.DOB = DOB;
    }

    /**
     * 
     * @return gender of patient
     */
    public Gender getGender(){
        return this.gender;
    }

    /**
     * Sets gender of patient
     * @param gender
     */
    public void setGender(Gender gender){
        this.gender = gender;
    }

    /**
     * 
     * @return email of patient
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * Sets email of patient
     * @param email
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * 
     * @return phone number of patient
     */
    public int getPhoneNumber(){
        return this.phoneNumber;
    }

    /**
     * Sets phone number of patient
     * @param phoneNumber
     */
    public void setPhoneNumber(int phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    /**
     * 
     * @return blood type of patient
     */
    public String getBloodType(){
        return this.bloodType;
    }

    /**
     * Sets blood type of patient
     * @param bloodType
     */
    public void setBloodType(String bloodType){
        this.bloodType = bloodType;
    }

    /**
     * 
     * @return past treatment of patient
     */
    public ArrayList<String> getTreatments(){
        return this.Treatments;
    }

    /**
     * Adds treatment to patient's medical record
     * @param treatment
     */
    public void addTreatment(String treatment){
        this.Treatments.add(treatment);
    }

    /**
     * 
     * @return all past diagnosis of patient
     */
    public ArrayList<String> getDiagnosis(){
        return this.Diagnosis;
    }

    /**
     * Adds a diagnosis to patient medical record
     * @param diagnosis
     */
    public void addDiagnosis(String diagnosis){
        this.Diagnosis.add(diagnosis);
    }

    /**
     * 
     * @return all prescribed medication of patient
     */
    public ArrayList<String> getPrescribedMedication(){
        return this.prescribedMedication;
    }
    
    /**
     * Adds a prescribed medication to patient's medical record
     * @param prescribedMedication
     */
    public void addPrescribedMedication(String prescribedMedication){
        this.prescribedMedication.add(prescribedMedication);
    }

    /**
     * Prints the medical record of patient with formatting
     */
    public void viewMedicalRecord(){
        System.out.println(String.format("%-5d %-15s %-10s %-25s %-12s %-15s %-12s %-20s %-20s %-15s", 
                patientID, name, gender, email, DOB, phoneNumber, bloodType,
                Treatments.isEmpty() ? "None" : String.join(", ", Treatments),
                Diagnosis.isEmpty() ? "None" : String.join(", ", Diagnosis),
                prescribedMedication.isEmpty() ? "None" : String.join(", ", prescribedMedication)));
    }

}

package user;


import java.util.*;
import appointment.*;
import timeslot.*;
import timeslot.Date;

/**
 *  Doctor class is a subclass of Staff class. 
 */
public class Doctor extends Staff{

    private ArrayList<Patient> doctorPatients;
    private ArrayList<DoctorAvailabilityDate> schedule;

    /**
     * Constructor
     * @param name name of doctor
     * @param hospitalID hospitalID of doctor
     * @param email email of doctor
     * @param phoneNumber phone number of doctor
     * @param age age of doctor
     * @param gender gender of doctor
     * @param salary salary of doctor
     * @param doctorAvailabilityManager instance of DoctorAvailabilityManager
     */
    public Doctor(String name, int hospitalID, String email, int phoneNumber, int age, Gender gender, int salary, DoctorAvailabilityManager doctorAvailabilityManager){
        super(hospitalID, name, email, UserType.DOCTOR, phoneNumber, age, gender, salary);
        this.schedule = new ArrayList<>();
        for (int i = 1; i < 8; i++){
            this.schedule.add(new DoctorAvailabilityDate(new Date(i,12,2024)));
        }
        doctorAvailabilityManager.addDoctorToAll(hospitalID);
    }

    /**
     * Adds a patient to doctor
     * @param patient instance of Patient class to be added
     */
    public void addPatient(Patient patient){
        doctorPatients.add(patient);
    }

    /**
     * removes patient from doctor
     * @param patient instance of Patient class to be removed
     */
    public void removePatient(Patient patient){
        doctorPatients.remove(patient);
    }

    /**
     * Returns Patient
     * @param patientID HospitalID of patient
     * @return instance of Patient with patientID
     */
    public Patient getPatient(int patientID){
        for (Patient patient : doctorPatients){
            if (patient.getHospitalID() == patientID){
                return patient;
            }
        }
        return null;
    }

    /**
     * 
     * @return All doctors that are associated with doctor
     */
    public ArrayList<Patient> getPatients(){
        return this.doctorPatients;
    }

    /**
     * View personal schedule of doctor
     */
    public void viewSchedule(){
        System.out.println(String.format("%-15s %-10s %-10s %-10s %-10s %-10s %-10s","Date", "0900-1000","1000-1100","1100-1200",
        "1300-1400","1400-1500","1500-1600","1600-1700"));
        for (int i = 0; i < 80; i++){
            System.out.print("-");
        }
        System.out.print("\n");
        for (DoctorAvailabilityDate d : schedule){
            d.viewAvailability();
        }
    }

    /**
     * Set particular date and time slot to available
     * @param slotNum slot number
     * @param day day of date
     * @param month month of date
     * @param year year of date
     */
    public void setAvailable(int slotNum, int day, int month, int year){
        for (DoctorAvailabilityDate d : schedule){
            if (d.isDate(day, month, year)){
                d.setAvailable(slotNum);
            }
        }
    }

    /**
     * Set particular date and time slot to unavailable
     * @param slotNum slot number
     * @param day day
     * @param month month 
     * @param year year
     */
    public void setUnavailable(int slotNum, int day, int month, int year){
        for (DoctorAvailabilityDate d : schedule){
            if (d.isDate(day, month, year)){
                d.setUnavailable(slotNum);
            }
        }
    }
    /**
     * Checks if the particular date exists in the doctor's schedule
     * @param day day 
     * @param month month
     * @param year year
     * @return true or false
     */
    public Boolean dateExist(int day, int month, int year){
        for (DoctorAvailabilityDate d : schedule){
            if (d.isDate(day, month, year)){
                return true;
            }
        }
        return false;
    }

}
    
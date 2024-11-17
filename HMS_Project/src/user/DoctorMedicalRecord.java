package user;

import appointment.*;
import java.util.*;

/**
 * Class contain methods related to doctor and patient's medical record
 */
public class DoctorMedicalRecord {

    /**
     * Constructor
     */
    public DoctorMedicalRecord(){}

    /**
     * Prints the medical record of particular patient
     * @param patientID hospitalID of patient
     * @param userManager instance of UserManager
     */
    public void viewMedicalRecord(int patientID, UserManager userManager){
        User user = userManager.getUser(patientID);
        Patient patient = (Patient) user;
        patient.viewMedicalRecord();
    }

    /**
     * Prints medical record of all patients associated with doctor
     * @param doctor instance of Doctor
     * @param userManager instance of UserManager
     * @param appointmentManager instance of AppointmentManager
     */
    public void viewAllMedicalRecord(Doctor doctor, UserManager userManager, AppointmentManager appointmentManager){
        System.out.println(String.format("%-5s %-15s %-10s %-25s %-12s %-15s %-12s %-20s %-20s %-15s",
                "ID", "Name", "Gender", "Email", "DOB", "PhoneNumber", "BloodType",
                "Treatments", "Diagnoses", "Medications"));
        for (int i = 0; i < 165;i++){
            System.out.print("-");
        }
        System.out.println();
        ArrayList<Patient> patients = new ArrayList<>();
        for (Appointment appointment : appointmentManager.getAppointmentDB()){
            Patient patient = (Patient) userManager.getUser(appointment.getPatientID());
            if (!(patients.contains(patient)) && (appointment.getDoctorID() == doctor.getHospitalID())){
                patient.viewMedicalRecord();
                patients.add(patient);
            }
        }
    }

    /**
     * Adds diagnosis to patient record
     * @param patientID hospitalID of patient
     * @param userManager instance of UserManager
     * @param diagnosis diagnosis that will be added to patient's medical record
     */
    public void addDiagnosis(int patientID, UserManager userManager, String diagnosis){
        User user = userManager.getUser(patientID);
        Patient patient = (Patient) user;
        patient.getMedicalRecord().addDiagnosis(diagnosis);
    }

    /**
     * Adds prescription to patient medical record
     * @param patientID hospitalID of patient
     * @param userManager instance of UserManager
     * @param prescription prescription that will be added to patient's medical record
     */
    public void addPescription(int patientID, UserManager userManager, String prescription){
        User user = userManager.getUser(patientID);
        Patient patient = (Patient) user;
        patient.getMedicalRecord().addPrescribedMedication(prescription);
    }

    /**
     * Adds treatment to patient medical record
     * @param patientID hospitalID of patient
     * @param userManager instance of UserManager
     * @param treatment treatment that will be added to patient's medical record
     */
    public void addTreatmentPlan(int patientID, UserManager userManager, String treatment){
        User user = userManager.getUser(patientID);
        Patient patient = (Patient) user;
        patient.getMedicalRecord().addTreatment(treatment);
    }

    
}

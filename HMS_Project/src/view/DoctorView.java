package view;

import user.*;
// import timeslot.*;
// import appointment.*;
// import inventory.*;
import java.util.*;

import appointment.*;
import inventory.MedicineManager;
import timeslot.*;
import timeslot.Date;

/**
 * The DoctorView class provides an interface for doctors to interact with the Hospital Management System (HMS).
 * This class allows doctors to manage appointments, view and update medical records, set availability, 
 * and handle other related tasks.
 */
public class DoctorView implements IDisplayMenu{

    private Doctor doctor;
    private UpdatePasswordView updatePasswordView;
    private AppointmentManager appointmentManager;
    private DoctorAvailabilityManager doctorAvailabilityManager;
    private DoctorAppointment doctorAppointment;
    private UserManager userManager;
    private MedicineManager medicineManager;
    private DoctorMedicalRecord doctorMedicalRecord;
    private Scanner scanner;

    /**
     * Constructs an instance of DoctorView.
     *
     * @param doctor the logged-in doctor
     * @param appointmentManager manages appointments
     * @param doctorAvailabilityManager manages doctor availability
     * @param medicineManager manages medicine inventory
     * @param userManager manages user-related operations
     * @param scanner the scanner object for user input
     */
    public DoctorView(Doctor doctor, AppointmentManager appointmentManager, DoctorAvailabilityManager doctorAvailabilityManager, MedicineManager medicineManager, UserManager userManager, Scanner scanner){
        this.doctor = doctor;

        this.appointmentManager = appointmentManager;
        this.medicineManager = medicineManager;
        this.doctorAvailabilityManager = doctorAvailabilityManager;
        this.userManager = userManager;

        this.updatePasswordView = new UpdatePasswordView(doctor, scanner);
        this.doctorAppointment = new DoctorAppointment();
        
        this.doctorMedicalRecord = new DoctorMedicalRecord();

        this.scanner = scanner;
    }

    /**
     * Displays the main menu for the doctor and handles navigation to various functionalities.
     */
    public void displayMenu(){
        int choice;
        while (true){
            while (true){
                System.out.println("\nDoctor Menu:");
                System.out.println("1. View Patient Medical Records");
                System.out.println("2. Update Patient Medical Records");
                System.out.println("3. View Personal Schedule");
                System.out.println("4. Set Availability for Appointments");
                System.out.println("5. Accept or Decline Appointment Request");
                System.out.println("6. View Upcoming Appointments");
                System.out.println("7. Record Appointment Outcome");
                System.out.println("8. Change Password");
                System.out.println("9. Logout");
                System.out.print("Enter your choice: ");
                
                if (this.scanner.hasNextInt()){
                    choice = this.scanner.nextInt();
                    break;
                }
                else{
                    this.scanner.next();
                    System.out.println("Invalid choice. Try again");
                }
            }

            if (choice == 1){
                viewMedicalRecordsView();
            }
            else if (choice == 2){
                updateMedicalRecordsView();
            }
            else if (choice == 3){
                viewScheduleView();
            }
            else if (choice == 4){
                viewSetAvailabilityAppointment();
            }
            else if (choice == 5){
                acceptAppointmentRequestView();
            }
            else if (choice == 6){
                viewUpcomingAppointmentView();
            }
            else if (choice == 7){
                recordAppointmentOutcomeView();
            }
            else if (choice == 8){
                updatePasswordView.updatePassword();
            }
            else{
                break;
            }
        }
    }

    /**
     * Displays all medical records associated with patients managed by the doctor.
     */
    public void viewMedicalRecordsView(){
        doctorMedicalRecord.viewAllMedicalRecord(doctor, userManager, appointmentManager);
    }

    /**
     * Updates specific medical records for a patient, such as treatments, diagnoses, or prescriptions.
     */
    public void updateMedicalRecordsView(){
        int patientID, choice;
        String updatedMedical;

        viewMedicalRecordsView();

        while (true){
            System.out.print("Enter patient id: ");
            if (scanner.hasNextInt()){
                patientID = scanner.nextInt();
                if (userManager.userExist(patientID)){
                    break;
                }
            }
            else{
                scanner.next();
            }
            System.out.println("Invalid patient id. Try again");
        }

        while (true){
            System.out.println("Which medical information to be updated");
            System.out.println("1. Treatment");
            System.out.println("2. Diagnosis");
            System.out.println("3. Prescribed Medication");
            System.out.println();
            System.out.println("Enter your choice: ");
            if (scanner.hasNextInt()){
                choice = scanner.nextInt();
                if (1 <= choice && choice <= 3){
                    break;
                }
            }
            else{
                scanner.next();
            }
            System.out.println("Invalid input. Try again");
        }
        
        Patient patient = (Patient) userManager.getUser(patientID);
        if (choice == 1){
            System.out.print("Enter treatment: ");
            updatedMedical = scanner.next();
            patient.getMedicalRecord().addTreatment(updatedMedical);
        }
        else if (choice == 2){
            System.out.println("Enter diagnosis: ");
            updatedMedical = scanner.next();
            patient.getMedicalRecord().addDiagnosis(updatedMedical);
        }
        else{
            System.out.println("Enter prescription: ");
            updatedMedical = scanner.next();
            patient.getMedicalRecord().addPrescribedMedication(updatedMedical);
        }

        
    }

    /**
     * Displays the schedule of the doctor.
     */
    public void viewScheduleView(){
        doctor.viewSchedule();
    }

    /**
     * Allows the doctor to set or update their availability for appointments.
     */
    public void viewSetAvailabilityAppointment(){
        int day, month, year, slot, choice;
        doctor.viewSchedule();

        while (true){
            System.out.println("Date");
            while (true){
                System.out.print("Enter day: ");
                if (scanner.hasNextInt()){
                    day = scanner.nextInt();
                    break;
                }
                scanner.next();
                System.out.println("Invalid day");
            }
            while (true){
                System.out.print("Enter month: ");
                if (scanner.hasNextInt()){
                    month = scanner.nextInt();
                    break;
                }
                scanner.next();
                System.out.println("Invalid month");
            }
            while (true){
                System.out.print("Enter year: ");
                if (scanner.hasNextInt()){
                    year = scanner.nextInt();
                    break;
                }
                scanner.next();
                System.out.println("Invalid year");
            }

            if (doctor.dateExist(day, month, year)){
                break;
            }
            System.out.println("Invalid date. Try again");
        }

        while (true){
            System.out.println("Timeslot");
            System.out.println("1. 0900-1000");
            System.out.println("2. 1000-1100");
            System.out.println("3. 1100-1200");
            System.out.println("4. 1300-1400");
            System.out.println("5. 1400-1500");
            System.out.println("6. 1500-1600");
            System.out.println("7. 1600-1700");
            System.out.println();
            System.out.print("Enter choice: ");

            if (scanner.hasNextInt()){
                slot = scanner.nextInt();
                if (1 <= slot && slot <= 7){
                    break;
                }
            }
            else{
                scanner.next();
            }
            System.out.println("Invalid choice. Try again");
        }

        while (true){
            System.out.println("Set availability");
            System.out.println("1. Available");
            System.out.println("2. Not Available");
            System.out.println();
            System.out.print("Enter choice: ");

            if (scanner.hasNextInt()){
                choice = scanner.nextInt();
                if (choice == 1){
                    doctor.setAvailable(slot, day, month, year);
                    doctorAvailabilityManager.addDoctorAvailability(day, slot, doctor.getHospitalID());
                    break;
                }
                else if (choice == 2){
                    doctor.setUnavailable(slot, day, month, year);
                    doctorAvailabilityManager.removeDoctorAvailability(day, slot, doctor.getHospitalID());
                    break;
                }
            }
            else{
                scanner.next();
            }
            System.out.println("Invalid choice. Try again");
        }
        System.out.println("Availabilty updated");
    }

    /**
     * Records the outcome of an appointment, including services provided and consultation notes.
     */
    public void recordAppointmentOutcomeView(){
        AppointmentOutcome appointmentOutcome;
        String serviceProvided, consultationNotes, prescription;
        int appointmentID, prescriptionQuantity;
        
        viewUpcomingAppointmentView();
        while (true){
            System.out.print("Enter Appointment ID: ");
            if (scanner.hasNextInt()){
                appointmentID = scanner.nextInt();
                if (appointmentManager.getAppointment(appointmentID) != null){
                    break;
                }
            }
            else{
                scanner.next();
            }
            System.out.println("Invalid ID. Try again");
        }

        System.out.print("Enter service provided: ");
        serviceProvided = scanner.next();
        
        System.out.print("Enter consultation notes: ");
        consultationNotes = scanner.next();

        medicineManager.viewAllMedicine();
        while (true){
            System.out.print("Enter prescription: ");
            prescription = scanner.next();
            if (medicineManager.medicineExist(prescription)){
                break;
            }
            System.out.println("Invalid prescription. Try again");
        }
        
        while (true){
            System.out.print("Enter prescription quantity: ");
            if (scanner.hasNextInt()){
                prescriptionQuantity = scanner.nextInt();
                if (prescriptionQuantity > 0){
                    break;
                }
            }
            else{
                scanner.next();
            }
            System.out.println("Invalid quantity");
        }
        doctorAppointment.recordAppointmentOutcome(appointmentID, appointmentManager, serviceProvided, consultationNotes, prescription, prescriptionQuantity);
        Patient patient = (Patient) userManager.getUser(appointmentManager.getAppointment(appointmentID).getPatientID());
    }

    /**
     * Displays all upcoming appointments for the doctor.
     */
    public void viewUpcomingAppointmentView(){
        appointmentManager.viewAppointmentByDoctorFilter(doctor.getHospitalID(), RequestStatus.ACCEPTED);
    }

    /**
     * Allows the doctor to accept or decline appointment requests.
     */
    public void acceptAppointmentRequestView(){
        int appointmentID, choice;
        appointmentManager.viewAppointmentByDoctorFilter(doctor.getHospitalID(), RequestStatus.PENDING);
        while (true){
            System.out.print("Enter appointment ID: ");
            if (scanner.hasNextInt()){
                appointmentID = scanner.nextInt();
                if (appointmentManager.getAppointment(appointmentID) != null){
                    break;
                }
            }
            else{
                scanner.next();
            }
            System.out.println("Invalid appointmend id. Try again");
        }

        while (true){
            System.out.println("1. Accept");
            System.out.println("2. Decline");
            System.out.println();
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()){
                choice = scanner.nextInt();
                if (1 <= choice && choice <= 2){
                    break;
                }
            }
            else{
                scanner.next();
            }
            System.out.println("Invalid choice. Try again");
        }

        if (choice == 1){
            doctorAppointment.acceptAppointment(doctor, appointmentID, appointmentManager, doctorAvailabilityManager);
        }

        else{
            doctorAppointment.declineAppointment(doctor, appointmentID, appointmentManager, doctorAvailabilityManager);
        }
    }
}
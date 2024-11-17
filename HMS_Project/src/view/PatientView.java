package view;

import java.util.*;

import appointment.AppointmentManager;
import timeslot.DoctorAvailabilityManager;
import user.*;
import timeslot.Date;

/**
 * The PatientView class provides an interface for patients to interact with the Hospital Management System (HMS)
 * This class allows patients to  view and update medical records, set availability, 
 */
public class PatientView implements IDisplayMenu{

    private Patient patient;
    private UpdatePasswordView updatePasswordView;
    private AppointmentManager appointmentManager;
    private DoctorAvailabilityManager doctorAvailabilityManager;
    private PatientAppointment patientAppointment;
    private UserManager userManager;
    private Scanner scanner;

    /**
     * Constructs an instance of PatientView
     *
     * @param patient The patient object representing the current patient.
     * @param appointmentManager The manager to handle appointment-related operations.
     * @param doctorAvailabilityManager The manager to handle doctor availability.
     * @param userManager The manager to handle user-related operations.
     * @param scanner The {@code Scanner} object for reading user input.
     */
    public PatientView(Patient patient, AppointmentManager appointmentManager, DoctorAvailabilityManager doctorAvailabilityManager, UserManager userManager, Scanner scanner){
        this.patient = patient;
        this.appointmentManager = appointmentManager;
        this.doctorAvailabilityManager = doctorAvailabilityManager;
        this.updatePasswordView = new UpdatePasswordView(patient, scanner);
        this.patientAppointment = new PatientAppointment();
        this.userManager = userManager;
        this.scanner = scanner;
    }

    /**
     * Displays the main menu for the patient and handles user interactions.
     */
    public void displayMenu(){
        int choice;
        while (true){
            while (true){
                System.out.println("\nPatient Menu:");
                System.out.println("1. View Medical Record");
                System.out.println("2. Update Personal Information");
                System.out.println("3. View Available Appointment Slots");
                System.out.println("4. Schedule an Appointment");
                System.out.println("5. Reschedule an Appointment");
                System.out.println("6. Cancel an Appointment");
                System.out.println("7. View Schedule Appointment");
                System.out.println("8. View Past Appointment Outcome Records");
                System.out.println("9. Change Password");
                System.out.println("10. Logout");
                System.out.print("Enter your choice: ");

                if (scanner.hasNextInt()){
                    choice = scanner.nextInt();
                    if (1 <= choice && choice <= 10){
                        break;
                    }
                }
                else{
                    scanner.next();
                }
                System.out.println("Invalid choice. Try again");
            }

            if (choice == 1){
                viewMedicalRecordView();
            }
            else if (choice == 2){
                updatePersonalInfoView();
            }
            else if (choice == 3){
                availableAppointmentView();
            }
            else if (choice == 4){
                scheduleAppointmentView();
            }
            else if (choice == 5){
                rescheduleAppointmentView();
            }
            else if (choice == 6){
                cancelAppointmentView();
            }
            else if (choice == 7){
                viewScheduledAppointmentView();
            }
            else if (choice == 8){
                viewPastAppointmentOutcome();
            }
            else if (choice == 9){
                updatePasswordView.updatePassword();
            }
            else{
                break;
            }

        }
    }

    /**
     * Displays the patient's medical record.
     */
    public void viewMedicalRecordView(){
        System.out.println(String.format("%-5s %-15s %-10s %-25s %-12s %-15s %-12s %-20s %-20s %-15s",
                "ID", "Name", "Gender", "Email", "DOB", "PhoneNumber", "BloodType",
                "Treatments", "Diagnoses", "Medications"));
        for (int i = 0; i < 165;i++){
            System.out.print("-");
        }
        System.out.println();
        patient.viewMedicalRecord();
    }

    /**
     * Displays available appointment slots for the patient.
     */
    public void availableAppointmentView(){
        doctorAvailabilityManager.viewAvailableTimeSlots();
    }

    /**
     * Allows the patient to update their personal information, such as name, email, and phone number.
     */
    public void updatePersonalInfoView(){
        int choice, newPhoneNumber;
        String newName, newEmail;
        while (true){
            System.out.println("Update Personal Information");
            System.out.println("1. Name");
            System.out.println("2. Email");
            System.out.println("3. Phone Number");
            System.out.println("4. Exit");
            System.out.println();
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()){
                choice = scanner.nextInt();
                if (1 <= choice && choice <= 4){
                    break;
                }
            }
            else{
                scanner.next();
            }
            System.out.println("Invalid choice. Try again");
        }

        if (choice == 4){
            return;
        }

        if (choice == 1){
            System.out.print("Enter new name: ");
            newName = scanner.next();
            patient.setName(newName);
        }
        else if (choice == 2){
            System.out.print("Enter new email: ");
            newEmail = scanner.next();
            patient.setEmail(newEmail);
        }
        else{
            while (true){
                System.out.print("Enter new phone number: ");
                if (scanner.hasNextInt()){
                    newPhoneNumber = scanner.nextInt();
                    if (String.valueOf(newPhoneNumber).length() == 8){
                        patient.setPhoneNumber(newPhoneNumber);
                        break;
                    }
                }
                else{
                    scanner.next();
                }
                System.err.println("Invalid phone number. Must be 8 digits. Try again");
            }
        }

    }

    /**
     * Allows the patient to schedule a new appointment by selecting a date and timeslot.
     */
    public void scheduleAppointmentView(){
        int day, month, year, slotNum;
        doctorAvailabilityManager.viewAvailableTimeSlots();
        while (true){
            System.out.println("Date");
            while (true){
                System.out.print("Enter day: ");
                if (scanner.hasNextInt()){
                    day = scanner.nextInt();
                    if (1 <= day && day <= 7){
                        break;
                    }
                }
                else{
                    scanner.next();
                }
                System.out.println("Invalid day");
            }
            while (true){
                System.out.print("Enter month: ");
                if (scanner.hasNextInt()){
                    month = scanner.nextInt();
                    if (month == 12){
                        break;
                    }
                }
                else{
                    scanner.next();
                }
                System.out.println("Invalid month");
            }
            while (true){
                System.out.print("Enter year: ");
                if (scanner.hasNextInt()){
                    year = scanner.nextInt();
                    if (year == 2024){
                        break;
                    }
                }
                else{
                    scanner.next();
                }
                System.out.println("Invalid year");
            }
            break;
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
                slotNum = scanner.nextInt();
                if (1 <= slotNum && slotNum <= 7){
                    break;
                }
            }
            else{
                scanner.next();
            }
            System.out.println("Invalid choice. Try again");
        }

        if (doctorAvailabilityManager.hasAvailableDoctor(day, slotNum)){
            patientAppointment.scheduleAppointment(patient, doctorAvailabilityManager, slotNum, day, appointmentManager, userManager);
            
        }
    }

    /**
     * Displays the patient's scheduled appointments.
     */
    public void viewScheduledAppointmentView(){
        appointmentManager.viewAppointmentByPatient(patient.getHospitalID());
    }

    /**
     * Allows the patient to cancel an existing appointment.
     */
    public void cancelAppointmentView(){
        viewScheduledAppointmentView();
        int appointmentID;
        
        while (true){
            System.out.print("Enter appointment id: ");
            if (scanner.hasNextInt()){
                appointmentID = scanner.nextInt();
                if (appointmentManager.getAppointment(appointmentID) != null){
                    break;
                }
            }
            else{
                scanner.next();
            }
            System.out.println("Invalid appointment id");
        }

        patientAppointment.cancelAppointment(appointmentID, appointmentManager, doctorAvailabilityManager, userManager);
    }

    /**
     * Allows the patient to reschedule an existing appointment.
     */
    public void rescheduleAppointmentView(){
        viewScheduledAppointmentView();
        int appointmentID;
        
        while (true){
            System.out.print("Enter appointment id: ");
            if (scanner.hasNextInt()){
                appointmentID = scanner.nextInt();
                if (appointmentManager.getAppointment(appointmentID) != null){
                    break;
                }
            }
            else{
                scanner.next();
            }
            System.out.println("Invalid appointment id");
        }

        patientAppointment.cancelAppointment(appointmentID, appointmentManager, doctorAvailabilityManager, userManager);
        scheduleAppointmentView();
    }

    /**
     * Displays the patient's past appointment outcomes.
     */
    public void viewPastAppointmentOutcome(){
        appointmentManager.viewPastAppointmentOutcome(patient.getHospitalID(), appointmentManager);
    }
}
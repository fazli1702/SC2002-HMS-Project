package main;

import java.util.Scanner;

import appointment.AppointmentManager;
import view.*;
import user.*;
import csvreader.*;
import inventory.*;
import timeslot.DoctorAvailabilityManager;

/**
 * Main class for the Hospital Management System (HMS) application.
 * This class serves as the entry point for the application and manages
 * the overall flow of the program based on user roles.
 */
public class HMSApp {
    private Scanner scanner;

    private AppView appView;
    private AdministratorView adminView;
    private DoctorView doctorView;
    private PharmacistView pharmacistView;
    private PatientView patientView;

    private UserManager userManager;
    private MedicineManager medicineManager;
    private ReplenishmentManager replenishmentManager;
    private AppointmentManager appointmentManager;
    private DoctorAvailabilityManager doctorAvailabilityManager;

    private ReadCsvStaff readCsvStaff;
    private ReadCsvPatient readCsvPatient;
    private ReadCsvMedicine readCsvMedicine;

    private User currentUser;

    /**
     * Constructor for the HMSApp class.
     * Initializes all manager classes and the main application view.
     */
    public HMSApp() {
        System.out.println("Initializing Hospital Management System...");
        this.scanner = new Scanner(System.in);

        // initialise managers
        this.replenishmentManager = new ReplenishmentManager();
        this.medicineManager = new MedicineManager();
        this.appointmentManager = new AppointmentManager();
        this.doctorAvailabilityManager = new DoctorAvailabilityManager();
        this.userManager = new UserManager(this.doctorAvailabilityManager);

        this.readCsvStaff = new ReadCsvStaff(this.userManager);
        this.readCsvPatient = new ReadCsvPatient(this.userManager);
        this.readCsvMedicine = new ReadCsvMedicine(this.medicineManager);
        

        // initialise views

        // test case
        // this.userManager.addStaff(1001, "John Smith", "john@mail.com", UserType.DOCTOR,  12345678, 20, Gender.MALE, 5000);
        // this.userManager.addStaff(1002, "Emily Clarke", "emily@mail.com", UserType.DOCTOR,  87654321, 25, Gender.FEMALE, 6000);
        // this.userManager.addStaff(1003, "Mark Lee", "mark@mail.com", UserType.PHARMACIST,  432128765, 30, Gender.MALE, 7000);
        // this.userManager.addStaff(1004, "Sarah Lee", "sarah@mail.com", UserType.ADMINISTRATOR,  87651234, 35, Gender.FEMALE, 8000);

        // this.userManager.addPatient(1005, "Alice Brown", "alice.brown@example.com", 12344321, 33, Gender.FEMALE, "14-05-2024", "O+");
        // this.userManager.addPatient(1006, "Bob Stone", "bob.stone@example.com", 97863542, 23, Gender.MALE, "22-11-1975", "B+");
        // this.userManager.addPatient(1007, "Charlie White", "charlie.white@example.com", 89674523, 55, Gender.MALE, "08-07-1977", "A-");

        this.readCsvStaff.readCsv();
        this.readCsvPatient.readCsv();
        // this.userManager.viewAllUsers();

        // this.medicineManager.addMedicine("Paracetamol", 100, 20);
        // this.medicineManager.addMedicine("Ibuprofen", 50, 10);
        // this.medicineManager.addMedicine("Amoxicillin", 75, 15);
        this.readCsvMedicine.readCsv();
        // this.medicineManager.viewAllMedicine();

        this.appView = new AppView(this.scanner, this.userManager);
    }

    /**
     * Runs the main application logic.
     * Handles user login, determines the user type, and delegates to the appropriate view.
     */
    public void run(){
        int choice;
        while (true){
            choice = this.appView.mainMenu();
            if (choice == 2){
                this.appView.exitProgram();
            }
            currentUser = this.appView.login();

            switch (currentUser.getUserType()){
                case ADMINISTRATOR:
                    // System.out.println("ADMINISTRATOR");
                    handleAdminActions(currentUser);
                    break;
                case DOCTOR:
                    // System.out.println("DOCTOR");
                    handleDoctorActions(currentUser);
                    break;
                case PHARMACIST:
                    // System.out.println("PHARMACIST");
                    handlePharmacistActions(currentUser);
                    break;
                case PATIENT:
                    // System.out.println("PATIENT");
                    handlePatientActions(currentUser);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Handles actions for the administrator role.
     *
     * @param adminView The view for the administrator user.
     */
    private void handleAdminActions(User currentUser){
        this.adminView = new AdministratorView((Administrator) currentUser, userManager, medicineManager, replenishmentManager, appointmentManager, scanner);
        this.adminView.displayMenu();
    }

    /**
     * Handles actions for the doctor role.
     *
     * @param doctorView The view for the doctor user.
     */
    private void handleDoctorActions(User currentUser){
        this.doctorView = new DoctorView((Doctor) currentUser, appointmentManager, doctorAvailabilityManager, medicineManager, userManager, scanner);
        this.doctorView.displayMenu();
    }

    /**
     * Handles actions for the pharmacist role.
     *
     * @param pharmacistView The view for the pharmacist user.
     */
    private void handlePharmacistActions(User currentUser){
        this.pharmacistView = new PharmacistView((Pharmacist) currentUser, medicineManager, replenishmentManager , appointmentManager, scanner);
        this.pharmacistView.displayMenu();
    }

    /**
     * Handles actions for the patient role.
     *
     * @param patientView The view for the patient user.
     */
    private void handlePatientActions(User currentUser){
        this.patientView = new PatientView((Patient) currentUser, appointmentManager, doctorAvailabilityManager, userManager, scanner);
        this.patientView.displayMenu();
    }
}

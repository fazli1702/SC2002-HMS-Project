package view;

import java.util.Scanner;

import appointment.AppointmentManager;
import appointment.RequestStatus;
import user.*;
import inventory.*;

/**
 * The PharmacistView class provides an interface for pharmacists to interact with the Hospital Management System (HMS)
 * This class allows pharmacists to view appointment outcomes, Update prescription statuses, Manage inventory, 
 * Submit replenishment requests for medicine, Change their password
 */
public class PharmacistView implements IDisplayMenu{
    private Pharmacist pharmacist;
    private MedicineManager medicineManager;
    private ReplenishmentManager replenishmentManager;
    private UpdatePasswordView updatePasswordView;
    private AppointmentManager appointmentManager;
    private Scanner scanner;

    /**
     * Constructs a {@code PharmacistView} with the required dependencies.
     *
     * @param pharmacist The pharmacist object representing the current pharmacist.
     * @param medicineManager The manager to handle medicine-related operations.
     * @param replenishmentManager The manager to handle replenishment requests.
     * @param appointmentManager The manager to handle appointment-related operations.
     * @param scanner The {@code Scanner} object for reading user input.
     */
    public PharmacistView(Pharmacist pharmacist, MedicineManager medicineManager, ReplenishmentManager replenishmentManager, AppointmentManager appointmentManager, Scanner scanner){
        this.scanner = scanner;    
        this.pharmacist = pharmacist;
        this.medicineManager = medicineManager;
        this.replenishmentManager = replenishmentManager;
        this.appointmentManager = appointmentManager;
        this.updatePasswordView = new UpdatePasswordView(pharmacist, scanner);
    }

    /**
     * Displays the main menu for the pharmacist and handles user interactions.
     */
    public void displayMenu(){
        int choice;
        while (true){
            while (true){
                System.out.println("\nPharmacist Menu:");
                System.out.println("1. View Appointment Outcome");
                System.out.println("2. Update Prescription Status");
                System.out.println("3. View Inventory");
                System.out.println("4. Submit Replenishment Request");
                System.out.println("5. Update Password");
                System.out.println("6. Logout");
                System.out.println();
                System.out.print("Enter your choice: ");

                if (this.scanner.hasNextInt()){
                    choice = this.scanner.nextInt();
                    if (1 <= choice && choice <= 6){
                        break;
                    }
                }
                this.scanner.next();
                System.out.println("Invalid choice. Try again");
            }

            if (choice == 1){
                viewAppointmentOutcome();
            }
            else if (choice == 2){
                updatePrescriptionStatus();
            }
            else if (choice == 3){
                viewInventoryView();
            }
            else if (choice == 4){
                submitReplenishmentRequestView();
            }
            else if (choice == 5){
                updatePasswordView.updatePassword();
            }
            else if (choice == 6){
                break;
            }
 
        }
    }


    /**
     * Displays the entire medicine inventory.
     */
    public void viewInventoryView(){
        medicineManager.viewAllMedicine();
    }

    /**
     * Allows the pharmacist to submit a replenishment request for a specific medicine.
     */
    public void submitReplenishmentRequestView(){
        int quantity;
        String medicineName;
        this.viewInventoryView();
        while (true){
            System.out.println("Enter requested name of medicine: ");
            medicineName = scanner.next();
            if (medicineManager.getMedicine(medicineName) != null){
                System.out.println("Enter requested quantity: ");
                if (scanner.hasNextInt()){
                    quantity = scanner.nextInt();
                    replenishmentManager.addReplenishmentRequest(medicineName, quantity);
                    break;       
                }
            }
            System.out.println("Invalid input. Try again");
        }
    }

    /**
     * Allows the pharmacist to update the prescription status for a completed appointment.
     * If the medicine is sufficient, it dispenses the prescription; otherwise, notifies the user.
     */
    public void updatePrescriptionStatus(){
        appointmentManager.viewAppointmentByFilter(RequestStatus.COMPLETED);
        int appointmentID;

        while (true){
            System.out.print("Enter appointment id: ");
            if (scanner.hasNextInt()){
                appointmentID = scanner.nextInt();
                if (appointmentManager.getAppointment(appointmentID) != null){
                    if (appointmentManager.getAppointment(appointmentID).getStatus() == RequestStatus.COMPLETED){
                        break;
                    }
                }
            }
            else{
                scanner.next();
            }
            System.out.println("Invalid appointment id. Try again");
        }
        if (pharmacist.isSufficientPrescription(appointmentID, appointmentManager, medicineManager)){
            pharmacist.dispensePrescription(appointmentID, appointmentManager, medicineManager);
        }
        else{
            System.out.println("Insufficient medication");
        }
    }

    /**
     * Displays the outcomes of all appointments handled by the hospital.
     */
    public void viewAppointmentOutcome(){
        appointmentManager.viewAllAppointmentOutcome(appointmentManager);
    }
}

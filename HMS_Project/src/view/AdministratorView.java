package view;

import java.util.*;

import appointment.AppointmentManager;
import inventory.*;
import user.*;

/**
 * The AdministratorView class provides a user interface for administrators in the Hospital Management System (HMS).
 * It allows administrators to manage staff, view appointments, handle inventory, and approve replenishment requests.
 */
public class AdministratorView implements IDisplayMenu{
    
    private Administrator administrator;
    private AdministratorViewing administratorViewing;
    private UserManager userManager;
    private MedicineManager medicineManager;
    private ReplenishmentManager replenishmentManager;
    private UpdatePasswordView updatePasswordView;
    private AppointmentManager appointmentManager;
    private Scanner scanner;

    /**
     * Constructs an AdministratorView with the specified managers for users, appointments, and medicines.
     *
     * @param administrator        The administrator using the view.
     * @param userManager          The UserManager to manage hospital staff.
     * @param medicineManager      The MedicineManager to manage inventory.
     * @param replenishmentManager The ReplenishmentManager to handle replenishment requests.
     * @param appointmentManager   The AppointmentManager to manage appointment details.
     * @param scanner              A Scanner object for user input.
     */
    public AdministratorView(Administrator administrator, UserManager userManager, MedicineManager medicineManager, ReplenishmentManager replenishmentManager, AppointmentManager appointmentManager, Scanner scanner){
        this.administrator = administrator;
        this.administratorViewing = new AdministratorViewing();
        this.userManager = userManager;
        this.medicineManager = medicineManager;
        this.replenishmentManager = replenishmentManager;
        this.appointmentManager = appointmentManager;
        this.updatePasswordView = new UpdatePasswordView(administrator, scanner);
        this.scanner = scanner;
    }

    /**
     * Displays the administrator menu and handles user choices to perform various administrative tasks.
     */
    public void displayMenu(){
        int choice;
        while (true){
            while (true){
                System.out.println("\nAdministrator Menu:");
                System.out.println("1. View Hospital Staff by filter");
                System.out.println("2. Manage Hospital Staff");
                System.out.println("3. View Appointment Details");
                System.out.println("4. View and Manage Medication Inventory");
                System.out.println("5. Approve Replenishment Request");
                System.out.println("6. Update password");
                System.out.println("7. Logout");
                System.out.println();
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
                viewHospitalStaffView();
            }
            if (choice == 2){
                manangeHospitalStaffView();
            }
            if (choice == 3){
                viewAppointmentDetailsView();
            }
            if (choice == 4){
                viewAndManageInventoryView();
            }
            if (choice == 5){
                approveReplenishmentRequestView();
            }
            if (choice == 6){
                updatePasswordView.updatePassword();
            }
            if (choice == 7){
                break;
            }

        }
    }

    /**
     * Displays and manages the hospital's medication inventory.
     * Allows administrators to view all medicines, add new medicines,
     * remove medicines, or update stock levels.
     */
    public void viewAndManageInventoryView(){
        int choice, inventoryChoice, quantity;
        String medicineName;

        medicineManager.viewAllMedicine();

        while (true){
            System.out.println("1. Add new medicine");
            System.out.println("2. Remove medicine");
            System.out.println("3. Update medicine");
            System.out.println("4. Exit");
            System.out.println();
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()){
                choice = scanner.nextInt();
                if (1 <= choice && choice <= 4){
                    break;
                }
            }
            scanner.next();
            System.out.println("Invalid choice. Try again");
        }

        if (choice == 4){
            return;
        }

        if (choice == 1){
            int currQuantity, lowStockQuantity;

            // get name
            System.out.print("Enter new medicine name: ");
            medicineName = scanner.next();

            // get current quantity
            while (true){
                System.out.print("Enter current quantity: ");
                if (scanner.hasNextInt()){
                    currQuantity = scanner.nextInt();
                    if (currQuantity > 0){
                        break;
                    }
                }
                scanner.next();
                System.out.println("Invalid quantity. Try again");
            }

            // get low stock quantity
            while (true){
                System.out.print("Enter low stock quantity: ");
                if (scanner.hasNextInt()){
                    lowStockQuantity = scanner.nextInt();
                    if (lowStockQuantity > 0){
                        break;
                    }
                }
                scanner.next();
                System.out.println("Invalid quantity. Try again");
            }
            medicineManager.addMedicine(medicineName, currQuantity, lowStockQuantity);
            System.out.println("New medicine added successfully");
        }

        else if (choice == 2){
            while (true){
                System.out.print("Enter medicine name: ");
                medicineName = scanner.next();
                if (medicineManager.medicineExist(medicineName)){
                    break;
                }
                scanner.next();
                System.out.println("Invalid medicine name. Try again");
            }
            medicineManager.removeMedicine(medicineName);
            System.out.println("Medicine removed successfully");
        }

        else if (choice == 3){
            while (true){
                System.out.print("Enter medicine name: ");
                medicineName = scanner.next();
                if (medicineManager.medicineExist(medicineName)){
                    break;
                }
                scanner.next();
                System.out.println("Invalid name. Try again");
            }

            while (true){
                System.out.println("1. Increase stock");
                System.out.println("2. Decrease stock");
                System.out.println();
                System.out.print("Enter choice:");

                if (scanner.hasNextInt()){
                    inventoryChoice = scanner.nextInt();
                    if (1 <= inventoryChoice && inventoryChoice <= 3){
                        break;
                    }
                }
                scanner.next();
                System.out.println("Invalid choice. Try again");
            }
            

            while (true){
                System.out.print("Enter quantity: ");
                if (scanner.hasNextInt()){
                    quantity = scanner.nextInt();
                    if (quantity > 0){
                        if (inventoryChoice == 2){
                            int medicineQuantity = medicineManager.getMedicine(medicineName).getQuantity();
                            if (quantity <= medicineQuantity){
                                break;
                            }
                        }
                        else{
                            break;
                        }
                    }
                }
                scanner.next();
                System.out.println("Invalid quantity. Try again");
            }

            if (inventoryChoice == 1){
                medicineManager.increaseMedicineStock(medicineName, quantity);
            }
            else if (inventoryChoice == 2){
                medicineManager.decreaseMedicineStock(medicineName, quantity);
            }
        }

        medicineManager.viewAllMedicine();
    }

    /**
     * Displays and handles the approval process for replenishment requests.
     * Prompts the administrator to approve a specific replenishment request 
     * by entering its ID.
     */
    public void approveReplenishmentRequestView(){
        while (true){
            replenishmentManager.viewAllReplenishmentRequest();
            System.out.println("Enter replenishmentID to approve:");
            int replenishmentID;
            if (scanner.hasNextInt()){
                replenishmentID = scanner.nextInt();
                if (replenishmentManager.replemnishmentRequestExist(replenishmentID)){
                    replenishmentManager.approveReplenishmentRequest(replenishmentID, medicineManager);
                    break;
                }
                else{
                    System.out.println("Invalid input. Try again");
                    continue;
                }
            }
            else{
                scanner.next();
                System.out.println("Invalid input. Try again");
                continue;
            }
        }
    }

    /**
     * Displays hospital staff based on various filters.
     * The administrator can choose to filter staff by hospital ID, user type, gender, age, or salary.
     */
    public void viewHospitalStaffView(){
        int choice;
        while (true){
            System.out.println("Filter by");
            System.out.println("1. Hospital ID");
            System.out.println("2. User Type");
            System.out.println("3. Gender");
            System.out.println("4. Age");
            System.out.println("5. Salary");
            System.out.println();
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()){
                choice = scanner.nextInt();
                if (1 <= choice && choice <= 5){
                    break;
                }
            }
            scanner.next();
            System.out.println("Invalid choice. Try again");
        }

        switch (choice){
            case 1:
                administratorViewing.viewStaffByID(userManager);
                break;
            case 2:
                administratorViewing.viewStaffByRole(userManager);
                break;
            case 3:
                administratorViewing.viewStaffByGender(userManager);
                break;
            case 4:
                administratorViewing.viewStaffByAge(userManager);
                break;
            case 5:
                administratorViewing.viewStaffBySalary(userManager);
            default:
                break;
        }
    }

    /**
     * Manages hospital staff operations, including adding, removing, or updating staff details.
     * Provides options to add new staff, remove existing staff, or update staff information.
     */
    public void manangeHospitalStaffView(){
        Staff staff;
        int hospitalID, choice;

        while (true){
            System.out.println("1. Add new staff");
            System.out.println("2. Remove staff");
            System.out.println("3. Update staff");
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
            System.out.println("Invalid Hospital ID. Try again");
        }

        if (choice == 4){
            return;
        }

        if (choice == 1){
            String newName, newPassword, newEmail;
            int newPhoneNumber, newAge, newSalary, currChoice, userTypeChoice, genderChoice;
            UserType newUserType;
            Gender newGender;

            System.out.print("Enter new name: ");
            newName = scanner.next();

            // System.out.print("Enter new password: ");
            // newPassword = scanner.next();

            System.out.print("Enter new email: ");
            newEmail = scanner.next();

            while (true){
                System.out.print("Enter new phone number: ");
                if (scanner.hasNextInt()){
                    newPhoneNumber = scanner.nextInt();
                    if (String.valueOf(newPhoneNumber).length() == 8){
                        break;
                    }
                }
                else{
                    scanner.next();
                }
                System.err.println("Invalid phone number. Must be 8 digits. Try again");
            }

            while (true){
                System.out.print("Enter new age: ");
                if (scanner.hasNextInt()){
                    newAge = scanner.nextInt();
                    if (newAge > 0){
                        break;
                    }
                }
                else{
                    scanner.next();
                }
                System.err.println("Invalid age. Try again");
            }

            while (true){
                System.out.print("Enter new salary: ");
                if (scanner.hasNextInt()){
                    newSalary = scanner.nextInt();
                    if (newSalary > 0){
                        break;
                    }
                }
                else{
                    scanner.next();
                }
                System.err.println("Invalid salary. Try again");
            }

            while (true){
                System.out.println("1. PATIENT");
                System.out.println("2. DOCTOR");
                System.out.println("3. PHARMACIST");
                System.out.println("4. ADMINISTRATOR");
                System.out.println();
                System.out.print("Enter your choice: ");

                if (scanner.hasNextInt()){
                    userTypeChoice = scanner.nextInt();
                    if (1 <= userTypeChoice && userTypeChoice <= 4){
                        break;
                    }
                }
                scanner.next();
                System.out.println("Invalid choice. Try again");
            }

            if (userTypeChoice == 1){
                newUserType = UserType.PATIENT;
            }
            else if (userTypeChoice == 2){
                newUserType = UserType.DOCTOR;
            }
            else if (userTypeChoice == 3){
                newUserType = UserType.PHARMACIST;
            }
            else{
                newUserType = UserType.ADMINISTRATOR;
            }


            while (true){
                System.out.println("1. MALE");
                System.out.println("2. FEMALE");
                System.out.println();
                System.out.print("Enter your choice: ");

                if (scanner.hasNextInt()){
                    genderChoice = scanner.nextInt();
                    if (1 <= genderChoice && genderChoice <= 2){
                        break;
                    }
                }
                else{
                    scanner.next();
                }
                System.out.println("Invalid choice. Try again");
            }

            if (genderChoice == 1){
                newGender = Gender.MALE;
            }
            else{
                newGender = Gender.FEMALE;
            }

            userManager.addStaff(0, newName, newEmail, newUserType, newPhoneNumber, newAge, newGender, newSalary);
            System.out.println("New staff added successfully");
        }

        else if (choice == 2){
            while (true){
                System.out.print("Enter Hospital ID: ");
                if (scanner.hasNextInt()){
                    hospitalID = scanner.nextInt();
                    if (userManager.userExist(hospitalID)){
                        userManager.removeUser(hospitalID);
                        break;
                    }
                }
                scanner.next();
                System.out.println("Invalid Hospital ID");
            }
            System.out.println("Staff removed successfully");
        }

        else if (choice == 3){
            while (true){
                System.out.print("Enter Hospital ID: ");
                if (scanner.hasNextInt()){
                    hospitalID = scanner.nextInt();
                    if (userManager.userExist(hospitalID)){
                        staff = (Staff) userManager.getUser(hospitalID);
                        staff.viewUser();
                        break;
                    }
                }
                scanner.next();
                System.out.println("Invalid Hospital ID");
            }

            while (true){
                System.out.println("Which information do you want to update");
                System.out.println("1. Hospital ID");
                System.out.println("2. Name");
                System.out.println("3. Password");
                System.out.println("4. Email");
                System.out.println("5. User Type");
                System.out.println("6. Phone Number");
                System.out.println("7. Age");
                System.out.println("8. Gender");
                System.out.println("9. Salary");
                System.out.println();
                System.out.print("Enter your choice: ");

                if (scanner.hasNextInt()){
                    choice = scanner.nextInt();
                    if (1 <= choice && choice <= 9){
                        break;
                    }
                }
                scanner.next();
                System.out.println("Invalid choice. Try again");
            }

            switch (choice){
                case 1:
                    int newHospitalID;
                    while (true){
                        System.out.print("Enter new Hospital ID: ");
                        if (scanner.hasNextInt()){
                            newHospitalID = scanner.nextInt();
                            if (!userManager.userExist(newHospitalID)){
                                staff.setHospitalID(newHospitalID);
                                break;
                            }
                        }
                        scanner.next();
                        System.out.println("Invalid Hospital ID. Try again");
                    }
                    break;

                case 2:
                    System.out.print("Enter new name: ");
                    String newName = scanner.next();
                    staff.setName(newName);
                    break;

                case 3:
                    System.out.print("Enter new password: ");
                    String newPassword = scanner.next();
                    staff.setPassword(newPassword);
                    break;

                case 4:
                    System.out.print("Enter new email: ");
                    String newEmail = scanner.next();
                    staff.setEmail(newEmail);
                    break;

                case 5:
                    int userTypeChoice;
                    while (true){
                        System.out.println("1. PATIENT");
                        System.out.println("2. DOCTOR");
                        System.out.println("3. PHARMACIST");
                        System.out.println("4. ADMINISTRATOR");
                        System.out.println();
                        System.out.print("Enter your choice: ");

                        if (scanner.hasNextInt()){
                            userTypeChoice = scanner.nextInt();
                            if (1 <= userTypeChoice && userTypeChoice <= 4){
                                break;
                            }
                        }
                        scanner.next();
                        System.out.println("Invalid choice. Try again");
                    }

                    switch (userTypeChoice){
                        case 1:
                            staff.setUserType(UserType.PATIENT);
                            break;
                        case 2:
                            staff.setUserType(UserType.DOCTOR);
                            break;
                        case 3:
                            staff.setUserType(UserType.PHARMACIST);
                            break;
                        case 4:
                            staff.setUserType(UserType.ADMINISTRATOR);
                            break;
                        default:
                            break;
                    }
                    break;

                case 6:
                    while (true){
                        System.out.print("Enter new phone number: ");
                        if (scanner.hasNextInt()){
                            int newPhoneNumber = scanner.nextInt();
                            staff.setPhoneNumber(newPhoneNumber);
                            break;
                        }
                        scanner.next();
                        System.out.println("Invalid choice. Try again");
                    }
                    break;
                    
                case 7:
                    while (true){
                        System.out.print("Enter new age: ");
                        if (scanner.hasNextInt()){
                            int newAge = scanner.nextInt();
                            staff.setAge(newAge);
                            break;
                        }
                        scanner.next();
                        System.out.println("Invalid choice. Try again");
                    }
                    break;

                case 8:
                    int genderChoice;
                    while (true){
                        System.out.println("1. MALE");
                        System.out.println("2. FEMALE");
                        System.out.println();
                        System.out.print("Enter your choice: ");

                        if (scanner.hasNextInt()){
                            genderChoice = scanner.nextInt();
                            if (1 <= genderChoice && genderChoice <= 2){
                                break;
                            }
                        }
                        scanner.next();
                        System.out.println("Invalid choice. Try again");
                    }

                    switch (genderChoice){
                        case 1:
                            staff.setGender(Gender.MALE);
                            break;
                        case 2:
                            staff.setGender(Gender.FEMALE);
                            break;
                        default:
                            break;
                    }

                case 9:
                    while (true){
                        System.out.print("Enter new salary: ");
                        if (scanner.hasNextInt()){
                            int newSalary = scanner.nextInt();
                            staff.setSalary(newSalary);
                            break;
                        }
                        scanner.next();
                        System.out.println("Invalid choice. Try again");
                    }
                    break;

                default:
                    break;
            }
            System.out.println("Staff updated successfully");
        }
        userManager.viewAllStaff();
    }

    /**
     * Displays details of all hospital appointments.
     * Allows the administrator to view all appointments in the system.
     */
    public void viewAppointmentDetailsView(){
        appointmentManager.viewAllAppointments();
    }
}



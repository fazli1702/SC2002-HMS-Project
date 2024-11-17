package view;

import java.util.Scanner;
import user.*;

/**
 * The AppView class handles the user interface for the main application.
 * Provides methods for the main menu, user login, and program exit.
 */
public class AppView {
    private Scanner scanner;
    private UserManager userManager;

    /**
     * Constructs an instance of AppView and initializes the scanner and user manager.
     * Displays a welcome message upon initialization.
     *
     * @param scanner the scanner object for user input
     * @param userManager the user manager for managing user-related operations
     */
    public AppView(Scanner scanner, UserManager userManager) {
        System.out.println("Welcome to HMS\n");
        this.scanner = scanner;
        this.userManager = userManager;
    }

    /**
     * Displays the main menu and captures the user's choice.
     * The menu includes options for logging in and exiting the program.
     *
     * @return the user's choice (1 for login, 2 for exit)
     */
    public int mainMenu(){
        int choice;
        while (true){
            System.out.println("Main Menu");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.println();
            System.out.print("Enter choice:");
            if (scanner.hasNextInt()){
                choice = scanner.nextInt();
                if (choice == 1 || choice == 2){
                    return choice;
                }
            }
            else{
                scanner.next();
            }
            System.out.println("Invalid input");
        }
    }

    public User login(){
        int hospitalID;
        String password;

        while (true){
            System.out.println("HMS LOGIN PAGE");
            System.out.println();
            System.out.print("Hospital ID: ");
            if (scanner.hasNextInt()){
                hospitalID = scanner.nextInt();
            }
            else{
                scanner.next();
                System.out.println("Invalid ID");
                continue;
            }

            System.out.print("Password: ");
            password = scanner.next();

            // check if hospitalID exist
            if (userManager.userExist(hospitalID)){
                User user = userManager.getUser(hospitalID);
                // check if user password match w corresponding hospital id
                // System.out.println("user password: " + user.getPassword());
                // System.out.println("Input password: " + password);
                if (user.verifyPassowrd(password)){
                    System.out.println("Login successful. Welcome " + user.getName());
                    return user;
                }
            }
            System.out.println("Login failed. Invalid Hospital ID or Password. Try again");
        }
    }

    /**
     * Exits the program with a termination message.
     * Calls the System.exit method to end the application.
     */
    public void exitProgram(){
        System.out.println("Terminating Program....");
        System.exit(0);
    }
}

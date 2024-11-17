package view;

import user.User;
import java.util.Scanner;

/**
 * The UpdatePasswordView class provides a user interface for updating a user's password.
 * It prompts the user to enter the current password and a new password, verifying the current password 
 * before allowing the password change.
 */
public class UpdatePasswordView {
    User user;
    Scanner scanner;
    
    /**
     * Constructs an {@code UpdatePasswordView} with the required dependencies.
     *
     * @param user The user object whose password is to be updated.
     * @param scanner The {@code Scanner} object for reading user input.
     */
    public UpdatePasswordView(User user, Scanner scanner){
        this.user = user;
        this.scanner = scanner;
    }

    /**
     * Prompts the user to enter the current password and a new password. If the current password 
     * is verified, the password is updated. If the current password is incorrect, an error message is displayed.
     */
    public void updatePassword(){
        String password;
        String newPassword;
        while (true){
            System.out.print("Enter current password: ");
            password = this.scanner.next();
            if (user.verifyPassowrd(password)){
                System.out.print("Enter new password: ");
                newPassword = this.scanner.next();
                user.setPassword(newPassword);
                System.out.println("Password updated");
                break;
            }
            else{
                System.out.println("Invlid current password. Try again");
            }
        }
    }
}

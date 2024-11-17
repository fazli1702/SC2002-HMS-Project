package csvreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import user.Gender;
import user.UserManager;
import user.UserType;

/**
 * Read a csv file containing staff information and save its content into userManager
 */
public class ReadCsvStaff implements IReadCsv{
    private UserManager userManager;

    /**
     * constructor of ReadCsvStaff
     * @param userManager
     */
    public ReadCsvStaff(UserManager userManager){
        this.userManager = userManager;
    }

    /**
     * Read a csv file containing staff information and save its content into userManager
     */
    public void readCsv(){
        String line, filePath;
        filePath = "HMS_Project/src/csvreader/csv/Staff_List.csv";
        // System.out.println(new File(".").getAbsolutePath());
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip header
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");

                // Parse fields
                int hospitalID = Integer.parseInt(fields[0].trim());
                String name = fields[1].trim();
                String email = fields[2].trim();
                UserType userType = UserType.valueOf(fields[3].trim().toUpperCase());
                int phoneNumber = Integer.parseInt(fields[4].trim());
                int age = Integer.parseInt(fields[5].trim());
                Gender gender = Gender.valueOf(fields[6].trim().toUpperCase());
                int salary = Integer.parseInt(fields[7].trim());

                // Call addStaff method
                userManager.addStaff(hospitalID, name, email, userType, phoneNumber, age, gender, salary);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
    }
}

package csvreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import user.Gender;
import user.UserManager;

/**
 * Read a csv file containing patient information and save its content into userManager
 */
public class ReadCsvPatient implements IReadCsv{
    private UserManager userManager;

    /**
     * constructor for ReadCsvPatient
     * @param userManager
     */
    public ReadCsvPatient(UserManager userManager){
        this.userManager = userManager;
    }

    /**
     * Read a csv file containing patient information and save its content into userManager
     */
    public void readCsv(){
        String line, filePath;
        filePath = "HMS_Project/src/csvreader/csv/Patient_List.csv";
        // System.out.println(new File(".").getAbsolutePath());
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip header
            line = br.readLine();

            while ((line = br.readLine()) != null) {
                // Split the line by commas, while trimming whitespace and removing quotes
                String[] fields = line.split(",");
                int patientID = Integer.parseInt(fields[0].trim());
                String name = fields[1].trim();
                String email = fields[2].trim();
                int phoneNumber = Integer.parseInt(fields[3].trim());
                int age = Integer.parseInt(fields[4].trim());
                Gender gender = Gender.valueOf(fields[5].trim().toUpperCase());
                String dob = fields[6].trim();
                String bloodType = fields[7].trim();

                // Call addPatient method
                userManager.addPatient(patientID, name, email, phoneNumber, age, gender, dob, bloodType);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
    }
}

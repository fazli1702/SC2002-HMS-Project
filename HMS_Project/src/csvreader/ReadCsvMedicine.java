package csvreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import inventory.MedicineManager;

/**
 * Read a csv file containing medcine information and save its content into medcineManager
 */
public class ReadCsvMedicine implements IReadCsv{
    private MedicineManager medicineManager;

    /**
     * constructor for ReadCsvMedicine
     * @param medicineManager
     */
    public ReadCsvMedicine(MedicineManager medicineManager){
        this.medicineManager = medicineManager;
    }

    /**
     * Read a csv file containing medcine information and save its content into medcineManager
     */
    public void readCsv(){
        String line, filePath;
        filePath = "HMS_Project/src/csvreader/csv/Medicine_List.csv";
        // System.out.println(new File(".").getAbsolutePath());
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip header
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");

                // Parse fields
                String medicineName = fields[0].trim();
                int quantity = Integer.parseInt(fields[1].trim());
                int lowStockQuantity =Integer.parseInt(fields[2].trim());

                // Call addStaff method
                medicineManager.addMedicine(medicineName, quantity, lowStockQuantity);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
    }
}

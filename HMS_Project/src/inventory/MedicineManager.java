package inventory;

import java.util.ArrayList;

/**
 * The MedicineManager class manages all the Medicine in the Inventory
 */
public class MedicineManager implements IMedicineManager{
    /**
     * ArrayList containing Medicine objects in inventory
     */
    private ArrayList<Medicine> medicineDB;


    public MedicineManager(){
        this.medicineDB = new ArrayList<Medicine>();
    }

    /**
     * This add a new Medicine into medicineDB and updates nextMedicineID
     * @param medicineName name of new Medicine
     * @param quantity quantity of new Medicine
     * @param lowStockQuantity minimum threshold quantity of new Medicine
     */
    public void addMedicine(String medicineName, int quantity, int lowStockQuantity){
        Medicine newMedicine = new Medicine(medicineName, quantity, lowStockQuantity);
        medicineDB.add(newMedicine);
    }
    
    public void increaseMedicineStock(String medicineName, int quantity){
        Medicine currMedicine = getMedicine(medicineName);
        currMedicine.setQuantity(currMedicine.getQuantity()+quantity);
        System.out.println("Stock increase");
    }

    public void decreaseMedicineStock(String medicineName, int quantity){
        Medicine currMedicine = getMedicine(medicineName);
        currMedicine.setQuantity(currMedicine.getQuantity()-quantity);
        System.out.println("Stock decrease");
    }

    /**
     * This removes a Medicine from medicineDB based on medicineID
     * @param medicineName name of this Medicine
     */
    public void removeMedicine(String medicineName){
        Medicine currMedicine;
        for (int i = 0; i < medicineDB.size(); i++){
            currMedicine = medicineDB.get(i);
            if (currMedicine.getMedicineName().equals(medicineName)){
                medicineDB.remove(i);
                return;
            }
        }
    }

    /**
     * This returns the Medicine based on the input medicineID
     * @param medicineName name of this Medicine
     * @return Medicine the object of this Medicine
     */
    public Medicine getMedicine(String medicineName){
        for (Medicine currMedicine : medicineDB){
            if (currMedicine.getMedicineName().equals(medicineName)){
                return currMedicine;
            }
        }
        return null;
    }

    /**
     * Goes through all Medicine in medicineDB and print its attributes
     */
    public void viewAllMedicine(){
        Medicine currMedicine;
        System.out.println(String.format("%-20s %-10s %-15s %-15s", "Name", "Quantity", "LowStockQty", "LowStockAlert"));
        System.out.println("----------------------------------------------------------------------");
        for (int i = 0; i < medicineDB.size(); i++){
            currMedicine = medicineDB.get(i);
            currMedicine.viewMedicine();
        }
        System.out.println("----------------------------------------------------------------------");
    }

    public Boolean medicineExist(String medicineName){
        Medicine medicine = getMedicine(medicineName);
        if (medicine != null){
            return true;
        }
        return false;
    }

}

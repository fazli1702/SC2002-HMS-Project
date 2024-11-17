package user;

import inventory.*;

/**
 * Contains methods related to inventory and administrator
 */
public class AdministratorInventory{

    /**
     * Increases the stock count of a particular medicine 
     * @param medicineManager manager of medicine
     * @param medicineName name of medicine
     * @param quantity quantity to be increased by
     */
    public void increaseMedicineStock(MedicineManager medicineManager, String medicineName, int quantity){
        medicineManager.increaseMedicineStock(medicineName, quantity);
    }
    
    /**
     * Decreases the stock count of a particular medicine
     * @param medicineManager manager of medicine
     * @param medicineName name of medicine
     * @param quantity quantity to be increased by
     */
    public void decreaseMedicineStock(MedicineManager medicineManager, String medicineName, int quantity){
        medicineManager.decreaseMedicineStock(medicineName, quantity);
    }

    /**
     * Updates the required quantity of medicine for there to be a low stock alert
     * @param medicineManager manager of medicine
     * @param medicineName name of medicine
     * @param lowStockQuantity quantitiy for there to be a low alert
     */
    public void updateLowStockQuantity(MedicineManager medicineManager, String medicineName, int lowStockQuantity){
        medicineManager.getMedicine(medicineName).setLowStockQuantity(lowStockQuantity);
    }

}
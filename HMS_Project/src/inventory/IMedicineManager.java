package inventory;

/**
 * Interface for medicine manager
 */
public interface IMedicineManager{
    public void addMedicine(String medicineName, int quantity, int lowStockQuantity);
    public void removeMedicine(String medicineName);
    public void viewAllMedicine();
}
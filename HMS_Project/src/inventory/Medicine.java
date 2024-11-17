package inventory;

/**
 * The Medicine class represents a medicine with its different attributes
 */
public class Medicine {
    /**
     * The name of the medicine
     */
    private String medicineName;
    /**
     * The quantity of the medicine in the inventory
     */
    private int quantity;
    /**
     * The minimum threshold value before being alerted for low stock
     */
    private int lowStockQuantity;
    /**
     * Alert if medicine below or above minimum threshold value (lowStockQuantity)
     */
    private Boolean lowStockAlert;

    /**
     * Constructor of Medicine with medicineName, quantity and lowStockQuantity
     * @param medicineName name of this Medicine
     * @param quantity quantity of this Medicine in inventory
     * @param lowStockQuantity threshold quantity for lowStockAlert
     */
    Medicine(String medicineName, int quantity, int lowStockQuantity){
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.lowStockQuantity = lowStockQuantity;
        this.lowStockAlert = quantity < lowStockQuantity;
    }

    /**
     * This returns the name of this Medicine
     * @return this medicineName
     */
    public String getMedicineName(){
        return this.medicineName;
    }

    /**
     * This sets the name of this Medicine to medicineName
     * @param medicineName new Medicine name to be set
     */
    public void setMedicineName(String medicineName){
        this.medicineName = medicineName;
    }

    /**
     * This returns the quantity of this medicine
     * @return this quantity
     */
    public int getQuantity(){
        return this.quantity;
    }

    /**
     * This sets the new quantity of this Medicine.
     * It also checks if the new quantity is below the lowStockQuantity
     * and updates lowStockAlert accordingly
     * @param quantity new quantity of Medicine
     */
    public void setQuantity(int quantity){
        this.quantity = quantity;
        if (this.quantity <= this.lowStockQuantity){
            this.setLowStockAlert(true);
        }
        else{
            this.setLowStockAlert(false);
        }
    }

    /**
     * This returns the lowStockQuantity value of this Medicine
     * @return this lowStockQuantity
     */
    public int getLowStockQuantity(){
        return this.lowStockQuantity;
    }

    /**
     * This sets the new quantity for this Medicine lowStockQuantity
     * @param lowStockQuantity threshold value for this Medicine lowStockAlert
     */
    public void setLowStockQuantity(int lowStockQuantity){
        this.lowStockQuantity = lowStockQuantity;
    }

    /**
     * This returns the Boolean value of this Medicine lowStockAlert
     * @return Boolean this lowStockAlert
     */
    public Boolean getLowStockAlert(){
        return this.lowStockAlert;
    }

    /**
     * This sets the new Boolean value of this Medicine lowStockAlert
     * @param lowStockAlert
     */
    public void setLowStockAlert(Boolean lowStockAlert){
        this.lowStockAlert = lowStockAlert;
    }

    /**
     * This prints the value of this Medicine variables
     */
    public void viewMedicine(){
        System.out.println(String.format("%-20s %-10d %-15d %-15s", medicineName, quantity, lowStockQuantity, lowStockAlert ? "YES" : "NO"));
    }
}

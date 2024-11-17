package inventory;

/**
 * The ReplenishmentRequest is a request to replenish a certain Medicine
 * with a certain amount
 */
public class ReplenishmentRequest {
    /**
     * The ID for the replenishment request
     */
    private int replenishmentRequestID;
    /**
     * The ID of the medicine requested
     */
    private String medicineNameRequested;
    /**
     * The quantity requested
     */
    private int quantityRequested;
    /**
     * The status of the request
     */
    private ReplenishmentStatus approvalStatus;

    /**
     * Constructor of ReplenishmentRequest with replenishmentRequestID, 
     * medicineIDRequested and quantityRequested
     * @param replenishmentRequestID ID of the replenishmenet request
     * @param medicineNameRequested Name of the medicine requested
     * @param quantityRequested requested quantity of the medicine
     */
    ReplenishmentRequest(int replenishmentRequestID, String medicineNameRequested, int quantityRequested){
        this.replenishmentRequestID = replenishmentRequestID;
        this.medicineNameRequested = medicineNameRequested;
        this.quantityRequested = quantityRequested;
        this.approvalStatus = ReplenishmentStatus.PENDING;
    }

    /**
     * This returns the ID of this replenishmentRequest
     * @return int this replenishmentRequestID
     */
    public int getReplenishmentRequestID(){
        return this.replenishmentRequestID;
    }

    /**
     * This sets the ID of this ReplenishmentRequest
     * @param replenishmentRequestID the new ID of this ReplenishmentRequest
     */
    public void setReplenishmentRequestID(int replenishmentRequestID){
        this.replenishmentRequestID = replenishmentRequestID;
    }

    /**
     * This returns the ID of the medicine requested of this replenishmentRequest
     * @return int this medicineIDRequested
     */
    public String getMedicineNameRequested(){
        return this.medicineNameRequested;
    }

    /**
     * This sets the ID of the medicine of this ReplenishmentRequest
     * @param medicineNameRequested the new name of this ReplenishmentRequest
     */
    public void setMedicineNameRequested(String medicineNameRequested){
        this.medicineNameRequested = medicineNameRequested;
    }

    /**
     * This returns the quantity requested of this ReplenishmentRequest
     * @return int this quantityRequested
     */
    public int getQuantityRequested(){
        return this.quantityRequested;
    }

    /**
     * This sets the quantity requested of this ReplenishmentRequest
     * @param quantityRequested new value of this quantityRequested
     */
    public void setQuantityRequested(int quantityRequested){
        this.quantityRequested = quantityRequested;
    }

    /**
     * This returns the approval status of this ReplenishmentRequest
     * @return RequestStatus this approvalStatus
     */
    public ReplenishmentStatus getApprovalStatus(){
        return this.approvalStatus;
    }

    /**
     * This sets the approval status of this ReplenishmentRequest
     * @param approvalStatus new approval status of this ReplenishmentRequest
     */
    public void setApprovalStatus(ReplenishmentStatus approvalStatus){
        this.approvalStatus = approvalStatus;
    }

    public void approveRequest(){
        this.setApprovalStatus(ReplenishmentStatus.APPROVED);
    }

    /**
     * This prints the attributes of this ReplenishmentRequest
     */
    public void viewRequest(){
        System.out.println(String.format("%-5d %-20s %-10d", replenishmentRequestID, medicineNameRequested, quantityRequested));
    }
}

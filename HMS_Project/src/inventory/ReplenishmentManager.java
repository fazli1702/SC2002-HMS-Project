package inventory;

import java.util.ArrayList;

/**
 * Manager class for Replenishment class
 */
public class ReplenishmentManager {
    /**
     * ArrayList of all ReplenishmentRequest objects
     */
    private ArrayList<ReplenishmentRequest> replenishmentRequests;
    /**
     * ID for the next ReplenishmentRequest added to replenishmentRequests
     */
    private int nextReplenishmentRequestID = 1;

    public ReplenishmentManager(){
        this.nextReplenishmentRequestID = 1;
        replenishmentRequests = new ArrayList<>();
    }

    /**
     * This approve the replenishment request based on its ID
     * and updates its approval status to ACCEPTED
     * @param replenishmentRequestID ID of the replenishment request
     */
    public void approveReplenishmentRequest(int replenishmentRequestID, MedicineManager medicineManager){
        ReplenishmentRequest currRequest;
        for (int i = 0; i < replenishmentRequests.size(); i++){
            currRequest = replenishmentRequests.get(i);
            if (currRequest.getReplenishmentRequestID() == replenishmentRequestID){
                currRequest.setApprovalStatus(ReplenishmentStatus.APPROVED);
                medicineManager.increaseMedicineStock(currRequest.getMedicineNameRequested(), currRequest.getQuantityRequested());
                System.out.println("approved");
                return;
            }
        }
    }

    /**
     * This returns the ReplenishmentRequest object based on the input ID
     * @param replenishmentRequestID ID of the replemnishmentRequest to be returned
     * @return ReplenishmentRequest object
     */
    public ReplenishmentRequest getReplenishmentRequest(int replenishmentRequestID){
        ReplenishmentRequest currRequest;
        for (int i = 0; i < replenishmentRequests.size(); i++){
            currRequest = replenishmentRequests.get(i);
            if (currRequest.getReplenishmentRequestID() == replenishmentRequestID){
                return currRequest;
            }
        }
        return null;
    } 

    /**
     * This creates a new replenishment request and add it into this replenishmentRequests
     * @param medicineNameRequested name of the medicine requested
     * @param quantityRequested quantity requested for the medicine
     */
    public void addReplenishmentRequest(String medicineNameRequested, int quantityRequested){
        ReplenishmentRequest newRequest = new ReplenishmentRequest(this.nextReplenishmentRequestID, medicineNameRequested, quantityRequested);
        replenishmentRequests.add(newRequest);
        this.nextReplenishmentRequestID++;
    }

    /**
     * This removes the replenishment request object based on its ID
     * @param replenishmentRequestID ID of the replenishment request
     */
    public void removeReplenishmentRequest(int replenishmentRequestID){
        ReplenishmentRequest currRequest;
        for (int i = 0; i < replenishmentRequests.size(); i++){
            currRequest = replenishmentRequests.get(i);
            if (currRequest.getReplenishmentRequestID() == replenishmentRequestID){
                replenishmentRequests.remove(i);
                return;
            }
        }
    }

    /**
     * This shows all the attributes of every replenishment request in this replenishmentRequests
     */
    public void viewAllReplenishmentRequest(){
        ReplenishmentRequest currRequest;
        System.out.println(String.format("%-5s %-20s %-10s", "ID", "MedicineName", "Quantity"));
        System.out.println("-------------------------------------------");
        for (int i = 0; i < replenishmentRequests.size(); i++){
            currRequest = replenishmentRequests.get(i);
            currRequest.viewRequest();
        }
    }

    public boolean replemnishmentRequestExist(int replenishmentID){
        ReplenishmentRequest replenishmentRequest = getReplenishmentRequest(replenishmentID);
        if (replenishmentRequest != null){
            return true;
        }
        return false;
    }
}

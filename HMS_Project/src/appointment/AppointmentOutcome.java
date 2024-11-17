package appointment;

import java.util.ArrayList;

/**
 * Stores info and contains methods related to the outcome of an appointment
 */
public class AppointmentOutcome {
    private int appointmentID;
	private String servicesProvided;
	private String prescription;
	private int prescriptionQuantity;
	private String consultationNotes;
	private RequestStatus prescriptionStatus;

    /**
     * Constructor
     * @param appointmentID the appointmentID of appointment 
     */
	public AppointmentOutcome(int appointmentID) {
        this.appointmentID = appointmentID;
        this.prescriptionStatus = RequestStatus.PENDING; // Assuming a default status
    }

    /**
     * Setter method for service, consultationNote, prescription and quantity together
     * @param services service provided during appointment
     * @param consultationNotes consultation notes during appointment
     * @param prescriptions medication prescribed to patient during appointment
     * @param quantities quantity of presribed medication
     */
    public void setAppointmentOutcome(String services, String consultationNotes, String prescriptions, int quantities) {
        this.servicesProvided = services;
        this.prescription = prescriptions;
        this.prescriptionQuantity = quantities;
        this.consultationNotes = consultationNotes;
    }

    /**
     * Prints the outcome of the appointment with formattingi
     * @param appointmentManager instance of AppointmentManager
     */
    public void viewAppointmentOutcome(AppointmentManager appointmentManager) {
        Appointment appointment = appointmentManager.getAppointment(appointmentID);
        System.out.println(String.format("%-8d %-8d %-8d %-15s %-10d %02d-%-2d-%-4d %-18s %-18s %-18s %-5d %-12s", 
        this.appointmentID, appointment.getDoctorID(), appointment.getPatientID(), appointment.getStatus(), appointment.getSlotNum(), 
        appointment.getDate().getDay(), appointment.getDate().getMonth(), appointment.getDate().getYear(), this.servicesProvided, this.consultationNotes, this.prescription, this.prescriptionQuantity, 
                        this.prescriptionStatus));
    }

    /**
     * Sets the status of prescription (Pending, Accepted)
     * @param status status of prescription
     */
    public void setPrescriptionStatus(RequestStatus status) {
        this.prescriptionStatus = status;
    }

    /**
     * prints the prescription status 
     */
    public void viewPrescriptionStatus() {
        System.out.println("Prescription Status: " + prescriptionStatus);
    }

    /**
     * 
     * @return status of the prescription
     */
    public RequestStatus getPresciptionStatus(){
        return this.prescriptionStatus;
    }

    /**
     * Sets prescription
     * @param prescription prescription being set
     */
    public void setPrescription(String prescription){
        this.prescription = prescription;
    }


    /**
     * 
     * @return prescription for the appointment
     */
    public String getPrescription(){
        return this.prescription;
    }

    /**
     * Sets the prescription quantity
     * @param prescriptionQuantity quantity of prescribed medication
     */
    public void setPrescriptionQuantity(int prescriptionQuantity){
        this.prescriptionQuantity = prescriptionQuantity;
    }

    /**
     * 
     * @return prescription quantity
     */
    public int getPrescriptionQuantity(){
        return this.prescriptionQuantity;
    }

    /**
     * Sets the consultation note of the appointment outcome
     */    public void setConsultationNotes(String consultationNotes){
        this.consultationNotes = consultationNotes;
    }

   

    /** 
     * views the consulation notes
     */    public void viewConsultationNotes() {
        System.out.println("Consultation Notes: " + consultationNotes);
    }
        public String getConsultationNotes(){
        return this.consultationNotes;
    }


    /**
     * Sets the service provided of appointment
     * @param serviceProvided service provided during appointment
     */     public void setServicesProvided(String serviceProvided){
    }



}

package appointment;

import java.util.*;
import timeslot.*;
import timeslot.Date;

/**
 * Appointment class containing all information and methods related to appointment
 */
public class Appointment {
    private int appointmentID;
    private int patientID;
    private int doctorID;
    private RequestStatus status;
    private int slotNum;
    private Date date;
    // private int outcomeRecord;
    private AppointmentOutcome appointmentOutcome;
    private DoctorAvailabilityManager doctorAvailabilityManager; // Reference to DoctorAvailabilityManager

    // Constructor
    /**
     * Constructor method with required parameter
     * @param appointmentID id of appointment
     * @param patientID hospital id of patient
     * @param doctorID hospital id of doctor
     * @param slotNum slot number of appointment
     * @param date date of appointment
     * @param doctorAvailabilityManager manager for doctorAvailability
     */
    public Appointment(int appointmentID, int patientID, int doctorID, int slotNum, Date date, DoctorAvailabilityManager doctorAvailabilityManager) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.date = date;
        this.doctorAvailabilityManager = doctorAvailabilityManager;
        this.status = RequestStatus.PENDING;
        this.slotNum = slotNum;
        this.appointmentOutcome = new AppointmentOutcome(appointmentID);
        // this.outcomeRecord = -1;

        // Check and assign time slot through DoctorAvailabilityManager
        doctorAvailabilityManager.removeDoctorAvailability(date.getDay(), slotNum, doctorID);
        
    }

    // Method to reschedule appointment
    // public void rescheduleAppointment(int newSlotNum, Date newDate) {
    //     if (doctorAvailabilityManager.getTimeSlot(newSlotNum, newDate) != null) {
    //         doctorAvailabilityManager.removeFreeTimeSlot(this.date.getDate(), this.timeSlot, this.doctorID);
    //         this.date = newDate;
    //         this.timeSlot = newSlotNum;
    //         doctorAvailabilityManager.setFreeTimeSlot(newDate.getDate(), newSlotNum, doctorID);
    //     } else {
    //         throw new UnsupportedOperationException("Requested reschedule time slot is not available.");
    //     }
    // }

    // Getters and Setters
    /**
     * get patient id
     * @return patientID
     */
    public int getPatientID() {
        return this.patientID;
    }

    /**
     * set patient id
     * @param patientID new value
     */
    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    /**
     * get doctor id
     * @return doctorID
     */
    public int getDoctorID() {
        return this.doctorID;
    }

    /**
     * set doctorID
     * @param doctorID new value
     */
    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    /**
     * get appointmentID
     * @return appointmentID
     */
    public int getAppointmentID() {
        return this.appointmentID;
    }

    /**
     * set appointment ID
     * @param appointmentID new value
     */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * get status of appointment
     * @return enum RequestStatus
     */
    public RequestStatus getStatus() {
        return this.status;
    }

    /**
     * set status of appointment
     * @param status new value
     */
    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    /**
     * get date of appointment
     * @return date of appointment
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * get slot number of appointment
     * @return slot number
     */
    public int getSlotNum() {
        return this.slotNum;
    }

    /**
     * set slot number of appointment
     * @param slotNum new value
     */
    public void setSlotNum(int slotNum){
        this.slotNum = slotNum;
    }
    
    /**
     * get outcome of appointment
     * @return outcome of appointment
     */
    public AppointmentOutcome getAppointmentOutcome() {
    	return this.appointmentOutcome;
    }

    /**
     * print appointment information
     */
    public void viewAppointment(){
        System.out.println(String.format("%-8d %-8d %-8d %-15s %-10d %02d-%-2d-%-4d", appointmentID, doctorID, patientID, status, slotNum, date.getDay(), date.getMonth(), date.getYear()));
    }
}

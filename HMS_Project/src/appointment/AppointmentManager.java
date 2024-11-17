package appointment;

import java.util.*;
import timeslot.*;
import timeslot.Date;
import user.*;


/**
 * Manages all appointment objects
 */
public class AppointmentManager implements IAppointmentManager{
    /**
     * id value for the next appointment created
     */
	private int nextAppointmentID;
    /**
     * stores all the appointment in this ArrayList
     */
	private ArrayList<Appointment> appointmentsDB;

    /**
     * Constructor for AppointmentManager
     */
    public AppointmentManager() {
        this.nextAppointmentID = 1;
        this.appointmentsDB = new ArrayList<>();
    }

    /**
     * prints all appointment in appointmentsDB
     */
    public void viewAllAppointments() {
        if (appointmentsDB.isEmpty()) {
            System.out.println("No appointments scheduled.");
            return;
        }
        System.out.println(String.format("%-8s %-8s %-8s %-15s %-10s %-8s", "ApptID","DocID","PatID","Status","SlotNum","Date"));
        for (Appointment appointment : appointmentsDB) {
            appointment.viewAppointment();
        }
    }

    /**
     * prints appointment by filtering based on request status
     * @param requestStatus used for filtering appointments
     */
    public void viewAppointmentByFilter(RequestStatus requestStatus) {
        System.out.println(String.format("%-8s %-8s %-8s %-15s %-10s %-8s", "ApptID","DocID","PatID","Status","SlotNum","Date"));
        for (Appointment appointment : appointmentsDB) {
            if (appointment.getStatus() == requestStatus) {
                appointment.viewAppointment();
            }
        }
    }

    /**
     * prints appointment based on patientID
     * @param patientID used for filtering appointments
     */
    public void viewAppointmentByPatient(int patientID) {
        System.out.println(String.format("%-8s %-8s %-8s %-15s %-10s %-8s", "ApptID","DocID","PatID","Status","SlotNum","Date"));
        for (Appointment appointment : appointmentsDB) {
            if (appointment.getPatientID() == patientID) {
                appointment.viewAppointment();
            }
        }
    }

    /**
     * prints appointment based on doctorID
     * @param doctorID used for filtering appointments
     */
    public void viewAppointmentByDoctor(int doctorID) {
        System.out.println(String.format("%-8s %-8s %-8s %-15s %-10s %-8s", "ApptID","DocID","PatID","Status","SlotNum","Date"));
        for (Appointment appointment : appointmentsDB) {
            if (appointment.getDoctorID() == doctorID) {
                appointment.viewAppointment();
            }
        }
    }

    /**
     * prints appointment based on doctorID and requestStatus
     * @param doctorID first parameter used for filtering
     * @param requestStatus second parameter used for filtering
     */
    public void viewAppointmentByDoctorFilter(int doctorID, RequestStatus requestStatus) {
        System.out.println(String.format("%-8s %-8s %-8s %-15s %-10s %-8s", "ApptID","DocID","PatID","Status","SlotNum","Date"));
        for (Appointment appointment : appointmentsDB) {
            if ((appointment.getDoctorID() == doctorID) && (appointment.getStatus() == requestStatus)) {
                appointment.viewAppointment();
            }
        }
    }

    /**
     * add a new appointment into appointmentDB
     * @param patientID hospital id of patient
     * @param doctorID hospital id of doctor
     * @param timeSlot time slot of appointment
     * @param date date of appointment
     * @param doctorAvailabilityManager manager for doctorAvailability
     * @param userManager manager for users
     */
    public void addAppointment(int patientID, int doctorID, int timeSlot, Date date, DoctorAvailabilityManager doctorAvailabilityManager, UserManager userManager) {
        Appointment newAppointment = new Appointment(nextAppointmentID++, patientID, doctorID, timeSlot, date, doctorAvailabilityManager);
        appointmentsDB.add(newAppointment);
        // doctorAvailabilityManager.removeDoctorAvailability(date.getDay(), timeSlot, doctorID);
        // Doctor doctor = (Doctor) userManager.getUser(doctorID);
        // doctor.setUnavailable(timeSlot, date.getDay(),date.getMonth(),date.getYear());
        System.out.println("Appointment added");
    }

    /**
     * remove appointment from appointmentDB
     * @param appointmentID id of appointment
     * @param doctorAvailabilityManager manager for doctorAvailability
     * @param userManager manger for user
     */
    public void removeAppointment(int appointmentID, DoctorAvailabilityManager doctorAvailabilityManager, UserManager userManager) {
        Appointment appointmentToRemove = getAppointment(appointmentID);
        int doctorID = appointmentToRemove.getDoctorID();
        Doctor doctor = (Doctor) userManager.getUser(doctorID);
        doctor.setAvailable(appointmentToRemove.getSlotNum(), appointmentToRemove.getDate().getDay(), appointmentToRemove.getDate().getMonth(), appointmentToRemove.getDate().getYear());
        doctorAvailabilityManager.addDoctorAvailability(appointmentToRemove.getDate().getDay(), appointmentToRemove.getSlotNum(), doctorID);
        appointmentsDB.remove(appointmentToRemove);
        System.out.println("Appointment removed");
    }

    /**
     * get appointment based on appointmentID
     * @param appointmentID id of appointment
     * @return appointment 
     */
    public Appointment getAppointment(int appointmentID) {
        for (Appointment appointment : appointmentsDB) {
            if (appointment.getAppointmentID() == appointmentID) {
                return appointment;
            }
        }
        return null;
    }

    /**
     * delete old appointment and reschedule it to a new selected date
     * @param appointmentID id of appointment
     * @param patientID hospital id of patient
     * @param doctorID hospital id of doctor 
     * @param timeSlot time slot of appointment
     * @param date date of appointment
     * @param doctorAvailabilityManager manager for doctorAvailability
     * @param userManager manager for user
     */
    public void rescheduleAppointment(int appointmentID, int patientID, int doctorID, int timeSlot, Date date, DoctorAvailabilityManager doctorAvailabilityManager, UserManager userManager){
        removeAppointment(appointmentID, doctorAvailabilityManager, userManager);
        addAppointment(patientID, doctorID, timeSlot, date, doctorAvailabilityManager, userManager);
    }

    /**
     * get entire ArrayList of appointmentDB
     * @return appointmentDB
     */
    public ArrayList<Appointment> getAppointmentDB(){
        return this.appointmentsDB;
    }

    /**
     * view all appointment outcome of appointments that are already completed based on patientID
     * @param patientID hospital id of patient
     * @param appointmentManager manager for appointment
     */
    public void viewPastAppointmentOutcome(int patientID, AppointmentManager appointmentManager){
        System.out.println(String.format("%-8s %-8s %-8s %-15s %-10s %-10s %-18s %-18s %-18s %-5s", 
        "ApptID","DocID","PatID","Status","SlotNum","Date","ServiceProvided","ConsulationNotes","Prescription","Qty"));
        for (Appointment appointment : appointmentsDB) {
            if ((appointment.getPatientID() == patientID) && (appointment.getStatus() == RequestStatus.COMPLETED)) {
                appointment.getAppointmentOutcome().viewAppointmentOutcome(appointmentManager);
            }
        }
    }

    /**
     * print all appointment outcomes for all completed appointment
     * @param appointmentManager manager for appointment
     */
    public void viewAllAppointmentOutcome(AppointmentManager appointmentManager){
        System.out.println(String.format("%-8s %-8s %-8s %-15s %-10s %-10s %-18s %-18s %-18s %-5s ", 
        "ApptID","DocID","PatID","Status","SlotNum","Date","ServiceProvided","ConsulationNotes","Prescription","Qty"));
        for (Appointment appointment : appointmentsDB){
            appointment.getAppointmentOutcome().viewAppointmentOutcome(appointmentManager);
        }
    }
}
package user;

import timeslot.*;
import timeslot.Date;
import appointment.*;
import java.util.*;

/**
 * Contain methods related to doctor and appointment
 */
public class DoctorAppointment {

    /**
     * Constructor
     */
    public DoctorAppointment(){}
    
    /**
     * Doctor accepts the appointment and updates personal calender and overall calender
     * @param doctor instance of doctor that is accepting the pending appointment
     * @param appointmentID appointmentID of pending appointment
     * @param appointmentManager instance of AppointmentManager
     * @param doctorAvailabilityManager instance of DoctorAvailabilityManager
     */
    public void acceptAppointment(Doctor doctor, int appointmentID, AppointmentManager appointmentManager, DoctorAvailabilityManager doctorAvailabilityManager){
        Appointment appointment = appointmentManager.getAppointment(appointmentID);
        appointment.setStatus(RequestStatus.ACCEPTED);
        doctor.setUnavailable(appointment.getSlotNum(), appointment.getDate().getDay(), appointment.getDate().getMonth(), appointment.getDate().getYear());
        doctorAvailabilityManager.removeDoctorAvailability(appointment.getDate().getDay(), appointment.getSlotNum(), doctor.getHospitalID());
    }

    /**
     * Doctor decline the appointment 
     * @param doctor instance of doctor that is accepting the pending appointment
     * @param appointmentID appointmentID of pending appointment
     * @param appointmentManager instance of AppointmentManager
     * @param doctorAvailabilityManager instance of DoctorAvailabilityManager
     */
    public void declineAppointment(Doctor doctor, int appointmentID, AppointmentManager appointmentManager, DoctorAvailabilityManager doctorAvailabilityManager){
        Appointment appointment = appointmentManager.getAppointment(appointmentID);
        appointment.setStatus(RequestStatus.REJECTED);
    }

    /**
     * Records outcome of the appointment and sets appointment as COMPLETED
     * @param appointmentID appointmentID of completed appointment
     * @param appointmentManager instance of AppointmentManager
     * @param services services provided during the appointment
     * @param consultationNotes consultation notes of the appointment
     * @param prescriptions prescription provided to patient during appointment
     * @param quantities amount of prescription
     */
    public void recordAppointmentOutcome(int appointmentID, AppointmentManager appointmentManager, String services, String consultationNotes, String prescriptions, int quantities){
        appointmentManager.getAppointment(appointmentID).setStatus(RequestStatus.COMPLETED);
        appointmentManager.getAppointment(appointmentID).getAppointmentOutcome().setAppointmentOutcome(services, consultationNotes, prescriptions, quantities);
    }
}
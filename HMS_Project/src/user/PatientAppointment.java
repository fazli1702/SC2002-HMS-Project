package user;

import timeslot.*;
import timeslot.Date;
import appointment.*;
import java.util.*;

/**
 * Contains methods related to Patient class and appointment
 */
public class PatientAppointment{

    /**
     * Constructor
     */
    public PatientAppointment(){}

    /**
     * Schedules an appointment for patient
     * @param patient instance of Patient that is scheduling the appointment
     * @param doctorAvailabilityManager instance of DoctorAvailabilityManager
     * @param timeslot the timeslot that is chosen by patient
     * @param day day chosen by patient
     * @param appointmentManager instance of AppointmentManager
     * @param userManager instance of UserManager
     */
    public void scheduleAppointment(Patient patient, DoctorAvailabilityManager doctorAvailabilityManager, int timeslot, int day, AppointmentManager appointmentManager, UserManager userManager){
        int doctorID = doctorAvailabilityManager.getAvailableDoctor(day, timeslot);
        appointmentManager.addAppointment(patient.getHospitalID(), doctorID, timeslot, new Date(day,12,2024), doctorAvailabilityManager, userManager);
        System.out.println("Appointment scheduled");
    }

    /**
     * Cancels an appointment that patient has already scheduled
     * @param appointmentID appointmentID of appointment being cancelled
     * @param appointmentManager insance of AppointmentManager
     * @param doctorAvailabilityManager isntance of DoctorAvailabilityManager
     * @param userManager instance of UserManager
     */
    public void cancelAppointment(int appointmentID, AppointmentManager appointmentManager, DoctorAvailabilityManager doctorAvailabilityManager,  UserManager userManager){
        appointmentManager.removeAppointment(appointmentID, doctorAvailabilityManager, userManager);
    }

    /**
     * Prints the appointment status of appointments scheduled by patient
     * @param patient instance of patient 
     * @param appointmentManager instance of AppointmentManager
     */
    public void viewAppointmentStatus(Patient patient, AppointmentManager appointmentManager){
        appointmentManager.viewAppointmentByPatient(patient.getHospitalID());
    }

    
}
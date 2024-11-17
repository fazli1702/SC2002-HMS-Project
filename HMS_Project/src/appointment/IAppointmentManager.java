package appointment;

import timeslot.*;
import user.*;

/**
 * interface for appointment manager
 */
public interface IAppointmentManager {
    void addAppointment(int patientID, int doctorID, int timeSlot, Date date, DoctorAvailabilityManager doctorAvailabilityManager, UserManager userManager);
    void removeAppointment(int appointmentID, DoctorAvailabilityManager doctorAvailabilityManager, UserManager userManager);
    void viewAllAppointments();
    void viewAppointmentByFilter(RequestStatus requestStatus);
}
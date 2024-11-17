package user;

import inventory.*;
import appointment.*;

/**
 * Pharmacist class that is a sub class of Staff
 */
public class Pharmacist extends Staff{
    /**
     * Constructor
     * @param name name of pharmacist
     * @param hospitalID hospitalID of pharmacist
     * @param email email of pharmacist
     * @param phoneNumber phone number of pharmacist
     * @param age age of pharmacist
     * @param gender gender of pharmacist
     * @param salary salary of pharmacist
     */
    public Pharmacist(String name, int hospitalID, String email, int phoneNumber, int age, Gender gender, int salary){
        super(hospitalID, name, email, UserType.PHARMACIST, phoneNumber, age, gender, salary);
    }

    /**
     * Dispenses the prescription stated in appointment outcome of a particular appointment
     * Causes stock level of medicine to fall
     * @param appointmentID appointmentID of chosen appointment
     * @param appointmentManager instance of AppointmentManager
     * @param medicineManager instance of MedicineManager
     */
    public void dispensePrescription(int appointmentID, AppointmentManager appointmentManager, MedicineManager medicineManager){
        AppointmentOutcome appointmentOutcome = appointmentManager.getAppointment(appointmentID).getAppointmentOutcome();
        appointmentManager.getAppointment(appointmentID).getAppointmentOutcome().setPrescriptionStatus(RequestStatus.ACCEPTED);
        medicineManager.decreaseMedicineStock(appointmentOutcome.getPrescription(), appointmentOutcome.getPrescriptionQuantity());
    }

    /**
     * Checks if there is sufficient stock to dispense the medicine
     * @param appointmentID appointmentID of appointment that pharmacist wants to dispense
     * @param appointmentManager instance of AppointmentManager
     * @param medicineManager instance of MedicineManager
     * @return
     */
    public Boolean isSufficientPrescription(int appointmentID, AppointmentManager appointmentManager, MedicineManager medicineManager){
        AppointmentOutcome appointmentOutcome = appointmentManager.getAppointment(appointmentID).getAppointmentOutcome();
        return ((medicineManager.getMedicine(appointmentOutcome.getPrescription()).getQuantity()) >= (appointmentOutcome.getPrescriptionQuantity()));
    }

    
}
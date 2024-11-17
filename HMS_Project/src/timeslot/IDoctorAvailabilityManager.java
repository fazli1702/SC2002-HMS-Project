package timeslot;

/**
 * Basic interface for Manager of doctor availability
 */
public interface IDoctorAvailabilityManager {
    //interface methods
	public void addDoctorAvailability(int date, int slotNum, int doctorID);
	public void removeDoctorAvailability(int date, int slotNum, int doctorID);
	public void checkDoctorAvailability(int date, int slotNum, int doctorID);
}

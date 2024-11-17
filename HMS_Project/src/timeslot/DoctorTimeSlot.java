package timeslot;

import java.util.ArrayList;

/**
 * Class to hold availability of doctors
 */
public class DoctorTimeSlot{
	
	//class fields
	/**
	 * ArrayList of doctor ID(s) as Integer object
	 * If doctor ID is in ArrayList, doctor is available
	 */
	private ArrayList<Integer> availableDoctors;
	
	//constructor
	public DoctorTimeSlot() {
		availableDoctors = new ArrayList<Integer>();
	}
	
	//class methods
	/**
	 * Adds doctor to ArrayList, sets doctor as available
	 * @param doctorID
	 */
	public void addAvailableDoctor(int doctorID) {
		if (!availableDoctors.contains(Integer.valueOf(doctorID))) {
			availableDoctors.add(Integer.valueOf(doctorID));
		}
	}
	/**
	 * Removes doctor to ArrayList, sets doctor as unavailable
	 * @param doctorID
	 */
	public void removeAvailableDoctor(int doctorID) {
		availableDoctors.remove(Integer.valueOf(doctorID));
	}
	/**
	 * Checks if doctor is in ArrayList
	 * @param doctorID
	 * @return True if doctor is in ArrayList and vice versa
	 */
	public boolean isDoctorAvailable(int doctorID) {
		return availableDoctors.contains(doctorID);
	}
	/**
	 * Finds the next available doctor in the ArrayList
	 * @return Next available doctor's ID
	 */
	public int getNextAvailableDoctor() {
		return availableDoctors.get(0);
	}
	/**
	 * Prints all available doctors in ArrayList
	 */
	public void printAllAvailableDoctors() {
		System.out.println(availableDoctors.toString());
	}
	/**
	 * Checks if ArrayList is empty/no available doctors
	 * @return True if no doctors in ArrayList and vice versa
	 */
	public boolean hasAvailableDoctors() {
		return !availableDoctors.isEmpty();
	}
}
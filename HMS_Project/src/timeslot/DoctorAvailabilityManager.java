package timeslot;

import java.util.ArrayList;

/**
 * Manager class for Doctor Availability
 */
public class DoctorAvailabilityManager implements IDoctorAvailabilityManager{
	
	/**
	 * ArrayList of TimeSlotDate objects
	 */
	private ArrayList<TimeSlotDate> doctorAvailabilityDB;
	
	/**
	 * Contructor 
	 */
	public DoctorAvailabilityManager() {
		doctorAvailabilityDB = new ArrayList<TimeSlotDate>();
		for (int i = 1; i < 8; i++){
			doctorAvailabilityDB.add(new TimeSlotDate(i,12,2024));
		}
	}
	
	//class methods
	/**
	 * Set doctor as available for a given date and timeslot
	 * Prints availability added or not
	 * If availability is not added, maybe TimeSlotDate for the given date does not exist
	 * @param date ddmmyyyy format
	 */
	public void addDoctorAvailability(int date, int slotNum, int doctorID) {
		int i;
		for (i=0; i<doctorAvailabilityDB.size(); i++) {
			if (doctorAvailabilityDB.get(i).getDay() == date) {
				doctorAvailabilityDB.get(i).getTimeSlot(slotNum).addAvailableDoctor(doctorID);
				System.out.printf("Doctor of ID: %d added to Slot %d on %s\n", doctorID, slotNum, doctorAvailabilityDB.get(i).getDateReadable());
				return;
			}
		}
		System.out.printf("Doctor of ID: %d NOT added to Slot %d on %s\n", doctorID, slotNum, doctorAvailabilityDB.get(i).getDateReadable());
	}
	/**
	 * Set doctor as unavailable for a given date and timeslot
	 * Prints availability removed or not
	 * If availability is not removed, maybe TimeSlotDate for the given date does not exist
	 * @param date ddmmyyyy format
	 * @param slotNum the slot number (1-7) selected
	 * @param doctorID HospitalID of doctor being made unavailable
	 */
	public void removeDoctorAvailability(int date, int slotNum, int doctorID) {
		int i;
		for (i=0; i<doctorAvailabilityDB.size(); i++) {
			if (doctorAvailabilityDB.get(i).getDay() == date) {
				doctorAvailabilityDB.get(i).getTimeSlot(slotNum).removeAvailableDoctor(doctorID);
				System.out.printf("Doctor of ID: %d removed from Slot %d on %s\n", doctorID, slotNum, doctorAvailabilityDB.get(i).getDateReadable());
				return;
			}
		}
		System.out.printf("Doctor of ID: %d NOT removed from Slot %d on %s\n", doctorID, slotNum, doctorAvailabilityDB.get(i).getDateReadable());
	}
	/**
	 * Check availabality of a doctor
	 * Prints if doctor is available or not
	 * Prints if TimeSlotDate for given date does not exist
	 * @param date ddmmyyyy format
	 * @param slotNum the slot number (1-7) selected
	 * @param doctorID HospitalID of doctor being checked
	 */
	public void checkDoctorAvailability(int date, int slotNum, int doctorID) {
		int i;
		for (i=0; i<doctorAvailabilityDB.size(); i++) {
			if (doctorAvailabilityDB.get(i).getDate() == date) {
				if (doctorAvailabilityDB.get(i).getTimeSlot(slotNum).isDoctorAvailable(doctorID)) {
					System.out.printf("Doctor of ID: %d is available at Slot %d on %s\n", doctorID, slotNum, doctorAvailabilityDB.get(i).getDateReadable());
					return;
				}
				else {
					System.out.printf("Doctor of ID: %d is not available at Slot %d on %s\n", doctorID, slotNum, doctorAvailabilityDB.get(i).getDateReadable());
					return;
				}
			}
		}
		System.out.printf("TimeSlotDate for %s does not exist\n", doctorAvailabilityDB.get(i).getDateReadable());
	}
	/**
	 * Adds TimeSlotDate for given date
	 * Prints if either TimeSlotDate exists or is added
	 * @param date ddmmyyyy format
	 */
	public void addTimeSlotDate(int date) {
		for (int i=0; i<doctorAvailabilityDB.size(); i++) {
			if (doctorAvailabilityDB.get(i).getDate() == date) {
				System.out.println("TimeSlotDate already exists");
				return;
			}
		}
		doctorAvailabilityDB.add(new TimeSlotDate(date));
		sortdoctorAvailabilityDB(doctorAvailabilityDB);
		System.out.println("TimeSlotDate added");
	}
	/**
	 * Remove TimeSlotDate for given date
	 * Prints if either TimeSlotDate not found or is removed
	 * @param date ddmmyyyy format
	 */
	public void removeTimeSlotDate(int date) {
		for (int i=0; i<doctorAvailabilityDB.size(); i++) {
			if (doctorAvailabilityDB.get(i).getDate() == date) {
				doctorAvailabilityDB.remove(i);
				System.out.println("TimeSlotDate removed");
				return;
			}
		}
		System.out.println("TimeSlotDate not found");
	}
	/**
	 * Sorts doctorAvailabilityDB by chronological order, earliest first 
	 * @param list doctorAvailabilityDB
	 */
	private void sortdoctorAvailabilityDB(ArrayList<TimeSlotDate> list) {
		list.sort((o1, o2)-> o1.getDateyyyymmdd().compareTo(o2.getDateyyyymmdd()));
	}
	/**
	 * Prints every doctor's availability for every date and timeslot
	 */
	public void printAllTimeSlotDate() {
		sortdoctorAvailabilityDB(doctorAvailabilityDB);
		for (int i=0; i<doctorAvailabilityDB.size(); i++) {
			System.out.printf("Date: %s\n", doctorAvailabilityDB.get(i).getDateReadable());
			for (int j=0; j<7; j++) {
				System.out.printf("Slot %d all available doctors: ", j+1);
				doctorAvailabilityDB.get(i).getTimeSlot(j).printAllAvailableDoctors();
			}
		}
	}
	/**
	 * Prints every doctor's availability for a given date
	 * @param date ddmmyyyy format
	 */
	public void printTimeSlotByDate(int date) {
		sortdoctorAvailabilityDB(doctorAvailabilityDB);
		for (int i=0; i<doctorAvailabilityDB.size(); i++) {
			if (doctorAvailabilityDB.get(i).getDate() == date) {
				System.out.printf("Date: %s\n", doctorAvailabilityDB.get(i).getDateReadable());
				for (int j=0; j<7; j++) {
					System.out.printf("Slot %d all available doctors: ", j+1);
					doctorAvailabilityDB.get(i).getTimeSlot(j).printAllAvailableDoctors();
				}
				// System.out.println();
			}
		}
	}
	/**
	 * Prints every doctor's availability for a given slot
	 * @param SlotNum slot to check for
	 */
	public void printTimeSlotBySlot(int SlotNum) {
		sortdoctorAvailabilityDB(doctorAvailabilityDB);
		for (int i=0; i<doctorAvailabilityDB.size(); i++) {
			System.out.printf("Date: %s\n", doctorAvailabilityDB.get(i).getDateReadable());
			System.out.printf("Slot %d available doctors: ", SlotNum);
			doctorAvailabilityDB.get(i).getTimeSlot(SlotNum).printAllAvailableDoctors();
		}
	}
	/**
	 * Set doctor as available for every slot possible
	 * @param doctorID
	 */
	public void addDoctorToAll(int doctorID) {
		for (int i=0; i<doctorAvailabilityDB.size(); i++) {
			// System.out.printf("Date: %s\n", doctorAvailabilityDB.get(i).getDateReadable());
			for (int j=0; j<7; j++) {
				if (doctorAvailabilityDB.get(i).getTimeSlot(j+1) != null)
					doctorAvailabilityDB.get(i).getTimeSlot(j+1).addAvailableDoctor(doctorID);
				// System.out.printf("Doctor of ID: %d added to Slot %d on %s\n", doctorID, j+1, doctorAvailabilityDB.get(i).getDateReadable());
				else{
					doctorAvailabilityDB.get(i).setTimeSlot(j+1, doctorID);
				}
			}
		}
	}
	/**
	 * Set doctor as unavailable for every slot possible
	 * @param doctorID
	 */
	public void removeDoctorFromAll(int doctorID) {
		for (int i=0; i<doctorAvailabilityDB.size(); i++) {
			System.out.printf("Date: %s\n", doctorAvailabilityDB.get(i).getDateReadable());
			for (int j=0; j<7; j++) {
				doctorAvailabilityDB.get(i).getTimeSlot(j+1).removeAvailableDoctor(doctorID);
				System.out.printf("Doctor of ID: %d removed from Slot %d on %s\n", doctorID, j+1, doctorAvailabilityDB.get(i).getDateReadable());
			}
		}
	}

	/**
	 * Shows all time slots and whether there are any availble doctors or not
	 */
	public void viewAvailableTimeSlots(){
		System.out.println(String.format("%-15s %-10s %-10s %-10s %-10s %-10s %-10s %-10s","Date", "0900-1000","1000-1100","1100-1200",
        "1300-1400","1400-1500","1500-1600","1600-1700"));
        for (int i = 0; i < 92; i++){
			System.out.print("-");
		}
		System.out.print("\n");
		for (TimeSlotDate t : doctorAvailabilityDB){
		// t.getTimeSlot(1).hasAvailableDoctors()
		System.out.print(String.format("%02d-%-2d-%-9d ", t.getDay(), t.getMonth(), t.getYear()));
		for (int i = 1; i < 8; i++){
			if (t.getTimeSlot(i).hasAvailableDoctors()){
				System.err.print(String.format("%-10d ", 1));
			}
			else{
				System.err.print(String.format("%-10d ", 0));
			}
		}
		System.out.println();
		}
	}

	/**
	 * Checks whether there is any available doctor for given date and time slot
	 * @param day day of chosen date
	 * @param slotNum slot number of chosen slot
	 * @return true if there is an available doctor else false
	 */
	public Boolean hasAvailableDoctor(int day, int slotNum){
		return doctorAvailabilityDB.get(day).getTimeSlot(slotNum).hasAvailableDoctors();
	}

	/**
	 * @param day day of selected date
	 * @param timeslot slot number of chosen slot
	 * @return doctorID of next available doctor
	 */
	public int getAvailableDoctor(int day, int timeslot){
		return doctorAvailabilityDB.get(day).getTimeSlot(timeslot).getNextAvailableDoctor();
	}
}

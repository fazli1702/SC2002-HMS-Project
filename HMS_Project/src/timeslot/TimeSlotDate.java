package timeslot;

/**
 * Inherits from Date class
 * Contains some more specified methods for handling doctor availability as well as for sorting
 */
public class TimeSlotDate extends Date{
	
	//class fields
	/**
	 * yymmdddd format
	 */
	private int date; //ddmmyyyy
	/**
	 * Array of timeslots, default is 7 timeslots per date
	 */
	private DoctorTimeSlot[] timeSlots;
	
	//constructors
	public TimeSlotDate(int day, int month, int year) {
		super(day, month, year);
		timeSlots = new DoctorTimeSlot[7];
		date = (day*1000000)+(month*10000)+year;
	}
	public TimeSlotDate(int date) {
		super(Integer.parseInt(Integer.toString(date).substring(0, 2)), Integer.parseInt(Integer.toString(date).substring(2, 4)), Integer.parseInt(Integer.toString(date).substring(4)));
		this.date = date;
	}
	
	//class methods
	/**
	 * Returns date
	 * @return date in yymmdddd format
	 */
	public int getDate() {
		return date;
	}
	/**
	 * Sets date of TimeSlotDate given day, month, year
	 * @param day int
	 * @param month int
	 * @param year int
	 */
	public void setDate(int day, int month, int year) {
		this.setDay(day);
		this.setMonth(month);
		this.setYear(year);
		date = (day*1000000)+(month*10000)+year;
	}
	/**
	 * Sets date of TimeSlotDate given date
	 * @param date ddmmyyyy format
	 */
	public void setDate(int date) {
		this.date = date;
		this.setDay(Integer.parseInt(Integer.toString(date).substring(0, 2)));
		this.setMonth(Integer.parseInt(Integer.toString(date).substring(2, 4)));
		this.setYear(Integer.parseInt(Integer.toString(date).substring(4)));
	}
	/**
	 * Returns a String object of date in a more readable format
	 * @return String of date with / between day, month and year
	 */
	public String getDateReadable() {
		return String.format("%d/%d/%d", super.getDay(), super.getMonth(), super.getYear());
	}
	/**
	 * Method to drive sorting of TimeSlotDate
	 * @return date in yyyymmdd format as Integer object
	 */
	public Integer getDateyyyymmdd() {
		return (super.getYear()*10000)+(super.getMonth()*100)+super.getDay();
	}
	/**
	 * Getter method for timeSlots
	 * @param SlotNum slot number to get
	 * @return DoctorTimeSlot of specified slot number
	 */
	public DoctorTimeSlot getTimeSlot(int SlotNum) {
		return timeSlots[SlotNum-1];
	}

	/**
	 * Adds a doctor to a chosen time slot
	 * @param slotNum slot number selected
	 * @param doctorID HospitalID of doctor selected
	 */
	public void setTimeSlot(int slotNum, int doctorID){
		timeSlots[slotNum- 1] = new DoctorTimeSlot();
		timeSlots[slotNum-1].addAvailableDoctor(doctorID);
	}


}
